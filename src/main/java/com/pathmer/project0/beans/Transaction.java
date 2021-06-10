package com.pathmer.project0.beans;

public class Transaction {
	private Integer id;
	private String ttype;
	private Double amount;
	private Integer fromacc;
	private Integer toacc;
	private Integer userssn;
	private String tstatus;
	private Integer transferid;
	private Integer accounts;
	
	
	public Transaction() {
		super();
	}

	public Transaction(Integer id, String ttype, Double amount, Integer fromacc, Integer toacc, Integer userssn,
			String tstatus, Integer transferid, Integer accounts) {
		super();
		this.id = id;
		this.ttype = ttype;
		this.amount = amount;
		this.fromacc = fromacc;
		this.toacc = toacc;
		this.userssn = userssn;
		this.tstatus = tstatus;
		this.transferid = transferid;
		this.accounts = accounts;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getFromacc() {
		return fromacc;
	}

	public void setFromacc(Integer fromacc) {
		this.fromacc = fromacc;
	}

	public Integer getToacc() {
		return toacc;
	}

	public void setToacc(Integer toacc) {
		this.toacc = toacc;
	}

	public Integer getUserssn() {
		return userssn;
	}

	public void setUserssn(Integer userssn) {
		this.userssn = userssn;
	}

	public String getTstatus() {
		return tstatus;
	}

	public void setTstatus(String tstatus) {
		this.tstatus = tstatus;
	}

	public Integer getTransferid() {
		return transferid;
	}

	public void setTransferid(Integer transferid) {
		this.transferid = transferid;
	}

	public Integer getAccounts() {
		return accounts;
	}

	public void setAccounts(Integer accounts) {
		this.accounts = accounts;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((fromacc == null) ? 0 : fromacc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((toacc == null) ? 0 : toacc.hashCode());
		result = prime * result + ((transferid == null) ? 0 : transferid.hashCode());
		result = prime * result + ((tstatus == null) ? 0 : tstatus.hashCode());
		result = prime * result + ((ttype == null) ? 0 : ttype.hashCode());
		result = prime * result + ((userssn == null) ? 0 : userssn.hashCode());
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
		Transaction other = (Transaction) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (fromacc == null) {
			if (other.fromacc != null)
				return false;
		} else if (!fromacc.equals(other.fromacc))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (toacc == null) {
			if (other.toacc != null)
				return false;
		} else if (!toacc.equals(other.toacc))
			return false;
		if (transferid == null) {
			if (other.transferid != null)
				return false;
		} else if (!transferid.equals(other.transferid))
			return false;
		if (tstatus == null) {
			if (other.tstatus != null)
				return false;
		} else if (!tstatus.equals(other.tstatus))
			return false;
		if (ttype == null) {
			if (other.ttype != null)
				return false;
		} else if (!ttype.equals(other.ttype))
			return false;
		if (userssn == null) {
			if (other.userssn != null)
				return false;
		} else if (!userssn.equals(other.userssn))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Transaction [ id=" + id + ", ttype=" + ttype + ", amount=" + amount + ", fromacc=" + fromacc + ", toacc="
				+ toacc + ", userssn=" + userssn + ", tstatus=" + tstatus + ", transferid=" + transferid + ", accounts="
				+ accounts + " ]";
	}

	public String userView() {
		return "Transaction [ Amount = $" + String.format("%.2f", amount) + " , Type = " + ttype + " ]";
	}

	public String userTransfer() {
		return "Transaction [ Amount = $" + String.format("%.2f",amount) + " , Type = " + ttype + " , Status = " + tstatus + " , Transfer ID = " + transferid + " ]";
	}

}
