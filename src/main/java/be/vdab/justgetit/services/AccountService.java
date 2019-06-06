package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.*;
import be.vdab.justgetit.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@Transactional(readOnly = false, isolation = READ_COMMITTED)
public class AccountService {
    private KlantRepository klantRepo;
    private GemeenteRepository gemeenteRepo;
    private ProvincieRepository provincieRepo;
    private LandRepository landRepo;
    //private RolRepository rolRepo;

    public AccountService(KlantRepository klantRepo, GemeenteRepository gemeenteRepo, ProvincieRepository provincieRepo, LandRepository landRepo /*,RolRepository rolRepo*/) {
        this.klantRepo = klantRepo;
        this.gemeenteRepo = gemeenteRepo;
        this.provincieRepo = provincieRepo;
        this.landRepo = landRepo;
        //this.rolRepo = rolRepo;
    }

    public Klant save(Klant klant){
        bewaarSubEntities(klant);
        String nieuwWachtwoord = "{noop}"+klant.getWachtwoord();
        klant.setWachtwoord(nieuwWachtwoord);
        //vervangRollen(klant);
        return klantRepo.saveAndFlush(klant);
    }

    private void bewaarSubEntities(Klant klant) {
        Gemeente gemeente = klant.getGemeente();
        Provincie provincie = gemeente.getProvincie();
        Land land = provincie.getLand();
        if (!landRepo.existsByNaam(land.getNaam())) {
            landRepo.save(land);
        } else {
            Land eerderOpgeslagenLand = landRepo.findByNaam(land.getNaam());
            provincie.setLand(eerderOpgeslagenLand);
        }
        if (!provincieRepo.existsByNaam(provincie.getNaam())) {
            provincieRepo.save(provincie);
        } else {
            Provincie eerderOpgeslagenProvincie = provincieRepo.findByNaam(provincie.getNaam());
            gemeente.setProvincie(eerderOpgeslagenProvincie);
        }
        if (!gemeenteRepo.existsByNaam(gemeente.getNaam())) {
            gemeenteRepo.save(gemeente);
        } else {
            Gemeente eerderOpgeslagenGemeente = gemeenteRepo.findByNaam(gemeente.getNaam());
            klant.setGemeente(eerderOpgeslagenGemeente);
        }
    }

    /*private void vervangRollen(Klant klant) {
        Set<Rol> rollen = klant.getRollen();
        Set<Rol> echteRollen =
                rollen.stream()
                        .map(Rol::getNaam)
                        .map(Enum::toString)
                        .map(rolRepo::findByNaam)
                        .collect(Collectors.toSet());
        klant.replaceRollen(echteRollen);
    }*/
}
