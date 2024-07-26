package GenericUtilities;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class consists of generic methods related to Java
 * @author Chaitra M
 *
 */
public class JavaUtility {
	
	/**
	 * This method will generate a random number for every run and return it to the caller
	 * @return
	 */
	public int getRandomNumber()
	{
		Random ran = new Random();
		int r = ran.nextInt(10000);
		return r;
	}
	
	
	public String getRandomString(int length) {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    SecureRandom random = new SecureRandom();
	    StringBuilder stringBuilder = new StringBuilder(length);

	    for (int i = 0; i < length; i++) {
	        int randomIndex = random.nextInt(characters.length());
	        stringBuilder.append(characters.charAt(randomIndex));
	    }

	    return stringBuilder.toString();
	}
	
	/**
	 * This method will capture the current system date in required format
	 * @return
	 */
	public String getSystemDate()
	{
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String date = formatter.format(d);
		return date;
	}

	
	
	
	
	
	
	
	
	
}
