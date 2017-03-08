package com.cody.common.utils.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cody.vo.project.ProjectAuditVO;


/**
 * 集合工具类
 * @author around
 * @date 2017-2-24
 */
public class ConnectionUtils {

	
	
	
	/**
	 * 处理冗余数据
	 * @param projectList
	 * @return
	 */
	public static List<ProjectAuditVO> getOnlyDataForProject(List<ProjectAuditVO> projectList) {
		List<ProjectAuditVO> tempData = new ArrayList<ProjectAuditVO>();
		ProjectAuditVO temp1 = null;
		
		Map<String, ProjectAuditVO> map = new HashMap<String, ProjectAuditVO>();
		
		
		for (int i = 0; i < projectList.size(); i++) {
			temp1 = projectList.get(i);
			
			if(map.get(temp1.getProjectId()) == null) {
				map.put(temp1.getProjectId(), temp1);
				tempData.add(temp1);
			}
		}
		
		
		System.out.println(tempData);
		return tempData;
	}
	

	
	
	public static void main(String[] args) {
		
		System.out.println(1 > 2);
	}
	
}
