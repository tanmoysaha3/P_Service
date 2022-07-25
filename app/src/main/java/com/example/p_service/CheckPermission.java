package com.example.p_service;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CheckPermission extends AppCompatActivity {
    Button checkB;
    ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_permission);

        checkB=findViewById(R.id.checkB);

        checkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasUsageStatsPermission(CheckPermission.this)){
                    Toast.makeText(CheckPermission.this, "Permission granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CheckPermission.this, "Need Permission", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY).setData(Uri.parse("package:" + "com.example.p_service")));
                }
            }
        });

    }

    private void requirePermission() {
        String permission= Manifest.permission.PACKAGE_USAGE_STATS;
        requestPermissionLauncher.launch(permission);
    }
    public static boolean hasUsageStatsPermission(Context context) {
        boolean granted = false;
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), context.getPackageName());
        if (mode == AppOpsManager.MODE_DEFAULT)
            granted = (context.checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED);
        else
            granted = (mode == AppOpsManager.MODE_ALLOWED);
        return granted;
    }
}