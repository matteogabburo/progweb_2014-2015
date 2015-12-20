package introwebprog.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by matteo on 19/12/15.
 */
@Entity
public class Ruolo {
    private int idRuolo;
    private String ruolo;

    @Id
    @Column(name = "ID_RUOLO")
    public int getIdRuolo() {
        return idRuolo;
    }

    public void setIdRuolo(int idRuolo) {
        this.idRuolo = idRuolo;
    }

    @Basic
    @Column(name = "RUOLO")
    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ruolo ruolo1 = (Ruolo) o;

        if (idRuolo != ruolo1.idRuolo) return false;
        if (ruolo != null ? !ruolo.equals(ruolo1.ruolo) : ruolo1.ruolo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRuolo;
        result = 31 * result + (ruolo != null ? ruolo.hashCode() : 0);
        return result;
    }
}
