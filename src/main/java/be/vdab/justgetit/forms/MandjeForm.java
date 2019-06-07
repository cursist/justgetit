package be.vdab.justgetit.forms;

import be.vdab.justgetit.entities.Product;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class MandjeForm {
    private final Map<Product, AantalForm> mandje;

    public MandjeForm() {
        this.mandje = new LinkedHashMap<>();
    }

    public void put(Product product){
        mandje.put(product, new AantalForm(null, null));
    }

    public void put(Product product, int aantal){
        mandje.put(product, new AantalForm(aantal, product.getId()));
    }

    public void remove(Product product){
        mandje.remove(product);
    }

    public Map<Product, AantalForm> getMandje() {
        return Collections.unmodifiableMap(mandje);
    }

    public int getLengte() {
        return mandje.size();
    }
}
