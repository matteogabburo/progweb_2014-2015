package introwebprog.jsp;

import introwebprog.dao.MultisalaDAO;
import introwebprog.models.Spettacolo;

import java.util.List;

/**
 * Created by matteo on 19/12/15.
 */
public class Functions {

    public List<Spettacolo> getAllSpettacoliByFilm(int idFilm)
    {
        MultisalaDAO dao = new MultisalaDAO();
        return dao.allShowsByIdFilm(idFilm);
    }

}
