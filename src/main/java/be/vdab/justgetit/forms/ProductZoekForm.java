package be.vdab.justgetit.forms;

import javax.validation.constraints.NotNull;

public class ProductZoekForm {
    @NotNull

    private final String zoekTerm;

    public ProductZoekForm(String zoekTerm) {
        this.zoekTerm = zoekTerm;
    }


    public String getZoekTerm() {
        return zoekTerm;
    }
}
