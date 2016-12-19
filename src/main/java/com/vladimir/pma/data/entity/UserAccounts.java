package com.vladimir.pma.data.entity;
// Generated 21-Oct-2016 11:22:09 by Hibernate Tools 4.3.1.Final

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "USER_ACCOUNTS", uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME") )
public class UserAccounts implements java.io.Serializable, UserDetails {

	private static final long serialVersionUID = 9024008808782790051L;
	
	@Id
	@SequenceGenerator(name = "user_gen", sequenceName = "SEQ_PMA_USERS", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;
	
	@JsonIgnore
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;

		
	public UserAccounts() {
	}
	
	

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
