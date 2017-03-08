<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>

<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-form.js" charset="utf-8"></script>

<div id="unitAwardInfo" style="width:100%;">
	<form id="unitAward_base_form" method="post">
		<div id="unitAward_data_head" >
			<table id="unitAward_data_table" style="width:100%;">	
				<tr>
					<td>单位名：</td>
					<td colspan="3">
						<input id="unitId" name="unitId" class="easyui-combobox" 
						data-options="editable : false ,panelHeight:100"style="width:380px;">
					</td>
				</tr>
				<tr>
					<td>金额：</td>
					<td>
						<input type="text" id="money" name="money" class="easyui-numberbox" 
						data-options="min:0,max:999999999,precision:2" style="width:150px;">
					</td>
					<td>年度：</td>
					<td align="left">
						<input type="hidden" name="pageType" id="pageType" >
						<input type="hidden" name="id" id="id" >
						<input id="year" name="year" class="easyui-combobox" 
						data-options="editable : false ,panelHeight:100" style="width:150px;">
					</td>
				</tr>
				<tr>
					<td>区域</td>
					<td><input id="sysareaId" name="sysareaId" style="width:150px;"></td>
					<td></td>
					<td></td>				
				</tr>
			</table>
		</div>
	</form>
</div>

<script type="text/javascript">

	$(function() {
		loadAreaTreeAll('${path}','sysareaId');
		$('#pageType').val(pagetype);
		loadDictionary('${path }', 'unitId', 'JLQY');
		
		
		
		
// 		$('#unitId').combobox({
// 			required :true ,
// 			valueField : 'id',			//值
// 			textField : 'name',			//文本
// 			url : '${path}/unit/findAllUnitByCombobox?otype='+"ssjthqt",									//初始加载数据
// 			editable : false,				//不可编辑
// 			panelHeight:100
// 		});
		
		$('#year').combobox({
			valueField : 'id',			//值
			textField : 'name',	
			url : '${path}/unitAward/findYear',									//初始加载数据
			editable : false,				//不可编辑	
		});
		
		//初始化字典
		
		
		if(pagetype!='add'){
			$("#id").val(row.id);
			$("#unitId").textbox('setValue',row.unitId);
			$("#year").combobox('setValue',row.year);
			$("#money").numberbox('setValue',row.money);
		}
		
		
		$('#unitAward_base_form').form({
			async : false,
			ajax : true,
			type : 'POST',
		    url : '${path}/unitAward/saveOrUpdate',
		    onSubmit: function(param){
		    	var isValid = $(this).form('validate');
				
		    	if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					$.messager.show({title:"系统提示",msg:'请填写必填项!',timeout:5000,showType:'slide'});
					return false;
				}
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
	

	function confirmmethods() {
		$("#unitAward_base_form").submit();
	}
	
</script>