package com.liran.zero.opinion;

import com.liran.zero.opinion.Opinion.Parsers;

public class Example {
	
	public static void main(String args[])
	{
		ParserArgs parser = ParserArgs.createArgsParser(true);
		parser.registOpinion(new Opinion<String>(Parsers.String,new String[] { "-xx" ,  "--longxx"}, "a" ,"设置a的值"));
		parser.parser(args);
		String help = (String) parser.getOpinion("--help").getValue();
		System.out.println(help);
	}

}
