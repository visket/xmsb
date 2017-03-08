<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/processTypes.js" charset="utf-8"></script>

<html>
<head>
<title>隐患项目审核-分发专家页面</title>
	<script type="text/javascript"> 
		var thisrow;
		var hiddanger_path;
	    var userId = '${currentUser.id}';
	    var newDisponseId = null; // 新的状态位ID
	    
		$(function(){
			
			thisrow = row;
	    	init_hiddangerDisponseExpert_list_datagrid('${path}', thisrow.projectId);
	    	
	    });
	    
		function init_hiddangerDisponseExpert_list_datagrid(path, projectId){
			hiddanger_path=path;
			$('#hiddangerDisponseExpert_list_datagrid').datagrid({
				url: path+'/user/disponseExpert',
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
				sortName : 'createdate',			//按字段排序
				sortOrder : 'desc',				//倒序
				columns : [[
				 {field:'id', title :'id', checkbox: true},
				 {field:'name', title : '专家姓名', width : '30%', align : 'center'},
				 {field:'tradetypename', title : '行业类型', width : '30%', align : 'center'},
				 {field:'phone', title : '手机', width : '37%', align : 'center'}
			   ]] 
		   });
		}
		
		function confirmmethods() {
			/* if(top.overall_disponseStatus == 1) {
				top.$.messager.alert('系统提示', '操作已经完成,请勿重复进行!', 'warning');
				return;
			} */
			
			var rows = $('#hiddangerDisponseExpert_list_datagrid').datagrid('getSelections');
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
	    	thisrow.approverUnitId = '${currentUser.unitId}';
	    	
	    	thisrow.stepUnitId = null;
	    	thisrow.stepRoleId = null;
	    	thisrow.stepUserId = rows[0].id;
	    	
	    	thisrow.projectDeclareTime = null;
	    	thisrow.logLastupdatetime = null;
	    	thisrow.logEndtime = null;
	    	
	    	thisrow.disponseStatus = top.overall_disponseStatus;//设置流程状态
			
	    	
	    	$.ajax({
				url: '${path}/hiddangerAudit/audit',
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
						top.$.messager.alert('系统提示', '审核失败!'+data.msg, 'warning');
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
	        <table id="hiddangerDisponseExpert_list_datagrid" data-options="fit:true,border:false"></table>
	    </div>
	</div>
</body>

