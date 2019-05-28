package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.repositories.MerkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
public class DefaultMerkService implements MerkService{

    private final MerkRepository merkrepo;

    DefaultMerkService(MerkRepository merkrepo){
        this.merkrepo = merkrepo;
    }

    @Override
    public Optional<Merk> findById(long id) {
        return merkrepo.findById(id);
    }

    @Override
    public Optional<Merk> findByNaamLike(String naam) {
        return merkrepo.findByNaamLike(naam);
    }
}
