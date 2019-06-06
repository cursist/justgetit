package be.vdab.justgetit.services;

public interface BediendeService {
    void zetProductInSubCategorie(long productid, long subcategorieid);
    long bepaalMinimaleVerkoopprijs(long productid);
}
