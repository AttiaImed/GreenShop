package esprit.tn.greenshopjavafx.Services.ProduitService;



import esprit.tn.greenshopjavafx.Entities.Produit.Marque;
import esprit.tn.greenshopjavafx.Entities.Produit.Produit;
import esprit.tn.greenshopjavafx.Services.IService;
import esprit.tn.greenshopjavafx.Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class ProduitService implements IService<Produit> {
    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;
    MarqueService marqueService=new MarqueService();
    public ProduitService() {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void ajouter(Produit produit) throws SQLException {
        String req = "INSERT INTO produit (nom, prix, marque) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setDouble(2, produit.getPrix());
            preparedStatement.setInt(4, produit.getMarque().getId()); // Assuming you have getId() in Marque
            int res = preparedStatement.executeUpdate();

            if (res > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    produit.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(Produit produit) throws SQLException {
        String req = "UPDATE produit SET nom = ?, prix = ?, marque = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setDouble(2, produit.getPrix());
            preparedStatement.setInt(4, produit.getMarque().getId()); // Assuming you have getId() in Marque
            preparedStatement.setInt(5, produit.getId());
            int res = preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM produit WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            int res = preparedStatement.executeUpdate();
        }
    }

    @Override
    public ArrayList<Produit> readAll() throws SQLException {
        ArrayList<Produit> list = new ArrayList<>();
        try {
            ResultSet resultSet = ste.executeQuery("SELECT * FROM produit");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                double prix = resultSet.getDouble("prix");
                int stock = resultSet.getInt("stock");
                String image = resultSet.getString("image");
                String status = resultSet.getString("status");
                int quantity = resultSet.getInt("quantity");
                // Assuming you have methods to fetch Categorie and Marque based on their IDs
                Marque marque = marqueService.get(resultSet.getInt("marque"));

                Produit p = new Produit(id, nom, prix,stock,image ,status,quantity, marque);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    @Override
    public Produit get(int id) throws SQLException {
        String req = "SELECT * FROM produit WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                double prix = resultSet.getDouble("prix");
                int stock = resultSet.getInt("stock");
                String image = resultSet.getString("image");
                String status = resultSet.getString("status");
                int quantity = resultSet.getInt("quantity");
                // Assuming you have methods to fetch Categorie and Marque based on their IDs
                Marque marque = marqueService.get(resultSet.getInt("marque"));

                Produit p = new Produit(id, nom, prix,stock,image ,status,quantity, marque);
            }
        }
        return null;
    }

    public ArrayList<Produit> getAllProductsByMarqueName(String marqueName) throws SQLException {
        ArrayList<Produit> list = new ArrayList<>();
        try {
            String query = "SELECT p.*, m.nom AS marque_nom FROM produit p INNER JOIN marque m ON p.marque_id = m.id WHERE m.nom = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, marqueName);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    double prix = resultSet.getDouble("prix");
                    int stock = resultSet.getInt("stock");
                    String image = resultSet.getString("image");
                    String status = resultSet.getString("status");
                    int quantity = resultSet.getInt("quantity");
                    Produit p = new Produit(id, nom, prix,stock,image ,status,quantity, new Marque(0, marqueName));
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }



}
