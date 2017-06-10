package sample.hello;

import java.io.Serializable;

public class Word implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String word;

	public Word(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public int hashCode() {
		return this.word.hashCode();
	}

}
