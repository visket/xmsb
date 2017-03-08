<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>


<div id="storageScienceInfo" style="width:100%;height:800px;">

	<form id="storageScience_base_form" method="post">
		<div id="storageScience_data_head" >
		<table id="storageScience_data_table" class="grid" >
			<tr>
				<td id="input_title" width="110px">项目名称</td>
				<td>
    				<input type="hidden" name="id" id="id" />
    				<input type="hidden" name="pageType" id="pageType" />
    				<input type="hidden" name="statusId" id="statusId"/>
    				<input type="hidden" id="unitId" name="unitId" />
				    <input type="text" id="projectName" name="projectName" class="easyui-textbox" data-options="editable:false,disabled:true"/>
				</td>
				
			</tr>
			<tr>
				<td width="110px">项目单位</td>
				<td>
				   <input type="text" id="unitName" name="unitName" class="easyui-textbox"
				    data-options="editable:false,disabled:true" />
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<table class="grid">
						<tr>
							<td colspan="4" align="center"><label class="label_font">项目实施情况</label></td>
						</tr>
						<tr>
							<td>是否制定项目实施计划、方案</td>
							<td><input type="checkbox" id="iscustimize" /></td>
							<td>是否编制项目内部验收报告</td>
							<td><input type="checkbox" id="isinspection" /></td>
						</tr>
						<tr>
							<td>市州安监局是否组织初审</td>
							<td><input type="checkbox" id="istissuetrial" /></td>
							<td>是否已经投入使用</td>
							<td><input type="checkbox" id="isuse" /></td>
						</tr>
						<tr>
							<td>项目是否达到项目申报书要求</td>
							<td><input type="checkbox" id="issatisfieddeclare" /></td>
							<td>项目档案管理是否规范</td>
							<td><input type="checkbox" id="isrecordstandard" /></td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<table class="grid" >
						<tr align="center">
							<td colspan="5" ><label class="label_font">取得的主要成果情况</label></td>
						</tr>
						<tr align="center">
							<td>是否鉴定</td>
							<td>获国家级奖</td>
							<td>获省部级奖</td>
							<td>专利申请数</td>
							<td>发明专利</td>
						</tr>
						<tr align="center">
							<td><input type="checkbox" id="isauthenticate" name="isauthenticate" /></td>
							<td><input type="text" id="notationaward" name="notationaward" class="easyui-numberbox" data-options="min:0,precision:0" style="width:100px"/></td>
							<td><input type="text" id="provinceaward" name="provinceaward" class="easyui-numberbox" data-options="min:0,precision:0" style="width:100px"/></td>
							<td><input type="text" id="patentquantity" name="patentquantity" class="easyui-numberbox" data-options="min:0,precision:0" style="width:100px"/></td>
							<td><input type="text" id="inventpatent" name="inventpatent" class="easyui-numberbox" data-options="min:0,precision:0" style="width:100px"/></td>
						</tr>
						<tr align="center">
							<td>专利授权数</td>
							<td>发明专利授权数</td>
							<td>发表省级以上论文数</td>
							<td>国际刊物发表数</td>
							<td>出版专著</td>
						</tr>
						<tr align="center">
							<td><input type="text" id="patentauthorize" name="patentauthorize" class="easyui-numberbox" data-options="min:0,precision:0,labelAlign:'right'" style="width:100px"/></td>
							<td><input type="text" id="inventpatentauthorize" name="inventpatentauthorize" class="easyui-numberbox" data-options="min:0,precision:0" style="width:100px"/></td>
							<td><input type="text" id="provincepaper" name="provincepaper" class="easyui-numberbox" data-options="min:0,precision:0" style="width:100px"/></td>
							<td><input type="text" id="internationalperiodical" name="internationalperiodical" class="easyui-numberbox" data-options="min:0,precision:0" style="width:100px"/></td>
							<td><input type="text" id="publication" name="publication" class="easyui-numberbox" data-options="min:0,precision:0" style="width:100px"/></td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr align="center">
				<td colspan="2"><label class="label_font">资金使用情况</label></td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea class="textbox" id="captitalcondition" name="captitalcondition" 
					style="height:100px;width:100%;white-space: pre-wrap;overflow-y:auto" cols="5"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<table border="1" class="grid">
						<tr>
							<td rowspan="2">项目总投资</td>
							<td rowspan="2">
								<input type="text" id="investment" name="investment" class="easyui-numberbox" data-options="min:0,precision:3"/>
							</td>
							<td colspan="4" align="center"><label class="label_font">其中</label></td>
						</tr>
						<tr>
							<td>专项资金</td>
							<td><input type="text" id="specialfund" name="specialfund" class="easyui-numberbox" data-options="min:0,precision:3"/></td>
							<td>自筹资金</td>
							<td><input type="text" id="selffund" name="selffund" class="easyui-numberbox" data-options="min:0,precision:0"/></td>
						</tr>
						<tr>
							<td>资金开支</td>
							<td><input type="text" id="fundexpenses" name="fundexpenses" class="easyui-numberbox" data-options="min:0,precision:3"/></td>
							
						</tr>
						<tr>
							<td>开支金额</td>
							<td><input type="text" id="expensesamount" name="expensesamount" class="easyui-numberbox" data-options="min:0,precision:3"/></td>
						</tr>
						<tr>
							<td>占比情况</td>
							<td><input type="text" id="proportion" name="proportion" class="easyui-numberbox" data-options="min:0,precision:2"/></td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr align="center">
				<td colspan="2" ><label class="label_font">项目实施效果</label></td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea class="textbox" id="implementation" name="implementation" 
					style="height:100px;width:100%;white-space: pre-wrap;overflow-y:auto" cols="30"></textarea>
				</td>
			</tr>
			
			<tr align="center">
				<td colspan="2"><label class="label_font">项目管理工作经验和主要问题</label></td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea class="textbox" id="expandissue" name="expandissue" 
					style="height:100px;width:100%;white-space: pre-wrap;overflow-y:auto" cols="30"></textarea>
				</td>
			</tr>
			
			
			<tr>
				<td colspan="2">
					<table class="grid">
						<tr>
							<td>是否存在挪用资金现象</td>
							<td><input type="checkbox" id="illegalEmbezzle" name="illegalEmbezzle" /></td>
						</tr>
						<tr>
							<td>是否伪造凭证、报表和篡改会计数字</td>
							<td><input type="checkbox" id="illegalForge" name="illegalForge" /></td>
						</tr>
						<tr>
							<td>是否巧立名目、虚报专项资金</td>
							<td><input type="checkbox" id="illegalFalsereport" name="illegalFalsereport" /></td>
						</tr>
						<tr>
							<td>是否使用专项资金发放津贴、加班费、福利或实物等</td>
							<td><input type="checkbox" id="illegalWelfare" name="illegalWelfare" /></td>
						</tr>
						<tr>
							<td>是否存在动用专项资金请客送礼、铺张浪费现象</td>
							<td><input type="checkbox" id="illegalGift" name="illegalGift" /></td>
						</tr>
						<tr>
							<td>是否存在违规报销票据现象</td>
							<td><input type="checkbox" id="illegalExpense" name="illegalExpense" /></td>
						</tr>
						<tr>
							<td>是否存在使用专项资金购买办公设备或私人用品等现象</td>
							<td><input type="checkbox" id="illegalPrivate" name="illegalPrivate" /></td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr align="center">
				<td colspan="2"><label class="label_font">检查验收意见</label></td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea class="textbox" id="acceptopinion" name="acceptopinion" 
					style="height:100px;width:100%;white-space: pre-wrap;overflow-y:auto" cols="30"></textarea>
				</td>
			</tr>
			
			<tr align="center">
				<td colspan="2"><label class="label_font">市州安监局意见</label></td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea class="textbox" id="cantonalopinion" name="cantonalopinion" 
					style="height:100px;width:100%;white-space: pre-wrap;overflow-y:auto" cols="30"></textarea>
				</td>
			</tr>
			
		</table>
		
		</div>
	</form>
	
	<div id="live_tabs" class="easyui-tabs" style="width:98%;height:300px;margin-top:0px">
	  
	  <div id="storagePerson" title="验收人员名单" style="padding:0px;display:block;height:215px;">		        
	     <table id="storagePerson_table" style="width:100%;height:200px;"></table>
	         <div id="storagePerson_toolbar" class="tb_left" style="height:26px">
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="hiddangerinfo_table_insert()" data-options="iconCls:'icon-add',plain:true" style="float: left;">新增</a>
				<div class="datagrid-btn-separator"></div>
			</div>
	   </div>
	   
	</div>
	
</div>

<script type="text/javascript">


	$(function() {

		//初始化设置JS携带参数
		$('#pageType').val(pagetype);
		
		//自适应初始化当前整行数据的宽度
		var input_title_width = $('#input_title')[0].clientWidth;
		var width = $('#storageScience_data_table')[0].clientWidth - input_title_width;
		$('#projectName').textbox('resize', width);
		$('#unitName').textbox('resize', width);
		
		
		$('#id').val(row.storageId);
		$('#statusId').val(row.statusId);
		$('#unitId').val(row.unitId);
		
		$('#projectName').textbox('setValue', row.projectName);
		$('#unitName').textbox('setValue', row.unitName);
		
		$('#iscustimize').attr('value',row.iscustimize);
		$('#isinspection').attr('value',row.isinspection);
		$('#istissuetrial').attr('value',row.istissuetrial);
		$('#isuse').attr('value',row.isuse);
		$('#issatisfieddeclare').attr('value',row.issatisfieddeclare);
		$('#isrecordstandard').attr('value',row.isrecordstandard);
		$('#isauthenticate').attr('value',row.isauthenticate);
		
		$('#notationaward').numberbox('setValue', row.notationaward);
		$('#provinceaward').numberbox('setValue', row.provinceaward);
		$('#patentquantity').numberbox('setValue', row.patentquantity);
		$('#inventpatent').numberbox('setValue', row.inventpatent);
		$('#patentauthorize').numberbox('setValue', row.patentauthorize);
		$('#inventpatentauthorize').numberbox('setValue', row.inventpatentauthorize);
		$('#provincepaper').numberbox('setValue', row.provincepaper);
		$('#internationalperiodical').numberbox('setValue', row.internationalperiodical);
		$('#publication').numberbox('setValue', row.publication);
	   	
		$('#investment').numberbox('setValue', row.investment);
		$('#specialfund').numberbox('setValue', row.specialfund);
		$('#selffund').numberbox('setValue', row.selffund);
		$('#fundexpenses').numberbox('setValue', row.fundexpenses);
		$('#expensesamount').numberbox('setValue', row.expensesamount);
		$('#proportion').numberbox('setValue', row.proportion);
		
		$('#illegalEmbezzle').attr('value',row.illegalEmbezzle);
		$('#illegalForge').attr('value',row.illegalForge);
		$('#illegalFalsereport').attr('value',row.illegalFalsereport);
		$('#illegalWelfare').attr('value',row.illegalWelfare);
		$('#illegalGift').attr('value',row.illegalGift);
		$('#illegalExpense').attr('value',row.illegalExpense);
		$('#illegalPrivate').attr('value',row.illegalPrivate);
		
		$('#captitalcondition').text(row.captitalcondition);
		$('#implementation').text(row.implementation);
		$('#expandissue').text(row.expandissue);
		$('#acceptopinion').text(row.acceptopinion);
		$('#cantonalopinion').text(row.cantonalopinion);
		
	});
	

	function setDataIsEdit(){
		if(pagetype == 'edit') {
			flag='enable';
			$('#iscustimize').attr('disabled', false);
			$('#isinspection').attr('disabled', false);
			$('#istissuetrial').attr('disabled', false);
			$('#isuse').attr('disabled', false);
			$('#issatisfieddeclare').attr('disabled', false);
			$('#isrecordstandard').attr('disabled', false);
			$('#isauthenticate').attr('disabled', false);
			
			$('#captitalcondition').attr('disabled', false);
			$('#implementation').attr('disabled', false);
			$('#expandissue').attr('disabled', false);
			$('#acceptopinion').attr('disabled', false);
			$('#cantonalopinion').attr('disabled', false);
		} else {
			flag='disable';
			$('#iscustimize').attr('disabled', true);
			$('#isinspection').attr('disabled', true);
			$('#istissuetrial').attr('disabled', true);
			$('#isuse').attr('disabled', true);
			$('#issatisfieddeclare').attr('disabled', true);
			$('#isrecordstandard').attr('disabled', true);
			$('#isauthenticate').attr('disabled', true);
			
			$('#captitalcondition').attr('disabled', true);
			$('#implementation').attr('disabled', true);
			$('#expandissue').attr('disabled', true);
			$('#acceptopinion').attr('disabled', true);
			$('#cantonalopinion').attr('disabled', true);
		}
		
		$('#notationaward').numberbox(flag);
		$('#provinceaward').numberbox(flag);
		$('#patentquantity').numberbox(flag);
		$('#inventpatent').numberbox(flag);
		$('#patentauthorize').numberbox(flag);
		$('#inventpatentauthorize').numberbox(flag);
		$('#provincepaper').numberbox(flag);
		$('#internationalperiodical').numberbox(flag);
		$('#publication').numberbox(flag);
	   	
		$('#investment').numberbox(flag);
		$('#specialfund').numberbox(flag);
		$('#selffund').numberbox(flag);
		$('#fundexpenses').numberbox(flag);
		$('#expensesamount').numberbox(flag);
		$('#proportion').numberbox(flag);
		
		
	}
	
	
	
	
	//查询审核意见
	function auditinfosearch() {
		if(previousRow == null) {
			top.$.messager.alert('系统提示', '数据完整性缺失，请关闭后刷新页面重试!', 'warning');
		}

		var userId = '${currentUser.id}';
		var str ="<div><iframe id='storageScienceAuditProcess_list_audit' src='"+
		"${path}/storageScience/searchAuditLog?projectId="+previousRow.projectId+
		"&&logId="+previousRow.logId+
		"&&actualApproverId="+userId+
		"&&logStatusId="+previousRow.logStatusId+
		"' width='100%' height='100%' style='border:0'></iframe></div>";
		
		var type = pagetype == 'search' ? 'default' : 'audit';
		
		loaddialogbyaudit('storageScienceAuditProcess_list_audit', str, previousRow.projectName + '-项目审核状态', 700, 400, type, 'storageScienceAudit_list_datagrid', previousRow);
	}
	
	
	//用于审核操作
	function auditmethods() {
		if(previousRow == null) {
			top.$.messager.alert('系统提示', '数据完整性缺失，请关闭后刷新页面重试!', 'warning');
		}
		var currentUserId = '${currentUser.id}';

		//校验身份
		if(previousRow.actualApproverId != currentUserId) {
			top.$.messager.alert('系统提示', '您没有审核权限！', 'warning');
			return false;
		}
		
		var type = pagetype == 'audit_search' ? 'submit' : 'default';
		
		var str ="<div><iframe id='storageScienceAuditProcess_audit' "
			+"src='${path}/storageScience/beforeAudit?projectId="+previousRow.projectId
			+"&&actualApproverId="+currentUserId
			+"&&logId="+previousRow.logId
			+"&&logStatusId="+previousRow.logStatusId
			+"' width='100%' height='100%' style='border:0'></iframe></div>";
		loaddialogbyaudit('storageScienceAuditProcess_audit', str, previousRow.projectName + '-审核操作', 600, 270, type, 'storageScienceAuditProcess_list_datagrid', previousRow);
	}
	
</script>