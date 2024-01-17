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


        String req = "INSERT INTO commande (montant, customer_id, date, address) VALUES (?, ?, ? , ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, PanierProduit.calculateTotalPrice());
            preparedStatement.setInt(2, commande.getCustomer_id());
            preparedStatement.setString(3, commande.getDate());
            preparedStatement.setString(4, commande.getAddress());// Assuming you have getId() in Marque
            int res = preparedStatement.executeUpdate();

            if (res > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    commande.setId(generatedKeys.getInt(1));
                    panierProduitService.ajouter(commande.getId(),PanierProduit.getProductListProperty());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void update(Commande commande) throws SQLException {
        String req = "UPDATE commande SET montant=?, customer_id=?, date=?, address=? WHERE id=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setDouble(1, PanierProduit.calculateTotalPrice());
            preparedStatement.setInt(2, commande.getCustomer_id());
            preparedStatement.setString(3, commande.getDate());
            preparedStatement.setString(4, commande.getAddress());
            preparedStatement.setInt(5, commande.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM commande WHERE id=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Commande> readAll() throws SQLException {
        ArrayList<Commande> commandes = new ArrayList<>();
        String req = "SELECT * FROM commande";
        try (ResultSet resultSet = ste.executeQuery(req)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double montant = resultSet.getDouble("montant");
                int customerId = resultSet.getInt("customer_id");
                String date = resultSet.getString("date");
                String address = resultSet.getString("address");

                Commande commande = new Commande(id, montant, customerId, date, address);
                commandes.add(commande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

    @Override
    public Commande get(int id) throws SQLException {
        String req = "SELECT * FROM commande WHERE id=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    double montant = resultSet.getDouble("montant");
                    int customerId = resultSet.getInt("customer_id");
                    String date = resultSet.getString("date");
                    String address = resultSet.getString("address");

                    return new Commande(id, montant, customerId, date, address);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
