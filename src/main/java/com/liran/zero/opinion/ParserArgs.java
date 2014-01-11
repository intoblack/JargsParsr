package com.liran.zero.opinion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.liran.zero.opinion.Opinion.Parsers;

public class ParserArgs {
	private Map<String, Opinion<?>> opinionValueMap = new HashMap<String, Opinion<?>>();
	private int minArgsNum = Integer.MAX_VALUE;
	private boolean isCreateHelp = true;

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
		minArgsNum = num;
	}

	public void parser(String... args) {
		createHelp();
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

	private ParserArgs(boolean isCreateHelp) {
		this.isCreateHelp = isCreateHelp;
	}

	public static ParserArgs createArgsParser(boolean isCreateHelp) {
		return new ParserArgs(isCreateHelp);
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

	private <T> void createHelp() {
		StringBuilder helpMsg = new StringBuilder();
		if (isCreateHelp
				&& !(opinionValueMap.containsKey("--help") || opinionValueMap
						.containsKey("-h"))) {
			Set<String> createdHelpName = new HashSet<String>();
			for (Entry<String, Opinion<?>> opinion : opinionValueMap.entrySet()) {
				boolean isFirsr = true;
				if (createdHelpName.containsAll(opinion.getValue().getOpinions())) {
					continue;
				}
				for (String opinionName : opinion.getValue().getOpinions()) {

					createdHelpName.add(opinionName);
					if (isFirsr) {
						helpMsg.append("\t" + opinionName);
						isFirsr = false;
					} else {
						helpMsg.append(" , " + opinionName);
					}
				}
				helpMsg.append("\t:" + opinion.getValue().getDescription()
						+ "\n");
			}
			this.registOpinion(new Opinion<String>(Parsers.String,
					new String[] { "--help", "-h" }, helpMsg.toString(), ""));
		}
	}

}
