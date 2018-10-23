package Dictionary;

public class Word {
	protected String word_target, word_explain;
	public Word(String setWord_target, String setWord_explain) {
		this.word_target = setWord_target;
		this.word_explain = setWord_explain;
	}
	public String getWord_target() {
		return word_target;
	}
	public void setWord_target(String word_target) {
		this.word_target = word_target;
	}
	public String getWord_explain() {
		return word_explain;
	}
	public void setWord_explain(String word_explain) {
		this.word_explain = word_explain;
	}
}
