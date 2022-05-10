package dao;

/**
 *
 * @author Herbert Caffarel
 */
public final class DAOFactory {

    public static UserDAO getUserDAO() {
        return new UserDAO();
    }
}
