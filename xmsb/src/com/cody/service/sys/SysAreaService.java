package com.cody.service.sys;

import java.util.List;
import com.cody.common.utils.TreeInfo;
import com.cody.entity.sys.SysArea;
import com.cody.vo.sys.AreaTreeVo;

/**
 * @description：区域管理
 * @author：wanhuan
 * @date：2016/12/16
 */

public interface SysAreaService {

	void findDataGrid(TreeInfo treeInfo);
	
	Integer save(SysArea record);
	
	SysArea findById(String id);
	
	Integer update(SysArea record);
	
	void delById(Integer id);

	SysArea findByCode(String code);

    List<AreaTreeVo> findTree();
	//void findTree(TreeInfo treeInfo);
    
    //查询所有区域编码
	List<SysArea> findAreaCodeAll();
	
	void deleteAreaAllByPid(String id);
	
	void deleteAreaAllByCode(String code);
	
}
