
public class DictExceptions extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String exception;
	
	DictExceptions(String exception) {
		this.exception=exception;
	}
	
	public String toString(){
		return ("Exception occure : "+exception) ;
	}
}