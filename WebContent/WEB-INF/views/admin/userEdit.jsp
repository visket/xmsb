<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="user_edit_form" method="post">
            
            <table class="grid">
                <tr>
                    <td>登录名</td>
                    <td><input name="id" type="hidden"  value="${user.id}">
                    <input name="loginname" type="text" placeholder="请输入登录名称" class="easyui-textbox" data-options="required:true" value="${user.loginname}"></td>
                    <td>姓名</td>
                    <td><input name="name" type="text" placeholder="请输入姓名" class="easyui-textbox" data-options="required:true" value="${user.name}"></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input type="text" name="password"  class="easyui-textbox" /></td>
                    <td>性别</td>
                    <td><select name="sex" id="sex"  class="easyui-combobox" data-options=" editable:false,panelHeight:'auto'">
                            <option value="0">男</option>
                            <option value="1">女</option>
                    </select></td>
                </tr>
                <tr>
                    <td>年龄</td>
                    <td><input type="text" name="age" value="${user.age}" class="easyui-numberbox"/></td>
                    <td>用户类型</td>
                    <td><select id="usertype" name="usertype"  class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
                            <option value="0">管理员</option>
                            <option value="1">用户</option>
                    </select></td>
                </tr>
                <tr>
                    <td>部门</td>
                    <td><select id="organizationId" name="organizationId"  class="easyui-textbox" data-options="required:true"></select></td>
                    <td>角色</td>
                    <td><input  id="roleIds" name="roleIds" /></td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td>
                        <input type="text" name="phone" class="easyui-numberbox" value="${user.phone}"/>
                    </td>
                    <td>用户类型</td>
                    <td><select id="state" name="status" value="${user.status}" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
                            <option value="1">正常</option>
                            <option value="0">停用</option>
                    </select></td>
                </tr>
            </table>
          
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function() {
        var roleIds = ${roleIds };
        $("#sex").val('${user.sex}');
        $("#usertype").val('${user.usertype}');
        $("#status").val('${user.status}');
        
        $('#organizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            value : '${user.organizationId}'
        });

        $('#roleIds').combotree({
            url : '${path }/role/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            multiple : true,
            required : true,
            cascadeCheck : false,
            value : roleIds
        });
        
        $('#user_edit_form').form({
        	async : false,
        	ajax : true,
        	iframe : true,
        	novalidate : false,
        	type : 'post',
            url : '${path }/user/edit',
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
    				top.destroy_dialog('user_editFun');
                }
                return result;
            }
        });
    });
    
    function confirmmethods() {
    	$('#user_edit_form').submit();
    }
</script>