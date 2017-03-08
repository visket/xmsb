<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>机构管理</title>
<script type="text/javascript" src="${staticPath }/static/ext/business/system/organization/organization.js" charset="utf-8"></script>
<script type="text/javascript">
	
    $(function() {
    	init_organization_list_datagrid('${path}');
    })

</script>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'center',border:false"  style="overflow: hidden;">
            <table id="treeGrid"></table>
        </div>
        
       <div id="organization_datagrid_toolbar" >	
        <shiro:hasPermission name="/organization/add"><!-- organization_add() -->
            <a onclick="organization_add();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/organization/edit">
            <a onclick="organization_edit('edit');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/organization/delete">
            <a onclick="organization_delete();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        </shiro:hasPermission>
    </div>
    </div>
</body>
</html>