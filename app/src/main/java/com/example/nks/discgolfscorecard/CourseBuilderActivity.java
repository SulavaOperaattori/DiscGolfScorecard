package com.example.nks.discgolfscorecard;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CourseBuilderActivity extends AppCompatActivity implements CourseDialog.dialogCloseListener {
    private CourseDialog.dialogCloseListener listener;
    int lastShownItem;
    int duration = Toast.LENGTH_SHORT;
    Course newCourse;
    TextView cTextview;
    private List<String> holes=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cTextview = (TextView) findViewById(R.id.seekBarText);
        showAlertDialog();
    }

    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        CourseDialog alertDialog = CourseDialog.newInstance("Create a new course");
        alertDialog.show(fm, "fragment_alert");
    }

    public void setHoleInfo() {
        for (int i=0; i < newCourse.getHoles(); i++) {
            holes.add("Hole: "+(i+1));
        }
    }

    @Override
    public void dialog_close(Course course) {
        newCourse=course;
        setHoleInfo();
        createUI();
    }

    private void createUI() {
        setContentView(R.layout.activity_course_builder);
        ListAdapter CourseBuilderList = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, holes);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(CourseBuilderList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lastShownItem = i;
                Toast.makeText(CourseBuilderActivity.this, holes.get(lastShownItem), duration).show();
            }
        });
    }
}