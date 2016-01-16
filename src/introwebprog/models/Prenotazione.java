package introwebprog.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by matteo on 19/12/15.
 */
@Entity
public class Prenotazione {
    private int idPrenotazione;
    private Timestamp dataOraOperazione;
    private int idPrezzo;
    private int idPosto;
    private int idSpettacolo;
    private int idUtente;

    @Id
    @Column(name = "ID_PRENOTAZIONE")
    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    @Basic
    @Column(name = "DATA_ORA_OPERAZIONE")
    public Timestamp getDataOraOperazione() {
        return dataOraOperazione;
    }

    public void setDataOraOperazione(Timestamp dataOraOperazione) {
        this.dataOraOperazione = dataOraOperazione;
    }

    public int getIdPrezzo() {
        return idPrezzo;
    }

    public void setIdPrezzo(int idPrezzo) {
        this.idPrezzo = idPrezzo;
    }

    public int getIdPosto() {
        return idPosto;
    }

    public void setIdPosto(int idPosto) {
        this.idPosto = idPosto;
    }

    public int getIdSpettacolo() {
        return idSpettacolo;
    }

    public void setIdSpettacolo(int idSpettacolo) {
        this.idSpettacolo = idSpettacolo;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
}
