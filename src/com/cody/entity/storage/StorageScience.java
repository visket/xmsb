package com.cody.entity.storage;

import java.math.BigDecimal;
import java.util.Date;

import com.cody.common.utils.annotation.Comparator;


/**
 * 科技项目入库实体
 * @author around
 * @date 2017-2-27
 */
public class StorageScience {
	
    private String id;

    /** 关联项目 */
    private String projectId;

    /** 验收人 */
    private Long acceptorId;

    /** 验收时间 */
    private Date accepttime;

    /** 入库状态 */
    @Comparator(column="status_id")
    private String statusId;

    /** 项目单位 */
    @Comparator(column="unit_id")
    private String unitId;

    /** 是否制定项目实施计划、方案等 */
    private Integer iscustimize;

    /** 是否编制项目内部验收报告 */
    private Integer isinspection;

    /** 市州安监局是否组织初审 */
    private Integer istissuetrial;

    /** 是否已经投入使用 */
    private Integer isuse;

    /** 项目是否达到项目申报书要求 */
    private Integer issatisfieddeclare;

    /** 项目档案管理是否规范 */
    private Integer isrecordstandard;

    /** 是否鉴定 */
    private Integer isauthenticate;

    /** 获国家级奖 */
    private Integer notationaward;

    /** 获省部级奖 */
    private Integer provinceaward;

    /** 专利申请数 */
    private Integer patentquantity;

    /** 其中：发明专利 */
    private Integer inventpatent;

    /** 专利授权数 */
    private Integer patentauthorize;

    /** 其中：发明专利授权数 */
    private Integer inventpatentauthorize;

    /** 发表省级以上论文数 */
    private Integer provincepaper;

    /** 其中：国际刊物发表数 */
    private Integer internationalperiodical;

    /** 出版专著 */
    private Integer publication;

    /** 项目总投资 */
    private BigDecimal investment;

    /** 专项资金 */
    private BigDecimal specialfund;

    /** 自筹资金 */
    private BigDecimal selffund;

    /** 资金开支 */
    private BigDecimal fundexpenses;

    /** 开支金额 */
    private BigDecimal expensesamount;

    /** 占比情况 */
    private BigDecimal proportion;

    /** 是否存在挪用资金现象 */
    private Integer illegalEmbezzle;

    /** 是否伪造凭证、报表和篡改会计数字 */
    private Integer illegalForge;

    /** 是否巧立名目、虚报专项资金 */
    private Integer illegalFalsereport;

    /** 是否使用专项资金发放津贴、加班费、福利或实物等 */
    private Integer illegalWelfare;

    /** 是否存在动用专项资金请客送礼、铺张浪费现象 */
    private Integer illegalGift;

    /** 是否存在违规报销票据现象 */
    private Integer illegalExpense;

    /** 是否存在使用专项资金购买办公设备或私人用品等现象 */
    private Integer illegalPrivate;
    

    /** 资金使用情况 */
    private String captitalcondition;

    /** 项目实施效果 */
    private String implementation;

    /** 项目管理工作经验和主要问题 */
    private String expandissue;

    /** 检查验收意见 */
    private String acceptopinion;

    /** 市州安监局意见 */
    private String cantonalopinion;
    
    private Date createtime;

    private Long creatorId;

    private Date lastupdatetime;

    private Long updatorId;
    
    public StorageScience() {
		// TODO Auto-generated constructor stub
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public Long getAcceptorId() {
        return acceptorId;
    }

    public void setAcceptorId(Long acceptorId) {
        this.acceptorId = acceptorId;
    }

    public Date getAccepttime() {
        return accepttime;
    }

    public void setAccepttime(Date accepttime) {
        this.accepttime = accepttime;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public Integer getIscustimize() {
        return iscustimize;
    }

    public void setIscustimize(Integer iscustimize) {
        this.iscustimize = iscustimize;
    }

    public Integer getIsinspection() {
        return isinspection;
    }

    public void setIsinspection(Integer isinspection) {
        this.isinspection = isinspection;
    }

    public Integer getIstissuetrial() {
        return istissuetrial;
    }

    public void setIstissuetrial(Integer istissuetrial) {
        this.istissuetrial = istissuetrial;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

    public Integer getIssatisfieddeclare() {
        return issatisfieddeclare;
    }

    public void setIssatisfieddeclare(Integer issatisfieddeclare) {
        this.issatisfieddeclare = issatisfieddeclare;
    }

    public Integer getIsrecordstandard() {
        return isrecordstandard;
    }

    public void setIsrecordstandard(Integer isrecordstandard) {
        this.isrecordstandard = isrecordstandard;
    }

    public Integer getIsauthenticate() {
        return isauthenticate;
    }

    public void setIsauthenticate(Integer isauthenticate) {
        this.isauthenticate = isauthenticate;
    }

    public Integer getNotationaward() {
        return notationaward;
    }

    public void setNotationaward(Integer notationaward) {
        this.notationaward = notationaward;
    }

    public Integer getProvinceaward() {
        return provinceaward;
    }

    public void setProvinceaward(Integer provinceaward) {
        this.provinceaward = provinceaward;
    }

    public Integer getPatentquantity() {
        return patentquantity;
    }

    public void setPatentquantity(Integer patentquantity) {
        this.patentquantity = patentquantity;
    }

    public Integer getInventpatent() {
        return inventpatent;
    }

    public void setInventpatent(Integer inventpatent) {
        this.inventpatent = inventpatent;
    }

    public Integer getPatentauthorize() {
        return patentauthorize;
    }

    public void setPatentauthorize(Integer patentauthorize) {
        this.patentauthorize = patentauthorize;
    }

    public Integer getInventpatentauthorize() {
        return inventpatentauthorize;
    }

    public void setInventpatentauthorize(Integer inventpatentauthorize) {
        this.inventpatentauthorize = inventpatentauthorize;
    }

    public Integer getProvincepaper() {
        return provincepaper;
    }

    public void setProvincepaper(Integer provincepaper) {
        this.provincepaper = provincepaper;
    }

    public Integer getInternationalperiodical() {
        return internationalperiodical;
    }

    public void setInternationalperiodical(Integer internationalperiodical) {
        this.internationalperiodical = internationalperiodical;
    }

    public Integer getPublication() {
        return publication;
    }

    public void setPublication(Integer publication) {
        this.publication = publication;
    }

    public BigDecimal getInvestment() {
        return investment;
    }

    public void setInvestment(BigDecimal investment) {
        this.investment = investment;
    }

    public BigDecimal getSpecialfund() {
        return specialfund;
    }

    public void setSpecialfund(BigDecimal specialfund) {
        this.specialfund = specialfund;
    }

    public BigDecimal getSelffund() {
        return selffund;
    }

    public void setSelffund(BigDecimal selffund) {
        this.selffund = selffund;
    }

    public BigDecimal getFundexpenses() {
        return fundexpenses;
    }

    public void setFundexpenses(BigDecimal fundexpenses) {
        this.fundexpenses = fundexpenses;
    }

    public BigDecimal getExpensesamount() {
        return expensesamount;
    }

    public void setExpensesamount(BigDecimal expensesamount) {
        this.expensesamount = expensesamount;
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public Integer getIllegalEmbezzle() {
        return illegalEmbezzle;
    }

    public void setIllegalEmbezzle(Integer illegalEmbezzle) {
        this.illegalEmbezzle = illegalEmbezzle;
    }

    public Integer getIllegalForge() {
        return illegalForge;
    }

    public void setIllegalForge(Integer illegalForge) {
        this.illegalForge = illegalForge;
    }

    public Integer getIllegalFalsereport() {
        return illegalFalsereport;
    }

    public void setIllegalFalsereport(Integer illegalFalsereport) {
        this.illegalFalsereport = illegalFalsereport;
    }

    public Integer getIllegalWelfare() {
        return illegalWelfare;
    }

    public void setIllegalWelfare(Integer illegalWelfare) {
        this.illegalWelfare = illegalWelfare;
    }

    public Integer getIllegalGift() {
        return illegalGift;
    }

    public void setIllegalGift(Integer illegalGift) {
        this.illegalGift = illegalGift;
    }

    public Integer getIllegalExpense() {
        return illegalExpense;
    }

    public void setIllegalExpense(Integer illegalExpense) {
        this.illegalExpense = illegalExpense;
    }

    public Integer getIllegalPrivate() {
        return illegalPrivate;
    }

    public void setIllegalPrivate(Integer illegalPrivate) {
        this.illegalPrivate = illegalPrivate;
    }
    
    public String getCaptitalcondition() {
        return captitalcondition;
    }

    public void setCaptitalcondition(String captitalcondition) {
        this.captitalcondition = captitalcondition == null ? null : captitalcondition.trim();
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation == null ? null : implementation.trim();
    }

    public String getExpandissue() {
        return expandissue;
    }

    public void setExpandissue(String expandissue) {
        this.expandissue = expandissue == null ? null : expandissue.trim();
    }

    public String getAcceptopinion() {
        return acceptopinion;
    }

    public void setAcceptopinion(String acceptopinion) {
        this.acceptopinion = acceptopinion == null ? null : acceptopinion.trim();
    }

    public String getCantonalopinion() {
        return cantonalopinion;
    }

    public void setCantonalopinion(String cantonalopinion) {
        this.cantonalopinion = cantonalopinion == null ? null : cantonalopinion.trim();
    }
    
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public Long getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }
}