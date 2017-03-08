/** 初始化教育信息table页中的所有控件 */

var prokeyperpath;

var init_prokeyper_table = function(path){
	
	prokeyperpath=path;
	
	$('#prokeyper_table').datagrid({
		url: prokeyperpath + '/prokeyper/find.do',
		border:false,
		pagination:false,
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
		sortName : 'createTime',			//按字段排序
		sortOrder : 'desc',				//倒序
		toolbar:'#prokeyper_toolbar',
		queryParams : {
			scienceId : $('#id').val(),
		},
		columns:[[
			{field:'id',title:'编号',width:100,align:'center',checkbox: true},
			//{field :'scienceid', title : '科技项目编号', width : 100, align : 'center', editor:'textbox'},
			{field:'name',title:'姓名',width:100,align : 'center', editor:'textbox'},
			{field:'officialcapacity',title:'职务/职称',width:100,align : 'center', editor:'textbox'},
			{field:'professbusiness',title:'专业',width:100,align : 'center', editor:'textbox'},
			{field:'sharetask',title:'分担任务',width:100,align : 'center', editor:'textbox'},
			{field:'belongunit',title:'所在单位',width:100,align : 'center', editor:'textbox'},
			{field : 'action', title:'操作', width:80, align:'center',
				formatter:function(value,row,index){
				  if(pagetype!='search'){	
					if (row.editing){
						var s = '<a href="javascript:void(0)" onclick="prokeyper_table_saverow(this)">保存</a> ';
						var c = '<a href="javascript:void(0)" onclick="prokeyper_table_cancelrow(this)">取消</a>';
						return s+c;
					} else {
						var e = '<a href="javascript:void(0)" onclick="prokeyper_table_editrow(this)">编辑</a> ';
						var d = '<a href="javascript:void(0)" onclick="prokeyper_table_deleterow(this)">删除</a>';
						return e+d;
					}
				  }
				}
			}
		]],
		//编辑前事件，可加载行数据问题
		onBeforeEdit:function(index,row) {
			row.editing = true;
			prokeyper_table_updateActions(index, row);
			$('#prokeyper_table').datagrid('refreshRow', index);
		},
		//编辑事件完成，调用此处，可向后台传递参数
		onAfterEdit:function(index,row) {
			row.editing = false;
			prokeyper_table_updateActions(index, row);
		},
		//取消编辑事件 -- 后置任务
		onCancelEdit:function(index,row){
			row.editing = false;
			if(row.isInsert) {
				$('#prokeyper_table').datagrid("deleteRow", index);
			}
			prokeyper_table_updateActions(index, row);
		}
	});
	
};

function prokeyper_table_updateActions(index, row){		
	$('#prokeyper_table').datagrid('updateRow',{
		index: index,
		row: {
			//targetNameName : row.tempTargetName,
			//targetTypeName : row.tempTargetType
		}
	});
	
}

//获取当前选择行
function prokeyper_table_getRowIndex(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

//编辑事件
function prokeyper_table_editrow(target){
	$('#prokeyper_table').datagrid('beginEdit', prokeyper_table_getRowIndex(target));
}

//取消行
function prokeyper_table_cancelrow(target){
	$('#prokeyper_table').datagrid('cancelEdit', prokeyper_table_getRowIndex(target));
}

//新增行事件
function prokeyper_table_insert(){
	
	if($('#id').val()=="")
		top.$.messager.alert('系统提示',"请先保存上部分的科技项目信息", 'warning');
		
	var row = $('#prokeyper_table').datagrid('getSelected');
	var index = 0;
	if (row){
		index = $('#prokeyper_table').datagrid('getRowIndex', row);
		if(index<0) //这里改了应该对了
			index=0;
	} else {
		index = 0;
	}
	$('#prokeyper_table').datagrid('insertRow', {
		index: index, 
		row:{
			testDataId : $('#id').val(),
			isInsert : true,
			editing : true
		}
	});
	$('#prokeyper_table').datagrid('selectRow',index);
	$('#prokeyper_table').datagrid('beginEdit',index);
}

//保存
 function prokeyper_table_saverow(target){
	 
	var index = prokeyper_table_getRowIndex(target);
	var row = $('#prokeyper_table').datagrid('getData').rows[index];
	
	$('#prokeyper_table').datagrid('endEdit', index);	
	$('#prokeyper_table').datagrid('refreshRow', index);
	
	$.ajax({
		type : 'POST',
		url : prokeyperpath+'/prokeyper/saveOrUpdate.do',
		data : {id:row.id,scienceId:$('#id').val(),name:row.name,officialcapacity:row.officialcapacity,professbusiness:row.professbusiness,sharetask:row.sharetask,belongunit:row.belongunit},
		beforeSend : function() {
			$.messager.progress({
				text : '正在保存中...'
			});
		},
		success : function(data) {
			if(row.isInsert == true) {
				row.isInsert = false;
			}
            row.id=data.id;
			$.messager.progress('close');
            scrollfoucs();
		}
	}); 
	 
}

function  prokeyper_table_deleterow(target){
	$.messager.confirm('警告','确认删除该条数据?',function(r){
		if (!r){
			return false;
		}
		var index = prokeyper_table_getRowIndex(target);
		var row = $('#prokeyper_table').datagrid('getData').rows[index];
		$.ajax({
			type : 'POST',
			url : prokeyperpath+'/prokeyper/delete.do',
			data : {id:row.id},
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
					$('#prokeyper_table').datagrid('deleteRow', index);
					$('#prokeyper_table').datagrid('refreshRow', index);//我加
				}else {
					$.messager.alert('删除失败！', '未知错误，请联系管理员！', 'warning');
				}
			}
		});
		
	});
}