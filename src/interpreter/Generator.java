package interpreter;

import javax.swing.text.BadLocationException;

import ui.TextPanel;

public class Generator {
	
	String text;
	
	public Generator(String text){
	this.text = text;
	}
	
	public TextPanel generate() throws BadLocationException{
		Parser parser = new Parser(text);
		parser.parse();
		return parser.getOutput();
	}

}
