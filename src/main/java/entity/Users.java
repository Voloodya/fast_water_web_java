package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users", schema = "db_fastwater", catalog = "")
public class Users implements Serializable {
    private Integer Id_Users;
    private String login;
    private String password;
    private String levelAccess;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Users")
    public Integer getId_Users() {
        return Id_Users;
    }
    public void setId_Users(Integer id_Users) {
        Id_Users = id_Users;    }

    @Basic
    @Column(name = "Login")
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "Password_")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "LevelAccess")
    public String getLevelAccess() {
        return levelAccess;
    }
    public void setLevelAccess(String levelAccess) {
        this.levelAccess = levelAccess;
    }

    @Override
    public String toString() {
        return "User{" +
                "Login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
