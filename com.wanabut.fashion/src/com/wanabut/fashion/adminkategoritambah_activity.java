package com.wanabut.fashion;

//import org.json.JSONException;
//import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.wanabut.fashion.R.id;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class adminkategoritambah_activity extends Activity implements
		OnClickListener, AdapterView.OnItemSelectedListener {

	String nama, deskripsi, gambar, urutan, status, parent;
	EditText etnama, etdeskripsi, etgambar, eturutan;
	Spinner spn_parent;
	CheckBox cbstatus;
	Button simpan;
	// private View fragmentView;
	View vi;
	ArrayList<String> id_parent = new ArrayList<String>();
	ArrayList<String> nama_parent = new ArrayList<String>();
	
//	ArrayList<String> liststring = new ArrayList<String>();
	List<NameValuePair> params = new ArrayList<NameValuePair>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_kategori_tambah);
		nama_parent.add("Tidak Ada Kategori");
		id_parent.add("0");

		spn_parent = (Spinner) findViewById(R.id.spn_parent);
		spn_parent.setOnItemSelectedListener(this);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, nama_parent);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spn_parent.setAdapter(aa);

		etnama = (EditText) findViewById(R.id.et_nama);// not null
		etdeskripsi = (EditText) findViewById(R.id.et_deskripsi);
		etgambar = (EditText) findViewById(R.id.et_gbr);// not null
		eturutan = (EditText) findViewById(R.id.et_urutan);// not null
		cbstatus = (CheckBox) findViewById(R.id.cc_status);

		simpan = (Button) findViewById(R.id.btn_simpan);
		simpan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ambilDataInput();
			}
		});

	}

	public void ambilDataInput() {

		nama = etnama.getText().toString();
		deskripsi = etdeskripsi.getText().toString();
		gambar = etgambar.getText().toString();
		urutan = eturutan.getText().toString();
		
		parent = spn_parent.getSelectedItem().toString();

		if (!nama.equals("") && !gambar.equals("") && !urutan.equals("")) {
			
			if (cbstatus.isChecked()) {
				status = "1";
			} else {
				status = "0";
			}

			params.add(new BasicNameValuePair("nama", nama));
			params.add(new BasicNameValuePair("deskripsi", deskripsi));
			params.add(new BasicNameValuePair("gambar", gambar));
			params.add(new BasicNameValuePair("urutan", urutan));
			params.add(new BasicNameValuePair("status", status));
			params.add(new BasicNameValuePair("parent", id_parent.get(nama_parent.indexOf(parent))));
			
			JSONObject json = crud.crud("insert/kategori", params);
			
			try {
				if (json.getString("status") != null) {// cek apakah valid null
					String res = json.getString("status");// mengambil nilai json
					if (res.equals("sukses")) {// cek valid
						Intent ak = new Intent(adminkategoritambah_activity.this, adminkategori_activity.class);// Berpindah
						ak.putExtra("status", "Penyimpanan Sukses");
						startActivity(ak);// memulai activity
					} else {
						Toast.makeText(this, res,
								Toast.LENGTH_LONG).show();// memunculkan popup
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			Toast.makeText(this, "Pastikan nama, gambar, dan urutan tidak kosong!.", Toast.LENGTH_LONG).show();
		}
	}

	
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
