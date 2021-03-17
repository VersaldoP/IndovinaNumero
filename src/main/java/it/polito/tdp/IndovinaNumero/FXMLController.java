/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.IndovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	private Model model;

	
	
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="layoutTentativo"
    private HBox layoutTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativoUtente"
    private TextField txtTentativoUtente; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void doNuovaPartita(ActionEvent event) {
//    	this.model = new Model();
    	this.model.nuovaPartita();
    	
    	//gestione dell'interfaccia
    	this.txtTentativi.setText(Integer.toString(this.model.getTMAX()));
    	this.layoutTentativo.setDisable(false);
    	this.txtRisultato.setText("");
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//lettura input dell'utente
    	
    	String ts = txtTentativoUtente.getText();
    	
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un numero!");
    		txtTentativi.setText("0");
    		this.layoutTentativo.setDisable(false);
    		
    		return;
    	}

    	this.txtTentativoUtente.setText("");
    	
    	
    	
    	int result ;
    	try {
    	 result = this.model.tentativo(tentativo);
    	}
    	catch(IllegalStateException e) {
    		txtRisultato.setText("La partita non è in corso ");
    		this.layoutTentativo.setDisable(false);
    		return;
    		
    	}
    	catch(InvalidParameterException e) {
    		
    		txtRisultato.setText("La partita non è ancora in corso O hai inserito un numero già inserito");
    		return;
    	}
    	this.txtTentativi.setText(Integer.toString(this.model.getTMAX()-this.model.getTentativiFatti()));
    	
    	if(result==0) {
    		//HO INDOVINATO!
    		txtRisultato.setText("HAI VINTO CON " + this.model.getTentativiFatti() + "TENTATIVI");
//    		this.inGioco = false;
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	else {
    		if(this.model.getTentativiFatti()==this.model.getTMAX()) {
        		txtRisultato.setText("Hai esaurito i tentativi");
        		this.txtTentativi.setText(Integer.toString(this.model.getTMAX()-this.model.getTentativiFatti()));
        		this.layoutTentativo.setDisable(true);
        		return;
    		}
    	
    	
    	
    	//Non ho vinto -> devo informare l'utente circa la bontà del suo tentativo
    	if(result <0) {
    		txtRisultato.setText("TENTATIVO TROPPO BASSO");
    	} else {
    		txtRisultato.setText("TENTATIVO TROPPO ALTO");
    	}
    	}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Model model) {
    	this.model = model;
    }
}
