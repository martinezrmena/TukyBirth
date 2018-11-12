package usonsonate.com.tukybirth;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import usonsonate.com.tukybirth.SQLite.DB;
import usonsonate.com.tukybirth.SQLite.Personas;
import usonsonate.com.tukybirth.Slides.SlideLoginAdapter;

public class SlideLogin extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SlideLoginAdapter slideAdapter;
    private TextView[] mDtos;
    private Button btnAnterior, btnSiguiente;
    private int mCurrentPage;
    private RelativeLayout maincontainer;
    private String Name;
    private String Password;
    private DB db;
    private ArrayList<Personas> lstPersonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_login);

        mSlideViewPager = findViewById(R.id.SlideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
        btnAnterior = findViewById(R.id.btnPrevio);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        slideAdapter = new SlideLoginAdapter(this);
        maincontainer = findViewById(R.id.MainContainer);

        //inicializando lista y db
        db = new DB(SlideLogin.this);

        //Recibiendo parametros de actividad Login
        Name = getIntent().getExtras().getString("USERNAME");
        Password = getIntent().getExtras().getString("PASSWORD");


        //region Adapter
        mSlideViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnSiguiente.getText().equals("Terminar")){
                    String periodo_duracion = String.valueOf(slideAdapter.getPERIODO_DURACION());
                    String duracion_pms = String.valueOf(slideAdapter.getDURACION_PMS());
                    String duracion_ciclo = String.valueOf(slideAdapter.getDURACION_CICLO());
                    String ultimo_periodo = String.valueOf(slideAdapter.getULTIMO_PERIODO());
                    String cumpleaños = String.valueOf(slideAdapter.getCUMPLEAÑOS());
                    Date fechaactual = new Date();
                    String actualdate = convertirDateToString(fechaactual);
                    final Personas persona = new Personas("", Name, Password, periodo_duracion, duracion_pms,
                            duracion_ciclo, ultimo_periodo, cumpleaños, actualdate);

                    if(!periodo_duracion.isEmpty() && !duracion_pms.isEmpty() && !duracion_ciclo.isEmpty()
                            && !ultimo_periodo.isEmpty() && !cumpleaños.isEmpty()){

                        AlertDialog.Builder builder = new AlertDialog.Builder(SlideLogin.this);
                        builder.setIcon(R.drawable.pregnant).
                                setTitle("Atención").setMessage("¿Está segura de proceder con los datos ingresados?").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                db.guardar_O_ActualizarPersonas(persona);
                                Toast.makeText(SlideLogin.this, "El usuario fue insertado exitosamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SlideLogin.this, CalendarLogin.class);
                                intent.putExtra("PERSONA", persona);
                                startActivity(intent);
                                finish();


                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                                Toast.makeText(SlideLogin.this, "La acción fue cancelada.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }else{
                        Toast.makeText(SlideLogin.this, "Revise los parametros ingresados.", Toast.LENGTH_SHORT).show();
                    }


                }

                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

        //endregion

    }

    //region AdapterEvents
    public void addDotsIndicator(int position){
        mDtos = new TextView[5];
        mDotLayout.removeAllViews();

        for (int i = 0; i <  mDtos.length; i++){
            mDtos[i] = new TextView(this);
            mDtos[i].setText(Html.fromHtml("&#8226;"));
            mDtos[i].setTextSize(35);
            mDtos[i].setTextColor(getResources().getColor(R.color.colorTransparentwhite));

            mDotLayout.addView(mDtos[i]);

        }

        if(mDtos.length > 0){
            mDtos[position].setTextColor(getResources().getColor(R.color.white));
        }


    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);
            mCurrentPage = position;

            //region Botones_Movimiento
            if(position == 0){
                btnSiguiente.setEnabled(true);
                btnAnterior.setEnabled(false);
                btnAnterior.setVisibility(View.INVISIBLE);

                btnAnterior.setText("");
                btnSiguiente.setText("Siguiente");

            }else if(position == mDtos.length - 1 ){
                btnSiguiente.setEnabled(true);
                btnAnterior.setEnabled(true);
                btnAnterior.setVisibility(View.VISIBLE);

                btnAnterior.setText("Anterior");
                btnSiguiente.setText("Terminar");

            }else{
                btnSiguiente.setEnabled(true);
                btnAnterior.setEnabled(true);
                btnAnterior.setVisibility(View.VISIBLE);

                btnAnterior.setText("Anterior");
                btnSiguiente.setText("Siguiente");
            }
            //endregion

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onSaveInstanceState(Bundle guardardatos) {
        super.onSaveInstanceState(guardardatos);
        //Guardamos la informacion
        guardardatos.putInt("PERIODO_DURACION", slideAdapter.getPERIODO_DURACION());
        guardardatos.putInt("DURACION_PMS", slideAdapter.getDURACION_PMS());
        guardardatos.putInt("DURACION_CICLO", slideAdapter.getDURACION_CICLO());
        guardardatos.putString("ULTIMO_PERIODO", slideAdapter.getULTIMO_PERIODO());
        guardardatos.putString("CUMPLEAÑOS", slideAdapter.getCUMPLEAÑOS());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Restauramos los valores en cada una de las varibles
        slideAdapter.setPERIODO_DURACION(savedInstanceState.getInt("PERIODO_DURACION"));
        slideAdapter.setDURACION_PMS(savedInstanceState.getInt("DURACION_PMS"));
        slideAdapter.setDURACION_CICLO(savedInstanceState.getInt("DURACION_CICLO"));
        slideAdapter.setULTIMO_PERIODO(savedInstanceState.getString("ULTIMO_PERIODO"));
        slideAdapter.setCUMPLEAÑOS(savedInstanceState.getString("CUMPLEAÑOS"));

    }
    //endregion

    private String convertirDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaComoCadena = sdf.format(date);

        return fechaComoCadena;
    }

}