package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	private List <String> vocabolario= new ArrayList <String>();
	
	public void loadDictionary(String language){
		if (language.compareTo("English")==0){
			try {
			FileReader fr = new FileReader("rsc/English.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
			vocabolario.add(word);
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
		}
		else if (language.compareTo("Italiano")==0){
			try {
				FileReader fr = new FileReader("rsc/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
				vocabolario.add(word);
				}
				br.close();
				} catch (IOException e){
				System.out.println("Errore nella lettura del file");
				}
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> temp= new ArrayList<RichWord>();
		for (String wtemp: inputTextList){
			if (vocabolario.contains(wtemp)){
				temp.add(new RichWord(wtemp, true));
			}
			else temp.add(new RichWord(wtemp, false));
		}
		return temp;
	}

	public void clear() {
		vocabolario.clear();
	}
}
