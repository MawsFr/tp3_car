package sample.hello;

import java.util.List;

public class MapperImpl implements Mapper {
	protected List<Reducer> reducers;

	@Override
	public void countWords(String line) {
		final String[] words = line.split(" ");
		for(String word : words) {
			ReducerProvider.partition(word, reducers).saveWord(word);
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
