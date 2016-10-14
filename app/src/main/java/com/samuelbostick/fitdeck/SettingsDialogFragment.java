package com.samuelbostick.fitdeck;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;


public class SettingsDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        MainActivity activity = (MainActivity)getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View v = activity.getLayoutInflater().inflate(R.layout.fragment_settings_dialog, null);

        builder.setTitle("Exercise Settings");
        builder.setMessage("Enter a list of exercises to perform, separated by a comma (,).");
        builder.setView(v);
        builder.setPositiveButton("Okay", new OnPositiveClickListener());
        builder.setNegativeButton("Cancel", new OnNegativeClickListener());

        Dialog dialog = builder.create();

        EditText suitesEdit = (EditText)v.findViewById(R.id.suites_edit);
        activity.loadSuites();
        suitesEdit.setText(TextUtils.join(",", activity.getSuites()));

        return dialog;
    }

    class OnPositiveClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int id){
            EditText suitesEdit = (EditText)getDialog().findViewById(R.id.suites_edit);
            ((MainActivity)getActivity()).saveSuites(suitesEdit.getText().toString().split(","));
        }
    }

    class OnNegativeClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int id){

        }
    }
}
