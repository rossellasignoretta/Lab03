package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {
	
	private List <String> vocabolario= new ArrayList <String>();
	
	public void loadDictionary(String language){
		if (language.equals("English")){
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
		else if (language.equals("Italiano")){
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
		Collections.sort(vocabolario);

		List<RichWord> temp= new ArrayList<RichWord>();
		for (String wtemp: inputTextList){
			/*if (vocabolario.contains(wtemp)){
				temp.add(new RichWord(wtemp, true));
			}
			else temp.add(new RichWord(wtemp, false));*/
			
			int min=0;
			int max=vocabolario.size();
			int posizione=(max-min)/2;
			String confronta=vocabolario.get(posizione);
			
			while(wtemp.compareTo(confronta)!=0 && (max-min)>1){
				if(wtemp.compareTo(confronta)<0){
					max=posizione;
					posizione=posizione-((max-min)/2);
				}
				if(wtemp.compareTo(confronta)>0){
					min=posizione;
					posizione=posizione+((max-min)/2);
				}
				
				confronta=vocabolario.get(posizione);
				}
			if(wtemp.compareTo(confronta)==0){
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
