import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryImpl implements Dictionary {
	private Map<String,List<String>> dictionary = null;
	private int COUNT = 0;

	public static Dictionary getObj() {
		return new DictionaryImpl();
	}

	@Override
	public int getWordcount() {
		return COUNT;
	}

	@Override
	public List<String> getAnagramsList(String input) throws DictExceptions {
		char[] inputarray = input.toCharArray();
		Arrays.sort(inputarray);
		String key = new String(inputarray);
		if(!isDictionaryLoaded()){
			if(dictionary.size()!=0){
				List<String> result = dictionary.get(key);
				if(result!=null){
					return result;
				}else{
					return new ArrayList<String>();
				}
			}else{
				System.out.println(ErrorMessages.DICT_IS_EMPTY);
				return new ArrayList<String>();
			}
		}else{
			throw new DictExceptions(ErrorMessages.DICT_NOT_LOADED);
			
		}


	}

	@Override
	public List<String> getSortedAnagramsList(String input,SortOrder order) throws DictExceptions {

		char[] inputarray = input.toCharArray();
		Arrays.sort(inputarray);
		String key = new String(inputarray);
		if(!isDictionaryLoaded()){
			if(dictionary.size()!=0){
				List<String> result = dictionary.get(key);
				if(result!=null){
					if(SortOrder.DESCENDING == order){
						Collections.sort(result, Collections.reverseOrder());
						return result;
					}else{
						Collections.sort(result);
						return result;
					}
				}else{
					return new ArrayList<String>();
				}
			}else{
				System.out.println(ErrorMessages.DICT_IS_EMPTY);
				return new ArrayList<String>();
			}
		}else{
			throw new DictExceptions(ErrorMessages.DICT_NOT_LOADED);
		}



	}


	@Override
	public boolean loadDictionary(String filepath) throws DictExceptions {
		BufferedReader br = null;
		dictionary = new HashMap<String,List<String>>();
		try {
			File fileexist = new File(filepath);
			boolean exists = fileexist.exists();

			if(!exists){
				throw new DictExceptions(ErrorMessages.FILE_NOT_FOUND);
			}

			br = new BufferedReader(new FileReader(filepath));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				COUNT++;
				char[] currentline = sCurrentLine.toCharArray();
				Arrays.sort(currentline);
				String key = new String(currentline);
				if(dictionary.containsKey(key)){
					dictionary.get(key).add(sCurrentLine);
				}else{
					ArrayList<String> list = new ArrayList<String>();
					list.add(sCurrentLine);
					dictionary.put(key, list);
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;

		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isDictionaryLoaded() {
		return dictionary == null;
	}

}
