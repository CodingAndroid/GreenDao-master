package com.helloworld.greendaodemo_master;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends Activity {

    private StudentDao studentDao;

    private StuInfoAdapter stuInfoAdapter;

    private List<Student> mStudentList;

    private String name_text, stu_no_text, stu_sex_text,stu_score_text;

    private EditText name_edit, stu_no_edit, stu_sex_edit, stu_score_edit;

    private Button add_btn;

    private Student student;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        DaoSession daoSession = ((GreenDaoApp)getApplication()).getDaoSession();

        studentDao = daoSession.getStudentDao();

        mStudentList = studentDao.queryBuilder().list();

        setupViews();
    }


    public void setupViews(){
        mRecyclerView = findViewById(R.id.stu_info);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stuInfoAdapter = new StuInfoAdapter(mStudentList, MainActivity.this, R.layout.stu_info_item);
        mRecyclerView.setAdapter(stuInfoAdapter);
        stuInfoAdapter.setOnClickItemListener(new StuInfoAdapter.OnClickItemListener() {
            @Override
            public void OnItemClick(int position) {
                showDeleteAlertDialog(mStudentList.get(position).getStuId());
            }
        });

        name_edit = findViewById(R.id.name_edittext);
        stu_no_edit = findViewById(R.id.stu_no);
        stu_sex_edit = findViewById(R.id.stu_sex);
        stu_score_edit = findViewById(R.id.stu_score);

        add_btn = findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInfo();
            }
        });
    }

    public void addInfo(){
        name_text = name_edit.getText().toString();
        stu_no_text = stu_no_edit.getText().toString();
        stu_sex_text = stu_sex_edit.getText().toString();
        stu_score_text = stu_score_edit.getText().toString();

        if (name_text.equals("")){
            name_edit.setError("Name is required");
            return;
        }
        student = new Student();
        student.setStuName(name_text);
        student.setStuNo(stu_no_text);
        student.setStuSex(stu_sex_text);
        student.setStuScore(stu_score_text);
        studentDao.insert(student);
        cleanEditText();
        refreshList();
    }

    private void refreshList(){
        mStudentList.clear();
        mStudentList.addAll(studentDao.queryBuilder().list());
        stuInfoAdapter.notifyDataSetChanged();
    }

    private void deleteStudent(long id){
        studentDao.deleteByKey(id);
        refreshList();
    }

    private void cleanEditText(){
        name_edit.setText("");
        stu_no_edit.setText("");
        stu_sex_edit.setText("");
        stu_score_edit.setText("");
    }

    private void showDeleteAlertDialog(final long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete this item?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteStudent(id);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.create().show();
    }
}
