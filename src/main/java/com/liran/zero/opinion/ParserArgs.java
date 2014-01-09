package com.liran.zero.opinion;

import java.util.HashMap;
import java.util.Map;

public class ParserArgs {
	private Map<String, Opinion<?>> opinionValueMap = new HashMap<String, Opinion<?>>();
	private int minArgsNum = Integer.MAX_VALUE;

	public void registOpinion(Opinion<?> opinion) {
		for (String opinionName : opinion.getOpinions()) {
			opinionValueMap.put(opinionName, opinion);
		}
	}

	/**
	 * 设置最小参数个数
	 * 
	 * @param num
	 */
	public void setMinArgsNum(int num) {
		if (num < 0) {
			throw new IllegalArgumentException("最小参数个数不能小于0");
		}
		this.minArgsNum = num;
	}

	public void parser(String... args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].contains("=")) {
				String argName[] = args[i].split("=");
				setOpinionMap(argName[0], argName[1]);
			} else {
				if (setOpinionMap(args[i], args[i + 1])) {
					i = i + 1;
				}
			}

		}
	}

	private boolean setOpinionMap(String key, String argValue) {
		if (opinionValueMap.containsKey(key)) {
			Object value = opinionValueMap.get(key).parserValue(argValue);
			for (String opinionName : opinionValueMap.get(key).getOpinions()) {
				opinionValueMap.get(opinionName).setValue(value);
			}
			return true;
		}
		return false;
	}

	public Opinion<?> getOpinion(String key) {
		if (opinionValueMap.containsKey(key)) {
			return opinionValueMap.get(key);
		}
		throw new IllegalArgumentException("没有这个参数设置:" + key);
	}

}
