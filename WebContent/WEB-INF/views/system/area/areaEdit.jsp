<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<%@ include file="/commons/js/extjs.jsp" %>
<script type="text/javascript" src="${staticPath }/static/ext/js/coderule.js" charset="utf-8"></script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
        <form id="edit_area_form" method="post">
           <input type="hidden" name="pageType" id="pageType" >
           <input type="hidden" name="id" id="id" >
            <table class="grid">
            	<tr>
                	<td>上级区域</td>
                	<td>
                		<select id="area_id" name="areaId" style="width:200px;" data-options="required:true"></select>
                		<input id="parentId" name="parentId" type="hidden" />
<!--                 		<input id="area_code" name="code" type="hidden" /> -->
                	</td>
                </tr>
                <tr>
                    <td>区域名称</td>
                    <td><input id="areaName" name="name" type="text" placeholder="请输入区域名称" style="width:200px;" class="easyui-textbox" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>行政区划</td>
                    <td><input id="code" name="code" type="text" style="width:200px;" class="easyui-textbox" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>排序</td>
                    <td><input id="seq"  name="seq"  class="easyui-numberspinner" style="width:200px;" data-options="editable:false" value="1" /></td>
                </tr>
                <tr>
                    <td>状态</td>
                    <td >
                        <select id="status" name="status" class="easyui-combobox" data-options="width:200,editable:false,panelHeight:'auto'">
                                    <option value="1">正常</option>
                                   <!--  <option value="0">停用</option> -->
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>级别</td>
                    <td >
                        <select id="gradetype" name="gradetype" class="easyui-combobox" data-options="width:200,editable:false,panelHeight:'auto',required:true">
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">

$(function() {
	$('#pageType').val(pagetype);
	loadDictionary('${path }', 'gradetype', 'DWDJ');
	$('#area_id').combotree({
        url : '${path }/area/allTree',
        parentField : 'pid',//pid parentId
        lines : true,
        panelMaxHeight : 160,//设置高度出现滚动条
        panelHeight : 'auto'
    });
	//debugger;
	if(pagetype!='add'){
	  $('#id').val(row.id);
      $('#areaName').textbox('setValue',row.name);
      $('#code').textbox('setValue',row.code);
      $('#area_id').combotree('setValue',row.pid);      
      $('#seq').numberspinner('setValue',row.seq);
      $('#gradetype').combobox('setValue',row.gradetype);
     /*  $('#status').combobox('setValue',row.status); */
	}
	
    $('#edit_area_form').form({
    	async : false,
    	ajax : true,
    	iframe : true,
    	novalidate : false,
    	type : 'post',
        url : '${path }/area/edit',
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
				top.destroy_dialog('role_addFun');
            }
            return result;
        }
    });
});

function confirmmethods(){
	
	var data = $('#area_id').combotree('tree').tree('getSelected');
//     var _find = data.code.match(new RegExp("_","g"));
//     var areaCode=coding_rule(data,_find);
//     if(areaCode=="wftj")
//     	return;
//     $('#area_code').val(areaCode);
    $('#parentId').val(data.id);
	$('#edit_area_form').submit();
}	

</script>