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

}
