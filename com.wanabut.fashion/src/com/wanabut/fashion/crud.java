package com.wanabut.fashion;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

public class crud {
	//private	static String baseurl	=	"http://10.0.2.2/wanabutmobile/admin/";
	private	static String baseurl	=	"http://192.168.1.6/wanabutmobile/admin/";
	private static JSONParser	jsonparser	=	new JSONParser();
	
	public static JSONObject crud(String url,List<NameValuePair> kolom){
		JSONObject json = jsonparser.getJSONFromUrl(baseurl+url, kolom);
		return json;
	}

}
