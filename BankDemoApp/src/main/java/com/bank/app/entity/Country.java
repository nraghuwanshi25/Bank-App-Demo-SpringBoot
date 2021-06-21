package com.bank.app.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the country database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="C_ID")
	private int cId;

	@Column(name="C_CREATEDBY")
	private String cCreatedby;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="C_CREEATED_DATE")
	private Date cCreeatedDate;

	@Column(name="C_NAME")
	private String cName;

	@Column(name="C_UPDATEDBY")
	private String cUpdatedby;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="C_UPPDATED_DATE")
	private Date cUppdatedDate;

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="country")
	private List<State> states;

//	public Country() {
//	}
//
//	public int getCId() {
//		return this.cId;
//	}
//
//	public void setCId(int cId) {
//		this.cId = cId;
//	}
//
//	public String getCCreatedby() {
//		return this.cCreatedby;
//	}
//
//	public void setCCreatedby(String cCreatedby) {
//		this.cCreatedby = cCreatedby;
//	}
//
//	public Date getCCreeatedDate() {
//		return this.cCreeatedDate;
//	}
//
//	public void setCCreeatedDate(Date cCreeatedDate) {
//		this.cCreeatedDate = cCreeatedDate;
//	}
//
//	public String getCName() {
//		return this.cName;
//	}
//
//	public void setCName(String cName) {
//		this.cName = cName;
//	}
//
//	public String getCUpdatedby() {
//		return this.cUpdatedby;
//	}
//
//	public void setCUpdatedby(String cUpdatedby) {
//		this.cUpdatedby = cUpdatedby;
//	}
//
//	public Date getCUppdatedDate() {
//		return this.cUppdatedDate;
//	}
//
//	public void setCUppdatedDate(Date cUppdatedDate) {
//		this.cUppdatedDate = cUppdatedDate;
//	}
//
//	public List<State> getStates() {
//		return this.states;
//	}
//
//	public void setStates(List<State> states) {
//		this.states = states;
//	}
//
//	public State addState(State state) {
//		getStates().add(state);
//		state.setCountry(this);
//
//		return state;
//	}
//
//	public State removeState(State state) {
//		getStates().remove(state);
//		state.setCountry(null);
//
//		return state;
//	}

}