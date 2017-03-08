package com.cody.controller.sys;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cody.common.core.Result;
import com.cody.common.vo.Tree;
import com.cody.controller.BaseController;
import com.cody.entity.sys.Resource;
import com.cody.service.sys.ResourceService;

/**
 * @description：资源管理
 * @author：wanhuan
 * @date：2016/11/23
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    /**
     * 菜单树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree() {
        List<Tree> tree = resourceService.findTree(getCurrentUser());
        return tree;
    }

    /**
     * 资源管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "system/resource/resource";
    }

    /**
     * 资源管理列表
     *
     * @return
     */
    @RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
    @ResponseBody
    public List<Resource> treeGrid() {
        List<Resource> treeGrid = resourceService.findResourceAll();
        return treeGrid;
    }

    /**
     * 添加资源页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "/admin/resourceAdd";
    }

    /**
     * 二级资源树
     *
     * @return
     */
    @RequestMapping("/allTree")
    @ResponseBody
    public List<Tree> allTree() {
        return resourceService.findAllTree();
    }

    /**
     * 三级资源树
     *
     * @return
     */
    @RequestMapping(value = "/allTrees", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> allTrees() {
        return resourceService.findAllTrees();
    }

    /**
     * 编辑资源页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        Resource resource = resourceService.findResourceById(id);
        request.setAttribute("resource", resource);
        return "/system/resource/resourceEdit";
    }

    /**
     * 编辑与添加资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(Resource resource) {
        Result result = new Result();
        if(resource.getId()==null){
        	try {
        	  if(resource.getSeq()==null)
        		resource.setSeq(0);
                resourceService.addResource(resource);
                result.setSuccess(true);
                result.setMsg("添加成功！");
                return result;
            } catch (RuntimeException e) {
                logger.error("添加资源失败：{}", e);
                result.setMsg(e.getMessage());
                return result;
            }
        }else{
        	try {
                resourceService.updateResource(resource);
                result.setSuccess(true);
                result.setMsg("编辑成功！");
                return result;
            } catch (RuntimeException e) {
                logger.error("编辑资源失败：{}", e);
                result.setMsg(e.getMessage());
                return result;
            }	
        }
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            resourceService.deleteResourceAllByPid(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return result;
        } catch (RuntimeException e) {
            logger.error("删除资源失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

}
