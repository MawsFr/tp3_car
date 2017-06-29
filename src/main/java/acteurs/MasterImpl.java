package acteurs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MasterImpl implements Master {

	private List<Mapper> mappers = new ArrayList<>();
	private List<Reducer> reducers = new ArrayList<>();

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
			s += reducer.print() + '\n';
		}

		return s;
	}

	@Override
	public List<Reducer> getReducers() {
		return this.reducers;
	}

	@Override
	public void setReducers(List<Reducer> reducers) {
		this.reducers = reducers;
	}

}
