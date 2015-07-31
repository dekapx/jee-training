package com.ericsson.trainings.jee6.jpa.one2one.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
@SuppressWarnings("serial")
public class Address implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "STREET_ADDRESS_1", length = 25, nullable = false)
	private String streetAddress1;

	@Column(name = "STREET_ADDRESS_2", length = 25, nullable = true)
	private String streetAddress2;

	@Column(name = "CITY", length = 25, nullable = false)
	private String city;

	@Column(name = "COUNTY", length = 2, nullable = false)
	private String county;

	@Column(name = "ZIP_CODE", length = 5, nullable = false)
	private String zip;

	public Address() {
	}

	public Address(String streetAddress1, String streetAddress2, String city, String county, String zip) {
		super();
		this.streetAddress1 = streetAddress1;
		this.streetAddress2 = streetAddress2;
		this.city = city;
		this.county = county;
		this.zip = zip;
	}

	public Long getId() {
		return id;
	}

	public String getStreetAddress1() {
		return streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public String getCity() {
		return city;
	}

	public String getCounty() {
		return county;
	}

	public String getZip() {
		return zip;
	}

	@Override
	public String toString() {
		return "[" + streetAddress1 + " " + city + " " + county + " " + zip + "]";
	}

}
