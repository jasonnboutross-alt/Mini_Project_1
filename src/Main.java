import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main extends Application {
	private ObservableList<Employee> employees = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee System - Sign In");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));

        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userLbl = new Label("User Name:");
        grid.add(userLbl, 0, 1);
        TextField userFld = new TextField();
        grid.add(userFld, 1, 1);

        Label pwLbl = new Label("Password:");
        grid.add(pwLbl, 0, 2);
        PasswordField pwFld = new PasswordField();
        grid.add(pwFld, 1, 2);
        
        Button signInBtn = new Button("Sign in");
        grid.add(signInBtn, 1, 3);

        Text errorMsg = new Text();
        errorMsg.setFill(Color.RED);
        grid.add(errorMsg, 0, 4, 2, 1);
        
     
        signInBtn.setOnMouseEntered(evt -> {
            if (userFld.getText().isEmpty() || pwFld.getText().isEmpty()) {
                if (signInBtn.getTranslateX() == 0) {
                    signInBtn.setTranslateX(-100); 
                } else {
                    signInBtn.setTranslateX(0);   
                }
                errorMsg.setText("Please fill the input fields before proceeding");
            }
        });

        userFld.textProperty().addListener((obs, oldText, newText) -> signInBtn.setTranslateX(0));
        pwFld.textProperty().addListener((obs, oldText, newText) -> signInBtn.setTranslateX(0));
        
        
        signInBtn.setOnAction(evt -> {
            String username = userFld.getText();
            String password = pwFld.getText();

            if (username.isEmpty() || password.isEmpty()) {
                errorMsg.setText("Username and password are required!");
            } else if (!username.equals("jason") || !password.equals("1234")) {
                errorMsg.setText("Incorrect username and/or password");
            } else {
                MainWindow mainWindow = new MainWindow(employees); 
                mainWindow.show();                        
                primaryStage.close();                     
            }
        });

        Scene scene = new Scene(grid, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}