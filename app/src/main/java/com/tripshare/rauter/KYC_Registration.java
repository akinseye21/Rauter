package com.tripshare.rauter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class KYC_Registration extends AppCompatActivity {

    ImageView back, imgFront, imgBack;
    LinearLayout lin_front, lin_back;
    TextView txt_NIMC, txt_DriversLicense, txt_Passport;
    String selected_kyc = "";
    Button next;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_CODE = 101;
    private static final int CAMERA_CAPTURE_CODE_2 = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_registration);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lin_front = findViewById(R.id.front_camera);
        lin_back = findViewById(R.id.back_camera);
        imgFront = findViewById(R.id.img_front);
        imgBack = findViewById(R.id.img_back);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), JoinCommunity.class));
            }
        });

        txt_NIMC = findViewById(R.id.txt_NIMC);
        txt_DriversLicense = findViewById(R.id.txt_DriversLicense);
        txt_Passport = findViewById(R.id.txt_Passport);

        txt_NIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_NIMC.setBackground(ContextCompat.getDrawable(KYC_Registration.this, R.drawable.button_orange));
                txt_NIMC.setTextColor(ContextCompat.getColor(KYC_Registration.this, R.color.white));
                txt_DriversLicense.setBackground(ContextCompat.getDrawable(KYC_Registration.this, R.drawable.corner_light_orange));
                txt_DriversLicense.setTextColor(ContextCompat.getColor(KYC_Registration.this, R.color.black));
                txt_Passport.setBackground(ContextCompat.getDrawable(KYC_Registration.this, R.drawable.corner_light_orange));
                txt_Passport.setTextColor(ContextCompat.getColor(KYC_Registration.this, R.color.black));

                selected_kyc = "NIMC";
            }
        });
        txt_DriversLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_DriversLicense.setBackground(ContextCompat.getDrawable(KYC_Registration.this, R.drawable.button_orange));
                txt_DriversLicense.setTextColor(ContextCompat.getColor(KYC_Registration.this, R.color.white));
                txt_NIMC.setBackground(ContextCompat.getDrawable(KYC_Registration.this, R.drawable.corner_light_orange));
                txt_NIMC.setTextColor(ContextCompat.getColor(KYC_Registration.this, R.color.black));
                txt_Passport.setBackground(ContextCompat.getDrawable(KYC_Registration.this, R.drawable.corner_light_orange));
                txt_Passport.setTextColor(ContextCompat.getColor(KYC_Registration.this, R.color.black));

                selected_kyc = "Drivers License";
            }
        });
        txt_Passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_Passport.setBackground(ContextCompat.getDrawable(KYC_Registration.this, R.drawable.button_orange));
                txt_Passport.setTextColor(ContextCompat.getColor(KYC_Registration.this, R.color.white));
                txt_NIMC.setBackground(ContextCompat.getDrawable(KYC_Registration.this, R.drawable.corner_light_orange));
                txt_NIMC.setTextColor(ContextCompat.getColor(KYC_Registration.this, R.color.black));
                txt_DriversLicense.setBackground(ContextCompat.getDrawable(KYC_Registration.this, R.drawable.corner_light_orange));
                txt_DriversLicense.setTextColor(ContextCompat.getColor(KYC_Registration.this, R.color.black));

                selected_kyc = "Passport";
            }
        });

        lin_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(KYC_Registration.this, android.Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    ActivityCompat.requestPermissions(KYC_Registration.this,
                            new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                }
            }
        });

        lin_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(KYC_Registration.this, android.Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    openCamera2();
                } else {
                    ActivityCompat.requestPermissions(KYC_Registration.this,
                            new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                }
            }
        });
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, CAMERA_CAPTURE_CODE);
        } else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show();
        }
    }

    private void openCamera2() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, CAMERA_CAPTURE_CODE_2);
        } else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CAPTURE_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                imgFront.setVisibility(View.VISIBLE);
                imgFront.setImageBitmap(imageBitmap);
            }
        }

        if (requestCode == CAMERA_CAPTURE_CODE_2 && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                imgBack.setVisibility(View.VISIBLE);
                imgBack.setImageBitmap(imageBitmap);
            }
        }

    }
}