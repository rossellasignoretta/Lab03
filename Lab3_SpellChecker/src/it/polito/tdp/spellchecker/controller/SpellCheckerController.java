package it.polito.tdp.spellchecker.controller;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	Dictionary d;
	@FXML
    private TextArea inputArea;;
    @FXML
    private TextArea outputArea;
    @FXML
    private Button btnSpell;
    @FXML
    private Button btnClear;
    @FXML
    private ComboBox <String> cmb;
    @FXML
    private Label txtNum;
    @FXML
    private Label txtTime;
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert inputArea != null : "fx:id=\"inputArea\" was not injected: check your FXML file 'SpellChecker.fxml'.";
    	assert outputArea != null : "fx:id=\"outputArea\" was not injected: check your FXML file 'SpellChecker.fxml'.";
    	assert btnSpell != null : "fx:id=\"bntSpell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
    	assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
    	assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
    	assert txtNum != null : "fx:id=\"txtNum\" was not injected: check your FXML file 'SpellChecker.fxml'.";
    	cmb.getItems().addAll("Italiano", "English");
    	d= new Dictionary();
    }
  
    
    @FXML
    void doSpellCheck(ActionEvent event) {
    	List <String> input= new ArrayList<String>();
    	String lingua=cmb.getValue();
    	String[] testo=inputArea.getText().toLowerCase().split(" ");
    	 
    	for (String temp: testo){
    		input.add(temp);
    	}
    	d.loadDictionary(lingua);
    	
    	int contatore=0;
    	List <RichWord> output=d.spellCheckText(input);
    	for (RichWord richWord : output) {
			if(!richWord.isCorretta()){
				outputArea.appendText(richWord.getWord()+"\n");
				contatore++;
			}
		}
    	txtNum.setText("The text contains "+contatore+" errors");
    	txtTime.setText("Spell check completed in "+System.currentTimeMillis()/1000+"seconds");
    	   	
    }
    
    @FXML
    void doClearText(ActionEvent event) {
    	inputArea.clear();
    	outputArea.clear();
    	d.clear();
    }
    
}
