package com.cody.controller.sys;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/*import cody.Util.GlobalProperty;
import cody.pojo.system.Menu;*/
import com.cody.common.core.Result;
import com.cody.common.utils.GlobalProperty;
import com.cody.common.vo.Tree;
import com.cody.controller.BaseController;
import com.cody.entity.sys.User;
import com.cody.service.sys.ResourceService;

/**
 * @description：登录退出
 * @author：wanhuan
 * @date：2016/11/18
 */
@Controller
public class LoginController extends BaseController{

    private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ResourceService resourceService;
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
    	if (!SecurityUtils.getSubject().isAuthenticated()) {
            return "/login";
        }
        return "redirect:/index";
    }
    
    /**
     * 注册
     */
    /*@RequestMapping(value = "/system/unit/")
    public String register() {
        return "redirect:/system/unit/unitRegister";
    }*/

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model,HttpSession session) {
    	User currentUser = getCurrentUser();
        List<Tree> tree = resourceService.findTree(currentUser);
        model.addAttribute("resourceTree",tree);
        session.setAttribute("currentUser", currentUser);
        return "/index";
    }
    
    /**
     * 获取
     * @param requestl
     * @param response
     * @param roleid
     * @throws IOException
     */
	@RequestMapping(value = "/menulist", produces = "text/html;charset=UTF-8")
	public void getMenuList(HttpServletRequest requestl,
			HttpServletResponse response ,String roleid) throws IOException {
		User currentUser = getCurrentUser();
		String strtreelist = new String();
		strtreelist = "[";
		List<Tree> listTree = resourceService.findTree(currentUser);
		for (Tree tree : listTree) {
			if (strtreelist != "[")
				strtreelist += ",";
			strtreelist += "{  \"text\":\"" + tree.getText() + "\",\"icon\":\""+ tree.getIconCls() + "\",\"mid\":\""+ tree.getId() + "\"}";
		}
		strtreelist += "]";
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(strtreelist);
	}
	
	@RequestMapping(value = "/treelist", produces = "text/html;charset=UTF-8")
	public void getTreeList(String name, HttpServletRequest requestl,
			HttpServletResponse response, String roleid) throws IOException {
		String id = requestl.getParameter("id");
		User currentUser = getCurrentUser();
		String strtreelist = new String();
		strtreelist = "[";
		List<Tree> listTree = resourceService.findTree(currentUser);
		List<Tree> listTreeChild=null;
			for (Tree tree : listTree) {
				listTreeChild=tree.getChildren();
				for(Tree treeTwo:listTreeChild){
				//这里的1是前台传递过来的值	
				 if(String.valueOf(id).equals(treeTwo.getPid())){//tree.getId()
						if (strtreelist != "[")
							strtreelist += ",";
						    strtreelist += "{ \"id\":\"" + treeTwo.getId() + "\", \"text\":\""
								+ treeTwo.getText() + "\",\"url\":\""
								+ GlobalProperty.getCurradress(requestl)
								+ treeTwo.getAttributes() + "\"}";
				 }	
				}
			}
		strtreelist += "]";
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(strtreelist);
	}

    /**
     * GET 登录
     *
     * @param model
     * @param request
     * @return
     */
    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request,HttpServletResponse response) {
        LOGGER.info("GET请求登录");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "/login";
    }*/

	/**
     * POST 登录 shiro 写法
     * @param username 用户名
     * @param password 密码
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result loginPost(String username, String password, HttpServletRequest request, Model model) {
        LOGGER.info("POST请求登录");
        Result result = new Result();
        if (StringUtils.isBlank(username)) {
            result.setMsg("用户名不能为空");
            return result;
        }
        if (StringUtils.isBlank(password)) {
            result.setMsg("密码不能为空");
            return result;
        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
        token.setRememberMe(true);
        try {
            user.login(token);
        } catch (UnknownAccountException e) {
            LOGGER.error("账号不存在：{}", e);
            result.setMsg("账号不存在");
            return result;
        } catch (DisabledAccountException e) {
            LOGGER.error("账号未启用：{}", e);
            result.setMsg("账号未启用");
            return result;
        } catch (IncorrectCredentialsException e) {
            LOGGER.error("密码错误：{}", e);
            result.setMsg("密码错误");
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("未知错误,请联系管理员：{}", e);
            result.setMsg("未知错误,请联系管理员");
            return result;
        }
        result.setSuccess(true);
        return result;
    }

//    /**
//     * 未授权
//     *
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/unauth")
//    public String unauth(Model model) {
//        if (SecurityUtils.getSubject().isAuthenticated() == false) {
//            return " <script type='text/javascript'> alert(1); </script>";//parent.location.href='/login'
//        }
//        return "/unauth";
//    }

    /**
     * 退出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
        LOGGER.info("登出");
        Subject subject = SecurityUtils.getSubject();
        Result result = new Result();
        subject.logout();
        result.setSuccess(true);
        return result;
    }
    
    @RequestMapping("/404")
    public String notFound(){
    	return "/404";
    }
    
    @RequestMapping("/500")
    public String exception(){
    	return "/500";
    }
}
