package esprit.tn.greenshopjavafx.Services.ProduitService;



import esprit.tn.greenshopjavafx.Utils.DataSource;
import esprit.tn.greenshopjavafx.Entities.Produit.Marque;

import java.sql.*;
import java.util.ArrayList;

public class MarqueService {
    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;

    public MarqueService() {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void ajouter(Marque marque) throws SQLException {
        String req = "INSERT INTO marque (nom) VALUES (?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, marque.getNom());
            int res = preparedStatement.executeUpdate();

            if (res > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    marque.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void update(Marque marque) throws SQLException {
        String req = "UPDATE marque SET nom = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setString(1, marque.getNom());
            preparedStatement.setInt(2, marque.getId());
            int res = preparedStatement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String req = "DELETE FROM marque WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            int res = preparedStatement.executeUpdate();
        }
    }

    public ArrayList<Marque> readAll() throws SQLException {
        ArrayList<Marque> list = new ArrayList<>();
        try {
            ResultSet resultSet = ste.executeQuery("SELECT * FROM marque");
            while (resultSet.next()) {
                int marqueId = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                Marque marque = new Marque(marqueId, nom);
                list.add(marque);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Marque get(int id) throws SQLException {
        String req = "SELECT * FROM marque WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                return new Marque(id, nom);
            }
        }
        return null;
    }
    public Marque getByName(String nom) throws SQLException {
        String req = "SELECT * FROM marque WHERE nom = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setString(1, nom);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                return new Marque(id, nom);
            }
        }
        return null;
    }
}

