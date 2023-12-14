module esprit.tn.greenshopjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jasperreports;

    opens esprit.tn.greenshopjavafx.Controllers to javafx.fxml;
    opens esprit.tn.greenshopjavafx.Entities.Produit to javafx.base;

    exports esprit.tn.greenshopjavafx;
    exports esprit.tn.greenshopjavafx.Controllers;

}