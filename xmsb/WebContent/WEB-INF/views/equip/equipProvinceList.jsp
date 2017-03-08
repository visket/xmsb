<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/business/equip/child/eqchild.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/equip/equipProvince.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/business/projectTypes.js" charset="utf-8"></script>

<script type="text/javascript">    
    
    $(function(){
    	
    	init_equipProvince_list_datagrid('${path}');
    	$('#save').hide();
    	$('#backstep').hide();
    	$('#live_tabs').hide();
    	
    	$('#equip_base_form').form({
			async : false,
			ajax : true,
			type : 'POST',
		    url : '${path}/equip/saveOrUpdateProvince',
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
				data = JSON.parse(data);
				if (data.success) {
					$('#id').val(data.id);
					saveAllChild(data.id);
				} else {
					$.messager.alert('系统提示', data.msg, 'warning');//$.messager是在本页面上打印消息，top.$.messager是父窗体上打印
				}
				return data;
		    }    
		});
    	
    	 $('#declareDetails').hide();
    	 $('#applicantName').textbox('setValue', '${currentUser.name}');
    	 //$('#gradeName').textbox('setValue', '${item.itemvalue}');
    	 $('#gradeName').val('${item.itemvalue}');
    	 $('#areaId').val('${unit.sysareaId}');
    	 $('#grade').val('${item.id}');
    });
 	
 	function equip_list_reset() {
		clearTableToolbar('equip_list_form');
	};
	
	function nextstep(){
		  
		var isValid = $("#equip_base_form").form('validate');
    	if (!isValid){
    		top.$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
			top.$.messager.alert({title:'系统提示', msg:'请填写必填项!', showType:'warning'});
			return false;
		}
    	$('#nextstep').hide();
    	$('#backstep').show();
    	$('#save').show();
    	$('#internalorg').val($('#internalorgshow').numberbox('getValue'));
    	$('#compile').val($('#compileshow').numberbox('getValue'));
       	$('#internalorgshow').numberbox("disable");
    	$('#compileshow').numberbox("disable");
        //$('#applynamehid').val($('#applyname').textbox('getValue'));
        $('#applyname').textbox("disable");
    	$('#live_tabs').show();
    	findEqchild();
	}
	
	function saveAll(){
		$("#equip_base_form").submit();
	}
	
	function backstep(){
		$('#nextstep').show();
		$('#save').hide();
		$('#backstep').hide();
       	$('#internalorgshow').numberbox("enable");
    	$('#compileshow').numberbox("enable");
    	$('#live_tabs').hide();
	}
	
	function findEqchild(){
	   <c:forEach var="tt" items="${opers}">
		 init_eqchild_table('${path}','${tt.id}','${tt.itemcode}');		 
	   </c:forEach>
	}
	
	function saveAllChild(id){
		<c:forEach var="tt" items="${opers}">
		   flag = eqchild_save('${path}','${tt.id}','${tt.itemcode}',id);
	           if(flag==false){
	        	   $.messager.alert('警告！', '输入的装备数量必须小于等于可配数量,请仔细检查后重新输入！', 'warning');
	        	   return;
	           }   	   
		</c:forEach>
       if(saveValid==true)
		$.messager.show({title:"系统提示",msg:"保存成功",timeout:5000,showType:'slide'});       
	}
    
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
 
    <div data-options="region:'west',border:true,split:false,title:'省级部门列表'"  style="width:14%;overflow: hidden; ">
        <ul id="organizationTree"  style="width:160px;margin: 10px 10px 10px 10px">
        </ul>
    </div>
    <div id="declareDetails" data-options="region:'center',border:true,title:'装备详情'" style="width:86%;height:600px">
       <div class="td_sinleft"">
           <a id="nextstep" href="javascript:void(0)" class="l-btn l-btn-small" style="float:left;" onclick="nextstep()"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">下一步
             </span><span class="l-btn-icon icon-usergo">&nbsp;</span></span>
            </a>
             <a id="save" href="javascript:void(0)" class="l-btn l-btn-small" style="float:left;" onclick="saveAll()"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">保存
             </span><span class="l-btn-icon icon-save">&nbsp;</span></span>
            </a>
             <a id="backstep" href="javascript:void(0)" class="l-btn l-btn-small" style="float:left;" onclick="backstep()"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">上一步
             </span><span class="l-btn-icon icon-back">&nbsp;</span></span>
            </a>
       </div>
       <div id="equipInfo" style="width:99%;height:600px">
		 <form id="equip_base_form" method="post">
			<input type="hidden" name="userId" id="userId" value="${currentUser.id}"/>
		    <input type="hidden" name="gradetype" id="gradetype" value="${unit.gradetype}"/>
		    <input type="hidden" name="pageType" id="pageType" />
		    <input type="hidden" name="id" id="id" />
		    <input type="hidden" name="orgId" id="orgId" />
		    <input type="hidden" name="areaId" id="areaId"/>
		    <input type="hidden" name="statusId" id="statusId"/>
		    <input type="hidden" id="unitId" name="unitId" /> 
		    <input type="hidden" id="grade" name="grade" />
		    <input type="hidden" id="applynamehid" name="applyname"  />
		    <input type="hidden" id="applicant" name="applicant" />
		     <input type="hidden" id="gradeName" name="gradeName" />
			<div id="equip_data_head" >
				<table id="equip_data_table" class="grid">
					<!-- <tr>
						<td>申请名称：</td>
						<td>
						    <input type="text" id="applyname" class="easyui-textbox" />
						</td>
						<td>申请人：</td>
						<td>  
						   <input type="text" id="applicantName" name="applicantName" class="easyui-textbox"
						    data-options="editable:false,disabled:true" />
						</td>
					</tr> -->
					<!-- <tr> -->
						<!-- <td>申请时间：</td>
						<td>
						  <input type="text" id="applytimeStr" name="applytimeStr" 
						  	class="easyui-datebox" data-options="editable:false">
						</td> -->
						<!-- <td>级别：</td>
						<td>
						  <input type="text" id="gradeName" name="gradeName" class="easyui-textbox" 
						  	data-options="editable:false,disabled:true"/>
						</td> -->
					<!-- </tr> -->
					<tr>
						<td>编制人数：</td>
						<td>
							<input type="text"  id="compileshow"  class="easyui-numberbox" 
								data-options="min:0,precision:0,required:true" />
							<input type="hidden" id="compile" name="compile"  />	
						</td>
						<td>内设机构数量：</td>
						<td>
							<input type="text"  id="internalorgshow"  class="easyui-numberbox" 
								data-options="min:0,precision:0,required:true" />
							<input type="hidden" id="internalorg" name="internalorg"  />	
						</td>
					</tr>
				</table>
			</div>
			<br>
			<div id="live_tabs" class="easyui-tabs" style="width:100%;height:460px">
			  <c:forEach var="tt" items="${opers}">		    	 
			     <div id="lzjcbznl" title='${tt.itemvalue}' style="padding:0px;display:block;height:261px;">		        
			        <table id='${tt.itemcode}' style="width:100%;height:261px;"></table>
			     </div>		    
			  </c:forEach>
			</div>            
		</form>
      </div>
    </div>
    <div id="toolbar" style="display: none;">
        <shiro:hasPermission name="/user/add">
            <a onclick="user_add();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/user/edit">
            <a onclick="user_edit( );" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/user/delete">
            <a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        </shiro:hasPermission>
    </div>
    
</div>