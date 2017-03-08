<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>

    <script type="text/javascript">
    var dicTable;
    $(function() {
        dicTable = $('#dicTable').datagrid({
        	//下次要做一个权限 访问
            url : '${path}/dictionary/find',
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
			singleSelect :true,
			fitColumns : true,
			toolbar : '#dicTable_toolbar',
            columns : [ [ 
				{title:'ID', field :'id' ,checkbox: true},             
            	{width : '15%', title : '代号', field : 'dictionarycode',sortable : true}, 
            	{width : '25%', title : '描述', field : 'dictionaryvalue', sortable : true},
            	{width : '30%', title : '创建时间', field : 'createtime', sortable : true,},
            	{field : 'action', title : '操作', width : '25%',
	                formatter : function(value,row,index) {
	                    var str = '';
	                        <shiro:hasPermission name="/dictionary/view">
	                            str += $.formatString('<a href="javascript:void(0)" class="dictionary-easyui-linkbutton-search" data-options="plain:true,iconCls:\'icon-search\'" onclick="checkView(\'{0}\');" >查看下级</a>', row.id);
                        	</shiro:hasPermission>
	                    return str;
	                }
            	}]],
			onLoadSuccess:function(data){       	
			    $('.dictionary-easyui-linkbutton-search').linkbutton({text:'查看下级',plain:true,iconCls:'icon-search'});
			}
        });
    });
    
    function addFun() {
        var title='字典添加';
    	var str ="<div><iframe id='dictionary_addFun' src='${path }/dictionary/editPage' width='100%' height='100%' style='border:0'></iframe></div>";	
    	loaddialogbynewpage('dictionary_addFun', str, title, 600, 400, 'add', 'dicTable', null);
    }
    
    function editFun() {
    	var title='字典编辑';

        	var row = $("#dicTable").datagrid('getSelected');
        	//var row	= $("#dicTable").datagrid('getData').rows[index];
        	var str ="<div><iframe id='dictionary_editFun' src='${path }/dictionary/editPage?id="+row.id+"' width='100%' height='100%' style='border:0'></iframe></div>";	
        	loaddialogbynewpage('dictionary_editFun', str, title, 600, 400, 'edit','dicTable',row);
        
    }
    
    function deleteFun() {
		deleteNoteById('dicTable', "${path}/dictionary/deletes", '确定删除所选记录?');
	};
    
    function searchFun() {
        dicTable.datagrid('load', $.serializeObject($('#dictionary_searchForm')));
    }
    function cleanFun() {
    	clearTableToolbar('dictionary_searchForm');
//         dicTable.datagrid('load', {});
    }
    
    function checkView(id){
    	var title='子字典';
    	if (id) {
    		var str ="<div><iframe id='check_View' src='${path }/item/manager?keyid="+id+"' width='100%' height='100%' style='border:0'></iframe></div>";	
    		checkviewdialog('check_View', str, title, 700,500,id);
        }
    }
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach">
		<form id="dictionary_searchForm" method="POST">
			<table>
				<tr>
					<td>代号:</td>
					<td><input id="dictionarycode" name="dictionarycode" class="easyui-textbox" /></td>
					<td>描述:</td>
					<td><input id="dictionaryvalue" name="dictionaryvalue" class="easyui-textbox" /></td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="dicTable" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="dicTable_toolbar" >
    	<div class="td_right">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
			<a class="datagrid-btn-separator"></a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun()">清空</a>
    	</div>
    	<div>
        <shiro:hasPermission name="/dictionary/add">
            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        
           <shiro:hasPermission name="/user/edit">
            <a onclick="editFun( );" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/user/delete">
            <a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        </shiro:hasPermission>
        
        </div>
    </div>
</div>











    
