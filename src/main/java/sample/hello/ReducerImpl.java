package sample.hello;

import java.util.HashMap;
import java.util.Map;

public class ReducerImpl implements Reducer {
	protected Map<String, Integer> wordsCount;

	public ReducerImpl() {
		this.wordsCount = new HashMap<>();
	}

	@Override
	public void saveWord(String word) {
		if (!this.wordsCount.containsKey(word)) {
			this.wordsCount.put(word, 0);
		}
		this.wordsCount.put(word, this.wordsCount.get(word) + 1);
	}

	@Override
	public String print() {
		String s = "";
		for (Map.Entry<String, Integer> entry : this.wordsCount.entrySet()) {
			s += entry.getKey() + " : " + entry.getValue();
		}
		return s;
	}

}
