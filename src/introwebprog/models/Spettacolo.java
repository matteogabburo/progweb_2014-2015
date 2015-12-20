package introwebprog.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by matteo on 19/12/15.
 */

public class Spettacolo {
    private int idSpettacolo;
    private Timestamp dataOra;
    private int idSala;

    public int getIdSpettacolo() {
        return idSpettacolo;
    }
    public void setIdSpettacolo(int idSpettacolo) {
        this.idSpettacolo = idSpettacolo;
    }
    public Timestamp getDataOra() {
        return dataOra;
    }
    public void setDataOra(Timestamp dataOra) {
        this.dataOra = dataOra;
    }

    public int getIdSala() {return idSala;}
    public void setIdSala(int idSala) {this.idSala = idSala;}
}
