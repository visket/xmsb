<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>



 <script type="text/javascript">

 $(function() {
	 
	 
	 loadDictionaryTest('${path}', 'type', 'DWLB');
	 loadDictionaryTest('${path}', 'tradeType', 'XMFL');
// 	 loadUnitByUnitId('${path}','test',1);
	 $('#organizationId').combotree({
         url : '${path }/organization/anonTree?type='+'DWLB_AJJ',
         parentField : 'pid',
         lines : true,
         panelMaxHeight : 160,
         panelHeight : 300
     });

    /*  $('#roleIds').combotree({
         url: '${path }/role/tree',
         multiple: true,
         required: true,
         panelHeight : 'auto'
     }); */
 });
 
 
 
 function addRegister(){
	 if (!$('#register_details').form('validate')) {
		 $.messager.alert('错误', '数据填写不完整', 'warning');
			return false;
	 }else{ 
		 $.ajax({
			 type :'post',
			 url :'${path}/register/addUserAndUnit',
			 data : $("#register_details").serializeArray(),
			 success : function() {
				 	console.log("${path}/index");
	                window.location.href="${path}/index";
			 }
		 });
	 }
 }
 
 
 function returnRegister(){
	 window.location.href = "${path}/login.jsp";
 }
</script>

 <title>安检局管理系统-企业注册</title>
<style>
body
{
    font-size:12px;
    color:#fff;
    background:#0057ab;
    }

</style>
 
<div style="background:#0057ab;background-size:100%; background-repeat:no-repeat; height:78px; background-image:url('<c:url value='/static/images/loginanjju.png'/>');" ></div>

<br/> 
<br>	
<br>	
 <h1  style=" width:100%; text-align: center; font-size:36px">企业注册</h1>
 
 <div style="width:100%;text-align: center;" >
	<div style="margin-left:auto;margin-right:auto;background:#0057ab;  width:500px;  ">
		<form id="register_details" method="post">
<br>
<br>	
 

				
<table id="userTable" data-options="fit:true,border:false" style="width:500px"> 
	        	<tr>
                    <td>登录名</td>
                    <td><input name="loginname" type="text" placeholder="请输入登录名称" class="easyui-textbox" data-options="required:true" value=""></td>
                	<td>密码</td>
                    <td><input id="password" name="password" type="password" placeholder="请输入密码" class="easyui-textbox" data-options="required:true"></td>
                </tr>
                <!-- <tr>
                    <td>重复密码</td>
                    <td><input id="password2" type="password" placeholder="请输入密码" class="easyui-textbox" data-options="required:true"></td>
                	<td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
                            <option value="0" selected="selected">男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                </tr>
                <tr>
             	    <td>姓名</td>
                    <td><input name="username" type="text" placeholder="请输入姓名" class="easyui-textbox" data-options="required:true" value=""></td>
					<td>手机号</td>
	        		<td><input id="userphone" name="userphone" class="easyui-textbox"/></td>
	        	</tr> -->
	        	<tr></tr>
	        	
                <tr>
	        		<td>组织机构代码</td>
	        		<td><input id="unitIdentifier" name="unitIdentifier" class="easyui-textbox" data-options="required:true"/></td>
	        		<td>单位名称</td>
	        		<td><input id="name" name="name" class="easyui-textbox" data-options="required:true"/></td>
                </tr>
                
                <tr>
                	<td>上级主管部门</td>
                    <td><select id="organizationId" name="userorganizationId" class="easyui-combotree" data-options="required:true" style="overflow:scroll"></select></td>
					<td>
	        			行业类别
	        		</td>
	        		<td  >
	        			<input id="tradeType"name="tradeType" class="easyui-combobox"	data-options="required:true"/>
	        		</td>
					
					
					</tr>
	        	<tr>
	        	
	        		<td>联系人</td>
	        		<td><input id="unitLinkman" name="unitLinkman" class="easyui-textbox" data-options="required:true"/></td>
	        		
	        		<td>联系电话</td>
	        		<td><input id="telephone" name="unittelephone" class="easyui-textbox" data-options="required:true"/></td> 	
	        	</tr>
	        	<tr>
	        		<td>手机号</td>
	        		<td><input id="phone" name="unitphone" class="easyui-textbox" data-options="required:true"/></td>
	        		<td>传真</td>
	        		<td><input id="portraiture" name="portraiture" class="easyui-textbox" data-options="required:true"/></td>
	        	</tr>
	        	<tr>
	        		<td>邮编</td>
	        		<td><input id="zipCode" name="zipCode" class="easyui-textbox" data-options="required:true"/></td>
	        		<td>邮箱</td>
	        		<td><input id="email" name="email" class="easyui-textbox" data-options="required:true"/></td>
	        		
	        	</tr>
	        	<tr>
	        		<td>地址</td>
	        		<td colspan="3"><input id="address" name="address" class="easyui-textbox"  style="width:388px"  data-options="required:true" /></td>
	        	</tr> 
	        	<tr align="center">
	        	<td align="left" colspan="4" style="height:50px;">
	        	<span style="color:f5572f;">注:请认真填写机构信用代码，并对所填资料自行负责</span>
	        	</td>
	        	</tr>
	        	<tr align="center">
	        		<td align="center" colspan="4">
	        			<input  style="border-style:none; width:50px;color:#fff;background-image:url('<c:url value='/static/images/qr.png'/>');" type="button" onclick="addRegister()" value="确定"  />
	        			&nbsp;&nbsp;&nbsp;&nbsp;
	        			<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
	        			<input  style="border-style:none; width:50px;color:#fff;background-image:url('<c:url value='/static/images/qr.png'/>');" type="button" onclick="returnRegister()" value="取 消"   />
	        		</td>
		         
		        		
	        		 
	        	</tr>
	        </table>



		</form>
	</div>
 </div>
 
 

