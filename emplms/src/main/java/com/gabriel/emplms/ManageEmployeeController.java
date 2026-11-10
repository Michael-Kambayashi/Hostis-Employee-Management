package com.gabriel.emplms;
import com.gabriel.emplms.model.Employee;
import com.gabriel.emplms.service.EmployeeService;
import com.gabriel.emplms.GenericEmployeeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class ManageEmployeeController extends GenericEmployeeController{
	@Setter
	Stage stage;

	@Setter
	Scene createViewScene;

	@Setter
	Scene editViewScene;

	@Setter
	Scene deleteViewScene;

	public ImageView employeeImage;
	@FXML
	public Button btnCreate;

	@FXML
	public Button btnEdit;

	@FXML
	public Button btnDelete;

	@FXML
	public Button btnClose;

	@FXML
	public Button imageButton;
	Employee selectedItem;

	@FXML
	@Getter
	private ListView<Employee> lvEmployees;

	@Override
	public void init() {
		try {
			Employee[] employees = EmployeeService.getService().getAll();
			lvEmployees.getItems().addAll(employees);
			enableFields(false);
		}
		catch (Exception e){
			showErrorDialog("Message: ", e.getMessage());
		}
	}

	public void onAction(MouseEvent mouseEvent) {
		GenericEmployeeController.selectedItem = lvEmployees.getSelectionModel().getSelectedItem();
		if(GenericEmployeeController.selectedItem == null) {
			return;
		}
		setFields("Manage");
	}
	public void onCreate(ActionEvent actionEvent)  throws Exception {
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(createViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManageEmployeeJFXApp.class.getResource("create-empl-view.fxml"));
			Parent root = fxmlLoader.load();
			CreateEmployeeController controller = fxmlLoader.getController();
			controller.setStage(stage);
			createViewScene = new Scene(root, 300, 720);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
			controller.setManageEmployeeController(this);
		}

		stage.setTitle("Create Employee");
		stage.setScene(createViewScene);
		stage.show();
	}
	public void onEdit(ActionEvent actionEvent)  throws Exception {
		if(GenericEmployeeController.selectedItem == null){
			showErrorDialog("Please select an employee from the list", "Cannot edit");
		return;
		}
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(editViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManageEmployeeJFXApp.class.getResource("edit-empl-view.fxml"));
			Parent root = fxmlLoader.load();
			EditEmployeeController controller = fxmlLoader.getController();
			controller.setStage(stage);
			editViewScene = new Scene(root, 300, 720);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
			controller.setManageEmployeeController(this);
		}
		stage.setTitle("Edit Employee");
		stage.setScene(editViewScene);
		stage.show();
	}
	public void onDelete(ActionEvent actionEvent)  throws Exception {
		if(GenericEmployeeController.selectedItem == null){
			showErrorDialog("Please select an employee from the list", "Cannot delete");
		return;
		}
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(deleteViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManageEmployeeJFXApp.class.getResource("delete-empl-view.fxml"));
			Parent root = fxmlLoader.load();
			DeleteEmployeeController controller = fxmlLoader.getController();
			controller.setStage(stage);
			deleteViewScene = new Scene(root, 300, 720);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
			controller.setManageEmployeeController(this);
		}
		stage.setTitle("Delete Employee");
		stage.setScene(deleteViewScene);
		stage.show();
	}
	public void onClose(ActionEvent actionEvent) {
		super.onClose(actionEvent);
	}

}
