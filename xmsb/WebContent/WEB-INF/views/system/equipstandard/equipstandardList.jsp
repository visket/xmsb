<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript">
	var equipstandardTable;
	$(function() {
		loadDictionaryTest('${path }', 'typecode', 'ZBLB');
		
		equipstandardTable = $('#equipstandard_list_datagrid').datagrid({
        	//下次要做一个权限 访问
            url : '${path}/equipStandard/find',
            fit : true,
			border : false,
			striped : true, //显示斑马线, 不同行颜色不同
			rownumbers : true, //显示行号
			pagination : true, //开启分页
			pageNumber : 1, //初始页码
			pageSize : 10, //每页10条
			singleSelect :true,
			pageList : [ 5, 10, 20, 50, 100 ], //可选择每页记录数
			sortName : 'createtime',				//按字段排序
			sortOrder : 'desc',				//倒序
			showFooter :true,					//显示行尾
			fitColumns : true,
			toolbar : '#equipstandard_datagrid_toolbar',
            columns : [ [ 
					{title:'ID', field :'id',checkbox : true},
					{width : '15%', title : '装备类别',align : 'center', field : 'typevalue'},
					{width : '15%', title : '装备名称',align : 'center', field : 'name'}, 
					{width : '10%', title : '省级', align : 'center',field : 'province',
						formatter : function(value,data){
							if(data.unitvalue=="㎡"){
								if(data.countprovincevalue=="内设机构"){
									return value+"/"+data.countprovincevalue;
								}
								if(data.countprovincevalue=="人"){
									return value+"/"+data.countprovincevalue;
								}
								if(data.countprovincevalue=="无"){
									return value;
								}
							}else{
								if(data.countprovincevalue=="内设机构"){
									return value+"/"+data.countprovincevalue;
								}
								if(data.countprovincevalue=="人"){
									if(value==1){
										return value+"/"+data.countprovincevalue;
									}else{
										return 1+"/"+value+data.countprovincevalue;
									}
								}
								if(data.countprovincevalue=="无"){
									return value;
								}
							}
						}	
					},
					{width : '10%', title : '市级', align : 'center',field : 'city',
						formatter : function(value,data){
							if(data.unitvalue=="㎡"){
								if(data.countcityvalue=="内设机构"){
									return value+"/"+data.countprovincevalue;
								}
								if(data.countcityvalue=="人"){
									return value+"/"+data.countprovincevalue;
								}
								if(data.countcityvalue=="无"){
									return value;
								}
							}else{
								if(data.countcityvalue=="内设机构"){
									return value+"/"+data.countcityvalue;
								}
								if(data.countcityvalue=="人"){
									if(value==1){
										return value+"/"+data.countcityvalue;
									}else{
										return 1+"/"+value+data.countcityvalue;
									}
								}
								if(data.countcityvalue=="无"){
									return value;
								}
							}
						}	
					},
					{width : '10%', title : '县级', align : 'center',field : 'county',
						formatter : function(value,data){
							if(data.unitvalue=="㎡"){
								if(data.countcountyvalue=="内设机构"){
									return value+"/"+data.countprovincevalue;
								}
								if(data.countcountyvalue=="人"){
									return value+"/"+data.countprovincevalue;
								}
								if(data.countcountyvalue=="无"){
									return value;
								}
							}else{
								if(data.countcountyvalue=="内设机构"){
									return value+"/"+data.countcountyvalue;
								}
								if(data.countcountyvalue=="人"){
									if(value==1){
										return value+"/"+data.countcountyvalue;
									}else{
										return 1+"/"+value+data.countcountyvalue;
									}
								}
								if(data.countcountyvalue=="无"){
									return value;
								}
							}
						}	
					},
					{width : '10%', title : '单位', align : 'center',field : 'unitvalue'},
					{width : '10%', title : '配备原则', align : 'center',field : 'equipcritevalue'},
					{width : '18%', title : '创建时间', align : 'center',field : 'createtime', sortable : true,
					},
				]],
        });
    });
    
	function equipstandard_list_del() {
		deleteNoteById('equipstandard_list_datagrid', "${path}/equipStandard/deleteByStatus", '确定删除所选记录?');
	}; 
	
	 function equipstandard_list_add() {
		var str ="<div><iframe id='equipstandard_list_add' src='${path}/equipStandard/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbynewpageequip('equipstandard_list_add', str, '装备标准添加', 750,300, 'add', 'equipstandard_list_datagrid', null);
	 };
	 
	function equipstandard_list_edit() {
		var rows = $('#equipstandard_list_datagrid').datagrid("getSelections");
		if(rows.length != 1){
			$.messager.alert('警告！', '必须且选定一条数据！', 'warning');
			return false;
		} else {
			var str ="<div><iframe id='equipstandard_list_edit' src='${path}/equipStandard/addNew' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbynewpage('equipstandard_list_edit', str, '装备标准编辑', 750,300, 'edit', 'equipstandard_list_datagrid', rows[0]);
		} 
	};
	
	function equipstandard_list_fileter() {
		   $('#equipstandard_list_datagrid').datagrid('load', { 
			    name : $.trim($('#name').val()),
			    typecode : $('#typecode').combobox('getValue'),
			});
	};

	function equipstandard_list_reset() {
		clearTableToolbar('equipstandard_form');
// 		equipstandardTable.datagrid('load', {});
	};
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" class="top_sreach">
		<form id="equipstandard_form" method="POST">
			<table>
				<tr>
					<td>装备名称:</td>
					<td>
						<input type="text" 	id="name" name="name" class="easyui-textbox" />
					</td>
					<td>装备类别:</td>
					<td>
						<input id="typecode" name="typecode" class="easyui-combobox" />
					</td>
				</tr>
            </table>
        </form>
    </div>
    
    <div data-options="region:'center',border:false" style="width: 100%;">
        <table id="equipstandard_list_datagrid" data-options="fit:true,border:false"></table>
    </div>
    
    <div id="equipstandard_datagrid_toolbar" style="height:25px">
    	<div class="td_left">
	        <shiro:hasPermission name="/equipstandard/add">
	            <a onclick="equipstandard_list_add()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/equipstandard/edit">
	            <a onclick="equipstandard_list_edit();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="/equipstandard/del">
	            <a onclick="equipstandard_list_del();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
	        </shiro:hasPermission>
        </div>
    	<div class="td_right">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="equipstandard_list_fileter();">查询</a>
			<a class="datagrid-btn-separator"></a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="equipstandard_list_reset();">清空</a>
    	</div>
    	
    </div>
</div>











    
