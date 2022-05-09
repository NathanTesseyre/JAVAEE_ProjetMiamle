package beans;

import java.io.Serializable;

/**
 *
 * @author Herbert Caffarel
 */
public class User implements Serializable {

    private static final long serialVersionUID = 2L;

    private Long id;
    private String email;
    private String pwd;
    private String pseudo;

    //@Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{id=").append(id);
        sb.append(", email=").append(email);
        sb.append(", pwd=").append(pwd);
        sb.append(", pseudo=").append(pseudo);
        sb.append('}');
        return sb.toString();
    }

}
