package esprit.tn.greenshopjavafx.Services.panierService;

import esprit.tn.greenshopjavafx.Entities.Panier.PanierProduit;
import esprit.tn.greenshopjavafx.Entities.Produit.Produit;
import esprit.tn.greenshopjavafx.Services.IService;
import esprit.tn.greenshopjavafx.Utils.DataSource;
import javafx.beans.property.ListProperty;

import java.sql.*;
import java.util.ArrayList;

public class PanierProduitService  {
    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;
    public PanierProduitService() {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void ajouter(int idcommande, ListProperty<Produit> listeProduit) throws SQLException {
        for (Produit p:listeProduit) {
            String req = "INSERT INTO panier_id_produit_id (produit_id, panier_id, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, p.getId());
                preparedStatement.setDouble(2, idcommande);
                preparedStatement.setInt(3, p.getQuantity()); // Assuming you have getId() in Marque
                int res = preparedStatement.executeUpdate();

            }
        }
    }

}

