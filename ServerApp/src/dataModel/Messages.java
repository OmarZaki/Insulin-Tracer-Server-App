package dataModel;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Messages {
	
	public final static String _Messages_TABLE="Messages";
	
	// Messages Columns names
	public final static String _ID = "id";
	public final static String _TEXT = "text";
	public final static String _DATE_TIME = "date_time";
	public final static String _USERS_ID = "Users_id";
	
	private int id;
	private String text;
	private java.util.Date date_time;
	private int Users_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	
	public boolean validate(){
		if(this.getDate_time()!=null && this.getText()!=null && this.getUsers_id()>0)
			return true;
		return false;
	}
	
	public static String convertToJson(Messages insertedMessage) {
		Gson gson = new GsonBuilder()
	               .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
	               .create();
		String userStringObject= gson.toJson(insertedMessage); 
		return userStringObject;
	}
	
	public static Messages convertToObject(String Json){
		Gson gson = new  GsonBuilder()
	               .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
	               .create();
		Messages message = gson.fromJson(Json, Messages.class);
		return message;
	}

}
