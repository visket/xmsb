var expertreview_path;
var init_expertreview_list_datagrid = function(path,unitId,userId){
	expertreview_path=path;
//debugger;
	$('#expertreview_list_datagrid').datagrid({
		url: path+'/expertreview/find',
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
		toolbar:'#expertreview_datagrid_toolbar',
		queryParams : {
			unitId : unitId,
			id : userId,
		},
		columns : [[
		 {field : 'id', title :'id', width : 50, checkbox: true},
		 {field : 'projectName', title:'项目名', width : 80, align : 'center',sortable:true},
		 {field : 'declareUnitId', title:'申报单位', width : 80, align : 'center',sortable:true},
		 {field : 'projectDeclareTime', title : '申报日期', width : 50, align : 'center',sortable:true},
		 {field : 'projectTypeId', title:'项目类型', width:50, align:'center'}
		/* {field : 'gradeName', title:'级别', width:50, align:'center'},
		 {field : 'internalorg', title : '内设机构数量', width : 50, align : 'center',sortable:true},
		 {field : 'compile', title : '编制人数', width : 50, align : 'center',sortable:true},
		 {field : 'reviewstatusName', title:'审核状态', width:50, align:'center'}*/
	   ]]	   
	   /*onSelect : function(index, row) {
		   if(row.statusId == Project_type.tbz || row.statusId == Project_type.th) {
			   $('#expertreview_edit').show();
			   $('#expertreview_search').show();
			   $('#expertreview_declare').show();
			   $('#expertreview_del').show();
		   } else {
			   $('#expertreview_search').show();
			   $('#expertreview_edit').hide();
			   $('#expertreview_declare').hide();
			   $('#expertreview_del').hide();
		   }
	   }*/
   });
};

/*function expertreview_list_add() {
	var str ="<div><iframe id='expertreview_list_add' src='"+expertreview_path+"/expertreview/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbyexpertreview('expertreview_list_add', str, '装备项目信息详情', 800, 580, 'add', 'expertreview_list_datagrid', null);
};

function expertreview_list_edit(pagetype){
	var rows = $('#expertreview_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
		return false;
	}else{
		var str ="<div><iframe id='expertreview_list_edit' src='"+expertreview_path+"/expertreview/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbyexpertreview('expertreview_list_edit', str, '装备项目信息详情', 800, 580, pagetype, 'expertreview_list_datagrid', rows[0]);
	}
}

function expertreview_list_declare(userId) {
	var rows = $('#expertreview_list_datagrid').datagrid("getSelections");
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
            $.post(expertreview_path + '/expertreview/declare', {
                projectId:rows[0].id, declareUnitId:rows[0].unitId, declarerId:userId, 
                processId:rows[0].processId, logId:rows[0].logId
            }, function(result) {
                if (result.success) {
                	top.$.messager.show({title:"系统提示", msg:result.msg, timeout:5000, showType:'slide' });
                	$('#expertreview_list_datagrid').datagrid("reload");
                } else {
                	top.$.messager.alert({title:"系统提示", msg:result.msg, timeout:5000, showType:'slide' });
                }
                progressClose();
            }, 'JSON');
        }
    });
}

var expertreview_list_delete=function() {
	deleteNoteById('expertreview_list_datagrid', expertreview_path+"/expertreview/delete", '确定删除所选记录?');
}*/