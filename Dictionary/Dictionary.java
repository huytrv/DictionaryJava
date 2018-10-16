package Dictionary;

import java.util.ArrayList;

public class Dictionary extends Word{
	public Dictionary(String setWord_target, String setWord_explain) {
		super(setWord_target, setWord_explain);
	}
	ArrayList<Dictionary> Word = new ArrayList<Dictionary>();
}
