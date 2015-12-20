package introwebprog.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by matteo on 19/12/15.
 */
@Entity
public class Posto {
    private int idPosto;
    private Integer riga;
    private Integer colonna;
    private Boolean esiste;

    @Id
    @Column(name = "ID_POSTO")
    public int getIdPosto() {
        return idPosto;
    }

    public void setIdPosto(int idPosto) {
        this.idPosto = idPosto;
    }

    @Basic
    @Column(name = "RIGA")
    public Integer getRiga() {
        return riga;
    }

    public void setRiga(Integer riga) {
        this.riga = riga;
    }

    @Basic
    @Column(name = "COLONNA")
    public Integer getColonna() {
        return colonna;
    }

    public void setColonna(Integer colonna) {
        this.colonna = colonna;
    }

    @Basic
    @Column(name = "ESISTE")
    public Boolean getEsiste() {
        return esiste;
    }

    public void setEsiste(Boolean esiste) {
        this.esiste = esiste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Posto posto = (Posto) o;

        if (idPosto != posto.idPosto) return false;
        if (riga != null ? !riga.equals(posto.riga) : posto.riga != null) return false;
        if (colonna != null ? !colonna.equals(posto.colonna) : posto.colonna != null) return false;
        if (esiste != null ? !esiste.equals(posto.esiste) : posto.esiste != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPosto;
        result = 31 * result + (riga != null ? riga.hashCode() : 0);
        result = 31 * result + (colonna != null ? colonna.hashCode() : 0);
        result = 31 * result + (esiste != null ? esiste.hashCode() : 0);
        return result;
    }
}
