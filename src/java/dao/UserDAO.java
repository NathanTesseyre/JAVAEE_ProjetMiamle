package dao;

import beans.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe permettant le passage de l'objet User vers la table guest et
 * vice-versa. Plus précisément, cette classe propose le CRUD complet sur la
 * table guest. Le bean utilisé sera une instance de User.
 *
 * @author Herbert Caffarel
 */
public class UserDAO extends DAO<User> {

    public UserDAO() {
        super("user");
    }

    @Override
    public User find(Long id) {
        User user = null;
        String sql = "SELECT * FROM " + table + " WHERE id=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery(); // Requête en sélection
            if (rs.first()) { // L'utilisateur a été trouvé dans la DB
                // => hydratation du bean
                user = new User();
                user.setEmail(rs.getString("email"));
                user.setPseudo(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setId(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User find(String pseudo) {
        User user = null;
        String sql = "SELECT * FROM " + table + " WHERE pseudo=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, pseudo);
            ResultSet rs = pstmt.executeQuery(); // Requête en sélection
            if (rs.first()) { // L'utilisateur a été trouvé dans la DB
                // => hydratation du bean
                user = new User();
                user.setPseudo(pseudo);
                user.setEmail(rs.getString("email"));
                user.setPwd(rs.getString("pwd"));
                user.setId(rs.getLong("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public void create(User obj) {
        String sql = "INSERT INTO " + table + " (email, pwd, name) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getEmail());
            pstmt.setString(2, obj.getPwd());
            pstmt.setString(3, obj.getPseudo());
            int nbLines = pstmt.executeUpdate();
            if (nbLines == 1) {
                ResultSet keys = pstmt.getGeneratedKeys();
                if (keys.first()) {
                    obj.setId(keys.getLong(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User obj) {
        String sql = "UPDATE " + table + " SET email=?, pwd=?, name=? WHERE id=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(4, obj.getId());
            pstmt.setString(2, obj.getPwd());
            pstmt.setString(1, obj.getEmail());
            pstmt.setString(3, obj.getPseudo());
            int nbLines = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> all() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM " + table;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) { // Pour chaque ligne de résultat
                // On crée un utilisateur
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setPwd(rs.getString("pwd"));
                user.setPseudo(rs.getString("name"));
                // On l'ajoute à la liste
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
}
