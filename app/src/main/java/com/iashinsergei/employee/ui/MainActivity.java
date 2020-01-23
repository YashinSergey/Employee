package com.iashinsergei.employee.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iashinsergei.employee.R;
import com.iashinsergei.employee.data.entity.Employee;
import com.iashinsergei.employee.presenter.Presenter;
import com.iashinsergei.employee.ui.adapter.RvAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this);
    }

    @Override
    public Context passContext() {
        return this;
    }

    @Override
    public void initRecyclerView(List<Employee> list){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RvAdapter adapter = new RvAdapter(list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
