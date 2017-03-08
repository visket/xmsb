<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>

<div>
    <form id="resource_edit_form" method="post">
        <table class="grid">
            <tr>
                <td>资源名称</td>
                <td><input id="resId" name="id" type="hidden"  value="" >
                <input id="resource_name" name="name"  placeholder="请输入资源名称" value="" class="easyui-textbox" data-options="required:true" ></td>
                <td>资源类型</td>
                <td>
                 <select id="resourcetype" name="resourcetype" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
                            <option value="0">菜单</option>
                            <option value="1">按钮</option>
                 </select>
                </td>
            </tr>
            <tr>
                <td>资源路径</td>
                <td><input id="resource_url" name="url" type="text" value="${resource.url}" placeholder="请输入资源路径" class="easyui-textbox"></td>
                <td>排序</td>
                <td><input id="seq" name="seq" value=""  class="easyui-numberspinner"  required="required" data-options="editable:false"></td>
            </tr>
            <tr>
                <td>菜单图标</td>
                <td ><input  id="resource_icon" name="icon" class="easyui-textbox" /></td>
                <td>状态</td>
                <td ><select id="status" name="status" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
                            <option value="1">正常</option>
                            <!-- <option value="0">停用</option> -->
                </select></td>
            </tr>
            <tr>
                <td>上级资源</td>
                <td colspan="3"><select id="pid" name="pid" ></select>
                <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">
	$(function() {

        $('#pid').combotree({
            url : '${path }/resource/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            panelMaxHeight : 160,
            value : '${resource.pid}'
        });
        
        if(pagetype!='add'){       	
      	    $('#resId').val(row.id);
            $('#resource_name').textbox('setValue',row.name);
            $('#resource_url').textbox('setValue',row.url);
            $('#seq').numberspinner('setValue',row.seq);
            $('#resourcetype').combobox('setValue',row.resourcetype);
            $('#pid').combotree('setValue',row.pid);
            /* $('#area_id').combotree('setValue',row.areaId);
            $('#org_id').combotree('setValue',row.pid);
            $('#address').textbox('setValue',row.address); */
      	}
        
        $('#resource_edit_form').form({
            async : false,
        	ajax : true,
        	iframe : true,
        	novalidate : false,
        	type : 'post',
            url : '${path }/resource/edit',
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
    				top.destroy_dialog('resource_editFun');
                }
                return result;
            }
        });

/*         $("#status").val('${resource.status}');
        $("#resourcetype").val('${resource.resourcetype}'); */
    });
    
    function confirmmethods() {
    	$('#resource_edit_form').submit();
    }
</script>