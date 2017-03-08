<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/css/basecss.jsp"%>
<%@ include file="/commons/js/basejs.jsp"%>
<%@ include file="/commons/js/extjs.jsp"%>

<!-- 定义完整路径，前端跳转等方法的前缀，示例：http://localhost：8080/项目名/-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!--注册后台资源类调用对象，采用c:标签为资源文件定义路径-->
<html>
<head>
<base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目申报管理系统</title>
<style type="text/css">
.line {
	position: absolute;
	z-index: 9999;
	width: 1px;
	height: 1px;
	font-size: 1px;
	background-color: #0000FF;
	overflow: hidden;
}
</style>
<script type="text/javascript" src="${staticPath }/static/ext/js/menu-tree.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/ext/js/tabs.js" charset="utf-8"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/json2.js'/>"></script> --%>

<script type="text/javascript">
	var highchartsId=null;
	
	var overall_auditStatus = 0; //全局流程审核状态，实时
	var overall_disponseStatus = 0; //全局流程分发状态，实时
	var overall_disponseOffice = 0; //全局流程分发状态，实时
	var overall_disponseExpert = 0; //全局流程分发状态，实时
	
	$(function() {	
		
	});
	
	var strurl="${staticPath }";
	
	function destroy_dialog(dialogId) {
		$('#' + dialogId).dialog('destroy');
	}

	/**
	 * 退出登陆
	 */
	var logout = function(path){

		$.messager.progress({msg:'注销中……'});
		var url = path +"/logout";
		//var obj = ajaxSubmit(url,'');
			
		 $.ajax({
	         url: url,
	         type: 'POST',
	         beforeSend : function() {
				$.messager.progress({
					text : '正在退出系统...'
					
				});
			 },
	         success: function (data) {
	         
	        	 $.messager.progress('close');      	 
	        		if(data.success){
	        
	        			top.window.location.href = "login.jsp";
	        		}
	         },
	         error: function (xhr) {
	        	 $.messager.alert('系统错误', data.msg, 'warning');
	         }
	     });
		//showMsg("系统提示",data.message);
	};
</script>


<style type="text/css">
.tstag {
	font-size: 8px;
	font-family: 楷体;
	color: #000;
	font-weight: bold;
}
.drag-item{
			list-style-type:none;
			display:block;
			padding:0px;

			margin:0px;
			width:100%;
			background:#fafafa;
			color:#444;
		}
		.indicator{
			position:absolute;
			font-size:9px;
			width:10px;
			height:10px;
			display:none;
			color:red;
		}
</style>


</head>
<body id="testBody" class="easyui-layout" >

    <!-- 顶部图片区 -->
	<div data-options="region:'north',border:false"   style="text-align:right;vertical-align:bottom; background:#03528b; background-repeat:no-repeat;height:74px;background-image:url('${resourcesPath }/images/banner_011.jpg');">
	 
	    <a style="color:#fff;margin-top:48px"  class="easyui-linkbutton"  data-options="plain:true" onclick="logout('${staticPath }');">${currentUser.name}(${currentUser.unName}),你好  | 注销</a>
	</div>

    <!-- 主界面左部菜单 -->
	<div data-options="region:'west',split:true,title:'功能导航'"
		style="width: 190px; padding: 0px;">
		<div id="navMenu" class="easyui-accordion"
			data-options="fit:true,border:false,"></div>
	</div>
	
	<!-- 主界面中部 -->
	<div data-options="region:'center'" style="overflow-x: hidden">
		<div id="tabs" class="easyui-tabs"
			data-options="fit:true,border:false">
		<div id="tabhome" title="首页">
				<div >
			 
		<div class="drag-item">

<!-- <div id="p" class="easyui-panel" title="科技项目" style="width:100%;height:100%;"
				data-options="iconCls:'icon-save',collapsible:true,region:'center'">
			<p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
			<ul>
				<li>easyui is a collection of user-interface plugin based on jQuery.</li>
				<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
				<li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
				<li>complete framework for HTML5 web page.</li>
				<li>easyui save your time and scales while developing your products.</li>
				<li>easyui is very easy but powerful.</li>
			</ul>
		</div> -->

</div>
		<div class="drag-item">
<!-- <div id="p" class="easyui-panel" title="装备项目" style="width:100%;height:100%;"
				data-options="iconCls:'icon-save',collapsible:true">
			<p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
			<ul>
				<li>easyui is a collection of user-interface plugin based on jQuery.</li>
				<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
				<li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
				<li>complete framework for HTML5 web page.</li>
				<li>easyui save your time and scales while developing your products.</li>
				<li>easyui is very easy but powerful.</li>
			</ul>
		</div> -->

</div>

</div>
		<div class="drag-item">

<!-- <div id="p" class="easyui-panel" title="隐患项目" style="width:100%;height:100%;"
				data-options="iconCls:'icon-save',collapsible:true">
			<p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
			<ul>
				<li>easyui is a collection of user-interface plugin based on jQuery.</li>
				<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
				<li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
				<li>complete framework for HTML5 web page.</li>
				<li>easyui save your time and scales while developing your products.</li>
				<li>easyui is very easy but powerful.</li>
			</ul>
		</div> -->

</div>
</div>
	 
 
<script>
		$(function(){
			var indicator = $('<div class="indicator">>></div>').appendTo('body');
			$('.drag-item').draggable({
				revert:true,
				deltaX:0,
				deltaY:0
			}).droppable({
				onDragOver:function(e,source){
					indicator.css({
						display:'block',
						left:$(this).offset().left-10,
						top:$(this).offset().top+$(this).outerHeight()-5
					});
				},
				onDragLeave:function(e,source){
					indicator.hide();
				},
				onDrop:function(e,source){
					$(source).insertAfter(this);
					indicator.hide();
				}
			});
		});
	</script>
				 
				</div>
			</div>
		</div>
	</div>

  <!-- 主界面右边部分 -->
	<!-- <div id="rightFrame"
		data-options="region:'east',split:true,collapsed:true,title:' '"
		style="width: 380px; height: 100%; padding: 0px;">
		<div id="equipTaps" class="easyui-tabs"
			data-options="fit:true,border:false"
			style="height: auto; width: 100%">
			<div id="equiptapsreload" title="刷新">
			</div>
			<div id="equiptaps0" title="执行任务">
				<table id="equipText"></table>
			</div>
			<div id="equiptaps1" title="人员编制">
				<div id="container"></div>
			</div>
			<div id="equiptaps2" title="技术作业线">
				<div id="container2"></div>
			</div>
			<div id="equiptaps3" title="武器储备">
				<div id="container3"></div>
			</div>
			<div id="equiptaps6" title="舰艇信息">
				<div id="container6"></div>
			</div>
		</div>
	</div> -->

	<div id="loading"
		style="position: absolute; z-index: 1000; top: 0px; left: 0px; width: 100%; height: 100%; background: #E0ECFF; text-align: center; padding-top: 20%;">
		<img src="${resourcesPath }/images/loading.gif " />
	</div>

	<script type="text/javascript">
	
         //使用淡入淡出效果隐藏元素	
		 function show() {
			$("#loading").fadeOut("normal", function() {
				$(this).remove();
			});
		}
		var delayTime;
		$.parser.onComplete = function() {
			if (delayTime)
				clearTimeout(delayTime);
			delayTime = setTimeout(show, 500);
		}
		
		$(document).ready(function() {

		});
			
	</script>
</body>
</html>