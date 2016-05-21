package interpreter;

import objects.tokens.Token;

class Tokenizer {

	private static final char TAG_START = '<';
	private static final char TAG_END = '>';
	private static final char ESCAPE_CHAR = '\\';

	String text;
	int counter = 0;
	private boolean escapeToggle = false;

	public Tokenizer(String text) {
		this.text = text;
	}

	public Token tokenize() {
		Token result = null;
		if (!"".equals(text)) {
			text.charAt(counter);
			StringBuffer buffer = new StringBuffer();
			do {
				if (text.charAt(counter) == TAG_START) {
					if (escapeToggle == false) {
						while (counter < text.length() && text.charAt(counter) != TAG_END) {
							buffer.append(text.charAt(counter++));
						}
					}
					buffer.append(text.charAt(counter++));
					escapeToggle = false;
				} else {
					while (counter < text.length() && text.charAt(counter) != TAG_START) {
						if (text.charAt(counter) == ESCAPE_CHAR) {
							escapeToggle = !escapeToggle;
							counter++;
						}
						if (counter < text.length()) {
							buffer.append(text.charAt(counter++));
						}
					}
				}
			} while (escapeToggle == true && counter < text.length());
			result = new Token(buffer.toString(), counter == text.length());
		}
		return result;
	}
}
