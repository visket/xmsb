var projectpath;
var saveValid=true;
var init_eqchild_table = function(path,typeId,tableName){
//console.info("tableName="+tableName+"typeId="+typeId);	
	projectpath=path;	
	$('#'+tableName).datagrid({
		url: projectpath + '/equipStandard/findEquipChild.do',
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
		fit:true,
		sortName : 'createTime',			//按字段排序
		sortOrder : 'desc',				//倒序
		toolbar:'#eqchild_toolbar',
		queryParams : {
			typeId : typeId, //传递不同子tab
			gradetype : $('#gradetype').val(),
			eqbaseId:$('#id').val()
		  //unitType:$('#unitType').val()//传递省市县
		},
		columns:[[
			{field:'id',title:'编号',width:100,align:'center',checkbox: true},
			{field : 'name', title : '装备名', width : 100, align : 'center'},
			//标准数量根据申报单位而定，如果是省级，就只显示省级，市，县不显示
			{field : 'standarnum', title : '标准数量', width : 120, align : 'center',
				formatter : function(value,rowData){
					if("内设机构"==rowData.standartypename){
						return value+"/"+rowData.standartypename;
					}
					if("人"==rowData.standartypename){
					  if("㎡"==rowData.unit)
						  return rowData.standarnum+"/人";
		                return 1+"/"+rowData.standartypename;
					}
					if("无"==rowData.standartypename){
						return value;
					}
				}
			},
			{field : 'unit', title : '单位', width : 100, align : 'center'},
			{field : 'equipcrite', title : '是否标配', width : 100, align : 'center'},
		    {field : 'canmatchnum', title : '可配数量', width : 100, align : 'center',
		    	formatter:function(value, rowData, rowIndex){
					if("人"==rowData.standartypename){
						if("㎡"==rowData.unit){
							rowData.canmatchnum=rowData.standarnum*$('#compileshow').val();
							return rowData.standarnum*$('#compileshow').val();
						}else{			
							var f = (1/rowData.standarnum)/(1/$('#compileshow').val());
							if(String(f).indexOf('.') != -1){
								if(f.toFixed(1)<1){
								  f=1;
								  rowData.canmatchnum=f;
								  return f;
								}
								 f=parseInt(f);
								 rowData.canmatchnum=f+1;
								 return f+1;
								 //rowData.canmatchnum=f.toFixed(1);//保留一位小数
							}else{
								rowData.canmatchnum=f;
								return f;
							}
						}
					}else if("内设机构"==rowData.standartypename){
						rowData.canmatchnum=rowData.standarnum*$('#internalorgshow').val();
						return rowData.standarnum*$('#internalorgshow').val();
					}else if("无"==rowData.standartypename){
						rowData.canmatchnum=rowData.standarnum;
						return rowData.standarnum;
					}
			    }
		   },
		   {field : 'equipnum', title : '装备数量', width : 100, align : 'center',editor:'numberbox'},
		   {hidden : true, field : 'eqstandardid', title : '装备名关联ID', width : 100, align : 'center'},
		   {hidden : true, field : 'eqid', title : 'eqID', width : 100, align : 'center'}
		]],
		onLoadSuccess:function(data){
			 
			var rows = $('#'+tableName).datagrid("getRows");
			for(var i=0;i<rows.length;i++){
				$('#'+tableName).datagrid('beginEdit',i);			
				
				//添加监听 
				var editorEquipnum= $('#'+tableName).datagrid("getEditor", {index:i, field:'equipnum'});

				editorEquipnum.target.textbox({
					onChange:function(){
						var i =insteadLive_table_getRowIndex(this);
						var row= $('#'+tableName).datagrid("getRows")[i];
					
						//做整数大于小于判断时一定要用parseInt()处理，否则比较的结果不准
						if(parseInt($(this).val())>parseInt(row.canmatchnum)){
							$.messager.alert('警告！', '输入的装备数量必须小于可配数量！', 'warning');
							$(this).numberbox('setValue','0');
							saveValid=false;
						}else
							saveValid=true;
					}
				});
			}
			 
		}
		
		/*//编辑前事件，可加载行数据问题
		onBeforeEdit:function(index,row) {
			row.editing = true;
			eqchild_table_updateActions(index, row);
			$('#eqchild_table').datagrid('refreshRow', index);
		},
		//编辑事件完成，调用此处，可向后台传递参数
		onAfterEdit:function(index,row) {
			row.editing = false;
			eqchild_table_updateActions(index, row);
		},
		//取消编辑事件 -- 后置任务
		onCancelEdit:function(index,row){
			row.editing = false;
			if(row.isInsert) {
				$('#eqchild_table').datagrid("deleteRow", index);
			}
			eqchild_table_updateActions(index, row);
		}*/
	});
	
};

/**
 * 获取 人员分工表 指定行 指定列的值，适用于取editor对象
 * @param row	行对象
 * @param index	行索引
 * @param field	列名
 * @returns	单元格值
 */
function get_row_editData(row, index, field,table) {
	var editor = $('#'+table).datagrid("getEditor", {index:index, field:field});
	var temp = null;
	if(editor == null) temp = rows[i].equipnum;
	else temp = editor.target.val();
	return temp;
}

function eqchild_save(path,typeId,tableName,id){
	
	if(saveValid==false)
		return;
	
	var rows = $('#'+tableName).datagrid("getRows");
	var json = [];
	var eqchild;
	//后台保存时注意已存在的用update,可以通过读取eqchild的ID是否为空判断是编辑还是保存
	$.each(rows,function(i){
	/*	//判断如果输入的装备数量大于可配数量则返回
		 if(rows[i].canmatchnum<get_row_editData(rows[i],i,'equipnum',tableName))
        	 flag=false;*/
		
		eqchild={
			"eqstandardid":rows[i].id,
			"id":rows[i].eqid,
			"equipnum":get_row_editData(rows[i],i,'equipnum',tableName),
			"eqbaseid":id
		};
		json.push(eqchild);
	});
	json =JSON.stringify(json);
	$.ajax({
		type : 'POST',
		url : path+'/equipchild/saveOrUpdate.do?eqbaseId='+id,
		data : {json:json},
		success:function(data){
	
			//必须reload否则保存后再点击保存会重复写入数据库
			$('#'+tableName).datagrid('reload',{typeId:typeId,gradetype:$('#gradetype').val(),eqbaseId:$('#id').val()});
	    }
	});
	
	
}

function insteadLive_table_getRowIndex(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}