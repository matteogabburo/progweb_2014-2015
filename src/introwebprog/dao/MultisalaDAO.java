package introwebprog.dao;

import introwebprog.models.Film;
import introwebprog.models.Spettacolo;
import introwebprog.models.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matteo on 18/12/15.
 */
public class MultisalaDAO {

    private final String DB_URL = "jdbc:derby://localhost:1527//home/matteo/Documenti/Scuola/programmazioneWeb/CinemaMultisala/DB/cinemamultisalaDB";
    private final String USER = " ";
    private final String PASS = " ";


    public List<Spettacolo> allShowsByIdFilm(int idFilm) {
        String query = "select * from APP.FILM" +
                " left join APP.SPETTACOLO on FILM.ID_FILM = SPETTACOLO.ID_FILM" +
                " where film.id_film = " + idFilm + ";";


        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            //work with data
            ArrayList<Spettacolo> res = null;
            while (rs.next()) {
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
        } catch (SQLException e) {
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

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());

            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            //work with data
            ArrayList<Film> res = new ArrayList<Film>();
            if (rs != null)
                while (rs.next()) {
                    //Retrieve by column name
                    Film s = new Film();

                    s.setIdFilm(rs.getInt("ID_FILM"));
                    s.setTitolo(rs.getString("TITOLO"));
                    s.setDurata(rs.getInt("DURATA"));
                    s.setTrama(rs.getString("TRAMA"));
                    s.setUriLocandina(rs.getString("URI_LOCANDINA"));
                    s.setUrlTrailer(rs.getString("URL_TRAILER"));
                    s.setIdGenere(rs.getInt("ID_GENERE"));

                     res.add(s);
                }

            //close rs, conn, and stmt
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();

            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Film getFilmById(int idfilm) {
        String query = "select * from APP.FILM WHERE APP.FILM.ID_FILM = " + idfilm;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Film s = null;

        try {
            //work with data
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
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
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }

    public Spettacolo getSpettacoloById(int idfilm)
    {
        String query = "select * from APP.SPETTACOLO WHERE APP.SPETTACOLO.ID_FILM = " + idfilm;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Spettacolo s = null;

        try {
            //work with data
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    s = new Spettacolo();

                    s.setIdSpettacolo(rs.getInt("ID_SPETTACOLO"));
                    s.setIdSala(rs.getInt("ID_SALA"));
                    s.setIdFilm(idfilm);
                    s.setDataOra(rs.getTimestamp("DATA_ORA"));
                }
            }
            //close rs, conn, and stmt
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }

    public boolean registerNewUser(Utente u) {

        int maxid = this.getMaxUserId();
        u.setIdUtente(maxid + 1);

        String query = "INSERT INTO APP.UTENTE (ID_UTENTE, ID_RUOLO, EMAIL, PASSWORD, CREDITO) " +
                "VALUES ("+u.getIdUtente()+",2,'"+u.getEmail()+"','"+u.getPassword()+"',"+u.getCredito()+")";

        Connection conn = null;
        PreparedStatement stmt = null;

        int rs = 0;
        boolean s = false;

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.prepareStatement(query);
            rs = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(rs > 0)
            s = true;

        try{
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }

    private int getMaxUserId()
    {
        String query = "select MAX(ID_UTENTE)from APP.UTENTE";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int s = 0;

        try {
            //work with data
            if (rs != null)
            { //Retrieve by column name
                while (rs.next()) {
                    s = rs.getInt(1);
                }
            }

            //close rs, conn, and stmt
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }

    public boolean existThisMail(String mail) {
        String query = "select EMAIL from APP.UTENTE where EMAIL='"+mail+"'";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean ret = false;

        try {
            //work with data
            if (rs.next())
            {
                ret = true;
            }

            //close rs, conn, and stmt
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public boolean login(String mail, String passwd)
    {
        String query = "select ID_UTENTE from APP.UTENTE where EMAIL='"+mail+"' AND PASSWORD='"+passwd+"'";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean ret = false;

        try {
            //work with data
            if (rs.next())
            {
                ret = true;
            }

            //close rs, conn, and stmt
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
