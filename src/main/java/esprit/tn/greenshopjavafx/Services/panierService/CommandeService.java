package esprit.tn.greenshopjavafx.Services.panierService;

import esprit.tn.greenshopjavafx.Entities.Panier.Commande;
import esprit.tn.greenshopjavafx.Entities.Panier.PanierProduit;
import esprit.tn.greenshopjavafx.Services.IService;
import esprit.tn.greenshopjavafx.Services.ProduitService.MarqueService;
import esprit.tn.greenshopjavafx.Utils.DataSource;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.util.Date;
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
    public double calculateTISumForToday() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "SELECT SUM(montant) FROM commande WHERE date = ?";

        try (Connection connection = DataSource.getInstance().getCon();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDate(1, sqlDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public float calculateTotalISum() {
        String sql = "SELECT SUM(montant) FROM commande";

        try (Connection connection = DataSource.getInstance().getCon();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getFloat(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // Return a default value or handle the absence of data
    }
    public XYChart.Series<String, Float> getIncomeChartData() {
        String sql = "SELECT date, SUM(montant) FROM commande GROUP BY date ORDER BY TIMESTAMP(date)";
        XYChart.Series<String, Float> chartData = new XYChart.Series<>();

        try (Connection connection = DataSource.getInstance().getCon();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                chartData.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getFloat(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chartData;
    }

}
