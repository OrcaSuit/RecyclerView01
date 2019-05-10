package com.example.recyclerview1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;
    private List<Student> mStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mStudents = new ArrayList<Student>();

        for (int i = 0; i < 100; i++) {
            Student student = new Student();

            student.setmName("CETI" + i);
            student.setmNumber("201600" + i);
            student.setmDepartment("컴퓨터 공학" + i);

            mStudents.add(student);
        }

        mListAdapter = new ListAdapter(MainActivity.this, mStudents);
        mRecyclerView.setAdapter(mListAdapter);
    }


    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mName;
        private TextView mNumber;
        private TextView mDepart;
        private Student mStudent;

        public ListViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name_text);
            mNumber = (TextView) itemView.findViewById(R.id.number_text);
            mDepart = (TextView) itemView.findViewById(R.id.department_text);
            itemView.setOnClickListener(this);
        }

        public void bindStudent(Student s) {
            mStudent = s;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), mStudent.getmName() + "is selected!!", Toast.LENGTH_SHORT).show();
        }
    }

    private class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
        private List<Student> mStudents;
        private Context mContext;

        public ListAdapter(Context context, List<Student> students) {
            this.mContext = context;
            this.mStudents = students;
        }

        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item_layout, null);

            return new ListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ListViewHolder holder, int position) {
            Student student = mStudents.get(position);
            holder.bindStudent(student);

            holder.mName.setText(student.getmName());
            holder.mNumber.setText(student.getmNumber());
            holder.mDepart.setText(student.getmDepartment());
        }

        @Override
        public int getItemCount() {return (mStudents != null ? mStudents.size() : 0); }
    }
}

