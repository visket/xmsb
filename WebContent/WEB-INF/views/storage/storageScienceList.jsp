<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/storage/storageScience.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/storageTypes.js" charset="utf-8"></script>


<html>
<head>
<title>科技项目审核界面</title>
	<script type="text/javascript">
		//当前页全局监听权限状态
		var operate_status = 0;
	
	    $(function(){
	
	    	
	    	init_storageScience_list_datagrid('${path}', '${currentUser.id}', '${currentUser.organizationId}', '${currentUser.unitId}');
	    	
	    	$("#projecttype").combobox({
	    		valueField : 'id',
	    		textField : 'itemvalue',	
	    		editable : false,
	    		data : [{'id':'1','itemvalue':'研发类'},{'id':'2','itemvalue':'推广应用类'}]
	    	});
	    	
	    	loadUnitByUnitId('${path}','unitId',null);
	    	
	    });
	    
	    
	    function project_list_fileter() {
	    	$('#storageScience_list_form').datagrid('load', {
	    		projectName : $.trim($('#projectName').val()),
	    		unitId : $('#unitId').combogrid('getValue'),
	    		statusId : $('#statusId').combobox('getValue'),
	    		projecttype : $('#projecttype').combobox('getValue'),
	    		accepttimeBegin : $('#accepttimeBegin').datebox('getValue'),
	    		accepttimeEnd : $('#accepttimeEnd').datebox('getValue')
	    	});
	    }
	    
	    function project_list_reset() {
	    	clearTableToolbar('storageScience_list_form');
	    };
	    
	</script>
</head>


<body>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach" style="height:70px">
		<form id="storageScience_list_form" method="POST">
			<table>
				<tr>
					<td>项目名称:</td>
					<td>
						<input type="text" id="projectName" name="projectName" class="easyui-textbox" />
					</td>
					<td>申报单位:</td>
					<td>
						<input type="text" id="unitId" name="unitId" class="easyui-combogrid" />
					</td>
					<td>申报日期:</td>
					<td>
						<input type="text" id="accepttimeBegin" name="accepttimeBegin" class="easyui-datebox" data-options="editable:false"/>
					</td>
					<td>至</td>
					<td>
						<input type="text" id="accepttimeEnd" name="accepttimeEnd" class="easyui-datebox" data-options="editable:false"/>
					</td>
					
				</tr>
				<tr>
					<td>项目状态:</td>
					<td>
					    <input type="text" id="statusId" name="statusId" class="easyui-combobox" />
					</td>
					<td>项目类型:</td>
					<td>
					    <input type="text" id="projecttype" name="projecttype" class="easyui-combobox" />
					</td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="storageScience_list_datagrid" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="storageScience_datagrid_toolbar" style="height:25px">
    	<div class="td_left">
    		<shiro:hasPermission name="storageScience/edit">
            	<a id="storageScience_edit" onclick="storageScience_list_edit(${currentUser.id})" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="storageScience/search">
            	<a id="storageScience_search" onclick="storageScience_list_search(${currentUser.id})" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查看</a>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="storageScience/accept">
            	<a id="storageScience_accept" onclick="storageScience_list_accept(${currentUser.id})" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-usergo'">验收</a>
        	</shiro:hasPermission>
    	</div>
    	<div class="td_right">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="project_list_fileter();">查询</a>
			<a class="datagrid-btn-separator"></a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="project_list_reset();">清空</a>
    	</div>
    </div>
</div>


</body>

</html>