package com.tripshare.rauter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.tripshare.rauter.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {

    ImageView back;
    EditText edtName, edtEmail, edtPhone;
    TextInputEditText password;
    ImageView check_fullname, check_email, check_phonenumber;
    CheckBox agree;
    Spinner spinner;
    Button next;
    Boolean nameBool = false, emailBool = false, phoneBool = false, genderBool = true, passwordBool = false, policyBool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spinner = findViewById(R.id.spinnerGender);
        // List of values to show in the spinner
        List<String> values = new ArrayList<>();
        values.add("Male");
        values.add("Female");
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(SignUp.this, values);
        spinner.setAdapter(spinnerAdapter);

        edtName = findViewById(R.id.name);
        edtEmail = findViewById(R.id.email);
        edtPhone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        check_fullname = findViewById(R.id.check_fullname);
        check_email = findViewById(R.id.check_email);
        check_phonenumber = findViewById(R.id.check_phonenumber);
        agree = findViewById(R.id.agree);
        agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    policyBool = true;
                    check();
                }else{
                    policyBool = false;
                    check();
                }
            }
        });

        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edtName.getText().toString().length()>=4){
                    check_fullname.setVisibility(View.VISIBLE);
                    nameBool = true;
                }else{
                    check_fullname.setVisibility(View.GONE);
                    nameBool = false;
                    edtName.setError("Input must be more than 3 characters");
                }
                check();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString().trim()).matches()){
                    check_email.setVisibility(View.VISIBLE);
                    emailBool = true;
                }else{
                    check_email.setVisibility(View.GONE);
                    edtEmail.setError("Wrong email address");
                    emailBool = false;
                }
                check();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edtPhone.getText().toString().length()>10){
                    check_phonenumber.setVisibility(View.VISIBLE);
                    phoneBool = true;
                }else{
                    check_phonenumber.setVisibility(View.GONE);
                    edtPhone.setError("Wrong phone number");
                    phoneBool = false;
                }
                check();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (password.getText().toString().length()>7){
                    passwordBool = true;
                }else{
                    passwordBool = false;
                    password.setError("Password must be more than 8 characters");
                }
                check();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        next = findViewById(R.id.next);


    }

    private void check() {
        if (emailBool && nameBool && passwordBool && genderBool && passwordBool && phoneBool && policyBool){
            next.setBackground(ContextCompat.getDrawable(SignUp.this, R.drawable.button_orange));
            next.setClickable(true);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(SignUp.this, KYC_Registration.class));
                }
            });

        }else{
            next.setBackground(ContextCompat.getDrawable(SignUp.this, R.drawable.button_disabled));
            next.setClickable(false);
        }
    }
}