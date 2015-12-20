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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prenotazione that = (Prenotazione) o;

        if (idPrenotazione != that.idPrenotazione) return false;
        if (dataOraOperazione != null ? !dataOraOperazione.equals(that.dataOraOperazione) : that.dataOraOperazione != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPrenotazione;
        result = 31 * result + (dataOraOperazione != null ? dataOraOperazione.hashCode() : 0);
        return result;
    }
}
