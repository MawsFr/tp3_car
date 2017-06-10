package sample.hello;

import java.io.IOException;
import java.util.List;

public interface Master {

	void countWords(String name) throws IOException;

	void setMappers(List<Mapper> mappers);

	List<Mapper> getMappers();

	void setReducers(List<Reducer> reducers);

	String print();

}
