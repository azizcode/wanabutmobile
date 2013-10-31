package com.wanabut.fashion;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.ListActivity;
import android.content.Intent;

public class Menu extends ListActivity{

	String[] menu = new String[] { "Kategori", "Produk", "Transaksi","Logout"};//array menu
	String[] user;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = this.getIntent().getStringArrayExtra("user");
        ListView listmenu = (ListView) findViewById(R.id.listmenu);//listview diambil dari menu.xml
        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));//set action
    }
    
    @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            Intent menu=null;
            switch(position){
            	case 0:
            		//menu = new Intent(Menu.this,Kategori.class);//Berpindah Activity
            		menu = new Intent(Menu.this,adminkategori_activity.class);
            		menu.putExtra("halaman", 1);//mengirimkan variabel halaman
            	break;
            	default:
            		menu = new Intent(Menu.this,Login.class);//Berpindah Activity
            		Toast.makeText(this, "Anda telah berhasil logout ", Toast.LENGTH_LONG).show();//memunculkan popup
            }
            startActivity(menu);//memulai activity
        }
    
}
