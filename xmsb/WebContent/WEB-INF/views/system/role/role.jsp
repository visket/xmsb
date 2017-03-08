<!-- 角色移动到system后面再搞 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/system/role/role.js" charset="utf-8"></script>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>角色管理</title>
<script type="text/javascript">
    $(function() {
    	init_role_list_datagrid('${path}');
    })
</script>

<script type="text/javascript">

    /* function addFun() {
    	var title='角色添加';
    	var str ="<iframe  id='role_addFun' src='${path }/role/addPage' width='100%' height='100%' style='border:0'></iframe>";	
    	datagrid_loaddialog('role_addFun', str, title, 600, 400, 'dataGrid');
    } */

    /* 
    function editFun(id) {
    	var title='角色编辑';
    	 if (id == undefined) {
             var rows = dataGrid.datagrid('getSelections');
             id = rows[0].id;
         } else {
             dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
         }
        if (id) {
        	var str ="<iframe id='role_editFun' src='${path }/role/editPage?id="+id+"' width='100%' height='100%' style='border:0'></iframe>";	
        	datagrid_loaddialog('role_editFun', str, title, 600, 400, 'dataGrid');
        }
    } 
    */

    /* function deleteFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
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
        });
    } */

   /* 
    function grantFun(id) {
        if (id == undefined) {
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        
        $.modalDialog({
            title : '授权',
            width : 500,
            height : 500,
            href : '${path }/role/grantPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    $.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = $.modalDialog.handler.find('#roleGrantForm');
                    f.submit();
                }
            } ]
        });
    }
  */  
    
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',fit:true,border:false">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
        <shiro:hasPermission name="/role/add">
            <a onclick="role_add();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/role/grant">
            <a onclick="role_grant();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">授权</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/role/edit">
            <a onclick="role_edit('edit');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/role/delete">
            <a onclick="role_delete();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        </shiro:hasPermission>
    </div>
</body>
</html>