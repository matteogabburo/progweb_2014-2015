package introwebprog.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

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
        /*
        res += "Giorno : "+this.dataOra.getDay();
        res += "/"+this.dataOra.getMonth();
        res += "/"+this.dataOra.getYear();

        res += " alle "+ this.dataOra.getHours();
        res += ":"+ this.dataOra.getMinutes();
        */

        Date date = new Date(this.dataOra.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        String res = sdf.format(date);

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
