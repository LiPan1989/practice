package com.lee.test.complier;

public class Compiler {

	public static void main(String[] args) {
		Lexer lexer = new Lexer();
		BasicParser basicParser = new BasicParser(lexer);
		basicParser.statements();
	}
}
