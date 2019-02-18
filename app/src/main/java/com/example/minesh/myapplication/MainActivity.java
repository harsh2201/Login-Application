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
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements OnClickListener{
    private TextInputLayout emailText;
    private TextInputLayout passwordText,confirmPassword;
    private TextInputEditText pass;
    private TextInputEditText conf;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText=findViewById(R.id.email);
        passwordText=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmPassword);
        pass=findViewById(R.id.pass);
        conf=findViewById(R.id.conf);
        progressDialog=new ProgressDialog(this);
        firebaseauth=FirebaseAuth.getInstance();
        TextView t1=findViewById(R.id.textView);
        t1.setText("User Registration");
        TextView t2=findViewById(R.id.textView3);
        t2.setText("*NOTE:- Do not enter your common password during the time of Registration");
        if(firebaseauth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(MainActivity.this, Profile.class));
        }
    }
    private void register()
    {
        String email=emailText.getEditText().getText().toString().trim();
        String password=passwordText.getEditText().getText().toString().trim();
        String confpassword=confirmPassword.getEditText().getText().toString().trim();
        if(confpassword.equals(password))
        {
            if(TextUtils.isEmpty(email))
            {
                emailText.setError("Enter your Email.");
                pass.setText(null);
                conf.setText(null);
                return;
            }
            if(TextUtils.isEmpty(password))
            {
                pass.setText(null);
                conf.setText(null);
                passwordText.setError("Please enter password.");
                return;
            }
            progressDialog.setMessage("Registering User");
            progressDialog.show();
            firebaseauth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,"Registered Succesfully!",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(MainActivity.this, Profile.class));
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Failed to Register!",Toast.LENGTH_SHORT).show();
                                pass.setText(null);
                                conf.setText(null);
                                //passwordText.addView(e1);
                            }
                            progressDialog.dismiss();
                        }
                    });
        }
        else
        {
            passwordText.setError("Password");
            confirmPassword.setError("Confirm Password");
            Toast.makeText(MainActivity.this,"The passwords do not mstch",Toast.LENGTH_SHORT).show();
            pass.setText(null);
            conf.setText(null);
            return;
        }
    }

    public void onClick(View v)
    {
        register();
    }


    public void onClickLogin(View v)
    {
        finish();
        startActivity(new Intent(MainActivity.this, Login_activity.class));
    }
 }