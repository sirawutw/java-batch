
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dep_bal")
public class DepositBalance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DepositBalanceId id;

	private Date txnDt;
	private BigDecimal netDepAmt;
	private String depDsc;
	private String cisNo;
	private String cstTile;
	private String cstNm;
	private String cstSurnm;
	private String rmId;
	private String rcCd;
	private String svcBrId;
	private String ddcAcNo;
	private String ddcAcNm;
	private String ddcAcBr;
	private String ddcBrNm;
	private String ddcAcPdCd;
	private String ddcGlAcNo;
	private String ddcIpaCntraNo;
	private String ddcIpaDsc;
	private Date crtDt;
	private String crtBy;
	private Date udtDt;
	private String udtBy;

	public DepositBalanceId getId() {
		return id;
	}

	public void setId(DepositBalanceId id) {
		this.id = id;
	}

	public Date getTxnDt() {
		return txnDt;
	}

	public void setTxnDt(Date txnDt) {
		this.txnDt = txnDt;
	}

	public BigDecimal getNetDepAmt() {
		return netDepAmt;
	}

	public void setNetDepAmt(BigDecimal netDepAmt) {
		this.netDepAmt = netDepAmt;
	}

	public String getDepDsc() {
		return depDsc;
	}

	public void setDepDsc(String depDsc) {
		this.depDsc = depDsc;
	}

	public String getCisNo() {
		return cisNo;
	}

	public void setCisNo(String cisNo) {
		this.cisNo = cisNo;
	}

	public String getCstTile() {
		return cstTile;
	}

	public void setCstTile(String cstTile) {
		this.cstTile = cstTile;
	}

	public String getCstNm() {
		return cstNm;
	}

	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}

	public String getCstSurnm() {
		return cstSurnm;
	}

	public void setCstSurnm(String cstSurnm) {
		this.cstSurnm = cstSurnm;
	}

	public String getRmId() {
		return rmId;
	}

	public void setRmId(String rmId) {
		this.rmId = rmId;
	}

	public String getRcCd() {
		return rcCd;
	}

	public void setRcCd(String rcCd) {
		this.rcCd = rcCd;
	}

	public String getSvcBrId() {
		return svcBrId;
	}

	public void setSvcBrId(String svcBrId) {
		this.svcBrId = svcBrId;
	}

	public String getDdcAcNo() {
		return ddcAcNo;
	}

	public void setDdcAcNo(String ddcAcNo) {
		this.ddcAcNo = ddcAcNo;
	}

	public String getDdcAcNm() {
		return ddcAcNm;
	}

	public void setDdcAcNm(String ddcAcNm) {
		this.ddcAcNm = ddcAcNm;
	}

	public String getDdcAcBr() {
		return ddcAcBr;
	}

	public void setDdcAcBr(String ddcAcBr) {
		this.ddcAcBr = ddcAcBr;
	}

	public String getDdcBrNm() {
		return ddcBrNm;
	}

	public void setDdcBrNm(String ddcBrNm) {
		this.ddcBrNm = ddcBrNm;
	}

	public String getDdcAcPdCd() {
		return ddcAcPdCd;
	}

	public void setDdcAcPdCd(String ddcAcPdCd) {
		this.ddcAcPdCd = ddcAcPdCd;
	}

	public String getDdcGlAcNo() {
		return ddcGlAcNo;
	}

	public void setDdcGlAcNo(String ddcGlAcNo) {
		this.ddcGlAcNo = ddcGlAcNo;
	}

	public String getDdcIpaCntraNo() {
		return ddcIpaCntraNo;
	}

	public void setDdcIpaCntraNo(String ddcIpaCntraNo) {
		this.ddcIpaCntraNo = ddcIpaCntraNo;
	}

	public String getDdcIpaDsc() {
		return ddcIpaDsc;
	}

	public void setDdcIpaDsc(String ddcIpaDsc) {
		this.ddcIpaDsc = ddcIpaDsc;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtBy() {
		return crtBy;
	}

	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}

	public Date getUdtDt() {
		return udtDt;
	}

	public void setUdtDt(Date udtDt) {
		this.udtDt = udtDt;
	}

	public String getUdtBy() {
		return udtBy;
	}

	public void setUdtBy(String udtBy) {
		this.udtBy = udtBy;
	}

}
