package com.example.ecogardenapp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FotoPlanta extends AppCompatActivity implements View.OnClickListener {

    Button TomarFoto;

    Button guardarCambios;
    ImageView CapturaFoto;
    ImageButton Galeria;
    ImageButton GirarCamara;
    ImageButton SubirImagen;
    TextView TxtSubirImagen;

    Bitmap bitmap;

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private ImageButton imageButton;


    private static final int REQUEST_PERMISSION_CAMERA = 100;
    private static final int TAKE_PICTURE = 101;
    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_planta);


        initUI();

        TomarFoto.setOnClickListener(this);
        guardarCambios.setOnClickListener(this);

        ImageButton back = findViewById(R.id.btn_back);
        ImageButton home = findViewById(R.id.btn_home);

        imageView = findViewById(R.id.imagenMostrar);
        imageButton = findViewById(R.id.imgBtn_SubirFoto);

        imageButton.setOnClickListener(new View.OnClickListener() { // Asignar un OnClickListener al ImageButton
            @Override
            public void onClick(View v) {
                selectImage(); // Llamar al método que permite seleccionar una imagen
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backActividad = new Intent(FotoPlanta.this, RegistroCrecimiento.class);
                startActivity(backActividad);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeActividad = new Intent(FotoPlanta.this, ZonaNavegacion.class);
                startActivity(homeActividad);
            }
        });

    }

    private void initUI() {

        TomarFoto = findViewById(R.id.btn_TomarFoto);
        guardarCambios = findViewById(R.id.fotoPlanta);
        CapturaFoto = findViewById(R.id.imgV_CapturaFoto);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_TomarFoto) {
            checkPermissionCamera();

        } else if (id == R.id.fotoPlanta) {
            checkPermissionStorage();

        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CAMERA) {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                TomarFoto();
            }
        } else if (requestCode == REQUEST_PERMISSION_WRITE_STORAGE) {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                salvarImagen();
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void checkPermissionCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                TomarFoto();
            } else {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_PERMISSION_CAMERA
                );
            }
        } else {
            TomarFoto();
        }
    }

    private void checkPermissionStorage() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    salvarImagen();
                } else {
                    ActivityCompat.requestPermissions(
                            this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION_WRITE_STORAGE
                    );
                }
            } else {
                salvarImagen();
            }
        } else {
            salvarImagen();


        }
    }

    private void TomarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, TAKE_PICTURE);
        }

    }

    private void salvarImagen() {
        OutputStream fos = null;
        File file = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            ContentResolver resolver = getContentResolver();
            ContentValues values = new ContentValues();

            String fileName = System.currentTimeMillis() + "image_example";

            values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "pictures/MyApp");
            values.put(MediaStore.Images.Media.IS_PENDING, 1);

            Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
            Uri imageUri = resolver.insert(collection, values);

            try {
                fos = resolver.openOutputStream(imageUri);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            values.clear();
            values.put(MediaStore.Images.Media.IS_PENDING, 0);
            resolver.update(imageUri,values,null,null);

        } else {
            String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            String fileName = System.currentTimeMillis() + ".png";
            file = new File(imageDir,fileName);
            try {
                fos = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        boolean saved = bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
        if (saved){
            Toast.makeText(this,"La imagen se guardó correctamente", Toast.LENGTH_SHORT).show();
        }

        if (fos!=null){
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (file!=null){
            MediaScannerConnection.scanFile(this, new String[]{file.toString()},null,null);
        }

    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK); // Crear un Intent para seleccionar una imagen de la galería
        intent.setType("image/*"); // Establecer el tipo de datos a imágenes
        startActivityForResult(intent, PICK_IMAGE_REQUEST); // Lanzar el Intent y esperar el resultado
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK && data != null) {
                Uri imageUri = data.getData();
                imageView.setImageURI(imageUri);
            }
        } else if (requestCode == TAKE_PICTURE) {
            if (resultCode == RESULT_OK && data != null) {
                bitmap = (Bitmap) data.getExtras().get("data");
                CapturaFoto.setImageBitmap(bitmap);
            }
        }
    }
}

