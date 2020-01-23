package com.iashinsergei.employee.data.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.iashinsergei.employee.data.entity.Employee;

@Database(entities = {Employee.class}, version = 1)
public abstract class EmployeeDatabase extends RoomDatabase {

    private static EmployeeDatabase instance;

    public abstract EmployeeDao employeeDao();

    public static synchronized EmployeeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EmployeeDatabase.class, "employee_database" )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private EmployeeDao employeeDao;

        PopulateDbAsyncTask(EmployeeDatabase database) {
            employeeDao = database.employeeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            employeeDao.insert(new Employee("Lilu", "Dallas", 19, "saviour"));
            employeeDao.insert(new Employee("Daenerys", "Targaryen", 18, "dragons mom"));
            employeeDao.insert(new Employee("Yoda", "unknown", 900, "master"));
            employeeDao.insert(new Employee("Freddy", "Krueger", 41, "maniac"));
            employeeDao.insert(new Employee("Leon", "-", 35, "killer"));
            employeeDao.insert(new Employee("Sherlock", "Holmes", 30, "private detective"));
            employeeDao.insert(new Employee("Indiana", "Jones", 32, "professor"));
            employeeDao.insert(new Employee("James", "Bond", 30, "agent 007"));
            return null;
        }
    }
}
