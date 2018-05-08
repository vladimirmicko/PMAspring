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
	
	@Column(name = "ANSWER_DELAY", nullable = false)
	private Integer answerDelay;
	
	@Column(name = "ANSWER_VALUE", nullable = false)
	private Integer answerValue;

	@JsonBackReference(value = "results")
	@ManyToOne
	@JoinColumn(name = "RESULT", nullable = false)
	private Result result;
	
	
	public Answer() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getAnswerNumber() {
		return answerNumber;
	}


	public void setAnswerNumber(Integer answerNumber) {
		this.answerNumber = answerNumber;
	}


	public Integer getAnswerDelay() {
		return answerDelay;
	}


	public void setAnswerDelay(Integer answerDelay) {
		this.answerDelay = answerDelay;
	}


	public Integer getAnswerValue() {
		return answerValue;
	}


	public void setAnswerValue(Integer answerValue) {
		this.answerValue = answerValue;
	}


	public Result getResult() {
		return result;
	}


	public void setResult(Result result) {
		this.result = result;
	}




}
