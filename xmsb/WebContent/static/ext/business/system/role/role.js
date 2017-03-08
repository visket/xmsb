var role_path;
var dataGrid;
var init_role_list_datagrid=function(path){
	role_path=path;
	dataGrid = $('#dataGrid').datagrid({
        url : role_path + '/role/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        toolbar : '#toolbar',
        fit : true,
        idField : 'id',
        sortName : 'id',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
        frozenColumns : [ [ {
            width : '4%',
            title : 'id',
            field : 'id',
            sortable : true,
            checkbox: true
        }, 
//        {
//            width : '24%',
//            title : '所属组织',
//            field : 'orgName'
//        } ,
        {
            width : '25%',
            title : '名称',
            field : 'name',
            sortable : true
        } , {
            width : '25%',
            title : '排序号',
            field : 'seq',
            sortable : true
        }, {
            width : '25%',
            title : '描述',
            field : 'description'
        } , {
            width : '24%',
            title : '状态',
            field : 'status',
            sortable : true,
            formatter : function(value, row, index) {
                switch (value) {
                case 1:
                    return '正常';
                case 0:
                    return '停用';
                }
            }
         }
        ] ]
    });
}

var role_add=function(){
	 var title='角色添加';
	 var str ="<iframe  id='role_add' src='"+role_path+"/role/addPage' width='100%' height='100%' style='border:0'></iframe>";	
	 loaddialogbynewpage('role_add',str,title,600,400,'add','dataGrid',null)
} 

var role_grant=function(id){
	var title='授权管理';
	var row = dataGrid.datagrid('getSelected');
	if (row){	 
		var str="<iframe  id='role_grant' src='"+role_path+"/role/grantPage' width='100%' height='100%' style='border:0'></iframe>";	
		loaddialogbynewpage('role_grant',str,title,500,600,'edit','dataGrid',row); 		
	}else{
   	 top.$.messager.alert('警告！', '需要选中一条数据才能操作！', 'warning');
	 }	

	/*var row = dataGrid.datagrid('getSelected');
	if (row){
	    top.$.modalDialog({
	        title : '授权',
	        width : 500,
	        height : 500,
	        href : role_path+'/role/grantPage?id=' + row.id,
	        buttons : [ {
	            text : '确定',
	            handler : function() {
debugger;	            	
	            	top.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
	                var f = top.$.modalDialog.handler.find('#roleGrantForm');
	                f.submit();
	            }
	        } ]
	    });
	}else{
		top.$.messager.alert('警告！', '需要选中一条数据才能操作！', 'warning');
	}*/
}

var role_edit=function(pagetype) {
	var title='角色编辑';
    var row = dataGrid.datagrid('getSelected');
	if (row){	  
	   	 var str ="<iframe  id='role_editFun' src='"+role_path+"/role/editPage?id="+row.id+"' width='100%' height='100%' style='border:0'></iframe>";
	   	    loaddialogbynewpage('role_editFun',str,title,600,400,pagetype,'dataGrid',row); 
	     }else{
	    	 top.$.messager.alert('警告！', '需要选中一条数据才能操作！', 'warning');
	 }	
}

var role_delete=function(){
	/*if (id == undefined) {//点击右键菜单才会触发这个
        var rows = dataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {//点击操作里面的删除图标会触发这个
        dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    $.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
        if (b) {
            progressLoad();
            $.post('${path }/role/delete', {
                id : id
            }, function(result) {
                if (result.success) {
                    $.messager.alert('提示', result.msg, 'info');
                    dataGrid.datagrid('reload');
                }
                progressClose();
            }, 'JSON');
        }
    });*/
    
    
    var row = dataGrid.datagrid('getSelected');  
    if (row) {
    	top.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
            if (b) {
                $.post(role_path+'/role/delete', {
                    id : row.id
                }, function(result) {
                    if (result.success) {
                    	top.$.messager.alert('提示', result.msg, 'info');
                    	dataGrid.datagrid('reload');
                    }
                }, 'JSON');
            }
        });
    }else{
    	top.$.messager.alert('警告！', '需要选中一条数据才能操作！', 'warning');
    }
}