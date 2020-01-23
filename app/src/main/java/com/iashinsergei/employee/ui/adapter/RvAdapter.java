package com.iashinsergei.employee.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iashinsergei.employee.R;
import com.iashinsergei.employee.data.entity.Employee;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private List<Employee> employeeList;
    private Context context;

    public RvAdapter(List<Employee> employeeList, Context context) {
        this.employeeList = employeeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bind(employeeList.get(position));

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, employeeList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void setEmployeeList(List<Employee> employees) {
        employeeList = employees;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvSurname;
        TextView tvAge;
        TextView tvPosition;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        void bind(Employee employee) {
            tvName.setText(employee.getName());
            tvSurname.setText(employee.getSurname());
            tvAge.setText(String.valueOf(employee.getAge()));
            tvPosition.setText(employee.getPosition());
        }

        private void initViews(@NonNull View itemView) {
            tvName = itemView.findViewById(R.id.tv_name);
            tvSurname = itemView.findViewById(R.id.tv_surname);
            tvAge = itemView.findViewById(R.id.tv_age);
            tvPosition = itemView.findViewById(R.id.tv_position);
        }
    }
}
