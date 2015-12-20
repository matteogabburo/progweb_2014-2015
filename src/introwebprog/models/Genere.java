package introwebprog.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by matteo on 19/12/15.
 */
@Entity
public class Genere {
    private int idGenere;
    private String descrizione;

    @Id
    @Column(name = "ID_GENERE")
    public int getIdGenere() {
        return idGenere;
    }

    public void setIdGenere(int idGenere) {
        this.idGenere = idGenere;
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

        Genere genere = (Genere) o;

        if (idGenere != genere.idGenere) return false;
        if (descrizione != null ? !descrizione.equals(genere.descrizione) : genere.descrizione != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGenere;
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        return result;
    }
}
