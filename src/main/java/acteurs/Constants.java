package acteurs;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Constants {

	// Question 2
	public static final String USER = "/user/";
	public static final String MAPPER_SYSTEM = "mapperssystem";
	public static final String MASTER_REDUCERS_SYSTEM = "masterreducerssystem";
	public static String ADDRESS_MAPPERS_SYSTEM;
	public static final String MASTER_NAME = "master";
	public static final String MAPPER1_NAME = "mapper1";
	public static String MAPPER1_ADRESS;
	public static final String MAPPER2_NAME = "mapper2";
	public static String MAPPER2_ADRESS;
	public static final String MAPPER3_NAME = "mapper3";
	public static String MAPPER3_ADRESS;
	public static final String REDUCER1_NAME = "reducer1";
	public static final String REDUCER2_NAME = "reducer2";
	public static final String CONFIG_MAPPERS = "mappers";
	public static final String CONFIG_MASTER_REDUCERS = "masterreducers";

	public static void init() throws UnsupportedEncodingException {
		ADDRESS_MAPPERS_SYSTEM = "akka.tcp://" + MAPPER_SYSTEM + "@127.0.0.1:2552";
		MAPPER1_ADRESS = URLEncoder.encode(ADDRESS_MAPPERS_SYSTEM + USER + MAPPER1_NAME, "UTF-8");
		MAPPER2_ADRESS = URLEncoder.encode(ADDRESS_MAPPERS_SYSTEM + USER + MAPPER2_NAME, "UTF-8");
		MAPPER3_ADRESS = URLEncoder.encode(ADDRESS_MAPPERS_SYSTEM + USER + MAPPER3_NAME, "UTF-8");
	}

}
