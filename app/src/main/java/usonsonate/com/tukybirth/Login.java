package usonsonate.com.tukybirth;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Login extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        collapsingToolbarLayout = findViewById(R.id.collapsedToolBar);

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
    }
}
