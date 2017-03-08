<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<title>大件团购网经销商平台-认证</title>
	<!-- 全局js css -->
    <%@ include file="/commons/basejs_boot.jsp" %>
	
	<!-- form -->
	<script type="text/javascript" src="${staticPath }/static/bootstrap/js/plugins/jquery/jquery.form.js" charset="utf-8"></script>
	
	<!-- jQuery Validation plugin javascript-->
    <script src="${staticPath }/static/bootstrap/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${staticPath }/static/bootstrap/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${staticPath }/static/bootstrap/js/plugins/validate/jquery.validate.extend.js"></script>
    
    
    <script type="text/javascript">
    //请求URL定义
	var CONSTANTS_URL = {
		EDITPWD_URL:"${path}/user/editUserPwd",
		LOGIN_URL:"${path}"
	};
	
	$(document).ready(function(){
    	FormValidator.init();
	});
	
	
	
	var FormValidator = function() {  
	    var handleSubmit = function() {  
	        $('#editForm').validate({  
	            errorElement : 'span',  
	            errorClass : 'help-block',  
	            focusInvalid : false,  
	            rules : {  
	            	oldPwd : {required : true},
	            	pwd : {required : true},
	            	pwd1 : {required : true, equalTo: "#pwd"}
	            },
	            messages : {
	            	/* agree : {
	            		required : '请确认是否同意'
	            	} */
	            },
	            highlight : function(element) {  
	                $(element).closest('.form-group').addClass('has-error');  
	            },  
	            success : function(label) {  
	                label.closest('.form-group').removeClass('has-error');  
	                label.remove();  
	            },  
	            errorPlacement : function(error, element) {  
	                element.parent('div').append(error);  
	            },  
	            submitHandler : function(form) {
	            	 $.post(CONSTANTS_URL.EDITPWD_URL,$(form).serializeArray(),function(result){
	 	                if (result.success) {
	 	                	alert('修改成功，请重新登录!');
	 	                    window.location.href = CONSTANTS_URL.LOGIN_URL;
	 	                }else{
	 	                     alert(result.msg);
	 	                }
	     			}); 
	            }  
	        });  
	    }  
	    return {  
	        init : function() {  
	            handleSubmit();  
	        }  
	    };  
	}();  
	
    </script>
    
    
</head>

<body class="gray-bg">

    <div class=" animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
	            <div class="ibox ">
                    <div class="ibox-title">
                        <h5>修改密码</h5>
                    </div>
                    <div class="ibox-content">
                      <form id="editForm" method="post">
                    	<div class="form-group">
                    		<label class="control-label">登录账号：</label>
                    		<span class="form-control" readonly>${user.loginname }</span>
                    	</div>
                    	<div class="form-group">
                    		<label class="control-label">原密码：</label>
                    		<input class="form-control" name="oldPwd" type="password"></input>
                    	</div>
                    	<div class="form-group">
                    		<label class="control-label">新密码：</label>
                    		<input class="form-control" name="pwd" id="pwd"  type="password"></input>
                    	</div>
                    	<div class="form-group">
                    		<label class="control-label">确认新密码：</label>
                    		<input class="form-control" name="pwd1" id="pwd1"  type="password"></input>
                    	</div>
                    	<button class="btn btn-primary btn-sm" type="submit">保存</button>
                    	</form>
					</div>
					
				</div>	
			</div>
			</div>
		</div>
	</div>

</body>

</html>
