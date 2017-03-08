<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/processTypes.js" charset="utf-8"></script>

<div id="projecttypecombo" style="width:100%;text-align:center;">
    <input type="radio" name="projecttype" id="projecttyperone" value="0" style="width:20px;height:14px" >研发类</input>
    <input type="radio" name="projecttype" id="projecttypertwo" value="0" style="width:20px;height:14px" />推广应用类</input>
</div>

<form id="science_base_form">

<div id="science_div">
	<input type="hidden" name="id" id="id" />
	<input type="hidden" id="projecttype" name="projecttype" />
  	<input type="hidden" id="applicant" name="applicant" />
  	<input type="hidden" id="unitId" name="unitId" />  
     <div>
       <table style="width:100%;height:400px;border:0px">
           <tr>
			<td>申请名称：</td>
			<td>

			    <input type="text" id="applyname" name="applyname" class="easyui-textbox" data-options="editable:false,disabled:true"/>
			</td>
			<td>申请人：</td>
			<td>
			   <input type="text" id="applicantName" name="applicantName" class="easyui-textbox"
			    data-options="editable:false,disabled:true" />
			</td>
		</tr>
		<tr>
			<td>申请时间：</td>
			<td>
			  <input type="text" id="applytimeStr" name="applytimeStr" 
			  	class="easyui-datebox" data-options="editable:false,disabled:true"/>
			</td>
			<td>单位名称：</td>
			<td>
				<input type="text" id="unitName" name="unitName" class="easyui-textbox" 
					data-options="editable:false,disabled:true"/>
			</td>
		</tr>
		<tr>
			<td>承担单位：</td>
			<td>
			  <input type="text" id="bearunit" name="bearunit" 
			  	class="easyui-textbox" data-options="editable:false,disabled:true"/>
			</td>
			<td>参加单位：</td>
			<td>
				<input type="text" id="partunit" name="partunit" class="easyui-textbox" 
					data-options="editable:false,disabled:true"/>
			</td>
		</tr>
		<tr>
			<td>项目负责人：</td>
			<td>
			  <input type="text" id="personcharge" name="personcharge" 
			  	class="easyui-textbox" data-options="editable:false,disabled:true"/>
			</td>
		</tr>
          <tr>
		<td>主要内容及意义：</td>
		<td width="80%" colspan="3">
			<textarea class="textbox" id="primarycoverage" name="primarycoverage" 
			style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40"></textarea>
		</td>
		</tr>
		<tr>
		<td>项目经费(含试点示范)：</td>
		<td width="80%" colspan="3">
			<textarea class="textbox" id="budget" name="budget" 
			style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40"></textarea>
		</td>
		</tr>
		<tr>
		<td>项目实施方案：</td>
		<td width="80%" colspan="3">
			<textarea class="textbox" id="projectimplement" name="projectimplement" 
			style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40"></textarea>
		</td>
		</tr>
		<!-- 研发类 -->
		<tr id="workbase_tr" >
			<td>现有工作基础和优势：</td>
			<td width="80%" colspan="3">
				<textarea class="textbox" id="workbase" name="workbase" 
				style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40"></textarea>
			</td>
		</tr>
		<tr id="pilotdemonstration_tr" >
			<td>研发完成后预期试点示范情况：</td>
			<td width="80%" colspan="3">
				<textarea class="textbox" id="pilotdemonstration" name="pilotdemonstration" 
				style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40"></textarea>
			</td>
		</tr>
		<tr id="assessmentindex_tr">
			<td>考核指标：</td>
			<td width="80%" colspan="3">
				<textarea class="textbox" id="assessmentindex" name="assessmentindex" 
				style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40"></textarea>
			</td>
		</tr>
		<!-- 推广应用类 -->
		<tr id="conditionaladvantage_tr">
		<td>项目实施后的引领带动效应：</td>
		<td width="80%" colspan="3">
			<textarea class="textbox" id="conditionaladvantage" name="conditionaladvantage" 
			style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40"></textarea>
		</td>
		</tr>
       </table>	          
     </div>

 </div>
</form>

<div id="live_tabs" class="easyui-tabs" style="width:98%;height:300px;margin-top:0px">
	  <div id="prokeyper" title="项目主要人员" style="padding:0px;display:block;height:215px;">		        
	     <table id="prokeyper_table" style="width:100%;height:260px;"></table>
	   </div>
</div>

<script type="text/javascript">
	
	var previousRow; //来自审核页面的数据存储
	var operate_status = null;
	var userId = '${currentUser.id}';
	
	$(function() {
		//还原全局流程状态监听
		top.overall_auditStatus = 0;
		top.overall_disponseStatus = 0;
		operate_status = pagetype;
		
		
		var auditscience = '${auditscience}';
		
		if(row != null) previousRow = row;
		//提取后台数据转换为js
		if(auditscience != '' && auditscience != null) row = JSON.parse(auditscience);

		
		
		$('#id').val(row.id);
		$('#unitName').textbox('setValue', row.unitName);//单位名称
	    $('#applyname').textbox('setValue',row.applyname);//申请名称
	    
	    $('#bearunit').textbox('setValue',row.bearunit);
	    $('#partunit').textbox('setValue',row.partunit);
	    $('#personcharge').textbox('setValue',row.personcharge);
	   	
	   	$('#primarycoverage').text(row.primarycoverage);
	   	$('#budget').text(row.budget);
	   	$('#projectimplement').text(row.projectimplement);
	   	
	    $('#applytimeStr').datebox('setValue',row.applytime);
	    
	      
	    if(row.projecttype=="01"){
			$('#projecttyperone').attr("checked","checked");
			$('#projecttyperone').val(1);
			$('#projecttypertwo').val(0);
			$('#projecttype').val(1);
			$('#workbase').text(row.workbase);
			$('#pilotdemonstration').text(row.pilotdemonstration);
			$('#assessmentindex').text(row.assessmentindex);
			swtichtr("yfl");
		}else if(row.projecttype=="02"){
			 $('#projecttypertwo').attr("checked","checked");
			 $('#projecttypertwo').val(2);
			 $('#projecttyperone').val(0);
			 $('#projecttype').val(2);
			 $('#conditionaladvantage').text(row.conditionaladvantage);
			 $('#benefitanalysis').text(row.benefitanalysis);
			 swtichtr("tgyyl");
		 }
		
		
		$('input:radio').attr("disabled", "disabled");
		
		$('#primarycoverage').attr("disabled",true);
		$('#workbase').attr("disabled",true);
		$('#pilotdemonstration').attr("disabled",true);
		$('#assessmentindex').attr("disabled",true);
		$('#budget').attr("disabled",true);
		$('#projectimplement').attr("disabled",true);
		$('#conditionaladvantage').attr("disabled",true);
		$('#benefitanalysis').attr("disabled",true);
		
		$('#live_tabs').show();
		init_prokeyper_table();
		
	});
	
	function swtichtr(status){
		if(status=="yfl"){
			//研发类
			$('#workbase_tr').show();
			$('#pilotdemonstration_tr').show();
			$('#assessmentindex_tr').show();
			//推广应用类
			$('#conditionaladvantage_tr').hide();
			$('#benefitanalysis_tr').hide();
		}else if(status=="tgyyl"){
			//研发类
			$('#workbase_tr').hide();
			$('#pilotdemonstration_tr').hide();
			$('#assessmentindex_tr').hide();
			//推广应用类
			$('#conditionaladvantage_tr').show();
			$('#benefitanalysis_tr').show();
		}
			
	}
	
	
	function init_prokeyper_table() {
		$('#prokeyper_table').datagrid({
			url:  '${path}/prokeyper/find.do',
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
			sortName : 'createTime',		//按字段排序
			sortOrder : 'desc',				//倒序
			queryParams : {
				scienceId : $('#id').val(),
			},
			columns:[[
				{field:'id',title:'编号',width:100,align:'center',checkbox: true},
				{field:'name',title:'姓名',width:100,align : 'center', editor:'textbox'},
				{field:'officialcapacity',title:'职务/职称',width:100,align : 'center', editor:'textbox'},
				{field:'professbusiness',title:'专业',width:100,align : 'center', editor:'textbox'},
				{field:'sharetask',title:'分担任务',width:100,align : 'center', editor:'textbox'},
				{field:'belongunit',title:'所在单位',width:100,align : 'center', editor:'textbox'}
			]]
		});
		
	}
	
	
	
	//查询审核意见
	function auditinfosearch() {
		if(previousRow == null) {
			top.$.messager.alert('系统提示', '数据完整性缺失，请关闭后刷新页面重试!', 'warning');
		}

		var userId = '${currentUser.id}';
		var str ="<div><iframe id='scienceAuditProcess_list_audit' src='"+
		"${path}/science/searchAuditLog?projectId="+previousRow.projectId+
		"&&logId="+previousRow.logId+
		"&&actualApproverId="+userId+
		"&&logStatusId="+previousRow.logStatusId+
		"' width='100%' height='100%' style='border:0'></iframe></div>";
		
		var type = pagetype == 'search' ? 'default' : 'audit';
		if(pagetype == 'office_search' || pagetype == 'expert_search') type = pagetype;
		
		loaddialogbyaudit('scienceAuditProcess_list_audit', str, previousRow.projectName + '-项目审核状态', 700, 400, type, 'scienceAudit_list_datagrid', previousRow);
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
		if(previousRow.actualApproverId != userId) {
			top.$.messager.alert('系统提示', '您没有审核权限！', 'warning');
			return false;
		}
		
		var type = pagetype == 'search' ? 'default' : 'submit';
		
		
		var str ="<div><iframe id='scienceAuditProcess_audit' "
			+"src='${path}/science/beforeAudit?projectId="+previousRow.projectId
			+"&&actualApproverId="+userId
			+"&&logId="+previousRow.logId
			+"&&logStatusId="+previousRow.logStatusId
			+"&&operateType=audit"
			+"' width='100%' height='100%' style='border:0'></iframe></div>";
			loaddialogbyscienceaudit('scienceAuditProcess_audit', str, previousRow.projectName + '-审核操作', 600, 270, type, 'scienceAuditProcess_list_datagrid', previousRow);
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
		
		var str ="<div><iframe id='scienceAuditProcess_list_disponseOffice' src='${path}"
			+"/science/beforeAudit?projectId="+previousRow.projectId
			+"&&actualApproverId="+userId
			+"&&logId="+previousRow.logId
			+"&&logStatusId="+previousRow.logStatusId
			+"&&operateType=office"
			+"' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbyscienceaudit('scienceAuditProcess_list_disponseOffice', str, previousRow.projectName + '-分发处室', 600, 350, type, 'scienceAudit_list_datagrid', previousRow);
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
		
		var str ="<div><iframe id='scienceAuditProcess_list_disponseOffice' src='${path}"
			+"/science/beforeAudit?projectId="+previousRow.projectId
			+"&&actualApproverId="+userId
			+"&&logId="+previousRow.logId
			+"&&logStatusId="+previousRow.logStatusId
			+"&&operateType=expert"
			+"' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbyscienceaudit('scienceAuditProcess_list_disponseOffice', str, previousRow.projectName + '-分发专家', 600, 350, type, 'scienceAudit_list_datagrid', previousRow);
		
	}
	
	

</script>