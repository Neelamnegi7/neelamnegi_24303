package com.example.projectapplication;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MyAdapter extends ArrayAdapter<String> implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    ArrayList<String> listItem;
    Context mContext;

    int notesLayout, hour, minute;

    MyViewHolder myViewHolder;

    public MyAdapter(Context context, int resource, ArrayList<String> notelist) {

        super(context, resource, notelist);

        this.mContext = context;
        this.notesLayout = resource;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup)
    {

        String note = String.valueOf(getItem(position));

        if(convertView == null)
        {
            myViewHolder = new MyViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);

            convertView = inflater.inflate(notesLayout, viewGroup, false);

            myViewHolder.noteDesc = convertView.findViewById(R.id.tv_note_desc);
            myViewHolder.noteimg = convertView.findViewById(R.id.img_add_alarm);

            myViewHolder.noteimg.setOnClickListener(this);

            convertView.setTag(myViewHolder);
        }
        else
        {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        myViewHolder.noteDesc.setText(note);

        return convertView;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.img_add_alarm)
        {
            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, android.R.style.Theme_Material_Dialog_Alert, this, hour, minute, true);

            timePickerDialog.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfHour) {

        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();

        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calSet.set(Calendar.MINUTE, minuteOfHour);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        if (calSet.compareTo(calNow) <= 0) {

            calSet.add(Calendar.DATE, 0);
        }

        setAlarm(calSet);

    }

    private void setAlarm(Calendar targetCal) {


        Toast.makeText(mContext, "Alarm is set at" + targetCal.getTime(),Toast.LENGTH_LONG).show();

        Intent intent = new Intent(mContext, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }

    private static class MyViewHolder
    {
        TextView noteDesc;
        ImageView noteimg;
    }
}

