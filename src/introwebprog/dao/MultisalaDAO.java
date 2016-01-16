package introwebprog.dao;

import introwebprog.models.*;

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

    public List<Spettacolo> getSpettacoloByFilmId(int idfilm)
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

        List<Spettacolo> sList = new ArrayList<>();

        try {
            //work with data
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    Spettacolo s = new Spettacolo();

                    s.setIdSpettacolo(rs.getInt("ID_SPETTACOLO"));
                    s.setIdSala(rs.getInt("ID_SALA"));
                    s.setIdFilm(idfilm);
                    s.setDataOra(rs.getTimestamp("DATA_ORA"));

                    sList.add(s);
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

        return sList;
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

    private List<Posto> getPostiByIdSpett_aux(List<Prenotazione> prenList)
    {

        List<Posto> sList = new ArrayList<>();

        for(int i = 0; i < prenList.size(); i++)
        {
            String query = "select * from APP.POSTO WHERE APP.POSTO.ID_POSTO = " + prenList.get(i).getIdPosto();

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
                if (rs != null) { //Retrieve by column name
                    while (rs.next()) {
                        Posto s = new Posto();

                        s.setIdPosto(rs.getInt("ID_POSTO"));
                        s.setColonna(rs.getInt("COLONNA"));
                        s.setRiga(rs.getInt("RIGA"));
                        s.setEsiste(rs.getBoolean("ESISTE"));
                        s.setIdSala(rs.getInt("ID_SALA"));

                        sList.add(s);
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
        }

        return sList;
    }



    public List<Posto> getPostiByIdSpett(int idSpett) {
        String query = "select * from APP.PRENOTAZIONE WHERE APP.PRENOTAZIONE.ID_SPETTACOLO = " + idSpett;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Prenotazione> sList = new ArrayList<>();

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
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    Prenotazione s = new Prenotazione();

                    s.setIdPrenotazione(rs.getInt("ID_PRENOTAZIONE"));
                    s.setDataOraOperazione(rs.getTimestamp("DATA_ORA_OPERAZIONE"));
                    s.setIdPosto(rs.getInt("ID_POSTO"));
                    s.setIdPrezzo(rs.getInt("ID_PREZZO"));
                    s.setIdSpettacolo(rs.getInt("ID_SPETTACOLO"));
                    s.setIdUtente(rs.getInt("ID_UTENTE"));

                    sList.add(s);
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

        return getPostiByIdSpett_aux(sList);
    }

    public Prezzo getPrezzoByidSpett(int idSpett) {

        int idPrezzo = -1;



        String query = "select APP.PRENOTAZIONE.ID_PREZZO from APP.PRENOTAZIONE WHERE APP.PRENOTAZIONE.ID_SPETTACOLO = " + idSpett;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Prenotazione> sList = new ArrayList<>();

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

            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    idPrezzo = rs.getInt("ID_PREZZO");
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

        return getPrezzoByIdPrezzo(idPrezzo);
    }

    public Prezzo getPrezzoByIdPrezzo(int idPrezzo) {
        String query = "select * from APP.PREZZO WHERE APP.PREZZO.ID_PREZZO = " + idPrezzo;

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

        Prezzo s = null;

        try {
            //work with data
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    s = new Prezzo();

                    s.setIdPrezzo(rs.getInt("ID_PREZZO"));
                    s.setPrezzo(rs.getDouble("PREZZO"));
                    s.setTipo(rs.getString("TIPO"));
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

    private int getMaxidPrenotation()
    {
        String query = "select MAX(APP.PRENOTAZIONE.ID_PRENOTAZIONE) as ID_PRENOTAZIONE from APP.PRENOTAZIONE";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        int out = 1;

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
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    out = rs.getInt("ID_PRENOTAZIONE");
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

        return out;
    }

    private int getIdUserByMail(String mail)
    {
        String query = "select APP.UTENTE.ID_UTENTE from APP.UTENTE WHERE APP.UTENTE.EMAIL ='" + mail+"'";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        int out = 1;

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
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    out = rs.getInt("ID_UTENTE");
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

        return out;
    }


    public boolean newPrenotation(String user_mail, List<Posto> posti, int idSpett) {

        int userId = this.getIdUserByMail(user_mail);
        int idPrezzo = this.getPrezzoByidSpett(idSpett).getIdPrezzo();
        int idSala = this.getIdSalaByIdSpettacolo(idSpett);


        System.out.println("*******************************************************");
        System.out.println(userId);
        System.out.println(idPrezzo);
        System.out.println(idSala);
        System.out.println("*******************************************************");



        for(int i = 0; i < posti.size(); i++) {
            int idPosto = this.newPosto(posti.get(i), idSala);
            int idPrenotazione = this.getMaxidPrenotation() + 1;

            String query = "INSERT INTO APP.PRENOTAZIONE (ID_PRENOTAZIONE, ID_UTENTE, ID_SPETTACOLO, ID_PREZZO, ID_POSTO, DATA_ORA_OPERAZIONE)" +
                    " VALUES ("+ idPrenotazione+"," +userId+","+ idSpett + ","+idPrezzo+"," +idPosto+ ",CURRENT_TIMESTAMP )";

            Connection conn = null;
            PreparedStatement stmt = null;

            int rs = 0;

            try {
                DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
                conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
                stmt = conn.prepareStatement(query);
                rs = stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (rs <= 0)
                return false;

            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private int getIdSalaByIdSpettacolo(int idSpett) {
        String query = "select APP.SPETTACOLO.ID_SALA from APP.SPETTACOLO WHERE ID_SPETTACOLO = "+idSpett;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        int out = 1;

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
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    out = rs.getInt("ID_SALA");
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

        return out;
        
    }

    private int newPosto(Posto posto, int idSala) {


            int idPosto = this.getMaxIdPosto() + 1;


            String query = "INSERT INTO APP.POSTO (ID_POSTO, ID_SALA, RIGA, COLONNA, ESISTE)" +
                    " VALUES ("+idPosto+","+ idSala+","+ posto.getRiga()+"," +posto.getColonna()+", 1)";

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



            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        return idPosto;

    }

    private int getMaxIdPosto() {
        String query = "select MAX(ID_POSTO) as ID_POSTO from APP.POSTO";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        int out = 1;

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
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    out = rs.getInt("ID_POSTO");
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

        return out;
    }
}
