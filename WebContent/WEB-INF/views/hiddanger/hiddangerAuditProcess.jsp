<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/processTypes.js" charset="utf-8"></script>

<html>
<head>
<title>隐患项目审核-查看所有流程状态</title>
	<script type="text/javascript"> 
		var thisrow;
		var hiddanger_path;
	    var currentUserId = '${currentUser.id}';
		$(function(){
			
			thisrow = row;
	    	init_hiddangerAuditProcess_list_datagrid('${path}', thisrow.projectId);
	    	
	    });
	    
		function init_hiddangerAuditProcess_list_datagrid(path, projectId){
			hiddanger_path=path;
			$('#hiddangerAuditProcess_list_datagrid').datagrid({
				url: path+'/hiddangerAudit/auditProcess',
				fit:true,
				border:false,
				pagination:true,
				pagePosition:'bottom',
				idField:'logId',
				pageSize:10,
				pageList: [10,20,30],
				checkOnSelect:true,
				selectOnCheck:true,
				singleSelect:false,
				nowrap:true, 
				rownumbers : true, //显示行号
				showfooter :true,	//显示行尾
				pageNumber:1,
				fitColumns: true,
				toolbar:'#hiddangerAuditProcess_datagrid_toolbar',
				queryParams : {
					projectId : projectId
				}, 
				columns : [[
				 {field:'logId', title :'logId', width : 50, checkbox: true},
				 {field:'actualApproveUnitName', title : '审核单位', width : 50, align : 'center',
					 formatter :  function(value,row,index) {
						 if(row.actualApproveUnitId == null || row.actualApproveUnitId == '') return row.approveUnitName;
						 else return value;
					 }},
				 {field:'actualApproverName', title : '实际审核人', width : 50, align : 'center'},
				 {field:'logLastupdatetime', title : '处理日期', width : 50, align : 'center'},
				 {field:'logStatusName', title : '审核状态', width : 50, align : 'center'},
				 {field:'logReviewcontent', title : '审核内容', width : 50, align : 'center'},
				 {hidden:true,field:'projectId', title:'项目ID', width:50, align:'center'}
			   ]] 
		   });
		}
		
		function auditmethods() {
			var rows = $('#hiddangerAuditProcess_list_datagrid').datagrid('getRows');
			var lastRow = rows[0];
			//校验身份
			if(lastRow.actualApproverId != currentUserId) {
				top.$.messager.alert('系统提示', '您没有审核权限！', 'warning');
				return false;
			}
			
			//校验数据
			if(lastRow.logId != thisrow.logId) {
				top.$.messager.alert('系统提示', '您没有审核权限！', 'warning');
				return false;
			}
			
			if(top.overall_auditStatus == 1) {
				top.$.messager.alert('系统提示', '审核操作已经完成,请勿重复审核!', 'warning');
				return;
			}

			//校验流程状态
			if(lastRow.logStatusId != Process_type.dsl && lastRow.logStatusId != Process_type.ysl) {
				top.$.messager.alert('系统提示', '审核操作已经完成,请勿重复审核!', 'warning');
				return false;
			}
			
			var type = pagetype == 'audit' ? 'submit' : 'default';
			
			var str ="<div><iframe id='hiddangerAuditProcess_audit' src='${path}"
				+"/hiddangerAudit/beforeAudit?projectId="+thisrow.projectId
				+"&&actualApproverId="+currentUserId
				+"&&logId="+thisrow.logId
				+"&&logStatusId="+thisrow.logStatusId
				+"&&operateType=audit"
				+"' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbyaudit('hiddangerAuditProcess_audit', str, thisrow.projectName + '-审核操作', 600, 270, type, 'hiddangerAuditProcess_list_datagrid', thisrow);
		}
		
		
		function disponseOffice() {
			if(thisrow == null) {
				top.$.messager.alert('系统提示', '数据完整性缺失，请关闭后刷新页面重试!', 'warning');
			}
			
			//校验身份
			var type = false;
			if(thisrow.logStatusId != Process_type.dsl && thisrow != Process_type.ysl)
				type = true;
			
			if(type && thisrow.actualApproverId != currentUserId) {
				top.$.messager.alert('系统提示', '您没有分发权限！', 'warning');
				return false;
			}
			
			var type = pagetype == 'search' ? 'default' : 'submit';
			
			var str ="<div><iframe id='hiddangerAuditProcess_list_disponseOffice' src='${path}"
				+"/hiddangerAudit/beforeAudit?projectId="+thisrow.projectId
				+"&&actualApproverId="+currentUserId
				+"&&logId="+thisrow.logId
				+"&&logStatusId="+thisrow.logStatusId
				+"&&operateType=office"
				+"' width='100%' height='100%' style='border:0'></iframe></div>";
				loaddialogbyscienceaudit('hiddangerAuditProcess_list_disponseOffice', str, thisrow.projectName + '-分发处室', 600, 350, type, 'hiddangerAudit_list_datagrid', thisrow);
		}
		
		
		function disponseExpert() {
			if(thisrow == null) {
				top.$.messager.alert('系统提示', '数据完整性缺失，请关闭后刷新页面重试!', 'warning');
			}
			
			//校验身份
			var type = false;
			if(thisrow.logStatusId != Process_type.dsl && thisrow != Process_type.ysl)
				type = true;
			
			if(type && thisrow.actualApproverId != currentUserId) {
				top.$.messager.alert('系统提示', '您没有分发权限！', 'warning');
				return false;
			}
			
			var type = pagetype == 'search' ? 'default' : 'submit';
			
			var str ="<div><iframe id='hiddangerAuditProcess_list_disponseOffice' src='${path}"
				+"/hiddangerAudit/beforeAudit?projectId="+thisrow.projectId
				+"&&actualApproverId="+currentUserId
				+"&&logId="+thisrow.logId
				+"&&logStatusId="+thisrow.logStatusId
				+"&&operateType=expert"
				+"' width='100%' height='100%' style='border:0'></iframe></div>";
				loaddialogbyscienceaudit('hiddangerAuditProcess_list_disponseOffice', str, thisrow.projectName + '-分发专家', 600, 350, type, 'hiddangerAudit_list_datagrid', thisrow);
		}
		
		
	</script>
</head>


<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
	    <div data-options="region:'center',border:false" style="width: 100%;">
	        <table id="hiddangerAuditProcess_list_datagrid" data-options="fit:true,border:false"></table>
	    </div>
	</div>
</body>

