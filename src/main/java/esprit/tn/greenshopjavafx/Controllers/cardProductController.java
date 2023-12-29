package esprit.tn.greenshopjavafx.Controllers;

import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.ResourceBundle;

import esprit.tn.greenshopjavafx.Entities.Panier.PanierProduit;
import esprit.tn.greenshopjavafx.Entities.Produit.Produit;

import esprit.tn.greenshopjavafx.Services.ProduitService.ProduitService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;



public class cardProductController   implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Spinner<Integer> prod_spinner;

    @FXML
    private Button prod_addBtn;

    private Produit prodData;
    private Image image;

    private String prodID;
    private String type;
    private String prod_date;
    private String prod_image;

    private SpinnerValueFactory<Integer> spin;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;
    public static HashMap<Produit, Integer> ProdMap = new HashMap<>();
    Image img;
    public void setData(Produit prodData) {
        this.prodData = prodData;

        prod_image = prodData.getImage();
        prodID = String.valueOf(prodData.getId());
        prod_name.setText(prodData.getNom());
        prod_price.setText("$" + prodData.getPrix());
        String pathimage = "/esprit/tn/greenshopjavafx/image/" + prodData.getImage();
        URL imageurl = getClass().getResource(pathimage);
        if(imageurl != null)
        {
            img = new Image(imageurl.toExternalForm(),190, 94, false, true);
            prod_imageView.setImage(img);
        }else {
            System.out.println("image noy found ");
        }
        pr = prodData.getPrix();

    }
    private int qty;

    public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_spinner.setValueFactory(spin);
    }

    private double totalP;
    private double pr;
    ProduitService produitService = new ProduitService();

    public void addBtn() {
        Produit P ;

        try {
            P = produitService.get(Integer.parseInt(prodID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        qty = prod_spinner.getValue();
        if (P.getStatus().equals("Unavailable") || qty == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Something Wrong :3");
            alert.showAndWait();
        } else {

            if (P.getStock() < qty) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid. This product is Out of stock");
                alert.showAndWait();
            } else
            {
                System.out.println(prodID);
                PanierProduit.addProduct(P,qty, Integer.parseInt(prodID));
                System.out.println(PanierProduit.getProductListProperty());
            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setQuantity();
    }

}
