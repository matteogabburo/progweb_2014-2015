package introwebprog.dao;

import introwebprog.models.Film;
import introwebprog.models.Spettacolo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by matteo on 18/12/15.
 */
public class MultisalaDAO
{

    private final String DB_URL = "jdbc:derby://localhost:1527//home/matteo/Documenti/Scuola/programmazioneWeb/CinemaMultisala/DB/cinemamultisalaDB";
    private final String USER = " ";
    private final String PASS = " ";


    public List<Spettacolo> allShowsByIdFilm(int idFilm)
    {
        String query = "select * from APP.FILM" +
                " left join APP.SPETTACOLO on FILM.ID_FILM = SPETTACOLO.ID_FILM" +
                " where film.id_film = "+idFilm+";";

        ResultSet rs = launchQuery(query);
        try
        {
            //work with data
            ArrayList<Spettacolo> res = null;
            while(rs.next()){
                //Retrieve by column name
                Spettacolo s = new Spettacolo();

                s.setIdSpettacolo(rs.getInt("ID_SPETTACOLO"));
                s.setDataOra(rs.getTimestamp("DATA_ORA"));
                s.setIdSala(rs.getInt("ID_SALA"));

                res.add(s);
            }

            //close rs
            rs.close();

            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


        return null;
    }
    public ArrayList<Film> allFilms() {
        String query = "select * from APP.FILM";

        //ResultSet rs = launchQuery(query);


        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());

            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }


        System.out.println("********************************************************");

        try
        {
            //work with data
            ArrayList<Film> res = new ArrayList<Film>();
            if(rs != null)
                while(rs.next()){
                    //Retrieve by column name
                    Film s = new Film();

                    s.setIdFilm(rs.getInt("ID_FILM"));
                    s.setTitolo(rs.getString("TITOLO"));
                    s.setDurata(rs.getInt("DURATA"));
                    s.setTrama(rs.getString("TRAMA"));
                    s.setUriLocandina(rs.getString("URI_LOCANDINA"));
                    s.setUrlTrailer(rs.getString("URL_TRAILER"));
                    s.setIdGenere(rs.getInt("ID_GENERE"));

                    System.out.println("Ciao ****************************");

                    res.add(s);
                }
            System.out.println("Buonasera ****************************");

            //close rs, conn, and stmt
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
            if(conn != null)
                conn.close();

            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public Film getFilmById(int idfilm)
    {
        String query = "select * from APP.FILM WHERE APP.FILM.ID_FILM = "+idfilm;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        Film s = null;

        try {
            //work with data
            if(rs != null) { //Retrieve by column name
                while(rs.next()) {
                    s = new Film();

                    s.setIdFilm(rs.getInt("ID_FILM"));
                    s.setTitolo(rs.getString("TITOLO"));
                    s.setDurata(rs.getInt("DURATA"));
                    s.setTrama(rs.getString("TRAMA"));
                    s.setUriLocandina(rs.getString("URI_LOCANDINA"));
                    s.setUrlTrailer(rs.getString("URL_TRAILER"));
                    s.setIdGenere(rs.getInt("ID_GENERE"));
                }
            }
            //close rs, conn, and stmt
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }


    //*************************************************************
    //ATTENTION : REMEMBER TO CLOSE RESULTSET WHO RETURN FROM THIS
    //METHOD
    //*************************************************************
    private ResultSet launchQuery(String query)
    {
        Connection conn = null;
        Statement stmt = null;

        try
        {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());

            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            stmt.close();
            conn.close();

            return rs;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

}
