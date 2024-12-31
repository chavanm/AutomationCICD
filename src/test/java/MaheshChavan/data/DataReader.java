package MaheshChavan.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;  // this dependency added

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//here you can write n number of utilities to make scan your JSON or retrieve the value based on your requirement
public class DataReader
{
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
    {
    	
    	//in java there in one method which will read file that means if you just pass JSON file.
    	//it will scan the entire content of your JSON and convert that into one string variable .
    	String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8); //also write the encoding format this(StandardCharsets.UTF_8)  //read json to string"
    	
    	//string to HashMap - new dependency using jackson Databind for converting
    	ObjectMapper mapper = new ObjectMapper();
    	List<HashMap<String,String>> data =	mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>()  //first read the string and convert to HashMap. //all hashmap put in the List
    	{});
    	
    	return data;
    	
    }
}
