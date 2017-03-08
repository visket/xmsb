var equip_path;
var init_equip_list_datagrid = function(path){
	equip_path=path;

	$('#equip_list_datagrid').datagrid({
		url: path+'/equip/find',
		fit:true,
		border:false,
		pagination:true,
		pagePosition:'bottom',
		idField:'id',
		pageSize:26,
		pageList: [26,30,50],
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
		toolbar:'#equip_datagrid_toolbar',
		columns : [[
		 {field : 'id', title :'id', width : 50, checkbox: true},
		 {field : 'applycode', title:'申请编码', width : 80, align : 'center',sortable:true},
		 {field : 'applyname', title:'申请名称', width : 80, align : 'center',sortable:true},
		 //{field : 'applicantName', title : '申请人', width : 50, align : 'center',sortable : true},
		 {field : 'applytime', title : '申请时间', width : 50, align : 'center',sortable:true},
		 //{field : 'statusName', title : '项目状态', width : 50, align : 'center',sortable:true},
		 {field : 'unitName', title:'申请单位', width:50, align:'center'},
		 {field : 'gradeName', title:'级别', width:50, align:'center'},
		 {field : 'internalorg', title : '内设机构数量', width : 50, align : 'center',sortable:true},
		 {field : 'compile', title : '编制人数', width : 50, align : 'center',sortable:true},
		 {field : 'reviewstatusName', title:'审核状态', width:50, align:'center'}
	   ]],
	   
	   onSelect : function(index, row) {
		   if(row.statusId == Project_type.tbz || row.statusId == Project_type.th) {
			   $('#equip_edit').show();
			   $('#equip_search').show();
			   $('#equip_declare').show();
			   $('#equip_del').show();
		   } else {
			   $('#equip_search').show();
			   $('#equip_edit').hide();
			   $('#equip_declare').hide();
			   $('#equip_del').hide();
		   }
	   }
   });
};

function equip_list_add() {
	var str ="<div><iframe id='equip_list_add' src='"+equip_path+"/equip/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbyequip('equip_list_add', str, '装备项目信息详情', 800, 580, 'add', 'equip_list_datagrid', null);
};

function equip_list_edit(pagetype){
	var rows = $('#equip_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
		return false;
	}else{
		var str ="<div><iframe id='equip_list_edit' src='"+equip_path+"/equip/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbyequip('equip_list_edit', str, '装备项目信息详情', 800, 580, pagetype, 'equip_list_datagrid', rows[0]);
	}
}

function equip_list_declare(userId) {
	var rows = $('#equip_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('系统提示！', '必须且选定一条数据！', 'warning');
		return false;
	}
	
	if(rows[0].applicant != userId) {
		top.$.messager.alert('系统提示', '您没有审核权限！', 'warning');
		return false;
	}
	
	top.$.messager.confirm('确认', '是否发起项目申报？', function(b) {
        if (b) {
            progressLoad();
            $.post(equip_path + '/equip/declare', {
                projectId:rows[0].id, declareUnitId:rows[0].unitId, declarerId:userId, 
                processId:rows[0].processId, logId:rows[0].logId
            }, function(result) {
                if (result.success) {
                	top.$.messager.show({title:"系统提示", msg:result.msg, timeout:5000, showType:'slide' });
                	$('#equip_list_datagrid').datagrid("reload");
                } else {
                	top.$.messager.alert({title:"系统提示", msg:result.msg, timeout:5000, showType:'slide' });
                }
                progressClose();
            }, 'JSON');
        }
    });
}

var equip_list_delete=function() {
	deleteNoteById('equip_list_datagrid', equip_path+"/equip/delete", '确定删除所选记录?');
}