/** 初始 科技项目入库 table页中的 验收人员名单 */
var storagepath_path;

var init_storagePerson_table = function(path){
	storagepath_path=path;
	
	
	$('#storagePerson_table').datagrid({
		url: path + '/storagePerson/find',
		fit:true,
		border:false,
		pagination:true,
		idField:'id',
		pageSize:5,
		pageList: [5,10,20],
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		nowrap:true, 
		rownumbers : true, //显示行号
		showfooter :true,	//显示行尾
		pageNumber:1,
		fitColumns: true,
		sortName : 'lastupdateTime',			//按字段排序
		sortOrder : 'desc',				//倒序
		toolbar:'#storagePerson_toolbar',
		queryParams : {
			storageId : $('#id').val(),
			pageType : $('#pageType').val()
		},
		columns:[[
			{hidden : true, field : 'id', title : '编号',width : 100, align : 'center'},
			{hidden : true, field : 'tempName', title : '临时名称', width : 100, align : 'center'},
			{field : 'name', title : '姓名', width : 100, align : 'center', editor:'textbox',sortable:true},
			{field : 'drillAddress', title : '演习名称或地点', width : 100, align : 'center', editor:'textbox',sortable:true},
			{field : 'drillAddress', title : '演习名称或地点', width : 100, align : 'center', editor:'textbox',sortable:true},
			{field : 'startTime', title : '开始时间',width : 100, align : 'center', editor:'datebox',sortable:true},
			{field : 'endTime', title : '结束时间', width : 100, align : 'center', editor:'datebox',sortable:true},
			{field : 'drillAddress', title : '演习名称或地点', width : 100, align : 'center', editor:'textbox',sortable:true},
			{field : 'jobName', title : '职务', width : 100, align : 'center',sortable:true, 
				formatter : function(value,row,index) {
					return value;
				},
				editor : {
					type:'combobox',
					options:{
						valueField:'id',
						textField:'itemCode',
						url: path+'/standard/dictionary/find.do?itemId=6',
						required:true
					}
				}},
			{field : 'action', title:'操作', width:80, align:'center',
				formatter:function(value,row,index){
					if (row.editing){
						var s = '<a href="#" onclick="storagePerson_table_saverow(this)">保存</a> ';
						var c = '<a href="#" onclick="storagePerson_table_cancelrow(this)">取消</a>';
						return s+c;
					} else {
						var e = '<a href="#" onclick="storagePerson_table_editrow(this)">编辑</a> ';
						var d = '<a href="#" onclick="storagePerson_table_deleterow(this)">删除</a>';
						return e+d;
					}
				}
			}
		]],
		//编辑前事件，可加载行数据问题
		onBeforeEdit:function(index,row) {
			row.editing = true;
			storagePerson_table_updateActions(index, row);
		},
		//编辑事件完成，调用此处，可向后台传递参数
		onAfterEdit:function(index,row) {
			row.editing = false;
			storagePerson_table_updateActions(index, row);
		},
		//取消编辑事件 -- 后置任务
		onCancelEdit:function(index,row){
			row.editing = false;
			if(row.isInsert) {
				$('#storagePerson_table').datagrid("deleteRow", index);
			}
			storagePerson_table_updateActions(index, row);
		}
	});
	
};


function storagePerson_table_updateActions(index, row){		
	$('#storagePerson_table').datagrid('updateRow',{
		index: index,
		row: {
			jobName : row.tempName
		}
	}); 
}

//获取当前选择行
function storagePerson_table_getRowIndex(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

//编辑事件
function storagePerson_table_editrow(target){
	$('#storagePerson_table').datagrid('beginEdit', storagePerson_table_getRowIndex(target));
}

//取消行
function storagePerson_table_cancelrow(target){
	$('#storagePerson_table').datagrid('cancelEdit', storagePerson_table_getRowIndex(target));
}

//新增行事件
function storagePerson_table_insert(){
	var row = $('#storagePerson_table').datagrid('getSelected');
	if (row){
		var index = $('#storagePerson_table').datagrid('getRowIndex', row);
		if(index < 0) index = 0;
	} else {
		index = 0;
	}
	$('#storagePerson_table').datagrid('insertRow', {
		index: index, 
		row:{
			personId : $('#id').val(),
			isInsert : true,
			editing : true
		}
	});
	$('#storagePerson_table').datagrid('selectRow',index);
	$('#storagePerson_table').datagrid('beginEdit',index);
}


//保存
function storagePerson_table_saverow(target){
	var index = storagePerson_table_getRowIndex(target);
	var row = $('#storagePerson_table').datagrid('getData').rows[index];
	
	//验证
	if(!datagrid_validateRow($('#id').val(), 'storagePerson_table', index)) {
		return;
	}
	
	
	//绑定combobox回显的值
	var et = $('#storagePerson_table').datagrid('getEditor', {index:index,field:'jobName'});
	var tempName = $(et.target).combobox('getText');
	var tempId = $(et.target).combobox('getValue');
	
	$('#storagePerson_table').datagrid('endEdit', index);
	row.tempName = tempName;
	if (tempId != null && tempId != undefined && tempId != tempName) {
		row.jobId = tempId;
	}
	
	$.ajax({
		type : 'POST',
		url : storagepath_path+'/drill/base/saveOrUpdate.do',
		data : {id:row.id, personId:row.personId, startStr:row.startTime, endStr:row.endTime,
			drillAddress:row.drillAddress, jobId:row.jobId, jobName:row.jobName},
		beforeSend : function() {
			$.messager.progress({
				text : '正在保存中...'
			});
		},
		success : function(data) {
			if(row.isInsert == true) {
				row.isInsert = false;
			}
			
			$.messager.progress('close');
			flashTableByPerson('storagePerson_table');
		}
	});
}


function storagePerson_table_deleterow(target){
	$.messager.confirm('警告','确认删除该条数据?',function(r){
		if (!r){
			return false;
		}
		var index = storagePerson_table_getRowIndex(target);
		var row = $('#storagePerson_table').datagrid('getData').rows[index];
		$.ajax({
			type : 'POST',
			url : storagepath_path+'/drill/base/delete.do',
			data : {id:row.id, personId:row.personId},
			beforeSend : function() {
				$.messager.progress({
					text : '正在删除中...'
				});
			},
			success : function(data) {
				$.messager.progress('close');
				if (data) { //删除成功
					$.messager.show({
						title : '提示',
						msg : '删除成功！'
					});
					$('#storagePerson_table').datagrid('deleteRow', index);
					flashTableByPerson('storagePerson_table');
				} else {
					$.messager.alert('删除失败！', '未知错误，请联系管理员！', 'warning');
				}
			}
		});
		
	});
}


