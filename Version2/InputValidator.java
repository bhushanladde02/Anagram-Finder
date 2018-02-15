import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
	
	
	public static boolean nullCheckInput(String input){
		if(input == null || input.equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean inputValidator(String input){
		String regex = "^[a-zA-Z]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
}
