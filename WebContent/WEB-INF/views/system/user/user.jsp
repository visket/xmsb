<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/system/user/user.js" charset="utf-8"></script>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>

<script type="text/javascript">
    $(function() {
    	init_user_list_datagrid('${path}');
    })
</script>
    <script type="text/javascript">

    var dataGrid;
    
    var organizationTree;
    
    function searchFun() {	
    	    dataGrid.datagrid('load', {  
    	    	name:$.trim($('#uname').textbox('getValue')),
    	    	createdateStartStr : $.trim($('#createdateStart').datebox('getValue')),
    	    	createdateEndStr : $.trim($('#createdateEnd').datebox('getValue'))
    		});
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
    <div data-options="region:'west',border:true,split:false,title:'组织机构'"  style="width:14%;overflow: scroll; ">
        <ul id="organizationTree"  style="width:160px;margin: 10px 10px 10px 10px;">
        </ul>
    </div>
    <div id="toolbar" style="display: none;">
        <shiro:hasPermission name="/user/add">
            <a onclick="user_add();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        
           <shiro:hasPermission name="/user/edit">
            <a onclick="user_edit( );" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/user/delete">
            <a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        </shiro:hasPermission>
    </div>
</body>
</html>