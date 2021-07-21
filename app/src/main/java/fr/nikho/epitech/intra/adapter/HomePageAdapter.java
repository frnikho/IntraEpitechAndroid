package fr.nikho.epitech.intra.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import fr.nikho.epitech.intra.fragments.HomeFragment;
import fr.nikho.epitech.intra.fragments.CalendarFragment;
import fr.nikho.epitech.intra.fragments.NetlogFragment;
import fr.nikho.epitech.intra.fragments.UserFragment;

public class HomePageAdapter extends FragmentStateAdapter {

    public HomePageAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public HomePageAdapter(@NonNull @NotNull Fragment fragment) {
        super(fragment);
    }

    public HomePageAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CalendarFragment();
            case 2:
                return new NetlogFragment();
            case 3:
                return new UserFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
