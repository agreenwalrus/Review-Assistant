package data;

import data.lecturer.Lecturer;
import resources.MD5Hash;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username", length = 100)
    private String username;
    @Column(name = "password", length = 100)
    private String password;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_lecturer")
    private Lecturer lecturer;

    public User(){
        this.username = null;
        this.password = null;
        this.lecturer = null;
    }

    public User(String username, String password, Lecturer lecturer){
        this.username = username;
        this.password = password;
        this.lecturer = lecturer;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setMD5Password(String md5Password){
        this.password = MD5Hash.getHash(md5Password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}