package be.vdab.justgetit.forms;

import be.vdab.justgetit.entities.Product;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class MandjeForm {
    private final Map<Product, AantalForm> mandjeForm;

    public MandjeForm() {
        this.mandjeForm = new LinkedHashMap<>();
    }

    public void put(Product product){
        mandjeForm.put(product, new AantalForm(null));
    }

    public void put(Product product, int aantal){
        mandjeForm.put(product, new AantalForm(aantal));
    }

    public void remove(Product product){
        mandjeForm.remove(product);
    }

    public Map<Product, AantalForm> getMandjeForm() {
        return Collections.unmodifiableMap(mandjeForm);
    }

    public int getLengte() {
        return mandjeForm.size();
    }
}
