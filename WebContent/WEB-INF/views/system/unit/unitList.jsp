<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>

    <script type="text/javascript">
    var unitTable;
    $(function() {
    	loadDictionaryTest('${path}','gradetype','DWDJ')
    	loadAreaTreeAll('${path}','sysareaId');
        unitTable = $('#unitTable').datagrid({
        	//下次要做一个权限 访问
            url : '${path}/unit/find',
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
			toolbar : '#unit_list_toolbar',
            	columns : [ [ 
					{title:'ID', field :'id',checkbox : true},
					{hidden :true, title:'创建人', field :'creatorname' },
					{hidden :true, title:'修改人', field :'updatorname' },
					{width : '10%', title : '登录名',align : 'center', field : 'loginname'},
					{width : '15%', title : '单位名称',align : 'center', field : 'name'}, 
					{width : '10%', title : '联系人', align : 'center',field : 'unitLinkman'},
					{width : '10%', title : '手机号', align : 'center',field : 'phone'},
					{width : '15%', title : '创建时间', align : 'center',field : 'createtime', sortable : true},
					{width : '14%', title : '行业类别', align : 'center',field : 'tradeTypeName'},
					{width : '10%', title : '上级部门', align : 'center',field : 'higherLevelName'},
					{width : '10%', title : '级别', align : 'center',field : 'typename'},
				]],
        });
    });
    
    function searchFun() {
        unitTable.datagrid('load',{
        	name : $('#name').textbox('getValue'),
        	unitLinkman: $('#unitLinkman').textbox('getValue'),
        	gradetype :$('#gradetype').combobox('getValue'),
        	sysareaId :$('#sysareaId').combotree('getValue')
        });
    }
    function cleanFun() {
    	clearTableToolbar('unit_searchForm');
        unitTable.datagrid('load', {});
    }
    
    function unit_list_edit(pagetype) {
    	var rows = $('#unitTable').datagrid("getSelections");
    	if(rows.length != 1){
    		$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
    		return false;
    	} else {
    		var str ="<div><iframe id='project_list_edit' src='${path}/unit/addNew' width='100%' height='100%' style='border:0'></iframe></div>";	
    		loaddialogbyproject('project_list_edit', str, '企业详情', 700,300, pagetype, 'unitTable', rows[0]);
    	}
    };
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach">
		<form id="unit_searchForm">
			<table>
				<tr>
					<td>单位名称:</td>
					<td><input id="name" name="name" class="easyui-textbox" /></td>
					<td>联系人:</td>
					<td><input id="unitLinkman" name="unitLinkman" class="easyui-textbox" /></td>
					<td>单位等级:</td>
					<td><input id="gradetype" name="gradetype" /></td>
					<td>区域:</td>
					<td><input id="sysareaId" name="sysareaId" /></td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="unitTable" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="unit_list_toolbar" >
<!--     	<div class="td_right"> -->
<!-- 	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> -->
<!-- 			<a class="datagrid-btn-separator"></a> -->
<!-- 			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun()">清空</a> -->
<!--     	</div> -->

			<shiro:hasPermission name="/unit/edit">
           	 	<a onclick="unit_list_edit('search');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查看</a>
        	</shiro:hasPermission>
        	<div class="td_right">
		    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
				<a class="datagrid-btn-separator"></a>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun()">清空</a>
    		</div>
        </div>
    </div>











    
