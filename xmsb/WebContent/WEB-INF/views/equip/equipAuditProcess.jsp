<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<%-- <script type="text/javascript" src="${staticPath }/static/ext/business/equip/audit/equipAuditProcess.js" charset="utf-8"></script> --%>
<script type="text/javascript" src="${staticPath }/static/ext/business/processTypes.js" charset="utf-8"></script>



<html>
<head>
<title>装备项目审核-查看所有流程状态</title>
	<script type="text/javascript"> 
		var thisrow;
		var equip_path;
	    var currentUserId = '${currentUser.id}';
		$(function(){
			
			thisrow = row;
	    	init_equipAuditProcess_list_datagrid('${path}', thisrow.projectId);
	    	
	    });
	    
		function init_equipAuditProcess_list_datagrid(path, projectId){
			equip_path=path;
			$('#equipAuditProcess_list_datagrid').datagrid({
				url: path+'/equip/auditProcess',
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
				toolbar:'#equipAuditProcess_datagrid_toolbar',
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
			var rows = $('#equipAuditProcess_list_datagrid').datagrid('getRows');
			var lastRow = rows[rows.length-1];
			//校验身份
			if(lastRow.actualApproverId != currentUserId) {
				top.$.messager.alert('系统提示', '您没有审核权限！', 'warning');
				return false;
			}

			//校验流程状态
			if(lastRow.logStatusId != Process_type.dsl && lastRow.logStatusId != Process_type.ysl) {
				top.$.messager.alert('系统提示', '该审核已执行完毕！', 'warning');
				return false;
			}
			
			if(top.overall_auditStatus == 1) {
				top.$.messager.alert('系统提示', '审核操作已经完成,请勿重复审核!', 'warning');
				return;
			}
			
			var type = pagetype == 'audit' ? 'submit' : 'default';
			
			var str ="<div><iframe id='equipAuditProcess_audit' src='${path}"
				+"/equip/beforeAudit?projectId="+thisrow.projectId
				+"&&actualApproverId="+currentUserId
				+"&&logId="+thisrow.logId
				+"&&logStatusId="+thisrow.logStatusId
				+"' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbyaudit('equipAuditProcess_audit', str, thisrow.projectName + '-审核操作', 600, 270, type, 'equipAuditProcess_list_datagrid', thisrow);
		}
		
		
	</script>
</head>


<body>

	<div class="easyui-layout" data-options="fit:true,border:false">
	   
	    <div data-options="region:'center',border:false" style="width: 100%;">
	        <table id="equipAuditProcess_list_datagrid" data-options="fit:true,border:false"></table>
	    </div>
	    
	    <!-- <div id="equipAuditProcess_datagrid_toolbar" style="height:25px">
	    	<div class="td_left">
	            <a onclick="equipAuditProcess_audit()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-useredit'">审核</a>
	    	</div>
	    </div> -->
	</div>

</body>

