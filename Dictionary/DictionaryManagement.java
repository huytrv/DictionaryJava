package Dictionary;

import java.io.*;
import java.util.*;

public class DictionaryManagement{
	public static ArrayList<Dictionary> Word = new ArrayList<Dictionary>();
	public static ArrayList<String> word = new ArrayList<String>();
	private static Scanner input = new Scanner(System.in);
    public void insertFromCommandline() {
    	System.out.print("Enter the number of word: ");
		int numberOfWord = input.nextInt();
		input.nextLine();
		Dictionary[] word = new Dictionary[numberOfWord];
		String[] word_target = new String[numberOfWord], word_explain = new String[numberOfWord];
		for (int i=0; i<numberOfWord; i++) {
            word_target[i] = input.nextLine();
            word_explain[i] = input.nextLine();
            word_target[i] = word_target[i].toLowerCase();
            word_explain[i] = word_explain[i].toLowerCase();
        }
		for (int i=0; i<numberOfWord; i++) {
			word[i] = new Dictionary(word_target[i], word_explain[i]); 
			Word.add(word[i]);
		}
	}
    public void insertFromFile() {
    	String s;
		int n=0;
		try{		
			BufferedReader data = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Dictionary/Dictionaries.txt")));
			while((s = data.readLine()) != null){
				for (String token : s.split("	")) {
					word.add(token);
					n++;
				}
			}
			Dictionary[] dic = new Dictionary[n];
			for (int i=0; i<n; i+=2) {
				dic[i] = new Dictionary(word.get(i), word.get(i+1));
				Word.add(dic[i]);
			}
			data.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Unable to open file");
		}
		catch(IOException e){
			System.out.println("Error reading file");
		}
	}
    public void dictionaryExportToFile() {
		try {	
			File file = new File("C:\\Users\\hstar\\eclipse-workspace\\Dictionary\\src\\Dictionary\\Dictionaries.txt");
			file.getParentFile().mkdirs();
			BufferedWriter data = new BufferedWriter(new FileWriter(file));
			for (int i=0; i<Word.size(); i++) {
				data.write(Word.get(i).getWord_target() + "	" + Word.get(i).getWord_explain());
				data.write("\n");
			}
			data.close();
		}
		catch(IOException e){
			System.out.println("Error writing file");
		}
    }
    public void sortDictionary() {
    	Collections.sort(Word, new Comparator<Dictionary>() {
    		@Override
    		public int compare(Dictionary d1, Dictionary d2) {
    			return (d1.getWord_target().compareTo(d2.getWord_target()));
    		}
    	});
    }
    public void DictionaryLookup() {
    	System.out.print("Enter the English word (press '>>' to skip): ");
    	String wordLookup;
    	while (true) {
    		wordLookup = input.nextLine();
    		wordLookup = wordLookup.toLowerCase();
    		for (int i=0; i<Word.size(); i++) {
        		if (wordLookup.equals(Word.get(i).getWord_target())) {
        			System.out.printf(Word.get(i).getWord_explain() + "\n");
        		}
        	}
    		if(wordLookup.equals(">>")) break;
    	}
    }
    public void add() {
    	System.out.print("Add to dictionary (press '>>' to skip): ");
    	while (true) {
    		int buf = 0;
	    	String addWord_target = input.nextLine();
	    	if (addWord_target.equals(">>")) break;
	    	String addWord_explain = input.nextLine();
	    	addWord_target = addWord_target.toLowerCase();
	    	addWord_explain = addWord_explain.toLowerCase();
	    	for (int i=0; i<Word.size(); i++) {
	    		if (Word.get(i).getWord_target() == addWord_target) buf ++;
	    	} 
	    	if (buf == 0) {
    			Dictionary d = new Dictionary(addWord_target, addWord_explain);
    	    	Word.add(d);	
	    	}
    	}
	}
    public void adjust() {
    	System.out.print("Adjust the dictionary (press '>>' to skip): ");
    	while (true) {
	    	String addWord_target = input.nextLine();
	    	if (addWord_target.equals(">>")) break;
	    	String addWord_explain = input.nextLine();
	    	addWord_target = addWord_target.toLowerCase();
	    	addWord_explain = addWord_explain.toLowerCase();
	    	for (int i=0; i<Word.size(); i++) {
        		if (addWord_target.equals(Word.get(i).getWord_target())) {
        			Dictionary d = new Dictionary(addWord_target, addWord_explain);
        			Word.set(i, d);
        		}
        		else if (addWord_target.equals(Word.get(i).getWord_explain())) {
        			Dictionary d = new Dictionary(addWord_explain, addWord_target);
        			Word.set(i, d);
        		}
        	}
	  
    	}
    }
    public void del() {
    	System.out.print("Delete word (press '>>' to skip): ");
    	while (true) {
	    	String addWord_target = input.nextLine();
	    	if (addWord_target.equals(">>")) break;
	    	for (int i=0; i<Word.size(); i++) {
        		if (addWord_target.equals(Word.get(i).getWord_target())) {
        			Word.remove(i);
        		}
        	}
	  
    	}
    }
}
