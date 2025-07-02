package com.example.sqlitecrudrecyclerviewexample;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        MySQLiteHelper studentDB = new MySQLiteHelper(this);

        /*

        studentDB.insertData(1, 991, "Monir", "26-01-1983", "IV", "Humanities", "Wadud",
                "Jahanara", "Male", "Lalpur", "Natore", "Bangladesh");

        studentDB.insertData(2, 992, "Halim", "26-02-1983", "IV", "Science", "Nofil",
                "Ma", "Male", "Lalpur", "Natore", "Bangladesh");

        studentDB.insertData(3, 993, "Baschu", "26-03-1983", "IV", "Commerce", "Jamshed",
                "Ma", "Male", "Lalpur", "Natore", "Bangladesh");

        studentDB.insertData(4, 994, "Kibria", "26-04-1983", "IV", "Humanities", "Momotaj",
                "Ma", "Male", "Lalpur", "Natore", "Bangladesh");
         */

        ArrayList<MyModel> data = new ArrayList<>();
        data = studentDB.readData();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerStudentAdapter adapter = new RecyclerStudentAdapter(this, data);
        recyclerView.setAdapter(adapter);

        /*
        studentTable.deleteData(5);
        studentTable.deleteData(6);
        studentTable.deleteData(7);
        studentTable.deleteData(8);
        */

        /*
        studentTable.updateData(1);
        */

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}