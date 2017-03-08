<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>



 <script type="text/javascript">

 $(function() {
	 $.ajax({
		 type :'post',
		 url :'${path}/personalUnit/find',
		 data : {
			 id :$('#usersession').val()
		 },
		 success : function(data) {
			 console.log(data);
         $('#loginname').textbox('setValue',data.loginname);
		 $('#id').val(data.userId);
		 }
	 });
 });
 
 
 function saveuserFun(){
	 if (!$('#user_details').form('validate')) {
		 $.messager.alert('错误', '数据填写不完整', 'warning');
			return false;
	 }else{ 
		 $.ajax({
			 type :'post',
			 url :'${path}/personalUnit/saveuser',
			 data : $("#user_details").serializeArray(),
			 success : function(result) {
				 console.log(result);
				 $.messager.show({title:"系统提示",msg:result.msg,timeout:5000,showType:'slide'});
			 }
		
		 });
		
	 }
 }
</script>



 <div class="easyui-layout" data-options="fit:true,border:false">
<div class="td_sinleft">
  <a onclick="saveuserFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">保存</a>
</div>

<br/> 
 
 <h1  style=" width:100%; text-align: center; font-size:22px">密码设置</h1>
 <div style="width:100%;text-align: center;" >
	<div style="margin-left:auto;margin-right:auto;width:500px;  ">
		<form id="user_details" method="post">
<br>
<br>	
 
<!-- 获取USER SESSION的隐藏域 -->
<input type="hidden" id="usersession" value="${currentUser.id}">
<input type="hidden" id="id" name="id">
				
<table id="userTable" data-options="fit:true,border:false" style="width:500px"> 
	        	<tr align="center">
                    <td>登录名</td>
                    <td><input id="loginname" name="loginname" type="text" style="width: 300px" class="easyui-textbox" data-options="disabled:true"></td>
                </tr>
                <tr align="center">
                    <td>旧密码</td>
                    <td><input id="password1" name="oldpassword" type="password" style="width: 300px" class="easyui-textbox" data-options="required:true"></td>
                </tr>
                <tr align="center">
                    <td>新密码</td>
                    <td><input id="password2" name="password" type="password" style="width: 300px"class="easyui-textbox" data-options="required:true"></td>
                </tr>
                <tr align="center">
                    <td>确认密码</td>
                    <td><input id="password3" name="newpassword"  type="password" style="width: 300px" class="easyui-textbox" data-options="required:true"></td>
                </tr>
	        </table>
		</form>
	</div>
 </div>
 </div>
 

