<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp" %>
<%@ include file="/commons/js/extjs.jsp" %>
<%-- <%@ include file="/commons/global.jsp" %> --%>
<script type="text/javascript">
    var resourceTree;
    $(function() {
    	
    	$('#id').val(row.id);
    	
        resourceTree = $('#resourceTree').tree({
            url : '${path }/resource/allTrees',
            parentField : 'pid',
            lines : true,
            checkbox : true,
            onClick : function(node) {
            },
            onLoadSuccess : function(node, data) {         	
                progressLoad();
                $.post( '${path }/role/findResourceIdListByRoleId', {
                    id : row.id
                }, function(result) {
                    var ids;
                    if (result.success == true && result.obj != undefined) {
                        ids = top.$.stringToList(result.obj + '');
                    }
                    if (ids.length > 0) {
                        for ( var i = 0; i < ids.length; i++) {
                            if (resourceTree.tree('find', ids[i])) {
                                resourceTree.tree('check', resourceTree.tree('find', ids[i]).target);
                            }
                        }
                    }
                }, 'json');
               progressClose();
            },
            cascadeCheck : false
        });

        $('#roleGrantForm').form({
            url : '${path }/role/grant',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                var checknodes = resourceTree.tree('getChecked');
                var ids = [];
                if (checknodes && checknodes.length > 0) {
                    for ( var i = 0; i < checknodes.length; i++) {
                        ids.push(checknodes[i].id);
                    }
                }
                $('#resourceIds').val(ids);
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    //$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    //$.modalDialog.handler.dialog('close');
                	$.messager.progress('close');
    				$.messager.show({title:"系统提示",msg:result.msg,timeout:5000,showType:'slide'});
    				//top.destroy_dialog('role_grant');
                } 
                /* else {
                    //$.messager.alert('错误', result.msg, 'error');
                } */
            }
        });
        
        //if(pagetype=='edit')
        	 //$('#id').val(row.id);
        
    });

    function checkAll() {
        var nodes = resourceTree.tree('getChecked', 'unchecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('check', nodes[i].target);
            }
        }
    }
    function uncheckAll() {
        var nodes = resourceTree.tree('getChecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('uncheck', nodes[i].target);
            }
        }
    }
    function checkInverse() {
        var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
        var checknodes = resourceTree.tree('getChecked');
        if (unchecknodes && unchecknodes.length > 0) {
            for ( var i = 0; i < unchecknodes.length; i++) {
                resourceTree.tree('check', unchecknodes[i].target);
            }
        }
        if (checknodes && checknodes.length > 0) {
            for ( var i = 0; i < checknodes.length; i++) {
                resourceTree.tree('uncheck', checknodes[i].target);
            }
        }
    }
    	
    function confirmmethods(){
    	$('#roleGrantForm').submit();
    }
</script>
<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'west'" title="系统资源" style="width: 300px; padding: 1px;">
        <div class="well well-small">
            <form id="roleGrantForm" method="post">
               <input id="id"  name="id" type="hidden"  value="${id}"  />
                <%-- <input name="id" type="hidden"  value="${id}" readonly="readonly"> --%>
                <!-- <input name="id"  type="hidden"  id="id"  value="" /> -->
                <ul id="resourceTree"></ul>
                <input id="resourceIds" name="resourceIds" type="hidden" />
            </form>
        </div>
    </div>
    <div data-options="region:'center'" title="" style="overflow: hidden; padding: 10px;">
        <div>
            <button class="btn btn-success" onclick="checkAll();">全选</button>
            <br /> <br />
            <button class="btn btn-warning" onclick="checkInverse();">反选</button>
            <br /> <br />
            <button class="btn btn-inverse" onclick="uncheckAll();">取消</button>
        </div>
    </div>
</div>