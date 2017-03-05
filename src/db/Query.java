package db;

import java.sql.ResultSet;

public class Query extends DBConnect{
	
	public ResultSet rs;
	
	/*Esegue la query che gli è stata passata e restituisce il risultato*/
	
	public ResultSet getData(String query){  
		try{
			rs = st.executeQuery(query);
		}catch(Exception e){
		e.printStackTrace();
		}
		return rs;
	}
}
