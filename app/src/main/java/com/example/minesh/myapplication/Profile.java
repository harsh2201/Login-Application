package com.example.minesh.myapplication;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Profile extends AppCompatActivity {
    FirebaseAuth firebaseauth;
    DatabaseReference myRef;
    TextInputLayout T;
    TextInputLayout idT;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseauth = FirebaseAuth.getInstance();
        if (firebaseauth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(Profile.this, Login_activity.class));
        }
        myRef=FirebaseDatabase.getInstance().getReference();
        TextView emailView;
        TextView t3=findViewById(R.id.textView4);
        T=findViewById(R.id.NameL);
        idT=findViewById(R.id.IDL);
        emailView = findViewById(R.id.emailView);
        FirebaseUser user=firebaseauth.getCurrentUser();
        t3.setText("User Profile");
        if(user.getEmail()!=null) {
            emailView.setText("Hello "+user.getEmail());
        }
    }
    private void saveInfo()
    {
        String Name=T.getEditText().getText().toString().trim();
        String ID=idT.getEditText().getText().toString().trim();
        UserInfo userInfo=new UserInfo(Name,ID);


        FirebaseUser user=firebaseauth.getCurrentUser();
        myRef.child(user.getUid()).setValue(userInfo);

        Toast.makeText(this, "Information saved successfully", Toast.LENGTH_SHORT).show();

    }
    public void signOut(View v)
    {
        finish();
        firebaseauth.signOut();
        startActivity(new Intent(Profile.this, Login_activity.class));
    }
    public void onSave(View v)
    {
        saveInfo();
        startActivity(new Intent(Profile.this, UserData.class));
    }
}
