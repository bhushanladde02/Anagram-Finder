import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnagramFinderV2 {


	public static void execute(String[] args){


		if(args.length < 1){
			System.out.println("Please provide the dictionary.txt file as an input");
			return;
		}

		if(args.length > 1){
			System.out.println("Only one dictionary.txt file gets accepted as an input");
			return;
		}

		Dictionary dictionary = DictionaryImpl.getObj();

		String regex = "^[a-zA-Z]+$";
		Pattern pattern = Pattern.compile(regex);

		System.out.println("Welcome to the Anagram Finder");
		System.out.println("-----------------------------");
		try{
			Long start = System.currentTimeMillis() % 1000;

			boolean isLoaded = dictionary.loadDictionary(System.getProperty("user.dir") +"\\"+ args[0]);
			if(isLoaded){
				Long end = System.currentTimeMillis() % 1000;
				System.out.println("Dictionary loaded in "+ (end-start) +" ms");
				Scanner reader = new Scanner(System.in);  // Reading from System.in


				while(true){

					System.out.print("\nAnagramFinder> ");

					String input = reader.next();

					if(input == null || input.equals("")){
						continue;
					}
					if(input.equals("exit")){
						break;
					}

					Matcher matcher = pattern.matcher(input);

					if(!matcher.matches()){
						System.out.println("Special characters and Integers are not allowed");
						continue;
					}

					char[] temp = input.toCharArray();
					Arrays.sort(temp);
					input = new String(temp);

					Long starta = System.currentTimeMillis() % 1000;

					List<String> setresult = dictionary.getAnagramsList(input);
					Long enda = System.currentTimeMillis() % 1000;
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
				}

				reader.close();
			}else{
				System.out.println("Dictionary is not gets loaded");
			}

		}catch(DictExceptions e){
			System.out.println(e);
		}

	}

	public static void main(String[] args) {


	}
}
