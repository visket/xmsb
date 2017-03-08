package com.cody.vo.sys;

import com.cody.entity.sys.Organization;

public class OrganizationVo extends Organization {

	private Long[] oids;

	private String linkman;

	private String telephone;

	public Long[] getOids() {
		return oids;
	}

	public void setOids(Long[] oids) {
		this.oids = oids;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
