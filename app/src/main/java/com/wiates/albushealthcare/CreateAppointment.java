package com.wiates.albushealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wiates.albushealthcare.BoarcastReciever.NotificationForwarder;
import com.wiates.albushealthcare.datamodels.Appointment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class CreateAppointment extends AppCompatActivity {

    TextInputLayout doctorName;
    TextInputLayout doctorPhone;

    TextView date;
    TextView time;
    Button submit;

    Appointment appointment;

    DatePickerDialog picker;
    TimePickerDialog timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        appointment = new Appointment();

        doctorName = findViewById(R.id.Doctor_Name_edit_text);
        doctorPhone = findViewById(R.id.doctor_phone_number_edit_text);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        submit = findViewById(R.id.add_appointment_button);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmission();
            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(CreateAppointment.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    appointment.day = dayOfMonth;
                                    appointment.month = monthOfYear+1;
                                    appointment.year = year;
                                    date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();
                }
            });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker = new TimePickerDialog(CreateAppointment.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        appointment.hour = hourOfDay;
                        appointment.minutes = minute;
                        time.setText(hourOfDay + ":" +minute);
                    }
                },0,0,true);
                timePicker.show();
            }
        });

    }

    private void handleSubmission() {
        appointment.iD = (new Random().nextLong());
        appointment.doctorName = doctorName.getEditText().getText().toString();
        appointment.phoneNumber = doctorPhone.getEditText().getText().toString();

        if(!appointment.doctorName.isEmpty()
            && !appointment.phoneNumber.isEmpty()
            && appointment.year != 0
            && appointment.month != 0
            && appointment.day != 0
        ){
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("appointments/"+FirebaseAuth.getInstance().getUid());
            String id = ref.push().getKey();
            ref.child(id).setValue(appointment);
            setUpAlarmService(appointment);
            setResult(RESULT_OK);
            finish();
        }



    }

    private void setUpAlarmService(Appointment appointment) {
        // do The Boardcast .
        Intent myIntent = new Intent(this, NotificationForwarder.class);
        myIntent.putExtra("ID",appointment.iD);
        myIntent.putExtra("DOCTOR_NAME",appointment.doctorName);
        myIntent.putExtra("PHONE",appointment.phoneNumber);

        Calendar calandar = Calendar.getInstance();
        calandar.set(appointment.year,appointment.month,appointment.day,appointment.hour,appointment.minutes);
        Log.e("SEconds",""+calandar.getTimeInMillis());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        Log.e("APP","THE BOARDCAST HAS BEEN SET UP");
        alarm.setExact(AlarmManager.RTC_WAKEUP,calandar.getTimeInMillis(),pendingIntent);
    }
}
