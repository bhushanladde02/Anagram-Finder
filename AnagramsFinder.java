/*
Name : Bhushan Ladde
Code : Anagrams Finder
Version : 1
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.*;

public class AnagramsFinder {

	//Method to get all anagrams
	public Set<String> anagramsFinder(String input, Set<String> setDict){
		Set<String> result = new HashSet<String>();
		return allAnagrams("",input,result,setDict);
	}

	public Set<String> allAnagrams(String prefix,String suffix, Set<String> result, Set<String> setDict){
		if(suffix.length()== 0){
			if(setDict.contains(prefix))
				result.add(prefix);
		}

		for(int i = 0; i < suffix.length(); i++){
			allAnagrams(prefix + suffix.charAt(i), suffix.substring(0,i) + suffix.substring(i+1,suffix.length()),result,setDict);
		}
		return result;
	}

	//Method to load the dictionary into hashset
	public Set<String> loadDict(String filePath){
		BufferedReader br = null;
		Set<String> mapDict = new HashSet<String>();
		try {
			br = new BufferedReader(new FileReader(filePath));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				mapDict.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return mapDict;
	}
	
	public void execute(String[] args){
		
		if(args.length < 1){
			System.out.println("Please provide the dictionary.txt file as an input");
			return;
		}

		if(args.length > 1){
			System.out.println("Only one dictionary.txt file gets accepted as an input");
			return;
		}
		
		String regex = "^[a-zA-Z]+$";
		Pattern pattern = Pattern.compile(regex);
 
		System.out.println("Welcome to the Anagram Finder");
		System.out.println("-----------------------------");
		AnagramsFinder anagrams = new AnagramsFinder();
		Long start = System.currentTimeMillis() % 1000;
		Set<String> setDict = anagrams.loadDict(System.getProperty("user.dir") +"//"+ args[0]);
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
			
			Long starta = System.currentTimeMillis() % 1000;
			Set<String> setresult = anagrams.anagramsFinder(input, setDict);
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
			System.out.println();
		}
		reader.close();
	}
	
	public static void main(String[] args) {
		AnagramsFinder anagrams = new AnagramsFinder();
		anagrams.execute(args);
	}

}
