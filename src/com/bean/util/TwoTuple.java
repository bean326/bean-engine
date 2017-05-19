package com.bean.util;

/**
 * 二元元祖
 * @author FXW
 * 2016年6月24日
 */
public class TwoTuple<A,B> {

	private final A first;
	private final B second;
	
	/**
	 * @param first
	 * @param second
	 */
	public TwoTuple(A first, B second) {
		super();
		this.first = first;
		this.second = second;
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}
	
	
	
	
}
