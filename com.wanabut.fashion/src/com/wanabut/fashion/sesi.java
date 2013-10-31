package com.wanabut.fashion;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class sesi {
	public static boolean in(){
		String url					=	"http://www.komber.pusku.com/admin/sesi";
		JSONParser jsonParser		=	new JSONParser();
		List<NameValuePair> params	= 	new ArrayList<NameValuePair>();
		JSONObject json = jsonParser.getJSONFromUrl(url, params);
		boolean status				=	false;
		 try {
	       	 if (json.getString("sesi") == "in") {
	             status = true;
	       	 }
		} catch (JSONException e) {
			e.printStackTrace();
		}
		 return status;
	}
}
