<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>

<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-form.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/js/object/valid.js" charset="utf-8"></script>

<div id="equipstandardInfo" style="width:100%;">
	<form id="equipstandard_base_form" method="post">
		<div id="equipstandard_data_head" >
			<table id="equipstandard_data_table" style="width:100%;">	
				<tr>
					<td>装备名称：</td>
					<td align="left">
						<input type="hidden" name="pageType" id="pageType" >
						<input type="hidden" name="id" id="id" >
						<input type="text" id="name" name="name" class="easyui-textbox" 
						data-options="required:true" style="width:250px;">
					</td>
					<td>装备类别：</td>
					<td>
						<input type="text" id="typecode" name="typecode" class="easyui-combobox" 
						data-options="editable:false,required:true,panelHeight:100"  style="width:250px;">
					</td>
				</tr>
				<tr>
					<td>省级：</td>
					<td>
						<input type="text" id="province" name="province" class="easyui-numberbox" 
						data-options="min:0,max:9999" style="width:152px;">
						<input id="countprovincetype" name="countprovincetype" class="easyui-combobox"
						data-options="editable:false,required:true, width:91,panelHeight:100">
					</td>
					<td>配备准则：</td>
					<td>
						<input type="text" id="equipcrite" name="equipcrite" class="easyui-combobox"
						data-options="editable:false,required:true,panelHeight:100" style="width:250px">
					</td>
				</tr>
				<tr>
					<td>市级：</td>
					<td>
						<input type="text" id="city" name="city" class="easyui-numberbox" 
						data-options="min:0,max:9999" style="width:152px;">
						<input id="countcitytype" name="countcitytype" class="easyui-combobox"
						data-options="editable:false,required:true,width:91,panelHeight:100">
					</td>
					<td>单位：</td>
					<td>
						<input id="unit" name="unit" class="easyui-combobox" 
						data-options="editable:false,required:true,panelHeight:100" style="width: 250px;"/>
					</td>
				</tr>
				<tr>
					<td>县级：</td>
					<td>
						<input type="text" id="county" name="county" class="easyui-numberbox" 
						data-options="min:0,max:9999" style="width:152px;">
						<input id="countcountytype" name="countcountytype" class="easyui-combobox"
						data-options="editable:false,required:true,width:91,panelHeight:100">
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
	        	<td align="left" colspan="4" style="height:50px;">
	        	<span style="color:f5572f;">
	        		注:如果是选择内设机构，则表示每个机构使用多少设备，如果选择人，则表示1台设备多少人用,
	        		如果选无，表示此单位对应所有的数量;	
	        	</span>
	        	</td>
	        	</tr>
			</table>
		</div>
	</form>
</div>

<script type="text/javascript">

	$(function() {
		$('#pageType').val(pagetype);
		//初始化字典
		loadDictionary('${path }', 'typecode', 'ZBLB');
		loadDictionary('${path }', 'countprovincetype', 'ZBBZLXCS');
		loadDictionary('${path }', 'countcitytype', 'ZBBZLXCS');
		loadDictionary('${path }', 'countcountytype', 'ZBBZLXCS');
		loadDictionary('${path }', 'unit', 'ZBPZBZUNIT');
		loadDictionary('${path }', 'equipcrite', 'ZBPZBZSFBP');
		
		
		if(pagetype!='add'){
			console.log(row);
			$("#id").val(row.id);
			$("#name").textbox('setValue',row.name);
			$("#typecode").combobox('setValue',row.typecode);
			$("#province").numberbox('setValue',row.province);
			$("#city").numberbox('setValue',row.city);
			$("#county").numberbox('setValue',row.county);
			$("#countprovincetype").combobox('setValue',row.countprovincetype);
			$("#countcitytype").combobox('setValue',row.countcitytype);
			$("#countcountytype").combobox('setValue',row.countcountytype);
			$("#unit").combobox('setValue',row.unit);
			$("#equipcrite").combobox('setValue',row.equipcrite);
		}
		
		
		$('#equipstandard_base_form').form({
			async : false,
			ajax : true,
			type : 'POST',
		    url : '${path}/equipStandard/saveOrUpdate',
		    onSubmit: function(param){
		    	var isValid = $(this).form('validate');
				
		    	if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					$.messager.show({title:"系统提示",msg:'请填写必填项!',timeout:5000,showType:'slide'});
					return false;
				}
		    	var valid = new Valid('t_sys_equipstandard', $('#id').val());
		    	valid.setStatus('status');
		 		valid.setFilter('装备名称', 'name', $("#name").textbox('getValue'));
		 		valid.setFilter('装备类别', 'typecode',$("#typecode").combobox('getValue'));
		    	if(!custom_Valid('${path}', valid)) return false;
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
		$("#equipstandard_base_form").submit();

	}
	
</script>