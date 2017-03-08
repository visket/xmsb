<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="user_add_form" method="post">
            <table class="grid">
                <tr>
                    <td>登录名</td>
                    <td><input name="loginname" type="text" placeholder="请输入登录名称" class="easyui-textbox" data-options="required:true" value=""></td>
                    <td>姓名</td>
                    <td><input name="name" type="text" placeholder="请输入姓名" class="easyui-textbox" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input name="password" type="password" placeholder="请输入密码" class="easyui-textbox" data-options="required:true"></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
                            <option value="0" selected="selected">男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>年龄</td>
                    <td><input type="text" name="age" class="easyui-numberbox"/></td>
                    <td>用户状态</td>
                    <td>
                        <select id="status" name="status" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
                                <option value="1">正常</option>
                                <option value="0">停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>部门</td>
                    <td><select id="organizationId" name="organizationId"  class="easyui-validatebox" data-options="required:true"></select></td>
                    <td>角色</td>
                    <td><select id="roleIds" name="roleIds" ></select></td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td>
                        <input type="text" name="phone" class="easyui-numberbox"/>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function() {

        $('#organizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto'
        });

        $('#roleIds').combotree({
            url: '${path }/role/tree',
            multiple: true,
            required: true,
            panelHeight : 'auto'
        });
        
        $('#user_add_form').form({
        	async : false,
        	ajax : true,
        	iframe : true,
        	novalidate : false,
        	type : 'post',
            url : '${path }/user/add',
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
    				top.destroy_dialog('user_addFun');
                }
                return result;
            }
        });
        
    });
    
    function confirmmethods() {
    	$('#user_add_form').submit();
    }
</script>