package sample.hello;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MasterImpl implements Master {

	protected List<Mapper> mappers;
	protected List<Reducer> reducers;

	@Override
	public void countWords(String name) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(name))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				mappers.get(i % mappers.size()).countWords(line);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	@Override
	public List<Mapper> getMappers() {
		return mappers;
	}

	@Override
	public void setMappers(List<Mapper> mappers) {
		this.mappers = mappers;
	}

	@Override
	public String print() {
		String s = "";
		for (Reducer reducer : this.reducers) {
			s += reducer.print();
		}
		return s;
	}

	@Override
	public void setReducers(List<Reducer> reducers) {
		this.reducers = reducers;
	}

}
