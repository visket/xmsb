<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>

<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>


<div id="projectInfo" style="width:100%;height:400px;">
	<form id="project_base_form" method="post">
		<div id="project_data_head" >
			<table id="project_data_table" style="width:100%;height:400px;">				
				<tr>
					<td id="input_title" width="110px">项目名称：</td>
					<td width="140px" colspan="3">
						<input type="hidden" name="pageType" id="pageType" >
						<input type="hidden" name="processId" id="processId" >
						<input type="hidden" name="areaId" id="areaId" >
						<input type="hidden" name="id" id="id" >
						<input type="text" id="name" name="name" class="easyui-textbox" data-options="required:true" style="width: 150px;">
					</td>
				</tr>
				<tr>
					<td width="110px">项目编号：</td>
					<td width="150px">
						<input type="text" id="number" name="number" class="easyui-textbox" style="width:150px;">
					</td>
					<td width="110px">关联企业：</td>
					<td width="150px">
						<input type="text" id="unitId" name="unitId" class="easyui-combogrid" 
						data-options="editable:false" style="width:150px;">
					</td>
				</tr>
				<tr>
					<td width="110px">项目类型：</td>
					<td width="150px">
						<input type="text" id="typeId" name="typeId" class="easyui-combobox" 
						data-options="editable:false" style="width:150px;">
					</td>
					<td width="110px">项目状态：</td>
					<td width="150px">
						<input type="text" id="statusId" name="statusId" class="easyui-combobox" 
						data-options="editable:false" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td width="110px">项目分类：</td>
					<td width="150px">
						<input type="text" id="classifyId" name="classifyId" class="easyui-combobox" 
						data-options="editable:false" style="width:150px;">
					</td>
					<td width="110px">项目类别：</td>
					<td width="150px">
						<input type="text" id="classesId" name="classesId" class="easyui-combobox" 
						data-options="editable:false" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td width="110px">项目开始时间:</td>
					<td width="150px">
						<input type="text" id="starttime" name="starttime" class="easyui-datebox"
						data-options="editable:false" style="width:150px">
					</td>
					<td width="110px">项目结束时间:</td>
					<td width="150px">
						<input type="text" name="endtime" id="endtime" class="easyui-datebox" 
						data-options="editable:false" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td width="110px">项目负责人:</td>
					<td width="150px">
						<input type="text" id="principaltorId" name="principaltorId" class="easyui-combogrid"
						data-options="editable:false" style="width:150px">
					</td>
					<td width="110px">项目总造价:</td>
					<td width="150px">
						<input type="text" name="cost" id="cost" class="easyui-numberbox" 
						data-options="min:0,precision:2" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td width="110px">创建人:</td>
					<td width="150px">
						<input type="text" id="creatorId" name="creatorId" class="easyui-combogrid"
						data-options="editable:false" style="width:150px">
					</td>
					<td width="110px">创建时间:</td>
					<td width="150px">
						<input type="text" name="createtime" id="createtime" class="easyui-datebox" 
						data-options="editable:false" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td width="110px">修改人:</td>
					<td width="150px">
						<input type="text" id="updatorId" name="updatorId" class="easyui-combogrid"
						data-options="editable:false" style="width:150px">
					</td>
					<td width="110px">最后修改时间:</td>
					<td width="150px">
						<input type="text" name="lastupdatetime" id="lastupdatetime" class="easyui-datebox" 
						data-options="editable:false,disabled:true" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td width="110px">回复状态：</td>
					<td width="150px">
						<input type="text" id="reviewstatus" name="reviewstatus" class="easyui-combobox" 
						data-options="editable:false" style="width: 150px;"/>
					</td>
					<td width="110px">回复评价：</td>
					<td width="150px">
						<input type="text" id="reviewscore" name="reviewscore" class="easyui-combobox" 
						data-options="editable:false" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td width="110px">评价详情：</td>
					<td width="220px" colspan="3">
						<textarea class="textbox" id="reviewcontent" name="reviewcontent" 
						style="height:100px;width:100%;white-space: pre-wrap;overflow-y:auto" cols="4"></textarea>
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
		var input_title_width = $('#input_title')[0].clientWidth;
		var width = $('#project_data_table')[0].clientWidth - input_title_width;
		$('#name').textbox('resize', width);
		
		//set_creatorfieldstatus('disable');
		
		//初始化字典
		loadDictionary('${path }', 'typeId', 'XMLX');
		loadDictionary('${path }', 'classifyId', 'XMFL');
		loadDictionary('${path }', 'classesId', 'XMLB');
		loadDictionary('${path }', 'statusId', 'XMZT');
		
		
		//pagetype为edit与search时，自动绑定值到参数列表
		if(pagetype != 'add') {
			loadUserCombogrid('${ctx }', 'creatorId', row.creatorId, row.creatorName);
			loadUserCombogrid('${ctx }', 'updatorId', row.updatorId, row.updatorName);
			loadUserCombogrid('${ctx }', 'principaltorId', row.principaltorId, row.principaltorName);
			
			$('#id').val(row.id);
			$('#areaId').val(row.areaId);
			$('#processId').val(row.processId);
			$('#number').textbox('setValue', row.number);
			$('#name').textbox('setValue', row.name);
			$('#cost').numberbox('setValue', row.cost);
			
			$('#starttime').datebox('setValue', row.starttime);
			$('#endtime').datebox('setValue', row.endtime);
			$('#createtime').datebox('setValue', row.createtime);
			$('#lastupdatetime').datebox('setValue', row.lastupdatetime);
			
			$('#unitId').combogrid('setValue', row.unitId);
			$('#creatorId').combogrid('setValue', row.creatorId);
			$('#updatorId').combogrid('setValue', row.updatorId);
			$('#principaltorId').combogrid('setValue', row.principaltorId);
			
			$('#typeId').combobox('setValue', row.typeId);
			$('#classifyId').combobox('setValue', row.classifyId);
			$('#classesId').combobox('setValue', row.classesId);
			$('#statusId').combobox('setValue', row.statusId);
			$('#reviewstatus').combobox('setValue', row.reviewstatus);
			$('#reviewscore').combobox('setValue', row.reviewscore);
			
			$('#reviewcontent').text(row.reviewcontent);
			
		} else {
			//loadUserCombogrid('${ctx }', 'inputorId','${user.id}','${user.name}');
			//loadUserCombogrid('${ctx }', 'entertor','${user.id}','${user.name}');
			//$('#creatorId').combogrid('setValue', '${user.id}');
			//$('#updatorId').combogrid('setValue', '${user.id}');
			//$('#principaltorId').combogrid('setValue', '${user.id}');
			
			
			$('#statusId').combobox('setValue', 'XMZT_TBZ');
			
			$('#createtime').datebox('setValue', new Date().toLocaleDateString());
			$('#lastupdatetime').datebox('setValue', new Date().toLocaleDateString());
			$('#starttime').datebox('setValue', new Date().toLocaleDateString());
			
		}
		
		//初始化页面编辑状态
		setDataIsEdit();
		
		$('#project_base_form').form({
			async : false,
			ajax : true,
			type : 'POST',
		    url : '${path}/project/saveOrUpdate',
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