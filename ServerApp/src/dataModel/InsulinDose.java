package dataModel;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class InsulinDose {

	public final static String _InsulinDose_TABLE = "insulinDose";
    public static final String _DATE_FORMAT_NOW="yyyy-MM-dd HH:mm:ss";
	    
	// InsulinDose Column names
	public final static String _ID = "id";
	public final static String _QUANTITY = "quantity";
	public final static String _TAKEN = "taken";
	public final static String _DATE_TIME = "date_time";
	public final static String _USERS_ID = "Users_id";
    public static final String _ORIGANL_ID = "o_id";

    
	private int id;
	private float quantity;
	private boolean taken;
	private java.util.Date date_time;
	private int Users_id;
	private int original_id;
	
	
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
	public boolean getTaken() {
		return taken;
	}
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	public java.util.Date getDate_time() {
		return date_time;
	}
	public void setDate_time(java.util.Date date_time) {
		this.date_time = date_time;
	}
	public int getUsers_id() {
		return Users_id;
	}
	public void setUsers_id(int users_id) {
		Users_id = users_id;
	}
	public int getOriginal_id() {
		return original_id;
	}
	public void setOriginal_id(int original_id) {
		this.original_id = original_id;
	}
	/**
	 * Validate the 
	 * @return
	 */
	public boolean validate(){
		if(this.getQuantity()>0 &&
				this.getDate_time()!=null)
			return true;
		return false;
	}
	
	/**
	 * convert ArrayList to JSON string 
	 * @param insulinDose
	 * @return
	 */
	public static String convertListToJson(List<InsulinDose> insulinDose) {
		Gson gson = new  GsonBuilder()
	               .setDateFormat("yyyy-MM-dd HH:mm:ss.S")
	               .create();
		String insulinDoseAsString = gson.toJson(insulinDose);
		return insulinDoseAsString;
	}
	
	/**
	 * Convert on String JSON object of InsulinDose to 	InsulinDose Java Object;
	 * @param insulinDoseAsString
	 * @return
	 */
	public static InsulinDose convertStringToObject(String insulinDoseAsString){
		Gson gson = new  GsonBuilder()
	               .setDateFormat("yyyy-MM-dd HH:mm:ss.S")
	               .create();
		InsulinDose dose = gson.fromJson(insulinDoseAsString, InsulinDose.class);
		return dose;
	}
	/**
	 * convert Object to Json string. 
	 * @param insulinDose
	 * @return
	 */
	public String convertObjectToString(InsulinDose insulinDose){
		Gson gson = new  GsonBuilder()
	               .setDateFormat("yyyy-MM-dd HH:mm:ss.S")
	               .create(); 
		String dose= gson.toJson(insulinDose);
		return dose;
	}
	
}
