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
    	outputArea.setDisable(true);
    }
  
    
    @FXML
    void doSpellCheck(ActionEvent event) {
    	outputArea.setDisable(false);
    	inputArea.setDisable(true);
    	btnSpell.setDisable(true);
    	cmb.setDisable(true);
    	
    	String lingua=cmb.getValue();
    	if (lingua==null){
    		outputArea.setText("Choose a language!\n");
    		return;
    		}
    	d.loadDictionary(lingua);
    	
    	
    	String[] testo=inputArea.getText().toLowerCase().split(" ");
    	
    	List <String> input= new ArrayList<String>();    	 
    	for (String temp: testo){
    		input.add(temp.replaceAll("[ \\p{Punct}]", ""));
    	}
    		
    	
    	int contatore=0;
    	
    	Long t1= System.nanoTime();
    	List <RichWord> output=d.spellCheckText(input);
    	for (RichWord richWord : output) {
			if(!richWord.isCorretta()){
				outputArea.appendText(richWord.getWord()+"\n");
				contatore++;
			}
		}
    	Long t2= System.nanoTime();

    	txtNum.setText("The text contains "+contatore+" errors");
    	txtTime.setText("Spell check completed in "+(t2-t1)/1e9+"seconds");
    	   	
    }
    
    @FXML
    void doClearText(ActionEvent event) {
    	inputArea.setDisable(false);
    	outputArea.setDisable(true);
    	btnSpell.setDisable(false);
    	cmb.setDisable(false);


    	inputArea.clear();
    	outputArea.clear();
    	d.clear();
    }
    
}
