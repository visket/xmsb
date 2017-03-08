<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>

<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-form.js" charset="utf-8"></script>

<div id="hiddangerGradeInfo" style="width:100%;">
	<form id="hiddangerGrade_base_form" method="post">
		<div id="hiddangerGrade_data_head" >
			<table id="hiddangerGrade_data_table" style="width:100%;">	
				<tr>
					<td>评分标准：</td>
					<td colspan="3">
						<input type="hidden" name="pageType" id="pageType" >
						<input type="hidden" name="id" id="id">
						<input type="hidden" name="examineId" id="examineId">
						<input id="grade" name="grade" class="easyui-textbox" 
						data-options="required:true" style="width:380px;">
					</td>
				</tr>
				<tr>
					<td>分值：</td>
					<td>
						<input type="text" id="points" name="points" class="easyui-numberbox" 
						data-options="min:0,max:999999999" style="width:150px;">
					</td>
<!-- 					<td> 分值类型：</td> -->
<!-- 					<td align="left"> -->
<!-- 						<input id="pointsType" name="year" class="easyui-combobox"  -->
<!-- 						data-options="editable : false ,panelHeight:100" style="width:150px;"> -->
<!-- 					</td> -->
				</tr>
			</table>
		</div>
	</form>
</div>

<script type="text/javascript">

	$(function() {
		$('#pageType').val(pagetype);
		$('#examineId').val(row.id);
		//loadDictionary('${path }', 'unitId', 'JLQY');
		//初始化字典
		if(pagetype!='add'){
			$("#id").val(row.id);
			$("#examineId").val(row.examineId);
			$("#grade").textbox('setValue',row.grade);
			$("#points").numberbox('setValue',row.points);
		}
		$('#hiddangerGrade_base_form').form({
			async : false,
			ajax : true,
			type : 'POST',
		    url : '${path}/hiddangerGrade/saveOrUpdate',
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
		$("#hiddangerGrade_base_form").submit();
	}
	
</script>