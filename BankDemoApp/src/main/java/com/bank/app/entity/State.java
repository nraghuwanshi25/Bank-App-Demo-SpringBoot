package com.bank.app.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the state database table.
 * 
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="S_ID")
	private int sId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="S_CREATED_DATE")
	private Date sCreatedDate;

	@Column(name="S_CREATEDBY")
	private String sCreatedby;

	@Column(name="S_NAME")
	private String sName;

	@Column(name="S_UPDATEDBY")
	private String sUpdatedby;

	private String STATEcol;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="state")
	private List<City> cities;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="C_ID")
	private Country country;

//	public State() {
//	}
//
//	public int getSId() {
//		return this.sId;
//	}
//
//	public void setSId(int sId) {
//		this.sId = sId;
//	}
//
//	public Date getSCreatedDate() {
//		return this.sCreatedDate;
//	}
//
//	public void setSCreatedDate(Date sCreatedDate) {
//		this.sCreatedDate = sCreatedDate;
//	}
//
//	public String getSCreatedby() {
//		return this.sCreatedby;
//	}
//
//	public void setSCreatedby(String sCreatedby) {
//		this.sCreatedby = sCreatedby;
//	}
//
//	public String getSName() {
//		return this.sName;
//	}
//
//	public void setSName(String sName) {
//		this.sName = sName;
//	}
//
//	public String getSUpdatedby() {
//		return this.sUpdatedby;
//	}
//
//	public void setSUpdatedby(String sUpdatedby) {
//		this.sUpdatedby = sUpdatedby;
//	}
//
//	public String getSTATEcol() {
//		return this.STATEcol;
//	}
//
//	public void setSTATEcol(String STATEcol) {
//		this.STATEcol = STATEcol;
//	}
//
//	public List<City> getCities() {
//		return this.cities;
//	}
//
//	public void setCities(List<City> cities) {
//		this.cities = cities;
//	}
//
//	public City addCity(City city) {
//		getCities().add(city);
//		city.setState(this);
//
//		return city;
//	}
//
//	public City removeCity(City city) {
//		getCities().remove(city);
//		city.setState(null);
//
//		return city;
//	}
//
//	public Country getCountry() {
//		return this.country;
//	}
//
//	public void setCountry(Country country) {
//		this.country = country;
//	}

}