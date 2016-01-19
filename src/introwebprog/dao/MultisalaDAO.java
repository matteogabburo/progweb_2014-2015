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


    public Utente getUtenteByMail(String mail) {
        String query = "select * from APP.UTENTE WHERE APP.UTENTE.EMAIL = '" + mail+ "'";

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

        Utente s = null;

        try {
            //work with data
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    s = new Utente();

                    s.setIdUtente(rs.getInt("ID_UTENTE"));
                    s.setEmail(rs.getString("EMAIL"));
                    s.setPassword(rs.getString("PASSWORD"));
                    s.setCredito(rs.getDouble("CREDITO"));
                    s.setIdRuolo(rs.getInt("ID_RUOLO"));
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


    public boolean newPrenotation(String user_mail, List<Posto> posti, int idSpett, List<Integer> prezzi, List<Prenotazione> prenotazioni) {

        int userId = this.getIdUserByMail(user_mail);
        int idSala = this.getIdSalaByIdSpettacolo(idSpett);


        System.out.println("*******************************************************");
        System.out.println(userId);
        System.out.println(idSala);
        System.out.println("*******************************************************");



        for(int i = 0; i < posti.size(); i++) {
            int idPosto = this.newPosto(posti.get(i), idSala);
            int idPrezzo;
            if(prezzi.get(i) == 4)//TODO dinamizzarlo
                idPrezzo = 2;
            else
                idPrezzo = 1;

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

            prenotazioni.add(this.getPrenotazioneById(idPrenotazione));
        }
        return true;
    }

    public Prenotazione getPrenotazioneById(int idPrenotazione) {
        String query = "select * from APP.PRENOTAZIONE WHERE ID_PRENOTAZIONE = "+idPrenotazione;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        Prenotazione p = null;

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
                p = new Prenotazione();
                while (rs.next()){
                    p.setIdPrenotazione(rs.getInt("ID_PRENOTAZIONE"));
                    p.setIdUtente(rs.getInt("ID_UTENTE"));
                    p.setIdSpettacolo(rs.getInt("ID_SPETTACOLO"));
                    p.setIdPrezzo(rs.getInt("ID_PREZZO"));
                    p.setIdPosto(rs.getInt("ID_POSTO"));
                    p.setDataOraOperazione(rs.getTimestamp("DATA_ORA_OPERAZIONE"));
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

        return p;
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

    public String getPasswordByMail(String mail) {
        String query = "select APP.UTENTE.PASSWORD AS PASSWORD from APP.UTENTE WHERE APP.UTENTE.EMAIL ='" + mail+"'";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String out = "";

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
                    out = rs.getString("PASSWORD");
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

    public boolean isAdmin(Utente u) {
        if(u.getIdRuolo() == 1)
            return true;
        else
            return false;
    }

    public List<Spettacolo> getAllSpettacoli() {
        String query = "select * from APP.SPETTACOLO";


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

                s.setIdFilm(rs.getInt("ID_FILM"));
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

    public List<Film> getAllFilm() {
        String query = "select * from APP.FILM";


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
            ArrayList<Film> res = null;
            while (rs.next()) {
                //Retrieve by column name
                Film s = new Film();

                s.setIdFilm(rs.getInt("ID_FILM"));
                s.setTitolo(rs.getString("TITOLO"));
                s.setIdGenere(rs.getInt("ID_GENERE"));
                s.setUrlTrailer(rs.getString("URL_TRAILER"));
                s.setDurata(rs.getInt("DURATA"));
                s.setTrama(rs.getString("TRAMA"));
                s.setUriLocandina(rs.getString("URL_LOCANDINA"));

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

    public double getGuadagnoPerFilm(List<Spettacolo> idSpettacoli) {

        double res = 0.0;

        for (int i = 0; i < idSpettacoli.size(); i++) {
            String query = "select ID_PREZZO from PRENOTAZIONE where PRENOTAZIONE.ID_SPETTACOLO=" + idSpettacoli.get(i).getIdFilm();


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


                //TODO prezzi dinamici

                int prezzo;
                while (rs.next()) {
                    if (rs.getInt("ID_PREZZO") == 1)
                        prezzo = 7;//completo
                    else
                        prezzo = 4;//ridotto

                    res += 1 * prezzo;
                }

                //close rs
                rs.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return res;
    }

    public Double getGuadagnoPerSpettacolo(int idSpett) {

        double res = 0.0;

        String query = "select ID_PREZZO from PRENOTAZIONE where PRENOTAZIONE.ID_SPETTACOLO=" + idSpett;


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


            //TODO prezzi dinamici

            int prezzo;
            while (rs.next()) {
                if (rs.getInt("ID_PREZZO") == 1)
                    prezzo = 7;//completo
                else
                    prezzo = 4;//ridotto

                res += 1 * prezzo;
            }

            //close rs
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();



        }
        return res;
    }

    public String getMailFromPosto(Posto posto) {
        String query = "select APP.UTENTE.EMAIL from APP.UTENTE where APP.UTENTE.ID_UTENTE = (select APP.PRENOTAZIONE.ID_UTENTE from APP.PRENOTAZIONE where APP.PRENOTAZIONE.ID_POSTO = "+posto.getIdPosto()+")";

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55" + query);

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
            String res = null;
            while (rs.next()) {
                res = rs.getString("EMAIL");
            }

            //close rs
            rs.close();

            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;

    }

    public boolean cancellazionePrenotazione(Posto posto)
    {
        String query = "DELETE FROM APP.PRENOTAZIONE WHERE APP.PRENOTAZIONE.ID_POSTO = "+posto.getIdPosto();
        String query2 = "DELETE FROM APP.POSTO WHERE APP.POSTO.ID_POSTO = "+posto.getIdPosto();

        Connection conn = null;
        PreparedStatement stmt = null;

        System.out.println("::::::::::::::::::::::::::: 1");

        int rs = 0;

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.prepareStatement(query);
            rs = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*if (rs <= 0)
            return false;*/


        System.out.println("::::::::::::::::::::::::::: 2");

        try {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.prepareStatement(query2);
            rs = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*if (rs <= 0)
            return false;*/


        System.out.println("::::::::::::::::::::::::::: 3");
        try {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public Film getFilmByIdSpettacolo(int idSpett) {

        String query = "select * from APP.FILM WHERE APP.FILM.ID_FILM = (select APP.SPETTACOLO.ID_FILM from APP.SPETTACOLO where APP.SPETTACOLO.ID_SPETTACOLO = "+idSpett+" )";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        Film f = null;

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
            f = new Film();
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    f.setIdFilm(rs.getInt("ID_FILM"));
                    f.setTitolo(rs.getString("TITOLO"));
                    f.setIdGenere(rs.getInt("ID_GENERE"));
                    f.setUrlTrailer(rs.getString("URL_TRAILER"));
                    f.setDurata(rs.getInt("DURATA"));
                    f.setTrama(rs.getString("TRAMA"));
                    f.setUriLocandina(rs.getString("URI_LOCANDINA"));
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

        return f;
    }

    public Spettacolo getSpettacoloByIdSpettacolo(int idSpettacolo)
    {
        String query = "select * from APP.SPETTACOLO WHERE APP.SPETTACOLO.ID_SPETTACOLO = " + idSpettacolo;

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

                    s.setIdFilm(rs.getInt("ID_FILM"));
                    s.setIdSpettacolo(rs.getInt("ID_SPETTACOLO"));
                    s.setIdSala(rs.getInt("ID_SALA"));
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

    public Posto getPostoByIdPosto(int idPosto) {
        String query = "select * from APP.POSTO WHERE APP.POSTO.ID_POSTO = " + idPosto;

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

        Posto s = null;

        try {
            //work with data
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    s = new Posto();

                    s.setIdPosto(rs.getInt("ID_POSTO"));
                    s.setColonna(rs.getInt("COLONNA"));
                    s.setRiga(rs.getInt("RIGA"));
                    s.setEsiste(rs.getBoolean("ESISTE"));
                    s.setIdSala(rs.getInt("ID_SALA"));
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

    public Posto getPostoByRigaColonnaIdSpettacolo(Integer riga, Integer colonna, int idSpett) {


        String query1 = "select APP.PRENOTAZIONE.ID_POSTO from APP.PRENOTAZIONE WHERE APP.PRENOTAZIONE.ID_SPETTACOLO = "+idSpett;

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& "+query1 );

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Integer> idPosti = new ArrayList<>();

        try {
            //work with data
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    idPosti.add(rs.getInt("ID_POSTO"));
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

        String query = "select * from APP.POSTO WHERE APP.POSTO.RIGA = "+riga+" AND APP.POSTO.COLONNA = "+colonna;

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& "+query );

        conn = null;
        stmt = null;
        rs = null;

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(DB_URL);//,USER,PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Posto s = null;
        List<Posto> posti = new ArrayList<>();

        try {
            //work with data
            if (rs != null) { //Retrieve by column name
                while (rs.next()) {
                    s = new Posto();

                    s.setIdPosto(rs.getInt("ID_POSTO"));
                    s.setColonna(rs.getInt("COLONNA"));
                    s.setRiga(rs.getInt("RIGA"));
                    s.setEsiste(rs.getBoolean("ESISTE"));
                    s.setIdSala(rs.getInt("ID_SALA"));

                    posti.add(s);
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


        for(int i = 0; i < posti.size(); i++)
        {
            for(int k = 0; k < idPosti.size(); k++)
            {
                if(posti.get(i).getIdPosto() == idPosti.get(k))
                    return posti.get(i);
            }
        }

        return null;
    }
}
