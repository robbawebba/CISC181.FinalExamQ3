package mortgageEstimator.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mortgageEstimator.MortgageEstimator;

public class EstimatorController {
	private MortgageEstimator main;
	
	@FXML
	private TextField grossIncome;
	@FXML
	private TextField monthlyDebt;
	@FXML
	private TextField interestRate;
	@FXML
	private ComboBox<String> term;
	@FXML
	private TextField downPayment;
	@FXML
	private TextField paymentOnly;
	@FXML
	private TextField housingAndOther;
	@FXML
	private TextField payAllowed;
	@FXML
	private TextField canBeFinanced;
	
	private ObservableList<String> terms = FXCollections.observableArrayList("10","15","30");
	
	double maxPay;
	double mortgage;

	private Object monthlyObl;

	private Object housePay;
	
	public EstimatorController() {
		// no-arg constructor
	}
	@FXML
	public void initialize() {
		term.setItems(terms);
		term.setValue("10");
	}
	public void handleEdit() {
		if (isComplete()) {
			double income = Double.parseDouble(grossIncome.getText());
			double debt = Double.parseDouble(monthlyDebt.getText());
			double downPay = Double.parseDouble(downPayment.getText());
			int trm = Integer.parseInt(term.getValue());
			double rate = Double.parseDouble(interestRate.getText())/100;
			
			calculate(income, debt, trm, rate);	

			canBeFinanced.setText("$" +mortgage);
			payAllowed.setText("$" +maxPay);
		}
	}

	private void calculate(double income, double debt, int trm,
			double rate) {
		calcPay(income, debt);
		mortgage = Math.round((maxPay*(Math.pow(1+(rate/12),(trm*12))- 1)/(rate/12))/(Math.pow(1+(rate/12),(trm*12))));
	}
	private void calcPay(double income, double debt) {
		double housePay = ((income/12)*0.18);
		paymentOnly.setText("$" + housePay);
		double monthlyObl = ((income/12)*0.36)-debt;
		housingAndOther.setText("$" + monthlyObl);
		if (housePay>=monthlyObl){
			maxPay = Math.round(monthlyObl);
		} else{
			maxPay = Math.round(housePay);
		}
	}
	private boolean isComplete() {
		return (!grossIncome.getText().isEmpty() && !monthlyDebt.getText().isEmpty() 
				&& !downPayment.getText().isEmpty() && 
				!interestRate.getText().isEmpty() &&
				term.getValue()!= null);
	}
	public void setMainApp(MortgageEstimator main) {
		this.main = main;
	}

	public MortgageEstimator getMain() {
		return main;
	}

}
