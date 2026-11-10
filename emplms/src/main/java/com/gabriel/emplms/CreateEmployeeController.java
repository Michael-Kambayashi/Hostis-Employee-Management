package com.gabriel.emplms;
import com.gabriel.emplms.model.Employee;
import com.gabriel.emplms.service.EmployeeService;
import com.gabriel.emplms.model.Job;
import com.gabriel.emplms.service.JobService;
import com.gabriel.emplms.model.Department;
import com.gabriel.emplms.service.DepartmentService;
import com.gabriel.emplms.model.Manager;
import com.gabriel.emplms.service.ManagerService;
import com.gabriel.emplms.model.Gender;
import com.gabriel.emplms.service.GenderService;
import com.gabriel.emplms.model.Status;
import com.gabriel.emplms.service.StatusService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import java.net.URL;
import java.util.ResourceBundle;
import lombok.Setter;

public class CreateEmployeeController extends GenericEmployeeController {
	public ImageView imgEmployee;
	@Override
	public void init() {
		clearFields("Edit");
		enableFields(true);
	}
	public void onSubmit(ActionEvent actionEvent) {
		try {
			Employee employee = toObject(false);
			Employee newEmployee = EmployeeService.getService().create(employee);
			manageEmployeeController.getLvEmployees().getItems().add(newEmployee);
			Node node = ((Node) (actionEvent.getSource()));
			Window window = node.getScene().getWindow();
			window.hide();
			stage.setTitle("Manage Employee");
			stage.setScene(manageScene);
			stage.show();
		}
		catch (Exception e){
			showErrorDialog("Error encountered creating employee", e.getMessage());
		}
	}
	public void onClose(ActionEvent actionEvent) {
		super.onClose(actionEvent);
	}
}
