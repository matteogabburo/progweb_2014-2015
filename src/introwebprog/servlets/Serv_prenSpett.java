package introwebprog.servlets;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Posto;
import introwebprog.models.Prezzo;
import introwebprog.models.Spettacolo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by matteo on 15/01/16.
 */
public class Serv_prenSpett extends HttpServlet {

    private String imgPostoLibero = "https://image.freepik.com/icone-gratis/poltrona-di-lusso_318-31059.png";
    private String imgPostoOccupato = "http://www.goalcollegeathlete.com/wp-content/uploads/2013/10/general-icons-03.png";
    private String imgPostoPrenotato = "http://www.clipartbest.com/cliparts/yco/e8n/ycoe8nrMi.png";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("id");

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
                    res += "<a class=\"descr\" id=\""+"x"+i+"y"+j+"A"+"\">";
                    res += "<img src=\""+sala[i][j]+"\" id=\"im"+"x"+i+"y"+j+"A\" height=\"40px\" width=\"40px\"/>";
                    res += "</a>";
                    res += "</td>";
                }
                res+="</tr>";
            }
            res += "</table>";


            request.getRequestDispatcher("/includes/header.jsp").include(request, response);

            res += "\n" +
                    "<footer>\n" +


                    "<div>Totale costo biglietto/i : <div id=\"displayPrezzo\">0</div> €</div>"+
                    "<button class=\"btn btn-primary\" id=\"btnPrenotazione\"type=\"button\">Procedi con Pagamento</button>"+

                    "</footer>\n" +

                    "<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-latest.min.js\"></script>\n" +
                    "                    <script>" +
                    "                    var output = \"\";" +
                    "                    var counterPosti = 0;" +
                    "                    $(document).ready(function() {\n" +
                    "                            $('.descr').click(function(){" +
                    "                               var text = 'im'+$(this).attr(\"id\");" +

                    "                          if( document.getElementById(text).src == '"+this.imgPostoLibero+"')"+
                    "                               {document.getElementById(text).src='"+this.imgPostoPrenotato+"';" +
                    "                                output += text;" +
                    "                                counterPosti++;} " +
                    "                          else if( document.getElementById(text).src == '"+this.imgPostoPrenotato+"')"+
                    "                                {document.getElementById(text).src='"+this.imgPostoLibero+"';" +
                    "                                 output = output.replace(text, '');" +
                    "                                 counterPosti--;} " +
                    "                          else"+
                    "                                { alert('Posto occupato');}" +
                    "                          console.log(output);" +

                    "                          $(\"div#displayPrezzo\").html(counterPosti*"+prezzo.getPrezzo()+");"+

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
