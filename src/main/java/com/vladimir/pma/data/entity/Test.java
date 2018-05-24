package com.vladimir.pma.data.entity;
// Generated 21-Oct-2016 11:22:09 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "TESTS")
public class Test implements java.io.Serializable {

	private static final long serialVersionUID = -7831448675098855042L;

	@Id
	@SequenceGenerator(name = "test_gen", sequenceName = "SEQ_PMA_TESTS", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	
	@Column(name = "TESTNAME", nullable = false)
	private String testName;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	@Lob
	@Column(name = "TEST_PROMO_IMAGE", nullable = true)
	private byte[] testPromoImage;
		
	@Column(name = "CREATION_DATE", nullable = true)
	private Date creationDate;
	
	@Column(name = "RESULT_YES_DESCRIPTION", nullable = true)
	private String resultYesDescription;
	
	@Column(name = "RESULT_NO_DESCRIPTION", nullable = true)
	private String resultNoDescription;
	
	
	@JsonManagedReference(value = "slides")
	@OneToMany(mappedBy = "test")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade(CascadeType.MERGE)
	public List<Slide> slideList;
	
//	@JsonManagedReference(value = "tests")
	@JsonIgnore
	@OneToMany(mappedBy = "test")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SUBSELECT)
	private List<Result> resultList;
	
	public Test() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getTestPromoImage() {
		return testPromoImage;
	}

	public void setTestPromoImage(byte[] testPromoImage) {
		this.testPromoImage = testPromoImage;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public List<Slide> getSlideList() {
		return slideList;
	}

	public void setSlideList(List<Slide> slideList) {
		this.slideList = slideList;
	}

	public List<Result> getResultList() {
		return resultList;
	}

	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}

	public String getResultYesDescription() {
		return resultYesDescription;
	}

	public void setResultYesDescription(String resultYesDescription) {
		this.resultYesDescription = resultYesDescription;
	}

	public String getResultNoDescription() {
		return resultNoDescription;
	}

	public void setResultNoDescription(String resultNoDescription) {
		this.resultNoDescription = resultNoDescription;
	}

	
	
}
