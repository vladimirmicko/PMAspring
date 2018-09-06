package com.vladimir.pma.data.entity;
// Generated 21-Oct-2016 11:22:09 by Hibernate Tools 4.3.1.Final

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "USER_ACCOUNTS")
public class UserAccount implements UserDetails, Serializable {

	private static final long serialVersionUID = -8157686871210899678L;

	@Id
	@SequenceGenerator(name = "user_gen", sequenceName = "SEQ_PMA_TESTS", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	
	@Column(name = "USERNAME", nullable = true)
	private String username;
	
	@Column(name = "PASSWORD", nullable = true)
	private String password;
	
	@Column(name = "SEX", nullable = true)
	private String sex;
	
	@Column(name = "BIRTHDATE", nullable = true)
	private Date birthdate;
	
//	@JsonManagedReference(value = "accounts")
	@JsonIgnore
	@OneToMany(mappedBy = "userAccount")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SUBSELECT)
	private List<Result> resultList;
	
	
	@Transient
	private String sessionId;
	
	@Transient
	private Long tsMobile;

	@Transient
	private Long tsServer;
	
	@Transient
	private Long deltaT;
	
	public UserAccount() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Result> getResultList() {
		return resultList;
	}


	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}


	public String getSex() {
		return sex;
	}


	public Date getBirthdate() {
		return birthdate;
	}


	public String getSessionId() {
		return sessionId;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	
	
	public Long getTsMobile() {
		return tsMobile;
	}


	public void setTsMobile(Long tsMobile) {
		this.tsMobile = tsMobile;
	}


	public Long getTsServer() {
		return tsServer;
	}


	public void setTsServer(Long tsServer) {
		this.tsServer = tsServer;
	}


	public Long getDeltaT() {
		return deltaT;
	}


	public void setDeltaT(Long deltaT) {
		this.deltaT = deltaT;
	}


	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		GrantedAuthority grantedAuthority = new GrantedAuthority(){
			private static final long serialVersionUID = 326003282737477973L;

			@Override
			public String getAuthority() {
				return "ROLE_ADMIN";
			}
		};
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(grantedAuthority);
		return authorities;
	}


	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
	

}
