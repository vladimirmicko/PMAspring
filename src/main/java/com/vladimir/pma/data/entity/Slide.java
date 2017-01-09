package com.vladimir.pma.data.entity;
// Generated 21-Oct-2016 11:22:09 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "SLIDES")
public class Slide implements java.io.Serializable {

	private static final long serialVersionUID = 605838641933944102L;

	@Id
	@SequenceGenerator(name = "slide_gen", sequenceName = "SEQ_PMA_SLIDES", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slide_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	
	@Column(name = "SLIDENAME", nullable = false)
	private String slideName;
	
	@Column(name = "DELAY", nullable = false)
	private Integer delay;
	
	@Lob
	@Column(name = "PRIMING_IMAGE", unique = true, nullable = true)
	private byte[] primingImage;
	
	@Lob
	@Column(name = "TEST_IMAGE", unique = true, nullable = false)
	private byte[] tetstImage;
		
	@ManyToOne
	@JoinColumn(name = "TESTS", nullable = false)
	private Test test;
	
		
	public Slide() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getSlideName() {
		return slideName;
	}


	public void setSlideName(String slideName) {
		this.slideName = slideName;
	}


	public Integer getDelay() {
		return delay;
	}


	public void setDelay(Integer delay) {
		this.delay = delay;
	}


	public byte[] getPrimingImage() {
		return primingImage;
	}


	public void setPrimingImage(byte[] primingImage) {
		this.primingImage = primingImage;
	}


	public byte[] getTetstImage() {
		return tetstImage;
	}


	public void setTetstImage(byte[] tetstImage) {
		this.tetstImage = tetstImage;
	}


	public Test getTest() {
		return test;
	}


	public void setTest(Test test) {
		this.test = test;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
