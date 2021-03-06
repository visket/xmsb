<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<script type="text/javascript" src="${staticPath }/static/ext/js/object/valid.js" charset="utf-8"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false"  style="overflow: hidden;padding: 3px;">
        <form id="dictionary_edit_form" method="POST">
            <table class="diction_Edit">
            	<tr>
            		<td>代号</td>
            		<td>
            			<input type="hidden" id="id" name="id"/>
            			<input type="hidden" id="pagetype" name="pagetype"/>
            			<input id="dictionarycode" name="dictionarycode" type="text" class="easyui-textbox">
            		</td>
            		<td>描述</td>
            		<td><input id="dictionaryvalue" name="dictionaryvalue" type="text" class="easyui-textbox" data-options="required:true"></td>
            	</tr>
            	
            </table>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(function() {
    	
    	$('#pagetype').val(pagetype);
    	
    	if(pagetype != 'add') {
    		$('#dictionarycode').textbox('setValue',row.dictionarycode);
    		$('#dictionaryvalue').textbox('setValue',row.dictionaryvalue);
    		$('#id').val(row.id);
    	} else {
    		//设置需要预设值的控件，例如时间等
    	}

        $('#dictionary_edit_form').form({
        	async : false,
        	ajax : true, //Ajax提交
        	//iframe : true,
        	novalidate : false,//需要验证
        	type : 'post',
            url : '${path }/dictionary/edit',
            onBeforeLoad : function() {
            	$.messager.progress();
            },
            onSubmit : function(param) {
				var isValid = $(this).form('validate');
		    	if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					$.messager.show({title:"系统提示",msg:'请填写必填项!',timeout:5000,showType:'slide'});
					return false;
				}
		    	var valid = new Valid('dictionary', $('#id').val());
		 		valid.setFilter('代号', 'dictionarycode', $("#dictionarycode").textbox('getValue'));
		 		valid.setFilter('描述', 'dictionaryvalue',$("#dictionaryvalue").textbox('getValue'));
		    	if(!custom_Valid('${path}', valid)) return false;
				return isValid;
            },
            success : function(result) {
            	console.info(result);
                result = $.parseJSON(result);
            	$.messager.progress('close');
 				$.messager.show({title:"系统提示",msg:result.msg,timeout:5000,showType:'slide'});
				if (result.success) {
					$('#id').val(result.id);
                }
                return result;
            }
        });
        
    });
    
    
    
    function confirmmethods() {
    	$('#dictionary_edit_form').submit();
    }

    
</script>