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
    private int idSala;

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

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }
}
