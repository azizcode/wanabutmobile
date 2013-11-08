package com.wanabut.fashion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class adminkategori_activity extends Activity {
	/** Called when the activity is first created. */

	// static String session="";
	// Button button;
//	View view;
	List<Map<String, String>> listkat = new ArrayList<Map<String, String>>();
	private SimpleAdapter simpleAdptkat;
	Button button;
	Button button1;
	Button button2;
	
	View vv;
	private void initListkat() {
	
		String halaman = getIntent().getStringExtra("halaman");
		// We populate the planets
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("halaman", halaman));//nama POST		
		JSONObject json = crud.crud("kategori", params);
		button1 =(Button)findViewById(R.id.button_prev);
		button2 =(Button)findViewById(R.id.button_next);
		if(this.getIntent().getStringExtra("status") !=null){
			Toast.makeText(this, this.getIntent().getStringExtra("status"), Toast.LENGTH_LONG).show();
		}
		try {
			if (json.getString("id_kategori").equals("kosong")) {
				listkat.add(createPlanet("kat", "Tidak ada data."));
				
				button1.setVisibility(vv.GONE);
				button2.setVisibility(vv.GONE);//menyembunyikan button
//				button2.setVisibility(vv.VISIBLE);//memunculkan button
				
			}else{
//				listkat.add(createPlanet("kat", halaman));
				button1.setVisibility(vv.VISIBLE);
				button2.setVisibility(vv.VISIBLE);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				

	}
	
	private HashMap<String, String> createPlanet(String key, String name) {
		HashMap<String, String> kat = new HashMap<String, String>();
		kat.put(key, name);

		return kat;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_kategori);
		
		initListkat();
		 
	    // We get the ListView component from the layout
	    ListView lv = (ListView) findViewById(R.id.listView_admin_kat);
	 
	    // This is a simple adapter that accepts as parameter
	    // Context
	    // Data list
	    // The row layout that is used during the row creation
	    // The keys used to retrieve the data
	    // The View id used to show the data. The key number and the view id must match
	    simpleAdptkat = new SimpleAdapter(this, listkat, android.R.layout.simple_list_item_1, new String[] {"kat"}, new int[] {android.R.id.text1});
	 
	    lv.setAdapter(simpleAdptkat);
	    
	    button = (Button)findViewById(R.id.btn_tmbh);
	    button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View vi) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(vi.getContext(),
						adminkategoritambah_activity.class);
				startActivityForResult(myIntent, 0);
			}
		});
//		ListView listmenu_kat = (ListView) findViewById(R.id.listView_admin_kat);
//		this.setListAdapter(new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, kategori));

		// Button submit =(Button)findViewById(R.id.buttonkem);
		// submit.setOnClickListener(this);
	}

//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		super.onListItemClick(l, v, position, id);
//		switch (position) {
//		case 0:
//			Intent i = new Intent(this, adminkategoritambah_activity.class);
//			startActivity(i);
//			break;
//		default:
//		}
//
//		Object o = this.getListAdapter().getItem(position);
//		String keyword = o.toString();
//
//		// tampilkanPilihan(keyword);
//
//		// Toast.makeText(this, "Anda Memilih: " + keyword,
//		// Toast.LENGTH_LONG).show();
//	}

	//sampai sini
	// protected void tampilkanPilihan(String pilihan) {
	// try {
	// // Intent digunakan untuk sebagai pengenal suatu activity
	// Intent i = null;
	// if (pilihan.equals("Kategori 1")) {
	// i = new Intent(this, adminkategoritambah_activity.class);
	// } else if (pilihan.equals("gf")) {
	// // Intent exit = new Intent(Intent.ACTION_MAIN);
	// // startActivity(exit);
	// AlertDialog.Builder builder = new AlertDialog.Builder(this);
	// builder.setMessage("Anda Yakin Ingin Menutup Aplikasi?")
	// .setCancelable(false).setPositiveButton("Ya",
	// new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog,
	// int id) {
	// Intent exit = new Intent(
	// Intent.ACTION_MAIN);
	// exit.addCategory(Intent.CATEGORY_HOME);
	// exit
	// .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	// startActivity(exit);
	// }
	// }).setNegativeButton("Tidak",
	// new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog,
	// int id) {
	// dialog.cancel();
	// }
	// }).show();
	//
	// } else {
	// Toast.makeText(
	// this,
	// "Anda Memilih: " + pilihan
	// + " , Actionnya belum dibuat",
	// Toast.LENGTH_LONG).show();
	// }
	// startActivity(i);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

}