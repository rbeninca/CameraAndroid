package com.example.mycamera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;

public class MainActivity extends AppCompatActivity {
    String [] permissions= new String [] {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //solicitar e checagem de permissão camera e armazenamento
        checkPermissoes();

    }
    public  void  checkPermissoes(){
        //Checando as permissões foram concedidas ou as solicitando ao usuário
        for (String permission: permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permissions,PackageManager.PERMISSION_GRANTED);
            }
        }
    }

}