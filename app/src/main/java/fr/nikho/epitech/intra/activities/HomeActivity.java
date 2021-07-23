package fr.nikho.epitech.intra.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomappbar.BottomAppBar;

import de.hdodenhof.circleimageview.CircleImageView;
import fr.nikho.epitech.intra.R;
import fr.nikho.epitech.intra.adapter.HomePageAdapter;
import fr.nikho.epitech.intra.data.User;
import fr.nikho.epitech.intra.services.ClientService;
import fr.nikho.epitech.intra.services.UserService;

public class HomeActivity extends AppCompatActivity {

    private CircleImageView homeUserIcon;
    private User user;
    private ViewPager2 viewPager;

    private BottomAppBar bottomAppBar;

    HomePageAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_IntraEpitechAndroid_DIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        Intent intent = getIntent();
        if (intent == null || intent.getExtras() == null)
            return;
        String pagerName = intent.getStringExtra("viewpager");
        switch (pagerName) {
            case "user":
                viewPager.setCurrentItem(3);
                break;
            case "scheduler":
                viewPager.setCurrentItem(1);
            default:
                viewPager.setCurrentItem(0);
                break;
        }
    }

    private void init() {
        this.user = UserService.getUser(this);
        homeUserIcon = findViewById(R.id.home_user_icon);
        homeUserIcon.setOnClickListener(this::showUserMenu);
        homeUserIcon.setOnLongClickListener(this::showUserMenuDialog);

        String imageLink = ClientService.getLoginLink(this);
        imageLink = imageLink.substring(0, imageLink.length() - 1) + user.getPicture();
        Glide.with(this).load(imageLink).into(homeUserIcon);

        viewPager = findViewById(R.id.home_viewpager);
        bottomAppBar = findViewById(R.id.bottom_app_bar);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new HomePageAdapter(fm, getLifecycle());
        viewPager.setAdapter(adapter);

        bottomAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_app_home:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.home_bottom_app_calendar:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.home_bottom_app_netlog:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.home_bottom_app_qrcode:
                    startActivity(new Intent(getApplicationContext(), QRActivity.class));
                    break;
            }
            return true;
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

            }
        });

    }

    private boolean showUserMenuDialog(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this::onUserMenuItemClick);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.home_picture_menu, popupMenu.getMenu());
        popupMenu.show();
        return true;
    }

    private void showUserMenu(View v) {
        if (viewPager.getCurrentItem() == 3) {
           showUserMenuDialog(v);
        }
        viewPager.setCurrentItem(3);
    }

    private boolean onUserMenuItemClick(MenuItem menu) {
        if (menu.getItemId() == R.id.menu_home_picture_user_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return false;
    }
}
