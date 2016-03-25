package Lab2;

public class RegularDefinition {

	String lexicalCategory;
	String lexeme;

	public RegularDefinition(String lexicalCategory, String lexeme) {
		super();
		this.lexicalCategory = lexicalCategory;
		this.lexeme = lexeme;
	}

	public String getLexicalCategory() {
		return lexicalCategory;
	}

	public void setLexicalCategory(String lexicalCategory) {
		this.lexicalCategory = lexicalCategory;
	}

	public String getLexeme() {
		return lexeme;
	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

}
