package esprit.tn.greenshopjavafx.Entities.Utilisateur;

public class Administrateur {
    int id ;
    String password;

    public Administrateur(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrateur{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }

}
