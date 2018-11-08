package usonsonate.com.tukybirth.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import java.util.Timer;
import java.util.TimerTask;

import usonsonate.com.tukybirth.EncounterHistoryActivity;
import usonsonate.com.tukybirth.InformacionSemanas;
import usonsonate.com.tukybirth.MainActivity;
import usonsonate.com.tukybirth.R;
import usonsonate.com.tukybirth.Threads.ViewPagerCaurosel;

public class Fragmento01 extends Fragment {
    private CardView Calendario, History;
    private View view;
    private Toolbar toolbar;
    Transition transition;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ViewPager viewPager;
    Timer timer;
    ViewPagerCaurosel viewPagerCaurosel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragmento01, container, false);

        //transicion inversa a //
        //CREAMOS LA TRANSICION
        Fade fadeIn = new Fade(Fade.IN);
        fadeIn.setDuration(MainActivity.DURATION_TRANSITION);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        //ESTABLECEMOS LA TRANSICION DE REGRESO
        getActivity().getWindow().setReenterTransition(fadeIn);
        getActivity().getWindow().setAllowEnterTransitionOverlap(false);

        //INICIALIZAMOS LAS VARIABLES
        collapsingToolbarLayout = view.findViewById(R.id.collapsingtoolbar);
        Calendario = view.findViewById(R.id.btnactivitycalendar);
        History = view.findViewById(R.id.btnactivityHistory);
        Calendario.setOnClickListener(new View.OnClickListener()
        {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v)
            {
                transition = new Fade(Fade.OUT);
                transition.setDuration(MainActivity.DURATION_TRANSITION);
                transition.setInterpolator(new DecelerateInterpolator());
                getActivity().getWindow().setExitTransition(transition);
                Context context = view.getContext();

                Intent detail = new Intent(context.getApplicationContext(), InformacionSemanas.class);
                context.startActivity(detail,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });

        History.setOnClickListener(new View.OnClickListener()
        {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v)
            {
                transition = new Fade(Fade.OUT);
                transition.setDuration(MainActivity.DURATION_TRANSITION);
                transition.setInterpolator(new DecelerateInterpolator());
                getActivity().getWindow().setExitTransition(transition);
                Context context = view.getContext();

                Intent detail = new Intent(context.getApplicationContext(), EncounterHistoryActivity.class);
                context.startActivity(detail,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });



        //ESTABLECEMOS EL CAROUSEL PARA SU MOVIMIENTO AUTOMATICO
        viewPager = view.findViewById(R.id.viewPager);

        viewPagerCaurosel = new ViewPagerCaurosel(getContext());

        viewPager.setAdapter(viewPagerCaurosel);

        return view;
    }


    public class MyTimerTask extends TimerTask {
        @Override
        public void run(){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Integer value = viewPager.getCurrentItem();

                    if (value == 39){
                        value = 0;
                    }else {
                        value++;
                    }

                    viewPager.setCurrentItem(value);

                }
            });
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000,4000);
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }
}
