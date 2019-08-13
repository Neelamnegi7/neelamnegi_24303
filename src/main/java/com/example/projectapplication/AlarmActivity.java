package com.example.projectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AlarmActivity extends AppCompatActivity {
    EditText alarmobj;
    ListView listView ;
    ArrayList<String> a1=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        alarmobj= findViewById(R.id.alarm);
    }
   public void onSetAlarm(View view)
   {
       String s=alarmobj.getText().toString();
       //Toast.makeText( this, "alarm set" ,Toast.LENGTH_LONG).show();
       a1.add(s);
       MyAdapter myadapt=new MyAdapter(this,R.layout.list_item,a1);
       listView= findViewById(R.id.list);
       listView.setAdapter(myadapt);

       myadapt.notifyDataSetChanged();
   }

}
