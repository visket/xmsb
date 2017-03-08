var projectpath;

var init_lzjcbznl_table = function(path){
//alert("$('#unittype').val()="+$('#unittype').val()+"--="+projectpath);	
	projectpath=path;
	$('#lzjcbznl_table').datagrid({
		url: projectpath + '/equipStandard/findEquipChild.do',
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
		sortName : 'createTime',			//按字段排序
		sortOrder : 'desc',				//倒序
		toolbar:'#lzjcbznl_toolbar',
		queryParams : {
			//testDataId : $('#id').val(), $('#pageType').val()
			equipChild : 'dsdsd', //传递不同子tab
		    unitType:$('#unittype').val()//这里还需要一个参数，传递省市县
		},
		columns:[[
			{field:'id',title:'编号',width:100,align:'center',checkbox: true},
			{field : 'name', title : '装备名', width : 100, align : 'center', editor:'textbox'},
			//标准数量根据申报单位而定，如果是省级，就只显示省级，市，县不显示
			{field : 'standarnum', title : '标准数量', width : 100, align : 'center', editor:'textbox'},
			{field : 'unit', title : '单位', width : 100, align : 'center', editor:'textbox'},
			{field : 'equipcrite', title : '是否标配', width : 100, align : 'center',
				/*formatter:function(value, rowData, rowIndex){
					if(value==null)
						return "未知";
					else if(value==0)
						return "否";
					else if(value==1)
						return "是";
			 }*/
			},
			{field : 'unit', title : '单位', width : 100, align : 'center', 
				/*formatter : function(value,row,index){
					return value;
				},				
				editor:{
					type:'combobox',
					options:{
						valueField:'id',
						textField:'itemCode',
						url: path+'/item/findByDictionarycode?dictionarycode=',
						required:true
					}
			   }*/
		  },
		  {field : 'equipnum', title : '装备数量', width : 100, align : 'center', editor:'textbox'}
		/*
			 {field : 'unit', title : '单位', width : 100, align : 'center', 
				formatter : function(value,row,index){
					return value;
				},
				editor:{
					type:'combobox',
					options:{
						valueField:'id',
						textField:'itemCode',
						url: path+'/item/findByDictionarycode?dictionarycode=',
						required:true
					}
			}},
		*/			
			/*{field : 'action', title:'操作', width:80, align:'center',
				formatter:function(value,row,index){
				  if(pagetype!='search'){	
					if (row.editing){
						var s = '<a href="#" onclick="lzjcbznl_table_saverow(this)">保存</a> ';
						var c = '<a href="#" onclick="lzjcbznl_table_cancelrow(this)">取消</a>';
						return s+c;
					} else {
						var e = '<a href="#" onclick="lzjcbznl_table_editrow(this)">编辑</a> ';
						var d = '<a href="#" onclick="lzjcbznl_table_deleterow(this)">删除</a>';
						return e+d;
					}
				  }	
				}
			}*/
			/*{hidden : true, field : 'tempName', title : 'tempName', width : 100, align : 'center'},
			{hidden : true, field : 'tempType', title : 'tempType', width : 100, align : 'center'},
			{hidden : true,	field : 'targetNameId', title : 'targetNameId', width : 100, align : 'center'},
			{hidden : true,	field : 'targetTypeId', title : 'targetTypeId', width : 100, align : 'center'}*/
		]],
		//编辑前事件，可加载行数据问题
		onBeforeEdit:function(index,row) {
			row.editing = true;
			lzjcbznl_table_updateActions(index, row);
			$('#lzjcbznl_table').datagrid('refreshRow', index);
		},
		//编辑事件完成，调用此处，可向后台传递参数
		onAfterEdit:function(index,row) {
			row.editing = false;
			lzjcbznl_table_updateActions(index, row);
		},
		//取消编辑事件 -- 后置任务
		onCancelEdit:function(index,row){
			row.editing = false;
			if(row.isInsert) {
				$('#lzjcbznl_table').datagrid("deleteRow", index);
			}
			lzjcbznl_table_updateActions(index, row);
		}
	});
	
};


//获取当前选择行
/*function lzjcbznl_table_getRowIndex(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

//编辑事件
function lzjcbznl_table_editrow(target){
	$('#lzjcbznl_table').datagrid('beginEdit', lzjcbznl_table_getRowIndex(target));
}

//取消行
function lzjcbznl_table_cancelrow(target){
	$('#lzjcbznl_table').datagrid('cancelEdit', lzjcbznl_table_getRowIndex(target));
}*/

//新增行事件
/*function lzjcbznl_table_insert(){
	var row = $('#lzjcbznl_table').datagrid('getSelected');
	var index = 0;
	if (row){
		index = $('#lzjcbznl_table').datagrid('getRowIndex', row);
		if(index<0) //这里改了应该对了
			index=0;
	} else {
		index = 0;
	}
	$('#lzjcbznl_table').datagrid('insertRow', {
		index: index, 
		row:{
			testDataId : $('#id').val(),
			isInsert : true,
			editing : true
		}
	});
	$('#lzjcbznl_table').datagrid('selectRow',index);
	$('#lzjcbznl_table').datagrid('beginEdit',index);
}*/

//保存
/*function lzjcbznl_table_saverow(target){
	var index = lzjcbznl_table_getRowIndex(target);

	var row = $('#lzjcbznl_table').datagrid('getData').rows[index];
	
	//绑定combobox回显的值
//	var et = $('#lzjcbznl_table').datagrid('getEditor', {index:index,field:'equipname'});//取得当前行对象
//	var tempName = $(et.target).combobox('getText');
//	var tempNameId = $(et.target).combobox('getValue');
	
	//绑定combobox回显的值
	var etr = $('#lzjcbznl_table').datagrid('getEditor', {index:index,field:'equiptype'});//取得当前行对象
	var tempType = $(etr.target).combobox('getText');
	var tempTypeId = $(etr.target).combobox('getValue');
	
	$('#lzjcbznl_table').datagrid('endEdit', index);

//	row.equipname = tempName;//临时存储作回显用
//	if (tempNameId != null && tempNameId != undefined && tempNameId != tempName) {
//		row.targetNameId = tempNameId;
//	}
	
	row.equiptype = tempType;//临时存储作回显用
	if (tempTypeId != null && tempTypeId != undefined && tempTypeId != tempType) {
		row.targetTypeId = tempTypeId;
	}
	
	
//	$.ajax({
//		type : 'POST',
//		url : projectpath+'/testson/lzjcbznll/saveOrUpdate.do',
//		data : {id:row.id,testDataId:row.testDataId,targetNameId:row.targetNameId,targetTypeId:row.targetTypeId,targetStateDesc:row.targetStateDesc},
//		beforeSend : function() {
//			$.messager.progress({
//				text : '正在保存中...'
//			});
//		},
//		success : function(data) {
//			if(row.isInsert == true) {
//				row.isInsert = false;
//			}
//			$.messager.progress('close');
//			flashTableByTestSon('lzjcbznll_table');
//		}
//	});
}*/

/*function lzjcbznl_table_deleterow(target){
	$.messager.confirm('警告','确认删除该条数据?',function(r){
		if (!r){
			return false;
		}
		var index = lzjcbznl_table_getRowIndex(target);
		var row = $('#lzjcbznl_table').datagrid('getData').rows[index];
//		$.ajax({
//			type : 'POST',
//			url : projectpath+'/testson/lzjcbznll/delete.do',
//			data : {id:row.id},
//			beforeSend : function() {
//				$.messager.progress({
//					text : '正在删除中...'
//				});
//			},
//			success : function(data) {
//				$.messager.progress('close');
//				if (data) { //删除成功
//					$.messager.show({
//						title : '提示',
//						msg : '删除成功！'
//					});
//					$('#lzjcbznll_table').datagrid('deleteRow', index);
//					flashTableBylzjcbznll('lzjcbznll_table');
//				} else {
//					$.messager.alert('删除失败！', '未知错误，请联系管理员！', 'warning');
//				}
//			}
//		});
		
	});
}*/

/*function lzjcbznl_table_updateActions(index, row){		
	$('#lzjcbznl_table').datagrid('updateRow',{
		index: index,
		row: {
			//targetNameName : row.tempTargetName,
			//targetTypeName : row.tempTargetType
		}
	});
	
}*/