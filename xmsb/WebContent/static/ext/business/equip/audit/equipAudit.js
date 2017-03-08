var equip_path;
var init_equipAudit_list_datagrid = function(path, userId, organizationId, unitId) {
	equip_path=path;
	$('#equipAudit_list_datagrid').datagrid({
		url: path+'/equip/auditData',
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
		sortName : 'logEndtime',			//按字段排序
		sortOrder : 'desc',				//倒序
		toolbar:'#equipAudit_datagrid_toolbar',
		queryParams : {
			id : userId,
			organizationId : organizationId,
			unitId : unitId
		}, 
		columns : [[
		 {field:'logId', title :'logId', width : 50, checkbox: true},
		 {field:'projectNumber', title:'项目编号', width : 80, align : 'center',sortable:true},
		 {field:'projectName', title:'项目名称', width : 80, align : 'center',sortable:true},
		 {field:'declareUnitName', title : '申报单位', width : 50, align : 'center',sortable : true},
		 {field:'projectDeclareTime', title : '申报日期', width : 50, align : 'center',sortable : true},
		 {field:'areaName', title : '所属区域', width : 50, align : 'center',sortable : true},
		 {field:'approveUnitName', title : '当前审核单位', width : 50, align : 'center',sortable : true},
		 {field:'actualApproverName', title : '实际审核人', width : 50, align : 'center',sortable : true},
		 {field:'projectReviewStatusName', title : '审核状态', width : 50, align : 'center',sortable : true},
		 {field:'logEndtime', title : '审核日期', width : 50, align : 'center',sortable : true},
		 {hidden:true,field:'projectId', title:'项目ID', width:50, align:'center'}
	   ]],
	   onLoadSuccess : function(data) {
		   top.overall_auditStatus = 0;
		   top.overall_disponseStatus = 0;
	   },
	   onSelect : function(index, row) {
		   if(Process_type.dsl == row.logStatusId || Process_type.ysl == row.logStatusId) {
			   operate_status = 1;
			   $('#equip_audit').show();
		   } else {
			   operate_status = 0;
			   $('#equip_audit').hide();
		   }
	   }
   });
};


/**
 * 装备项目审核-关联项目详情
 * @returns {Boolean}
 */
function equipData_list_search(userId) {
	var rows = $('#equipAudit_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
		return false;
	}
	
	top.overall_auditStatus = 0;
	top.overall_disponseStatus = 0;
	
	//if(rows[0].logStatusId == Process_type.dsl && rows[0].actualApproverId == null) rows[0].actualApproverId = userId;
	
	var str ="<div><iframe id='equipData_list_search' src='"+equip_path
		+"/equip/auditSearch?projectId="+rows[0].projectId
		+"&&actualApproverId="+userId
		+"&&logId="+rows[0].logId
		+"&&logStatusId="+rows[0].logStatusId
		+"' width='100%' height='100%' style='border:0'></iframe></div>";

	var type = operate_status == 0 ? "search" : "audit_search";
	
	loaddialogbyaudit('equipData_list_search', str, '装备项目信息详情', 800, 580, type, 'equipAudit_list_datagrid', rows[0]);
};


/**
 * 装备项目审核-关联项目所有流程信息
 * @returns {Boolean}
 */
function equipAuditProcess_list_audit(userId) {
	var rows = $('#equipAudit_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
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
	
	var type = operate_status == 0 ? 'default' : 'submit';
	
	var str ="<div><iframe id='equipAuditProcess_audit' src='"+equip_path
		+"/equip/beforeAudit?projectId="+rows[0].projectId
		+"&&actualApproverId="+userId
		+"&&logId="+rows[0].logId
		+"&&logStatusId="+rows[0].logStatusId
		+"' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbyaudit('equipAuditProcess_audit', str, rows[0].projectName + '-审核操作', 600, 270, type, 'equipAudit_list_datagrid', rows[0]);
	
	
	
};


