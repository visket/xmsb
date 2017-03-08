<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>

 <script type="text/javascript">
    console.info("---父字典的ID"+keyid);
    var itemTable;
    $("#dictionaryId").val(keyid)
    $(function() {
        itemTable = $('#itemTable').datagrid({
        	//下次要做一个权限 访问
            url : '${path}/item/find',
            queryParams: {
        		dictionaryId: keyid
        	},
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
			singleSelect :true,
			toolbar : '#itemTable_toolbar',
            columns : [ [ 
				{title:'ID', field :'id',checkbox : true},
				{hidden :true, title:'dictionaryId', field :'dictionaryId' },  
            	{width : '30%', title : '代号', field : 'itemcode',sortable : true}, 
            	{width : '34%', title : '描述', field : 'itemvalue', sortable : true},
            	{width : '30%', title : '创建时间', field : 'createtime', sortable : true},
//             	{field : 'action', title : '操作', width : '25%',
// 	                formatter : function(value,row,index) {
// 	                    var str = '';
// 	                            str += $.formatString('<a href="javascript:void(0)" class="item-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>',index);
// 	                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
// 	                            str += $.formatString('<a href="javascript:void(0)" class="item-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
// 	                    return str;
// 	                }
//             	}
            	]],
        });
    });
    
    function item_list_add() {
        var title='字典添加';
    	var str ="<div><iframe id='item_addFun' src='${path }/item/editPage' width='100%' height='100%' style='border:0'></iframe></div>";	
    	loaddialogbynewpage('item_addFun', str, title, 600, 400, 'add', 'itemTable', keyid);
    }
    
    function item_list_edit() {
    	 var title='字典编辑';
        /*if (index) {
        	var row	= $("#itemTable").datagrid('getData').rows[index];
        	var str ="<div><iframe id='item_editFun' src='${path }/item/editPage?id="+row.id+"' width='100%' height='100%' style='border:0'></iframe></div>";	
        	loaddialogbynewpage('item_editFun', str, title, 600, 400, 'edit','itemTable',row);
        } */
        var rows = $('#itemTable').datagrid("getSelections");
		if(rows.length != 1){
			$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
			return false;
		} else {
			var str ="<div><iframe id='item_editFun' src='${path }/item/editPage' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbynewpage('item_editFun', str, title, 500,350, 'edit', 'itemTable', rows[0]);
		}
    }
    
    function item_list_del() {
		deleteNoteById('itemTable', "${path}/item/deletes", '确定删除所选记录?');
	}; 
    
//     function item_list_del(){
//     	deleteById('itemTable', id, "${path}/item/delete", '确定删除所选子字典记录?');
//     }
    
    function item_list_fileter() {
    	itemTable.datagrid('load',{
    		itemcode : $('#itemcode').textbox('getValue'),
    		itemvalue: $('#itemvalue').textbox('getValue'),
    		dictionaryId :keyid,
        });
    }
    function item_list_reset() {
    	clearTableToolbar('item_searchForm');
//         $('#searchForm input').val('');
//         itemTable.datagrid('load', {
//         	dictionaryId :keyid,
//         });
    }
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach">
		<form id="item_searchForm" method="POST">
			<table>
				<input type="hidden" id="dictionaryId" name="dictionaryId"/>
				<tr>
					<td>代号:</td>
					<td><input id="itemcode" name="itemcode" class="easyui-textbox" /></td>
					<td>描述:</td>
					<td><input id="itemvalue" name="itemvalue" class="easyui-textbox" /></td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="itemTable" data-options="fit:true,border:false"></table>
    </div>
    
<!--     <div id="itemTable_toolbar" > -->
<!--     	<div class="td_right"> -->
<!-- 	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> -->
<!-- 			<a class="datagrid-btn-separator"></a> -->
<!-- 			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun()">清空</a> -->
<!--     	</div> -->
<!--             <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a> -->
<!--     </div> -->
    
    
    <div id="itemTable_toolbar" style="height:25px">
    	<div class="td_left">
	            <a onclick="item_list_add()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
	            <a onclick="item_list_edit();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
	            <a onclick="item_list_del();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        </div>
    	<div class="td_right">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="item_list_fileter();">查询</a>
			<a class="datagrid-btn-separator"></a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="item_list_reset();">清空</a>
    	</div>
    	
    </div>
</div>
