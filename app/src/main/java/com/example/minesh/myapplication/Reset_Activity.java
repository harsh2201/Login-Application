package com.example.minesh.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_);
    }
    public void onReset(View v)
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        final TextInputLayout emailText;
        emailText=findViewById(R.id.Textemail);
        String email=emailText.getEditText().getText().toString().trim();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter your Email.", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Sending Mail...");
        progressDialog.show();
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Reset_Activity.this, "Email is sent successfully. Check your mail box", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(Reset_Activity.this, Login_activity.class));
                        }
                        else
                        {
                            emailText.setError("This email does not exists. Plesae enter valid email.");
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}
