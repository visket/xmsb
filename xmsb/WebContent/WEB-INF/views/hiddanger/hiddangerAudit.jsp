<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>

<html>
<head>
<title>隐患项目审核-审核操作界面</title>

	<script type="text/javascript">    
		var thisrow;
		var userId = '${currentUser.id}';
	
	    $(function(){
	    	thisrow = row;
			$('input[name="auditModel"]:radio').each(function(param) {
				if(this.id == 'auditModel_1') this.checked = true;
			});
			
			//控制省级单位，处室，专家，只能通过
			if(thisrow.stepBackStatusId == null || thisrow.stepBackStatusId == '') {
				//$('input:radio').attr("disabled", "disabled");
				$('#radio_no').hide();
			} else {
				//此处留作后续变更radio状态通过节点绑定动态value
			}
			
	    });
	    
	    function confirmmethods() {
			if(top.overall_auditStatus == 1) {
				top.$.messager.alert('系统提示', '审核操作已经完成,请勿重复审核!', 'warning');
				return;
			}

	    	var radio = $('#hiddangerAudit_table input[name="auditModel"]:checked').val();

	    	
	    	if('2' == radio && '' == $('#logReviewcontent').val()) {
	    		top.$.messager.alert('系统提示', '请填写审核意见!', 'warning');
				return;
	    	}
	    	
	    	
	    	//审核类型
	    	thisrow.auditType = radio;
	    	//当前用户
	    	thisrow.actualApproverId = userId;
	    	//审核评价
	    	thisrow.logReviewcontent = $('#logReviewcontent').val();
	    	
	    	thisrow.projectDeclareTime = null;
	    	thisrow.logLastupdatetime = null;
	    	
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
					//data = JSON.parse(data);
					if(data.success) {
						top.$.messager.show({title:"系统提示",msg:data.msg,timeout:5000,showType:'slide'});
						top.overall_auditStatus = 1;
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

	<div data-options="fit:true,border:false" style="width: 100%;">
	    <form>
	        <table id="hiddangerAudit_table" style="width: 100%;height:130px">
	        	<tr height="29px">
	        		<td width="20%">审核状态：</td>
	        		<td width="15%">
	        			<input type="hidden" id="auditType"/>
	        			通过<input class="radioStyle" type="radio" id="auditModel_1" name="auditModel" value="1">
	        		</td>
	        		<td	id="radio_no" width="15%">
	        			不通过<input class="radioStyle" type="radio" id="auditModel_2" name="auditModel" value="2">
	        		</td>
	        		<td	width="50%"></td>
	        	</tr>
				<tr height="100px">
					<td width="20%">审核意见：</td>
					<td colspan="3">
						<textarea class="textbox" id="logReviewcontent" name="logReviewcontent" 
						style="font-size:12px;height:100px;width:100%;white-space: pre-wrap;overflow-y:auto" cols="3"></textarea>
					</td>
				</tr>
	        </table>
        </form>
	    
	    
	</div>

</body>
