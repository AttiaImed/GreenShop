package esprit.tn.greenshopjavafx.Entities.Produit;

public class Marque {
    private int id;
    private String nom;

    public Marque(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public Marque(String nom) {
        this.nom = nom;
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

    @Override
    public String toString() {
        return "Marque{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
