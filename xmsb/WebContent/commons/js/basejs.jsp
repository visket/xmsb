 <%--标签 --%>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ include file="/commons/global.jsp"%>
 <!--[if lt IE 8]>
<script>
    alert('H+已不支持IE6-8，请使用谷歌、火狐等浏览器\n或360、QQ等国产浏览器的极速模式浏览本页面！');
</script>
<![endif]-->

<!-- 注意jquery.min.js一定要放在上面最先加载，否则前端报异常

<script type="text/javascript" src="${staticPath }/static/jquery-easyui-1.4/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/jquery-easyui-1.4/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<script type="text/javascript" src="${staticPath }/static/jquery-3.1/jquery-1.9.0.min.js" charset="utf-8"></script>
 -->


<script type="text/javascript" src="${staticPath }/static/jquery-easyui-1.5/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/jquery-easyui-1.5/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/jquery-easyui-1.5/jquery.form.js" charset="utf-8"></script>

<!-- 为保证在session失效或者其他异常退出时，不在iframe中打开登陆页，而是以最顶级页面打开登陆页 -->
<script type="text/javascript" src="${staticPath }/static/ext/js/TopWindowShow.js" charset="utf-8"></script>

<!-- easyui功能扩展封装   -->
<script type="text/javascript" src="${staticPath }/static/ext/js/calldialog.js" charset="utf-8"></script> <!-- 自定义窗口层   -->
