<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/css/easyui.css" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/js/default/datagrid-detailview.js" charset="utf-8"></script>
<script type="text/javascript">
	var hiddangerExamineTable;
	
	$(function() {
		loadDictionary('${path }', 'type', 'KHNR');
		hiddangerExamineTable = $('#hiddangerExamine_list_datagrid').datagrid({
        	//下次要做一个权限 访问
        	view: detailview,
        	url : '${path}/hiddangerExamine/find',
			fit : true,
			border : false,
			striped : true,						//显示斑马线, 不同行颜色不同
			rownumbers : true,					//显示行号
			pagination : true,					//开启分页
			pageNumber : 1,						//初始页码
			pageSize : 10,						//每页10条
			pageList : [5,10, 20, 50],		//可选择每页记录数
			fitColumns : true,
          	singleSelect :true,
			sortName : 'createtime',				//按字段排序
			sortOrder : 'desc',				//倒序
			showFooter :true,					//显示行尾
			fitColumns : true,
			toolbar : '#hiddangerExamine_datagrid_toolbar',
            columns : [ [ 
					{title:'ID', field :'id',checkbox : true},
					{width : 100, title : '考核名称',align : 'center', field : 'name'}, 
					{width : 100, title : '类别名称', align : 'center',field : 'typename'},
					{width : 100, title : '总分', align : 'center',field : 'totalPoints'},
					{width : 100, title : '创建时间', align : 'center',field : 'createtime', sortable : true},
			]],
		    detailFormatter:function(index,row){ 
	            return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';  
	        }, 
		    onExpandRow:function(index,row){
		    	$('#ddv-'+index).datagrid({  
		    		url : '${path}/hiddangerGrade/find',
	                queryParams:{examineId:row.id},		
	    			pagination : true,					//开启分页
	        		pageNumber : 1,						//初始页码
	        		pageSize : 5,	
	        		pageList : [5,10,20],
	                fitColumns:true,  
	                singleSelect:true,  
	                height:'auto',
	                sortName : 'createtime',				//按字段排序
	    			sortOrder : 'desc',				//倒序
	                columns:[[  
	               			{field : 'examineId',title : 'examineId',hidden : true}, 
	                        {
	              				field : 'id',title : '序号',width :50,align : 'center',checkbox : true
	              			},
	              			{field : 'grade',title : '评分标准',width : 150,align : 'center'},
	              			{field : 'points',title : '分值',width :  50,align : 'center'}, 
	              			{field : 'createtime',title : '创建时间',width : 150,align : 'center'}, 
	                ]],  
	            });
		    }
        });
    });
    
	function hiddangerExamine_list_del() {
		deleteNoteById('hiddangerExamine_list_datagrid', "${path}/hiddangerExamine/deletes", '确定删除所选记录?');
	}; 
	
	function hiddangerExamine_list_add() {
		var str ="<div><iframe id='hiddangerExamine_list_add' src='${path}/hiddangerExamine/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbynewpage('hiddangerExamine_list_add', str, '隐患考核标准添加', 500,250, 'add', 'hiddangerExamine_list_datagrid', null);
	};
	
	function hiddangerGrade_list_add() {
		var rows = $('#hiddangerExamine_list_datagrid').datagrid("getSelections");
		if(rows.length != 1){
			$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
			return false;
		} else {
			var str ="<div><iframe id='hiddangerGrade_list_add' src='${path}/hiddangerGrade/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbynewpage('hiddangerGrade_list_add', str, '隐患考核评分添加', 500,250, 'add', 'hiddangerExamine_list_datagrid', rows[0]);
		}
	};
	 
	function hiddangerExamine_list_edit() {
		var rows = $('#hiddangerExamine_list_datagrid').datagrid("getSelections");
		if(rows.length != 1){
			$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
			return false;
		} else {
			var str ="<div><iframe id='hiddangerExamine_list_edit' src='${path}/hiddangerExamine/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbynewpage('hiddangerExamine_list_edit', str, '隐患考核标准修改', 500,250, 'edit', 'hiddangerExamine_list_datagrid', rows[0]);
		} 
	};
	
	function hiddangerExamine_list_fileter() {
		    $('#hiddangerExamine_list_datagrid').datagrid('load', { 
			    name : $('#name').textbox('getValue'),
			    type : $('#type').combobox('getValue'),
			}); 
	};
	
	function hiddangerExamine_list_reset() {
		clearTableToolbar('hiddangerExamine_form');
// 		hiddangerExamineTable.datagrid('load', {});
	};
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach">
		<form id="hiddangerExamine_form" method="POST">
			<table>
				<tr>
					<td>考核名称:</td>
					<td>
						<input  id="name" name="name" class="easyui-textbox" />
					</td>
					<td>考核类别:</td>
					<td>
						<input id="type" name="type" class="easyui-combobox" />
					</td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="hiddangerExamine_list_datagrid" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="hiddangerExamine_datagrid_toolbar" style="height:25px">
    	<div class="td_left">
	        <shiro:hasPermission name="/hiddangerExamine/add">
	            <a onclick="hiddangerExamine_list_add()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加标准</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/hiddangerGrade/addpoint">
	            <a onclick="hiddangerGrade_list_add()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加评分</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/hiddangerExamine/edit">
	            <a onclick="hiddangerExamine_list_edit();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/hiddangerExamine/del">
	            <a onclick="hiddangerExamine_list_del();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
	        </shiro:hasPermission>
        </div>
    	<div class="td_right">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="hiddangerExamine_list_fileter();">查询</a>
			<a class="datagrid-btn-separator"></a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="hiddangerExamine_list_reset();">清空</a>
    	</div>
    </div>
</div>
