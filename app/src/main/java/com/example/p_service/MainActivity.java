package com.example.p_service;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startB, stopB;
    TextView textView;
    Button appListButton;
    ListView appListListV;

    ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        startB =findViewById( R.id.startButton );
        stopB =findViewById( R.id.stopButton );

        textView=findViewById(R.id.textView);
        appListButton=findViewById(R.id.appListButton);
        appListListV=findViewById(R.id.appListListV);

        startB.setOnClickListener( this );
        stopB.setOnClickListener( this );

        appListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*@SuppressLint("WrongConstant") List<PackageInfo> applicationInfoList= getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
                String[] strings=new String[applicationInfoList.size()];
                int i1=0;
                for (PackageInfo applicationInfo:applicationInfoList){
                    strings[i1]=applicationInfo.packageName;
                    i1++;
                }
                appListListV.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,strings));
                textView.setText(applicationInfoList.size()+" apps are installed");*/

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
                    else if(procInfos.get(i).processName.equals("com.google.android.youtube")){
                        Toast.makeText(getApplicationContext(), "Browser is running", Toast.LENGTH_LONG).show();
                    }
                    else if(procInfos.get(i).processName.equals("com.simplemobiletools.gallery")){
                        Toast.makeText(getApplicationContext(), "Browser is running", Toast.LENGTH_LONG).show();
                    }
                    else if(procInfos.get(i).processName.equals("com.example.p18_studyplanner")){
                        Toast.makeText(getApplicationContext(), "Browser is running", Toast.LENGTH_LONG).show();
                    }
                    else if(procInfos.get(i).processName.equals("org.mozilla.firefox")){
                        Toast.makeText(getApplicationContext(), "Browser is running", Toast.LENGTH_LONG).show();
                    }
                    else if (procInfos.get(i).processName.equals("com.example.p_service")){
                        Toast.makeText(getApplicationContext(), "Service is running", Toast.LENGTH_SHORT).show();
                    }
                    else if (procInfos.get(i).processName.equals("com.example.sampleproject")){
                        Toast.makeText(MainActivity.this, "Sample Project is running", Toast.LENGTH_SHORT).show();
                    }
                    else if (procInfos.get(i).processName.equals("com.google.android.calendar")){
                        Toast.makeText(MainActivity.this, "Calendar is running", Toast.LENGTH_SHORT).show();
                    }
                    else if (procInfos.get(i).processName.equals("com.google.android.apps.photos")){
                        Toast.makeText(MainActivity.this, "Photos is running", Toast.LENGTH_SHORT).show();
                    }
                    else if (procInfos.get(i).processName.equals("com.android.chrome")){
                        Toast.makeText(MainActivity.this, "Chrome is running", Toast.LENGTH_SHORT).show();
                    }
                }*/

                hasUsageStatsPermission(MainActivity.this);


                /*PackageManager packageManager=getPackageManager();
                List<ApplicationInfo> applicationInfoList=packageManager.getInstalledApplications(0);
                Toast.makeText(MainActivity.this, "list"+applicationInfoList.get(0), Toast.LENGTH_SHORT).show();
                UsageStatsManager usageStatsManager=(UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
                Calendar calendar=Calendar.getInstance();
                calendar.add(Calendar.YEAR,-1);
                long start=calendar.getTimeInMillis();
                long end=System.currentTimeMillis();
                Map<String, UsageStats> stats = usageStatsManager.queryAndAggregateUsageStats(start, end);

                for (ApplicationInfo applicationInfo:applicationInfoList){
                    //Toast.makeText(MainActivity.this, "For", Toast.LENGTH_SHORT).show();
                    UsageStats usageStats=stats.get(applicationInfo.packageName);
                    //Toast.makeText(MainActivity.this, "aa"+usageStats, Toast.LENGTH_SHORT).show();
                    long lastTimeUsed;
                    long actualLastTimeUsed;
                    if (usageStats!=null){
                        Toast.makeText(MainActivity.this, "Not null", Toast.LENGTH_SHORT).show();
                        lastTimeUsed=usageStats.getLastTimeUsed();
                        actualLastTimeUsed=end-lastTimeUsed;
                        Log.d("TAG", "Wait Please"+actualLastTimeUsed);
                        Toast.makeText(MainActivity.this, "time"+ actualLastTimeUsed, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Toast.makeText(MainActivity.this, "Null", Toast.LENGTH_SHORT).show();
                    }
                    //GetAppInfo(applicationInfo);
                }*/
                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void hasUsageStatsPermission(Context context) {
        boolean granted=false;
        //AppOpsManager appOpsManager

    }

    private void GetAppInfo(ApplicationInfo applicationInfo) {

    }

    public void onClick(View view) {
        Intent intent=new Intent(this,MusicService.class);
        if(view == startB){
            /*if (!foregroundServiceRunning()){
                startForegroundService(intent);
            }*/
            startForegroundService(intent);
        }
        else if (view == stopB){
            stopService(intent);
        }
    }

    //To check if service is already running
    public boolean foregroundServiceRunning(){
        ActivityManager activityManager=(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        //noinspection deprecation
        for (ActivityManager.RunningServiceInfo serviceInfo:activityManager.getRunningServices(Integer.MAX_VALUE)){
            if (MusicService.class.getName().equals(serviceInfo.service.getClassName())){
                return true;
            }
        }
        return false;
    }
}