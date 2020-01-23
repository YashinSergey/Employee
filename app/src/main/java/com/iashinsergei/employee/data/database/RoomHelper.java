package com.iashinsergei.employee.data.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.iashinsergei.employee.data.entity.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomHelper {

    private EmployeeDao employeeDao;
    private List<Employee> employeeList;
    private Map<Integer, Employee> identityMap;

    @SuppressLint("UseSparseArrays")
    public RoomHelper(Context context) {
        EmployeeDatabase database = EmployeeDatabase.getInstance(context);
        employeeDao = database.employeeDao();
        employeeList = employeeDao.getAllEmployees();
        identityMap = new HashMap<>();
    }

    public void insert(Employee employee) {
        new InsertEmployeeAsyncTask(employeeDao).execute(employee);
        identityMap.put(employee.getId(), employee);
    }

    public void update(Employee employee) {
        new UpdateEmployeeAsyncTask(employeeDao).execute(employee);
        identityMap.replace(employee.getId(), employee);
    }

    public void delete(Employee employee) {
        new DeleteEmployeeAsyncTask(employeeDao).execute(employee);
        identityMap.remove(employee.getId());
    }

    public void deleteAllEmployees() {
        new DeleteAllEmployeesAsyncTask(employeeDao).execute();
        identityMap.clear();
    }

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public void getById(int id) {
        employeeDao.getById(id);
    }

    public void getByName(String name) {
        employeeDao.getByName(name);
    }

    public void getBySurname(String surname) {
        employeeDao.getBySurname(surname);
    }

    private static class InsertEmployeeAsyncTask extends AsyncTask<Employee, Void, Void> {

        private EmployeeDao employeeDao;

        private InsertEmployeeAsyncTask(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDao.insert(employees[0]);
            return null;
        }
    }

    private static class UpdateEmployeeAsyncTask extends AsyncTask<Employee, Void, Void> {

        private EmployeeDao employeeDao;

        private UpdateEmployeeAsyncTask(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDao.update(employees[0]);
            return null;
        }
    }

    private static class DeleteEmployeeAsyncTask extends AsyncTask<Employee, Void, Void> {

        private EmployeeDao employeeDao;

        private DeleteEmployeeAsyncTask(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDao.delete(employees[0]);
            return null;
        }
    }

    private static class DeleteAllEmployeesAsyncTask extends AsyncTask<Void, Void, Void> {

        private EmployeeDao employeeDao;

        private DeleteAllEmployeesAsyncTask(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            employeeDao.deleteAllEmployees();
            return null;
        }
    }
}
