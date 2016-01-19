package introwebprog.scriptavvio;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.log.SysoCounter;
import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import com.sun.org.apache.xpath.internal.operations.Mult;
import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Spettacolo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.Set;

/**
 * Created by matteo on 19/01/16.
 */
public class SetUp {

    static final int NSPETTACOLI = 6;
    static final int NPRENOTAZIONI = 20;
    static final int NPOSTI = 100;
    static final int MAXFILM = 10;
    static final int MAXUSER = 15;
    static final int MAXSALA = 5;
    static final int MAXPOSTICOLONNA = 10 ;
    static final int MAXPOSTIRIGA = 5;

    public static void main(String[] args)
    {
        SetUp setUp = new SetUp();

        System.out.println("Insert \"x\" value : ");
        String input = "";
        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            input = bufferRead.readLine();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        //parse input
        if(input.matches("[0-9]+"))
        {
            int x = Integer.parseInt(input);
            System.out.println(x);

            setUp.removeAllSpettacoliPrenotazioniAndPosti();
            setUp.createSpettacoli(x);
        }
        else
            System.out.println("Error, input not valid");
    }

    private void createPrenontazioniEPosti(int idPosto, int idSpettacolo, int idSala, int rigaecolonna)
    {
        MultisalaDAO dao = new MultisalaDAO();

        int riga =1 +( rigaecolonna / (SetUp.MAXPOSTIRIGA-1));
        int colonna =1 +( rigaecolonna % (SetUp.MAXPOSTICOLONNA-1));

        String query1 = "INSERT INTO APP.POSTO (ID_POSTO, ID_SALA, RIGA, COLONNA, ESISTE) " +
                "VALUES ("+idPosto+", "+idSala+", "+riga+", "+colonna+", null)";

        Random rnd = new Random();
        Date d = new Date();

        String query2 = "INSERT INTO APP.PRENOTAZIONE (ID_PRENOTAZIONE, ID_UTENTE, ID_SPETTACOLO, ID_PREZZO, ID_POSTO, DATA_ORA_OPERAZIONE) " +
                "VALUES ("+idPosto+", "+String.valueOf(1 + rnd.nextInt(10))+", "+idSpettacolo+", "+String.valueOf(1 + rnd.nextInt(2))+", "+idPosto+", '"+new Timestamp(d.getTime())+"')";

        System.out.println(query1);
        System.out.println(query2);

        dao.sendInsertQuery(query1);
        dao.sendInsertQuery(query2);
    }

    private void removeAllSpettacoliPrenotazioniAndPosti() {
        MultisalaDAO dao = new MultisalaDAO();
        dao.removeAllSpettacoliPrenotazioniAndPosti();
    }


    private void createSpettacoli(int x)
    {
        MultisalaDAO dao = new MultisalaDAO();
        int idSpettacolo = 1;
        int idSala = 0;
        int val = x;
        int idPosto = 1;

        for(int i = 1; i <= SetUp.MAXFILM; i++)
        {
            for(int j = 0; j < SetUp.NSPETTACOLI; j++)
            {
                idSala = idSpettacolo % (SetUp.MAXSALA)+1;
                dao.sendInsertQuery(this.createSpettacolo(idSpettacolo, i, val, idSala));
                System.out.println("Add spettacolo("+idSpettacolo+", "+i+", "+val+","+idSala+")");

                Random random = new Random();
                for(int k = 0; k < random.nextInt(SetUp.NPRENOTAZIONI)+1; k++)
                {
                    this.createPrenontazioniEPosti(idPosto, idSpettacolo, idSala, k);
                    idPosto++;
                }

                val= val + x;
                idSpettacolo++;
            }
            val = x;
        }
    }

    public String createSpettacolo(int id, int idFilm, int x, int idSala)
    {
        Date d = new Date();

        Timestamp t = new Timestamp(d.getTime() + x*60*1000);

        String query = "INSERT INTO APP.SPETTACOLO (ID_SPETTACOLO, ID_FILM, DATA_ORA, ID_SALA) " +
                "VALUES ("+id+","+idFilm+", '"+t.toString()+"',"+idSala+")";
        return query;
    }
}
