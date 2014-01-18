package com.liran.zero.opinion;


public class Example {
	
	public static void main(String args[])
	{
		ParserArgs parser = ParserArgs.createArgsParser(true);
		parser.registOpinion(new Opinion<String>(Parsers.ParserString,new String[] { "-xx" ,  "--longxx"}, "a" ,"设置a的值"));
		parser.parser(args);
		String help = (String) parser.getOpinion("--help").getValue();
		System.out.println(help);
	}

}
