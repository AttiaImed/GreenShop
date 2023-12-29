package esprit.tn.greenshopjavafx.Entities.Panier;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Commande {
        private int id;
        private double montant;
        private int customer_id;
        private String date;

    public Commande(int id, double montant, int customer_id) {
        this.id = id;
        this.montant = montant;
        this.customer_id = customer_id;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date today = new Date();
        System.out.println(formatter.format(today));
        this.date = formatter.format(today);
    }

    public Commande(double montant, int customer_id, String date) {
        this.montant = montant;
        this.customer_id = customer_id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
