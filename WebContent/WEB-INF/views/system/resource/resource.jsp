<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${staticPath }/static/ext/business/system/resource/resource.js" charset="utf-8"></script>
<meta http-equiv="Content-Type" content="text/html;" />
<title>资源管理</title>
<script type="text/javascript">
    $(function() {
    	init_resource_list_datagrid('${path}');
    })
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false"  style="overflow: hidden;width:100%><!--  style="overflow: hidden;"-->
        <div data-options="region:'center',border:false"  >
            <table id="treeGrid" ></table>
        </div>
    </div>

    <div id="toolbar" ><!-- style="display: none;" -->
        <shiro:hasPermission name="/resource/add">
            <a onclick="resource_add();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/resource/edit">
            <a onclick="resource_edit('edit');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/resource/delete">
            <a onclick="resource_delete();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        </shiro:hasPermission>
    </div>
</body>
</html>