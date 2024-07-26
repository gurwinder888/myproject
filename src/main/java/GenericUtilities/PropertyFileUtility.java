package GenericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyFileUtility {
	

	public String readDataFromCommonDataPropertyFile(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("src/test/resources/CommonData.properties");
	    Properties p = new Properties();
	    p.load(fis);
	    String value = p.getProperty(key);
	    return value;
	    
	}
	public String readDataFromPropertyProgressNotes(String key) throws IOException {
		FileInputStream fis = new FileInputStream("src/test/resources/enterProgressNotes.properties");
	    Properties p = new Properties();
	    p.load(fis);
	    String value = p.getProperty(key);
	    return value;
	}

}
