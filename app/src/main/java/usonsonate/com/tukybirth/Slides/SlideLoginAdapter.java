package usonsonate.com.tukybirth.Slides;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
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
    Dialog myDialog;

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
            "¿Recuerdas cuando inicio tu último periodo?",
            "¿Cuál es tu fecha de cumpleaños?"
    };

    public String[] desc_slide = {
            "La duración normal del periodo dura entre 2 y 7 días, por lo tanto si tu periodo no esta comprendido entre ese rango es un buen momento para visitar al médico.",
            "La experiencia del sindrome premenstrual es diferente entre personas, sin embargo muchas experimentan algún tipo de PMS en algún grado. Los síntomas del síndrome premenstrual pueden ocurrir en cualquier momento después de la ovulación y terminar con el inicio del ciclo.",
            "Un ciclo menstrual regular puede durar alrededor de 25 a 35 días, y comienza el primer día de la menstruación. Ciclos mayores que 33 días o menores que 25 pueden indicar problemas hormonales. Sin embargo, es normal tener ciclos irregulares en los 2 primeros años después de la menarquía, que es la primera menstruación de la mujer.",
            "El Período Menstrual es la fase en que ocurre sangrado y desprendimiento de la pared externa del útero, marcando el inicio de un nuevo ciclo menstrual y el fin del ciclo anterior. En general, la menstruación ocurre 1 vez al mes y dura de 3 a 8 días.",
            "Conocer la fecha de nacimiento ayuda a obtener calculos más precisos del ciclo menstrual."
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
        myDialog = new Dialog(context);

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
                    String date = year  + "-" + month + "-" + day;

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

        //region Image_evet
        slideimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtclose, txbnumero, txbInformacion, txbSemana;
                ImageView imagenPopUp;
                //inicializamos las variables
                myDialog.setContentView(R.layout.custompopup);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                txbnumero = myDialog.findViewById(R.id.txtNumeroSelected);
                txbInformacion = myDialog.findViewById(R.id.txtinformacion);
                txbSemana = myDialog.findViewById(R.id.txbPopSemana);
                imagenPopUp = myDialog.findViewById(R.id.ImagePopup);
                imagenPopUp.setScaleType(ImageView.ScaleType.FIT_XY);

                //establecemos los valores del pop up
                txbnumero.setText(slide_headings[position]);
                imagenPopUp.setImageResource(slide_images[position]);
                imagenPopUp.setScaleType(ImageView.ScaleType.FIT_CENTER);
                txbInformacion.setText(desc_slide[position]);
                txbSemana.setText("");
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();

                //addItem(currentPosition, infoData);
            }
        });

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
