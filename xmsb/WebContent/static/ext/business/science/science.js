var science_path;
var init_science_list_datagrid = function(path){
	science_path=path;

	$('#science_list_datagrid').datagrid({
		url: path+'/science/find',
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
		sortName : 'applytime',			//按字段排序
		sortOrder : 'desc',				//倒序
		toolbar:'#science_datagrid_toolbar',
		columns : [[
		 {field : 'id', title :'id', width : 50, checkbox: true},
		 {field : 'applycode', title:'项目编码', width : 80, align : 'center',sortable:true},
		 {field : 'applyname', title:'项目名称', width : 80, align : 'center',sortable:true},
		 {field : 'applytime', title : '申报时间', width : 50, align : 'center',sortable:true},
		 {field : 'statusName', title : '项目状态', width : 50, align : 'center',sortable:true},
		 {field : 'unitName', title:'申报单位', width:50, align:'center'},
		 {field : 'gradeTypeName', title:'级别', width:50, align:'center'},
		 {field : 'reviewstatusName', title:'审核状态', width:50, align:'center'}
	   ]],
	   
	   onSelect : function(index, row) {
		   if(row.statusId == Project_type.tbz || row.statusId == Project_type.th) {
			   $('#science_edit').show();
			   $('#science_search').show();
			   $('#science_declare').show();
			   $('#science_del').show();
		   } else {
			   $('#science_search').show();
			   $('#science_edit').hide();
			   $('#science_declare').hide();
			   $('#science_del').hide();
		   }
	   }
   });
};

function science_list_add(userId, unitId) {
	
	var str ="<div><iframe id='science_list_add' src='"+science_path+"/science/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbyscience('science_list_add', str, '科技项目信息详情', 880, 680, 'add', 'science_list_datagrid', null);
		
};

function science_list_edit(pagetype){
	var rows = $('#science_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
		return false;
	}else{
		var str ="<div><iframe id='science_list_edit' src='"+science_path+"/science/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbyscience('science_list_edit', str, '科技项目信息详情', 880, 680, pagetype, 'science_list_datagrid', rows[0]);
	}
}

function science_list_declare(userId) {
	var rows = $('#science_list_datagrid').datagrid("getSelections");
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
            $.post(science_path + '/science/declare', {
                projectId:rows[0].id, declareUnitId:rows[0].unitId, declarerId:userId, 
                processId:rows[0].processId, logId:rows[0].logId
            }, function(result) {
                if (result.success) {
                	top.$.messager.show({title:"系统提示", msg:result.msg, timeout:5000, showType:'slide' });
                	cleanSelect('science_list_datagrid');
        			flashTable('science_list_datagrid');
                } else {
                	top.$.messager.alert({title:"系统提示", msg:result.msg, timeout:5000, showType:'slide' });
                }
                progressClose();
            }, 'JSON');
        }
    });
}

var science_list_delete=function() {
	var rows = $('#science_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('系统提示！', '必须且选定一条数据！', 'warning');
		return false;
	}
	if(rows[0].statusId != Project_type.tbz && rows[0].statusId != Project_type.sbz){
		top.$.messager.alert('系统提示！', '非填报中与申报中的数据不能删除！', 'warning');
		return false;
	}
	deleteNoteById('science_list_datagrid', science_path+"/science/delete", '确定删除所选记录?');
}