package usonsonate.com.tukybirth;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.getbase.floatingactionbutton.FloatingActionButton;
import android.widget.EditText;

import com.applandeo.materialcalendarview.CalendarView;

import java.util.Calendar;
import java.util.Date;

import usonsonate.com.tukybirth.Calendar.MyEventDay;

public class AddNoteActivity extends AppCompatActivity {

    FloatingActionButton button;
    Calendar c;
    Date lastdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        setTitle("Agregar nota");
        //Inicializamos el calendario del sistema para poder asignar una fecha
        c = Calendar.getInstance();
        lastdate = new Date();
        c.setTime(lastdate);



        final CalendarView datePicker = (CalendarView) findViewById(R.id.datePicker);

        //Establecemos la fecha de inicio del calendario principal (widget)
        try {
            datePicker.setDate(c);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        button = findViewById(R.id.addNoteButton);
        final EditText noteEditText = findViewById(R.id.noteEditText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                MyEventDay myEventDay = new MyEventDay(datePicker.getSelectedDate(),
                        R.drawable.note, noteEditText.getText().toString());
                returnIntent.putExtra(MainNotes.RESULT, myEventDay);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
