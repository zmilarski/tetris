package sample;



import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button tetris;

    @FXML
    private ImageView imageButtonHome;



    @FXML
    void initialize() {
        tetris.setOnAction( actionEvent ->{

        } );

    }
}
