package in.project.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="Mobilecustomer")
public class MobileCustomer implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//this is for auto incremented id
	private Integer cNo;
	
	private long mobileNo;
	
	@Column(length=20)
	private String cName;
	
	@Column(length=50)
	private String callerTune;
	
	
//	@CreationTimestamp
//	private LocalDateTime openingDate;
//	
//	@CreationTimestamp
//	private LocalDateTime lastUpadte;
//	
//	public LocalDateTime getOpeningDate() {
//		return openingDate;
//	}
//
//	public void setOpeningDate(LocalDateTime openingDate) {
//		this.openingDate = openingDate;
//	}
//
//	public LocalDateTime getLastUpadte() {
//		return lastUpadte;
//	}
//
//	public void setLastUpadte(LocalDateTime lastUpadte) {
//		this.lastUpadte = lastUpadte;
//	}

	@Version
	private Integer versionCount;

	public Integer getcNo() {
		return cNo;
	}

	public void setcNo(Integer cNo) {
		this.cNo = cNo;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getCallerTune() {
		return callerTune;
	}

	public void setCallerTune(String callerTune) {
		this.callerTune = callerTune;
	}

	public Integer getVersionCount() {
		return versionCount;
	}

	public void setVersionCount(Integer versionCount) {
		this.versionCount = versionCount;
	}

	@Override
	public String toString() {
		return "MobileCustomer [cNo=" + cNo + ", mobileNo=" + mobileNo + ", cName=" + cName + ", callerTune="
				+ callerTune + ", versionCount=" + versionCount + "]";
	}
	
}
