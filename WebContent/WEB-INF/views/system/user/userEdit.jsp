<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%> 
<%@ include file="/commons/js/basejs.jsp"%>
<script type="text/javascript" src="${staticPath }/static/ext/js/custom-combo.js" charset="utf-8"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="user_add_form" method="post">
            <table class="grid">
                <tr>
                    <td>登录名</td>
                    <input id="id"  name="id" type="hidden"  value=""  />
                    <td><input id="loginname"  name="loginname"  type="text"  placeholder="请输入登录名称" class="easyui-textbox" data-options="required:true" value=""></td>
                    <td>姓名</td>
                    <td><input id="name"  name="name" type="text" placeholder="请输入姓名" class="easyui-textbox" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input id="password"  name="password" type="password" placeholder="请输入密码" class="easyui-textbox" data-options="required:true"></td>
                    <td>性别</td>
                    <td>
                        <select id="sex"  name="sex" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
                            <option value="0" selected="selected">男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>年龄</td>
                    <td><input id="age"  type="text" name="age" class="easyui-numberbox"/></td>
                    <td>用户状态</td>
                    <td>
                        <select id="status" name="status" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
                                <option value="1">正常</option>
                                <option value="0">停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>机关单位</td>
                    <td>
                    	<select id="organizationId" name="organizationId"  class="easyui-validatebox " data-options="required:true"></select>
                    	<a onclick="organization_add()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
                    </td>
                    <td>角色</td>
                    <td><select id="roleIds" name="roleIds" ></select></td>
                </tr>
                <tr>
<!--                     <td>单位</td> -->
<!--                     <td> -->
<!--                         <input id="unitId" type="text" name="unitId" class="easyui-combobox" data-options="editable:true,panelHeight:'auto',required:true" /> -->
<!--                            <a onclick="" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查找</a> -->
<!--                     </td> -->
                    <td>电话</td>
                    <td>
                        <input id="phone" type="text" name="phone" class="easyui-numberbox"/>
                    </td>
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
            panelHeight : 150,
            onShowPanel : function(){
    			$("#organizationId").combotree('reload','${path }/organization/tree');
    		}
        });

        $('#roleIds').combotree({
            url: '${path }/role/tree',
            multiple: false,
//             checked :true,
            required: true,
            panelMaxHeight : 160,
            panelHeight : 'auto'
        });
        
        $('#user_add_form').form({
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
                return isValid;
            },
            success : function(result) {
                result = $.parseJSON(result);
                if (result.success) {
                	$.messager.progress('close');
    				top.$.messager.show({title:"系统提示",msg:result.msg,timeout:5000,showType:'slide'});
    				//top.destroy_dialog('user_addFun');
                }else{
                	$.messager.show({title:"系统提示",msg:result.msg,timeout:5000,showType:'slide'});
                }
                return result;
            }
        });
        
        loadAllUnitByCombobox('${path }', 'unitId');
        
    	if(pagetype!='add'){
    		 $('#id').val(row.id);
    		 $('#loginname').textbox('setValue',row.loginname);
    		 $('#name').textbox('setValue',row.name); 
    		 $('#password').textbox('setValue',row.password);
    		 $('#sex').combobox('setValue',row.sex);
    		 $('#age').numberbox('setValue',row.age);
    		 $('#status').combobox('setValue',row.status);
 
    		 $('#organizationId').combotree('setValue',row.organizationId);
    		 var rids=JSON.parse("${roleIds}");
    		 $('#roleIds').combotree('setValue',rids);
    		 $('#phone').numberbox('setValue',row.phone);
    		 $('#password').textbox('textbox').attr('readonly',true);	 
    		 $('#unitId').combobox('setValue',row.unitId);
    		 $('#roleIds').combotree('setValue',row.rolesList[0].id?row.rolesList[0].id:'');
  	  }
        
    });
    
    function confirmmethods() {
    	$('#user_add_form').submit();
    }
    
    var organization_add=function(){
	     var title='机关单位添加';
	     var str ="<iframe  id='organization_add' src='${path}/organization/editPage' width='100%' height='100%' style='border:0'></iframe>";	
	     loaddialogbynewpage('organization_add',str,title,600,380,'add','treeGrid',null);
	}
</script>