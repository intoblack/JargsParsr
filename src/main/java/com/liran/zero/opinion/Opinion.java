package com.liran.zero.opinion;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.liran.zero.opinion.Opinion.Parsers;

/**
 * 
 * 
 * 
 * @author lixuze
 * 
 * @param <T>
 */
public class Opinion<T> {

	public static enum Parsers {

		INT {
			@Override
			public Object parser(String value) {
				return Integer.parseInt(value);
			}
		},
		String {
			@Override
			public Object parser(String value) {
				return value;
			}
		},
		Long {
			@Override
			public Object parser(String value) {
				return Long.parser(value);
			}
		},
		Boolean {
			@Override
			public Object parser(String value) {
				return Boolean.parser(value);
			}
		},
		Double {
			@Override
			public Object parser(String value) {
				return Double.parser(value);
			}
		};

		public abstract Object parser(String value);

	}

	private T value; // 取值
	private TreeSet<String> opinionSet = new TreeSet<String>(
			new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.length() == o2.length() ? 0
							: o1.length() > o2.length() ? 1 : -1;
				}
			}); // 选择选项
	private String descMsg = "";
	private Parsers parser = null;

	public Opinion(Parsers parser, String opinionName, T defaultValue) {
		this(parser, new String[] { opinionName }, defaultValue, "");
	}

	public Opinion(Parsers parser, String opinionName, T defaultValue,
			String descMsg) {
		this(parser, new String[] { opinionName }, defaultValue, descMsg);
	}

	public Opinion(Parsers parser, String opinions[], T defaultValue,
			String descMsg) {
		this.parser = parser;
		if (opinions.length == 0) {
			throw new IllegalArgumentException("opinion 列表不能为空");
		}
		this.value = defaultValue;
		for (String opinion : opinions) {
			opinionSet.add(opinion);
		}
		this.descMsg = descMsg;
	}

	public Opinion(Parsers parser, String opinions[], T defaultValue) {
		this(parser, opinions, defaultValue, "");
	}

	public TreeSet<String> getOpinions() {
		return new TreeSet<String>(opinionSet);
	}

	public boolean assertExcute(String key) {
		for (String opinionName : opinionSet) {
			if (key.equals(opinionName)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public void setValue(Object value) {
		this.value = (T) value;
	}

	public T getValue() {
		return this.value;
	}

	public Object parserValue(String value) {
		return this.parser.parser(value);
	}

	public String getDescription() {
		return this.descMsg;
	}

}
