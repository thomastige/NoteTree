package interpreter;

import objects.tokens.Token;

class Tokenizer {

	private static final char TAG_START = '<';
	private static final char TAG_END = '>';

	String text;
	int counter = 0;

	public Tokenizer(String text) {
		this.text = text;
	}

	public Token tokenize() {
		text.charAt(counter);
		StringBuffer buffer = new StringBuffer();
		if (text.charAt(counter) == TAG_START) {
			while (counter < text.length() && text.charAt(counter) != TAG_END) {
				buffer.append(text.charAt(counter++));
			}
			buffer.append(text.charAt(counter++));
		} else {
			while (counter < text.length() && text.charAt(counter) != TAG_START) {
				buffer.append(text.charAt(counter++));
			}
		}
		return new Token(buffer.toString(), counter == text.length());
	}

}
