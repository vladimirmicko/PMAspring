package com.vladimir.pma.data.entity;
// Generated 21-Oct-2016 11:22:09 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "PROBA")
public class Proba implements java.io.Serializable {

	private static final long serialVersionUID = 6030235036599717553L;

	@Id
	@SequenceGenerator(name = "proba_gen", sequenceName = "SEQ_PMA_PROBA", allocationSize = 1, initialValue = 3)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proba_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	
	@Lob
	@Column(name = "FILE_BLOB", unique = true, nullable = false)
	private byte[] fileBlob;

		
	public Proba() {
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(byte[] fileBlob) {
		this.fileBlob = fileBlob;
	}

}
