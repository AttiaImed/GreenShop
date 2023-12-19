package esprit.tn.greenshopjavafx.Test;


import esprit.tn.greenshopjavafx.Entities.Produit.Marque;
import esprit.tn.greenshopjavafx.Entities.Produit.Produit;
import esprit.tn.greenshopjavafx.Services.ProduitService.MarqueService;
import esprit.tn.greenshopjavafx.Services.ProduitService.ProduitService;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestProduit {
    public static void main(String[] args) {
        try {
            // Test MarqueService
            testMarqueService();

            // Test ProduitService
            testProduitService();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void testMarqueService() throws SQLException {
        MarqueService marqueService = new MarqueService();

        // Add a brand
        Marque brand = new Marque("Sony");
        marqueService.ajouter(brand);
        System.out.println("Added Brand: " + brand);

        // Update brand name
        brand.setNom("Sony Updated");
        marqueService.update(brand);
        System.out.println("Updated Brand: " + brand);

        // Get and print all brands
        ArrayList<Marque> brands = marqueService.readAll();
        System.out.println("All Brands: " + brands);

        // Get a specific brand by ID
        int brandId = brand.getId();
        Marque retrievedBrand = marqueService.get(brandId);
        System.out.println("Retrieved Brand by ID " + brandId + ": " + retrievedBrand);

        // Delete the brand
//        marqueService.delete(brandId);
//        System.out.println("Deleted Brand with ID " + brandId);
    }

    private static void testProduitService() throws SQLException {
        ProduitService produitService = new ProduitService();

        // Assuming you have Categorie and Marque objects
        Marque brand = new Marque(1, "Sony");

        // Add a product
        Produit product = new Produit("TV", 599.99,54,"dfg","true",1425, brand);
        produitService.ajouter(product);
        System.out.println("Added Product: " + product);

        // Update product details
        product.setNom("Updated TV");
        product.setPrix(699.99);
        produitService.update(product);
        System.out.println("Updated Product: " + product);

        // Get and print all products
        ArrayList<Produit> products = produitService.readAll();
        System.out.println("All Products: " + products);

        // Get a specific product by ID
        int productId = product.getId();
        Produit retrievedProduct = produitService.get(productId);
        System.out.println("Retrieved Product by ID " + productId + ": " + retrievedProduct);

        // Delete the product
//        produitService.delete(productId);
//        System.out.println("Deleted Product with ID " + productId);
    }

}
