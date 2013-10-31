package com.wanabut.fashion;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

	Button login;// tombol login
	EditText username, password;// textfield username dan password

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// mengatur nilai variabel dari main.xml
		login = (Button) findViewById(R.id.tombollogin);
		login.setOnClickListener(this);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
	}

	public void onClick(View v) {
		// Otentikasi otentikasi = new Otentikasi();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username.getText()
				.toString()));
		params.add(new BasicNameValuePair("password", password.getText()
				.toString()));
		JSONObject json = crud.viewdata("login", params);
		try {
			if (json.getString("valid") != null) {// cek apakah valid null
				String res = json.getString("valid");// mengambil nilai json
				if (Integer.parseInt(res) == 1) {// cek valid
					String user[] = { json.getString("level"),
							json.getString("deskripsi") };
					Intent menu;
					if (user[0].equals("0")) {
						menu = new Intent(Login.this, Menu.class);// Berpindah
																	// Activity
					} else {
						menu = new Intent(Login.this, ListBarang.class);// Berpindah
																		// Activity
					}
					menu.putExtra("user", user);
					startActivity(menu);// memulai activity
				} else {
					Toast.makeText(this, "Username / Password Tidak Valid ",
							Toast.LENGTH_LONG).show();// memunculkan popup
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
