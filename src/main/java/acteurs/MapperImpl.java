package acteurs;

import java.util.ArrayList;
import java.util.List;

public class MapperImpl implements Mapper {
	private List<Reducer> reducers = new ArrayList<>();

	@Override
	public void countWords(String line) {
		final String[] words = line.split(" ");
		for (String word : words) {
			reducers.get(Math.abs(word.hashCode()) % reducers.size()).saveWord(word);
		}

	}

	@Override
	public void setReducers(List<Reducer> reducers) {
		this.reducers = reducers;
	}

}
