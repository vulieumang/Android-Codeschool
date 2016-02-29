package com.example.vince.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity2 extends AppCompatActivity {
    EditText etEditItem;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item2);

        final Intent edit_item = getIntent();
       // edit_item.getStringExtra("et");
        etEditItem = (EditText) findViewById(R.id.etEditItem);

        btnSave = (Button) findViewById(R.id.btnSave);
        etEditItem.setText(edit_item.getStringExtra("et"));

        final Intent edit_data = new Intent();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_data.putExtra("editdata",etEditItem.getText().toString());
                edit_data.putExtra("editdata_position",edit_item.getIntExtra("et_positon",-1));
                setResult(0,edit_data);
                finish();
            }
        });
    }



}
