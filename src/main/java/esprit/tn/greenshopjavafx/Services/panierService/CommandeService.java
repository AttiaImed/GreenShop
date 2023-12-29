package esprit.tn.greenshopjavafx.Services.panierService;

import esprit.tn.greenshopjavafx.Entities.Panier.Commande;
import esprit.tn.greenshopjavafx.Entities.Panier.PanierProduit;
import esprit.tn.greenshopjavafx.Services.IService;
import esprit.tn.greenshopjavafx.Services.ProduitService.MarqueService;
import esprit.tn.greenshopjavafx.Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class CommandeService implements IService<Commande> {
    PanierProduitService panierProduitService = new PanierProduitService();
    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;
    public CommandeService() {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void ajouter(Commande commande) throws SQLException {
        String req = "INSERT INTO commande (montant, customer_id, date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, PanierProduit.calculateTotalPrice());
            preparedStatement.setInt(2, commande.getCustomer_id());
            preparedStatement.setString(3, commande.getDate()); // Assuming you have getId() in Marque
            int res = preparedStatement.executeUpdate();

            if (res > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    commande.setId(generatedKeys.getInt(1));
                    panierProduitService.ajouter(commande.getId(),PanierProduit.getProductListProperty());
                }
            }
        }
    }

    @Override
    public void update(Commande commande) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public ArrayList<Commande> readAll() throws SQLException {
        return null;
    }

    @Override
    public Commande get(int id) throws SQLException {
        return null;
    }
}
