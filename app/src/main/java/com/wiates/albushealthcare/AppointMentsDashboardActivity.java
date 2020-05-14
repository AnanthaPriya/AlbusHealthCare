package com.wiates.albushealthcare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.wiates.albushealthcare.adapters.AppointmentsAdapter;

public class AppointMentsDashboardActivity extends AppCompatActivity {

    private ListView appointments_ListView;
    private AppointmentsAdapter appointmentsAdapter;
    private ImageButton addAppointments;
    private final int CREATE_APPOINTMENT = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_ments_dashboard);

        appointments_ListView = findViewById(R.id.appoints_list_view);
        appointmentsAdapter = new AppointmentsAdapter(this);
        appointments_ListView.setAdapter(appointmentsAdapter);

        addAppointments = findViewById(R.id.add_appointment_image_button);

        addAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AppointMentsDashboardActivity.this, CreateAppointment.class),CREATE_APPOINTMENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            UpdateTheAdapter();
        }
    }

    private void UpdateTheAdapter() {
        this.appointmentsAdapter.UpdateAdapter();
    }
}
