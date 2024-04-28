module org.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires metadata.extractor;


    opens org.example.finalproject to javafx.fxml;
    exports org.example.finalproject;
}