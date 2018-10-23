package Dictionary;

public class DictionaryCommandLineImplement extends DictionaryManagement{
	DictionaryManagement d = new DictionaryManagement();
	DictionaryCommandLine d2 = new DictionaryCommandLine();
	public void dictionaryBasic() {
		d.insertFromCommandline();
		d2.showAllWorlds();
		Word.clear();
	}
	public void dictionaryAdvanced() {
		d.insertFromFile();
		d2.showAllWorlds();
		d.DictionaryLookup();
	}
	public void dictionaryEdit() {
		d.add();
		d.adjust();
		d.del();
		d2.dictionarySearcher();
		d2.showAllWorlds();
		d.dictionaryExportToFile();
	}
	public static void main(String[] args){
		DictionaryCommandLineImplement dic = new DictionaryCommandLineImplement();

		dic.dictionaryBasic();
		dic.dictionaryAdvanced();
		dic.dictionaryEdit(); 
	}
}
