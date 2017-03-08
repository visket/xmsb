var storagescience_path;
var init_storageScience_list_datagrid = function(path, userId, organizationId, unitId) {
	storagescience_path=path;
	$('#storageScience_list_datagrid').datagrid({
		url: path+'/storageScience/find',
		fit:true,
		border:false,
		pagination:true,
		pagePosition:'bottom',
		idField:'id',
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
		sortName : 'lastupdatetime',			//按字段排序
		sortOrder : 'desc',				//倒序
		toolbar:'#storageScience_datagrid_toolbar',
		columns : [[
			{field:'id', title :'id', width : 50, checkbox: true},
			{field:'projectNumber', title:'项目编号', width : '20%', align : 'center',sortable:true},
			{field:'projectName', title:'项目名称', width : '20%', align : 'center',sortable:true},
			{field:'projecttype', title : '项目类型', width : '8%', align : 'center',sortable : true,
				formatter: function(value) {
					if('01' == value) {
						return "研发类";
					} else if('02' == value) {
						return "推广应用类";
					} else {
						return "";
					}
				}},
			{field:'unitName', title : '申报单位', width : '20%', align : 'center',sortable : true},
			{field:'projectDeclareTime', title : '申报日期', width : '10%', align : 'center',sortable : true},
			{field:'statusName', title : '入库状态', width : '10%', align : 'center',sortable : true},
			{field:'accepttime', title : '验收日期', width : '10%', align : 'center',sortable : true},
			{hidden:true,field:'projectId', title:'项目ID', width:50, align:'center'}
		]],
		onSelect : function(index, row) {
		   if(Storage_type.xz == row.statusId || Storage_type.tj == row.statusId) {
			   $('#storageScience_edit').show();
			   $('#storageScience_accept').hide();
			   operate_status = 'edit';
		   } else {
			   $('#storageScience_edit').hide();
			   $('#storageScience_accept').show();
			   operate_status = 'accept';
		   }
		}
	});
};


/**
 * 科技项目执行-编辑
 * @returns {Boolean}
 */
function storageScience_list_edit(userId) {
	var rows = $('#storageScience_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('系统提示', '必须且选定一条数据！', 'warning');
		return false;
	}
	
	//if(rows[0].logStatusId == Process_type.dsl && rows[0].actualApproverId == null) rows[0].actualApproverId = userId;
	
	var str ="<div><iframe id='storageScience_list_edit' src='"+storagescience_path
		+"/storageScience/edit' width='100%' height='100%' style='border:0'></iframe></div>";
	
	loaddialogbyscienceaudit('storageScience_list_edit', str, '科技项目执行详情', 700, 580, 'submit', 'storageScience_list_datagrid', rows[0]);
};


/**
 * 科技项目审核
 * @returns {Boolean}
 */
function storageScience_list_search(userId) {
	var rows = $('#scienceAudit_list_datagrid').datagrid("getSelections");
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
	
	var type = operate_status == 'search' ? 'default' : 'submit';
	
	var str ="<div><iframe id='scienceAuditProcess_audit' src='"+storagescience_path
		+"/science/beforeAudit?projectId="+rows[0].projectId
		+"&&actualApproverId="+userId
		+"&&logId="+rows[0].logId
		+"&&logStatusId="+rows[0].logStatusId
		+"&&operateType=audit"
		+"' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbyscienceaudit('scienceAuditProcess_audit', str, rows[0].projectName + '-审核操作', 600, 270, type, 'scienceAudit_list_datagrid', rows[0]);

};

