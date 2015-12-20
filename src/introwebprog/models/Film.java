package introwebprog.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by matteo on 19/12/15.
 */
public class Film {
    private int idFilm;
    private String titolo;
    private String urlTrailer;
    private Integer durata;
    private String trama;
    private String uriLocandina;
    private int idGenere;

    public int getIdGenere() {return idGenere;}
    public void setIdGenere(int idGenere) {this.idGenere = idGenere;}
    public int getIdFilm() {
        return idFilm;
    }
    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }
    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public String getUrlTrailer() {
        return urlTrailer;
    }
    public void setUrlTrailer(String urlTrailer) {
        this.urlTrailer = urlTrailer;
    }
    public Integer getDurata() {
        return durata;
    }
    public void setDurata(Integer durata) {
        this.durata = durata;
    }
    public String getTrama() {
        return trama;
    }
    public void setTrama(String trama) {
        this.trama = trama;
    }
    public String getUriLocandina() {
        return uriLocandina;
    }
    public void setUriLocandina(String uriLocandina) {
        this.uriLocandina = uriLocandina;
    }
}
