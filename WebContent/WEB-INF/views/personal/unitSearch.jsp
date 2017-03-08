<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>



 <script type="text/javascript">

 $(function() {
	 loadDictionary('${path}', 'type', 'DWLB');
	 loadDictionary('${path}', 'tradeType', 'XMFL');
	 $('#organizationId').combotree({
         url : '${path }/organization/anonTree?type='+'DWLB_AJJ',
         parentField : 'pid',
         lines : true,
         panelMaxHeight : 160,
         panelHeight : 300
     });

     $('#roleIds').combotree({
         url: '${path }/role/tree',
         multiple: true,
         required: true,
         panelHeight : 'auto'
     });
	 
	 
	 $.ajax({
		 type :'post',
		 url :'${path}/personalUnit/find',
		 data : {
			 id :$('#usersession').val()
		 },
		 success : function(data) {
			 /* <input type="hidden" id="type" name="type">
			 <input type="hidden" id="higherLevelIdentifier" name="higherLevelIdentifier">
			 <input type="hidden" id="sysareaId" name="sysareaId">
			 <input type="hidden" id="gradetype" name="gradetype"> */
		 console.log(data);
	     $("#unittype").val(data.type);
		 $("#higherLevelIdentifier").val(data.higherLevelIdentifier);
		 $("#sysareaId").val(data.sysareaId);
		 $("#gradetype").val(data.gradetype);
         $('#unitIdentifier').textbox('setValue',data.unitIdentifier);
		 $('#name').textbox('setValue',data.name);
		 $('#organizationId').combotree('setValue',data.userorganizationId);
		 $('#telephone').textbox('setValue',data.telephone);
 		 $('#unitLinkman').textbox('setValue',data.unitLinkman);
		 $('#zipCode').textbox('setValue',data.zipCode);
 		 $('#phone').textbox('setValue',data.phone);
 		 $('#portraiture').textbox('setValue',data.portraiture);
		 $('#email').textbox('setValue',data.email);
		 $('#address').textbox('setValue',data.address);
		 $('#tradeType').combobox('setValue',data.tradeType);
		 $('#userid').val(data.userId);
		 $('#unitid').val(data.id);
		 }
	 });
 });
 
 
 function saveFun(){
	 if (!$('#personal_details').form('validate')) {
		 $.messager.alert('错误', '数据填写不完整', 'warning');
			return false;
	 }else{ 
		 $.ajax({
			 type :'post',
			 url :'${path}/personalUnit/save',
			 data : $("#personal_details").serializeArray(),
			 success : function() {
				 $.messager.show({title:"系统提示",msg:'保存成功',timeout:5000,showType:'slide'});
			 }
		
		 });
		
	 }
 }
</script>



 <div class="easyui-layout" data-options="fit:true,border:false">
<div class="td_sinleft">
  <a onclick="saveFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">保存</a>
</div>

<br/> 
 
 <h1  style=" width:100%; text-align: center; font-size:22px">企业基础信息</h1>
 <div style="width:100%;text-align: center;" >
	<div style="margin-left:auto;margin-right:auto;width:500px;  ">
		<form id="personal_details" method="post">
<br>
<br>	
 
<!-- 获取USER SESSION的隐藏域 -->
<input type="hidden" id="usersession" value="${currentUser.id}">
<input type="hidden" id="userid" name="userId">
<input type="hidden" id="unitid" name="id">
<input type="hidden" id="unittype" name="type">
<input type="hidden" id="higherLevelIdentifier" name="higherLevelIdentifier">
<input type="hidden" id="sysareaId" name="sysareaId">
<input type="hidden" id="gradetype" name="gradetype">
				
<table id="personTable" data-options="fit:true,border:false" style="width:500px">
				 
                <tr>
	        		<td>组织机构代码</td>
	        		<td><input   id="unitIdentifier" name="unitIdentifier" class="easyui-textbox" data-options="required:true,editable:false,disabled:true"/></td>
	        		<td>单位名称</td>
	        		<td><input id="name" name="name" class="easyui-textbox" data-options="required:true"/></td>
                </tr>
                
                <tr>
                	<td>上级主管部门</td>
                    <td><select id="organizationId" name="userorganizationId" class="easyui-combotree" data-options="required:true"></select></td>
					<td>联系电话</td>
	        		<td><input id="telephone" name="telephone" class="easyui-textbox" data-options="required:true"/></td> 	
				</tr>
	        	<tr>
	        		<td>联系人</td>
	        		<td><input id="unitLinkman" name="unitLinkman" class="easyui-textbox" data-options="required:true"/></td>
	        		<td>邮编</td>
	        		<td><input id="zipCode" name="zipCode" class="easyui-textbox" data-options="required:true"/></td>
	        	</tr>
	        	<tr>
	        		<td>手机号</td>
	        		<td><input id="phone" name="phone" class="easyui-textbox" data-options="required:true"/></td>
	        		<td>传真</td>
	        		<td><input id="portraiture" name="portraiture" class="easyui-textbox" data-options="required:true"/></td>
	        	</tr>
	        	<tr>
	        		<td>邮箱</td>
	        		<td><input id="email" name="email" class="easyui-textbox" data-options="required:true"/></td>
	        		<td>行业类别</td>
	        		<td>
	        		<input id="tradeType"name="tradeType" class="easyui-combobox"	data-options="required:true"/>
	        		</td>
	        	</tr>
	        	<tr>
	        		<td>
	        			地址
	        		</td>
	        		<td colspan="3">
	        			<input id="address" name="address" class="easyui-textbox" style="width:388px" data-options="required:true"/>
	        		</td>
	        	</tr> 
	         
	        
	        </table>



		</form>
	</div>
 </div>
 </div>
 

