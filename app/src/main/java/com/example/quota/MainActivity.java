package com.example.quota;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView RecyclerView;
    classAdapter classAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ClassItem> ClassItem = new ArrayList<>();
    Toolbar toolbar;
    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);

        fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(v -> showDialog());

        loadData();

        RecyclerView = findViewById(R.id.RecyclerView);
        RecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(layoutManager);
        classAdapter = new classAdapter(this, ClassItem);
        RecyclerView.setAdapter(classAdapter);
        classAdapter.setOnItemClickListener(position -> gotoItemActivity(position));
        setToolbar();
    }

    private void loadData() {
        Cursor cursor = dbHelper.getClassTable();

        ClassItem.clear();
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(DbHelper.C_ID));
            @SuppressLint("Range")
            String sectionName = cursor.getString(cursor.getColumnIndex(DbHelper.SECTION_NAME_KEY));
            @SuppressLint("Range")
            String gradeLevel = cursor.getString(cursor.getColumnIndex(DbHelper.GRADE_LEVEL_KEY));
            ClassItem.add(new ClassItem(id, sectionName, gradeLevel ));
        }
    }


    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        TextView title = toolbar.findViewById(R.id.title_toolbar);
        TextView subtitle = toolbar.findViewById(R.id.subtitle_toolbar);
        ImageButton back = toolbar.findViewById(R.id.back);
        ImageButton save = toolbar.findViewById(R.id.save);

        title.setText("Q.U.O.T.A.");
        subtitle.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);
    }

    private void gotoItemActivity(int position) {
        Intent intent = new Intent(this, StudentActivity.class);

        intent.putExtra("sectionName", ClassItem.get(position).getSection());
        intent.putExtra("gradeLevel", ClassItem.get(position).getGrade());
        intent.putExtra("cid", ClassItem.get(position).getCid());
        startActivity(intent);
    }

    private void showDialog() {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(), MyDialog.CLASS_ADD_DIALOG);
        dialog.setListener((sectionName, gradeLevel) -> addClass(sectionName, gradeLevel));

    }

    @SuppressLint("NotifyDataSetChanged")
    private void addClass(String sectionName, String gradeLevel) {
        long cid = dbHelper.addClass(sectionName, gradeLevel);
        ClassItem classItem = new ClassItem(cid,sectionName, gradeLevel);
        ClassItem.add(classItem);
        classAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                showUpdateDialog(item.getGroupId());
                break;
            case 1:
                deleteClass(item.getGroupId());
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(int position) {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.CLASS_UPDATE_DIALOG);
        dialog.setListener((sectionName, gradeLevel)->updateClass(position, sectionName, gradeLevel));
    }

    private void updateClass(int position, String sectionName, String gradeLevel) {
        dbHelper.updateClass(ClassItem.get(position).getCid(),sectionName, gradeLevel);
        ClassItem.get(position).setSectionName(sectionName);
        ClassItem.get(position).setGradeLevel(gradeLevel);
        classAdapter.notifyItemChanged(position);
    }

    private void deleteClass(int position) {
        dbHelper.deleteClass(ClassItem.get(position).getCid());
        ClassItem.remove(position);
        classAdapter.notifyItemRemoved(position);

    }
}
