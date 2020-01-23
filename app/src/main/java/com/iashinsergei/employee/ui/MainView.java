package com.iashinsergei.employee.ui;

import android.content.Context;

import com.iashinsergei.employee.data.entity.Employee;

import java.util.List;

public interface MainView {

    Context passContext();
    void initRecyclerView(List<Employee> list);
}
