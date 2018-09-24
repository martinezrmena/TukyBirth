package usonsonate.com.tukybirth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

public class Fragmento01 extends Fragment {
    private CardView Procesar;
    private View view;
    private Toolbar toolbar;
    Transition transition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragmento01, container, false);
        Procesar = view.findViewById(R.id.btnactivitycalendar);
        Procesar.setOnClickListener(new View.OnClickListener()
        {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v)
            {
                transition = new Explode();
                transition.setDuration(MainActivity.DURATION_TRANSITION);
                transition.setInterpolator(new DecelerateInterpolator());
                getActivity().getWindow().setExitTransition(transition);
                Context context = view.getContext();
                Intent detail = new Intent(context.getApplicationContext(), InformacionSemanas.class);
                context.startActivity(detail,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });

        return view;
    }


}
