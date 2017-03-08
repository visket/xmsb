<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<%String oids = (String)request.getAttribute("oids"); %>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="role_edit_form" method="post">
        	<input id="oids" type="hidden" value="${requestScope.oids}">
            <table class="grid">
            	<tr>
            	 <td>角色名称</td>
                    <td><input id="id"  name="id" type="hidden"  value=""  />
                    <input id="role_name" name="name" type="text" placeholder="请输入角色名称"   class="easyui-textbox" data-options="required:true" value=""></td>
                	<td>排序</td>
                    <td><input id="seq" name="seq"  class="easyui-numberspinner"   required="required" data-options="editable:false" value="${role.seq}"></td>
                </tr>
            
                <tr>
                    <td>状态</td>
	                <td >
		                <select id="status" name="status" class="easyui-combobox" data-options=" editable:false,panelHeight:'auto'">
		                            <option value="1">正常</option>
		                            <!-- <option value="0">停用</option> -->
		                </select>
	                </td>
                </tr>
                <tr>
                	<td>所属组织</td>
                	<td colspan="3">
                		<input id="org_id" name="pid"   data-options="required:true" style="width: 450px">
                	</td>
                </tr>
              
                <tr>
                    <td>备注</td>
                    <td colspan="3">
                      <input id="description" name="description"  class="easyui-textbox" style=" white-space: pre-wrap;overflow-y:auto;width:450px" /> 
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">
	
	var oldids;
    $(function() {
//     	$('#org_id').combotree({
//             url : '${path }/organization/tree',
//             parentField : 'pid',
//             lines : true,
//             panelMaxHeight : 160,
//             panelHeight : 'auto',
//         });
    	
    	$('#org_id').combotree({
            url : '${path }/organization/tree',
            checkbox :true,
            cascadeCheck: false,
            multiple : true,
            parentField : 'pid',
            panelMaxHeight : 160,
//             onLoadSuccess : function(node,data){
//             	oldids =data[0].children;
//             }
        });
    	
    	
    	$('#role_edit_form').form({
        	async : false,
        	ajax : true,
        	iframe : true,
        	novalidate : false,
        	type : 'post',
            url : '${path }/role/edit',
            onBeforeLoad : function() {
            	$.messager.progress();
            },
            onSubmit : function(param) {
                var isValid = $(this).form('validate');
                return true;
            },
            success : function(result) {
                result = $.parseJSON(result);
                if (result.success) {
                	$.messager.progress('close');
    				$.messager.show({title:"系统提示",msg:result.msg,timeout:5000,showType:'slide'});
    				//$('#id').val(result.id);
    				top.destroy_dialog('role_editFun');
                }
                return result;
            }
        });
    	
    	if(pagetype!='add'){
    		var toids =$("#oids").val();
    		var oids =new Array();
    		oids = toids.split(",");
    		  $('#id').val(row.id);
    		  $('#org_id').combotree('setValue',oids);
    		  $('#role_name').textbox('setValue',row.name);
    		  $('#seq').numberspinner('setValue',row.seq); 
    		  $('#description').textbox('setValue',row.description);
    	}
    });
    
    function confirmmethods(){
    	$('#role_edit_form').submit();
    }
</script>