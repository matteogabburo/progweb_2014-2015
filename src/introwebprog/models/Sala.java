package introwebprog.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by matteo on 19/12/15.
 */
@Entity
public class Sala {
    private int idSala;
    private String descrizione;

    @Id
    @Column(name = "ID_SALA")
    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    @Basic
    @Column(name = "DESCRIZIONE")
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sala sala = (Sala) o;

        if (idSala != sala.idSala) return false;
        if (descrizione != null ? !descrizione.equals(sala.descrizione) : sala.descrizione != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSala;
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        return result;
    }
}
