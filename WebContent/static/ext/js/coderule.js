/**
 * 简单编码规则(区域部分) 
 */
function coding_rule(data,_find){
//debugger;	
	if(_find!=null){
   	 var _count = _find.length;   
   	 //遇到最下层区域不能继续添加子集	 
        if(_count>2){
     	   alert("所选择区域已属最下层,无法添加子区域"); 
     	   return "wftj"; //无法提交
        }       
        var j=(_count+2)*1000+data.childrenCodeCount+1;     
        areaCode=data.code+"_"+j;      
    }else{ 	
   	 var i=2000+data.childrenCodeCount+1;  	 
   	 areaCode=data.code+"_"+i;   	 
    }
	return areaCode;
}