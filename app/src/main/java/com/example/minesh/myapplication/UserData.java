package com.example.minesh.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UserData extends AppCompatActivity {

    /*public TextView nameText;
    private TextView idText;
    private UserInfo userInfo=new UserInfo();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference database;
    private DatabaseReference myRef;
    ArrayList<String> list;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        /*nameText=findViewById(R.id.NameText);
        idText=findViewById(R.id.IdText);

        firebaseAuth = FirebaseAuth.getInstance();
        user= firebaseAuth.getCurrentUser();

        list=new ArrayList<>();

        String UID=user.getUid();
        database = FirebaseDatabase.getInstance().getReference();
        myRef = database.child(UID);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    userInfo=ds.getValue(UserInfo.class);
                    list.add(userInfo.getName().toString() + "" + userInfo.getID().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        nameText.setText(list.get(0).indexOf(3));
        idText.setText(list.get(1).indexOf(2));*/
    }
}
