<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/equip/audit/equipAudit.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/processTypes.js" charset="utf-8"></script>


<html>
<head>
<title>装备项目审核界面</title>
	<script type="text/javascript">
		//当前页全局监听权限状态
		var operate_status = 0;
	
	    $(function(){
	
	    	$('#successStatus_tag').hide();
			$('#successStatus_context').hide();
	    	
	    	init_equipAudit_list_datagrid('${path}', '${currentUser.id}', '${currentUser.organizationId}', '${currentUser.unitId}');
	    	
	    	$("#logStatus").combobox({
	    		valueField : 'id',
	    		textField : 'itemvalue',	
	    		editable : false,
	    		data : [{'id':'1','itemvalue':'待处理'},{'id':'2','itemvalue':'已处理'}],
	    		onChange : function(newValue,oldValue) {
	    			if('2' == newValue) {
	    				$('#successStatus_tag').show();
	    				$('#successStatus_context').show();
	    			} else {
	    				$('#successStatus_tag').hide();
	    				$('#successStatus_context').hide();
	    			}
	    		}
	    	});
	    	
	    	$("#successStatus").combobox({
	    		valueField : 'id',
	    		textField : 'itemvalue',	
	    		editable : false,
	    		data : [{'id':'1','itemvalue':'通过'},{'id':'2','itemvalue':'不通过'}]
	    	});
	    	
	    	loadUnitByUnitId('${path}','declareUnitId',null);
	    	
	    });
	    
	    
	    function project_list_fileter() {
	    	$('#equipAudit_list_datagrid').datagrid('load', { 
	    		projectNumber : $.trim($('#projectNumber').val()),
	    		projectName : $.trim($('#projectName').val()),
	    		declareUnitId : $('#declareUnitId').combogrid('getValue'),
	    		logStatus : $('#logStatus').combobox('getValue'),
	    		successStatus : $('#successStatus').combobox('getValue'),
	    		projectDeclareTimeBegin : $('#projectDeclareTimeBegin').datebox('getValue'),
	    		projectDeclareTimeEnd : $('#projectDeclareTimeEnd').datebox('getValue'),
	    		logEndtimeBegin : $('#logEndtimeBegin').datebox('getValue'),
	    		logEndtimeEnd : $('#logEndtimeEnd').datebox('getValue'),
	    		id : '${currentUser.id}',
	    		organizationId : '${currentUser.organizationId}',
	    		unitId : '${currentUser.unitId}'
	    	});
	    }
	    
	    function project_list_reset() {
	    	clearTableToolbar('equipAudit_list_form');
	    	$('#successStatus_tag').hide();
			$('#successStatus_context').hide();
	    };
	    
	</script>
</head>


<body>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach" style="height:70px">
		<form id="equipAudit_list_form" method="POST">
			<table>
				<tr>
					<td>项目编码:</td>
					<td>
						<input type="text" id="projectNumber" name="projectNumber" class="easyui-textbox" />
					</td>
					<td>项目名称:</td>
					<td>
						<input type="text" id="projectName" name="projectName" class="easyui-textbox" />
					</td>
					<td>申报单位:</td>
					<td>
						<input type="text" id="declareUnitId" name="declareUnitId" class="easyui-combogrid" />
					</td>
					<td>审核状态:</td>
					<td>
					    <input type="text" id="logStatus" name="logStatus" class="easyui-combobox" />
					</td>
					<td id="successStatus_tag">审核结果:</td>
					<td id="successStatus_context">
					    <input type="text" id="successStatus" name="successStatus" class="easyui-combobox" />
					</td>
				</tr>
				<tr>
					<td>申报日期:</td>
					<td>
						<input type="text" id="projectDeclareTimeBegin" name="projectDeclareTimeBegin" class="easyui-datebox" data-options="editable:false"/>
					</td>
					<td>至</td>
					<td>
						<input type="text" id="projectDeclareTimeEnd" name="projectDeclareTimeEnd" class="easyui-datebox" data-options="editable:false"/>
					</td>
					<td>审核日期:</td>
					<td>
						<input type="text" id="logEndtimeBegin" name="logEndtimeBegin" class="easyui-datebox" data-options="editable:false"/>
					</td>
					<td>至</td>
					<td>
						<input type="text" id="logEndtimeEnd" name="logEndtimeEnd" class="easyui-datebox" data-options="editable:false"/>
					</td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="equipAudit_list_datagrid" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="equipAudit_datagrid_toolbar" style="height:25px">
    	<div class="td_left">
    		<shiro:hasPermission name="equip/equipData">
            	<a onclick="equipData_list_search(${currentUser.id})" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查看</a>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="equip/auditData">
            	<a id="equip_audit" onclick="equipAuditProcess_list_audit(${currentUser.id})" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-useredit'">审核</a>
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