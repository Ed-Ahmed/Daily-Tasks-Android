package ali.ahmed.ed.dailyduty;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NewTaskActivity extends AppCompatActivity {
    TaskFragment newTask = new TaskFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction(); //beginning of transaction
        //adding and deleting fragments here
        //transaction.add(R.id.cLayout, login, "mylogin"); //context, fragment, Tag = id
        transaction.add(R.id.containerLayout, newTask,"newTask");
        transaction.commit(); //end of transaction
    }
}
