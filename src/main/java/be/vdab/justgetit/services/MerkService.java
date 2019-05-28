package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Subcategorie;

import java.util.Optional;

public interface MerkService {
    Optional<Merk> findByNaamLike(String naam);
    Optional<Merk> findById(long id);
}
