package com.wanabut.fashion;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

public class crud {
	private	static String baseurl	=	"http://10.0.2.2/wanabutmobile/admin/";
	private static JSONParser	jsonparser	=	new JSONParser();
	
	public static JSONObject viewdata(String url,List<NameValuePair> kolom){
		JSONObject json = jsonparser.getJSONFromUrl(baseurl+url, kolom);
		return json;
	}
	
	public void insert(String username, String password){
		
	}
	
	public void update(String username, String password){
		
	}
	
}