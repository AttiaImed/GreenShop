package esprit.tn.greenshopjavafx.Controllers;
import esprit.tn.greenshopjavafx.Entities.Fournisseur.Fournisseur;
import esprit.tn.greenshopjavafx.Entities.GestionStock.Stock;
import esprit.tn.greenshopjavafx.Entities.GestionStock.categorie;
import esprit.tn.greenshopjavafx.Entities.Panier.Commande;
import esprit.tn.greenshopjavafx.Entities.Panier.PanierProduit;
import esprit.tn.greenshopjavafx.Entities.Produit.Marque;
import esprit.tn.greenshopjavafx.Entities.Produit.Produit;
import esprit.tn.greenshopjavafx.Entities.data;
import esprit.tn.greenshopjavafx.Services.FournisseurService.FournisseurService;
import esprit.tn.greenshopjavafx.Services.ProduitService.MarqueService;
import esprit.tn.greenshopjavafx.Services.ProduitService.ProduitService;

import esprit.tn.greenshopjavafx.Services.StockService.ServiceStock;
import esprit.tn.greenshopjavafx.Services.UtilisateurService.ServiceUtilisateur;
import esprit.tn.greenshopjavafx.Services.panierService.CommandeService;
import esprit.tn.greenshopjavafx.Utils.DataSource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
public class mainFormController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button menu_btn;
    @FXML
    private Button stock_btn;
    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane inventory_form;

    @FXML
    private AnchorPane stock_form;

    @FXML
    private TableView<Produit> inventory_tableView;

    @FXML
    private TableColumn<Produit, String> inventory_col_productID;

    @FXML
    private TableColumn<Produit, String> inventory_col_productName;

    @FXML
    private TableColumn<Produit, String> inventory_col_prix;

    @FXML
    private TableColumn<Produit, String> inventory_col_stock;

    @FXML
    private TableColumn<Produit, String> inventory_col_quantity;

    @FXML
    private TableColumn<Produit, String> inventory_col_status;

    @FXML
    private ImageView inventory_imageView;

    @FXML
    private Button inventory_importBtn;

    @FXML
    private Button inventory_addBtn;

    @FXML
    private Button inventory_updateBtn;

    @FXML
    private Button inventory_clearBtn;

    @FXML
    private Button inventory_deleteBtn;

    @FXML
    private TextField inventory_productID;

    @FXML
    private TextField inventory_productName;

    @FXML
    private TextField inventory_stock;

    @FXML
    private TextField inventory_price;

    @FXML
    private ComboBox<?> inventory_status;

    @FXML
    private ComboBox<String> inventory_marque;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private GridPane menu_gridPane;

    @FXML
    private TableView<Produit> menu_tableView;

    @FXML
    private TableColumn<Produit, String> menu_col_productName;

    @FXML
    private TableColumn<Produit, String> menu_col_quantity;

    @FXML
    private TableColumn<Produit, String> menu_col_price;

    @FXML
    private Label menu_total;

    @FXML
    private TextField menu_amount;

    @FXML
    private Label menu_change;

    @FXML
    private Button menu_payBtn;

    @FXML
    private Button menu_removeBtn;

    @FXML
    private Button menu_receiptBtn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_NC;

    @FXML
    private Label dashboard_TI;

    @FXML
    private Label dashboard_TotalI;

    @FXML
    private Label dashboard_NSP;

    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;

    @FXML
    private BarChart<?, ?> dashboard_CustomerChart;


    //Fournisseur

    @FXML
    private Button searchButton;
    @FXML
    private Button fournisseur_btn;

    @FXML
    private AnchorPane fournisseurs_form;

    @FXML
    private TableView<Fournisseur> fournisseur_tableView;
    @FXML
    private TableColumn<Fournisseur, Integer> fournisseur_col_id;
    @FXML
    private TableColumn<Fournisseur, String> fournisseur_col_nom;
    @FXML
    private TableColumn<Fournisseur, String> fournisseur_col_prenom;
    @FXML
    private TableColumn<Fournisseur, String> fournisseur_col_email;
    @FXML
    private TableColumn<Fournisseur, String> fournisseur_col_adresse;
    @FXML
    private TableColumn<Fournisseur, Integer> fournisseur_col_phoneNumber;

    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateFournisseurButton;
    @FXML
    private TextField searchTextField;



    @FXML
    private TextField nomTextField;
    @FXML
    private TextField prenomTextField;
    @FXML
    private TextField adresseTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneNumberTextField;



    private Alert alert;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;
    MarqueService marqueService = new MarqueService();
    ProduitService produitService = new ProduitService();
    CommandeService commandeService = new CommandeService();
    FournisseurService fournisseurService = new FournisseurService();
    ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();
    private ObservableList<Produit> cardListData = FXCollections.observableArrayList();

    public void dashboardDisplayNC() {

        try {
            int nc = 0;
            int count = serviceUtilisateur.count();

            if (count > 0) {
                nc = count;
            }
            dashboard_NC.setText(String.valueOf(nc));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardDisplayTI() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "SELECT SUM(montant) FROM commande WHERE date = '"
                + sqlDate + "'";

        connect = DataSource.getInstance().getCon();

        try {
            double ti = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                ti = result.getDouble("SUM(montant)");
            }

            dashboard_TI.setText("$" + ti);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardTotalI() {
        String sql = "SELECT SUM(montant) FROM commande";

        connect = DataSource.getInstance().getCon();

        try {
            float ti = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                ti = result.getFloat("SUM(montant)");
            }
            dashboard_TotalI.setText("$" + ti);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardNSP() {
        String sql = "SELECT SUM(quantity) FROM produit";
        connect = DataSource.getInstance().getCon();

        try {
            int q = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                q = result.getInt("SUM(quantity)");
            }
            dashboard_NSP.setText(String.valueOf(q));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardIncomeChart() {
        dashboard_incomeChart.getData().clear();

        String sql = "SELECT date, SUM(montant) FROM commande GROUP BY date ORDER BY TIMESTAMP(date)";
        connect = DataSource.getInstance().getCon();
        XYChart.Series chart = new XYChart.Series();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getFloat(2)));
            }

            dashboard_incomeChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardCustomerChart(){
        dashboard_CustomerChart.getData().clear();

        String sql = "SELECT Date_inscription as date, COUNT(id) FROM utilisateur GROUP BY date ORDER BY Date_inscription";
        connect = DataSource.getInstance().getCon();
        XYChart.Series chart = new XYChart.Series();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_CustomerChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inventoryAddBtn() {

        if (inventory_productName.getText().isEmpty()
                || inventory_marque.getSelectionModel().getSelectedItem() == null
                || inventory_stock.getText().isEmpty()
                || inventory_price.getText().isEmpty()
                || inventory_status.getSelectionModel().getSelectedItem() == null
                || data.path == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            // CHECK PRODUCT ID
            String checkProdID = "SELECT id FROM produit WHERE id = '"
                    + inventory_productID.getText() + "'";

            connect = DataSource.getInstance().getCon();

            try {
                Produit pExist = produitService.get(Integer.parseInt(inventory_productID.getText()));

                if (pExist != null) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(inventory_productID.getText() + " is already taken");
                    alert.showAndWait();
                } else
                {
                    Marque marque;
                    try {
                        marque = marqueService.getByName(String.valueOf(inventory_marque.getSelectionModel().getSelectedItem()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    String path = data.path;
                    path = path.replace("\\", "\\\\");
                    Produit p = new Produit(
                            inventory_productName.getText(),
                            Double.parseDouble(inventory_price.getText()),
                            Integer.parseInt(inventory_stock.getText()),
                            path,
                            (String) inventory_status.getSelectionModel().getSelectedItem(),
                            0,
                            marque
                    );
                    produitService.ajouter(p);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    inventoryShowData();
                    inventoryClearBtn();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventoryUpdateBtn() {

        if (inventory_productID.getText().isEmpty()
                || inventory_productName.getText().isEmpty()
                || inventory_marque.getSelectionModel().getSelectedItem() == null
                || inventory_stock.getText().isEmpty()
                || inventory_price.getText().isEmpty()
                || inventory_status.getSelectionModel().getSelectedItem() == null
                || data.path == null || data.id == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String path = data.path;
            path = path.replace("\\", "\\\\");
            Marque marque;
            try {
                marque = marqueService.getByName(String.valueOf(inventory_marque.getSelectionModel().getSelectedItem()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            Produit p = new Produit(Integer.parseInt(inventory_productID.getText()),inventory_productName.getText(),Double.parseDouble(inventory_price.getText()),Integer.parseInt(inventory_stock.getText()),path, (String) inventory_status.getSelectionModel().getSelectedItem(),0,marque);

            try {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE PRoduct ID: " + inventory_productID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    produitService.update(p);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW
                    inventoryShowData();
                    // TO CLEAR YOUR FIELDS
                    inventoryClearBtn();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventoryDeleteBtn() {
        if (data.id == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Product ID: " + inventory_productID.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                try {
                    produitService.delete(data.id);
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Deleted!");
                    alert.showAndWait();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        }
    }

    public void inventoryClearBtn() {

        inventory_productID.setText("");
        inventory_productName.setText("");
        inventory_marque.getSelectionModel().clearSelection();
        inventory_stock.setText("");
        inventory_price.setText("");
        inventory_status.getSelectionModel().clearSelection();
        data.path = "";
        data.id = 0;
        inventory_imageView.setImage(null);

    }

    // LETS MAKE A BEHAVIOR FOR IMPORT BTN FIRST
    public void inventoryImportBtn() {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new ExtensionFilter("Open Image File", "*.png", "*.jpg"));

        File file = openFile.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            data.path = file.getName(); // Only the image file name with its extension
            image = new Image(file.toURI().toString(), 120, 127, false, true);
            inventory_imageView.setImage(image);
        }
    }

    private ObservableList<Produit> inventoryListData;

    public void inventoryShowData() {
        try {
            inventoryListData = FXCollections.observableArrayList();
            inventoryListData.addAll(produitService.readAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        inventory_col_productName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        inventory_col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        inventory_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        inventory_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        inventory_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        inventory_tableView.setItems(inventoryListData);

    }

    public void inventorySelectData() {

        Produit prodData = inventory_tableView.getSelectionModel().getSelectedItem();
        int num = inventory_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        inventory_productID.setText(String.valueOf(prodData.getId()));
        inventory_productName.setText(prodData.getNom());
        inventory_stock.setText(String.valueOf(prodData.getStock()));
        inventory_price.setText(String.valueOf(prodData.getPrix()));

        // Assuming the image is in the same package as this class (adjust the path accordingly)
        String imagePath = "/esprit/tn/greenshopjavafx/image/" + prodData.getImage();

        // Use getClass().getResource() to load the image from the resources directory
        URL imageUrl = getClass().getResource(imagePath);
        data.path = prodData.getImage();
        data.id = prodData.getId();

        if (imageUrl != null) {
            image = new Image(imageUrl.toExternalForm(), 120, 127, false, true);
            inventory_imageView.setImage(image);
        } else {
            // Handle the case where the image could not be loaded
            System.out.println("Image not found: " + imagePath);
        }
        // Assuming you have a list of items in your ComboBox (adjust the type accordingly)
        ObservableList<String> items = inventory_marque.getItems();

        // Assuming prodData.getMarque() returns the value that should be selected in the ComboBox
        String selectedMarque = prodData.getMarque().getNom();

        // Check if the items list contains the selected value before setting it
        if (items.contains(selectedMarque)) {
            inventory_marque.setValue(selectedMarque);
        }
    }

    private ArrayList<Marque> brands;

    {
        try {
            brands = marqueService.readAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void inventoryBrandList() {

        List<String> typeL = new ArrayList<>();

        for (Marque data : brands) {
            typeL.add(data.getNom());
        }

        ObservableList listData = FXCollections.observableArrayList(typeL);
        inventory_marque.setItems(listData);
    }

    private String[] statusList = {"Available", "Unavailable"};

    public void inventoryStatusList() {

        List<String> statusL = new ArrayList<>();

        for (String data : statusList) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(statusL);
        inventory_status.setItems(listData);

    }

    public ObservableList<Produit> menuGetData() {

        String sql = "SELECT * FROM produit";

        ObservableList<Produit> listData = FXCollections.observableArrayList();
        connect = DataSource.getInstance().getCon();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Produit prod;

            while (result.next()) {
                Marque m = marqueService.get(result.getInt("marque"));
               Produit p1 = new Produit(result.getInt("id"),
                        result.getString("nom"),
                        result.getDouble("prix"),
                        result.getInt("stock"),
                        result.getString("image"),
                        result.getString("status"),
                        result.getInt("quantity"),
                        m);

                listData.add(p1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    public void menuDisplayCard() {

        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/esprit/tn/greenshopjavafx/cardProduct.fxml"));
                AnchorPane pane = load.load();
                cardProductController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }

                menu_gridPane.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void menuShowOrderData() {
        customerID();
        ObservableList<Produit> listData = FXCollections.observableArrayList();
        listData.addAll(PanierProduit.getProductListProperty());

        // Set up your table columns
        menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        menu_col_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        // Set the items directly to the TableView
        menu_tableView.setItems(listData);
        menu_tableView.refresh();

        System.out.println(listData);
    }
    private int getid;

    public void menuSelectOrder() {
        Produit prod = menu_tableView.getSelectionModel().getSelectedItem();
        int num = menu_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        // TO GET THE ID PER ORDER
        getid = prod.getId();

    }

    private double totalP;
    public void menuGetTotal() {
        customerID();
        totalP = PanierProduit.calculateTotalPrice();
    }

    public void menuDisplayTotal() {
        totalP = PanierProduit.calculateTotalPrice();
        menu_total.setText("$" + PanierProduit.calculateTotalPrice());
    }

    private double amount;
    private double change;

    public void menuAmount() {
        if (menu_amount.getText().isEmpty() || totalP == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();
        } else {
            amount = Double.parseDouble(menu_amount.getText());
            if (amount < totalP) {
                menu_amount.setText("");
            } else {
                change = (amount - totalP);
                menu_change.setText("$" + change);
            }
        }
    }

    public void menuPayBtn() {

        if (PanierProduit.calculateTotalPrice() == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please choose your order first!");
            alert.showAndWait();
        } else {
            menuGetTotal();

            try {
                if (false) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Messaged");
                    alert.setHeaderText(null);
                    alert.setContentText("Something wrong :3");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {
                        customerID();
                        menuGetTotal();
                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        Commande c  = new Commande( totalP, Integer.parseInt(String.valueOf(cID)),String.valueOf(sqlDate));
                        commandeService.ajouter(c);

                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Infomation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successful.");
                        alert.showAndWait();

                        menuShowOrderData();

                    } else {
                        alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Infomation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Cancelled.");
                        alert.showAndWait();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void menuRemoveBtn() {
        if (getid == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the order you want to remove");
            alert.showAndWait();
        } else {
            try {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this order?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    PanierProduit.removeProductById(getid);
                    menuShowOrderData();
                }

                menuShowOrderData();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void menuReceiptBtn() {

        if (PanierProduit.calculateTotalPrice() == 0 ) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Please order first");
            alert.showAndWait();
        } else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("getReceipt", (cID - 1));
            connect = DataSource.getInstance().getCon();
            try {

                // Load the JRXML file from the resources folder
                InputStream inputStream = getClass().getResourceAsStream("/esprit/tn/greenshopjavafx/report.jrxml");
                JasperDesign jDesign = JRXmlLoader.load(inputStream);

                // Compile the JRXML file into a JasperReport object
                JasperReport jReport = JasperCompileManager.compileReport(jDesign);

                // Fill the JasperReport with data and obtain a JasperPrint object
                JasperPrint jPrint = JasperFillManager.fillReport(jReport, map, connect);

                // Display the JasperPrint in a JasperViewer
                JasperViewer.viewReport(jPrint, false);

                // Restart the menu (possibly clearing the shopping cart)
                menuRestart();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void menuRestart() {
        totalP = 0;
        change = 0;
        amount = 0;
        menu_total.setText("$0.0");
        menu_amount.setText("");
        menu_change.setText("$0.0");
    }

    private int cID;

    public void customerID() {

        String sql = "SELECT MAX(id) FROM utilisateur";
        connect = DataSource.getInstance().getCon();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                cID = result.getInt("MAX(id)");
            }

            String checkCID = "SELECT MAX(customer_id) FROM commande";
            prepare = connect.prepareStatement(checkCID);
            result = prepare.executeQuery();
            int checkID = 0;
            if (result.next()) {
                checkID = result.getInt("MAX(customer_id)");
            }

            if (cID == 0) {
                cID += 1;
            } else if (cID == checkID) {
                cID += 1;
            }

            data.cID = cID;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Fournisseur
    private void configureTableViewColumns() {
        fournisseur_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        fournisseur_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        fournisseur_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        fournisseur_col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    }
    private void setupAddButton() {
        addButton.setOnAction(e -> {
            addFournisseurFromInputFields();
            refreshTableView(); // Rafraîchir la TableView après l'ajout
        });
    }
    private void refreshTableView() {
        try {
            List<Fournisseur> allFournisseurs = fournisseurService.readAll();
            fournisseur_tableView.getItems().setAll(allFournisseurs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidInput() {
        // Vérifie si les champs sont remplis correctement avant l'ajout
        return !nomTextField.getText().isEmpty() && !prenomTextField.getText().isEmpty();
    }

    private void clearInputFields() {
        nomTextField.clear();
        prenomTextField.clear();
        adresseTextField.clear();
        emailTextField.clear();
        phoneNumberTextField.clear();
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    //Ajouter fournisseur
    @FXML
    private void addFournisseurFromInputFields() {
        if (!isValidInput()) {
            // Affichage d'une alerte JavaFX pour indiquer que tous les champs doivent être remplis
            showAlert(Alert.AlertType.ERROR, "Champs manquants", "Veuillez remplir tous les champs.");
            return;
        }try {
            // Créer un nouvel objet Fournisseur avec les données de l'interface utilisateur
            Fournisseur newFournisseur = new Fournisseur();
            newFournisseur.setNom(nomTextField.getText());
            newFournisseur.setPrenom(prenomTextField.getText());
            newFournisseur.setAdresse(adresseTextField.getText());
            newFournisseur.setEmail(emailTextField.getText());
            newFournisseur.setPhonenumber(Integer.parseInt(phoneNumberTextField.getText()));
            // Ajouter le nouveau fournisseur
            fournisseurService.ajouter(newFournisseur);
            // Mettez à jour la TableView pour afficher le nouveau fournisseur
            refreshTableView();
            // Réinitialiser les champs après l'ajout
            clearInputFields();
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            // Gérer l'exception (par exemple, afficher un message d'erreur)
        }
    }

    //Supprimer fournisseur
    @FXML
    private void deleteFournisseur() {
        Fournisseur selectedFournisseur = fournisseur_tableView.getSelectionModel().getSelectedItem();
        if (selectedFournisseur == null) {
            // Affichage d'une alerte JavaFX pour indiquer qu'un fournisseur doit être sélectionné
            showAlert(Alert.AlertType.WARNING, "Aucun fournisseur sélectionné", "Veuillez sélectionner un fournisseur à supprimer.");
            return;
        }
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation de suppression");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Voulez-vous vraiment supprimer ce fournisseur ?");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                fournisseurService.delete(selectedFournisseur.getId());
                refreshTableView(); // Rafraîchir la TableView après la suppression
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Modifier fournisseur
    @FXML
    private void updateFournisseurButton() {
        Fournisseur selectedFournisseur = fournisseur_tableView.getSelectionModel().getSelectedItem();
        if (selectedFournisseur == null) {
            showAlert(Alert.AlertType.WARNING, "Aucun fournisseur sélectionné", "Veuillez sélectionner un fournisseur à modifier.");
            return;
        }if (!isValidInput()) {
            showAlert(Alert.AlertType.ERROR, "Champs manquants", "Veuillez remplir tous les champs.");
            return;
        }try {
            // Créer un nouvel objet Fournisseur avec les données de l'interface utilisateur
            Fournisseur updatedFournisseur = new Fournisseur();
            updatedFournisseur.setId(selectedFournisseur.getId());
            updatedFournisseur.setNom(nomTextField.getText());
            updatedFournisseur.setPrenom(prenomTextField.getText());
            updatedFournisseur.setAdresse(adresseTextField.getText());
            updatedFournisseur.setEmail(emailTextField.getText());
            updatedFournisseur.setPhonenumber(Integer.parseInt(phoneNumberTextField.getText()));
            // Mettre à jour le fournisseur
            fournisseurService.update(updatedFournisseur);
            // Mettre à jour la TableView pour afficher le fournisseur mis à jour
            refreshTableView();
            // Réinitialiser les champs après la modification
            clearInputFields();
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            // Gérer l'exception (par exemple, afficher un message d'erreur)
        }
    }
    //Rechercher fournisseur
    @FXML
    private void handleRechercherFournisseur(ActionEvent event) {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        if (nom.isEmpty() && prenom.isEmpty()) {
            refreshTableView();
            return;
        }try {
            // Rechercher les fournisseurs correspondant au nom et prénom saisis dans les champs de recherche
            List<Fournisseur> fournisseurs = fournisseurService.consulter(nom, prenom);
            if (!fournisseurs.isEmpty()) {
                // Afficher les fournisseurs trouvés dans la TableView
                fournisseur_tableView.getItems().setAll(fournisseurs);
            } else {
                // Affichage d'une alerte JavaFX pour indiquer qu'aucun fournisseur n'a été trouvé
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aucun fournisseur trouvé");
                alert.setHeaderText(null);
                alert.setContentText("Aucun fournisseur n'a été trouvé pour le nom et prénom saisis.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Contacter fournisseur
    @FXML
    private void handleContacterButton(ActionEvent event) {
        String fournisseurNom = nomTextField.getText(); // Récupérez le nom du fournisseur

        try {
            List<Integer> phoneNumbers = fournisseurService.getPhoneNumbersByNom(fournisseurNom);

            if (!phoneNumbers.isEmpty()) {
                // Afficher les numéros de téléphone dans une boîte de dialogue ou tout autre composant
                String phoneNumbersString = String.join(", ", phoneNumbers.stream().map(Object::toString).collect(Collectors.toList()));
                showAlert(Alert.AlertType.INFORMATION, "Numéros de téléphone", "Numéros de téléphone : " + phoneNumbersString);
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Aucun numéro de téléphone", "Aucun numéro de téléphone trouvé pour le fournisseur : " + fournisseurNom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //End Fournisseur


    //Stock///////////////////Omaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaar////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Stock///////////////////Omaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaar////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Stock////////////////////////////////////////Omarrrrrrrrrrrrrrrrrrrrrrrrrrrrr//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private TableView<Stock> stock_tableView;
    @FXML
    private TableColumn<Stock, Integer> stock_col_id;
    @FXML
    private TableColumn<Stock, String> stock_col_nom;
    @FXML
    private TableColumn<Stock, String> stock_col_marque;
    @FXML
    private TableColumn<Stock, String> stock_col_categorie;


    @FXML
    private TableColumn<Stock, Double> stock_col_prix;
    @FXML
    private TableColumn<Stock, Integer> stock_col_quantite;

    @FXML
    private TableColumn<Stock, Integer> stock_col_note;
    @FXML
    private TableColumn<Stock, Integer> stock_col_produit_id;

    @FXML
    Button addButtonStock;
    @FXML
    private ComboBox<Produit> produitComboBox;


    private ServiceStock serviceStock = new ServiceStock();
    private ProduitService serviceProduit = new ProduitService();

    @FXML
    private TextField quantiteTextField;

    @FXML
    private TextField nomTextField2, marqueTextField, prixTextField, noteTextField, produitIdTextField;

    @FXML
    private ComboBox<categorie> categorieComboBox;

    private void refreshTableViewOmar() {
        try {
            ArrayList<Stock> stocks = serviceStock.readAll();
            ObservableList<Stock> data = FXCollections.observableArrayList(stocks);
            stock_tableView.setItems(data); // Met à jour les données dans la TableView
            stock_tableView.refresh(); // Actualise la TableView

            // Debug statement to print the size of the TableView items
            System.out.println("Number of items in TableView: " + stock_tableView.getItems().size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setupProduitComboBox() throws SQLException {
        List<Produit> produits = serviceProduit.readAll();

        // Utilisez un mapper pour afficher uniquement le nom du produit dans la ComboBox
        produitComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Produit produit) {
                return produit != null ? produit.getNom() : "";
            }

            @Override
            public Produit fromString(String string) {
                // Conversion non nécessaire pour une ComboBox non-éditable
                return null;
            }
        });

        produitComboBox.getItems().setAll(produits);

        // Mettez à jour les champs marque et prix lorsque le produit sélectionné change
        produitComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // newValue est maintenant le Produit sélectionné
                prixTextField.setText(String.valueOf(newValue.getPrix()));
            }
        });
    }



    private void setupAddButtonStock() {
        addButtonStock.setOnAction(e -> {
            addStockFromInputFields();
            refreshTableViewOmar(); // Rafraîchir la TableView après l'ajout
        });
    }

    private boolean isValidInputOmar() {
        // Check if the ComboBox has a selected value and if the text field is not empty
        return produitComboBox.getValue() != null
                && !quantiteTextField.getText().isEmpty()
                && categorieComboBox.getValue() != null; // Use getValue() for ComboBox
    }

    private void resetInputFields() {
        produitComboBox.getSelectionModel().clearSelection();
        categorieComboBox.getSelectionModel().clearSelection(); // Clear selection for ComboBox
        quantiteTextField.clear();
    }



    private void addStockFromInputFields() {
        if (!isValidInputOmar()) {
            // Display a JavaFX alert to indicate that all fields must be filled
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        try {
            // Create a new Stock object with user interface data
            Stock newStock = new Stock();

            // Get the selected product from the ComboBox
            Produit produit = produitComboBox.getSelectionModel().getSelectedItem();
            newStock.setNom(produit.getNom());
            newStock.setMarque(produit.getMarque().getNom());
            newStock.setPrix(produit.getPrix());
            newStock.setIdProduit(produit.getId());

            // Get the selected category from the ComboBox
            categorie selectedCategorie = categorieComboBox.getValue();
            if (selectedCategorie != null) {
                newStock.setCategorie(selectedCategorie);
            } else {

            }

            // Set the quantity from the input field
            newStock.setQuantite(Integer.parseInt(quantiteTextField.getText()));

            // Call the ajouter method of your service to add the new stock
            serviceStock.ajouter(newStock);

            // Update the TableView to display the new stock
            refreshTableViewOmar();

            // Reset the input fields after the addition
            resetInputFields();

            // Display a success message
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Stock ajouté avec succès");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Le stock a été ajouté avec succès.");
            successAlert.showAndWait();
            quantiteTextField.clear();
            categorieComboBox.getSelectionModel().clearSelection();
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();

            // Display an error message
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur lors de l'ajout du stock");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Une erreur s'est produite lors de l'ajout du stock.");
            errorAlert.showAndWait();
        }
    }



    @FXML
    private void deleteStock(ActionEvent event) {
        Stock selectedStock = stock_tableView.getSelectionModel().getSelectedItem();

        if (selectedStock == null) {
            // Display a JavaFX alert to indicate that a stock must be selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun stock sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un stock à supprimer.");
            alert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation de suppression");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Voulez-vous vraiment supprimer ce stock ?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                serviceStock.delete(selectedStock.getId());
                refreshTableViewOmar(); // Refresh the TableView after deletion

                // Display a success message
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Stock supprimé avec succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Le stock a été supprimé avec succès.");
                successAlert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();

                // Display an error message
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur lors de la suppression du stock");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Une erreur s'est produite lors de la suppression du stock.");
                errorAlert.showAndWait();
            }
        }
    }


    @FXML
    private void modifyStock() {
        Stock selectedStock = stock_tableView.getSelectionModel().getSelectedItem();

        if (selectedStock == null) {
            // Display a JavaFX alert to indicate that a stock must be selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pas de stock sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un stock.");
            alert.showAndWait();
            return;
        }

        categorie newCategorie = categorieComboBox.getValue(); // Get selected category from ComboBox
        String newQuantityText = quantiteTextField.getText();

        if (newCategorie != null) {
            selectedStock.setCategorie(newCategorie);
        }

        if (!newQuantityText.isEmpty()) {
            try {
                int newQuantity = Integer.parseInt(newQuantityText);
                selectedStock.setQuantite(newQuantity);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Confirm with the user before updating the stock
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation de modification");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Voulez-vous vraiment modifier ce stock ?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Call the update method of your service to update the stock
                serviceStock.update(selectedStock);
                refreshTableViewOmar(); // Refresh the TableView after the update

                // Display a success message
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Stock modifié avec succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Le stock a été modifié avec succès.");
                successAlert.showAndWait();
                // Clear input fields after the update
                quantiteTextField.clear();
                categorieComboBox.getSelectionModel().clearSelection();
            } catch (SQLException e) {
                e.printStackTrace();

                // Display an error message
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur lors de la modification du stock");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Une erreur s'est produite lors de la modification du stock.");
                errorAlert.showAndWait();
            }
        }
    }



    @FXML
    private TextField rechercheMarqueTextField;




    // Créez une variable pour stocker les données d'origine
    private ObservableList<Stock> originalStockData;
    @FXML
    private ComboBox<categorie> categorieComboBoxSearch;



    // Modifiez votre méthode de recherche
    @FXML
    private void rechercheParMarqueEtCategorie() {
        String marque = rechercheMarqueTextField.getText();

        try {
            ServiceStock serviceStock = new ServiceStock();

            // Get the selected category from the ComboBox
            categorie selectedCategory = categorieComboBoxSearch.getValue();

            // Search by both marque and category or only by marque or only by category
            ArrayList<Stock> stocksTrouves = new ArrayList<>();

            if (!marque.isEmpty() && selectedCategory != null) {
                // Search by both marque and category
                stocksTrouves = serviceStock.chercherProduitParMarqueEtCategorie(marque, selectedCategory);
            } else if (!marque.isEmpty()) {
                // Search only by marque
                stocksTrouves = serviceStock.chercherProduitParMarque(marque);
            } else if (selectedCategory != null) {
                // Search only by category
                stocksTrouves = serviceStock.chercherProduitParCategorie(String.valueOf(selectedCategory));
            } else {
                // No search criteria provided, handle accordingly (e.g., display a message)
                System.out.println("Veuillez fournir des critères de recherche.");
                return;
            }

            // Filter the results to include those with a matching category or matching marque
            stocksTrouves = stocksTrouves.stream()
                    .filter(stock -> (selectedCategory == null || stock.getCategorie() == selectedCategory)
                            && (marque.isEmpty() || stock.getMarque().equalsIgnoreCase(marque)))
                    .collect(Collectors.toCollection(ArrayList::new));

            System.out.println("Number of results: " + stocksTrouves.size());

            stock_tableView.getItems().clear(); // Clear the current content
            stock_tableView.getItems().addAll(stocksTrouves); // Display the filtered results

            // Clear input fields after the search
            rechercheMarqueTextField.clear();
            categorieComboBoxSearch.getSelectionModel().clearSelection();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., display an error message)
        }
    }












    // Ajoutez une méthode pour restaurer les données d'origine
    private void restoreOriginalStockData() {
        stock_tableView.getItems().clear();
        stock_tableView.getItems().addAll(originalStockData);
    }




    @FXML
    private void refreshButtonClicked() {
        restoreOriginalStockData(); // Appel à la méthode pour restaurer les données d'origine
    }

    private ObservableList<categorie> getAllCategories() {
        // This method could fetch categories from your data source or provide some predefined values
        return FXCollections.observableArrayList(categorie.values());
    }










    //Stock///////////////////Omaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaar////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Stock///////////////////Omaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaar////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Stock///////////////////Omaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaar////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            fournisseurs_form.setVisible(false);
            stock_form.setVisible(false);
            dashboardDisplayNC();
            dashboardDisplayTI();
            dashboardTotalI();
            dashboardNSP();
            dashboardIncomeChart();
            dashboardCustomerChart();

        } else if (event.getSource() == inventory_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(true);
            menu_form.setVisible(false);
            fournisseurs_form.setVisible(false);
            stock_form.setVisible(false);
            inventoryBrandList();
            inventoryStatusList();
            inventoryShowData();
        } else if (event.getSource() == menu_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(true);
            fournisseurs_form.setVisible(false);
            stock_form.setVisible(false);
            menuDisplayCard();
            menuDisplayTotal();
            menuShowOrderData();

        }else if (event.getSource() == stock_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            stock_form.setVisible(true);
            fournisseurs_form.setVisible(false);
            refreshTableViewOmar();

        }
        else if (event.getSource() == fournisseur_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            stock_form.setVisible(false);
            fournisseurs_form.setVisible(true);

            setupAddButton();
            configureTableViewColumns();
            refreshTableView();
        }

    }
// LETS PROCEED TO OUR DASHBOARD FORM : )

    public void logout() {

        try {

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                // TO HIDE MAIN FORM
                logout_btn.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM AND SHOW IT
                Parent root = FXMLLoader.load(getClass().getResource("/esprit/tn/greenshopjavafx/AuthForm.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setTitle("Green Shop Management System");

                stage.setScene(scene);
                stage.show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayUsername() {

        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);

        username.setText(user);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        displayUsername();

        dashboardDisplayNC();
        dashboardDisplayTI();
        dashboardTotalI();
        dashboardNSP();
        dashboardIncomeChart();
        dashboardCustomerChart();

        inventoryBrandList();
        inventoryStatusList();
        inventoryShowData();

        menuDisplayCard();
        menuDisplayTotal();
        menuShowOrderData();




        //Fournisseur
        setupAddButton();
        configureTableViewColumns();
        refreshTableView();
        searchButton.setOnAction(e -> handleRechercherFournisseur(null));


        /////omarrrrr/////////////////////////////////

        setupAddButtonStock();
        try {
            setupProduitComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /////////////// Stock form /////////////////////////////////////////////////
        stock_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        stock_col_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        stock_col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        stock_col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        stock_col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        /////////////// Stock form /////////////////////////////////////////////////



        refreshTableViewOmar();
        originalStockData = FXCollections.observableArrayList();
        originalStockData.addAll(stock_tableView.getItems());
        stock_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Récupérez le stock sélectionné
                Stock selectedStock = stock_tableView.getSelectionModel().getSelectedItem();

                // Mettez à jour les champs du formulaire avec les informations du stock sélectionné
                categorieComboBox.getSelectionModel().select(selectedStock.getCategorie());
                quantiteTextField.setText(String.valueOf(selectedStock.getQuantite()));
            }
        });

        ObservableList<categorie> categories = FXCollections.observableArrayList(getAllCategories());
        categorieComboBox.setItems(categories);
        categorieComboBoxSearch.setItems(categories);


    }
}