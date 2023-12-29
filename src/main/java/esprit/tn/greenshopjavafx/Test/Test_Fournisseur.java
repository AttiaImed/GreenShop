//package esprit.tn.greenshopjavafx.Test;
//
//import esprit.tn.greenshopjavafx.Entities.Fournisseur.Fournisseur;
//import esprit.tn.greenshopjavafx.Services.FournisseurService.FournisseurService;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class Test_Fournisseur {
//
//    public static void main(String[] args) {
//
//
//        FournisseurService fournisseurService = new FournisseurService();
//
//        // Test d'ajout
//        try {
//            Fournisseur nouveauFournisseur = new Fournisseur(0, "Faouzi", "Chargui", "Faouzi@gmail.com", "Lagoulette", 97708777);
//            fournisseurService.ajouter(nouveauFournisseur);
//            System.out.println("Fournisseur ajouté avec succès. ID : " + nouveauFournisseur.getId());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Fournisseur nouveauFournisseur = new Fournisseur(1, "Chokri", "Jbeli", "chokrijbeli@gmail.com", "Khaireddine", 98653214);
//            fournisseurService.ajouter(nouveauFournisseur);
//            System.out.println("Fournisseur ajouté avec succès. ID : " + nouveauFournisseur.getId());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Fournisseur nouveauFournisseur = new Fournisseur(2, "Aicha", "Dhaoui", "Aicha@gmail.com", "SidiBouSaid", 56064618);
//            fournisseurService.ajouter(nouveauFournisseur);
//            System.out.println("Fournisseur ajouté avec succès. ID : " + nouveauFournisseur.getId());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Test de lecture de tous les fournisseurs
//        try {
//            ArrayList<Fournisseur> fournisseurs = fournisseurService.readAll();
//            System.out.println("Liste de tous les fournisseurs :");
//            for (Fournisseur f : fournisseurs) {
//                System.out.println(f);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Test de modification
//        try {
//            Fournisseur fournisseurAModifier = fournisseurService.consulter("Faouzi","Chargui"); // Remplacez 1 par l'ID réel
//            if (fournisseurAModifier != null) {
//                fournisseurAModifier.setNom("NouveauNom");
//                fournisseurService.update(fournisseurAModifier);
//                System.out.println("Fournisseur modifié avec succès.");
//            } else {
//                System.out.println("Fournisseur non trouvé.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        // Test de lecture de tous les fournisseurs
//        try {
//            ArrayList<Fournisseur> fournisseurs = fournisseurService.readAll();
//            System.out.println("Liste de tous les fournisseurs :");
//            for (Fournisseur f : fournisseurs) {
//                System.out.println(f);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Test de suppression
//        try {
//            int idFournisseurASupprimer = 8; // Remplacez 2 par l'ID réel
//            fournisseurService.delete(idFournisseurASupprimer);
//            System.out.println("Fournisseur supprimé avec succès. ID : " + idFournisseurASupprimer);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        // Test de lecture de tous les fournisseurs
//        try {
//            ArrayList<Fournisseur> fournisseurs = fournisseurService.readAll();
//            System.out.println("Liste de tous les fournisseurs :");
//            for (Fournisseur f : fournisseurs) {
//                System.out.println(f);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Test de contacter
//        try {
//            int idFournisseurAContacter = 10; // Remplacez 3 par l'ID réel
//            String message = "Bonjour, nous sommes intéressés par vos produits.";
//            fournisseurService.contacterParPhoneNumber("");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
