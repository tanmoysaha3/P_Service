package com.example.p_service;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
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
                @SuppressLint("WrongConstant") List<PackageInfo> applicationInfoList= getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
                String[] strings=new String[applicationInfoList.size()];
                int i=0;
                for (PackageInfo applicationInfo:applicationInfoList){
                    strings[i]=applicationInfo.packageName;
                    i++;
                }
                appListListV.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,strings));
                textView.setText(applicationInfoList.size()+" apps are installed");

                /*List<PackageInfo> packageInfoList=getPackageManager().getInstalledPackages(0);
                ArrayList<String> arrayList=new ArrayList<>();
                int i=0;
                for (PackageInfo packageInfo:packageInfoList){
                    if (((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)==0) || (packageInfo.applicationInfo.loadLabel(getPackageManager()).toString().equals("YouTube"))){
                        arrayList.add(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
                        //strings[i]=packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                        i++;
                    }
                }
                appListListV.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,arrayList));
                textView.setText(arrayList.size()+" apps are installed");*/

                /*ActivityManager activityManager = (ActivityManager) getSystemService( ACTIVITY_SERVICE );
                List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
                for(int i = 0; i < procInfos.size(); i++){
                    if(procInfos.get(i).processName.equals("com.android.browser")) {
                        Toast.makeText(getApplicationContext(), "Browser is running", Toast.LENGTH_LONG).show();
                    }
                    else if(procInfos.get(i).processName.equals("com.google.android.youtube"))
                    else if(procInfos.get(i).processName.equals("com.simplemobiletools.gallery"))
                    else if(procInfos.get(i).processName.equals("com.example.p18_studyplanner"))
                    else if(procInfos.get(i).processName.equals("org.mozilla.firefox"))
                }*/
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