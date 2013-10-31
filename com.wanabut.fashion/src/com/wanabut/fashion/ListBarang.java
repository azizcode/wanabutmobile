package com.wanabut.fashion;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.ListActivity;
import android.content.Intent;

public class ListBarang extends ListActivity{

	String[] menu = new String[] { "Baju1", "Baju2", "Baju3","Baju4"};//array menu
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listmenu = (ListView) findViewById(R.id.listmenu);//listview diambil dari menu.xml
        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));//set action
    }
    
    @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            
        }
    
}
