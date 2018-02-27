package ali.ahmed.ed.dailyduty;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataHelper extends SQLiteOpenHelper {
    public DataHelper(Context context) {
        super(context, "Tasks", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tasks(id integer primary key autoincrement, title varchar(50), date varchar(20), fromTime varchar(5), toTime varchar(5), description varchar(150))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
