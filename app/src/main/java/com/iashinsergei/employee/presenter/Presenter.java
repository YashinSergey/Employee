package com.iashinsergei.employee.presenter;

import com.iashinsergei.employee.ui.MainView;
import com.iashinsergei.employee.data.database.RoomHelper;
import com.iashinsergei.employee.data.entity.Employee;

import java.util.List;

public class Presenter {

    private MainView mainView;
    private RoomHelper roomHelper;
    private List<Employee> employeeList;

    public Presenter(MainView mainView) {
        this.mainView = mainView;
        roomHelper = new RoomHelper(this.mainView.passContext());
        employeeList = roomHelper.getAllEmployees();
        this.mainView.initRecyclerView(employeeList);
    }
}
