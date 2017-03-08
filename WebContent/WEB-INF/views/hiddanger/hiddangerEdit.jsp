<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/hiddanger/hiddangerinfo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/hiddanger/hiddangersupervise.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/js/object/valid.js" charset="utf-8"></script>

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
  	<input type="hidden" id="applytimeBegin" name="applytimeBegin" />
  	<input type="hidden" id="applytimeEnd" name="applytimeEnd" />
  	<input type="hidden" id="pagetypeScience" name="pagetypeScience" />
       <table style="width:100%;height:200px;border:0px">
           <tr>
			<td>项目名称：</td>
			<td>
			   <input type="text" id="applyname" name="applyname" class="easyui-textbox" data-options="required:true" />
			</td>
			<td>项目联系人：</td>
			<td>
			   <input type="text" id="projectcontacts" name="projectcontacts" class="easyui-textbox"
			    data-options="editable:true,required:true" />
			</td>
		  </tr>
		 <tr>
			<td>填报时间：</td>			
			<td>
			  <input type="text" id="applytimeStr" name="applytimeStr" 
			  	class="easyui-datebox" data-options="editable:false">
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
			   <input type="text" id="phone" name="phone" class="easyui-numberbox" data-options="required:true"/>
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
	         <div id="hiddangerinfo_toolbar" class="tb_left" style="height:26px">
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="hiddangerinfo_table_insert()" data-options="iconCls:'icon-add',plain:true" style="float: left;">新增</a>
				<div class="datagrid-btn-separator"></div>
			</div>
	   </div>
	   
	   <div id="hiddangersupervise" title="隐患挂牌督办情况" style="padding:0px;display:block;height:215px;">		        
	     <table id="hiddangersupervise_table" style="width:100%;height:260px;"></table>
	         <div id="hiddangersupervise_toolbar" class="tb_left" style="height:26px">
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="hiddangersupervise_table_insert()" data-options="iconCls:'icon-add',plain:true" style="float: left;">新增</a>
				<div class="datagrid-btn-separator"></div>
			</div>
	   </div>
	   
</div>

<script type="text/javascript">
	
	$(function() {
		
		$('#live_tabs').hide();
		
		$('#hiddanger_base_form').form({
			async : false,
			ajax : true,
			type : 'POST',
		    url : '${path}/hiddanger/saveOrUpdate',
		    onSubmit: function(param){
		    	var isValid = $(this).form('validate');
		    	if (!isValid){
		    		top.$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					top.$.messager.alert({title:'系统提示', msg:'请填写必填项!', showType:'warning'});
					return false;
				}
		    },    
		    success:function(data){
		    	top.$.messager.progress('close');
				data = JSON.parse(data);
				if (data.success) {
					$('#id').val(data.id);
					$('#pagetypeScience').val('savesuccess');
					$('#live_tabs').show();
					init_hiddangerinfo_table('${path}');
					init_hiddangersupervise_table('${path}');
					top.$.messager.show({title:"系统提示",msg:"保存成功",timeout:5000,showType:'slide'});
				} else {
					if(data.msg=='每个单位每年仅允许申报一次隐患项目')
						$('#live_tabs').hide();
					top.$.messager.alert('系统提示', data.msg, 'warning');//$.messager是在本页面上打印消息，top.$.messager是父窗体上打印
				}
				return data;
		    }    
		});
		
		$('#unitId').val('${unit.id}');
		$('#applicant').val('${currentUser.id}');
			
	$('#pagetypeScience').val(pagetype);	
		
    if(pagetype == 'edit' || pagetype == 'search') {
			
			$('#live_tabs').show();
			$('#id').val(row.id);
			
			parent.$('.dialog-button a:eq(0)').hide();
			parent.$('.dialog-button a:eq(1)').show();
			parent.$('.dialog-button a:eq(2)').show(); 
		     
			init_hiddangerinfo_table('${path}');
			init_hiddangersupervise_table('${path}');
			
			$('#applyname').textbox('setValue',row.applyname);
			$('#applytimeStr').datebox('setValue',row.applytime);
			$('#phone').numberbox('setValue',row.phone);
			$('#worksummary').text(row.worksummary);
			$('#projectcontacts').textbox('setValue',row.projectcontacts);
			$('#unitName').textbox('setValue',row.unitName);
		  }else{
			$('#unitName').textbox('setValue', '${unit.name}');
			$('#applytimeStr').datebox('setValue', new Date().toLocaleDateString());
			parent.$('.dialog-button a:eq(1)').hide();
			parent.$('.dialog-button a:eq(2)').hide();  
		  }
		
           setDataIsEdit(); 
	});
	
 	function setDataIsEdit(){
		
		var flag='enable';
		if(pagetype=='search'){
			flag='disable';
			$('#worksummary').attr("disabled",true);
			parent.$('.dialog-button a:eq(1)').hide();
		}else{
			$('#worksummary').attr("disabled",false);
		}
		
		$('#applyname').textbox(flag);
		$('#applytimeStr').datebox(flag);		
		$('#phone').textbox(flag);
		$('#projectcontacts').textbox(flag);
		$(".easyui-linkbutton").linkbutton(flag);
		
	} 
	
 	function confirmmethods() {
 		datecheck();
		$("#hiddanger_base_form").submit();
	}
 	
 	function nextstep(){
 		
 		datecheck();  	
    	var isValid = $("#hiddanger_base_form").form('validate');
    	if (!isValid){
    		top.$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
			top.$.messager.alert({title:'系统提示', msg:'请填写必填项!', showType:'warning'});
			return false;
		}
    	
    	//到这里了 缺项目名校验
    	var valid = new Valid('t_hiddanger_declare', $('#id').val());
 		valid.setFilter('项目名称', 'applyname', $('#applyname').val());
    	if(!custom_Valid('${path}', valid)) 
    		return false;
    	
    	$("#hiddanger_base_form").submit();
 		parent.$('.dialog-button a:eq(0)').hide();
		parent.$('.dialog-button a:eq(1)').show();
		parent.$('.dialog-button a:eq(2)').show();
	}
 
 	function scrollfoucs(){
 		
 	}
 	
 	function hiddangerinfo_insert(){
 		
 	}
 	
 	function hiddangersupervise_insert(){
 		
 	}
 	
 	function datecheck(){
 		var thisDate = $('#applytimeStr').datebox('getValue').substring(0,4);
		var applytimeBegin = thisDate+'-01-01';
		var applytimeEnd = thisDate+'-12-31';	
		$('#applytimeBegin').val(applytimeBegin);
		$('#applytimeEnd').val(applytimeEnd);
 	}

</script>