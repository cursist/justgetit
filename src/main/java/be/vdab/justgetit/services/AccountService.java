package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Gemeente;
import be.vdab.justgetit.entities.Klant;
import be.vdab.justgetit.entities.Land;
import be.vdab.justgetit.entities.Provincie;
import be.vdab.justgetit.repositories.GemeenteRepository;
import be.vdab.justgetit.repositories.KlantRepository;
import be.vdab.justgetit.repositories.LandRepository;
import be.vdab.justgetit.repositories.ProvincieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@Transactional(readOnly = false, isolation = READ_COMMITTED)
public class AccountService {
    private KlantRepository klantRepo;
    private GemeenteRepository gemeenteRepo;
    private ProvincieRepository provincieRepo;
    private LandRepository landRepo;

    public AccountService(KlantRepository klantRepo, GemeenteRepository gemeenteRepo, ProvincieRepository provincieRepo, LandRepository landRepo) {
        this.klantRepo = klantRepo;
        this.gemeenteRepo = gemeenteRepo;
        this.provincieRepo = provincieRepo;
        this.landRepo = landRepo;
    }

    public Klant save(Klant klant){
        Gemeente gemeente = klant.getGemeente();
        Provincie provincie = gemeente.getProvincie();
        Land land = provincie.getLand();
        landRepo.save(land);
        provincieRepo.save(provincie);
        gemeenteRepo.save(gemeente);
        return klantRepo.saveAndFlush(klant);
    }
}
