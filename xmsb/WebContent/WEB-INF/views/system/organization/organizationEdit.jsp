<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-easyui.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/js/coderule.js" charset="utf-8"></script>

<div style="padding: 3px;">
    <form id="organizationAddForm" method="post">
           <input type="hidden" name="pageType" id="pageType" >
           <input type="hidden" name="id" id="id" >
        <table class="grid">
            <tr>
               
                <td>组织名称</td>
                <td><input id="org_name"  name="name" type="text" placeholder="请输入组织名称" class="easyui-textbox" data-options="required:true" ></td>
                <td>排序</td>
                <td><input id="seq" name="seq" value="" class="easyui-numberspinner"  required="required" data-options="required:true,editable:false" ></td>
            </tr>
            <tr>
            <td>上级组织</td>
                <td  ><select id="org_id" name="pid"  data-options="required:true,editable:false" ></select>
                <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#org_id').combotree('clear');" >清空</a></td> 
                <td>组织类型</td>
                <td>
                    <input type="text" id="org_type" name="type" class="easyui-combobox" data-options="required:true,editable:false" > 
                </td>
            </tr>
            <tr>
             <td>所属区域</td>
                <td>
                	<select id="area_id" name="areaId"  data-options="required:true,editable:false"  >
                	</select>
                </td>
                <td>地址</td>
                <td  ><input id="address" name="address" class="easyui-textbox"   /></td>
            </tr>
            <tr>
               <td>行政区划</td>
               <td>
               		<input id="org_code" name="code" class="easyui-textbox" />
               </td>
               <td> 联系人</td>
               <td>
               		<input id="linkmanname" name="linkmanname" class="easyui-textbox"/>
               </td>
            </tr>
            <tr>
            	<td>联系号码</td>
            	<td><input id="phone" name="phone" class="easyui-textbox"/></td>
            	<td></td>
            	<td></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">

    $(function() {
    	
    	$('#pageType').val(pagetype);
    	
    	//查询区域编码zzlx DWLB
    	loadDictionary('${path }','org_type','DWLB');
    	
    	$('#area_id').combotree({
            url : '${path }/area/allTree',
            parentField : 'pid',//parentId pid
            lines : true,
            panelMaxHeight : 160,
            panelHeight : 'auto'
        });
        
        $('#org_id').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            panelMaxHeight : 160,
            panelHeight : 'auto'
        });
    
        if(pagetype!='add'){   
        	console.log(row);
      	    $('#id').val(row.id);
            $('#org_name').textbox('setValue',row.name);           
            $('#seq').numberspinner('setValue',row.seq);       
            $('#org_type').combobox('setValue',row.type);           
            $('#area_id').combotree('setValue',row.areaId);
            $('#org_id').combotree('setValue',row.pid);
            $('#address').textbox('setValue',row.address);
            $('#org_code').textbox('setValue',row.code);
            $('#linkmanname').textbox('setValue',row.linkman);
            $('#phone').textbox('setValue',row.telephone);
      	}
        
        $('#organizationAddForm').form({
        	async : false,
        	ajax : true,
        	iframe : true,
        	novalidate : false,
        	type : 'post',
            url : '${path }/organization/edit',
            onBeforeLoad : function() {
            	$.messager.progress();
            },
            onSubmit : function(param) {
                var isValid = $(this).form('validate');
                return isValid;
            },
            success : function(result) {
                result = $.parseJSON(result);
                if (result.success) {
                	$.messager.progress('close');
    				$.messager.show({title:"系统提示",msg:result.msg,timeout:5000,showType:'slide'});
    				top.destroy_dialog('resource_addFun');
                }
                return result;
            }
        });
    });
    
    function confirmmethods() {
    	  	
      if(pagetype!='add'){
//     	  var data = $('#org_id').combotree('tree').tree('getSelected');  	
//           var _find = data.code.match(new RegExp("_","g"));
//           var orgCode=coding_rule(data,_find);        
//           if(orgCode=="wftj")
//           	return;
//           $('#org_code').val(orgCode);  
      }	
    	$('#organizationAddForm').submit();
    
    }
</script>