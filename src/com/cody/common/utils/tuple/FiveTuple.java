package com.cody.common.utils.tuple;


/**
 * 元组，组定义返回参数，此类提供5个返回参数，顺序传入顺序取
 * @author around
 * @param <A> 1
 * @param <B> 2
 * @param <C> 3
 * @param <D> 4
 * @param <E> 5
 */
public class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {

	public final E e;
	
	public FiveTuple(A a, B b, C c, D d, E e) {
		super(a, b, c, d);
		this.e = e;
	}
	
	@Override
	public String toString() {
		return "FiveTuple [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", e=" + e + "]";
	}
	
}
