package esprit.tn.greenshopjavafx.Entities.Utilisateur;

import java.util.Date;
import java.time.LocalDate;

public class Utilisateur {

    private int id;
    private String nom;
    private String username;
    private String email;
    private String password;
    private UserType userType;
    private LocalDate dateInscription;

    public Utilisateur(int id, String nom,  String email, String password, UserType userType) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.dateInscription = LocalDate.now();
    }

     public Utilisateur(String nom,  String email, String password, UserType userType) {
         this.nom = nom;
         this.email = email;
         this.password = password;
         this.userType = userType;
         this.dateInscription = LocalDate.now();
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }


    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", dateInscription=" + dateInscription +
                '}';
    }
}



