package acteurs;

import java.util.List;

public class MapperImpl implements Mapper {
	private List<Reducer> reducers;

	@Override
	public void countWords(String line) {
		final String[] words = line.split(" ");
		for(String word : words) {
			reducers.get(Math.abs(word.hashCode()) % reducers.size()).saveWord(word);
		}

	}

	@Override
	public List<Reducer> getReducers() {
		return reducers;
	}

	@Override
	public void setReducers(List<Reducer> reducers) {
		this.reducers = reducers;
	}

	@Override
	public String print() {
		String s = "";
		for (Reducer reducer : reducers) {
			s += reducer.print();
		}
		return s;
	}

}
