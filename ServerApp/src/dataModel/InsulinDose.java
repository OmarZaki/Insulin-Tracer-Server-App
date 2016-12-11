package dataModel;

import java.sql.Date;

public class InsulinDose {

	public final static String _InsulinDose_TABLE="InsulinDose";
	
	// InsulinDose Column names
	public final static String _ID = "id";
	public final static String _QUANTITY = "quantity";
	public final static String _TAKEN = "taken";
	public final static String _DATE = "date";
	public final static String _USERS_ID = "Users_id";
	
	private int id;
	private float quantity;
	private boolean taken;
	private Date date;
	private int Users_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public boolean isTaken() {
		return taken;
	}
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUsers_id() {
		return Users_id;
	}
	public void setUsers_id(int users_id) {
		Users_id = users_id;
	}
	
}
