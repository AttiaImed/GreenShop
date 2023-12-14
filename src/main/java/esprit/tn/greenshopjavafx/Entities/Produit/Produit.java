package esprit.tn.greenshopjavafx.Entities.Produit;

public class Produit {
    private int id;
    private String nom;
    private double prix;
    private Integer stock;
    private String image;
    private String status;

    private Integer quantity;

    private Categorie categorie;
    private Marque marque;


    public Produit(int id, String nom, double prix, Integer stock, String image, String status, Integer quantity, Categorie categorie, Marque marque) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
        this.image = image;
        this.status = status;
        this.quantity = quantity;
        this.categorie = categorie;
        this.marque = marque;
    }
    public Produit(int id, String nom, double prix, Integer stock, String image, String status, Integer quantity) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
        this.image = image;
        this.status = status;
        this.quantity = quantity;
    }
    public Produit(String nom, double prix, Integer stock, String image, String status, Integer quantity, Categorie categorie, Marque marque) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
        this.image = image;
        this.status = status;
        this.quantity = quantity;
        this.categorie = categorie;
        this.marque = marque;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", stock=" + stock +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", quantity=" + quantity +
                ", categorie=" + categorie +
                ", marque=" + marque +
                '}';
    }
}
