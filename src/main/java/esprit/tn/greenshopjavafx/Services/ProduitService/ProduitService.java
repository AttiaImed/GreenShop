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
        String req = "INSERT INTO produit (nom, prix,stock,image,status,quantity, marque) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setDouble(2, produit.getPrix());
            preparedStatement.setInt(3, 0);
            preparedStatement.setString(4, produit.getImage());
            preparedStatement.setString(5, produit.getStatus());
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, produit.getMarque().getId()); // Assuming you have getId() in Marque
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
        String req = "UPDATE produit SET nom = ?, prix = ?, marque = ?,status = ?,image =? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setDouble(2, produit.getPrix());
            preparedStatement.setInt(3, produit.getMarque().getId());
            preparedStatement.setString(4,produit.getStatus());
            preparedStatement.setString(5, produit.getImage());
            preparedStatement.setInt(6, produit.getId());
            int res = preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void updateQuantity(int Quantity,int id_produit) throws SQLException {

        String req = "UPDATE produit SET quantity = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            Produit p= this.get(id_produit);
            p.setQuantity(p.getQuantity()+Quantity);
            preparedStatement.setInt(1, p.getQuantity());
            preparedStatement.setInt(2, p.getId());
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
                // Get the quantity value from the current result set
                int quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                String status = resultSet.getString("status");

                // Fetching the stock value from the stock table
                int stock = getStockValue(id);

                // Assuming you have methods to fetch Categorie and Marque based on their IDs
                Marque marque = marqueService.get(resultSet.getInt("marque"));

                Produit p = new Produit(id, nom, prix, stock, image, status, quantity, marque);
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
                // Get the quantity value from the current result set
                int quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                String status = resultSet.getString("status");

                // Fetching the stock value from the stock table
                int stock = getStockValue(id);

                // Assuming you have methods to fetch Categorie and Marque based on their IDs
                Marque marque = marqueService.get(resultSet.getInt("marque"));

                return new Produit(id, nom, prix, stock, image, status, quantity, marque);
            }
        }
        return null;
    }

    private int getStockValue(int produitId) throws SQLException {
        String stockQuery = "SELECT quantite FROM stock WHERE produit_id = ?";
        try (PreparedStatement stockStatement = con.prepareStatement(stockQuery)) {
            stockStatement.setInt(1, produitId);
            ResultSet stockResultSet = stockStatement.executeQuery();
            if (stockResultSet.next()) {
                return stockResultSet.getInt("quantite");
            }
        }
        return 0; // Return a default value if stock information is not found
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
