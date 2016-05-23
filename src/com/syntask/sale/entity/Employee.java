package com.syntask.sale.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

@Entity
@Name(value = "employee")
@Table(name = "employee")
@NamedQueries({ @NamedQuery(name = "getEmployees", query = "SELECT e " + "FROM Employee e") })
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "birdth")
	private Date birdth;

	@Column(name = "short_name")
	private String shortName;

	@Column(name = "gender")
	private Integer gender; // 0 = male, 1 = female

	@Column(name = "is_english_language")
	private boolean isEnlishLanguage; // 0 = false, 1 = true

	@Column(name = "is_china_language")
	private boolean isChinaLanguage; // the same above

	@Column(name = "type")
	private int type; // 0 = Domestic, 1 = Commercial, 2 = Dealer

	@Column(name = "address")
	private String address;
	
	private int status;

	public Employee() {
		super();
	}

	public Employee(String id, String name, Date birdth, String shortName, Integer gender, boolean isEnlishLanguage,
			boolean isChinaLanguage, int type, String address) {
		super();
		this.id = id;
		this.name = name;
		this.birdth = birdth;
		this.shortName = shortName;
		this.gender = gender;
		this.isEnlishLanguage = isEnlishLanguage;
		this.isChinaLanguage = isChinaLanguage;
		this.type = type;
		this.address = address;

	}

	public Employee(String name, Date birdth, String shortName, Integer gender, boolean isEnlishLanguage,
			boolean isChinaLanguage, int type, String address, int status) {
		super();
		this.name = name;
		this.birdth = birdth;
		this.shortName = shortName;
		this.gender = gender;
		this.isEnlishLanguage = isEnlishLanguage;
		this.isChinaLanguage = isChinaLanguage;
		this.type = type;
		this.address = address;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirdth() {
		return birdth;
	}

	public void setBirdth(Date birdth) {
		this.birdth = birdth;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public boolean isEnlishLanguage() {
		return isEnlishLanguage;
	}

	public void setEnlishLanguage(boolean isEnlishLanguage) {
		this.isEnlishLanguage = isEnlishLanguage;
	}

	public boolean isChinaLanguage() {
		return isChinaLanguage;
	}

	public void setChinaLanguage(boolean isChinaLanguage) {
		this.isChinaLanguage = isChinaLanguage;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
