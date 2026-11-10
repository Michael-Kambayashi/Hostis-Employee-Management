package com.gabriel.emplms;
import com.gabriel.emplms.model.Employee;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.Window;
import lombok.Setter;
import javafx.util.StringConverter;
import java.net.URL;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Locale;

public class GenericEmployeeController implements Initializable{
	@Setter
	CreateEmployeeController createEmployeeController;

	@Setter
	DeleteEmployeeController deleteEmployeeController ;

	@Setter
	EditEmployeeController editEmployeeController;

	@Setter
	ManageEmployeeController manageEmployeeController;

	@Setter
	Stage stage;

	@Setter
	Scene splashScene;

	@Setter
	Scene manageScene;

	@Setter
	public ListView<Employee> lvEmployees;

	@Setter
	public static Employee selectedItem;
	public TextField txtId;
	public TextField txtFirstname;
	public TextField txtLastname;
	public TextField txtEmail;
	public TextField txtPhoneNumber;
	public DatePicker dtHireDate;
	public ComboBox<Job> cmbJob;
	public TextField txtJobName;
	public ComboBox<Department> cmbDepartment;
	public TextField txtDepartmentName;
	public ComboBox<Manager> cmbManager;
	public TextField txtManagerName;
	public ComboBox<Gender> cmbGender;
	public TextField txtGenderName;
	public ComboBox<Status> cmbStatus;
	public TextField txtStatusName;
	public TextField txtAddress;
	public TextField txtCountry;
	public TextField txtState;
	public TextField txtCity;
	public DatePicker dtDateOfBirth;
	public TextField txtSalary;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Job[] jobs =  (Job[]) JobService.getService().getAll();
		cmbJob.getItems().addAll(jobs);
		StringConverter<Job> jobConverter = new StringConverter<Job>() {
			@Override
			public String toString(Job job) {
			if(job==null)
				return "";
			else
				return job.toString();
			}
			@Override
			public Job fromString(String s) {
				if(!s.isEmpty()){
					for (Job job : jobs) {
						if (s.equals(job.getName())){
							return job;
						}
					}
				}
				return null;
			}
		};
		cmbJob.setConverter(jobConverter);
		Department[] departments =  (Department[]) DepartmentService.getService().getAll();
		cmbDepartment.getItems().addAll(departments);
		StringConverter<Department> departmentConverter = new StringConverter<Department>() {
			@Override
			public String toString(Department department) {
			if(department==null)
				return "";
			else
				return department.toString();
			}
			@Override
			public Department fromString(String s) {
				if(!s.isEmpty()){
					for (Department department : departments) {
						if (s.equals(department.getName())){
							return department;
						}
					}
				}
				return null;
			}
		};
		cmbDepartment.setConverter(departmentConverter);
		Manager[] managers =  (Manager[]) ManagerService.getService().getAll();
		cmbManager.getItems().addAll(managers);
		StringConverter<Manager> managerConverter = new StringConverter<Manager>() {
			@Override
			public String toString(Manager manager) {
			if(manager==null)
				return "";
			else
				return manager.toString();
			}
			@Override
			public Manager fromString(String s) {
				if(!s.isEmpty()){
					for (Manager manager : managers) {
						if (s.equals(manager.getName())){
							return manager;
						}
					}
				}
				return null;
			}
		};
		cmbManager.setConverter(managerConverter);
		Gender[] genders =  (Gender[]) GenderService.getService().getAll();
		cmbGender.getItems().addAll(genders);
		StringConverter<Gender> genderConverter = new StringConverter<Gender>() {
			@Override
			public String toString(Gender gender) {
			if(gender==null)
				return "";
			else
				return gender.toString();
			}
			@Override
			public Gender fromString(String s) {
				if(!s.isEmpty()){
					for (Gender gender : genders) {
						if (s.equals(gender.getName())){
							return gender;
						}
					}
				}
				return null;
			}
		};
		cmbGender.setConverter(genderConverter);
		Status[] statuss =  (Status[]) StatusService.getService().getAll();
		cmbStatus.getItems().addAll(statuss);
		StringConverter<Status> statusConverter = new StringConverter<Status>() {
			@Override
			public String toString(Status status) {
			if(status==null)
				return "";
			else
				return status.toString();
			}
			@Override
			public Status fromString(String s) {
				if(!s.isEmpty()){
					for (Status status : statuss) {
						if (s.equals(status.getName())){
							return status;
						}
					}
				}
				return null;
			}
		};
		cmbStatus.setConverter(statusConverter);
		init();
	}
	protected void init(){
		System.out.println("Invoked from Generic Controller");
	}
	protected Employee toObject(boolean isEdit){
		Employee employee= new Employee();
		try {
			if(isEdit) {
				employee.setId(Integer.parseInt(txtId.getText()));
			}
			employee.setFirstname(txtFirstname.getText());
			employee.setLastname(txtLastname.getText());
			employee.setEmail(txtEmail.getText());
			employee.setPhoneNumber(txtPhoneNumber.getText());
			employee.setHireDate(toDate(dtHireDate.getValue()));
			Job job = cmbJob.getSelectionModel().getSelectedItem();
			employee.setJobId(job.getId());
			employee.setJobName(job.getName());
			Department department = cmbDepartment.getSelectionModel().getSelectedItem();
			employee.setDepartmentId(department.getId());
			employee.setDepartmentName(department.getName());
			Manager manager = cmbManager.getSelectionModel().getSelectedItem();
			employee.setManagerId(manager.getId());
			employee.setManagerName(manager.getName());
			Gender gender = cmbGender.getSelectionModel().getSelectedItem();
			employee.setGenderId(gender.getId());
			employee.setGenderName(gender.getName());
			Status status = cmbStatus.getSelectionModel().getSelectedItem();
			employee.setStatusId(status.getId());
			employee.setStatusName(status.getName());
			employee.setAddress(txtAddress.getText());
			employee.setCountry(txtCountry.getText());
			employee.setState(txtState.getText());
			employee.setCity(txtCity.getText());
			employee.setDateOfBirth(toDate(dtDateOfBirth.getValue()));
			employee.setSalary(Double.parseDouble(txtSalary.getText()));
		}catch (Exception e){
			showErrorDialog("Error" ,e.getMessage());
		}
		return employee;
	}
	protected void setFields(String action){
		String formattedDate;
		Employee employee = GenericEmployeeController.selectedItem;
		SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
		txtId.setText(Integer.toString(employee.getId()));
		txtFirstname.setText(employee.getFirstname());
		txtLastname.setText(employee.getLastname());
		txtEmail.setText(employee.getEmail());
		txtPhoneNumber.setText(employee.getPhoneNumber());
		dtHireDate.setValue(toLocalDate(employee.getHireDate()));
		Job job = JobService.getService().get(employee.getJobId());
		cmbJob.getSelectionModel().select(job);
		if(action.equals("Create") || action.equals("Edit")){
			cmbJob.setVisible(true);
			txtJobName.setVisible(false);
			cmbJob.getSelectionModel().select(job);
		}
		else{
			cmbJob.setVisible(false);
			txtJobName.setVisible(true);
			txtJobName.setText(job.getName());
		}
		txtJobName.setText(employee.getJobName());
		Department department = DepartmentService.getService().get(employee.getDepartmentId());
		cmbDepartment.getSelectionModel().select(department);
		if(action.equals("Create") || action.equals("Edit")){
			cmbDepartment.setVisible(true);
			txtDepartmentName.setVisible(false);
			cmbDepartment.getSelectionModel().select(department);
		}
		else{
			cmbDepartment.setVisible(false);
			txtDepartmentName.setVisible(true);
			txtDepartmentName.setText(department.getName());
		}
		txtDepartmentName.setText(employee.getDepartmentName());
		Manager manager = ManagerService.getService().get(employee.getManagerId());
		cmbManager.getSelectionModel().select(manager);
		if(action.equals("Create") || action.equals("Edit")){
			cmbManager.setVisible(true);
			txtManagerName.setVisible(false);
			cmbManager.getSelectionModel().select(manager);
		}
		else{
			cmbManager.setVisible(false);
			txtManagerName.setVisible(true);
			txtManagerName.setText(manager.getName());
		}
		txtManagerName.setText(employee.getManagerName());
		Gender gender = GenderService.getService().get(employee.getGenderId());
		cmbGender.getSelectionModel().select(gender);
		if(action.equals("Create") || action.equals("Edit")){
			cmbGender.setVisible(true);
			txtGenderName.setVisible(false);
			cmbGender.getSelectionModel().select(gender);
		}
		else{
			cmbGender.setVisible(false);
			txtGenderName.setVisible(true);
			txtGenderName.setText(gender.getName());
		}
		txtGenderName.setText(employee.getGenderName());
		Status status = StatusService.getService().get(employee.getStatusId());
		cmbStatus.getSelectionModel().select(status);
		if(action.equals("Create") || action.equals("Edit")){
			cmbStatus.setVisible(true);
			txtStatusName.setVisible(false);
			cmbStatus.getSelectionModel().select(status);
		}
		else{
			cmbStatus.setVisible(false);
			txtStatusName.setVisible(true);
			txtStatusName.setText(status.getName());
		}
		txtStatusName.setText(employee.getStatusName());
		txtAddress.setText(employee.getAddress());
		txtCountry.setText(employee.getCountry());
		txtState.setText(employee.getState());
		txtCity.setText(employee.getCity());
		dtDateOfBirth.setValue(toLocalDate(employee.getDateOfBirth()));
		txtSalary.setText(Double.toString(employee.getSalary()));
	}

	protected void clearFields(String action){
		txtId.setText("");
		//txtFirstname.setText("");
		//txtLastname.setText("");
		//txtEmail.setText("");
		//txtPhoneNumber.setText("");
		//dtHireDate.setText("");
		cmbJob.getSelectionModel().clearSelection();
		txtJobName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbJob.setVisible(true);
			txtJobName.setVisible(false);
		}
		else{
			cmbJob.setVisible(false);
			txtJobName.setVisible(true);
		}
		//txtJobName.setText("");
		cmbDepartment.getSelectionModel().clearSelection();
		txtDepartmentName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbDepartment.setVisible(true);
			txtDepartmentName.setVisible(false);
		}
		else{
			cmbDepartment.setVisible(false);
			txtDepartmentName.setVisible(true);
		}
		//txtDepartmentName.setText("");
		cmbManager.getSelectionModel().clearSelection();
		txtManagerName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbManager.setVisible(true);
			txtManagerName.setVisible(false);
		}
		else{
			cmbManager.setVisible(false);
			txtManagerName.setVisible(true);
		}
		//txtManagerName.setText("");
		cmbGender.getSelectionModel().clearSelection();
		txtGenderName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbGender.setVisible(true);
			txtGenderName.setVisible(false);
		}
		else{
			cmbGender.setVisible(false);
			txtGenderName.setVisible(true);
		}
		//txtGenderName.setText("");
		cmbStatus.getSelectionModel().clearSelection();
		txtStatusName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbStatus.setVisible(true);
			txtStatusName.setVisible(false);
		}
		else{
			cmbStatus.setVisible(false);
			txtStatusName.setVisible(true);
		}
		//txtStatusName.setText("");
		//txtAddress.setText("");
		//txtCountry.setText("");
		//txtState.setText("");
		//txtCity.setText("");
		//dtDateOfBirth.setText("");
		//txtSalary.setText("");
	}

	protected void enableFields(boolean enable){
		txtFirstname.editableProperty().set(enable);
		txtLastname.editableProperty().set(enable);
		txtEmail.editableProperty().set(enable);
		txtPhoneNumber.editableProperty().set(enable);
		dtHireDate.editableProperty().set(enable);
		cmbJob.editableProperty().set(enable);
		txtJobName.editableProperty().set(enable);
		txtJobName.editableProperty().set(enable);
		cmbDepartment.editableProperty().set(enable);
		txtDepartmentName.editableProperty().set(enable);
		txtDepartmentName.editableProperty().set(enable);
		cmbManager.editableProperty().set(enable);
		txtManagerName.editableProperty().set(enable);
		txtManagerName.editableProperty().set(enable);
		cmbGender.editableProperty().set(enable);
		txtGenderName.editableProperty().set(enable);
		txtGenderName.editableProperty().set(enable);
		cmbStatus.editableProperty().set(enable);
		txtStatusName.editableProperty().set(enable);
		txtStatusName.editableProperty().set(enable);
		txtAddress.editableProperty().set(enable);
		txtCountry.editableProperty().set(enable);
		txtState.editableProperty().set(enable);
		txtCity.editableProperty().set(enable);
		dtDateOfBirth.editableProperty().set(enable);
		txtSalary.editableProperty().set(enable);
	}

	public int getId(){
		return Integer.parseInt(txtId.getText());
	}

	protected void showErrorDialog(String message, String expandedMessage){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(message);
		alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(expandedMessage)));
		alert.showAndWait();
	}
	public void onBack(ActionEvent actionEvent) {
		Node node = ((Node) (actionEvent.getSource()));
		Window window = node.getScene().getWindow();
		window.hide();
		stage.setScene(manageScene);
		stage.show();
	}
	public void onClose(ActionEvent actionEvent) {
		Platform.exit();
	}
	LocalDate toLocalDate(Date date){
		Instant instant = date.toInstant();
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = instant.atZone( z );
		return zdt.toLocalDate();
	}
	protected Date toDate(LocalDate ld){
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = ld.atStartOfDay(z);
		Instant instant  = zdt.toInstant();
		return Date.from(instant);
	}
}

