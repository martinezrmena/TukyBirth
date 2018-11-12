package usonsonate.com.tukybirth.Slides;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

import usonsonate.com.tukybirth.R;

public class SlideLoginAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    private int PERIODO_DURACION = 4;
    private int DURACION_PMS = 4;
    private int DURACION_CICLO = 28;
    private String ULTIMO_PERIODO = "";
    private String CUMPLEAÑOS = "";

    public SlideLoginAdapter(Context context) {
        this.context = context;
    }

    //region Arrays
    public int[] slide_images = {
            R.drawable.period_blood,
            R.drawable.ic_pms,
            R.drawable.calendar_duracion,
            R.drawable.period_blood,
            R.drawable.birthday
    };

    public String[] slide_headings= {
            "PERIODO",
            "PMS",
            "DURACIÓN DEL CICLO",
            "ÚLTIMO PERIODO",
            "CUMPLEAÑOS"
    };

    public String[] slide_descs = {
            "¿Conoces cuánto dura tu periodo?",
            "¿Sufres el síndrome premenstrual?",
            "¿Cuánto dura tu ciclo normalmente?",
            "¿Recuerdas la fecha de tu último periodo?",
            "¿Cuál es tu fecha de cumpleaños?"
    };

    //endregion

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view  == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideimageview = (ImageView) view.findViewById(R.id.slide_image);
        TextView sliderHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);
        NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.NumberPicker);
        final TextView mDisplayDate = (TextView) view.findViewById(R.id.txbPickDate);
        final DatePickerDialog.OnDateSetListener mDateSetListener;

        slideimageview.setImageResource(slide_images[position]);
        sliderHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        //region Position
        if(position == 0 || position == 1){
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(10);

            if(position == 0){
                numberPicker.setValue(getPERIODO_DURACION());
            }

            if(position == 1){
                numberPicker.setValue(getDURACION_PMS());
            }

        }

        if(position == 2){
            numberPicker.setMinValue(10);
            numberPicker.setMaxValue(90);
            numberPicker.setValue(getDURACION_CICLO());
        }

        if (position == 3 ){
            if ( !getULTIMO_PERIODO().isEmpty()){
                mDisplayDate.setText(getULTIMO_PERIODO());
            }
            mDisplayDate.setVisibility(View.VISIBLE);
            numberPicker.setVisibility(View.INVISIBLE);

        }else if(position == 4){
            if(!getCUMPLEAÑOS().isEmpty()){
                mDisplayDate.setText(getCUMPLEAÑOS());
            }
            mDisplayDate.setVisibility(View.VISIBLE);
            numberPicker.setVisibility(View.INVISIBLE);
        }

        //endregion

        //region NumberPicker
        if(position <=2){
            numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                    if(position  == 0){
                        setPERIODO_DURACION(newVal);
                    }

                    if(position == 1){
                        setDURACION_PMS(newVal);
                    }

                    if(position == 2){
                        setDURACION_CICLO(newVal);
                    }
                }
            });
        }


        //endregion

        //region DatePicker
        if(position == 3 || position == 4){
            mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    String date = day  + "-" + month + "-" + year;

                    if (position == 3){
                        setULTIMO_PERIODO(date);
                    }else if(position == 4){
                        setCUMPLEAÑOS(date);
                    }

                    mDisplayDate.setText(date);
                }
            };

            mDisplayDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            context,
                            android.R.style.Theme_Material_Dialog,
                            mDateSetListener,
                            year,month,day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                    dialog.show();
                }
            });
        }

        //endregion

        container.addView(view);


        return view;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }

    //region GETTER_SETTER

    public int getPERIODO_DURACION() {
        return PERIODO_DURACION;
    }

    public void setPERIODO_DURACION(int PERIODO_DURACION) {
        this.PERIODO_DURACION = PERIODO_DURACION;
    }

    public int getDURACION_PMS() {
        return DURACION_PMS;
    }

    public void setDURACION_PMS(int DURACION_PMS) {
        this.DURACION_PMS = DURACION_PMS;
    }

    public int getDURACION_CICLO() {
        return DURACION_CICLO;
    }

    public void setDURACION_CICLO(int DURACION_CICLO) {
        this.DURACION_CICLO = DURACION_CICLO;
    }

    public String getULTIMO_PERIODO() {
        return ULTIMO_PERIODO;
    }

    public void setULTIMO_PERIODO(String ULTIMO_PERIODO) {
        this.ULTIMO_PERIODO = ULTIMO_PERIODO;
    }

    public String getCUMPLEAÑOS() {
        return CUMPLEAÑOS;
    }

    public void setCUMPLEAÑOS(String CUMPLEAÑOS) {
        this.CUMPLEAÑOS = CUMPLEAÑOS;
    }

    //endregion
}
