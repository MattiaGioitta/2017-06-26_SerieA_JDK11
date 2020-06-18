package it.polito.tdp.seriea;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;
import it.polito.tdp.seriea.model.Vicino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

//controller turno A --> switchare al branch master_turnoB o master_turnoC per turno B o C

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Team> boxSquadra;

    @FXML
    private ChoiceBox<Season> boxStagione;

    @FXML
    private Button btnCalcolaConnessioniSquadra;

    @FXML
    private Button btnSimulaTifosi;

    @FXML
    private Button btnAnalizzaSquadre;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalizzaSquadre(ActionEvent event) {
    	this.txtResult.clear();
    	this.model.createGraph();
    	this.txtResult.appendText("Grafo creato\n");
    	this.txtResult.appendText(String.format("#Vertici: %d\n#Archi: %d", this.model.nVertici(),this.model.nArchi()));
        this.boxSquadra.getItems().addAll(this.model.getSquadre());
    }

    @FXML
    void doCalcolaConnessioniSquadra(ActionEvent event) {
    	this.txtResult.clear();
    	Team scelto = this.boxSquadra.getValue();
    	if(scelto == null) {
    		this.txtResult.setText("scegli una squadra");
    		return;
    	}
    	List<Vicino> vicini = this.model.getVicini(scelto);
    	this.txtResult.appendText("Squadre connesse a "+scelto.toString()+":\n");
    	for(Vicino v : vicini) {
    		this.txtResult.appendText(v.toString()+"\n");
    	}
    	this.boxStagione.getItems().addAll(this.model.getSeasons());

    }

    @FXML
    void doSimulaTifosi(ActionEvent event) {
    	this.txtResult.clear();
    	Season scelta = this.boxStagione.getValue();
    	if(scelta == null) {
    		this.txtResult.setText("scegli una stagione");
    		return;
    	}
    	this.model.simula(scelta);
    	Collection<Team> lista = this.model.getTeams();
    	for(Team t : lista) {
    		this.txtResult.appendText(t.getTeam()+" "+t.getPunti()+" "+t.getNumTifosi()+"\n");
    	}

    }

    @FXML
    void initialize() {
        assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxStagione != null : "fx:id=\"boxStagione\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnCalcolaConnessioniSquadra != null : "fx:id=\"btnCalcolaConnessioniSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSimulaTifosi != null : "fx:id=\"btnSimulaTifosi\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnAnalizzaSquadre != null : "fx:id=\"btnAnalizzaSquadre\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}