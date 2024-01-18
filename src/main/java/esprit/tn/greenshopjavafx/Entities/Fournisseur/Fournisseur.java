package esprit.tn.greenshopjavafx.Entities.Fournisseur;

import esprit.tn.greenshopjavafx.Entities.Produit.Produit;

import java.util.ArrayList;

public class Fournisseur extends ArrayList<Fournisseur> {
    int id;
    String nom;
    String prenom;
    String email;
    String adresse;
    int phonenumber;


    public Fournisseur(){}

    public Fournisseur(int id,String nom,String prenom,String email,String adresse,int phonenumber){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.adresse=adresse;
        this.phonenumber=phonenumber;
    }
    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", phonenumber=" + phonenumber +
                '}';
    }
}
