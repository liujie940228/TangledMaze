package me.gorgeousone.tangledmaze.command.framework.argument;

public enum ArgType {

	INTEGER("integer"),
	DECIMAL("number"),
	STRING("string"),
	BOOLEAN("boolean");

	private String simpleName;
	
	ArgType(String simpleName) {
		this.simpleName = simpleName;
	}
	
	public String simpleName() {
		return simpleName;
	}
}