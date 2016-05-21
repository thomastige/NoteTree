package interpreter;

import helpers.UIHelper;
import helpers.property.FormatterTagsPropertiesHelper;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import objects.tokens.Token;
import ui.TextPanel;

public class Formatter {

	public static final String BOLD_TAG = "bold";
	public static final String ITALIC_TAG = "italic";
	public static final String UNDERLINE_TAG = "underline";
	public static final String STRIKETHROUGH_TAG = "strike";

	private TextPanel output;
	private StyledDocument doc;

	private SimpleAttributeSet attributeSet;

	public Formatter() {
		output = new TextPanel("", UIHelper.getTextArea().getNodeOnDisplay(), false);
		doc = output.getStyledDocument();
		attributeSet = new SimpleAttributeSet();
	}

	public void append(Token token) throws BadLocationException {
		doc.insertString(doc.getLength(), token.getValue(), attributeSet);
	}

	public void handleFormatting(Token token) {
		String formattingToken = token.getValue();
		applyFormatting(formattingToken);
	}

	public TextPanel getOutput() {
		output.setDocument(doc);
		return output;
	}

	private void applyFormatting(String tokenValue) {
		setFormattingValue(tokenValue.substring(1, tokenValue.length() - 1));
	}

	private void setFormattingValue(String formattingCode) {
		if (formattingCode.startsWith(FormatterTagsPropertiesHelper.getClosingTagDelimiter())) {
			resolveFormattingRemoval(formattingCode.substring(1));
		} else {
			resolveFormattingAddition(formattingCode);
		}
	}

	private void resolveFormattingAddition(String code) {
		if (FormatterTagsPropertiesHelper.hasParameters(code)) {
			
		} else {
			if (FormatterTagsPropertiesHelper.getPropertyFromCache(BOLD_TAG).equals(code)) {
				StyleConstants.setBold(attributeSet, true);
			}
			if (FormatterTagsPropertiesHelper.getPropertyFromCache(ITALIC_TAG).equals(code)) {
				StyleConstants.setItalic(attributeSet, true);
			}
			if (FormatterTagsPropertiesHelper.getPropertyFromCache(UNDERLINE_TAG).equals(code)) {
				StyleConstants.setUnderline(attributeSet, true);
			}
			if (FormatterTagsPropertiesHelper.getPropertyFromCache(STRIKETHROUGH_TAG).equals(code)) {
				StyleConstants.setStrikeThrough(attributeSet, true);
			}
		}

	}

	private void resolveFormattingRemoval(String code) {
		if (FormatterTagsPropertiesHelper.getPropertyFromCache(BOLD_TAG).equals(code)) {
			StyleConstants.setBold(attributeSet, false);
		}
		if (FormatterTagsPropertiesHelper.getPropertyFromCache(ITALIC_TAG).equals(code)) {
			StyleConstants.setItalic(attributeSet, false);
		}
		if (FormatterTagsPropertiesHelper.getPropertyFromCache(UNDERLINE_TAG).equals(code)) {
			StyleConstants.setUnderline(attributeSet, false);
		}
		if (FormatterTagsPropertiesHelper.getPropertyFromCache(STRIKETHROUGH_TAG).equals(code)) {
			StyleConstants.setStrikeThrough(attributeSet, false);
		}
	}
}
