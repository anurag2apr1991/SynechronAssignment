package com.javainuse.dto;

public class JSONComparisonOutput {

	private Integer id;

	private boolean nameMatch;

	private boolean genderMatch;

	private boolean cityMatch;

	private boolean pincodeMatch;

	public JSONComparisonOutput() {

	}

	public JSONComparisonOutput(Integer id, boolean nameMatch, boolean genderMatch, boolean cityMatch,
			boolean pincodeMatch) {
		this.id = id;
		this.nameMatch = nameMatch;
		this.genderMatch = genderMatch;
		this.cityMatch = cityMatch;
		this.pincodeMatch = pincodeMatch;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isNameMatch() {
		return nameMatch;
	}

	public void setNameMatch(boolean nameMatch) {
		this.nameMatch = nameMatch;
	}

	public boolean isGenderMatch() {
		return genderMatch;
	}

	public void setGenderMatch(boolean genderMatch) {
		this.genderMatch = genderMatch;
	}

	public boolean isCityMatch() {
		return cityMatch;
	}

	public void setCityMatch(boolean cityMatch) {
		this.cityMatch = cityMatch;
	}

	public boolean isPincodeMatch() {
		return pincodeMatch;
	}

	public void setPincodeMatch(boolean pincodeMatch) {
		this.pincodeMatch = pincodeMatch;
	}

}
