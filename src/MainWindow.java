import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow {

    private Stage stage;
    private Scene formScene;
    private Scene successScene;
    private Text successMsg;
    private ObservableList<Employee> employees;

    public MainWindow(ObservableList<Employee> employees) {
        this.employees = employees;

        stage = new Stage();
        stage.setTitle("Employee Management System");

        createFormScene();
        createSuccessScene();

        stage.setScene(formScene); 
    }


    private void createFormScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));

        Text title = new Text("Add Employee");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
        grid.add(title, 0, 0, 2, 1);

        grid.add(new Label("ID:"), 0, 1);
        TextField idFld = new TextField();
        idFld.setPromptText("e.g. 1");
        grid.add(idFld, 1, 1);

        grid.add(new Label("Name:"), 0, 2);
        TextField nameFld = new TextField();
        grid.add(nameFld, 1, 2);

        grid.add(new Label("Salary:"), 0, 3);
        TextField salaryFld = new TextField();
        salaryFld.setPromptText("e.g. 1500");
        grid.add(salaryFld, 1, 3);

        grid.add(new Label("Birth Date:"), 0, 4);
        DatePicker dobPck = new DatePicker();
        grid.add(dobPck, 1, 4);

        grid.add(new Label("Gender:"), 0, 5);
        RadioButton maleBtn = new RadioButton("Male");
        maleBtn.setSelected(true);
        RadioButton femaleBtn = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleBtn.setToggleGroup(genderGroup);
        femaleBtn.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(25, maleBtn, femaleBtn);
        grid.add(genderBox, 1, 5);

        grid.add(new Label("Department:"), 0, 6);
        ChoiceBox<String> deptBox = new ChoiceBox<>();
        deptBox.getItems().addAll("IT", "HR", "Finance", "Sales");
        deptBox.getSelectionModel().selectFirst();
        grid.add(deptBox, 1, 6);

        grid.add(new Label("Full-time:"), 0, 7);
        CheckBox fullTimeBox = new CheckBox("Yes");
        grid.add(fullTimeBox, 1, 7);

        Button submitBtn = new Button("Submit");
        Button viewBtn = new Button("View All Employees");
        HBox buttons = new HBox(10, submitBtn, viewBtn);
        grid.add(buttons, 1, 8);

        Text errorMsg = new Text();
        errorMsg.setFill(Color.RED);
        grid.add(errorMsg, 0, 9, 2, 1);

        submitBtn.setOnAction(evt -> {
            if (idFld.getText().isEmpty() || nameFld.getText().isEmpty()
                    || salaryFld.getText().isEmpty() || dobPck.getValue() == null) {
                errorMsg.setText("Please fill all the fields!");
            } else {
                try {
                    int id = Integer.parseInt(idFld.getText());
                    String name = nameFld.getText();
                    double salary = Double.parseDouble(salaryFld.getText());
                    LocalDate birthDate = dobPck.getValue();
                    String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
                    String department = deptBox.getValue();
                    boolean fullTime = fullTimeBox.isSelected();

                    employees.add(new Employee(id, name, salary, birthDate,
                            gender, department, fullTime));

                    
                    idFld.setText("");
                    nameFld.setText("");
                    salaryFld.setText("");
                    dobPck.setValue(null);
                    maleBtn.setSelected(true);
                    deptBox.getSelectionModel().selectFirst();
                    fullTimeBox.setSelected(false);
                    errorMsg.setText("");

                    successMsg.setText(name + " was added successfully!");
                    stage.setScene(successScene); 
                } catch (NumberFormatException e) {
                    errorMsg.setText("ID and Salary must be numbers!");
                }
            }
        });

        viewBtn.setOnAction(evt -> new TableWindow(employees).show());

        formScene = new Scene(grid, 450, 480);
    }

 
    private void createSuccessScene() {
        successMsg = new Text();
        successMsg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));

        Button addAnotherBtn = new Button("Add Another Employee");
        addAnotherBtn.setOnAction(evt -> stage.setScene(formScene));

        Button viewAllBtn = new Button("View All Employees");
        viewAllBtn.setOnAction(evt -> new TableWindow(employees).show());

        VBox vbox = new VBox(20, successMsg, addAnotherBtn, viewAllBtn);
        vbox.setAlignment(Pos.CENTER);

        successScene = new Scene(vbox, 450, 480);
    }

    public void show() {
        stage.show();
    }
}