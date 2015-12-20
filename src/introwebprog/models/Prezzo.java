package introwebprog.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by matteo on 19/12/15.
 */
@Entity
public class Prezzo {
    private int idPrezzo;
    private String tipo;
    private Double prezzo;

    @Id
    @Column(name = "ID_PREZZO")
    public int getIdPrezzo() {
        return idPrezzo;
    }

    public void setIdPrezzo(int idPrezzo) {
        this.idPrezzo = idPrezzo;
    }

    @Basic
    @Column(name = "TIPO")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "PREZZO")
    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prezzo prezzo1 = (Prezzo) o;

        if (idPrezzo != prezzo1.idPrezzo) return false;
        if (tipo != null ? !tipo.equals(prezzo1.tipo) : prezzo1.tipo != null) return false;
        if (prezzo != null ? !prezzo.equals(prezzo1.prezzo) : prezzo1.prezzo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPrezzo;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (prezzo != null ? prezzo.hashCode() : 0);
        return result;
    }
}
