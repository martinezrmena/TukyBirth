package usonsonate.com.tukybirth;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import usonsonate.com.tukybirth.Slides.SlideLoginAdapter;

public class SlideLogin extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SlideLoginAdapter slideAdapter;
    private TextView[] mDtos;
    private Button btnAnterior, btnSiguiente;
    private int mCurrentPage;
    private RelativeLayout maincontainer;


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

        mSlideViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);

                if(btnSiguiente.getText().equals("Terminar")){
                    Toast.makeText(SlideLogin.this, "Duracion periodo: " + slideAdapter.getPERIODO_DURACION() +
                            " Duracion PMS: " + slideAdapter.getDURACION_PMS() + " Duracion Ciclo: " + slideAdapter.getDURACION_CICLO()+
                            " Ultimo periodo: "+ slideAdapter.getULTIMO_PERIODO() + " Cumpleaños: " + slideAdapter.getCUMPLEAÑOS(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

    }

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

}
