package usonsonate.com.tukybirth;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    private Animation anim_abajo, anim_arriba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        anim_abajo = AnimationUtils.loadAnimation(this, R.anim.a_abajo);
        anim_arriba = AnimationUtils.loadAnimation(this, R.anim.a_arriba);

        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#1a1b29"))
                .withAfterLogoText("Tuky Birth")
                .withLogo(R.drawable.stork);

        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setAnimation(anim_arriba);
        config.getLogo().setAnimation(anim_abajo);


        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
