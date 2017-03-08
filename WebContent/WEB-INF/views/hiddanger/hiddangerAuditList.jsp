<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/hiddanger/audit/hiddangerAudit.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/processTypes.js" charset="utf-8"></script>


<html>
<head>
<title>隐患项目审核界面</title>
	<script type="text/javascript">
		//当前页全局监听权限状态
		var operate_status = 0;
	
	    $(function(){
	
	    	$('#successStatus_tag').hide();
			$('#successStatus_context').hide();
	    	
	    	init_hiddangerAudit_list_datagrid('${path}', '${currentUser.id}', '${currentUser.organizationId}', '${currentUser.unitId}');
	    	
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
	    	
	    	$("#projectClassifyId").combobox({
	    		valueField : 'id',
	    		textField : 'itemvalue',	
	    		editable : false,
	    		data : [{'id':'1','itemvalue':'研发类'},{'id':'2','itemvalue':'推广应用类'}]
	    	});
	    	
	    	loadUnitByUnitId('${path}','declareUnitId', "DWLB_AJJ");
	    	
	    });
	    
	    
	    function project_list_fileter() {
	    	$('#hiddangerAudit_list_datagrid').datagrid('load', { 
	    		projectNumber : $.trim($('#projectNumber').val()),
	    		projectName : $.trim($('#projectName').val()),
	    		declareUnitId : $('#declareUnitId').combogrid('getValue'),
	    		logStatus : $('#logStatus').combobox('getValue'),
	    		successStatus : $('#successStatus').combobox('getValue'),
	    		projectClassifyId : $('#projectClassifyId').combobox('getValue'),
	    		projectDeclareTimeBegin : $('#projectDeclareTimeBegin').datebox('getValue'),
	    		projectDeclareTimeEnd : $('#projectDeclareTimeEnd').datebox('getValue'),
	    		id : '${currentUser.id}',
	    		organizationId : '${currentUser.organizationId}',
	    		unitId : '${currentUser.unitId}'
	    	});
	    }
	    
	    function project_list_reset() {
	    	clearTableToolbar('hiddangerAudit_list_form');
	    	$('#successStatus_tag').hide();
			$('#successStatus_context').hide();
	    };
	    
	</script>
</head>


<body>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach" style="height:70px">
		<form id="hiddangerAudit_list_form" method="POST">
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
					
				</tr>
				<tr>
					<td>项目类型:</td>
					<td>
					    <input type="text" id="projectClassifyId" name="projectClassifyId" class="easyui-combobox" />
					</td>
					<td>申报日期:</td>
					<td>
						<input type="text" id="projectDeclareTimeBegin" name="projectDeclareTimeBegin" class="easyui-datebox" data-options="editable:false"/>
					</td>
					<td>至</td>
					<td>
						<input type="text" id="projectDeclareTimeEnd" name="projectDeclareTimeEnd" class="easyui-datebox" data-options="editable:false"/>
					</td>
					<td id="successStatus_tag">审核结果:</td>
					<td id="successStatus_context">
					    <input type="text" id="successStatus" name="successStatus" class="easyui-combobox" />
					</td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="hiddangerAudit_list_datagrid" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="hiddangerAudit_datagrid_toolbar" style="height:25px">
    	<div class="td_left">
    		<shiro:hasPermission name="hiddangerAudit/hiddangerData">
            	<a onclick="hiddangerData_list_search(${currentUser.id})" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查看</a>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="hiddangerAudit/audit">
            	<a id="hiddanger_audit" onclick="hiddangerAuditProcess_list_audit(${currentUser.id})" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-useredit'">审核</a>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="hiddangerAudit/disponseOffice">
            	<a id="hiddanger_office" onclick="hiddangerAuditProcess_list_disponseOffice(${currentUser.id})" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-list'">分发处室</a>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="hiddangerAudit/disponseExpert">
            	<a id="hiddanger_expert" onclick="hiddangerAuditProcess_list_disponseExpert(${currentUser.id})" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-usergo'">分发专家</a>
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