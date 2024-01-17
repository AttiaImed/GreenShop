package esprit.tn.greenshopjavafx.Entities.GestionStock;

public class Stock {
    int id;
    String nom;
    int idProduit;
    int quantite;
    double prix;
    String marque;
    String categorie;


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

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", idProduit=" + idProduit +
                ", quantite=" + quantite +
                ", prix=" + prix +
                ", marque='" + marque + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }

    public Stock(int id, String nom, int idProduit, int quantite, double prix, String marque, String categorie) {
        this.id = id;
        this.nom = nom;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.prix = prix;
        this.marque = marque;
        this.categorie = categorie;
    }

    public Stock() {
    }
}


