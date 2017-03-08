package com.cody.common.utils.object;

import java.util.Date;

import com.cody.common.utils.IDUtil;
import com.cody.common.utils.finals.StorageType;
import com.cody.entity.science.ScienceBase;
import com.cody.entity.storage.StorageScience;
import com.cody.vo.project.ProjectAuditVO;


/**
 * 入库信息工具类
 * @author around
 * @date 2017-3-1
 */
public class StorageDataUtils {


	
	/**
	 * 创建科技项目入库
	 * @param project
	 * @return StorageScience
	 */
	public static StorageScience getNewStorageScience(ProjectAuditVO project) {
		StorageScience storage = new StorageScience();
		Date date = new Date();
		
		storage.setId(IDUtil.UUID());
		
		storage.setProjectId(project.getProjectId());
		storage.setUnitId(project.getDeclareUnitId());
		
		storage.setStatusId(StorageType.RKZT_XZ);//新增
		
		storage.setCreatetime(date);
		storage.setCreatorId(project.getActualApproverId());
		
		storage.setLastupdatetime(date);
		storage.setUpdatorId(project.getActualApproverId());
		
		
		return storage;
	}
	
	
	
}
