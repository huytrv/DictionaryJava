package Dictionary;
import java.util.*;
public class DictionaryCommandLine extends DictionaryManagement{
	private static Scanner input = new Scanner(System.in);
	public void showAllWorlds() {
		DictionaryManagement dic = new DictionaryManagement();
		dic.sortDictionary();
		System.out.printf("%-8s%-20s%s\n\n", "No", "| English", "| Vietnamese");
		for (int i=0; i<Word.size(); i++) {
			System.out.printf("%-8s| %-18s| %s\n", i, Word.get(i).getWord_target(), Word.get(i).getWord_explain());	
		}
		System.out.print("\n\n");
	}	
	public void dictionarySearcher() {
		System.out.print("Enter word you want to search (press '>>' to skip): ");
		int dem = 0;
		while(true) {
			String word = input.nextLine();
			word = word.toLowerCase();
			if (word.equals(">>")) break;
			for (int j=0; j<Word.size(); j++) {
				for (int i=0; i<word.length(); i++) {
					if(word.charAt(i) == Word.get(j).getWord_target().charAt(i)) {
						dem++;
					}	
					if (dem == word.length()) {
						System.out.print(Word.get(j).getWord_target() + ", ");
					}
				}
				dem = 0;
			}
			System.out.print("\n");
		}
	}
}
