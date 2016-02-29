package com.example.vince.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	ArrayList<String> todoItems;
	ArrayAdapter<String> aTodoAdapter;
	ListView lvItems;
	EditText etEditText;
	EditText etEditItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
//		aTodoAdapter = new ArrayAdapter<String>;
		lvItems = (ListView) findViewById(R.id.lvItems);
		lvItems.setAdapter(aTodoAdapter);
		etEditText = (EditText) findViewById(R.id.etEditText);
		etEditItem = (EditText) findViewById(R.id.etEditItem);
		lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				todoItems.remove(position);
				aTodoAdapter.notifyDataSetChanged();
				writeItems();
				return true;
			}
		});

		final Intent edit_ui = new Intent(this, EditItemActivity2.class);
		lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				todoItems.get(position);
				edit_ui.putExtra("et",todoItems.get(position));
				edit_ui.putExtra("et_positon",position);

				startActivityForResult(edit_ui, 0);

			}
		});
	}
	public void populateArrayItems(){
		todoItems = new ArrayList<String>();
//		todoItems.add("Item 1");
//		todoItems.add("Item 2");
//	todoItems.add("Item 3");
		readItems();
		aTodoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);

	}

	private  void readItems(){
		File filesDir = getFilesDir();
		File file = new File(filesDir, "todo.txt");
		try {
			todoItems = new ArrayList<String>(FileUtils.readLines(file));
		}catch (IOException e){

		}
	}
	public  void writeItems(){
		File filesDir = getFilesDir();
		File file = new File(filesDir, "todo.txt");
		try {
			FileUtils.writeLines(file, todoItems);
		}catch (IOException e){

		}
	}
	public void onAddItem(View view) {
		aTodoAdapter.add(etEditText.getText().toString());
		etEditText.setText("");
		writeItems();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 0){
			String[] lv_arr = {};
			data.getStringExtra("editdata");
			data.getIntExtra("editdata_position", -1);

			todoItems.set(data.getIntExtra("editdata_position", -1), data.getStringExtra("editdata"));

			writeItems();
			aTodoAdapter.notifyDataSetChanged();

		}

	}
}
