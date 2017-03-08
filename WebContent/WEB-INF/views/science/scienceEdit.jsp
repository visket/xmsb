<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/prokeyper/prokeyper.js" charset="utf-8"></script>
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
  	<input type="hidden" id="applytimeBegin" name="applytimeBegin" />
  	<input type="hidden" id="applytimeEnd" name="applytimeEnd" />
  	<input type="hidden" id="pagetypeScience" name="pagetypeScience" />
     <div>
       <table style="width:100%;height:400px;border:0px">
           <tr>
			<td>项目名称：</td>
			<td>
			    <input type="text" id="applyname" name="applyname" class="easyui-textbox" data-options="required:true" />
			</td>
			<td>申请人：</td>
			<td>
			   <input type="text" id="applicantName" name="applicantName" class="easyui-textbox"
			    data-options="editable:false,disabled:true" />
			</td>
		</tr>
		<tr>
			<td>申报时间：</td>
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
			<td>承担单位：</td>
			<td>
			  <input type="text" id="bearunit" name="bearunit" 
			  	class="easyui-textbox" data-options="editable:true,required:true">
			</td>
			<td>参加单位：</td>
			<td>
				<input type="text" id="partunit" name="partunit" class="easyui-textbox" 
					data-options="editable:true,required:true" />
			</td>
		</tr>
		<tr>
			<td>项目负责人：</td>
			<td>
			  <input type="text" id="personcharge" name="personcharge" 
			  	class="easyui-textbox" data-options="editable:true,required:true">
			</td>
		</tr>
          <tr>
		<td>主要内容及意义：</td>
		<td width="80%" colspan="3">
			<textarea class="textbox" id="primarycoverage" name="primarycoverage" 
			style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40" data-options="required:true"></textarea>
		</td>
		</tr>
		<tr>
		<td>项目经费(含试点示范)：</td>
		<td width="80%" colspan="3">
			<textarea class="textbox" id="budget" name="budget" 
			style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40" data-options="required:true"></textarea>
		</td>
		</tr>
		<tr>
		<td>项目实施方案：</td>
		<td width="80%" colspan="3">
			<textarea class="textbox" id="projectimplement" name="projectimplement" 
			style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40" data-options="required:true"></textarea>
		</td>
		</tr>
		<!-- 研发类 -->
		<tr id="workbase_tr" >
			<td>现有工作基础和优势：</td>
			<td width="80%" colspan="3">
				<textarea class="textbox" id="workbase" name="workbase" 
				style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40" data-options="required:true"></textarea>
			</td>
		</tr>
		<tr id="pilotdemonstration_tr" >
			<td>研发完成后预期试点示范情况：</td>
			<td width="80%" colspan="3">
				<textarea class="textbox" id="pilotdemonstration" name="pilotdemonstration" 
				style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40" data-options="required:true"></textarea>
			</td>
		</tr>
		<tr id="assessmentindex_tr">
			<td>考核指标：</td>
			<td width="80%" colspan="3">
				<textarea class="textbox" id="assessmentindex" name="assessmentindex" 
				style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40" data-options="required:true"></textarea>
			</td>
		</tr>
		<!-- 推广应用类 -->
		<tr id="conditionaladvantage_tr">
		<td>项目实施后的引领带动效应：</td>
		<td width="80%" colspan="3">
			<textarea class="textbox" id="conditionaladvantage" name="conditionaladvantage" 
			style="height:100px;width:80%;white-space: pre-wrap;overflow-y:auto" cols="40" data-options="required:true"></textarea>
		</td>
		</tr>
       </table>	          
     </div>

 </div>
</form>

<div id="live_tabs" class="easyui-tabs" style="width:98%;height:300px;margin-top:0px">
	  <div id="prokeyper" title="项目主要人员" style="padding:0px;display:block;height:215px;">		        
	     <table id="prokeyper_table" style="width:100%;height:260px;"></table>
	         <div id="prokeyper_toolbar" class="tb_left" style="height:26px">
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="prokeyper_table_insert()" data-options="iconCls:'icon-add',plain:true" style="float: left;">新增</a>
				<div class="datagrid-btn-separator"></div>
			</div>
	   </div>
</div>

<script type="text/javascript">
	
	$(function() {
		
		$('#live_tabs').hide();
		$('#applicant').val('${currentUser.id}');
		$('#applicantName').textbox('setValue', '${currentUser.name}');
		$('#unitId').val('${unit.id}');
		$('#unitName').textbox('setValue', '${unit.name}');
		$('#applytimeStr').datebox('setValue', new Date().toLocaleDateString());
				
		$('#projecttyperone').change(function(){
			swtichtr("yfl");
			$('#projecttype').val(1);
			$('#projecttyperone').val(1);
			$('#projecttypertwo').val(0);
		});
		
		$('#projecttypertwo').change(function(){
			 swtichtr("tgyyl");
			 $('#projecttype').val(2);
			 $('#projecttypertwo').val(2);
			 $('#projecttyperone').val(0);
		});
		
		$('#science_base_form').form({
			async : false,
			ajax : true,
			type : 'POST',
		    url : '${path}/science/saveOrUpdate',
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
					init_prokeyper_table('${path}');
					top.$.messager.show({title:"系统提示",msg:"保存成功",timeout:5000,showType:'slide'});
				} else {
					if(data.msg=='每个单位每年仅允许申报一次隐患项目')
						$('#live_tabs').hide();
					top.$.messager.alert('系统提示', data.msg, 'warning');//$.messager是在本页面上打印消息，top.$.messager是父窗体上打印
				}
				return data;
		    }    
		});
		
		//初始化设置JS携带参数
		//$('#pageType').val(pagetype);
		$('#pagetypeScience').val(pagetype);
		
		if(pagetype == 'edit' || pagetype == 'search') {
					
			$('#live_tabs').show();
			parent.$('.dialog-button a:eq(0)').hide();
			parent.$('.dialog-button a:eq(1)').show();
			parent.$('.dialog-button a:eq(2)').show(); 
			
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
			init_prokeyper_table('${path}');
		  }else{
			parent.$('.dialog-button a:eq(1)').hide();
			parent.$('.dialog-button a:eq(2)').hide();  
			swtichtr("yfl");			
			$('#projecttyperone').attr("checked","checked");
			$('#projecttyperone').val(1);
			$('#projecttype').val(1);
		  }
		setDataIsEdit();		
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
	
 	function setDataIsEdit(){
		
		var flag='enable';
		if(pagetype=='search'){
			flag='disable';
			$('#primarycoverage').attr("disabled",true);
			$('#workbase').attr("disabled",true);
			$('#pilotdemonstration').attr("disabled",true);
			$('#assessmentindex').attr("disabled",true);
			$('#budget').attr("disabled",true);
			$('#projectimplement').attr("disabled",true);
			$('#conditionaladvantage').attr("disabled",true);
			$('#benefitanalysis').attr("disabled",true);
			$('#projecttyperone').attr("disabled",true);
			$('#projecttypertwo').attr("disabled",true);
			parent.$('.dialog-button a:eq(1)').hide();
		}else{
			$('#primarycoverage').attr("disabled",false);
			$('#workbase').attr("disabled",false);
			$('#pilotdemonstration').attr("disabled",false);
			$('#assessmentindex').attr("disabled",false);
			$('#budget_research').attr("disabled",false);
			$('#budget_extension').attr("disabled",false);
			$('#projectimplement').attr("disabled",false);
			$('#conditionaladvantage').attr("disabled",false);
			$('#benefitanalysis').attr("disabled",false);
		}	
		
		$('#bearunit').textbox(flag);
		$('#partunit').textbox(flag);
		$('#personcharge').textbox(flag);
		$('#applyname').textbox(flag);
		$('#applytimeStr').datebox(flag);
		$(".easyui-linkbutton").linkbutton(flag);
		
	} 
	
 	function confirmmethods() {
 		datecheck();
		$("#science_base_form").submit();
 	}
 	
 	function nextstep(){	
 		datecheck();	
 		
		var isValid = $("#science_base_form").form('validate');
    	if (!isValid){
    		top.$.messager.progress('close');	//如果表单是无效的则隐藏进度条
			top.$.messager.alert({title:'系统提示', msg:'请填写必填项!', showType:'warning'});
			return false;
		}

 		
    	//t_science_base为表名
    	var valid = new Valid('t_science_base', $('#id').val());

    	//项目名称为回显的值,比如回显项目名是否是合法的,applyname为数据库字段名,$("#applyname").val()为前台获取的值
 		valid.setFilter('项目名称', 'applyname', $('#applyname').val());
    	
    	if(!custom_Valid('${path}', valid))
    		return false;

    	
    	$("#science_base_form").submit();
        scrollfoucs();
    
        
        //通过查看元素获得
    	parent.$('.dialog-button a:eq(0)').hide();
		parent.$('.dialog-button a:eq(1)').show();
		parent.$('.dialog-button a:eq(2)').show();
	
	}
 
 	function scrollfoucs(){
		var h = $('#science_div').height(); 
        $(window).scrollTop(h);   
 	}
 	
 	function datecheck(){
 		var thisDate = $('#applytimeStr').datebox('getValue').substring(0,4);
		var applytimeBegin = thisDate+'-01-01';
		var applytimeEnd = thisDate+'-12-31';	
		$('#applytimeBegin').val(applytimeBegin);
		$('#applytimeEnd').val(applytimeEnd);
 	}

</script>