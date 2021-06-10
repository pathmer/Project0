package com.pathmer.project0.beans;

public class Account {
	private Integer id;
	private Integer accnumber;
	private String atype;
	private Double balance;
	private String astatus;
	private Integer users;
	
	
	public Account() {
		super();
	}

	public Account(Integer id, Integer accnumber, String atype, Double balance, String astatus, Integer users) {
		super();
		this.id = id;
		this.accnumber = accnumber;
		this.atype = atype;
		this.balance = balance;
		this.astatus = astatus;
		this.users = users;
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAstatus() {
		return astatus;
	}

	public void setAstatus(String astatus) {
		this.astatus = astatus;
	}

	public Integer getUsers() {
		return users;
	}

	public void setUsers(Integer users) {
		this.users = users;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accnumber == null) ? 0 : accnumber.hashCode());
		result = prime * result + ((astatus == null) ? 0 : astatus.hashCode());
		result = prime * result + ((atype == null) ? 0 : atype.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		if (astatus == null) {
			if (other.astatus != null)
				return false;
		} else if (!astatus.equals(other.astatus))
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
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Account [ id=" + id + ", accnumber=" + accnumber + ", atype=" + atype + ", balance=" + balance
				+ ", astatus=" + astatus + ", users=" + users + " ]";
	}

	public String empView() {
		if (id == null) {
			return "No account selected.";
		}
		else {
			return "userid=" + users + ", accountid=" + id + ", accnumber=" + accnumber + ", atype=" + atype + ", balance=" + balance
				+ ", astatus=" + astatus;
		}
	}
	
	public String userView() {
		if (id == null) {
			return "No account selected.";
		}
		else {
			return "Account Number = " + accnumber + " , Type = " + atype + " , Balance = $" + String.format("%.2f", balance) + " , Status = " + astatus;
		}
	}
}
