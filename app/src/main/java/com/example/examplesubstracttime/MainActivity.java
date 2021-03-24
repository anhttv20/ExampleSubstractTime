package com.example.examplesubstracttime;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity
     implements View.OnFocusChangeListener{
    private EditText e1,e2,e3,e4;
    private EditText e5,e6,e7;
    private TextView txt1,txt2;
    private Button bt1,bt2;
    private LocalTime currentTime;
    private LocalDateTime currentDate;
    private DateTimeFormatter f;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();
        e5.setOnFocusChangeListener(this);
        e6.setOnFocusChangeListener(this);
        e7.setOnFocusChangeListener(this);

        currentTime=LocalTime.now();
        f = DateTimeFormatter.ofPattern("HH:mm");
        e1.setText(currentTime.format(f));

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTime=LocalTime.now();
                e2.setText(currentTime.format(f));
                try {
                    LocalTime startTime = LocalTime.parse(e1.getText().toString());

                    Duration difference = Duration.between(startTime, currentTime);

                    long hours = difference.toHours();
                    difference = difference.minusHours(hours);
                    long minutes = difference.toMinutes();

                    String time = hours + ":" + minutes;
                    txt1.setText(time);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        });

        currentDate=LocalDateTime.now();
        f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        e3.setText(currentDate.format(f));
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDate=LocalDateTime.now();
                e4.setText(currentDate.format(f));
                try {
                    LocalDateTime startDate = LocalDateTime.parse(e3.getText().toString(),f);
                    LocalDateTime stopDate = LocalDateTime.parse(e4.getText().toString(),f);
                    Duration difference = Duration.between(startDate, stopDate);

                    long  day= difference.toDays();
                    difference = difference.minusDays(day);
                    long hours = difference.toHours();
                    difference = difference.minusHours(hours);
                    long minutes = difference.toMinutes();
                    String time = day+" ngay "+hours + " hours " + minutes+" minutes";
                    txt2.setText(time);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        });
    }

    private void intView() {
        e1=findViewById(R.id.t1);
        e2=findViewById(R.id.t2);
        e3=findViewById(R.id.t3);
        e4=findViewById(R.id.t4);
        e5=findViewById(R.id.t5);
        e6=findViewById(R.id.t6);
        e7=findViewById(R.id.t7);

        txt1=findViewById(R.id.time1);
        txt2=findViewById(R.id.time2);
        bt1=findViewById(R.id.btStop);
        bt2=findViewById(R.id.btTinh);
    }
    private void focus(EditText et){
        if(et==null || (et.getText().toString().isEmpty())){
            Toast.makeText(MainActivity.this,
                    "not blank",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){
            if(v==e5){
                focus(e5);
            }
            if(v==e6){
                focus(e6);
            }
            if(v==e7){
                focus(e7);
            }
        }
    }
}
