<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/hiddanger/hiddanger.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/projectTypes.js" charset="utf-8"></script>

<script type="text/javascript">    
    
    $(function(){	
    	init_hiddanger_list_datagrid('${path}');    	
    });
    
    function hiddanger_list_fileter() {
 	   $('#hiddanger_list_datagrid').datagrid('load', {  
 		  applycode:$.trim($('#applycode').textbox('getValue')),
 		  applyname:$.trim($('#applyname').textbox('getValue')),
 		  applytimeBegin : $.trim($('#applytimeBegin').datebox('getValue')),
 		  applytimeEnd : $.trim($('#applytimeEnd').datebox('getValue')),
 		});
 	};
 	
 	function hiddanger_list_reset() {	
		clearTableToolbar('hiddanger_list_form');
	};
    
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach" style="height:30px">
		<form id="hiddanger_list_form" method="POST">
			<table>
				<tr>
					<td>项目编码:</td>
					<td>
						<input type="text" id="applycode" name="applycode" class="easyui-textbox" />
					</td>
					<td>项目名称:</td>
					<td>
						<input type="text" id="applyname" name="applyname" class="easyui-textbox" />
					</td>
					<td>填报日期:</td>
					<td>
						<input type="text" id="applytimeBegin" name="applytimeBegin" class="easyui-datebox" data-options="editable:false"/>
					</td>
					<td>-----至:</td>
					<td>
						<input type="text" id="applytimeEnd" name="applytimeEnd" class="easyui-datebox" data-options="editable:false"/>
					</td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="hiddanger_list_datagrid" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="hiddanger_datagrid_toolbar" >
    
    	<div class="td_right">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="hiddanger_list_fileter();">查询</a>
			<a class="datagrid-btn-separator"></a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="hiddanger_list_reset();">清空</a>
    	</div>
    	
        <shiro:hasPermission name="hiddanger/add">
            <a onclick="hiddanger_list_add('${currentUser.id}','${currentUser.unitId}')" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="hiddanger/edit">
            <a id="hiddanger_edit" onclick="hiddanger_list_edit('edit');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="hiddanger/search">
            <a id="hiddanger_search" onclick="hiddanger_list_edit('search');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查看</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="hiddanger/declare">
        	<a id="hiddanger_declare" onclick="hiddanger_list_declare('${currentUser.id}');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">申报</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="hiddanger/del">
        	<a id="hiddanger_del" onclick="hiddanger_list_delete();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        </shiro:hasPermission>

    </div>
</div>