package be.vdab.justgetit.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AantalForm {
    @NotNull
    @Positive
    @Min(1)
    private final Integer aantal;

    public AantalForm (Integer aantal) {
        this.aantal = aantal;
    }

    public Integer getAantal() {
        return aantal;
    }
}
