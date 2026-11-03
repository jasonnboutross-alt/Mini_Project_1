import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TableWindow {

    private Stage stage;

    public TableWindow(ObservableList<Employee> employees) {
        stage = new Stage();
        stage.setTitle("All Employees");

        Text title = new Text("Employees");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));

        TableView<Employee> table = new TableView<>();

        TableColumn<Employee, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee, Double> salaryCol = new TableColumn<>("Salary");
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Employee, LocalDate> dobCol = new TableColumn<>("Birth Date");
        dobCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        TableColumn<Employee, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Employee, String> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));

        TableColumn<Employee, Boolean> fullTimeCol = new TableColumn<>("Full-time");
        fullTimeCol.setCellValueFactory(new PropertyValueFactory<>("fullTime"));

        table.getColumns().addAll(idCol, nameCol, salaryCol, dobCol,
                genderCol, deptCol, fullTimeCol);

        table.setItems(employees); 

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(evt -> stage.close());

        VBox vbox = new VBox(15, title, table, closeBtn);
        vbox.setPadding(new Insets(20));

        stage.setScene(new Scene(vbox, 750, 450));
    }

    public void show() {
        stage.show();
    }
}