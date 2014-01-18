package com.liran.zero.opinion;

public class Parsers {
	public static interface Parser {
		public Object parser(String value);
	}

	public static class ParserInt implements Parser {

		public Object parser(String value) {
			return Integer.parseInt(value);
		}

	}

	public static class ParserString implements Parser {

		public Object parser(String value) {
			return value;
		}

	}

	public static class ParserLong implements Parser {

		public Object parser(String value) {
			return Long.parseLong(value);
		}

	}

	public static class ParserBoolean implements Parser {

		public Object parser(String value) {
			return Boolean.parseBoolean(value);
		}

	}

	public static class ParserDouble implements Parser {

		public Object parser(String value) {
			return Double.parseDouble(value);
		}
	}

}
