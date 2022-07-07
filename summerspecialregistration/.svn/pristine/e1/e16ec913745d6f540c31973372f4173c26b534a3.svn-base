package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="MIGRATION_STUDENT_HISTORY_ACAD", schema="EXAMINATIONS")
public class MigrationStudentHistoryAcadModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private MigrationStudentHistoryAcadPKModel migrationStudentHistoryAcadPKId;
				
	@ManyToOne
	@JoinColumn(name="CRS_CTALOG_GENERIC_COURSE_TYPE", insertable = false, updatable = false)
	private CourseTypeComponentModel courseTypeComponentModel;
	
	@Column(name="COURSE_OPTION_MASTER_CODE")
	private String courseOptionCode;
			
	@Column(name="GRADE")
	private String grade;
	
	@Column(name="CREDITS")
	private int credit;
	
	@Column(name="RESULT_DECLARED_DATE")
	@Temporal(TemporalType.DATE)
	private Date resultDate;
	
	@Column(name="CODE")
	private String courseCode;

	public MigrationStudentHistoryAcadPKModel getMigrationStudentHistoryAcadPKId() {
		return migrationStudentHistoryAcadPKId;
	}

	public void setMigrationStudentHistoryAcadPKId(MigrationStudentHistoryAcadPKModel migrationStudentHistoryAcadPKId) {
		this.migrationStudentHistoryAcadPKId = migrationStudentHistoryAcadPKId;
	}

	public CourseTypeComponentModel getCourseTypeComponentModel() {
		return courseTypeComponentModel;
	}

	public void setCourseTypeComponentModel(CourseTypeComponentModel courseTypeComponentModel) {
		this.courseTypeComponentModel = courseTypeComponentModel;
	}

	public String getCourseOptionCode() {
		return courseOptionCode;
	}

	public void setCourseOptionCode(String courseOptionCode) {
		this.courseOptionCode = courseOptionCode;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((migrationStudentHistoryAcadPKId == null) ? 0 : migrationStudentHistoryAcadPKId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MigrationStudentHistoryAcadModel other = (MigrationStudentHistoryAcadModel) obj;
		if (migrationStudentHistoryAcadPKId == null) {
			if (other.migrationStudentHistoryAcadPKId != null)
				return false;
		} else if (!migrationStudentHistoryAcadPKId.equals(other.migrationStudentHistoryAcadPKId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MigrationStudentHistoryAcadModel [migrationStudentHistoryAcadPKId=" + migrationStudentHistoryAcadPKId
				+ ", courseTypeComponentModel=" + courseTypeComponentModel + ", courseOptionCode=" + courseOptionCode
				+ ", grade=" + grade + ", credit=" + credit + ", resultDate=" + resultDate + ", courseCode="
				+ courseCode + "]";
	}
			
}
