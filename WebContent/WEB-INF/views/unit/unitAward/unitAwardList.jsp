<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript">
	var unitAwardTable;
	
	$(function() {
		
		loadAreaTreeAll('${path}','sysareaId');
		
		loadDictionary('${path }', 'unitId', 'JLQY');
		
		$('#year').combobox({
			valueField : 'id',			//值
			textField : 'name',	
			url : '${path}/unitAward/findYear',									//初始加载数据
			editable : false,				//不可编辑	
		});
		
		
		unitAwardTable = $('#unitAward_list_datagrid').datagrid({
        	//下次要做一个权限 访问
            url : '${path}/unitAward/find',
            fit : true,
			border : false,
			striped : true, //显示斑马线, 不同行颜色不同
			rownumbers : true, //显示行号
			pagination : true, //开启分页
			pageNumber : 1, //初始页码
			pageSize : 10, //每页10条
			pageList : [ 5, 10, 20, 50, 100 ], //可选择每页记录数
			sortName : 'createtime',				//按字段排序
			sortOrder : 'desc',				//倒序
			showFooter :true,					//显示行尾
			fitColumns : true,
			toolbar : '#unitAward_datagrid_toolbar',
            columns : [ [ 
					{title:'ID', field :'id',checkbox : true},
					{width : '14%', title : '年度',align : 'center', field : 'year'}, 
					{width : '20%', title : '单位名', align : 'center',field : 'unitname'},
					{width : '20%', title : '金额', align : 'center',field : 'money'},
					{width : '20%', title : '区域', align : 'center',field : 'sysareaname'},
					{width : '25%', title : '创建时间', align : 'center',field : 'createtime', sortable : true},
				]],
        });
    });
    
	function unitAward_list_del() {
		deleteNoteById('unitAward_list_datagrid', "${path}/unitAward/deletes", '确定删除所选记录?');
	}; 
	
	 function unitAward_list_add() {
		var str ="<div><iframe id='unitAward_list_add' src='${path}/unitAward/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbynewpage('unitAward_list_add', str, '企业奖励添加', 500,350, 'add', 'unitAward_list_datagrid', null);
	 };
	 
	function unitAward_list_edit() {
		var rows = $('#unitAward_list_datagrid').datagrid("getSelections");
		if(rows.length != 1){
			$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
			return false;
		} else {
			var str ="<div><iframe id='unitAward_list_edit' src='${path}/unitAward/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbynewpage('unitAward_list_edit', str, '企业奖励编辑', 500,350, 'edit', 'unitAward_list_datagrid', rows[0]);
		} 
	};
	
	function unitAward_list_fileter() {
		   $('#unitAward_list_datagrid').datagrid('load', { 
			    unitId : $('#unitId').combobox('getValue'),
			    year : $('#year').combobox('getValue'),
			    sysareaId : $('#sysareaId').combobox('getValue'),
			});
	};
	
	function unitAward_list_reset() {
		clearTableToolbar('unitAward_form');
		//unitAwardTable.datagrid('load', {});
	};
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach">
		<form id="unitAward_form" method="POST">
			<table>
				<tr>
					<td>单位名:</td>
					<td>
						<input  id="unitId" name="unitId" class="easyui-combobox" />
					</td>
					<td>年度:</td>
					<td>
						<input id="year" name="year" class="easyui-combobox" />
					</td>
					<td>区域:</td>
					<td>
						<input id="sysareaId" name="sysareaId" class="easyui-combobox" style="width:150px;overflow:scroll"/>
					</td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="unitAward_list_datagrid" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="unitAward_datagrid_toolbar" style="height:25px">
    	<div class="td_left">
	        <shiro:hasPermission name="/unitAward/add">
	            <a onclick="unitAward_list_add()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/unitAward/edit">
	            <a onclick="unitAward_list_edit();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/unitAward/del">
	            <a onclick="unitAward_list_del();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
	        </shiro:hasPermission>
        </div>
    	<div class="td_right">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="unitAward_list_fileter();">查询</a>
			<a class="datagrid-btn-separator"></a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="unitAward_list_reset();">清空</a>
    	</div>
    	
    </div>
</div>
