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


@Entity
@Table(name = "ANSWERS")
public class Answer implements java.io.Serializable {

	private static final long serialVersionUID = -8036418326200237905L;

	@Id
	@SequenceGenerator(name = "answer_gen", sequenceName = "SEQ_PMA_ANSWERS", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	
	@Column(name = "ANSWER_NUMBER", nullable = false)
	private Integer answerNumber;
	
	@Column(name = "ANSWER_TIME", nullable = false)
	private long answerTime;
	
	@Column(name = "PRIME_STIM_SHOW_TIME", nullable = false)
    private long primeStimShowTime;
	
	@Column(name = "TEST_STIM_SHOW_TIME", nullable = false)
    private long testStimShowTime;
	
	@Column(name = "ANSWER_VALUE", nullable = false)
	private Integer answerValue;

	@JsonBackReference(value = "results")
	@ManyToOne
	@JoinColumn(name = "RESULT", nullable = false)
	private Result result;
	
	@Transient
	private String slideName;
	
	
	
	public Answer() {
	}

	
	public Long getAnswerDuration(){
		return answerTime-testStimShowTime;
	}
	

	public Long getPrimeTestDuration(){
		return testStimShowTime-primeStimShowTime;
	}

	
	
	public Integer getId() {
		return id;
	}


	public Integer getAnswerNumber() {
		return answerNumber;
	}


	public long getAnswerTime() {
		return answerTime;
	}


	public long getPrimeStimShowTime() {
		return primeStimShowTime;
	}


	public long getTestStimShowTime() {
		return testStimShowTime;
	}


	public Integer getAnswerValue() {
		return answerValue;
	}


	public Result getResult() {
		return result;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setAnswerNumber(Integer answerNumber) {
		this.answerNumber = answerNumber;
	}


	public void setAnswerTime(long answerTime) {
		this.answerTime = answerTime;
	}


	public void setPrimeStimShowTime(long primeStimShowTime) {
		this.primeStimShowTime = primeStimShowTime;
	}


	public void setTestStimShowTime(long testStimShowTime) {
		this.testStimShowTime = testStimShowTime;
	}


	public void setAnswerValue(Integer answerValue) {
		this.answerValue = answerValue;
	}


	public void setResult(Result result) {
		this.result = result;
	}


	public String getSlideName() {
		return slideName;
	}


	public void setSlideName(String slideName) {
		this.slideName = slideName;
	}
	
	

}
