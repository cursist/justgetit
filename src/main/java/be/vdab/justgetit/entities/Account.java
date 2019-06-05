package be.vdab.justgetit.entities;

import javax.persistence.*;

@Entity
@Table(name = "klantenaccounts")
public class Account {
    @Id
    private long klantId;
    private String accountnaam;
    private String wachtwoord;

    protected Account() {}

    public Account(long klantId, String accountnaam, String wachtwoord) {
        this.klantId = klantId;
        this.accountnaam = accountnaam;
        this.wachtwoord = wachtwoord;
    }

    public String getAccountnaam() {
        return accountnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }
}
