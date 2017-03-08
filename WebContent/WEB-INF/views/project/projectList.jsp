<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>

<script type="text/javascript" src="${staticPath }/static/ext/business/project/project.js" charset="utf-8"></script>

<script type="text/javascript">

	$(function() {
		init_project_list_datagrid('${path}');
		loadDictionary('${path}', 'typeId', 'XMLX');
		loadAreaTreeAll('${path}', 'areaId');
		//loadAreaCodeAll('${path}', 'areaId');

    });
    
    
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach">
		<form id="project_list_form" method="POST">
			<table>
				<tr>
					<td>项目编号:</td>
					<td>
						<input type="text" id="number" name="number" class="easyui-textbox" />
					</td>
					<td>项目名称:</td>
					<td>
						<input type="text" id="name" name="name" class="easyui-textbox" />
					</td>
					<td>项目开始时间:</td>
					<td>
						<input type="text" id="starttime" name="starttime" class="easyui-datebox" data-options="editable:false"/>
					</td>
					<td>项目结束时间:</td>
					<td>
						<input type="text" id="endtime" name="endtime" class="easyui-datebox" data-options="editable:false"/>
					</td>
				</tr>
				<tr>
					<td>项目类型:</td>
					<td>
						<input type="text" id="typeId" name="typeId" class="easyui-combobox" />
					</td>
					<td>所属区域:</td>
					<td>
					    <select id="areaId" name="areaId" ></select>
						<!-- <input type="text" id="areaId" name="areaId" class="easyui-combobox" /> -->
					</td>
					<td>创建时间:</td>
					<td>
						<input type="text" id="createtime" name="createtime" class="easyui-datebox" data-options="editable:false"/>
					</td>
					<td>最后修改时间:</td>
					<td>
						<input type="text" id="lastupdatetime" name="lastupdatetime" class="easyui-datebox" data-options="editable:false"/>
					</td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="project_list_datagrid" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="project_datagrid_toolbar" style="height:25px">
    	<div class="td_left">
	        <shiro:hasPermission name="/project/add">
	            <a onclick="project_list_add()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/project/edit">
	            <a onclick="project_list_edit('edit');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/project/search">
	            <a onclick="project_list_edit('search');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查看</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/project/del">
	            <a onclick="project_list_del();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/project/projectDeclare">
	            <a onclick="project_list_declare('${currentUser.id}');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">申报</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/project/audit">
	            <a onclick="project_list_audit();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-zoom'">审批</a>
	        </shiro:hasPermission>
        </div>
    	<div class="td_right">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="project_list_fileter();">查询</a>
			<a class="datagrid-btn-separator"></a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="project_list_reset();">清空</a>
    	</div>
    	
    </div>
</div>











    
