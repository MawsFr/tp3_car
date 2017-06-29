package acteurs;

import java.util.List;

public interface Mapper {
	void countWords(String line);
	void setReducers(List<Reducer> reducers);

}
