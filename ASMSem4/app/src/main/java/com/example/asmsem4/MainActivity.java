package com.example.asmsem4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asmsem4.model.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText edName;
    public EditText edQuantity;
    public Button btnAdd;
    public Button btnView;
    private DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        db = new DBHelper(this);
        db.getReadableDatabase();
    }

    private void initView() {
       edName = (EditText) findViewById(R.id.edName);
       edQuantity = (EditText) findViewById(R.id.edQuantity);
       btnAdd = (Button) findViewById(R.id.btnAdd);
       btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            onAdd();
        }

    }

    private void onAdd() {
        if (edName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Name",Toast.LENGTH_LONG).show();
            return;
        }

        if (edQuantity.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Quantity",Toast.LENGTH_LONG).show();
            return;
        }

        String isAdd = db.addUser(edName.getText().toString(), edQuantity.getText().toString());
        Toast.makeText(this, isAdd, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MainActivity.this, ListUserAct.class);
        startActivity(intent);
    }
}
