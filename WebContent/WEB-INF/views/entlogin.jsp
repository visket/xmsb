<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>申报管理系统-企业登录</title>
<style>
body
{
    font-size:12px;
    color:#fff;
    background:#0057ab;
    }

</style>
</head>
<body>
 <div style="background:#0057ab;background-size:100%; background-repeat:no-repeat; height:78px; background-image:url('<c:url value='/static/images/loginanjju.png'/>');" ></div>

<br/><br/>
 
 
 <div style="width:100%;text-align: center;" >
	<div style="margin-left:auto;margin-right:auto;background:#0057ab;background-size:100%; background-repeat:no-repeat; width:1000px;height:600px; background-image:url('<c:url value='/static/images/loginbj.png'/>');">
		<form id="fusers" method="post">
			<table width="1000px">
				<tr><td rowspan="6" width="520px" ></td>  <td height="170px"></td><td height="170px"></td></tr>
		        <tr>  <td  colspan="2" style=" font-size:20px; font-family:楷体; color:#fff; text-align:left;"><b>企业登录</b></td></tr>
		        <tr><td  colspan="2" style=" font-size:20px; font-family:楷体; color:#fff; text-align:left;"><hr style="height:1px;border:none;border-top:1px solid #185598;" /> </td></tr>
				<tr>
					<td style="width:50px"><b>用户名：</b></td>
					<td  ><input type="text" id="loginname" name="username" value="admin" class="textbox" style="width:120px"/></td>
				</tr>
				<tr>
					<td  ><b>密&nbsp;&nbsp;&nbsp;码：</b></td>
					<td  ><input type="password" id="pwd" name="password" class="textbox"  value="test" style="width:120px"/></td>
				</tr>
		        <tr>
		        <td  ></td>
		        <td  >
		        	<input  style="border-style:none; width:50px;color:#fff;background-image:url('<c:url value='/static/images/qr.png'/>');" type="button" id="btusubmit" value="登 录"  />
		        	<input  style="border-style:none; width:50px;color:#fff;background-image:url('<c:url value='/static/images/qr.png'/>');" type="button" id="register" value="注 册"   />
		        </td>
		        </tr>
			</table>
		</form>
	</div>
 </div>
 
</body>
<script type="text/javascript">

  $(function(){
	   $('#loginname').validatebox({
			required : true,
			validType : 'length[3,30]'
		});
   });
  
   $('#pwd').validatebox({
		required : true
	});
   
   $('#loginname,#pwd').bind('keypress',function(event){
       if(event.keyCode == "13")    
       {
       	//前台验证
       	if (!$('#loginname').validatebox('isValid')) {
       		$('#loginname').focus();
       		return false;
       	}
       	if (!$('#pwd').validatebox('isValid')) {
       		$('#pwd').focus();
       		return false;
       	}
       	//后台验证
       	checkLogin();
       }
   });
   
   $('#clearForm').click(function() {
		$('#fusers').form('clear');
   });
   
 //绑定提交事件
   $('#btusubmit').click(function() {
   	//前台验证
   	if (!$('#loginname').validatebox('isValid')) {
   		$('#loginname').focus();
   		return false;
   	}
   	if (!$('#pwd').validatebox('isValid')) {
   		$('#pwd').focus();
   		return false;
   	}
   	//后台验证
   	checkLogin();
   });
 
   function checkLogin(){
		$.ajax({
			url : "${path }/login",
			type : "post",
			data : $("#fusers").serializeArray(),
			async : false,
			beforeSend : function() {
				$.messager.progress({
					msg : '登录中......',
					text : '${value}'
				})
			},
			success : function(data) {		
				$.messager.progress("close");
                 if(data.success)
                    window.location.href="${path}/index";
                    else
                    	$.messager.show({title:"系统提示",msg:data.msg,timeout:5000,showType:'slide'});
                    	
			}
		})
	}
   
   
   $('#register').click(function() {
	   window.location.href="${path}/anonpage/userRegister.jsp";
   });
</script>   
</html>