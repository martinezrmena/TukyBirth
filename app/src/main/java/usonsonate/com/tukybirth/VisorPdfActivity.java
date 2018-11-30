package usonsonate.com.tukybirth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class VisorPdfActivity extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_pdf);

        /**********************PLAN DE DIETA TRIMESTRE 1***********************/
        if(getIntent().getStringExtra("imgposicion").equals("0") && getIntent().getStringExtra("trimestrePosicion").equals("0")){
            pdfView = findViewById(R.id.pdfviewer);
            pdfView.fromAsset("trim1_plan1.pdf").load();
        }
        if(getIntent().getStringExtra("imgposicion").equals("1") && getIntent().getStringExtra("trimestrePosicion").equals("0")){
            pdfView = findViewById(R.id.pdfviewer);
            pdfView.fromAsset("trim1_plan2.pdf").load();
        }

        /**********************PLAN DE DIETA TRIMESTRE 2***********************/
        if(getIntent().getStringExtra("imgposicion").equals("0") && getIntent().getStringExtra("trimestrePosicion").equals("1")){
            pdfView = findViewById(R.id.pdfviewer);
            pdfView.fromAsset("trim2_plan1.pdf").load();
        }
        if(getIntent().getStringExtra("imgposicion").equals("1") && getIntent().getStringExtra("trimestrePosicion").equals("1")){
            pdfView = findViewById(R.id.pdfviewer);
            pdfView.fromAsset("trim2_plan2.pdf").load();
        }

        /**********************PLAN DE DIETA TRIMESTRE 3***********************/

        if(getIntent().getStringExtra("imgposicion").equals("0") && getIntent().getStringExtra("trimestrePosicion").equals("2")){
            pdfView = findViewById(R.id.pdfviewer);
            pdfView.fromAsset("trim3_plan1.pdf").load();
        }
        if(getIntent().getStringExtra("imgposicion").equals("1") && getIntent().getStringExtra("trimestrePosicion").equals("2")){
            pdfView = findViewById(R.id.pdfviewer);
            pdfView.fromAsset("trim3_plan2.pdf").load();
        }
    }
}
