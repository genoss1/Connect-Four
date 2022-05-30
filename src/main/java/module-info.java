module com.example.demo {
  requires javafx.controls;
  requires javafx.fxml;


  opens connectfour to javafx.fxml;
  exports connectfour;
}