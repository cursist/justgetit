package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Klant;
import be.vdab.justgetit.repositories.KlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultKlantService implements KlantService {
    private final KlantRepository klantRepository;

    public DefaultKlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    @Override
    public List<Klant> findAll() {
        return klantRepository.findAll();
    }
    @Override
    public Optional<Klant>findById(long klantId){
        return  klantRepository.findById(klantId);
    }
    @Override
    public List<Klant> findByGemeentenaamBegin(String str){
        return klantRepository.findByGemeenteStartingWithOrderByNaam(str);
    }
    @Override
    public List<Klant> findByPostcode(String str){
        return klantRepository.findByGemeentePostcodeOrderByGemeentePostcode(str);
    }
    @Override
    public List<Klant> findByVoornaamBevat(String str){
        return klantRepository.findByVoornaamContainingOrderByNaam(str);
    }
}
