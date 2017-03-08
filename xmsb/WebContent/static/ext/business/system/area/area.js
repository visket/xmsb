var sysarea_path;
var treeGrid;
var init_sysarea_list_datagrid=function(path){
	sysarea_path=path;
	treeGrid=$('#treeGrid').treegrid({
		url : sysarea_path+'/area/dataGrid',
		idField : 'id',
		treeField : 'name',
        parentField : 'pid',
        fitColumns : false,
        rownumbers : true, 
        border : false,
        fit : true,
        //autoRowHeight : false, 
        toolbar : '#area_datagrid_toolbar',
        columns : [ [
		{
			field : 'id',
		    title : 'id',
		    checkbox: true
		},
         {
        	field : 'code',
            title : '行政区划',
            width : '20%'
        },{
            field : 'name',
            title : '名称',
            width : '46%'
        },{
            field : 'seq',
            title : '排序',
            width : '4%'
        },{
            field : 'createDate',
            title : '创建时间',
            width : '15%'
        },{
            field : 'gradetypename',
            title : '级别',
            width : '5%'
        },{
            field : 'status',
            title : '状态',
            width : '9%',
            formatter:function(val, row, index){
            	switch (val) {
                case 1:
                    return '正常';
                case 0:
                    return '锁定';
                 default:
                	 return '未知';
                }
         }
        } ] ]
	});
}

var area_add=function(){
	 var title='区域添加';
	     var str ="<iframe  id='area_add' src='"+sysarea_path+"/area/editPage' width='100%' height='100%' style='border:0'></iframe>";	
	     loaddialogbynewpage('area_add',str,title,500,300,'add','treeGrid',null)
} 

var area_edit=function(pagetype) {	 
	 var title='区域编辑';
     var row = treeGrid.treegrid('getSelected');
     if (row){	  
   	 var str ="<iframe  id='area_edit' src='"+sysarea_path+"/area/editPage' width='100%' height='100%' style='border:0'></iframe>";
   	    loaddialogbynewpage('area_edit',str,title,500,300,pagetype,'treeGrid',row); 
     }else{
   	  $.messager.alert('警告！', '需要选中一条数据才能操作！', 'warning');
     }
}

var area_delete=function() {

    var row = treeGrid.treegrid('getSelected');  
    if (row) {
    	top.$.messager.confirm('询问', '您是否要删除当前资源？删除当前资源会连同子资源一起删除!', function(b) {
            if (b) {
                $.post(sysarea_path+'/area/delete', {
                    code : row.code
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