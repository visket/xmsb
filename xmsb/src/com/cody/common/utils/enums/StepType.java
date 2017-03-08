package com.cody.common.utils.enums;


/**
 * 步骤类型
 * @author around
 * @date 2016-12-28
 */
public enum StepType {

	ROLE,USER,JOB,ORGANIZATION;
	
	private StepType() {
		// TODO Auto-generated constructor stub

	}
	
	StepType(StepType step) {
		
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	
	public static void main(String[] args) {
		System.out.println(StepType.JOB.toString());
	}
	
	
}
