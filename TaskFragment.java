package ali.ahmed.ed.dailyduty;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TaskFragment extends Fragment {
    private EditText title;
    private EditText etDate;
    private EditText startTime;
    private EditText endTime;
    private EditText description;
    private Button save;
    Calendar myCalendar = Calendar.getInstance();
    Calendar myTime = Calendar.getInstance();
    private int hour;
    private int minute;
    private int year;
    private int month;
    private int day;
    private DataHelper helper;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.task_fragment, container, false);
        title = v.findViewById(R.id.etTitle);
        description = v.findViewById(R.id.etDescription);
        etDate = v.findViewById(R.id.etDate);
        startTime = v.findViewById(R.id.etTime);
        endTime = v.findViewById(R.id.etTime2);
        save = v.findViewById(R.id.bSave);
        helper = new DataHelper(getActivity());
        db = helper.getWritableDatabase();
        year = myCalendar.get(Calendar.YEAR);
        month = myCalendar.get(Calendar.MONTH);
        day = myCalendar.get(Calendar.DAY_OF_MONTH);
        hour = myTime.get(Calendar.HOUR_OF_DAY);
        minute = myTime.get(Calendar.MINUTE);
        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, year, month, day).show();
            }
        });
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(getActivity(), fromTime,hour,minute,true).show();
            }
        });
        endTime.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(getActivity(), toTime,hour,minute,true).show();
            }
        }));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String taskTitle = title.getText().toString();
                    String taskdesc  = description.getText().toString();
                    //inserting data into the table students created in my MyHelper
                    //nullColumnHack to make columns can be left empty from users inputs
                    ContentValues row = new ContentValues(); //contentvalues takes the data and insert it into table
                    row.put("title", taskTitle); // takes "key" and value
                    row.put("description", taskdesc);
                    row.put("date", String.valueOf(etDate));
                    row.put("fromTime", String.valueOf(startTime));
                    row.put("toTime", String.valueOf(endTime));
                    //db.insert returns value long of the ID for the newly added row--if its -1, an error occurred during adding to data base
                    long id = db.insert("tasks", null, row);
                    if (id != -1){
                        Toast.makeText(getActivity(), "You have been added successfully "+id, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Error Occurred. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
        });
        return v;
    }
    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int myYear, int myMonth, int myDay) {
            myCalendar.set(Calendar.YEAR, myYear);
            myCalendar.set(Calendar.MONTH, myMonth);
            myCalendar.set(Calendar.DAY_OF_MONTH, myDay);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "MM-dd-yyyy"; // In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etDate.setText(sdf.format(myCalendar.getTime()));
    }
    final TimePickerDialog.OnTimeSetListener fromTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int myHour, int myMinute) {
            myTime.set(Calendar.HOUR_OF_DAY, myHour);
            myTime.set(Calendar.MINUTE, myMinute);
            startTime.setText(myHour+":"+myMinute);
        }
    };
    final TimePickerDialog.OnTimeSetListener toTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int myHour, int myMinute) {
            myTime.set(Calendar.HOUR_OF_DAY, myHour);
            myTime.set(Calendar.MINUTE, myMinute);
            endTime.setText(myHour+":"+myMinute);
        }
    };
}