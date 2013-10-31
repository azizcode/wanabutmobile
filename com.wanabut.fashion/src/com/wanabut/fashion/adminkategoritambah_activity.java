package com.wanabut.fashion;

//import org.json.JSONException;
//import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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
OnClickListener, AdapterView.OnItemSelectedListener{

	String nama, deskripsi, gambar, urutan, status, parent;
	EditText etnama, etdeskripsi, etgambar, eturutan;
	Spinner spn_parent;
	CheckBox cbstatus;
	Button simpan;
	//private View fragmentView;
	View vi;
	ArrayList<String> id_parent = new ArrayList<String>();
	ArrayList<String> nama_parent = new ArrayList<String>();
	
	
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
		
		etnama=(EditText)findViewById(R.id.et_nama);
		etdeskripsi=(EditText)findViewById(R.id.et_deskripsi);
		etgambar=(EditText)findViewById(R.id.et_gbr);
		eturutan=(EditText)findViewById(R.id.et_urutan);
		cbstatus=(CheckBox)findViewById(R.id.cc_status);
		
		
		simpan = (Button)findViewById(R.id.btn_simpan);
		simpan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ambilDataInput();
			}
		});
		
	}
	
	public void ambilDataInput(){
		
		nama = etnama.getText().toString();
		deskripsi = etdeskripsi.getText().toString();
		gambar = etgambar.getText().toString();
		urutan = eturutan.getText().toString();
		parent = spn_parent.getSelectedItem().toString();
		//status = cbstatus.getText().toString();
		
        cbstatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked()){
					status = cbstatus.getText().toString();
				}else{
					status = "Tidak "+cbstatus.getText().toString();;
				}
			}
		});
		
		Toast.makeText(this, nama+"\n"+deskripsi+"\n"+gambar+"\n"+urutan+"\n"+parent
				+"\n"+status,
				Toast.LENGTH_LONG).show();
//		parent = 
		
//		if(){
//			
//			
//		}
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
