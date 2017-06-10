package sample.hello;

import java.util.List;

public interface Mapper {
	void countWords(String line);

	void setReducers(List<Reducer> reducers);

	List<Reducer> getReducers();

	String print();

}
