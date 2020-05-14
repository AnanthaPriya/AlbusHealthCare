package com.wiates.albushealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonalActivity extends AppCompatActivity {
    EditText sname,sage,sgender,sweight,sheight,sblood;
    Button submit;
    DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("item");

        sname = findViewById(R.id.name);
        sage = findViewById(R.id.age);
        sgender = findViewById(R.id.gender);
        sweight = findViewById(R.id.weight);
        sheight = findViewById(R.id.height);

        sblood = findViewById(R.id.blood);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
                Intent intent = new Intent(PersonalActivity.this, AppointMentsDashboardActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addItem () {

        String name = sname.getText().toString();
        String age = sage.getText().toString();
        String gender = sgender.getText().toString();
        String weight= sweight.getText().toString();
        String height= sheight.getText().toString();

        String blood = sblood.getText().toString();

        if (!TextUtils.isEmpty(name)) {
            String id = mDatabaseReference.push().getKey();
            AddItem item = new AddItem(name,age,gender,weight,height,blood);
            mDatabaseReference.child(FirebaseAuth.getInstance().getUid()).child(id).setValue(item);
            Toast.makeText(this, "Details added successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enter the details", Toast.LENGTH_SHORT).show();
        }
    }
}
