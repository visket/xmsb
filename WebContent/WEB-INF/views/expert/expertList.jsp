<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>

    <script type="text/javascript">
    var expertTable;
    $(function() {
    	loadDictionaryTest('${path }', 'tradetype', 'XMFL');
//     	loadAreaTreeAll('${path}','sysareaId');
        expertTable = $('#expert_list_datagrid').datagrid({
        	//下次要做一个权限 访问
            url : '${path}/expert/find',
            fit : true,
			border : false,
			striped : true, //显示斑马线, 不同行颜色不同
			rownumbers : true, //显示行号
			pagination : true, //开启分页
			pageNumber : 1, //初始页码
			pageSize : 10, //每页10条
			pageList : [ 5, 10, 20, 50, 100 ], //可选择每页记录数
			sortName : 'createdate',				//按字段排序
			sortOrder : 'desc',				//倒序
			showFooter :true,					//显示行尾
			fitColumns : true,
			toolbar : '#expert_datagrid_toolbar',
            	columns : [ [ 
					{title:'ID', field :'id',checkbox : true},
					{width : '25%', title : '登录名',align : 'center', field : 'loginname'}, 
					{width : '10%', title : '姓名', align : 'center',field : 'name'},
					{width : '25%', title : '手机号', align : 'center',field : 'phone'},
					{width : '24%', title : '创建时间', align : 'center',field : 'createdate', sortable : true},
					{width : '15%', title : '行业类型', align : 'center',field : 'tradetypename'},
					
				]],
        });
    });
    
    
    
    function expert_list_del() {
		deleteNoteById('expert_list_datagrid', "${path}/expert/deletes", '确定删除所选记录?');
	}; 
	
 function expert_list_add() {
	var str ="<div><iframe id='expert_list_add' src='${path}/expert/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
	loaddialogbynewpage('expert_list_add', str, '专家添加', 700,300, 'add', 'expert_list_datagrid', null);
 };
	 
	function expert_list_edit() {
		var rows = $('#expert_list_datagrid').datagrid("getSelections");
		if(rows.length != 1){
			$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
			return false;
		} else {
			var str ="<div><iframe id='expert_list_edit' src='${path}/expert/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbynewpage('expert_list_edit', str, '专家编辑', 700,300, 'edit', 'expert_list_datagrid', rows[0]);
		} 
	};
    function expert_list_fileter() {
    	expertTable.datagrid('load', $.serializeObject($('#expert_form')));
    }
    function expert_list_reset() {
    	clearTableToolbar('expert_form');
//     	expertTable.datagrid('load', {});
    }
    
</script>


<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach">
		<form id="expert_form" method="POST">
			<table>
				<tr>
					<td>名称:</td>
					<td><input id="name" name="name" class="easyui-textbox" /></td>
					<td>行业类别:</td>
					<td><input id="tradetype" name="tradetype" class="easyui-combobox" data-options="editable:false" /></td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="expert_list_datagrid" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="expert_datagrid_toolbar" style="height:25px">
    	<div class="td_left">
	        <shiro:hasPermission name="/expert/add">
	            <a onclick="expert_list_add()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/expert/edit">
	            <a onclick="expert_list_edit();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/expert/del">
	            <a onclick="expert_list_del();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
	        </shiro:hasPermission>
        </div>
    	<div class="td_right">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="expert_list_fileter();">查询</a>
			<a class="datagrid-btn-separator"></a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="expert_list_reset();">清空</a>
    	</div>
    	
    </div>
</div>

    
