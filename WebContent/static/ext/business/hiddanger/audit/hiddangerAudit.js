var hiddanger_path;
var init_hiddangerAudit_list_datagrid = function(path, userId, organizationId, unitId) {
	hiddanger_path=path;
	$('#hiddangerAudit_list_datagrid').datagrid({
		url: path+'/hiddangerAudit/auditData',
		fit:true,
		border:false,
		pagination:true,
		pagePosition:'bottom',
		idField:'logId',
		pageSize:30,
		pageList: [30,50,100],
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		nowrap:true, 
		rownumbers : true, //显示行号
		showfooter :true,	//显示行尾
		pageNumber:1,
		fitColumns: true,
		sortName : 'logStarttime',			//按字段排序
		sortOrder : 'desc',				//倒序
		toolbar:'#hiddangerAudit_datagrid_toolbar',
		queryParams : {
			id : userId,
			organizationId : organizationId,
			unitId : unitId
		}, 
		columns : [[
			{field:'logId', title :'logId', width : 50, checkbox: true},
			{field:'projectNumber', title:'项目编号', width : '10%', align : 'center',sortable:true},
			{field:'projectName', title:'项目名称', width : '10%', align : 'center',sortable:true},
			{field:'declareUnitName', title : '申报单位', width : '13%', align : 'center',sortable : true},
			{field:'projectDeclareTime', title : '申报日期', width : '8%', align : 'center',sortable : true},
			{field:'projectContacts', title : '项目联系人', width : '6%', align : 'center',sortable : true},
			{field:'projectPhone', title : '联系电话', width : '8%', align : 'center',sortable : true},
			{field:'areaName', title : '所属区域', width : '5%', align : 'center',sortable : true},
			{field:'approveUnitName', title : '当前审核单位', width : '8%', align : 'center',sortable : true},
			{field:'actualApproverName', title : '实际审核人', width : '8%', align : 'center',sortable : true},
			{field:'logStatusName', title : '本级审核状态', width : '8%', align : 'center',sortable : true},
			{field:'projectReviewStatusName', title : '最新审核状态', width : '8%', align : 'center',sortable : true},
			//{field:'logEndtime', title : '审核日期', width : '8%', align : 'center',sortable : true},
			{hidden:true,field:'projectId', title:'项目ID', width:50, align:'center'}
		]],
		onLoadSuccess : function(data) {
			   top.overall_auditStatus = 0;
			   top.overall_disponseStatus = 0;
		},
		onSelect : function(index, row) {
		   if(Process_type.dsl == row.logStatusId || Process_type.ysl == row.logStatusId) {
			   if(Process_type.sjsp == row.projectReviewStatus ||
					   Process_type.dffcs == row.projectReviewStatus) { //市级审批-分发处室
				   $('#hiddanger_office').show();
				   $('#hiddanger_expert').hide();
				   $('#hiddanger_audit').hide();
				   operate_status = 'office_search';
			   } else if(Process_type.cssp == row.projectReviewStatus) { //处室审批-分发专家
				   $('#hiddanger_office').hide();
				   $('#hiddanger_expert').show();
				   $('#hiddanger_audit').hide();
				   operate_status = 'expert_search';
			   } else { //审核
				   $('#hiddanger_office').hide();
				   $('#hiddanger_expert').hide();
				   $('#hiddanger_audit').show();
				   operate_status = 'audit_search';
			   }
		   } else {
			   $('#hiddanger_office').hide();
			   $('#hiddanger_expert').hide();
			   $('#hiddanger_audit').hide();
			   operate_status = 'search';
		   }
		}
	});
};


/**
 * 项目审核-关联项目详情
 * @returns {Boolean}
 */
function hiddangerData_list_search(userId) {
	var rows = $('#hiddangerAudit_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('系统提示', '必须且选定一条数据！', 'warning');
		return false;
	}
	
	top.overall_auditStatus = 0;
	top.overall_disponseStatus = 0;
	
	//if(rows[0].logStatusId == Process_type.dsl && rows[0].actualApproverId == null) rows[0].actualApproverId = userId;
	
	var str ="<div><iframe id='hiddangerData_list_search' src='"+hiddanger_path
		+"/hiddangerAudit/auditSearch?projectId="+rows[0].projectId
		+"&&actualApproverId="+userId
		+"&&logId="+rows[0].logId
		+"&&logStatusId="+rows[0].logStatusId
		+"' width='100%' height='100%' style='border:0'></iframe></div>";
	
	loaddialogbyscienceaudit('hiddangerData_list_search', str, '隐患项目信息详情', 800, 620, operate_status, 'hiddangerAudit_list_datagrid', rows[0]);
};


/**
 * 项目审核
 * @returns {Boolean}
 */
function hiddangerAuditProcess_list_audit(userId) {
	var rows = $('#hiddangerAudit_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('系统提示', '必须且选定一条数据！', 'warning');
		return false;
	}
	//校验身份
	var type = false;
	if(rows[0].logStatusId != Process_type.dsl && rows[0].logStatusId != Process_type.ysl)
		type = true;
	
	if(type && rows[0].actualApproverId != userId) {
		top.$.messager.alert('系统提示', '您没有审核权限！', 'warning');
		return false;
	}
	
	top.overall_auditStatus = 0;
	top.overall_disponseStatus = 0;
	
	var type = operate_status == 'search' ? 'default' : 'submit';
	
	var str ="<div><iframe id='hiddangerAuditProcess_audit' src='"+hiddanger_path
		+"/hiddangerAudit/beforeAudit?projectId="+rows[0].projectId
		+"&&actualApproverId="+userId
		+"&&logId="+rows[0].logId
		+"&&logStatusId="+rows[0].logStatusId
		+"&&operateType=audit"
		+"' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbyscienceaudit('hiddangerAuditProcess_audit', str, rows[0].projectName + '-审核操作', 600, 270, type, 'hiddangerAudit_list_datagrid', rows[0]);

};


/**
 * 科技项目-分发处室
 * @param userId
 */
function hiddangerAuditProcess_list_disponseOffice(userId) {
	var rows = $('#hiddangerAudit_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('系统提示', '必须且选定一条数据！', 'warning');
		return false;
	}
	
	//校验身份
	var type = false;
	if(rows[0].logStatusId != Process_type.dsl && rows[0].logStatusId != Process_type.ysl)
		type = true;
	
	if(type && rows[0].actualApproverId != userId) {
		top.$.messager.alert('系统提示', '您没有分发权限！', 'warning');
		return false;
	}
	
	top.overall_auditStatus = 0;
	top.overall_disponseStatus = 0;
	
	var type = operate_status == 'search' ? 'default' : 'submit';
	
	var str ="<div><iframe id='hiddangerAuditProcess_list_disponseOffice' src='"+hiddanger_path
		+"/hiddangerAudit/beforeAudit?projectId="+rows[0].projectId
		+"&&actualApproverId="+userId
		+"&&logId="+rows[0].logId
		+"&&logStatusId="+rows[0].logStatusId
		+"&&operateType=office"
		+"' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbyscienceaudit('hiddangerAuditProcess_list_disponseOffice', str, rows[0].projectName + '-分发处室', 600, 350, type, 'hiddangerAudit_list_datagrid', rows[0]);
	
};

/**
 * 科技项目-分发专家
 * @param userId
 */
function hiddangerAuditProcess_list_disponseExpert(userId) {
	var rows = $('#hiddangerAudit_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('系统提示', '必须且选定一条数据！', 'warning');
		return false;
	}
	
	//校验身份
	var type = false;
	if(rows[0].logStatusId != Process_type.dsl && rows[0].logStatusId != Process_type.ysl)
		type = true;
	
	if(type && rows[0].actualApproverId != userId) {
		top.$.messager.alert('系统提示', '您没有分发权限！', 'warning');
		return false;
	}
	
	top.overall_auditStatus = 0;
	top.overall_disponseStatus = 0;
	
	
	var type = operate_status == 'search' ? 'default' : 'submit';
	
	var str ="<div><iframe id='hiddangerAuditProcess_list_disponseOffice' src='"+hiddanger_path
		+"/hiddangerAudit/beforeAudit?projectId="+rows[0].projectId
		+"&&actualApproverId="+userId
		+"&&logId="+rows[0].logId
		+"&&logStatusId="+rows[0].logStatusId
		+"&&operateType=expert"
		+"' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbyscienceaudit('hiddangerAuditProcess_list_disponseOffice', str, rows[0].projectName + '-分发专家', 600, 350, type, 'hiddangerAudit_list_datagrid', rows[0]);
	
};



