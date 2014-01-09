package com.liran.zero.opinion;

import com.liran.zero.opinion.Opinion.Parsers;

public class Example {
	
	public static void main(String args[])
	{
		ParserArgs.registOpinion(new Opinion<String>(Parsers.String, "xx", "a"));
		ParserArgs.parser(args);
		String aa = (String) ParserArgs.getOpinion("xx").getValue();
		System.out.println(aa);
	}

}
