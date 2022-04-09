module com.example.javafxhomedevices {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxhomedevices to javafx.fxml;
    exports com.example.javafxhomedevices;
    exports com.example.javafxhomedevices.customExceptions;
    opens com.example.javafxhomedevices.customExceptions to javafx.fxml;
    exports com.example.javafxhomedevices.Apartment;
    opens com.example.javafxhomedevices.Apartment to javafx.fxml;
}