var equip_path;
var init_equipAuditProcess_list_datagrid = function(path, projectId){
	equip_path=path;
	$('#equipAuditProcess_list_datagrid').datagrid({
		url: path+'/equip/auditProcess',
		fit:true,
		border:false,
		pagination:true,
		pagePosition:'bottom',
		idField:'logId',
		pageSize:10,
		pageList: [10,20,30],
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:false,
		nowrap:true, 
		rownumbers : true, //显示行号
		showfooter :true,	//显示行尾
		pageNumber:1,
		fitColumns: true,
		toolbar:'#equipAuditProcess_datagrid_toolbar',
		queryParams : {
			projectId : projectId
		}, 
		columns : [[
		 {field:'logId', title :'logId', width : 50, checkbox: true},
		 {field:'actualApproveUnitName', title : '审核单位', width : 50, align : 'center',
			 formatter :  function(value,row,index) {
				 if(row.actualApproveUnitId == null || row.actualApproveUnitId == '') return row.approveUnitName;
				 else return value;
			 }},
		 {field:'actualApproverName', title : '实际审核人', width : 50, align : 'center'},
		 {field:'logEndtime', title : '审核时间', width : 50, align : 'center'},
		 {field:'logStatusName', title : '审核状态', width : 50, align : 'center'},
		 {field:'logReviewcontent', title : '审核内容', width : 50, align : 'center'},
		 {hidden:true,field:'projectId', title:'项目ID', width:50, align:'center'}
	   ]] 
   });
};

function equipAuditProcess_audit(row) {
	var str ="<div><iframe id='equipAuditProcess_audit' src='"+equip_path+"/equip/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbyproject('equipAuditProcess_audit', str, row.projectName + '-审核操作', 600, 400, 'audit', 'equipAuditProcess_list_datagrid', null);
};





