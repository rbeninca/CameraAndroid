package com.example.mycamera;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Uri> takePictureLauncher;
    private Uri imageUri;
    private ImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takePictureLauncher = registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> {
            if (result) {
                // A foto foi tirada com sucesso, lidar com a Uri da imagem
                // Exemplo: exibir em uma ImageView
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageURI(imageUri);
            } else {
                // A captura da foto falhou, lidar com o erro
            }
        });

        // Resto do código de inicialização...
        Button takePhotoButton = findViewById(R.id.btnCamera);
        takePhotoButton.setOnClickListener(v -> dispatchTakePictureIntent());
    }

    private void dispatchTakePictureIntent() {
        try {
            imageUri = createImageUri();
            takePictureLauncher.launch(imageUri);
        } catch (IOException e) {
            e.printStackTrace();
            // Tratar exceção
        }
    }

    private Uri createImageUri() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);

        return FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", imageFile);
    }
}