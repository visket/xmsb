<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/expert/expertreview.js" charset="utf-8"></script>

<script type="text/javascript">
  $(function(){
	  init_expertreview_list_datagrid('${path}','${currentUser.id}','${currentUser.unitId}'); 
  });
</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach">
		<!-- <form id="expert_form" method="POST">
			<table>
				<tr>
					<td>名称:</td>
					<td><input id="name" name="name" class="easyui-textbox" /></td>
					<td>行业类别:</td>
					<td><input id="tradetype" name="tradetype" class="easyui-combobox" data-options="editable:false" /></td>
				</tr>
            </table>
        </form> -->
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="expertreview_list_datagrid" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="expertreview_datagrid_toolbar" style="height:25px">
    	<div class="td_left">
	        <%-- <shiro:hasPermission name="/expert/add">
	            <a onclick="expert_list_add()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/expert/edit">
	            <a onclick="expert_list_edit();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/expert/del">
	            <a onclick="expert_list_del();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
	        </shiro:hasPermission> --%>
        </div>
    	<div class="td_right">
	    	<!-- <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="expert_list_fileter();">查询</a>
			<a class="datagrid-btn-separator"></a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="expert_list_reset();">清空</a> -->
    	</div>
    	
    </div>
</div>

    
