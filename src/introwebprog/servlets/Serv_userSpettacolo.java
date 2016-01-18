package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by matteo on 17/01/16.
 */
public class Serv_userSpettacolo extends HttpServlet {

    private String imgPostoLibero = "https://image.freepik.com/icone-gratis/poltrona-di-lusso_318-31059.png";
    private String imgPostoOccupato = "http://www.goalcollegeathlete.com/wp-content/uploads/2013/10/general-icons-03.png";
    private String imgPostoPrenotato = "http://www.clipartbest.com/cliparts/yco/e8n/ycoe8nrMi.png";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //responseWrapper importante per includere header ********************************************
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
            private final StringWriter sw = new StringWriter();

            @Override
            public PrintWriter getWriter() throws IOException {
                return new PrintWriter(sw);
            }

            @Override
            public String toString() {
                return sw.toString();
            }
        };
        request.getRequestDispatcher("/includes/header.jsp").include(request, responseWrapper);
        //**************************************************************************************

        int idSpett  = Integer.parseInt(request.getParameter("id"));

        MultisalaDAO dao = new MultisalaDAO();


        String res = "";

        res += "<html>\n" +
                "<head>\n" +
                "    <title>Cinema Multisala</title>\n" +
                "    <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">\n" +
                "    <link href=\"CSS/mycss.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n" +
                "    <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\n" +
                "</head>";

        res += "<body>";

        res += responseWrapper.toString();

        HttpSession s = null;
        s = request.getSession(false);

        Utente u = dao.getUtenteByMail((String)s.getAttribute("USER_MAIL"));

        System.out.println("= = = = = = = = = > "+ u.getEmail() + u.getIdRuolo());


        String body= "";
        if(dao.isAdmin(u))
        {
            Prezzo prezzo = dao.getPrezzoByidSpett(idSpett);
            List<Posto> posti = dao.getPostiByIdSpett(idSpett);

            //disposizione utenti, possibilità rimozione utente con relativa mail e accredito percentuale costo su credito persona
            //genero matrice posti
            int postiX = 5;
            int postiY = 10;
            String sala[][] = new String[postiX][postiY];
            for(int i = 0; i < postiX; i++)
                for(int j = 0; j < postiY; j++)
                    sala[i][j] = this.imgPostoLibero;

            //riempio tabella con posti occuati
            for (int i = 0; i < posti.size(); i++) {
                sala[posti.get(i).getRiga()][posti.get(i).getColonna()] = this.imgPostoOccupato;
            }

            //disegno sala (posso metterla in un filtro)
            body += "<table>";
            for(int i = 0; i < postiX; i++) {
                body+="<tr>";
                for (int j = 0; j < postiY; j++) {
                    body += "<td>";
                    body += "<a type=\"button\" class=\"descr\" id=\""+"x"+i+"y"+j+"A"+"\">";
                    body += "<img src=\""+sala[i][j]+"\" id=\"im"+"x"+i+"y"+j+"A\" height=\"40px\" width=\"40px\"/>";
                    body += "</a>";
                    body += "</td>";
                }
                body+="</tr>";
            }
            body += "</table>";


            //btn cancellazione
            body += "<div class = \"row\">";
            body += "<form name=\"cancellazione\" method=\"post\" action=\"http://localhost:8080/CinemaMultisala_war_exploded/prenotation/canc\">";
            body += "<button type=\"submit\" name=\"canc\" class=\"btn btn-primary\" id=\"btnCancellazione\"type=\"button\">Cancellazione prenotazione/i</button>";
            body += "</form>";

            body += "</row>";

            //incasso singolo spettacolo
            body += "<div class = \"row\">";
            body += "Incasso spettacolo : "+dao.getGuadagnoPerSpettacolo(idSpett);
            body += "</row>";


            //TODO extra : lista utenti prenotati


            body += "\n" +
                    "<footer>\n" +

                    "</footer>\n" +

                    "</body>\n" +
                    //TODO script deve eliminare prenotazioni
                    "<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-latest.min.js\"></script>\n" +
                    "                    <script>" +
                    "                    var output = \"S"+idSpett+"P\";" +
                    "                    var counterPosti = 0;" +
                    "                    $(document).ready(function() {\n" +
                    "                            $('.descr').click(function(){" +
                    "                               var text = 'im'+$(this).attr(\"id\");" +

                    "                          if( document.getElementById(text).src == '"+this.imgPostoOccupato+"')"+
                    "                               {document.getElementById(text).src='"+this.imgPostoPrenotato+"';" +
                    "                                output += text;" +
                    "                                counterPosti++;} " +
                    "                          else if( document.getElementById(text).src == '"+this.imgPostoPrenotato+"')"+
                    "                                {document.getElementById(text).src='"+this.imgPostoOccupato+"';" +
                    "                                 output = output.replace(text, '');" +
                    "                                 counterPosti--;} " +
                    "                          else"+
                    "                                { alert('Posto Libero');}" +
                    "                          console.log(output);";

            if(prezzo != null) {
                body += "                          $(\"div#displayPrezzo\").html(counterPosti*" + prezzo.getPrezzo() + ");";
            }
            else
            {
                body += "                          $(\"div#displayPrezzo\").html(counterPosti* 0);";

            }
            body += "                          document.getElementById('btnCancellazione').value = output;"+

                    "                            });" +
                    "                        });" +
                    "                    </script>"+
                    "</html>";
        }
        else
        {

            body += "USER MODE";// TODO: 17/01/16
        }

        res += body;

        PrintWriter out = response.getWriter();
        out.println(res);

    }
}
