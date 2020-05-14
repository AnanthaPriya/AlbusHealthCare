package com.wiates.albushealthcare.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wiates.albushealthcare.R;
import com.wiates.albushealthcare.datamodels.Appointment;

import java.util.ArrayList;

public class AppointmentsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Appointment> AppointmentList;
    private DatabaseReference ref ;

    public AppointmentsAdapter(Context context) {
        this.context = context;
        AppointmentList = getAppointmentList();
        ref = FirebaseDatabase.getInstance().getReference("appointments/"+FirebaseAuth.getInstance().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Appointment> temp = new ArrayList<Appointment>();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    Appointment appointment = dataSnapshot1.getValue(Appointment.class);
                    temp.add(appointment);
                    Log.e("Tag",appointment.doctorName);

                }

                AppointmentList = temp;
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static ArrayList<Appointment> getAppointmentList(){

        ArrayList<Appointment> temp = new ArrayList<Appointment>();

        return temp;
    }

    @Override
    public int getCount() {
        return AppointmentList.size();
    }

    @Override
    public Object getItem(int position) {
        return AppointmentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return AppointmentList.get(position).iD;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.appointment_list_item,parent,false);
        }
        Appointment appointment = AppointmentList.get(position);
        TextView Day = convertView.findViewById(R.id.appointment_date_text_view);
        Day.setText(appointment.day + "/" + appointment.month + "/" +appointment.year);

        TextView Time = convertView.findViewById(R.id.appointment_time_text_view);
        Time.setText(appointment.hour + ":" + appointment.minutes);

        TextView Doctor = convertView.findViewById((R.id.doctor_text_view));
        Doctor.setText(appointment.doctorName);

        TextView Phone = convertView.findViewById((R.id.doctor_phone_number));
        Phone.setText(appointment.phoneNumber);

        return convertView;
    }

    public void UpdateAdapter(){
        /*AppointmentList = getAppointmentList();
        this.notifyDataSetChanged();*/
    }
}
