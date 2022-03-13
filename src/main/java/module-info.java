module de.gsso.sw_alpha {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires eu.hansolo.tilesfx;
    requires javafx.media;

    opens de.gsso.sw_alpha to javafx.fxml;
    exports de.gsso.sw_alpha;
}