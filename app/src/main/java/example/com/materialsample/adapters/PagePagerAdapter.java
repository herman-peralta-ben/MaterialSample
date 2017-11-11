package example.com.materialsample.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * https://medium.com/inloop/adventures-with-fragmentstatepageradapter-4f56a643f8e0
 */
public class PagePagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public PagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof Fragment && fragmentList.contains(object)) {
            return fragmentList.indexOf(object);
        }
        return FragmentStatePagerAdapter.POSITION_NONE;
    }

    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    public void removeItem(int position) {
        fragmentList.remove(position);
        fragmentTitleList.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(@NonNull Fragment fragment, @Nullable String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }
}
