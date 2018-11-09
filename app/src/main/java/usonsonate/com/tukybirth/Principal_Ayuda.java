package usonsonate.com.tukybirth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Principal_Ayuda extends AppCompatActivity {

    TextView lblayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal__ayuda);

        this.lblayuda = findViewById(R.id.lblayuda);

        lblayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
