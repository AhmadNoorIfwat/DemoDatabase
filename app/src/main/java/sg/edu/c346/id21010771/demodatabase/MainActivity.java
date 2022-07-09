package sg.edu.c346.id21010771.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    EditText etTask, etTaskDate;
    ListView lv;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        etTask = findViewById(R.id.etTask);
        etTaskDate = findViewById(R.id.etTaskDate);
        lv = findViewById(R.id.lv);
        alTask = new ArrayList<>();
        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lv.setAdapter(aaTask);

        btnInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //create the DBHelper object, passing in the
                //activity's context
                DBHelper db = new DBHelper(MainActivity.this);


                //insert a task
                db.insertTask(etTask.getText().toString(), etTaskDate.getText().toString());


            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                aaTask.clear();
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<Task> dataList = db.getTasks();

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                    alTask.add(dataList.get(i).getId() + "\n" + dataList.get(i).getDescription() + "\n" + dataList.get(i).getDate() + "\n" );

                }
                aaTask.notifyDataSetChanged();
                tvResults.setText(txt);

            }
        });

    }
}


