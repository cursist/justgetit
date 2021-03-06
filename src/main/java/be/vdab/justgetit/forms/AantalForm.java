package be.vdab.justgetit.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AantalForm {
    @NotNull
    @Positive
    @Min(1)
    private final Integer aantal;
    private final Long id;

    public AantalForm(Integer aantal, Long id) {
        this.aantal = aantal;
        this.id = id;
    }

    public Integer getAantal() {
        return aantal;
    }

    public Long getId() {
        return id;
    }
}
