<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/equip/child/eqchild.js" charset="utf-8"></script>


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
					    <input type="text" id="applyname" name="applyname" class="easyui-textbox" 
					     data-options="required:true" />
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
					  	class="easyui-datebox" data-options="editable:false">
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
							data-options="min:0,precision:0,required:true" />
						<input type="hidden" id="internalorg" name="internalorg"  />	
					</td>
				</tr>
				<tr>
					<td>编制人数：</td>
					<td>
						<input type="text"  id="compileshow"  class="easyui-numberbox" 
							data-options="min:0,precision:0,required:true" />
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
	var previousRow = null;
	
	$(function() {
		$('#live_tabs').hide();
		
		//初始化设置JS携带参数
		$('#pageType').val(pagetype);
		
		if(pagetype == 'edit' || pagetype == 'search') {	
			if(row) previousRow = row;
			
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
		   	findEqchild();
		} else {
			parent.$('.dialog-button a:eq(1)').hide();
			parent.$('.dialog-button a:eq(2)').hide();
			$('#areaId').val('${unit.sysareaId}');
			$('#applicant').val('${currentUser.id}');
			$('#applicantName').textbox('setValue', '${currentUser.name}');
			$('#unitId').val('${unit.id}');
			$('#unitName').textbox('setValue', '${unit.name}');
			$('#grade').val('${item.id}');
			$('#gradeName').textbox('setValue', '${item.itemvalue}');
			$('#applytimeStr').datebox('setValue', new Date().toLocaleDateString());
		}
		
		//注意表头(主表单)要判断新增与保存，子表单已经判断完
		$('#equip_base_form').form({
			async : false,
			ajax : true,
			type : 'POST',
		    url : '${path}/equip/saveOrUpdate',
		    onSubmit: function(param){

		    	var isValid = $(this).form('validate');
		    	if (!isValid){
		    		top.$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					top.$.messager.alert({title:'系统提示', msg:'请填写必填项!', showType:'warning'});
					return false;
				}
				return isValid;

		    },    
		    success:function(data){
		    	top.$.messager.progress('close');
				data = JSON.parse(data);
				if (data.success) {				
					$('#id').val(data.id);
					saveAllChild(data.id);				
					if($('#pageType').val() == 'add') {
						$('#pageType').val('edit');
						parent.$('.dialog-button a:eq(1)').show();
					}					
				} else {
					$.messager.alert('系统提示', data.msg, 'warning');//$.messager是在本页面上打印消息，top.$.messager是父窗体上打印
				}
				return data;
		    }    
		});

		setDataIsEdit();
		
	});
	
	function saveAllChild(id){

		<c:forEach var="tt" items="${opers}">
		   flag = eqchild_save('${path}','${tt.id}','${tt.itemcode}',id);
	           if(flag==false){
	        	   $.messager.alert('警告！', '输入的装备数量必须小于等于可配数量,请仔细检查后重新输入！', 'warning');
	        	   return;
	           }
		</c:forEach>
		
	
     if(saveValid==true)
		top.$.messager.show({title:"系统提示",msg:"保存成功",timeout:5000,showType:'slide'});
       
	}
	
	function confirmmethods() {
		//由于numberbox设置了disable true 值无法传递到后台,所以要建立对应的隐藏域传值
    	$('#internalorg').val($('#internalorgshow').numberbox('getValue'));
    	$('#compile').val($('#compileshow').numberbox('getValue'));
		$("#equip_base_form").submit();
	}
	
	function nextstep(){
		var isValid = $("#equip_base_form").form('validate');
    	if (!isValid){
    		top.$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
			top.$.messager.alert({title:'系统提示', msg:'请填写必填项!', showType:'warning'});
			return false;
		}
    	
    	$('#internalorgshow').numberbox("disable");
    	$('#compileshow').numberbox("disable");
    	$('#live_tabs').show();
    	findEqchild();
    	
    	parent.$('.dialog-button a:eq(0)').hide();
		parent.$('.dialog-button a:eq(1)').hide();
		parent.$('.dialog-button a:eq(2)').show();
    	
    	return isValid;
	}
	
	function findEqchild(){
		<c:forEach var="tt" items="${opers}">
		 init_eqchild_table('${path}','${tt.id}','${tt.itemcode}');		 
	   </c:forEach>
	 }
	
	function setDataIsEdit(){
	
		var flag='enable';
		if(pagetype=='search')
			flag='disable';
		
		$('#applyname').textbox(flag);
		$('#applicantName').textbox(flag);
		$('#applytimeStr').datebox(flag);
		$('#unitName').textbox(flag);
		$('#gradeName').textbox(flag);
		$('#internalorgshow').textbox(flag);
		$('#compileshow').textbox(flag);
	}
	
	
	function confirmdeclare() {
		var userId = '${currentUser.id}';
		
		//当为新增状态进入，给前端绑定伪对象数据
		if(previousRow == null) {
			previousRow = {};
			previousRow.id = $('#id').val();
			previousRow.unitId = $('#unitId').val();
			previousRow.applicant = $('#applicant').val();
		}
		
		if(previousRow.applicant != userId) {
			top.$.messager.alert('系统提示', '您没有审核权限！', 'warning');
			return false;
		}
		
		top.$.messager.confirm('确认', '是否发起项目申报？', function(b) {
	        if (b) {
	            progressLoad();
	            $.post('${path}/equip/declare', {
	                projectId:previousRow.id, declareUnitId:previousRow.unitId, declarerId:userId, 
	                processId:previousRow.processId, logId:previousRow.logId
	            }, function(result) {
	                if (result.success) {
	                	top.$.messager.show({title:"系统提示", msg:result.msg, timeout:5000, showType:'slide' });
	                	$('#equip_list_datagrid').datagrid("reload");
	                	
	                	parent.$('.dialog-button a:eq(1)').hide();
                		parent.$('.dialog-button a:eq(2)').hide();
                		
	                } else {
	                	top.$.messager.alert({title:"系统提示", msg:result.msg, timeout:5000, showType:'slide' });
	                }
	                progressClose();
	            }, 'JSON');
	        }
	    });
	}
	

</script>