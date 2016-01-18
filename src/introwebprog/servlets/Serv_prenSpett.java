package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Posto;
import introwebprog.models.Prezzo;
import introwebprog.models.Spettacolo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by matteo on 15/01/16.
 */
public class Serv_prenSpett extends HttpServlet {

    private String imgPostoLibero = "https://image.freepik.com/icone-gratis/poltrona-di-lusso_318-31059.png";
    private String imgPostoOccupato = "http://www.goalcollegeathlete.com/wp-content/uploads/2013/10/general-icons-03.png";
    private String imgPostoPrenotatoNormale = "http://www.clipartbest.com/cliparts/yco/e8n/ycoe8nrMi.png";
    private String imgPostoPrenotatoRidotto = "https://freightbooksblog.files.wordpress.com/2014/01/8-helvetica.jpg";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("id");
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


        if (param != null) {
            Integer tmp = Integer.parseInt(param);
            int idSpett = tmp.intValue();

            MultisalaDAO dao = new MultisalaDAO();

            List<Posto> posti = dao.getPostiByIdSpett(idSpett);
            Prezzo prezzo = dao.getPrezzoByidSpett(idSpett);

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
            res += "<table>";
            for(int i = 0; i < postiX; i++) {
                res+="<tr>";
                for (int j = 0; j < postiY; j++) {
                    res += "<td>";
                    res += "<a type=\"button\" class=\"descr\" id=\""+"x"+i+"y"+j+"A"+"\">";
                    res += "<img src=\""+sala[i][j]+"\" id=\"im"+"x"+i+"y"+j+"A\" height=\"40px\" width=\"40px\"/>";
                    res += "</a>";
                    res += "</td>";
                }
                res+="</tr>";
            }
            res += "</table>";

            res += "\n" +
                    "<footer>\n" +

                    "<form>\n" +
                    "<h2>Tipo di biglietto</h2>" +
                    " Normale <input type=\"radio\" name=\"tipobiglietto\" id = \"normale\" checked/></br>\n" +
                    " Ridotto <input type=\"radio\" name=\"tipobiglietto\" id = \"ridotto\"/>\n" +
                    "</form> "+


                    "<div>Totale costo biglietto/i : <div id=\"displayPrezzo\">0</div> €</div>"+



                    "<form name=\"pagare\" method=\"post\" action=\"http://localhost:8080/CinemaMultisala_war_exploded/prenotation/spett/endprenotation\">"+
                    "<button type=\"submit\" name=\"prenotation\" class=\"btn btn-primary\" id=\"btnPrenotazione\"type=\"button\">Procedi con Pagamento</button>"+
                    "</form>"+


                    "</footer>\n" +

                    "<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-latest.min.js\"></script>\n" +

                    "<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-latest.min.js\"></script>\n" +
                    "                    <script>" +
                    "                    var output = \"S"+idSpett+"P\";" +
                    "                    var counterPosti = 0;" +
                    "                    var prezzo = 0;"+
                    "                     var prezzomax = "+dao.getPrezzoByIdPrezzo(1).getPrezzo()+";"+
                    "                     var prezzomin = "+dao.getPrezzoByIdPrezzo(2).getPrezzo()+";"+
                    "                    $(document).ready(function() {\n" +
                    "                            $('.descr').click(function(){" +
                    "                               var text = 'im'+$(this).attr(\"id\");" +

                    "                          if( document.getElementById(text).src == '"+this.imgPostoLibero+"')"+
                    "                               {" +
                    "                                   if(document.getElementById(\"normale\").checked == true)" +
                    "                                       {" +
                    "                                           document.getElementById(text).src='"+this.imgPostoPrenotatoNormale+"';" +
                    "                                           prezzo += prezzomax;" +
                    "                                           text += \"PRE\"+prezzomax+\"B\";" +
                "                                           }" +
                    "                                   else" +
                    "                                      { " +
                    "                                           document.getElementById(text).src='"+this.imgPostoPrenotatoRidotto+"';" +
                    "                                           prezzo += prezzomin;" +
                    "                                           text += \"PRE\"+prezzomin+\"B\";" +
                    "                                       }" +
                    "                                     output += text;" +
                    "                                     counterPosti++;" +
                    "                               } " +
                    "                          else if( document.getElementById(text).src == '"+this.imgPostoPrenotatoNormale+"')"+
                    "                          {" +
                    "                               document.getElementById(text).src='"+this.imgPostoLibero+"';" +
                    "                                 output = output.replace(text+\"PRE\"+prezzomax+\"B\", '');" +
                    "                                 counterPosti--;" +
                    "                                   prezzo -= prezzomax;" +
                    "                          } " +
                    "                          else if( document.getElementById(text).src == '"+this.imgPostoPrenotatoRidotto+"')"+
            "                                  {" +
                    "                               document.getElementById(text).src='"+this.imgPostoLibero+"';" +
                    "                                 output = output.replace(text+\"PRE\"+prezzomin+\"B\", '');" +
                    "                                 counterPosti--;" +
                    "                                   prezzo -= prezzomin;" +
                    "                           } "+
                    "                          else"+
                    "                                { alert('Posto occupato');}" +
                    "                          console.log(output);" +

                    "                          $(\"div#displayPrezzo\").html(prezzo);" +
                    "                          document.getElementById('btnPrenotazione').value = output;"+

                    "                            });" +
                    "                        });" +
                    "                    </script>"+

                    "</body>\n" +
                    "</html>";


            PrintWriter out = response.getWriter();
            out.println(res);
        }
    }
}