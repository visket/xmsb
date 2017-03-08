package com.cody.vo.sys;

import java.util.List;
import com.cody.entity.sys.SysArea;

/**
 * 
 * @ClassName: AreaTreeVo
 * @Description: (区域树形通用Vo)
 * @author wanhuan
 * @date 2016年12月19日
 * 
 */
public class AreaTreeVo extends SysArea {

	private static final long serialVersionUID = 238217468539532194L;
	
	private String text;

	private String pid;

	private Integer level;

	private boolean isLeaf;

	private boolean loaded;

	private boolean expanded;

	private String userName;

	private String modifyName;

	private String catName;// 类目名称
	
	private String gradetypename;//等级名称
	
	private List<AreaTreeVo> children;
	
	private String state = "open";// open,closed
	
	private Integer childrenCodeCount;

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//由于combotree默认的textField绑定的是text所以这里在name之外多加个text迎合combotree
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	public List<AreaTreeVo> getChildren() {
		return children;
	}

	public void setChildren(List<AreaTreeVo> children) {
		this.children = children;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getChildrenCodeCount() {
		return childrenCodeCount;
	}

	public void setChildrenCodeCount(Integer childrenCodeCount) {
		this.childrenCodeCount = childrenCodeCount;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getGradetypename() {
		return gradetypename;
	}

	public void setGradetypename(String gradetypename) {
		this.gradetypename = gradetypename;
	}
	
	
	/*@Override
	public String toString() {
		return "TreeVo [id=" + id + ", code=" + code + ", name=" + name
				+ ", pid=" + pid + ", seq=" + seq + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", status=" + status
				+ ", level=" + level + ", isLeaf=" + isLeaf + ", loaded="
				+ loaded + ", expanded=" + expanded + ", userName=" + userName
				+ ", modifyName=" + modifyName + "]";
	}*/

}