package usonsonate.com.tukybirth.Fragmentos;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import usonsonate.com.tukybirth.Principal_Ayuda;
import usonsonate.com.tukybirth.R;



public class FragmentoPrincipal extends Fragment {

    private ImageView imgLogo;
    private TextView lblFecha;
    private EditText txtFecha;
    private Calendar Calcular;
    private FloatingActionButton btnAyuda;
    private  Calendar cal = Calendar.getInstance();
    private Date date = cal.getTime();


    private Animation anim_rotar;

    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragmento_principal, container, false);

        this.txtFecha = view.findViewById(R.id.txtFechaN);
        this.lblFecha = view.findViewById(R.id.lblFechaNa);
        this.imgLogo = view.findViewById(R.id.imgLogo);
        this.btnAyuda = view.findViewById(R.id.btnYuda);

        anim_rotar = AnimationUtils.loadAnimation(getContext(), R.anim.rotar);
        imgLogo.startAnimation(anim_rotar);

        sMonthIni = C.get(Calendar.MONTH);
        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sYearIni = C.get(Calendar.YEAR);

        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                imgLogo.startAnimation(anim_rotar);
                if (!txtFecha.getText().toString().isEmpty()) return;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setIcon(R.mipmap.ic_launcher).
                        setTitle("Atencion").setMessage("La fecha de nacimiento del bebe es aproximado, no es exacta.\n" +
                        "Desea Calcularla?").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //getActivity().showDialog(DATE_ID);

                        imgLogo.startAnimation(anim_rotar);


                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        imgLogo.startAnimation(anim_rotar);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ayuda = new Intent(getContext(), Principal_Ayuda.class);
                startActivity(ayuda);
            }
        });

        return view;
    }
    private void colocar_fecha() {
        txtFecha.setText(mYearIni + "-" + (mMonthIni + 1) + "-" + mDayIni);
        Calcular = convertirACalendar(txtFecha.getText().toString());
        date = sumarMeses(Calcular.getTime(),-3,7,1);
        lblFecha.setText("El bebe nacera aproximadamente el :"+date.toString());
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni = year;
                    mMonthIni = monthOfYear;
                    mDayIni = dayOfMonth;
                    colocar_fecha();

                }

            };

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(getContext(), mDateSetListener, sYearIni, sMonthIni, sDayIni);
        }

        return null;
    }

    private Calendar convertirACalendar(String fecha){

        String[] fechArray = fecha.split("-");

        int anio = Integer.parseInt(fechArray[0]);

        int mes =  Integer.parseInt(fechArray[1]) - 1;

        int dia =  Integer.parseInt(fechArray[2]);

        Calendar c1 = new GregorianCalendar(anio, mes, dia);

        return c1;

    }

    private Date sumarMeses(Date fecha,int meses,int dias,int year){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(calendar.MONTH,meses);
        calendar.add(calendar.DAY_OF_MONTH,dias);
        calendar.add(calendar.YEAR,year);
        return calendar.getTime();
    }

}
