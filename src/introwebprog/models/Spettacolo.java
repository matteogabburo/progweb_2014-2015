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
    private int idFilm;

    public int getIdSpettacolo() {
        return idSpettacolo;
    }
    public void setIdSpettacolo(int idSpettacolo) {
        this.idSpettacolo = idSpettacolo;
    }
    public Timestamp getDataOra() {
        return dataOra;
    }

    public String getDataOraToString()
    {
        String res = "";

        res += "Giorno : "+this.dataOra.getDay();
        res += "/"+this.dataOra.getMonth();
        res += "/"+this.dataOra.getYear();

        res += " alle "+ this.dataOra.getHours();
        res += ":"+ this.dataOra.getMinutes();

        return res;
    }

    public void setDataOra(Timestamp dataOra) {
        this.dataOra = dataOra;
    }

    public int getIdFilm() {return idFilm;}
    public void setIdFilm(int idFilm) {this.idFilm = idFilm;}

    public int getIdSala() {return idSala;}
    public void setIdSala(int idSala) {this.idSala = idSala;}
}
