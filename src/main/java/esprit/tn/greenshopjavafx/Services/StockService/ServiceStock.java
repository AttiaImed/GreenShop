package esprit.tn.greenshopjavafx.Services.StockService;

import esprit.tn.greenshopjavafx.Entities.GestionStock.Stock;
import esprit.tn.greenshopjavafx.Services.IService;
import esprit.tn.greenshopjavafx.Services.ProduitService.ProduitService;
import esprit.tn.greenshopjavafx.Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class ServiceStock implements IService<Stock> {
    private Connection connection= DataSource.getInstance().getCon();
    private Statement statement;

    private ProduitService serviceProduit = new ProduitService();
    public ServiceStock(){
        try {
            statement= connection.createStatement();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void ajouter(Stock stock) throws SQLException {
        String query = "INSERT INTO stock (nom, marque, categorie, prix, produit_id, quantite) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, stock.getNom());
            preparedStatement.setString(2, stock.getMarque());
            preparedStatement.setString(3, stock.getCategorie());
            preparedStatement.setDouble(4, stock.getPrix());
            preparedStatement.setInt(5, stock.getIdProduit());
            preparedStatement.setInt(6, stock.getQuantite());

            int res = preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    stock.setId(generatedKeys.getInt(1));
                    System.out.println("stock ajouté avec succès. Nouvel ID : " + stock.getId());
                } else {
                    System.out.println("Erreur lors de la récupération de l'ID généré automatiquement.");
                }
            }
        }
    }



    @Override
    public void update(Stock stock) throws SQLException {
        String query = "UPDATE stock SET nom = ?, marque = ?, categorie = ?, prix = ?, produit_id = ?, quantite = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, stock.getNom());
            preparedStatement.setString(2, stock.getMarque());
            preparedStatement.setString(3, stock.getCategorie());
            preparedStatement.setDouble(4, stock.getPrix());
            preparedStatement.setInt(5, stock.getIdProduit());
            preparedStatement.setInt(6, stock.getQuantite());
            preparedStatement.setInt(7, stock.getId());

            int res = preparedStatement.executeUpdate();
            System.out.println("stock mis à jour avec succès. ID : " + stock.getId());
        }
    }


    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM stock WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int res = preparedStatement.executeUpdate();
            System.out.println("stock supprimé avec succès. ID : " + id);
        }
    }


    @Override
    public ArrayList<Stock> readAll() throws SQLException {
        ArrayList<Stock> stocks = new ArrayList<>();
        String query = "SELECT * FROM stock";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Stock stock = new Stock();
                stock.setId(resultSet.getInt("id"));
                stock.setNom(resultSet.getString("nom"));
                stock.setMarque(resultSet.getString("marque"));
                stock.setCategorie(resultSet.getString("categorie"));
                stock.setPrix(resultSet.getDouble("prix"));
                stock.setIdProduit(resultSet.getInt("produit_id"));
                stock.setQuantite(resultSet.getInt("quantite"));

                stocks.add(stock);

                // Debug statement to print the stock object
                System.out.println(stock);
            }
        }

        // Debug statement to print the size of the stocks list
        System.out.println("Number of stocks fetched: " + stocks.size());

        return stocks;
    }


    public Stock consulter(int id) throws SQLException {
        Stock stock = null;
        String query = "SELECT * FROM stock WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String marque = resultSet.getString("marque");
                String categorie = resultSet.getString("categorie");
                double prix = resultSet.getDouble("prix");
                int produit_id = resultSet.getInt("produit_id");
                int quantite = resultSet.getInt("quantite");

                stock = new Stock();
                stock.setId(id);
                stock.setNom(nom);
                stock.setMarque(marque);
                stock.setCategorie(categorie);
                stock.setPrix(prix);
                stock.setIdProduit(produit_id);
                stock.setQuantite(quantite);
            }
        }

        return stock;
    }


    @Override
    public Stock get(int id) throws SQLException {
        return null;
    }



    public ArrayList<Stock> chercherProduitParMarque(String marque) throws SQLException {
        ArrayList<Stock> stocksTrouves = new ArrayList<>();
        String query = "SELECT * FROM stock WHERE marque = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, marque);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String categorie = resultSet.getString("categorie");
                double prix = resultSet.getDouble("prix");
                int produit_id = resultSet.getInt("produit_id");
                int quantite = resultSet.getInt("quantite");

                Stock stock = new Stock();
                stock.setId(id);
                stock.setNom(nom);
                stock.setMarque(marque);
                stock.setCategorie(categorie);
                stock.setPrix(prix);
                stock.setIdProduit(produit_id);
                stock.setQuantite(quantite);

                stocksTrouves.add(stock);
            }
        }

        return stocksTrouves;
    }

    public ArrayList<Stock> chercherProduitParCategorie(String categorie) throws SQLException {
        ArrayList<Stock> stocksTrouves = new ArrayList<>();
        String query = "SELECT * FROM stock WHERE categorie = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, categorie);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String marque = resultSet.getString("marque");
                double prix = resultSet.getDouble("prix");
                int produit_id = resultSet.getInt("produit_id");
                int quantite = resultSet.getInt("quantite");

                Stock stock = new Stock();
                stock.setId(id);
                stock.setNom(nom);
                stock.setMarque(marque);
                stock.setCategorie(categorie);
                stock.setPrix(prix);
                stock.setIdProduit(produit_id);
                stock.setQuantite(quantite);

                stocksTrouves.add(stock);
            }
        }

        return stocksTrouves;
    }




}

