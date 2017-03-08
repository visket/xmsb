<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>

<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-form.js" charset="utf-8"></script>

<div id="expertInfo" style="width:100%;">
	<form id="expert_base_form" method="post">
		<div id="expert_data_head" >
			<table id="expert_data_table" style="width:100%;">	
				<tr>
					<td style="width:100px;">登陆名：</td>
					<td>
						<input type="hidden" name="pageType" id="pageType" >
						<input type="hidden" name="id" id="id" >
						<input type="text" id="loginname" name="loginname" class="easyui-textbox" 
						data-options="required:true" style="width:150px;">
					</td>
					<td style="width:100px;">名称：</td>
					<td>
						<input type="text" id="name" name="name" class="easyui-textbox" 
						data-options="required:true"  style="width:150px;">
					</td>
				</tr>
				<tr>
					<td style="width:100px;">密码：</td>
					<td>
						<input type="password" id="password" name="password" class="easyui-textbox" 
						data-options="required:true" style="width:150px;">
					</td>
					<td style="width:100px;">行业类别</td>
					<td><input id="tradetype" name ="tradetype" class="easyui-combobox"  
						data-options="editable:false,required:true,panelHeight:100"  style="width:150px;"/> 
					</td>
				</tr>
				<tr>
					<td style="width:100px;">电话：</td>
					<td>
						<input type="text" id="phone" name="phone" class="easyui-textbox"
						data-options="required:true" style="width:150px">
					</td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
	</form>
</div>

<script type="text/javascript">

	$(function() {
		$('#pageType').val(pagetype);
		//初始化字典
		loadDictionary('${path }', 'tradetype', 'XMFL');
		loadDictionary('${path }', 'countprovincetype', 'ZBBZLXCS');
		loadDictionary('${path }', 'countcitytype', 'ZBBZLXCS');
		loadDictionary('${path }', 'countcountytype', 'ZBBZLXCS');
		loadDictionary('${path }', 'unit', 'ZBPZBZUNIT');
		loadDictionary('${path }', 'equipcrite', 'ZBPZBZSFBP');
		
		
		if(pagetype!='add'){
			$("#id").val(row.id);
			$("#name").textbox('setValue',row.name);
			$("#password").textbox('setValue',row.password);
			$("#phone").textbox('setValue',row.phone);
			$("#loginname").textbox('setValue',row.loginname);
			$("#tradetype").combobox('setValue',row.tradetype);
		}
		
		
		$('#expert_base_form').form({
			async : false,
			ajax : true,
			type : 'POST',
		    url : '${path}/expert/saveOrUpdate',
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
		    	console.log(data);
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
		$("#expert_base_form").submit();

	}
	
</script>