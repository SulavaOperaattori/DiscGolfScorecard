package com.example.nks.discgolfscorecard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nks on 9/10/17.
 */

public class CourseDialog extends AppCompatDialogFragment {
    SeekBar cSeekbar;
    int holeSlider = 0;
    TextView cTextview;

    public CourseDialog() {
        // Empty constructor required for DialogFragment
    }

    public static CourseDialog newInstance(String title) {
        CourseDialog frag = new CourseDialog();
        Bundle args = new Bundle();
        args.putString("title", title); //Setting alertdialog title
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       // Create the view to show

        Toast.makeText(getActivity(), "perse", Toast.LENGTH_SHORT).show();

        View cView = LayoutInflater.from(getActivity()).inflate(R.layout.course_dialog_custom, null);

        // Build the alertdialog

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(cView);

        cSeekbar = cView.findViewById(R.id.courseDialogSeekbar);
        cTextview = cView.findViewById(R.id.seekBarText);

        cSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                cTextview.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

// Set positive and negative buttons
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Toast.makeText(getActivity(), "perse", Toast.LENGTH_SHORT).show(); // Testiprinttaus, käy tässä funktiossa
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        return alertDialogBuilder.create();

    }


        }







