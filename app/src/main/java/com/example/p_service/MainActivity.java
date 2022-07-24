package com.example.p_service;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start, stop;
    TextView textView;
    Button appListButton;
    ListView appListListV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        start =findViewById( R.id.startButton );
        stop =findViewById( R.id.stopButton );

        textView=findViewById(R.id.textView);
        appListButton=findViewById(R.id.appListButton);
        appListListV=findViewById(R.id.appListListV);

        start.setOnClickListener( this );
        stop.setOnClickListener( this );

        appListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*@SuppressLint("WrongConstant") List<PackageInfo> applicationInfoList= getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
                String[] strings=new String[applicationInfoList.size()];
                int i=0;
                for (PackageInfo applicationInfo:applicationInfoList){
                    strings[i]=applicationInfo.packageName;
                    i++;
                }

                appListListV.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,strings));
                textView.setText(applicationInfoList.size()+" apps are installed");*/
                List<PackageInfo> packageInfoList=getPackageManager().getInstalledPackages(0);
                ArrayList<String> arrayList=new ArrayList<>();
                //String[] strings=new String[packageInfoList.size()];
                /*for (int i=0; i<packageInfoList.size();i++){
                    PackageInfo packageInfo=packageInfoList.get(i);
                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)==0){
                        Toast.makeText(MainActivity.this, "Name"+packageInfo.applicationInfo.loadLabel(getPackageManager()).toString(), Toast.LENGTH_SHORT).show();
                        //strings[i]=packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                    }
                }*/
                int i=0;
                for (PackageInfo packageInfo:packageInfoList){
                    if (((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)==0) || (packageInfo.applicationInfo.loadLabel(getPackageManager()).toString().equals("YouTube"))){
                        arrayList.add(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
                        //strings[i]=packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                        i++;
                    }
                }

                appListListV.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,arrayList));
                textView.setText(arrayList.size()+" apps are installed");
            }
        });
    }

    public void onClick(View view) {
        Intent intent=new Intent(this,MusicService.class);
        if(view == start){
            startForegroundService(intent);
        }
        else if (view == stop){
            stopService(intent);
        }
    }
}