package com.example.meownavigation;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    RelativeLayout main_layout;
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_layout = findViewById(R.id.main_layout);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        container = findViewById(R.id.container);

        // Dastlabki fragment sifatida HomeFragment ni yuklash
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            main_layout.setBackgroundColor(Color.parseColor("#009688"));
            bottomNavigation.show(2, true);  // HomeFragment uchun ikkinchi elementni ko'rsatish
        }

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.settings));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.person));

        meowNavigation();
    }

    private void meowNavigation() {
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        loadFragment(new SettingsFragment());
                        break;
                    case 2:
                        loadFragment(new HomeFragment());
                        break;
                    case 3:
                        loadFragment(new ProfileFragment());
                        break;
                }
                return null;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        // Fragmentni containerga yuklash
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void changeMainLayoutColor(int color) {
        main_layout.setBackgroundColor(color);
    }
}
