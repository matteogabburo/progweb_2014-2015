package introwebprog.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by matteo on 19/12/15.
 */
@Entity
public class Utente {
    private int idUtente;
    private String email;
    private String password;
    private double credito;

    @Id
    @Column(name = "ID_UTENTE")
    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "CREDITO")
    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utente utente = (Utente) o;

        if (idUtente != utente.idUtente) return false;
        if (Double.compare(utente.credito, credito) != 0) return false;
        if (email != null ? !email.equals(utente.email) : utente.email != null) return false;
        if (password != null ? !password.equals(utente.password) : utente.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idUtente;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        temp = Double.doubleToLongBits(credito);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
