
package com.wiates.albushealthcare;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.Toast;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class HealthRecordActivity extends AppCompatActivity {
    Button submit;
    Boolean a,b,c,d,e,f;
    DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_record);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("item");

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(HealthRecordActivity.this, SetRemainderActivity.class);
                //startActivity(intent);
            }

        });


    }
    public void onRadioButtonClicked(View view) {


        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.a1:
                a = checked;
                break;
            case R.id.b1:
                b = checked;
                break;
            case R.id.c1:
                c = checked;
                break;


            case R.id.d1:
                d = checked;
                break;

            case R.id.e1:
                e = checked;
                break;

            case R.id.f1:
                f = checked;
                break;



        }
    }}