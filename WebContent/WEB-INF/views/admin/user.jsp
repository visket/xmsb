<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
    <script type="text/javascript">

    var dataGrid;
    var organizationTree;

    $(function() {
        organizationTree = $('#organizationTree').tree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            onClick : function(node) {
                dataGrid.datagrid('load', {
                    organizationId: node.id
                });
            }
        });

        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/user/dataGrid',
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
          
                title : '所属部门',
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
            }, {
         
                title : '用户类型',
                field : 'usertype',
                //sortable : true,
                formatter : function(value, row, index) {
                    if(value == '0') {
                        return "管理员";
                    }else if(value == '1') {
                        return "用户";
                    }
                    return "未知类型";
                }
            },{
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
    });
    
    function addFun() {
        var title='用户添加';
    	var str ="<iframe  id='user_addFun' src='${path }/user/addPage' width='100%' height='100%' style='border:0'></iframe>";	
    	datagrid_loaddialog('user_addFun', str, title, 600, 400, 'dataGrid');
    }
    
    function editFun(id) {
    	var title='用户编辑';
        if (id == undefined) {
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        if (id) {
        	var str ="<iframe id='user_editFun' src='${path }/user/editPage?id="+id+"' width='100%' height='100%' style='border:0'></iframe>";	
        	datagrid_loaddialog('user_editFun', str, title, 600, 400, 'dataGrid');
        }
    }
    
    function deleteFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        $.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
            if (b) {
                var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
                if (currentUserId != id) {
                    progressLoad();
                    $.post('${path }/user/delete', {
                        id : id
                    }, function(result) {
                        if (result.success) {
                            $.messager.alert('提示', result.msg, 'info');
                            dataGrid.datagrid('reload');
                        }
                        progressClose();
                    }, 'JSON');
                } else {
                    $.messager.show({
                        title : '提示',
                        msg : '不可以删除自己！'
                    });
                }
            }
        });
    }
    
    function searchFun() {
//console.info("createdateStart="+$('#createdateStart').datebox('getValue')+"#createdateEnd="+$('#createdateEnd').datebox('getValue'));    	
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    	    /* dataGrid.datagrid('load', { //重载页面，并传递以下自定义参数
    	    uname : $.trim($('#uname').val()),
    	    createdateStart : $.trim($('#createdateStart').datebox('getValue')),
    	    createdateEnd : $.trim($('#createdateEnd').datebox('getValue')) 
		});*/
    }
    function cleanFun() {
        $('#searchForm input').val('');
        dataGrid.datagrid('load', {});
    }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach">
        <form id="searchForm" method="POST">
            <table>
                <tr>
                    <td><span>姓名:</span><input id="uname" name="name" class="easyui-textbox" placeholder="请输入用户姓名"/></td>
                    <td><!-- placeholder="点击选择时间"  -->
                       <span>创建时间:</span>
                       <input id="createdateStart" name="createdateStartStr" class="easyui-datebox" data-options="editable:false"  />
                       <span>至</span>
                       <input id="createdateEnd" name="createdateEndStr" class="easyui-datebox" data-options="editable:false" />
                       <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                       <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'用户列表'"  style="width: 86%">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div data-options="region:'west',border:true,split:false,title:'组织机构'"  style="width:14%;overflow: hidden; ">
        <ul id="organizationTree"  style="width:160px;margin: 10px 10px 10px 10px">
        </ul>
    </div>
    <div id="toolbar" style="display: none;">
        <shiro:hasPermission name="/user/add">
            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        
           <shiro:hasPermission name="/user/edit">
            <a onclick="editFun( );" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/user/delete">
            <a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        </shiro:hasPermission>
    </div>
</body>
</html>