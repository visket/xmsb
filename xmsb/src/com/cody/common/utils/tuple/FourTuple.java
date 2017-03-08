package com.cody.common.utils.tuple;


/**
 * 元组，组定义返回参数，此类提供4个返回参数，顺序传入顺序取
 * @author around
 * @param <A> 1
 * @param <B> 2
 * @param <C> 3
 * @param <D> 4
 */
public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {

	public final D d;
	
	public FourTuple(A a, B b, C c, D d) {
		super(a, b, c);
		this.d = d;
	}
	
	@Override
	public String toString() {
		return "FourTuple [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + "]";
	}
	
}
