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
		Token result = null;
		if (!"".equals(text)) {
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
			result = new Token(buffer.toString(), counter == text.length());
		}
		return result;
	}

}
