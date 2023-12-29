package esprit.tn.greenshopjavafx.Entities.Panier;

import esprit.tn.greenshopjavafx.Entities.Produit.Produit;
import javafx.beans.property.ListProperty;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanierProduit {

    private static ListProperty<Produit> productListProperty = new SimpleListProperty<>(FXCollections.observableArrayList());

    public static ListProperty<Produit> getProductListProperty() {
        return productListProperty;
    }

    public static void addProduct(Produit produit , int quantity,int productId) {
        List<Produit> productList = productListProperty.getValue();
        boolean productExists = false;

        for (Produit existingProduct : productList) {
            if (existingProduct.getId() == productId) {
                existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            produit.setQuantity(quantity);
            productList.add(produit);
        }
    }
    public static void removeProductById(int productId) {
        List<Produit> productList = productListProperty.getValue();

        for (Produit produit : productList) {
            if (produit.getId() == productId) {
                productList.remove(produit);
                break; // Assuming each product s a unique ID, so we can break after finding the first match
            }
        }
    }
    public static double calculateTotalPrice() {
        double total = 0.0;

        for (Produit produit : productListProperty.getValue()) {
            total += produit.getPrix() * produit.getQuantity();
        }

        return total;
    }


}
