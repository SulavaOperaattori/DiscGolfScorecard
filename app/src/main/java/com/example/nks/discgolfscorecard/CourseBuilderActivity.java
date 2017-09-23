package com.example.nks.discgolfscorecard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CourseBuilderActivity extends AppCompatActivity {

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
        //setHoleInfo();

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

    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        CourseDialog alertDialog = CourseDialog.newInstance("Create a new course");
        alertDialog.show(fm, "fragment_alert");
        newCourse = alertDialog.getNewCourse();
    }

    public void setHoleInfo() {
        for (int i=0; i < newCourse.getHoles(); i++) {
            holes.add("Hole: "+(i+1));
        }
    }
}