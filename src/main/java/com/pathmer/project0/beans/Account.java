package com.pathmer.project0.beans;

public class Account {
	private Integer id;
	private Integer accnumber;
	private String atype;
	private Integer balance;
	
	
	public Account(Integer id, Integer accnumber, String atype, Integer balance) {
		super();
		this.id = id;
		this.accnumber = accnumber;
		this.atype = atype;
		this.balance = balance;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getAccnumber() {
		return accnumber;
	}


	public void setAccnumber(Integer accnumber) {
		this.accnumber = accnumber;
	}


	public String getAtype() {
		return atype;
	}


	public void setAtype(String atype) {
		this.atype = atype;
	}


	public Integer getBalance() {
		return balance;
	}


	public void setBalance(Integer balance) {
		this.balance = balance;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accnumber == null) ? 0 : accnumber.hashCode());
		result = prime * result + ((atype == null) ? 0 : atype.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accnumber == null) {
			if (other.accnumber != null)
				return false;
		} else if (!accnumber.equals(other.accnumber))
			return false;
		if (atype == null) {
			if (other.atype != null)
				return false;
		} else if (!atype.equals(other.atype))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Account [id=" + id + ", accnumber=" + accnumber + ", atype=" + atype + ", balance=" + balance + "]";
	}
	
}
