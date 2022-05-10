package forms;

import beans.User;
import dao.DAOFactory;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stag
 */
public class ConnectFormCheker extends FormChecker {

    private final int MIN_PWD_SIZE = 3;
    private final int MAX_PWD_SIZE = 24;

    public ConnectFormCheker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public User check() {
// Les paramètres de la requête
        String pseudo = request.getParameter("pseudo");
        String pwd = request.getParameter("pwd");

        // Le bean hydraté par les données du formulaire pour le réaffichage
        // desdites données dans le formulaire, donc à mettre en attribut de
        // requête
        User user = new User();
        user.setPseudo(pseudo);
        user.setPwd(pwd);
        request.setAttribute("user", user);

        // Les tests
        try {
            mandatoryField(pseudo);
            minFieldLength(pseudo, MIN_PWD_SIZE);
            maxFieldLength(pseudo, MAX_PWD_SIZE);
        } catch (Exception ex) {
            errors.put("pseudo", ex.getMessage());
        }
        try {
            mandatoryField(pwd);
            minFieldLength(pwd, MIN_PWD_SIZE);
            maxFieldLength(pwd, MAX_PWD_SIZE);
        } catch (Exception ex) {
            errors.put("pwd", ex.getMessage());
        }

        // Vérification de l'existence de l'utilisateur en DB et établissement
        // du message d'erreur
        if (errors.isEmpty()) {
            User fromDb = DAOFactory.getUserDAO().find(user.getPseudo());
            if (fromDb == null || !fromDb.getPwd().equals(user.getPwd())) {
                // Si l'utilisateur n'existe pas ou qu'il existe mais que le mot
                // de passe est faux => mesage d'erreur et invalidation de la
                // session
                errors.put("connection", "Couple pseudo/mot de passe inconnu");
                request.getSession().invalidate();
            } else {
                // On réucpère l'utilisateur "complet" à partir de la DB
                user = fromDb;
                // On le met en session
                request.getSession().setAttribute("user", user);
                // On met un message d'information
            }
        }
        
        // Ajouter la collection d'erreurs à la requête
        request.setAttribute("errors", errors);

        // Enfin on n'oublie pas de retourner l'utilisateur !
        return user;
    }

}
