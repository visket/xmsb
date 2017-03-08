var project_path;
var init_projectAudit_list_datagrid = function(path){
	project_path=path;
	$('#projectAudit_list_datagrid').datagrid({
		url: path+'/projectAudit/find',
		fit:true,
		border:false,
		pagination:true,
		pagePosition:'bottom',
		idField:'id',
		pageSize:26,
		pageList: [26,30,50],
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:false,
		nowrap:true, 
		rownumbers : true, //显示行号
		showfooter :true,	//显示行尾
		pageNumber:1,
		fitColumns: true,
		sortName : 'logStarttime',			//按字段排序
		sortOrder : 'desc',				//倒序
		toolbar:'#projectAudit_datagrid_toolbar',
		columns : [[
		 {field : 'id', title :'id', width : 50, checkbox: true},
		 {field : 'number', title:'项目编号', width : 80, align : 'center',sortable:true},
		 {field : 'name', title:'项目名称', width : 80, align : 'center',sortable:true},
		 {field : 'typename', title : '项目类型', width : 50, align : 'center',sortable : true},
		 {field : 'statusname', title : '项目状态', width : 50, align : 'center',sortable:true},
		 {field : 'areaname', title : '所属地区', width : 50, align : 'center',sortable:true},
		 {field : 'cost', title : '项目总造价', width : 60, align : 'center',sortable:true},
		 {field : 'companyname', title : '关联单位', width : 60, align : 'center',sortable:true},
		 {field : 'principaltorname', title : '项目负责人', width : 60, align : 'center',sortable:true},
		 {field : 'createtime', title : '创建时间', width : 50, align : 'center',sortable:true},
		 {field : 'lastupdatetime', title:'最后修改时间', width:50, align:'center',sortable:true},
		 {hidden:true,field:'processId', title:'审批流程ID', width:50, align:'center'},
		 {hidden:true,field:'starttime', title:'项目开始时间', width:50, align:'center'},
		 {hidden:true,field:'endtime', title:'项目结束时间', width:50, align:'center'},
		 {hidden:true,field:'classifyId', title:'项目分类', width:50, align:'center'},
		 {hidden:true,field:'classesId', title:'项目类别', width:50, align:'center'}
		 
	   ]] 
   });
};

function project_list_fileter() {
   $('#project_list_datagrid').datagrid('load', { 
	    number : $.trim($('#number').val()),
	    name : $.trim($('#name').val()),
	    starttime : $('#starttime').datebox('getValue'),
	    endtime : $('#endtime').datebox('getValue'),
	    typeId : $('#typeId').combobox('getValue'),
	    areaId : $('#areaId').combobox('getValue'),
	    createtime : $('#createtime').datebox('getValue'),
	    lastupdatetime : $('#lastupdatetime').datebox('getValue'),
	});

};


function project_list_reset() {
	clearTableToolbar('project_list_form');
};


function project_list_add() {
	var str ="<div><iframe id='project_list_add' src='"+project_path+"/project/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbyproject('project_list_add', str, '项目基本信息详情', 640, 500, 'add', 'project_list_datagrid', null);
};

function project_list_edit(pagetype) {
	var rows = $('#project_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
		return false;
	} else {
		var str ="<div><iframe id='project_list_edit' src='"+project_path+"/project/addNew' width='100%' height='100%' style='border:0'></iframe></div>";	
		loaddialogbyproject('project_list_edit', str, '项目基本信息详情', 640, 500, pagetype, 'project_list_datagrid', rows[0]);
	}
};

function project_list_del() {
	deleteNoteById('project_list_datagrid', project_path+"/project/deletes", '确定删除所选记录?');
};


function project_list_declare(userId) {
	var rows = $('#project_list_datagrid').datagrid("getSelections");
	if(rows.length != 1){
		top.$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
		return false;
	}
	
	top.$.messager.confirm('确认', '是否发起项目申报？', function(b) {
        if (b) {
            progressLoad();
            $.post(project_path + '/project/projectDeclare', {
                id:rows[0].id, unitId:rows[0].unitId, creatorId:userId
            }, function(result) {
                if (result.success) {
                	$.messager.show({title:"系统提示", msg:result.msg, timeout:5000, showType:'slide' });
                	$('#project_list_datagrid').datagrid("reload");
                	//刷新前端显示
                	//rows[0].statusname = result.obj;
                	//$('#project_list_datagrid').datagrid("refreshRow", $('#project_list_datagrid').datagrid("getRowIndex", rows[0]));
                }
                progressClose();
            }, 'JSON');
        }
    });
	
}







