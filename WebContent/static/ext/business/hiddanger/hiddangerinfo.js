/** 初始化教育信息table页中的所有控件 */

var hiddangerinfopath;

var init_hiddangerinfo_table = function(path){
	
	hiddangerinfopath=path;
	
	$('#hiddangerinfo_table').datagrid({
		url: hiddangerinfopath + '/hiddangerinfo/find.do',
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
		toolbar:'#hiddangerinfo_toolbar',
		queryParams : {
			hiddangerId : $('#id').val(),
		},
		columns:[[
			{field:'id',title:'编号',width:50,align:'center',checkbox: true},
			{field:'screegovernName',title:'排查治理状况',width:100,align : 'center',
				formatter : function(value,row,index){
					return value;
				},
				editor:{
					type:'combobox',
					options:{
						valueField:'id',
						textField:'itemvalue',
						url: path+'/item/findByDictionarycode?dictionarycode=PCZLQKJB',
						editable:false
						//required:true
					}
			  }
			},
			{field:'gradeName',title:'级别',width:100,align : 'center', 
				formatter : function(value,row,index){
					return value;
				},
				editor:{
					type:'combobox',
					options:{
						valueField:'id',
						textField:'itemvalue',
						url: path+'/item/findByDictionarycode?dictionarycode=YHJB',
						editable:false
						//required:true
					}
			  }
			},
			{field:'quantity',title:'数量',width:100,align : 'center', editor:'numberbox'},
			{field :'action', title:'操作', width:80, align:'center',
				formatter:function(value,row,index){
				  if(pagetype!='search'){
					if (row.editing){
						var s = '<a href="javascript:void(0)" onclick="hiddangerinfo_table_saverow(this)">保存</a> ';
						var c = '<a href="javascript:void(0)" onclick="hiddangerinfo_table_cancelrow(this)">取消</a>';
						return s+c;
					} else {
						var e = '<a href="javascript:void(0)" onclick="hiddangerinfo_table_editrow(this)">编辑</a> ';
						var d = '<a href="javascript:void(0)" onclick="hiddangerinfo_table_deleterow(this)">删除</a>';
						return e+d;
					}
				  }
				}
			}
		]],
		//编辑前事件，可加载行数据问题
		onBeforeEdit:function(index,row) {
			row.editing = true;
			hiddangerinfo_table_updateActions(index, row);
			$('#hiddangerinfo_table').datagrid('refreshRow', index);
		},
		//编辑事件完成，调用此处，可向后台传递参数
		onAfterEdit:function(index,row) {
			row.editing = false;
			hiddangerinfo_table_updateActions(index, row);
		},
		//取消编辑事件 -- 后置任务
		onCancelEdit:function(index,row){
			row.editing = false;
			if(row.isInsert) {
				$('#hiddangerinfo_table').datagrid("deleteRow", index);
			}
			hiddangerinfo_table_updateActions(index, row);
		}
	});
	
};

function hiddangerinfo_table_updateActions(index, row){		
	$('#hiddangerinfo_table').datagrid('updateRow',{
		index: index,
		row: {
		}
	});
}

//获取当前选择行
function hiddangerinfo_table_getRowIndex(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

//编辑事件
function hiddangerinfo_table_editrow(target){
	$('#hiddangerinfo_table').datagrid('beginEdit', hiddangerinfo_table_getRowIndex(target));
}

//取消行
function hiddangerinfo_table_cancelrow(target){
	$('#hiddangerinfo_table').datagrid('cancelEdit', hiddangerinfo_table_getRowIndex(target));
}

//新增行事件
function hiddangerinfo_table_insert(){
//debugger;
	if($('#id').val()=="")
		top.$.messager.alert('系统提示',"请先保存上部分的隐患项目信息", 'warning');
	
	var row = $('#hiddangerinfo_table').datagrid('getSelected');
	var index = 0;
	if (row){
		index = $('#hiddangerinfo_table').datagrid('getRowIndex', row);
		if(index<0) //这里改了应该对了
			index=0;
	} else {
		index = 0;
	}
//console.info($('#id').val());	
	$('#hiddangerinfo_table').datagrid('insertRow', {
		index: index, 
		row:{
			testDataId : $('#id').val(),
			isInsert : true,
			editing : true
		}
	});
	$('#hiddangerinfo_table').datagrid('selectRow',index);
	$('#hiddangerinfo_table').datagrid('beginEdit',index);
}

//保存
 function hiddangerinfo_table_saverow(target){
	 
	var index = hiddangerinfo_table_getRowIndex(target);
	var row = $('#hiddangerinfo_table').datagrid('getData').rows[index];
	
	//绑定combobox回显的值
	var et = $('#hiddangerinfo_table').datagrid('getEditor', {index:index,field:'gradeName'});//取得当前行对象
	var tempName = $(et.target).combobox('getText');
	var tempNameId = $(et.target).combobox('getValue');
	
	//绑定combobox回显的值
	var et = $('#hiddangerinfo_table').datagrid('getEditor', {index:index,field:'screegovernName'});//取得当前行对象
	var tempName1 = $(et.target).combobox('getText');
	var tempNameId1 = $(et.target).combobox('getValue');
	
	$('#hiddangerinfo_table').datagrid('endEdit', index);
	
	row.gradeName = tempName;//临时存储作回显用
	if (tempNameId != null && tempNameId != undefined && tempNameId != tempName) {
		row.grade = tempNameId;
	}
	
	row.screegovernName = tempName1;//临时存储作回显用
	if (tempNameId1 != null && tempNameId1 != undefined && tempNameId1 != tempName1) {
		row.screegovern = tempNameId1;
	}
	
	$('#hiddangerinfo_table').datagrid('refreshRow', index);
	
	$.ajax({
		type : 'POST',
		url : hiddangerinfopath+'/hiddangerinfo/saveOrUpdate.do',
		data : {id:row.id,hiddangerId:$('#id').val(),screegovern:row.screegovern,grade:row.grade,quantity:row.quantity},
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

function  hiddangerinfo_table_deleterow(target){
	
	$.messager.confirm('警告','确认删除该条数据?',function(r){
		if (!r){
			return false;
		}
		var index = hiddangerinfo_table_getRowIndex(target);
		var row = $('#hiddangerinfo_table').datagrid('getData').rows[index];
		$.ajax({
			type : 'POST',
			url : hiddangerinfopath+'/hiddangerinfo/delete.do',
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
					$('#hiddangerinfo_table').datagrid('deleteRow', index);
					$('#hiddangerinfo_table').datagrid('refreshRow', index);//我加
				}else {
					$.messager.alert('删除失败！', '未知错误，请联系管理员！', 'warning');
				}
			}
		});
		
	});
	
}