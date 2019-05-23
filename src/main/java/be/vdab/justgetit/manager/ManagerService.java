package be.vdab.justgetit.manager;

import be.vdab.justgetit.domain.Categorie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService {
    public List<Categorie> vindAlleCategorieen() {
        Categorie een = new Categorie("een");
        Categorie twee = new Categorie("twee");
        List<Categorie> lijst = new ArrayList<>();
        lijst.add(een);
        lijst.add(twee);
        return lijst;
    }

    public void save(Categorie categorie) {
    }
}
