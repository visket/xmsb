<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>

<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-form.js" charset="utf-8"></script>

<div id="hiddangerExamineInfo" style="width:100%;">
	<form id="hiddangerExamine_base_form" method="post">
		<div id="hiddangerExamine_data_head" >
			<table id="hiddangerExamine_data_table" style="width:100%;">
				<tr>
					<td>考核名称：</td>
					<td colspan="3">
						<input type="hidden" name="pageType" id="pageType" >
						<input type="hidden" name="id" id="id" >
						<input type="text"  id="name" name="name" class="easyui-textbox" 
						data-options="required:true" style="width:377px;" >
					</td>
				</tr>
				<tr>
					<td>考核类别：</td>
					<td>
						<input id="type" name="type" class="easyui-combobox" 
						data-options="editable : false ,panelHeight:100" style="width:150px;">
					</td>
					<td>考核总分</td>
					<td>
						<input id="totalPoints" name="totalPoints" class="easyui-numberbox" 
						data-options="min:0,max:999999" style="width:150px;">
					</td>
				</tr>
			</table>
		</div>
	</form>
        <table id="hiddangerGrade_list_datagrid" data-options="fit:true,border:false" style="width: 350px"></table>
</div>
<script type="text/javascript">

	$(function() {
		$('#pageType').val(pagetype);
		loadDictionary('${path }', 'type', 'KHNR');
		//初始化字典
		if(pagetype!='add'){
			$("#id").val(row.id);
			$("#name").textbox('setValue',row.name);
			$("#type").combobox('setValue',row.type);
			$("#totalPoints").numberbox('setValue',row.totalPoints);
		}
		$('#hiddangerExamine_base_form').form({
			type : 'POST',
		    url : '${path}/hiddangerExamine/saveOrUpdate',
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
	
	
	function hiddangerGrade_list_add() {
		var str ="<div><iframe id='hiddangerGrade_list_add' src='${path}/hiddangerGrade/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbynewpage('hiddangerGrade_list_add', str, '隐患考核评分添加', 500,250, 'add', 'hiddangerGrade_list_datagrid', row);
	};
	
	function hiddangerGrade_list_edit() {
		var str ="<div><iframe id='hiddangerGrade_list_edit' src='${path}/hiddangerGrade/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbynewpage('hiddangerGrade_list_edit', str, '隐患考核评分编辑', 500,250, 'edit', 'hiddangerGrade_list_datagrid', $('#hiddangerGrade_list_datagrid').datagrid('getSelected'));
	};

	function confirmmethods() {
		$("#hiddangerExamine_base_form").submit();
	}
	
</script>