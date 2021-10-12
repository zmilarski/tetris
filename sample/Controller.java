package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//import static org.graalvm.compiler.options.OptionType.User;

public class Controller extends GameTetris{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button LoginSignUpButton;

    @FXML
    void initialize() {
        authSignInButton.setOnAction(actionEvent -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();
            if(!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            }else {
                System.out.println("Login and password is empty");
            }
        });
        LoginSignUpButton.setOnAction(actionEvent -> {
            Thread thread = new Thread(() -> {
                System.out.println("Tetris Game is started");
                new GameTetris().go();
                System.out.println("Tetris Game is stoped");
            });
            thread.start();

            LoginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/SignUp.fxml"));
            try {
                loader.load();
            } catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
      /*  ResultSet result =  dbHandler.getUser(user);

        int counter = 0;
        while (true){
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }
        if(counter>=1){
            System.out.println("Success!");
        }*/
    }
}
