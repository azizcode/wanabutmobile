package com.wanabut.fashion;

//import org.json.JSONException;
//import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.wanabut.fashion.R.id;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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
	Button simpan, gbr;
	ArrayList<String> id_parent = new ArrayList<String>();
	ArrayList<String> nama_parent = new ArrayList<String>();
	private int file = 1;
	private String pathToFile = "";
	private ImageView penampakan;
//	ArrayList<String> liststring = new ArrayList<String>();
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	Bitmap bm;

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
		etgambar = (EditText) findViewById(R.id.et_gambar);
		etgambar.setKeyListener(null);
		eturutan = (EditText) findViewById(R.id.et_urutan);// not null
		cbstatus = (CheckBox) findViewById(R.id.cc_status);
		penampakan = (ImageView) findViewById(R.id.gbr);

		simpan = (Button) findViewById(R.id.btn_simpan);
		gbr = (Button) findViewById(R.id.btngbr);
		simpan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ambilDataInput();
			}
		});
		
		gbr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				intent.setType("image/*");
				startActivityForResult(Intent.createChooser(intent, "Pilih File"), file);
			}
		});

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == file) {
				Uri urigambar = data.getData();
				String [] filePathColumn = {MediaStore.Images.Media.DATA};
				Cursor cursor = getContentResolver().query(urigambar, filePathColumn, null, null, null);
				cursor.moveToFirst();
				
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String filePath = cursor.getString(columnIndex);
				cursor.close();
				etgambar.setText(filePath);
				pathToFile=filePath;
				filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
				this.penampakan.setImageBitmap(BitmapFactory.decodeFile(filePath));
				gbr.setText("Ubah Gambar");
			}
		}
	}
	
	public void ambilDataInput() {

		nama = etnama.getText().toString();
		deskripsi = etdeskripsi.getText().toString();
		gambar = etgambar.getText().toString();
		urutan = eturutan.getText().toString();
		
		parent = spn_parent.getSelectedItem().toString();

		if (!nama.equals("") && !urutan.equals("")) {
			
			if (cbstatus.isChecked()) {
				status = "1";
			} else {
				status = "0";
			}
			bm = BitmapFactory.decodeFile(pathToFile);
			ByteArrayOutputStream bao = new ByteArrayOutputStream();

	        // Resize the image
	        double width = bm.getWidth();
	        double height = bm.getHeight();
	        double ratio = 400 / width;
	        int newheight = (int) (ratio * height);

	        bm = Bitmap.createScaledBitmap(bm, 400, newheight,
	                true);

	        // Here you can define .PNG as well
	        bm.compress(Bitmap.CompressFormat.JPEG, 90, bao);
	       // byte[] ba = bao.toByteArray();
	        
			params.add(new BasicNameValuePair("nama", nama));
			params.add(new BasicNameValuePair("deskripsi", deskripsi));
			//params.add(new BasicNameValuePair("gambar", ba1));
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

	public void pilihGambar(){
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
