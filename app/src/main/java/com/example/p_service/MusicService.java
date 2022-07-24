package com.example.p_service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.List;

public class MusicService extends Service {

    String channelId="StudyPlanner";

    // declaring object of MediaPlayer
    private MediaPlayer player;

    @Override

    // execution of service will start
    // on calling this method
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();

        Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("titleExtra","titleExtra");
        intent.putExtra("messageExtra","messageExtra");

        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent1,0);

        Notification notification= new NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle(intent.getStringExtra("titleExtra"))
                .setContentText(intent.getStringExtra("messageExtra"))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();

        startForeground(1,notification);

        //checkProcess();
        ActivityManager activityManager=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
        //List<ActivityManager.>
        // creating a media player which
        // will play the audio of Default
        // ringtone in android device
        player = MediaPlayer.create( this, Settings.System.DEFAULT_RINGTONE_URI );

        // providing the boolean
        // value as true to play
        // the audio on loop
        player.setLooping( true );

        // starting the process
        player.start();

        // returns the status
        // of the program
        return START_STICKY;
    }

    private void checkProcess() {
        ActivityManager activityManager = (ActivityManager) getSystemService( ACTIVITY_SERVICE );
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
            /*else if (procInfos.get(i).processName.equals("com.example.p_service")){
                Toast.makeText(getApplicationContext(), "Service is running", Toast.LENGTH_SHORT).show();
            }*/
            else if (procInfos.get(i).processName.equals("com.example.sampleproject")){
                Toast.makeText(this, "Sample Project is running", Toast.LENGTH_SHORT).show();
            }
            else if (procInfos.get(i).processName.equals("com.google.android.calendar")){
                Toast.makeText(this, "Calendar is running", Toast.LENGTH_SHORT).show();
            }
            else if (procInfos.get(i).processName.equals("com.google.android.apps.photos")){
                Toast.makeText(this, "Photos is running", Toast.LENGTH_SHORT).show();
            }
            else if (procInfos.get(i).processName.equals("com.android.chrome")){
                Toast.makeText(this, "Chrome is running", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override

    // execution of the service will
    // stop on calling this method
    public void onDestroy() {
        stopForeground(true);
        stopSelf();
        super.onDestroy();
        if (player!=null){
            player.stop();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        CharSequence name = "channelName";
        String description = "Channel Description";
        int importance = NotificationManager.IMPORTANCE_MAX;
        @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(channelId, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}