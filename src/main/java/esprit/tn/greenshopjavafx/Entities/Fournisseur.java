package esprit.tn.greenshopjavafx.Entities;

import java.sql.Date;

public class Fournisseur {
    private Integer id;
    private Integer customerID;
    private Double total;
    private Date date;
    private String emUsername;

    public Fournisseur(Integer id, Integer customerID, Double total,
                         Date date, String emUsername) {
        this.id = id;
        this.customerID = customerID;
        this.total = total;
        this.date = date;
        this.emUsername = emUsername;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public Double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }

    public String getEmUsername() {
        return emUsername;
    }

}

