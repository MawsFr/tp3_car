package acteurs;

import java.util.List;

public class ReducerProvider {
	public static Reducer partition(final String word, final List<Reducer> reducers) {
//		System.out.println("word" + word.hashCode());
//		System.out.println("reducer" + reducers.size());
		return reducers.get(Math.abs(word.hashCode()) % reducers.size());
	}

}
