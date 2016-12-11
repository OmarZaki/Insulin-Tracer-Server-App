package dataModel;

import java.sql.Date;

public class Categories {

	public final static String _Categories_TABLE="Categories";
	
	// Categories Columns names
	public final static String _ID = "id";
	public final static String _DATE_TIME = "date_time";
	public final static String _VALUE = "value";
	public final static String _USERS_ID = "users_id";
	public final static String _CATEGORY_NAME_ID = "Category_name_id";
	
	private int id;
	private Date date_time;
	private float value;
	private int Users_id;
	private int Category_name_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public int getUsers_id() {
		return Users_id;
	}
	public void setUsers_id(int users_id) {
		Users_id = users_id;
	}
	public int getCategory_name_id() {
		return Category_name_id;
	}
	public void setCategory_name_id(int category_name_id) {
		Category_name_id = category_name_id;
	}
	
}
