package com.iashinsergei.employee.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.iashinsergei.employee.data.entity.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert
    void insert(Employee employee);

    @Update
    void update(Employee employee);

    @Query("SELECT * FROM employees_table")
    List<Employee> getAllEmployees();

    @Query("SELECT * FROM employees_table WHERE id LIKE :id LIMIT 1")
    Employee getById(int id);

    @Query("SELECT * FROM employees_table WHERE name LIKE :name")
    List<Employee> getByName(String name);

    @Query("SELECT * FROM employees_table WHERE surname LIKE :surname")
    List<Employee> getBySurname(String surname);

    @Delete
    void delete(Employee employee);

    @Query("DELETE FROM employees_table")
    void deleteAllEmployees();
}
