/*
Name : Bhushan Ladde
Purpose : Main class - execute method 
*/
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnagramFinder {

	public static void execute(String[] args){
		
		if(args.length < 1){
			System.out.println(ErrorMessages.PROVIDE_FILE_INPUT);
			return;
		}

		if(args.length > 1){
			System.out.println(ErrorMessages.ONLY_ONE_PARAMETER_AS_AN_INPUT);
			return;
		}
		
		Dictionary dictionary = DictionaryImpl.getObj();
		
		try{
			
			Long start = Timer.getCurrentTimeInMiliS();
			boolean isLoaded = dictionary.loadDictionary(System.getProperty("user.dir") +"//"+ args[0]);
			Long end = Timer.getCurrentTimeInMiliS();
			
			//System.out.println("total words :" + dictionary.getWordcount());
			
			System.out.println("Welcome to the Anagram Finder");
			System.out.println("-----------------------------");
			System.out.println("Dictionary loaded in "+ (end-start) +" ms");
			
			if(isLoaded){
				
				Scanner reader = new Scanner(System.in);  // Reading from System.in
				
				while(true){
					
					System.out.print("\nAnagramFinder> ");

					String input = reader.next();
					
					//Input Validation code
					if(InputValidator.nullCheckInput(input)){
						continue;
					}
					if(input.equals("exit")){
						break;
					}

					if(!InputValidator.inputValidator(input)){
						System.out.println(ErrorMessages.SPECIAL_CHAR_NOT_ALLOW);
						continue;
					}

					char[] temp = input.toCharArray();
					Arrays.sort(temp);
					input = new String(temp);

					Long starta = Timer.getCurrentTimeInMiliS();
					List<String> setresult = dictionary.getAnagramsList(input);
					//To Get Sorted List - Acending or Decending
					//List<String> setresult = dictionary.getSortedAnagramsList(input,SortOrder.DESCENDING);
					Long enda = Timer.getCurrentTimeInMiliS();

					if(setresult.size() != 0){
						System.out.println(setresult.size()+" Anagrams found for stop in  "+ (enda-starta) +" ms");
						int  i = 0;
						for(String str : setresult){
							System.out.print(str);
							if(i !=setresult.size()-1){
								System.out.print(",");
							}
							i++;
						}
					}else{
						System.out.println("No anagrams found for accept in "+ (enda-starta) +" ms");
					}
					
					System.out.println();
				}

				reader.close();
				
			}else{
				System.out.println(ErrorMessages.DICT_NOT_LOADED);
			}
			
		}catch(DictExceptions e){
			System.out.println(e);
		}
		System.out.println("Bye!!!!");
		System.exit(0);
		System.gc();
	}

	public static void main(String[] args) {
		execute(args);
	}
}
