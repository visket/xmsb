package com.cody.service.sys.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cody.common.core.Constants;
import com.cody.common.shiro.DataScope;
import com.cody.common.utils.DataUtil;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.TreeInfo;
import com.cody.common.utils.UserUtils;
import com.cody.common.vo.Tree;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.Organization;
import com.cody.entity.sys.Resource;
import com.cody.entity.sys.SysArea;
import com.cody.entity.sys.User;
import com.cody.mapper.sys.ItemMapper;
import com.cody.mapper.sys.SysAreaMapper;
import com.cody.service.sys.ItemService;
import com.cody.service.sys.SysAreaService;
import com.cody.vo.sys.AreaTreeVo;
import com.google.common.collect.Lists;


/**
 * @ClassName: SysAreaServiceImpl
 * @Description: 区域类别
 * @author wanhuan
 * @date 2016年12月16日
 *
 */
@Service
public class SysAreaServiceImpl implements SysAreaService {

	private static Logger LOGGER = LoggerFactory.getLogger(SysAreaServiceImpl.class);

	@Autowired
	private SysAreaMapper sysAreaMapper;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Override
	public void findDataGrid(TreeInfo treeInfo) {
		
		List<SysArea> list = sysAreaMapper.findListByCondition(treeInfo);
		
		List<AreaTreeVo> treeList=new ArrayList<AreaTreeVo>();
		
		AreaTreeVo atv=null;
		
		int level=0;
		
		for (SysArea item: list) {
		   atv = new AreaTreeVo();
		   atv.setCode(item.getCode());
		   atv.setId(item.getId());
		   atv.setName(item.getName());
		   //atv.setText(item.getName());
		   atv.setPid(item.getParentId());
		   atv.setSeq((short)item.getSeq());
		   atv.setCreateBy(item.getCreateBy());
		   atv.setCreateDate(item.getCreateDate());
		   atv.setUpdateBy(item.getUpdateBy());
		   atv.setUpdateDate(item.getUpdateDate());
		   atv.setStatus(item.getStatus());
		   atv.setGradetype(item.getGradetype());
		   atv.setGradetypename(itemMapper.
				   selectByPrimaryKey(item.getGradetype()).getItemvalue());
		   if("0".equals(item.getParentId())||DataUtil.isNull(item.getParentId())){//取根级的区域
			   atv.setLevel(level);
			   treeList.add(atv);
		   }else{//非根级数据进行插入
			   for (int i = 0; i < treeList.size(); i++) {
				   AreaTreeVo areaTreeVo = treeList.get(i);
				   if(item.getParentId().equals(areaTreeVo.getId())){
					   atv.setLevel(areaTreeVo.getLevel() + 1);
					   treeList.add(i+1, atv);
					   break;
				   }
			   }
		   }
		   
		   String node=item.getId();
		   
		   for (SysArea sysArea : list) {
			 if(node.equals(sysArea.getParentId())){
				 atv.setLeaf(false);//有子节点
				 break;
			 }else{
				 atv.setLeaf(true);//无子节点
			 }
		   }
		   
		   atv.setLoaded(true);
		   atv.setExpanded(true);
		}
		treeInfo.setRows(treeList);
	}
   
/*	@Override
	public Integer save(SysArea record) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	//@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Integer save(SysArea record) {

		SysArea parent = null;//  record.getParentId() == null || record.getParentId() == 0
		if(DataUtil.isNull(record.getParentId())){
    		parent = new SysArea();
    		parent.setId(IDUtil.UUID());
    		//parent.setParentIds(StringUtils.EMPTY)//暂时注释
    	}
/*		else{
    		parent = findById(record.getParentId());
    	}*/
    	
		// 设置新的父节点串
    	//record.setParentId(parent.getId());
    	//record.setParentIds(parent.getParentIds()+parent.getId()+",");//暂时注释
		record.setId(IDUtil.UUID());
		return sysAreaMapper.insertSelective(record);
		//return null;
	}
	
	@Override
	public Integer update(SysArea record) {
		// TODO Auto-generated method stub
		return sysAreaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysArea findById(String id) {
		// TODO Auto-generated method stub
		return sysAreaMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delById(Integer id) {
		
	}

	@Override
	public SysArea findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//这里最好把区域的最底层层次限制，最底层不能做为父级，也无法有子级
    @Override
    public List<AreaTreeVo> findTree() {

    	List<AreaTreeVo> trees = Lists.newArrayList();
    	
        //查询所有的一级树        
        List<SysArea> sysAreas = sysAreaMapper.findSysAreaAll();
        if (sysAreas == null) {
            return null;
        }
        for (SysArea sysAreaOne : sysAreas) {
        	AreaTreeVo areaTreeVoOne = new AreaTreeVo();
        	areaTreeVoOne.setId(sysAreaOne.getId());
        	areaTreeVoOne.setText(sysAreaOne.getName());
        	areaTreeVoOne.setCode(sysAreaOne.getCode());
        	areaTreeVoOne.setPid(sysAreaOne.getParentId());
        	areaTreeVoOne.setSeq(sysAreaOne.getSeq());

            // 查询所有一级树下的菜单
            List<SysArea> sysAreaOneSon = sysAreaMapper.findSysAreaAllByPid(areaTreeVoOne.getId());

            if (sysAreaOneSon != null) {
                List<AreaTreeVo> treeOneSon = Lists.newArrayList();
                for (SysArea sysAreaTwo : sysAreaOneSon) {
                	AreaTreeVo areaTreeVoTwo = new AreaTreeVo();
                    //二级菜单的属性
                	areaTreeVoTwo.setId(sysAreaTwo.getId());
                	areaTreeVoTwo.setText(sysAreaTwo.getName());
                	areaTreeVoTwo.setCode(sysAreaTwo.getCode());
                	areaTreeVoTwo.setPid(sysAreaTwo.getParentId());
                	areaTreeVoTwo.setSeq(sysAreaTwo.getSeq());
                	
                	  //查询所有二级树下的菜单
                	    List<SysArea> sysAreaTwoSon = sysAreaMapper.findSysAreaAllByPid(areaTreeVoTwo.getId());
                	    
                	    if(sysAreaTwoSon != null){
                	    	 List<AreaTreeVo> treeTwoSon = Lists.newArrayList();
                	    	 for(SysArea sysAreaThree : sysAreaTwoSon){
                	    		 AreaTreeVo areaTreeVoThree = new AreaTreeVo();
                	    		//三级菜单的属性
                	    		 areaTreeVoThree.setId(sysAreaThree.getId());
                	    		 areaTreeVoThree.setText(sysAreaThree.getName());
                	    		 areaTreeVoThree.setCode(sysAreaThree.getCode());
                	    		 areaTreeVoThree.setPid(sysAreaThree.getParentId());
                	    		 areaTreeVoThree.setSeq(sysAreaThree.getSeq());
                	    		 //treeTwoSon.add(areaTreeVoThree);
                	    		 
                	    		 //查询所有三级树下的菜单
                	    		 List<SysArea> sysAreaThreeSon = sysAreaMapper.findSysAreaAllByPid(areaTreeVoThree.getId());
                	    		 
                	    		 if(sysAreaThreeSon != null){
                	    			 List<AreaTreeVo> treeThreeSon = Lists.newArrayList();
                	    			 for(SysArea sysAreaFour : sysAreaThreeSon){
                	    				 AreaTreeVo areaTreeVoFour = new AreaTreeVo();
                	    				 //四级菜单的属性
                	    				 areaTreeVoFour.setId(sysAreaFour.getId());
                	    				 areaTreeVoFour.setText(sysAreaFour.getName());
                	    				 areaTreeVoFour.setCode(sysAreaFour.getCode());
                	    				 areaTreeVoFour.setPid(sysAreaFour.getParentId());
                	    				 areaTreeVoFour.setSeq(sysAreaFour.getSeq());
                	    				 treeThreeSon.add(areaTreeVoFour);
                	    			 }
                	    			 areaTreeVoThree.setChildren(treeThreeSon);
                	    			 areaTreeVoThree.setChildrenCodeCount(treeThreeSon.size());
                	    		 }
                	    	   treeTwoSon.add(areaTreeVoThree);
                	    	 }
                	      areaTreeVoTwo.setChildren(treeTwoSon);
                	      areaTreeVoTwo.setChildrenCodeCount(treeTwoSon.size());
                	    }                	 
                	treeOneSon.add(areaTreeVoTwo);
                }
                areaTreeVoOne.setChildren(treeOneSon);
                areaTreeVoOne.setChildrenCodeCount(treeOneSon.size());
            } else {
            	areaTreeVoOne.setState("closed");
            }
            
            trees.add(areaTreeVoOne);
        }
        return trees;
    }
    
    public void diGui(){
    	
    }
    
	@Override
	public List<SysArea> findAreaCodeAll() {
		return sysAreaMapper.findAreaCodeAll();
	}
	
    @Override
    public void deleteAreaAllByPid(String id){
    	List<SysArea> list = sysAreaMapper.findSysAreaAllByPid(id);
    	sysAreaMapper.deleteByPrimaryKey(id);
    	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
    		SysArea sysArea = (SysArea) iterator.next();
    		//递归删除所有的子节点
			deleteAreaAllByPid(sysArea.getId());
		}
    }

	@Override
	public void deleteAreaAllByCode(String code) {
		// TODO Auto-generated method stub
		sysAreaMapper.deleteAreaAllByCode(code);
	}
    
	/*
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Integer save(SysArea record) {
		SysArea parent = null;
		if(record.getParentId() == null || record.getParentId() == 0){
    		parent = new SysArea();
    		parent.setId(0);
    		parent.setParentIds(StringUtils.EMPTY);
    	}else{
    		parent = findById(record.getParentId());
    	}
    	
		// 设置新的父节点串
    	record.setParentId(parent.getId());
    	record.setParentIds(parent.getParentIds()+parent.getId()+",");
		return sysAreaMapper.insertSelective(record);
	}

	@Override
	public SysArea findById(Integer id) {
		return sysAreaMapper.selectByPrimaryKey(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Integer update(SysArea record) {
		return sysAreaMapper.updateByPrimaryKeySelective(record);
	};

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void delById(Integer id) {
		SysArea area = sysAreaMapper.selectByPrimaryKey(id);
		area.setStatus((byte)Status.DELETE.getType());
		sysAreaMapper.updateByPrimaryKeySelective(area);
		//更新子节点状态为-1
		sysAreaMapper.deleteByPids(area.getParentIds() + area.getId());
	}

	@Override
	public SysArea findByCode(String code) {
		return sysAreaMapper.selectByCode(code);
	}*/
	
}