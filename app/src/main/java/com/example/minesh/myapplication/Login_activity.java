package com.example.minesh.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_activity extends AppCompatActivity {
    private TextInputLayout emailText;
    private TextInputLayout passwordText;
    private TextInputEditText pass;
    private ProgressDialog progressDialog;
    public FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        TextView t2=findViewById(R.id.textView2);
        t2.setText("User Login");
        emailText=findViewById(R.id.emailLogin);
        passwordText=findViewById(R.id.passwordLogin);
        pass=findViewById(R.id.pass);
        progressDialog=new ProgressDialog(this);
        firebaseauth = FirebaseAuth.getInstance();
    }
    private void login() {
        String email=emailText.getEditText().getText().toString().trim();
        String password=passwordText.getEditText().getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            emailText.setError("Enter your Email");
            pass.setText(null);
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            pass.setText(null);
            passwordText.setError("Enter your password");
            return;
        }
        progressDialog.setMessage("Logging In");
        progressDialog.show();
        firebaseauth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(Login_activity.this, "You have logged In Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(Login_activity.this, Profile.class));
                        }
                        else
                        {
                            Toast.makeText(Login_activity.this,"Failed to Login!",Toast.LENGTH_SHORT).show();
                            pass.setText(null);
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    public void  onLogin(View v)
    {
        login();
    }
    public void onClickRegister(View v)
    {
        finish();
        startActivity(new Intent(Login_activity.this, MainActivity.class));
    }
    public void resetPassword(View v)
    {
        startActivity(new Intent(Login_activity.this, Reset_Activity.class));
    }
}
