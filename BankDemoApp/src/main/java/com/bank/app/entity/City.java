package com.bank.app.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the city database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CT_ID")
	private int ctId;

	@Column(name="CT_CREATEDBY")
	private String ctCreatedby;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CT_CREEATED_DATE")
	private Date ctCreeatedDate;

	@Column(name="CT_NAME")
	private String ctName;

	@Column(name="CT_UPDATEDBY")
	private String ctUpdatedby;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CT_UPPDATED_DATE")
	private Date ctUppdatedDate;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="ST_ID")
	private State state;

	

}