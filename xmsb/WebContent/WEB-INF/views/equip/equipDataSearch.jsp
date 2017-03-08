<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>


<div id="equipInfo" style="width:100%;height:400px;">

	<form id="equip_base_form" method="post">
		<input type="hidden" name="userId" id="userId" value="${currentUser.id}"/>
	    <input type="hidden" name="gradetype" id="gradetype" value="${unit.gradetype}"/>
	    <input type="hidden" name="pageType" id="pageType" />
		<div id="equip_data_head" >
			<table id="equip_data_table" class="grid">
				<tr>
					<td>申请名称：</td>
					<td>
	    				<input type="hidden" name="id" id="id" />
	    				<input type="hidden" name="areaId" id="areaId"/>
	    				<input type="hidden" name="statusId" id="statusId"/>
	    				<input type="hidden" id="unitId" name="unitId" /> 
	    				<input type="hidden" id="grade" name="grade" />
					    <input type="text" id="applyname" name="applyname" class="easyui-textbox" data-options="editable:false,disabled:true"/>
					</td>
					<td>申请人：</td>
					<td>
					   <input type="hidden" id="applicant" name="applicant" /> 
					   <input type="text" id="applicantName" name="applicantName" class="easyui-textbox"
					    data-options="editable:false,disabled:true" />
					</td>
				</tr>
				<tr>
					<td>申请时间：</td>
					<td>
					  <input type="text" id="applytimeStr" name="applytimeStr" 
					  	class="easyui-datebox" data-options="editable:false,disabled:true">
					</td>
					<td>单位名称：</td>
					<td>
						<input type="text" id="unitName" name="unitName" class="easyui-textbox" 
							data-options="editable:false,disabled:true" />
					</td>
				</tr>
				<tr>
					<td>级别：</td>
					<td>
					  <input type="text" id="gradeName" name="gradeName" class="easyui-textbox" 
					  	data-options="editable:false,disabled:true"/>
					</td>
					<td>内设机构数量：</td>
					<td>
						<input type="text"  id="internalorgshow"  class="easyui-numberbox" 
							data-options="min:0,precision:0,required:true" data-options="editable:false,disabled:true"/>
						<input type="hidden" id="internalorg" name="internalorg"  />	
					</td>
				</tr>
				<tr>
					<td>编制人数：</td>
					<td>
						<input type="text"  id="compileshow"  class="easyui-numberbox" 
							data-options="min:0,precision:0,required:true" data-options="editable:false,disabled:true"/>
						<input type="hidden" id="compile" name="compile"  />	
					</td>
				</tr>
			</table>
		</div>
		
		<div id="live_tabs" class="easyui-tabs" style="width:100%;height:300px;">
		  <c:forEach var="tt" items="${opers}">		    	 
		     <div id="lzjcbznl" title='${tt.itemvalue}' style="padding:0px;display:block;height:261px;">		        
		        <table id='${tt.itemcode}' style="width:100%;height:261px;"></table>
		     </div>		    
		  </c:forEach>
		</div>
		
	</form>
</div>

<script type="text/javascript">

	var previousRow; //来自审核页面的数据存储
	var userId = '${currentUser.id}';

	$(function() {
		
		var auditequip = '${auditequip}';
		
		if(row != null) previousRow = row;
		//提取后台数据转换为js
		if(auditequip != '' && auditequip != null) row = JSON.parse(auditequip);

		//初始化设置JS携带参数
		$('#pageType').val(pagetype);
		
		$('#live_tabs').show();
		$('#id').val(row.id);
		$('#applicant').val(row.applicant);
		$('#areaId').val(row.areaId);
		$('#grade').val(row.grade);
		$('#unitId').val(row.unitId);
		$('#statusId').val(row.statusId);
		
		$('#applycode').textbox('setValue', row.applycode);//申请编码
		$('#gradeName').textbox('setValue', row.gradeName);//级别
		$('#unitName').textbox('setValue', row.unitName);//单位名称
	   	$('#applyname').textbox('setValue',row.applyname);//申请人
		$('#applicantName').textbox('setValue', row.applicantName);
	   	
	   	$('#applytimeStr').datebox('setValue', row.applytime);//申请时间
	   	$('#internalorgshow').numberbox('setValue', row.internalorg);//内设机构
	   	$('#compileshow').numberbox('setValue', row.compile);//编制人数
	   	
	   	setDataIsEdit();
	   	
	   	findEqchild();
	});
	

	
	function findEqchild(){
		<c:forEach var="tt" items="${opers}">
			init_eqchild_table('${path}','${tt.id}','${tt.itemcode}');
		</c:forEach>
	}
	
	function setDataIsEdit(){
		flag='disable';
		
		$('#applyname').textbox(flag);
		$('#applicantName').textbox(flag);
		$('#applytimeStr').datebox(flag);
		$('#unitName').textbox(flag);
		$('#gradeName').textbox(flag);
		$('#internalorgshow').textbox(flag);
		$('#compileshow').textbox(flag);
	}
	
	
	function init_eqchild_table(path,typeId,tableName) {
		$('#'+tableName).datagrid({
			url: '${path}/equipStandard/findEquipChild.do',
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
			toolbar:'#eqchild_toolbar',
			queryParams : {
				typeId : typeId, //传递不同子tab
				gradetype : $('#gradetype').val(),
				eqbaseId:$('#id').val(),
				selectStatus:'search'
			},
			columns:[[
				{field:'id',title:'编号',width:100,align:'center',checkbox: true},
				{field : 'name', title : '装备名', width : 100, align : 'center'},
				//标准数量根据申报单位而定，如果是省级，就只显示省级，市，县不显示
				{field : 'standarnum', title : '标准数量', width : 120, align : 'center',
					formatter : function(value,rowData){
						if("内设机构"==rowData.standartypename){
							return value+"/"+rowData.standartypename;
						}
						if("人"==rowData.standartypename){
							return 1+"/"+value+rowData.standartypename;
						}
						if("无"==rowData.standartypename){
							return value;
						}
					}
				},{field : 'equipcrite', title : '是否标配', width : 100, align : 'center'},
			    {field : 'canmatchnum', title : '可配数量', width : 100, align : 'center',
					formatter:function(value, rowData, rowIndex){
						if("人"==rowData.standartypename){
							if("㎡"==rowData.unit){
								rowData.canmatchnum=rowData.standarnum*$('#compileshow').val();
								return rowData.standarnum*$('#compileshow').val();
							}else{			
								var f = (1/rowData.standarnum)/(1/$('#compileshow').val());
								if(String(f).indexOf('.') != -1){
									if(f.toFixed(1)<1){
									  f=1;
									  rowData.canmatchnum=f;
									  return f;
									}
									 f=parseInt(f);
									 rowData.canmatchnum=f+1;
									 return f+1;
									 //rowData.canmatchnum=f.toFixed(1);//保留一位小数
								}else{
									rowData.canmatchnum=f;
									return f;
								}
							}
						}else if("内设机构"==rowData.standartypename){
							rowData.canmatchnum=rowData.standarnum*$('#internalorgshow').val();
							return rowData.standarnum*$('#internalorgshow').val();
						}else if("无"==rowData.standartypename){
							rowData.canmatchnum=$('#internalorgshow').val();
							return $('#internalorgshow').val();
						}
				    }
			   },
			   {field : 'equipnum', title : '装备数量', width : 100, align : 'center',editor:'textbox'},
			   {hidden : true, field : 'eqstandardid', title : '装备名关联ID', width : 100, align : 'center'},
			   {hidden : true, field : 'eqid', title : 'eqID', width : 100, align : 'center'}
			]]
		});
	}
	
	//查询审核意见
	function auditinfosearch() {
		if(previousRow == null) {
			top.$.messager.alert('系统提示', '数据完整性缺失，请关闭后刷新页面重试!', 'warning');
		}

		
		var str ="<div><iframe id='equipAuditProcess_list_audit' src='"+
		"${path}/equip/searchAuditLog?projectId="+previousRow.projectId+
		"&&logId="+previousRow.logId+
		"&&actualApproverId="+userId+
		"&&logStatusId="+previousRow.logStatusId+
		"' width='100%' height='100%' style='border:0'></iframe></div>";
		
		var type = pagetype == 'search' ? 'default' : 'audit';
		
		loaddialogbyaudit('equipAuditProcess_list_audit', str, previousRow.projectName + '-项目审核状态', 700, 400, type, 'equipAudit_list_datagrid', previousRow);
	}
	
	
	//用于审核操作
	function auditmethods() {
		if(previousRow == null) {
			top.$.messager.alert('系统提示', '数据完整性缺失，请关闭后刷新页面重试!', 'warning');
		}

		
		
		if(top.overall_auditStatus == 1) {
			top.$.messager.alert('系统提示', '审核操作已经完成,请勿重复审核!', 'warning');
			return;
		}
		
		var type = pagetype == 'audit_search' ? 'submit' : 'default';
		
		var str ="<div><iframe id='equipAuditProcess_audit' "
			+"src='${path}/equip/beforeAudit?projectId="+previousRow.projectId
			+"&&actualApproverId="+userId
			+"&&logId="+previousRow.logId
			+"&&logStatusId="+previousRow.logStatusId
			+"' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbyaudit('equipAuditProcess_audit', str, previousRow.projectName + '-审核操作', 600, 270, type, 'equipAuditProcess_list_datagrid', previousRow);
	}
	
</script>