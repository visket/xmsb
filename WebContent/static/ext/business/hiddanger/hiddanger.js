var hiddanger_path;
var init_hiddanger_list_datagrid = function(path){
	hiddanger_path=path;
	$('#hiddanger_list_datagrid').datagrid({
		url: path+'/hiddanger/find',
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
		toolbar:'#hiddanger_datagrid_toolbar',
		columns : [[
		 {field : 'id', title :'id', width : 50, checkbox: true},
		 {field : 'applycode', title:'项目编码', width : 80, align : 'center',sortable:true},
		 {field : 'applyname', title:'项目名称', width : 80, align : 'center',sortable:true},
		 {field : 'unitName', title:'申报单位', width:50, align:'center'},
		 {field : 'projectcontacts', title:'项目联系人', width:50, align:'center'},
		 {field : 'phone', title:'联系电话', width : 80, align : 'center',sortable:true},
		 {field : 'applytime', title : '填报日期', width : 50, align : 'center',sortable:true},
		 {field : 'statusName', title : '项目状态', width : 50, align : 'center',sortable:true},
		 {field : 'reviewstatusName', title : '最后流程状态', width : 50, align : 'center',sortable:true}
	   ]],
	   onSelect : function(index, row) {
		   if(row.statusId == Project_type.tbz || row.statusId == Project_type.th) {
			   $('#hiddanger_edit').show();
			   $('#hiddanger_search').show();
			   $('#hiddanger_declare').show();
			   $('#hiddanger_del').show();
		   } else {
			   $('#hiddanger_search').show();
			   $('#hiddanger_edit').hide();
			   $('#hiddanger_declare').hide();
			   $('#hiddanger_del').hide();
		   }
	   }
   });	
};

function hiddanger_list_add(userId, unitId) {

	var str ="<div><iframe id='hiddanger_list_add' src='"+hiddanger_path+"/hiddanger/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbyscience('hiddanger_list_add', str, '隐患项目信息详情', 1080, 640, 'add', 'hiddanger_list_datagrid', null);

   var thisDate = new Date().getFullYear();
   
   $.ajax({
		url: hiddanger_path+'/hiddanger/isapply',
		async : false,
		type: 'POST',
		data : {
			unitId : unitId,
			applytimeBegin : thisDate + "-01-01",
			applytimeEnd : thisDate + "-12-31"
		},
		beforeSend : function() {
		},
		success: function (data) {	
			top.$.messager.progress('close');
			if(data.success) {
				var str ="<div><iframe id='hiddanger_list_add' src='"+hiddanger_path+"/hiddanger/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
				loaddialogbyscience('hiddanger_list_add', str, '隐患项目信息详情', 1080, 640, 'add', 'hiddanger_list_datagrid', null);
			}else{
				top.$.messager.alert('系统提示',data.msg, 'warning');
			}
		},
		error: function (xhr) {
		}
	  });

};


  function hiddanger_list_edit(pagetype){
	var rows = $('#hiddanger_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
		return false;
	}else{
		var str ="<div><iframe id='hiddanger_list_edit' src='"+hiddanger_path+"/hiddanger/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbyscience('hiddanger_list_edit', str, '隐患项目信息详情', 1080, 640, pagetype, 'hiddanger_list_datagrid', rows[0]);
	}
}



 function hiddanger_list_declare(userId) {
	var rows = $('#hiddanger_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('系统提示！', '必须且选定一条数据！', 'warning');
		return false;
	}
	
	
	top.$.messager.confirm('确认', '是否发起项目申报？', function(b) {
        if (b) {
            progressLoad();
            $.post(hiddanger_path + '/hiddangerAudit/declare', {
                projectId:rows[0].id, declareUnitId:rows[0].unitId, declarerId:userId, 
                processId:rows[0].processId, logId:rows[0].logId
            }, function(result) {
                if (result.success) {
                	top.$.messager.show({title:"系统提示", msg:result.msg, timeout:5000, showType:'slide' });
                	cleanSelect('hiddanger_list_datagrid');
        			flashTable('hiddanger_list_datagrid');
                } else {
                	top.$.messager.alert({title:"系统提示", msg:result.msg, timeout:5000, showType:'slide' });
                }
                progressClose();
            }, 'JSON');
        }
    });
}

var hiddanger_list_delete=function() {
	var rows = $('#hiddanger_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('系统提示！', '必须且选定一条数据！', 'warning');
		return false;
	}
	if(rows[0].statusId != Project_type.tbz && rows[0].statusId != Project_type.sbz){
		top.$.messager.alert('系统提示！', '非填报中与申报中的数据不能删除！', 'warning');
		return false;
	}
	deleteNoteById('hiddanger_list_datagrid', hiddanger_path+"/hiddanger/delete", '确定删除所选记录?');
}