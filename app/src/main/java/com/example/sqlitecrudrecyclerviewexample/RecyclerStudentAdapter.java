package com.example.sqlitecrudrecyclerviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerStudentAdapter extends RecyclerView.Adapter<RecyclerStudentAdapter.ViewHolder> {
    Context context;
    ArrayList<MyModel> data;

    public RecyclerStudentAdapter(Context context, ArrayList<MyModel> data)
    {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_info_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.student_id.setText(String.valueOf(data.get(position).student_id));
        holder.roll_number.setText(String.valueOf(data.get(position).roll_number));
        holder.registration_number.setText(String.valueOf(data.get(position).registration_number));
        holder.country.setText(data.get(position).country);
        holder.district.setText(data.get(position).district);
        holder.full_address.setText(data.get(position).full_address);
        holder.sex.setText(data.get(position).sex);
        holder.mother_name.setText(data.get(position).mother_name);
        holder.father_name.setText(data.get(position).father_name);
        holder.study_group.setText(data.get(position).study_group);
        holder.enrollment_class.setText(data.get(position).enrollment_class);
        holder.dob.setText(data.get(position).dob);
        holder.name.setText(data.get(position).name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView student_id, roll_number, registration_number, name, dob, enrollment_class,
                study_group, father_name, mother_name, sex, full_address, district, country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id = itemView.findViewById(R.id.student_id);
            roll_number = itemView.findViewById(R.id.roll_number);
            registration_number = itemView.findViewById(R.id.registration_number);
            name = itemView.findViewById(R.id.name);
            dob = itemView.findViewById(R.id.dob);
            enrollment_class = itemView.findViewById(R.id.enrollment_class);
            study_group = itemView.findViewById(R.id.study_group);
            father_name = itemView.findViewById(R.id.father_name);
            mother_name = itemView.findViewById(R.id.mother_name);
            sex = itemView.findViewById(R.id.sex);
            full_address = itemView.findViewById(R.id.full_address);
            district = itemView.findViewById(R.id.district);
            country = itemView.findViewById(R.id.country);
        }
    }
}
