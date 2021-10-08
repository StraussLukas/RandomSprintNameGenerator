package stage1;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;




public class RandomSprintNameGenerator{

	public RandomSprintNameGenerator() {
		
	}

	public List<SprintName> getRandomSprintNames( String character, int count) throws IOException{
		StringBuilder urlString =  new StringBuilder("https://random-word-form.herokuapp.com/random/noun/");
		urlString.append("" + character.toUpperCase() + "?count=" + count);
		URL url = new URL( urlString.toString() );
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod( "GET" );
		con.setRequestProperty( "Content-Type", "application/JSON" );

		JsonReader reader = Json.createReader(con.getInputStream());
		JsonArray arr = reader.readArray();
		List<SprintName> sprintNames = new ArrayList<SprintName>();
		for (int i = 0; i < arr.size(); i++) {
			SprintName name = new SprintName( arr.getString(i), i);
			sprintNames.add(name);
		}
		return sprintNames;
	}
}
