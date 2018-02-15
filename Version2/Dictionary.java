import java.util.List;

public interface Dictionary {
	int getWordcount();
	boolean isDictionaryLoaded();
	List<String> getAnagramsList(String input) throws DictExceptions;
	List<String> getSortedAnagramsList(String input,SortOrder order) throws DictExceptions;
	boolean loadDictionary(String filepath) throws DictExceptions;
}
