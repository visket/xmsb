<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/hiddanger/hiddangerinfo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/hiddanger/hiddangersupervise.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/js/object/valid.js" charset="utf-8"></script>


<script type="text/javascript" src="${staticPath }/static/ext/business/processTypes.js" charset="utf-8"></script>

<style>
<!--
 
  a{color:#2c850;text-decoration:none;}
   a:link{color:#2c850;text-decoration:none;}
    a:visited{color:#2c850;text-decoration:none;}
     a:hover{color:#2c850;text-decoration:none;}
      a:active{color:#2c850;text-decoration:none;}

-->
</style>

<form id="hiddanger_base_form">

 <div id="hiddanger_div">
	<input type="hidden" name="id" id="id" />
	<input type="hidden" id="projecttype" name="projecttype" />
  	<input type="hidden" id="applicant" name="applicant" />
  	<input type="hidden" id="unitId" name="unitId" />  
       <table style="width:100%;height:200px;border:0px">
           <tr>
			<td>项目名称：</td>
			<td>
			   <input type="text" id="applyname" name="applyname" class="easyui-textbox" data-options="editable:false,disabled:true"/>
			</td>
			<td>项目联系人：</td>
			<td>
			   <input type="text" id="projectcontacts" name="projectcontacts" class="easyui-textbox"
			    data-options="editable:false,disabled:true"/>
			</td>
		  </tr>
		 <tr>
			<td>填报时间：</td>			
			<td>
			  <input type="text" id="applytimeStr" name="applytimeStr" 
			  	class="easyui-datebox" data-options="editable:false,disabled:true"/>
			</td>
			<td>申报单位：</td>
			<td>
				<input type="text" id="unitName" name="unitName" class="easyui-textbox" 
					data-options="editable:false,disabled:true" />
			</td>
		</tr>
		<tr>
			<td>联系电话：</td>
			<td>
			   <input type="text" id="phone" name="phone" class="easyui-numberbox" data-options="editable:false,disabled:true"/>
			</td>
		</tr>		
        <tr>
		<td>工作总结：</td>
		<td width="80%" colspan="3">
			<textarea class="textbox" id="worksummary" name="worksummary" 
			style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40"></textarea>
		</td>
		</tr>	
       </table>
 </div>
</form>

<div id="live_tabs" class="easyui-tabs" style="width:98%;height:300px;margin-top:0px">
	  
	  <div id="hiddangerinfo" title="隐患信息" style="padding:0px;display:block;height:215px;">		        
	     <table id="hiddangerinfo_table" style="width:100%;height:260px;"></table>
	   </div>
	   
	   <div id="hiddangersupervise" title="隐患挂牌督办情况" style="padding:0px;display:block;height:215px;">		        
	     <table id="hiddangersupervise_table" style="width:100%;height:260px;"></table>
	   </div>
	   
</div>

<script type="text/javascript">
	
	var previousRow = null;
	var userId = '${currentUser.id}';
	var operate_status = null;
	
	$(function() {
		//还原全局流程状态监听
		top.overall_auditStatus = 0;
		top.overall_disponseStatus = 0;
		
		operate_status = pagetype;
		
		var audithiddanger = '${audithiddanger}';
		
		
		if(row != null) previousRow = row;
		//提取后台数据转换为js
		if(audithiddanger != '' && audithiddanger != null) row = JSON.parse(audithiddanger);
	
		$('#id').val(row.id);
	    
		$('#applyname').textbox('setValue',row.applyname);
		$('#projectcontacts').textbox('setValue',row.projectcontacts);
		$('#unitName').textbox('setValue',row.unitName);
		$('#applytimeStr').datebox('setValue',row.applytime);
		$('#phone').numberbox('setValue',row.phone);
		$('#worksummary').text(row.worksummary);
		
		$('#worksummary').attr("disabled",true);
		
		$('#hiddangersupervise_table').datagrid({
			url: '${path}/hiddangersupervise/find.do',
			border:false,
			pagination:false,
			idField:'id',
			pageSize:5,
			pageList: [5,10,20],
			checkOnSelect:true,
			selectOnCheck:true,
			singleSelect:true,
			nowrap:true, 
			rownumbers : true, //显示行号
			showfooter :true,	//显示行尾
			pageNumber:1,
			fitColumns: true,
			sortName : 'createTime',			//按字段排序
			sortOrder : 'desc',				//倒序
			toolbar:'#hiddangersupervise_toolbar',
			queryParams : {
				hiddangerId : $('#id').val(),
			},
			columns:[[
				{field:'id',title:'编号',width:50,align:'center',checkbox: true},
				{field:'applyname',title:'隐患名称',width:60,align : 'center'},
				{field:'gradeName',title:'级别',width:50,align : 'center'},
				{field:'superviseorg',title:'督办机构',width:60,align : 'center'},
				{field:'listingtime',title:'挂牌时间',width:60,align : 'center'},
				{field:'governterm',title:'治理期限',width:60,align : 'center'},
				{field:'disannulterm',title:'消号时间',width:60,align : 'center'},
				{field:'termgovern',title:'未限期完成治理原因',width:100,align : 'center'},
				{field:'capitalbudget',title:'投资预算',width:60,align : 'center'},
				{field:'investedfunds',title:'投入资金',width:60,align : 'center'}
			]]
		});
		
		
		$('#hiddangerinfo_table').datagrid({
			url: '${path}/hiddangerinfo/find.do',
			border:false,
			pagination:false,
			idField:'id',
			pageSize:5,
			pageList: [5,10,20],
			checkOnSelect:true,
			selectOnCheck:true,
			singleSelect:true,
			nowrap:true, 
			rownumbers : true, //显示行号
			showfooter :true,	//显示行尾
			pageNumber:1,
			fitColumns: true,
			sortName : 'createTime',			//按字段排序
			sortOrder : 'desc',				//倒序
			toolbar:'#hiddangerinfo_toolbar',
			queryParams : {
				hiddangerId : $('#id').val(),
			},
			columns:[[
				{field:'id',title:'编号',width:50,align:'center',checkbox: true},
				{field:'screegovernName',title:'排查治理状况',width:100,align : 'center'},
				{field:'gradeName',title:'级别',width:100,align : 'center'},
				{field:'quantity',title:'数量',width:100,align : 'center', editor:'numberbox'}
			]]
		});
		
	});

	
	//查询审核意见
	function auditinfosearch() {
		if(previousRow == null) {
			top.$.messager.alert('系统提示', '数据完整性缺失，请关闭后刷新页面重试!', 'warning');
		}

		var userId = '${currentUser.id}';
		var str ="<div><iframe id='hiddangerAuditProcess_list_audit' src='"+
		"${path}/hiddangerAudit/searchAuditLog?projectId="+previousRow.projectId+
		"&&logId="+previousRow.logId+
		"&&actualApproverId="+userId+
		"&&logStatusId="+previousRow.logStatusId+
		"' width='100%' height='100%' style='border:0'></iframe></div>";

		var type = pagetype == 'search' ? 'default' : 'audit';
		if(pagetype == 'office_search' || pagetype == 'expert_search') type = pagetype;

		loaddialogbyaudit('hiddangerAuditProcess_list_audit', str, previousRow.projectName + '-项目审核状态', 700, 400, type, 'hiddangerAudit_list_datagrid', previousRow);
	}
	
	
	
	//审核
	function auditmethods() {
		if(previousRow == null) {
			top.$.messager.alert('系统提示', '数据完整性缺失，请关闭后刷新页面重试!', 'warning');
		}
		
		if(top.overall_auditStatus == 1) {
			top.$.messager.alert('系统提示', '审核操作已经完成,请勿重复审核!', 'warning');
			return;
		}
		
		//校验身份
		var type = false;
		if(previousRow.logStatusId != Process_type.dsl && previousRow != Process_type.ysl)
			type = true;
		
		//校验身份
		if(type && previousRow.actualApproverId != userId) {
			top.$.messager.alert('系统提示', '您没有审核权限！', 'warning');
			return false;
		}
		
		var type = pagetype == 'search' ? 'default' : 'submit';
		
		var str ="<div><iframe id='hiddangerAuditProcess_audit' "
			+"src='${path}/hiddangerAudit/beforeAudit?projectId="+previousRow.projectId
			+"&&actualApproverId="+userId
			+"&&logId="+previousRow.logId
			+"&&logStatusId="+previousRow.logStatusId
			+"&&operateType=audit"
			+"' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbyscienceaudit('hiddangerAuditProcess_audit', str, previousRow.projectName + '-审核操作', 600, 270, type, 'hiddangerAuditProcess_list_datagrid', previousRow);
	}
	
	
	function disponseOffice() {
		if(previousRow == null) {
			top.$.messager.alert('系统提示', '数据完整性缺失，请关闭后刷新页面重试!', 'warning');
		}
		
		//校验身份
		var type = false;
		if(previousRow.logStatusId != Process_type.dsl && previousRow != Process_type.ysl)
			type = true;
		
		if(type && previousRow.actualApproverId != userId) {
			top.$.messager.alert('系统提示', '您没有分发权限！', 'warning');
			return false;
		}
		
		var type = pagetype == 'search' ? 'default' : 'submit';
		
		var str ="<div><iframe id='hiddangerAuditProcess_list_disponseOffice' src='${path}"
			+"/hiddangerAudit/beforeAudit?projectId="+previousRow.projectId
			+"&&actualApproverId="+userId
			+"&&logId="+previousRow.logId
			+"&&logStatusId="+previousRow.logStatusId
			+"&&operateType=office"
			+"' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbyscienceaudit('hiddangerAuditProcess_list_disponseOffice', str, previousRow.projectName + '-分发处室', 600, 350, type, 'hiddangerAudit_list_datagrid', previousRow);
	}
	
	
	function disponseExpert() {
		if(previousRow == null) {
			top.$.messager.alert('系统提示', '数据完整性缺失，请关闭后刷新页面重试!', 'warning');
		}
		
		//校验身份
		var type = false;
		if(previousRow.logStatusId != Process_type.dsl && previousRow != Process_type.ysl)
			type = true;
		
		if(type && previousRow.actualApproverId != userId) {
			top.$.messager.alert('系统提示', '您没有分发权限！', 'warning');
			return false;
		}
		
		var type = pagetype == 'search' ? 'default' : 'submit';
		
		var str ="<div><iframe id='hiddangerAuditProcess_list_disponseOffice' src='${path}"
			+"/hiddangerAudit/beforeAudit?projectId="+previousRow.projectId
			+"&&actualApproverId="+userId
			+"&&logId="+previousRow.logId
			+"&&logStatusId="+previousRow.logStatusId
			+"&&operateType=expert"
			+"' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbyscienceaudit('hiddangerAuditProcess_list_disponseOffice', str, previousRow.projectName + '-分发专家', 600, 350, type, 'hiddangerAudit_list_datagrid', previousRow);
		
	}
 	

</script>