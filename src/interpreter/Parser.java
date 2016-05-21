package interpreter;

import javax.swing.text.BadLocationException;

import objects.tokens.Token;
import ui.TextPanel;

class Parser {

	private static final String TAG_STARTER = "<";

	String text;
	Tokenizer tokenizer;
	Token current;
	Generator generator;
	Formatter formatter;

	public Parser(String text) {
		tokenizer = new Tokenizer(text);
		formatter = new Formatter();
	}

	private void getNextToken() {
		current = tokenizer.tokenize();
	}

	public void parse() throws BadLocationException {
		do {
			getNextToken();
			if (current != null) {
				if (current.getValue().startsWith(TAG_STARTER)) {
					formatter.handleFormatting(current);
				} else {
					formatter.append(current);
				}
			}
		} while (current != null && !current.isLast());
	}

	// TODO: support possibility of using HTML
	public TextPanel getOutput() {
		return formatter.getOutput();
	}

}
