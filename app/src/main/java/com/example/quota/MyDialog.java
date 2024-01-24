package com.example.quota;


import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {
    public static final String CLASS_ADD_DIALOG="addClass";
    public static final String CLASS_UPDATE_DIALOG="updateClass";
    public static final String STUDENT_ADD_DIALOG="addStudent";
    public static final String STUDENT_UPDATE_DIALOG = "updateStudent";

    private OnClickListener listener;
    private int roll;
    private String name;

    public MyDialog(int roll, String name) {

        this.roll = roll;
        this.name = name;
    }

    public MyDialog() {

    }

    public interface OnClickListener{
        void onClick(String text1,String text2);
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        if(getTag().equals(CLASS_ADD_DIALOG))dialog=getAddClassDialog();
        if(getTag().equals(STUDENT_ADD_DIALOG))dialog=getAddStudentDialog();
        if(getTag().equals(CLASS_UPDATE_DIALOG))dialog=getUpdateClassDialog();
        if(getTag().equals(STUDENT_UPDATE_DIALOG))dialog=getUpdateStudentDialog();


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return dialog;
    }

    private Dialog getUpdateStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Update Student");

        EditText roll_edt = view.findViewById(R.id.est01);
        EditText name_edt = view.findViewById(R.id.edt02);

        roll_edt.setHint("Roll");
        name_edt.setHint("Name");
        Button cancel = view.findViewById(R.id.cancel_Btn);
        Button add = view.findViewById(R.id.add_Btn);
        add.setText("update");
        roll_edt.setText(roll+"");
        roll_edt.setEnabled(false);
        name_edt.setText(name);
        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v-> {
            String roll = roll_edt.getText().toString();
            String name = name_edt.getText().toString();
            listener.onClick(roll,name);
            dismiss();
        });


        return builder.create();
    }

    private Dialog getUpdateClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Update Class");

        EditText section_edt = view.findViewById(R.id.est01);
        EditText grade_edt = view.findViewById(R.id.edt02);

        section_edt.setHint("Section/Strand");
        grade_edt.setHint("Grade Level");
        Button cancel = view.findViewById(R.id.cancel_Btn);
        Button add = view.findViewById(R.id.add_Btn);
        add.setText("Update");

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String sectionName = section_edt.getText().toString();
            String gradeLevel = grade_edt.getText().toString();
            listener.onClick(sectionName,gradeLevel);
            dismiss();
        });


        return builder.create();
    }

    private Dialog getAddStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Add New Student");

        EditText roll_edt = view.findViewById(R.id.est01);
        EditText name_edt = view.findViewById(R.id.edt02);

        roll_edt.setHint("Roll");
        name_edt.setHint("Name");
        Button cancel = view.findViewById(R.id.cancel_Btn);
        Button add = view.findViewById(R.id.add_Btn);

        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v-> {
            String roll = roll_edt.getText().toString();
            String name = name_edt.getText().toString();
            roll_edt.setText(String.valueOf(Integer.parseInt(roll)+1));
            name_edt.setText("");
            listener.onClick(roll,name);
        });


        return builder.create();
    }

    private Dialog getAddClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Add New Class");

        EditText section_edt = view.findViewById(R.id.est01);
        EditText grade_edt = view.findViewById(R.id.edt02);

        section_edt.setHint("Section/Strand");
        grade_edt.setHint("Grade Level");
        Button cancel = view.findViewById(R.id.cancel_Btn);
        Button add = view.findViewById(R.id.add_Btn);

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String sectionName = section_edt.getText().toString();
            String gradeLevel = grade_edt.getText().toString();
            listener.onClick(sectionName,gradeLevel);
            dismiss();
        });


        return builder.create();
    }
}
