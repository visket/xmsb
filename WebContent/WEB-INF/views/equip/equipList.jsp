<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/equip/equip.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/projectTypes.js" charset="utf-8"></script>


<script type="text/javascript">    
    
    $(function(){
    	
    	init_equip_list_datagrid('${path}');
        
    	$('#unitId').combobox({
		    url:"${path}/equip/findAllUnit",  
		    valueField:'id',    
		    textField:'name'   
		});
    	
    	/* $('#grade').combobox({    
		    url:"${path}/equip/findAllGrade",  
		    valueField:'id',    
		    textField:'itemvalue'   
		}); */
    	
    	/* $('#statusId').combobox({    
		    url:"${path}/equip/findAllStatus",  
		    valueField:'id',    
		    textField:'itemvalue'   
		});  */
    	
    });
    
    function equip_list_fileter() {
 	   $('#equip_list_datagrid').datagrid('load', {  
 		  applycode:$.trim($('#applycode').textbox('getValue')),
 		  applyname:$.trim($('#applyname').textbox('getValue')),
 		  //applicantName:$.trim($('#applicantName').textbox('getValue')),
 		  applytimeStr : $.trim($('#applytimeStr').datebox('getValue')),
 		  applytimeEnd : $.trim($('#applytimeEnd').datebox('getValue')),
 		  unitName : $.trim($('#unitName').textbox('getValue')),
 		  //unitId : $.trim($('#unitId').combobox('getValue')),
 		  //grade : $.trim($('#grade').combobox('getValue')),
 		  //statusId : $.trim($('#statusId').combobox('getValue')),
 		  internalorg : $.trim($('#internalorg').numberspinner('getValue')),
 		  compile : $.trim($('#compile').numberspinner('getValue'))
 		});
 	};
 	
 	function equip_list_reset() {
		clearTableToolbar('equip_list_form');
	};
    
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach" style="height:66px">
		<form id="equip_list_form" method="POST">
			<table>
				<tr>
					<td>申请编码:</td>
					<td>
						<input type="text" id="applycode" name="applycode" class="easyui-textbox" />
					</td>
					<td>申请名称:</td>
					<td>
						<input type="text" id="applyname" name="applyname" class="easyui-textbox" />
					</td>
					<td>单位名称:</td>
					<td>
						<!-- <input type="text" id="unitId" name="unitId" class="easyui-combobox" /> -->
						<input type="text" id="unitName" name="unitName" class="easyui-textbox" />
					</td>
					<td>编制人数:</td>
					<td>
						<input type="text" id="compile" name="compile" class="easyui-numberspinner" data-options="min:0" />
					</td> 
				</tr>
				<tr>
					<td>申请开始时间:</td>
					<td>
						<input type="text" id="applytimeStr" name="applytimeStr" class="easyui-datebox" data-options="editable:false"/>
					</td>
					<td>申请结束时间:</td>
					<td>
						<input type="text" id="applytimeEnd" name="applytimeEnd" class="easyui-datebox" data-options="editable:false"/>
					</td>
					<td>内设机构数量:</td>
					<td>
					    <input id="internalorg"  name="internalorg"  class="easyui-numberspinner" data-options="min:0" />
					</td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="equip_list_datagrid" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="equip_datagrid_toolbar" >
    	<div class="td_right">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="equip_list_fileter();">查询</a>
			<a class="datagrid-btn-separator"></a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="equip_list_reset();">清空</a>
    	</div>
    	
        <shiro:hasPermission name="equip/add">
            <a onclick="equip_list_add()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="equip/edit">
            <a id="equip_edit" onclick="equip_list_edit('edit');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="equip/search">
            <a id="equip_search" onclick="equip_list_edit('search');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查看</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="equip/declare">
        	<a id="equip_declare" onclick="equip_list_declare('${currentUser.id}');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">申报</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="equip/del">
        	<a id="equip_del" onclick="equip_list_delete();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        </shiro:hasPermission>

    </div>
</div>