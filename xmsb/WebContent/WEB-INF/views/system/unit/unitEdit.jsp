<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<%@ include file="/commons/js/extjs.jsp" %>

<div id="unitInfo" style="width:100%; ">
	<form id="unit_base_form" method="post">
		<div id="unit_data_head" >
			<table id="unit_data_table" style="width:100%;">				
				<tr>
					<td id="input_title" width="110px">企业名称：</td>
					<td width="140px">
						<input type="hidden" name="pageType" id="pageType" >
						<input type="hidden" name="processId" id="processId" >
						<input type="hidden" name="areaId" id="areaId" >
						<input type="hidden" name="id" id="id" >
						<input type="text" id="name" name="name" class="easyui-textbox" data-options="editable:false,disabled:true" />
					</td>
						<td width="110px">组织机构代码：</td>
					<td width="150px">
						<input type="text" id="unitIdentifier" name="unitIdentifier" data-options="editable:false,disabled:true"	class="easyui-textbox" style="width:150px;">
					</td>
				</tr>
				<tr>
					<td width="110px">主管部门：</td>
					<td width="150px">
	        			<input type="text" id="userorganizationname"name="userorganizationname" class="easyui-textbox"	data-options="editable:false,disabled:true"/>
	        		</td>
	        		<td width="110px">行业类别：</td>
					<td width="150px">
						<input type="text" id="tradeType" name="tradeType" data-options="editable:false,disabled:true"	class="easyui-combobox" style="width:150px;">
					</td>
				</tr>
				<tr>
					<td width="110px">级别：</td>
					<td width="150px">
						<input id="gradetype" name="gradetype" class="easyui-combobox"
						data-options="editable:false,disabled:true" style="width:150px">
					</td>	
					<td width="110px">联系人：</td>
					<td width="150px">
						<input type="text" id="unitLinkman" name="unitLinkman" class="easyui-textbox" data-options="editable:false,disabled:true" style="width:150px;">
					</td>
				</tr>
				<tr>
					<td width="110px">联系电话：</td>
					<td width="150px">
						<input type="text" id="telephone" name="telephone" class="easyui-textbox" data-options="editable:false,disabled:true"	style="width:150px;">
					</td>
					<td width="110px">手机号：</td>
					<td width="150px">
						<input type="text" id="phone" name="phone" class="easyui-textbox" data-options="editable:false,disabled:true" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td width="110px">邮箱：</td>
					<td width="150px">
						<input type="text" id="email" name="email" class="easyui-textbox" data-options="editable:false,disabled:true" style="width:150px">
					</td>
					<td width="110px">邮编：</td>
					<td width="150px">
						<input type="text" id="zipCode" name="zipCode" class="easyui-textbox" data-options="editable:false,disabled:true" style="width:150px;">
					</td>
				</tr>
				<tr>
					<td width="110px">传真：</td>
					<td width="150px">
						<input type="text" id="portraiture" name="portraiture" class="easyui-textbox"  data-options="editable:false,disabled:true" style="width: 150px;"/>
					</td>
	        		<td width="110px">地址：</td>
					<td width="150px">
						<input type="text" name="address" id="address" class="easyui-textbox" data-options="editable:false,disabled:true" style="width: 150px;"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>

<script type="text/javascript">

	$(function() {
		//初始化设置JS携带参数
		$('#pageType').val(pagetype);
	 

		//自适应初始化当前整行数据的宽度
 
		
		//set_creatorfieldstatus('disable');
		
		//初始化字典
// 		loadDictionary('${path }', 'tradeType', 'XMLX');
		loadDictionary('${path }', 'tradeType', 'XMFL');
		loadDictionary('${path }', 'gradetype', 'DWDJ');
// 		loadDictionary('${path }', 'classesId', 'XMLB');
// 		loadDictionary('${path }', 'statusId', 'XMZT');
		
		
		//pagetype为edit与search时，自动绑定值到参数列表
		if(pagetype != 'add') {
			console.log(row);
			$('#id').val(row.id);
			$('#unitIdentifier').textbox('setValue',row.unitIdentifier);
			$('#unitLinkman').textbox('setValue',row.unitLinkman);
			$('#telephone').textbox('setValue', row.number);
			$('#name').textbox('setValue', row.name);
			$('#phone').textbox('setValue', row.phone);
			$('#zipCode').textbox('setValue', row.zipCode);
			$('#portraiture').textbox('setValue', row.portraiture);
			$('#createtime').datebox('setValue', row.createtime);
			$('#lastupdatetime').datebox('setValue', row.lastupdatetime);
			$('#email').textbox('setValue', row.email);
			$('#address').textbox('setValue', row.address);
			$('#address').textbox('setValue', row.address);
			$('#tradeType').combobox('setValue', row.tradeType);
			$('#gradetype').combobox('setValue', row.gradetype);
			$('#creatorname').textbox('setValue',row.creatorname);
			$('#updatorname').textbox('setValue',row.updatorname);
			$('#userorganizationname').textbox('setValue',row.higherLevelName);
		} 
		
		//初始化页面编辑状态
		setDataIsEdit();
		
		$('#unit_base_form').form({
			async : false,
			ajax : true,
			type : 'POST',
		    url : '${path}/unit/search',
		    onSubmit: function(param){
		    	var isValid = $(this).form('validate');
				
		    	if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					$.messager.show({title:"系统提示",msg:'请填写必填项!',timeout:5000,showType:'slide'});
					return false;
				}
		    	
		    	/* if(!data_validate('${ctx}', $('#id').val(), 'BookInfo', '文档名称', 'txtName' , $('#txtName').val())) {
		    		$.messager.progress('close');
		    		$('#txtName').textbox('setValue', '');
		    		return false;
		    	} */
		    		
				return isValid;
		    },    
		    success:function(data){
		    	$.messager.progress('close');
				data = JSON.parse(data);
				$.messager.show({title:"系统提示",msg:data.msg,timeout:5000,showType:'slide'});
				if (data.success) {
					$('#id').val(data.id);
					if($('#pageType').val() == 'add') {
						$('#pageType').val('edit');
					}
				}
				return data;
		    }    
		});
	});
	
	
	//设置控件状态是否编辑
	function setDataIsEdit() {
		var status = $('#pageStatus').val();
		var flag = '';
		if(status == 'search') flag = 'disable';
		else flag = 'enable';
		
		//$('#statusId').combobox("disable");
		
		//$('#creatorId').combogrid("disable");
		//$('#updatorId').combogrid("disable");
		
		//$('#createtime').datebox("disable");
		//$('#lastupdatetime').datebox("disable");
		//$('#starttime').datebox("disable");
		//$('#endtime').datebox("disable");
		
		
		/* $('#txtName').textbox(flag);
		$('#bookName').textbox(flag);
		$('#author').textbox(flag);
		$('#page').textbox(flag);
		
		$('#inputDateStr').datebox(flag);
		$('#publishDateStr').datebox(flag);		

		$('#inputorId').combogrid(flag);
		$('#publishId').combobox(flag);
		$('#majorTypeId').combobox(flag);
		
		$("#filebtn").linkbutton(flag); */
	}

	function confirmmethods() {
		if('search' == $('#pageType').val()) {
			$.messager.alert('提醒', '请先启用编辑后再保存!', 'warning');
			return false;
		}
		$("#project_base_form").submit();

	}
	
	/**
	 * 项目申报操作
	 */
	function confirmdeclare() {

		
		
	}
	

	
</script>