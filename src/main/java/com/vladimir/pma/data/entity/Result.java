package com.vladimir.pma.data.entity;
// Generated 21-Oct-2016 11:22:09 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "RESULTS")
public class Result implements java.io.Serializable {

	private static final long serialVersionUID = -6447906865377148761L;

	@Id
	@SequenceGenerator(name = "result_gen", sequenceName = "SEQ_PMA_RESULTS", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	
	@Column(name = "TEST_TAKEN", nullable = false)
	private Date testTaken;
	
	@Column(name = "TEST_START_TIME", nullable = false)
	private long testStartTime;
	
	@Column(name = "SUPERVISED_VALUE", nullable = true)
	private Boolean supervisedValue;
	
	@Column(name = "EVALUATION", nullable = true)
	private Integer evaluation;
	
	@JsonBackReference(value = "tests")
	@ManyToOne
	@JoinColumn(name = "TEST", nullable = false)
	private Test test;
	
//	@JsonBackReference(value = "accounts")
	@ManyToOne
	@JoinColumn(name = "USER_ACCOUNT", nullable = false)
	private UserAccount userAccount;
	
	@JsonManagedReference(value = "results")
	@OneToMany(mappedBy = "result")
	@Cascade({ CascadeType.ALL })
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SUBSELECT)
	private List<Answer> answerList;
	
	
	public Result() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getTestTaken() {
		return testTaken;
	}


	public void setTestTaken(Date testTaken) {
		this.testTaken = testTaken;
	}


	public Test getTest() {
		return test;
	}


	public void setTest(Test test) {
		this.test = test;
	}


	public UserAccount getUserAccount() {
		return userAccount;
	}


	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}


	public List<Answer> getAnswerList() {
		return answerList;
	}


	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}


	public long getTestStartTime() {
		return testStartTime;
	}


	public Boolean getSupervisedValue() {
		return supervisedValue;
	}


	public Integer getEvaluation() {
		return evaluation;
	}


	public void setTestStartTime(long testStartTime) {
		this.testStartTime = testStartTime;
	}


	public void setSupervisedValue(Boolean supervisedValue) {
		this.supervisedValue = supervisedValue;
	}


	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}

	public String getTestName() {
		return test.getTestName();
	}

	public int getTestId() {
		return test.getId();
	}
	
	public int getUserId() {
		return userAccount.getId();
	}

	public void setTestName(String testName) {
		test.setTestName(testName);
	}

	public void setTestId(Integer testId) {
		test.setId(testId);
	}
	
	public void setUserId(Integer userId) {
		userAccount.setId(userId);
	}
}
