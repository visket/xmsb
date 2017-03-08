var user_path;
var dataGrid;
var init_user_list_datagrid=function(path){
	user_path=path;
	
	organizationTree = $('#organizationTree').tree({
        url : user_path+'/organization/tree',
        parentField : 'pid',
        lines : true,
        panelMaxHeight : 500,
        onClick : function(node) {    	
            dataGrid.datagrid('load', {
                organizationId: node.id
            });
        }
    });
	
	dataGrid = $('#dataGrid').datagrid({
        url :  user_path +'/user/dataGrid',
        fit : true,
        striped : true,
        rownumbers : true,
        fitColumns:true,
        pagination : true,
        singleSelect : true,
        idField : 'id',
        sortName : 'createdate',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
        columns : [ [ {
            title : 'id',
            field : 'id',
            checkbox: true
        }, {
       
            title : '登录名',
            field : 'loginname',
            //sortable : true
        }, {
         
            title : '姓名',
            field : 'name',
            //sortable : true
        },{
            //width : '80',
            title : '部门ID',
            field : 'organizationId',
            hidden : true
        },{
      
            title : '机关单位',
            field : 'organizationName'
        },{

            title : '创建时间',
            field : 'createdate',
            sortable : true
        },  {

            title : '性别',
            field : 'sex',
            //sortable : true,
            formatter : function(value, row, index) {
                switch (value) {
                case 0:
                    return '男';
                case 1:
                    return '女';
                }
            }
        }, {
     
            title : '年龄',
            field : 'age',
            //sortable : true
        },{
    
            title : '电话',
            field : 'phone',
            //sortable : true
        }, 
      {
  
            title : '角色',
            field : 'rolesList',
            //sortable : true,
            formatter : function(value, row, index) {
                var roles = [];
                for(var i = 0; i< value.length; i++) {
                    roles.push(value[i].name);  
                }
                return(roles.join('\n'));
            }
        },
//        {
//     
//            title : '用户类型',
//            field : 'usertype',
//            //sortable : true,
//            formatter : function(value, row, index) {
//                if(value == '0') {
//                    return "管理员";
//                }else if(value == '1') {
//                    return "用户";
//                }
//                return "未知类型";
//            }
//        },
        {
           // width : '4%',
            title : '状态',
            field : 'status',
            //sortable : true,
            formatter : function(value, row, index) {
                switch (value) {
                case 1:
                    return '正常';
                case 0:
                    return '停用';
                }
            }
        } , 
        {
            width : '6%',
            title : '用户类型',
            field : 'typename',
            //sortable : true
        },
        ] ],
       
        toolbar : '#toolbar'
    }); 
}

var user_add=function() {
    var title='用户添加';
	var str ="<iframe  id='user_addFun' src='"+user_path+"/user/addPage' width='100%' height='100%' style='border:0'></iframe>";
	loaddialogbynewpage('user_addFun', str, title, 600, 400, 'add','dataGrid',null);
}

var user_edit=function() {
	var title='用户编辑';
	var rows =dataGrid.datagrid("getSelections");
	if(rows.length != 1){
		$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
		return false;
	}else{
		var str="<iframe  id='user_editFun' src='"+user_path+"/user/editPage?id="+rows[0].id+"' width='100%' height='100%' style='border:0'></iframe>";	
		loaddialogbynewpage('user_editFun',str,title,600,400,'edit','dataGrid',rows[0]); 	
	}
	
} 

var deleteFun=function() {
	deleteNoteById('dataGrid', user_path+"/user/delete", '确定删除所选记录?');
}