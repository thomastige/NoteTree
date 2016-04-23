package enums;

public enum Tags {
	BOLD("bold");
	private final String value;

	private Tags(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getValue();
	}
}