JargsParser java main参数解析
==============================
+ 工作以来 发现java 写参数配置的时候总是需要写自己的解析，所以提供一个工具完成这样的步骤

:::java 
       

       public static void main(String args[])
	  {
		ParserArgs parser = ParserArgs.createArgsParser(true); //createArgsParser(boolean) 是否自动生成帮助  
		parser.registOpinion(new Opinion<String>(Parsers.String,new String[] { "-xx" ,  "--longxx"}, "a" ,"设置a的值")); //添加选项值 ， 默认值 , 描述信息 用于生成帮助使用
		parser.parser(args); // 解析args 
		String help = (String) parser.getOpinion("--help").getValue(); //获得参数信息
		System.out.println(help); //处理参数信息 
	 }

待完善
-----------------------
+ 待完成数据解析类型的扩展  
+ 待完成数据不需要强制转换就可以获得值  
+ 待完成数据大小判断  


