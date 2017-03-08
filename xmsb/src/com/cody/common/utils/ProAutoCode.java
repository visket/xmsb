package com.cody.common.utils;

import java.util.Date;
import javax.annotation.Resource;

import com.cody.entity.equip.EquipBase;
import com.cody.entity.hiddanger.HiddangerDeclare;
import com.cody.entity.science.ScienceBase;
import com.cody.service.equip.EquipService;

public class ProAutoCode {

	@Resource
	EquipService eq;
	
	private String code;
	
	//获取生成的项目编码  前台传递ProTypeEnum.KJXM
	public String getCode(String proType,int count){
		int i= 1000+count;
		code = proType+DateFormatUtil.dateToString(new Date(),"yyyyMMdd")+i;//这里的1001是通过调后台查询数据库得到的
		return code;				
	}
	
	//查询最后一条数据，截取数据中的后四位加1
	public String getNewCode(String proType,Object obj,String objName,int index){
		int a=0;
		if("ScienceBase".equals(objName)){
			ScienceBase sb = (ScienceBase)obj;
			if(sb==null)
			 return proType+DateFormatUtil.dateToString(new Date(),"yyyyMMdd")+1000;
			a=cutCode(sb.getApplycode(),index)+1;
		}else if("EquipBase".equals(objName)){
			EquipBase eb = (EquipBase)obj;
			if(eb==null)
			 return proType+DateFormatUtil.dateToString(new Date(),"yyyyMMdd")+1000;
			a=cutCode(eb.getApplycode(),index)+1;
		}else if("HiddangerDeclare".equals(objName)){
			HiddangerDeclare hd = (HiddangerDeclare)obj;
			if(hd==null)
			 return proType+DateFormatUtil.dateToString(new Date(),"yyyyMMdd")+1000;
			a=cutCode(hd.getApplycode(),index)+1;
		}
		code = proType+DateFormatUtil.dateToString(new Date(),"yyyyMMdd")+a;
		return code;	
	}
	
	public int cutCode(String code,int index){	
		code = code.substring(index,code.length());
		int b = Integer.valueOf(code);
		return b;
	}
	
}
