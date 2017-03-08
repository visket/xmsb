<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/processTypes.js" charset="utf-8"></script>

<html>
<head>
<title>科技项目审核-分发处室页面</title>
	<script type="text/javascript"> 
		var thisrow;
		var science_path;
	    var userId = '${currentUser.id}';
		$(function(){
			
			thisrow = row;
	    	init_scienceDisponseOffice_list_datagrid('${path}', thisrow.projectId);
	    	
	    });
	    
		function init_scienceDisponseOffice_list_datagrid(path, projectId){
			science_path=path;
			$('#scienceDisponseOffice_list_datagrid').datagrid({
				url: path+'/unit/findUnitByTypeId',
				fit:true,
				border:false,
				pagination:true,
				pagePosition:'bottom',
				idField:'id',
				pageSize:10,
				pageList: [10,20,30],
				checkOnSelect:true,
				selectOnCheck:true,
				singleSelect:true,
				nowrap:true, 
				rownumbers : true, //显示行号
				showfooter :true,	//显示行尾
				pageNumber:1,
				fitColumns: true,
				columns : [[
				 {field:'id', title :'id', width : 50, checkbox: true},
				 {field:'name', title : '处室名称', width : 50, align : 'center'},
				 {field:'unit_linkman', title : '联系人', width : 50, align : 'center'},
				 {field:'telephone', title : '联系电话', width : 50, align : 'center'},
				 {field:'phone', title : '手机', width : 50, align : 'center'}
			   ]] 
		   });
		}
		
		function confirmmethods() {
			var rows = $('#scienceDisponseOffice_list_datagrid').datagrid('getSelections');
			if(rows.length != 1){
				top.$.messager.alert('系统提示', '必须且选定一条数据！', 'warning');
				return false;
			}
			
			//校验流程状态
			if(thisrow.logStatusId != Process_type.dsl && thisrow.logStatusId != Process_type.ysl) {
				top.$.messager.alert('系统提示', '该审核已执行完毕！', 'warning');
				return false;
			}

			//审核类型
	    	thisrow.auditType = '1';
	    	//当前用户
	    	thisrow.actualApproverId = userId;
	    	
	    	thisrow.stepUserId = null;
	    	thisrow.stepRoleId = null;
	    	thisrow.stepUnitId = rows[0].id;
	    	
	    	thisrow.projectDeclareTime = null;
	    	thisrow.logLastupdatetime = null;
	    	thisrow.logEndtime = null;
	    	
	    	thisrow.disponseStatus = top.overall_disponseStatus;//设置流程状态
			
	    	$.ajax({
				url: '${path}/science/audit',
				async : false,
				type: 'POST',
				data: thisrow,
				beforeSend : function() {
					top.$.messager.progress({text : '正在执行审核...'});
				},
				success: function (data) {
					top.$.messager.progress('close');
					if(data.success) {
						top.$.messager.show({title:"系统提示",msg:data.msg,timeout:5000,showType:'slide'});
						top.overall_disponseStatus = 1;
						winAudit.dialog('destroy');
					} else {
						top.$.messager.alert('系统提示', ''+data.msg, 'warning');
					}
				},
				error: function (xhr) {
					console.info(xhr);
				}
	   		});
			
		}
		
		
	</script>
</head>


<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
	    <div data-options="region:'center',border:false" style="width: 100%;">
	        <table id="scienceDisponseOffice_list_datagrid" data-options="fit:true,border:false"></table>
	    </div>
	</div>
</body>

