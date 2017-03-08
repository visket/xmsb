var resource_path;
var treeGrid;
var init_resource_list_datagrid=function(path){
	resource_path=path;
	treeGrid = $('#treeGrid').treegrid({
        url : path+'/resource/treeGrid',
        idField : 'id',
        treeField : 'name',
        parentField : 'pid',
        rownumbers : true, //显示行号
        fit : true,
        fitColumns : false,
        border : false,
        toolbar:'#toolbar',
        columns : [ [ {
            title : 'id',
            field : 'id',
            checkbox: true
        }, {
            field : 'name',
            title : '资源名称',
            width : "31%"
        }, {
            field : 'url',
            title : '资源路径',
            width : "30%"
        }, {
            field : 'seq',
            title : '排序',
            width : "6%"
        }, {
            field : 'iconCls',
            title : '图标',
            width : "14%"
        }, {
            field : 'resourcetype',
            title : '资源类型',
            width : "8%",
            formatter : function(value, row, index) {
                switch (value) {
                case 0:
                    return '菜单';
                case 1:
                    return '按钮';
                }
            }
        }, {
            field : 'pid',
            title : '上级资源ID',
            hidden : true//这里不占用宽度
        }, {
            field : 'status',
            title : '状态',
            width : "8%",
            formatter : function(value, row, index) {
                switch (value) {
                case 1:
                    return '正常';
                case 0:
                    return '停用';
                }
            }
        } ] ]
    });	
}

var resource_add=function(){
    var title='资源添加';
    var str ="<iframe id='resource_add' src='"+resource_path+"/resource/editPage' width='100%' height='100%' style='border:0'></iframe>";	
    loaddialogbynewpage('resource_add',str,title,600,420,'add','treeGrid',null);
}

var resource_edit=function(pagetype) {
	var title='资源编辑';
    var row = treeGrid.treegrid('getSelected');   
    if (row){	  
  	var str ="<iframe  id='resource_edit' src='"+resource_path+"/resource/editPage' width='100%' height='100%' style='border:0'></iframe>";
  	    loaddialogbynewpage('resource_edit',str,title,600,420,pagetype,'treeGrid',row); 
    }else{
  	  $.messager.alert('警告！', '需要选中一条数据才能操作！', 'warning');
    }
}

var resource_delete=function(){
	
	var row = treeGrid.treegrid('getSelected');  
    if (row) {
    	top.$.messager.confirm('询问', '您是否要删除当前资源？删除当前资源会连同子资源一起删除!', function(b) {
            if (b) {
                $.post(resource_path+'/resource/delete', {
                    id : row.id
                }, function(result) {
                    if (result.success) {
                    	top.$.messager.alert('提示', result.msg, 'info');
                        treeGrid.treegrid('reload');
                    }
                }, 'JSON');
            }
        });
    }else{
    	top.$.messager.alert('警告！', '需要选中一条数据才能操作！', 'warning');
    }
	
	/*if (id != undefined) {
        treeGrid.treegrid('select', id);
    }*/
    /*var node = treeGrid.treegrid('getSelected');
    if (node) {
       $.messager.confirm('询问', '您是否要删除当前资源？删除当前资源会连同子资源一起删除!', function(b) {
            if (b) {
                $.post('${pageContext.request.contextPath}/resource/delete', {
                    id : node.id
                }, function(result) {
                    if (result.success) {
                        $.messager.alert('提示', result.msg, 'info');
                        treeGrid.treegrid('reload');
                    }
                }, 'JSON');
            }
        });
    }*/
	
}
