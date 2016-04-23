package objects.tokens;

public class Token {

	private String value;
	private boolean isLast;
	
	public Token(String content){
		this(content, false);
	}
	public Token(String content, boolean isLast){
		this.value = content;
		this.isLast = isLast;
	}

	public String getValue(){
		return value;
	}
	
	public boolean isLast(){
		return isLast;
	}

	
	
}
