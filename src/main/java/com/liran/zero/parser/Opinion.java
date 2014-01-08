package com.liran.zero.parser;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 
 * 
 * @author lixuze
 *
 * @param <T>
 */
public abstract class Opinion<T> {
	private T value; //取值
	private Set<String> opinionSet = new HashSet<String>(); //选择选项
	private String descMsg = "";

	public Opinion(String opinionName, T defaultValue) {
		this(new String[] { opinionName }, defaultValue, "");
	}

	public Opinion(String opinionName, T defaultValue, String descMsg) {
		this(new String[] { opinionName }, defaultValue, descMsg);
	}

	public Opinion(String opinions[], T defaultValue, String descMsg) {
		if (opinions.length == 0) {
			throw new IllegalArgumentException("opinion 列表不能为空");
		}
		this.value = defaultValue;
		for (String opinion : opinions) {
			opinionSet.add(opinion);
		}
		this.descMsg = descMsg;
	}

	public Opinion(String opinions[], T defaultValue) {
		this(opinions, defaultValue ,"");
	}
	

	public T getOpinion() {
		return this.value;
	}

	public String getDescription() {
		return this.descMsg;
	}
}
