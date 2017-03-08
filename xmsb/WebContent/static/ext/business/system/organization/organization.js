var organization_path;
var treeGrid;
var init_organization_list_datagrid=function(path){
	organization_path=path;
	 treeGrid = $('#treeGrid').treegrid({
         url : organization_path+'/organization/treeGrid',
         idField : 'id',
         treeField : 'name',
         parentField : 'pid',
         rownumbers : true, //显示行号
         fit : true,
         fitColumns : false,
         border : false,
         toolbar : '#organization_datagrid_toolbar',
         columns : [ [
		{
			field : 'id',
		    title : 'id',
		    checkbox: true
		},
         {
             field : 'code',
             title : '行政区划',
             width : '10%'
         },{
             field : 'name',
             title : '机构名称',
             width : '18%'
         },{
             field : 'areaName',
             title : '区域名称',
             width : '10%'
         },{
             field : 'unitTypeName',
             title : '单位类型',
             width : '10%'
         },{
             field : 'seq',
             title : '排序',
             width : '10%'
         }, {
             field : 'iconCls',
             title : '图标',
             width : '10%'
         }
         ,  {
             width : '10%',
             title : '创建时间',
             field : 'createdate'
         },{
             field : 'pid',
             title : '上级资源ID',
             hidden : true
         }, {
             field : 'address',
             title : '地址',
             width : '20%'
         } ] ]
     });
}

var organization_add=function(){
	     var title='组织架构添加';
	     var str ="<iframe  id='organization_add' src='"+organization_path+"/organization/editPage' width='100%' height='100%' style='border:0'></iframe>";	
	     loaddialogbynewpage('organization_add',str,title,600,380,'add','treeGrid',null)
}

var organization_edit=function(pagetype) {	 
	var title='组织架构编辑';
    var row = treeGrid.treegrid('getSelected');   
    if (row){	  
  	var str ="<iframe  id='organization_edit' src='"+organization_path+"/organization/editPage' width='100%' height='100%' style='border:0'></iframe>";
  	    loaddialogbynewpage('organization_edit',str,title,600,380,pagetype,'treeGrid',row); 
    }else{
  	  $.messager.alert('警告！', '需要选中一条数据才能操作！', 'warning');
    }
}

var organization_delete=function() {
	
	var row = treeGrid.treegrid('getSelected');  
    if (row) {
    	top.$.messager.confirm('询问', '您是否要删除当前资源？删除当前资源会连同子资源一起删除!', function(b) {
            if (b) {
                $.post(organization_path+'/organization/delete', {
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
    
}