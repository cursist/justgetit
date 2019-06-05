package be.vdab.justgetit.forms;

import javax.validation.constraints.NotBlank;

public class BediendeZoekForm {
    @NotBlank
    private String zoekterm;

    public BediendeZoekForm (String zoekterm) {
        this.zoekterm = zoekterm;
    }

    public String getZoekterm () {
        return zoekterm;
    }
}
