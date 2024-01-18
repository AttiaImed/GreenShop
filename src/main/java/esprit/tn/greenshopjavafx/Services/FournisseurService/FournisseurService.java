package esprit.tn.greenshopjavafx.Services.FournisseurService;

import esprit.tn.greenshopjavafx.Entities.Fournisseur.Fournisseur;
import esprit.tn.greenshopjavafx.Entities.Produit.Produit;
import esprit.tn.greenshopjavafx.Services.IService;
import esprit.tn.greenshopjavafx.Services.ProduitService.ProduitService;
import esprit.tn.greenshopjavafx.Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurService implements IService<Fournisseur> {

    private Connection connnection = DataSource.getInstance().getCon();
    private Statement statement;
    ProduitService produitService = new ProduitService();

    public FournisseurService() {
        try {
            statement = connnection.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @Override
    public  void ajouter(Fournisseur fournisseur) throws SQLException {
        String query = "INSERT INTO fournisseur (nom, prenom, email, adresse, phonenumber) VALUES ('"
                + fournisseur.getNom() + "', '" + fournisseur.getPrenom() + "', '" + fournisseur.getEmail()
                + "', '" + fournisseur.getAdresse() + "', '" + fournisseur.getPhonenumber() + "')";
        int res = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                fournisseur.setId(generatedKeys.getInt(1));
                System.out.println("Fournisseur ajouté avec succès. Nouvel ID : " + fournisseur.getId());
            } else {
                System.out.println("Erreur lors de la récupération de l'ID généré automatiquement.");
            }
        }
    }

    @Override
    public void update(Fournisseur fournisseur) throws SQLException {
        String req = "UPDATE fournisseur SET " +
                "nom = '" + fournisseur.getNom() + "', " +
                "prenom = '" + fournisseur.getPrenom() + "', " +
                "email = '" + fournisseur.getEmail() + "', " +
                "adresse = '" + fournisseur.getAdresse() + "', " +
                "phonenumber = '" + fournisseur.getPhonenumber() + "' " +
                "WHERE id = " + fournisseur.getId();
        int res = statement.executeUpdate(req);
    }

    @Override
    public  void delete(int id) throws SQLException {
        String req = "DELETE FROM fournisseur WHERE id = " + id;
        int res = statement.executeUpdate(req);
    }

    @Override
    public ArrayList<Fournisseur> readAll() throws SQLException {
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from fournisseur");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nom = resultSet.getString(2);
                String prenom = resultSet.getString(3);
                String email = resultSet.getString(4);
                String adresse = resultSet.getString(5);
                int phonenumber = resultSet.getInt(6);

                fournisseurs.add(new Fournisseur(id, nom, prenom, email, adresse,phonenumber));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return fournisseurs;
    }

    @Override
    public Fournisseur get(int id) throws SQLException {
        return null;
    }

    public List<Fournisseur> consulter(String nom, String prenom) throws SQLException {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        String req = "SELECT * FROM fournisseur WHERE nom = ? AND prenom = ?";
        try (PreparedStatement preparedStatement = connnection.prepareStatement(req)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String email = resultSet.getString(4);
                    String adresse = resultSet.getString(5);
                    int phonenumber = resultSet.getInt(6);

                    Fournisseur fournisseur = new Fournisseur(id, nom, prenom, email, adresse, phonenumber);
                    fournisseurs.add(fournisseur);
                }
            }
        }
        return fournisseurs;
    }


    public Fournisseur contacterParPhoneNumber(String phoneNumber) throws SQLException {
        String req = "SELECT * FROM fournisseur WHERE phoneNumber = ?";
        PreparedStatement preparedStatement = connnection.prepareStatement(req);
        preparedStatement.setString(1, phoneNumber);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String nom = resultSet.getString(2);
            String prenom = resultSet.getString(3);
            String email = resultSet.getString(4);
            String adresse = resultSet.getString(5);
            int phonenumber = resultSet.getInt(6);



            Fournisseur fournisseur = new Fournisseur(id, nom, prenom, email, adresse, phonenumber);
            return fournisseur;
        } else {
            return null;
        }
    }

    public List<Integer> getPhoneNumbersByNom(String nom) throws SQLException {
        List<Integer> phoneNumbers = new ArrayList<>();
        String req = "SELECT phoneNumber FROM fournisseur WHERE nom = ?";
        PreparedStatement preparedStatement = connnection.prepareStatement(req);
        preparedStatement.setString(1, nom);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int phoneNumber = resultSet.getInt(1);
            phoneNumbers.add(phoneNumber);
        }

        return phoneNumbers;
    }
}
