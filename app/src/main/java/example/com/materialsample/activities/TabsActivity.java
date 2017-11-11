package example.com.materialsample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.materialsample.R;
import example.com.materialsample.adapters.PagePagerAdapter;
import example.com.materialsample.adapters.StringAdapter;
import example.com.materialsample.fragments.PageFragment;

// TODO: ADD MOVE NEXT AND REMOVE
/**
 * https://www.androidhive.info/2015/09/android-material-design-working-with-tabs/
 * https://stackoverflow.com/questions/33749792/changing-tablayout-icons-on-left-top-right-or-bottom-in-com-android-supportde
 * https://gist.github.com/Runly/75657c18c9f3f3eb8a1c9b6ae0c431cd
 * <p>
 * ** https://stackoverflow.com/questions/40480675/android-tab-layout-wrap-tab-indicator-width-with-respect-to-tab-title/44026386#44026386
 */
public class TabsActivity extends AppCompatActivity {
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_TITLE = "title";
    public static final int TYPE_SAMPLE_LIST = 0;
    public static final int TYPE_FIXED = 1; //DEFAULT
    public static final int TYPE_SCROLLABLE = 2;
    public static final int TYPE_ICON_AND_TEXT = 3;
    public static final int TYPE_CUSTOM_ICON_AND_TEXT = 4;
    public static final int TYPE_CUSTOM_ICON_AND_TEXT_WITH_SELECTOR = 5;
    private static final int[] tabs_icons = new int[]{
            android.R.drawable.ic_btn_speak_now,
            android.R.drawable.ic_delete,
            android.R.drawable.ic_dialog_dialer,
            android.R.drawable.ic_lock_idle_low_battery,
    };
    private static final String[] tabs_titles = new String[]{
            "ONE",
            "TWO",
            "THREE",
            "FOUR",
            "FIVE",
            "SIX",
            "SEVEN",
            "EIGHT",
            "NINE",
            "TEN"
    };

    @Nullable
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @Nullable
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindArray(R.array.tabs_samples)
    String[] tabs_samples;

    @Nullable
    @BindView(R.id.next)
    View buttonNext;
    private String title;
    private PagePagerAdapter adapter;

    public static Intent getStartIntent(@NonNull Context from, int type, String title) {
        Intent intent = new Intent(from, TabsActivity.class);
        Bundle extras = new Bundle();
        extras.putInt(EXTRA_TYPE, type);
        extras.putString(EXTRA_TITLE, title);
        intent.putExtras(extras);

        return intent;
    }

    void onNextClicked() {
        final int currentItem = viewPager.getCurrentItem();
        if (currentItem != adapter.getCount() - 1 /*ONE_INT*/) {
            Log.e("HERMAN", "NEXT ");
            viewPager.setCurrentItem(currentItem + 1 /*ONE_INT*/);
        } else {
            // go to next activity
            Intent intent = TabsActivity.getStartIntent(TabsActivity.this, TabsActivity.TYPE_SAMPLE_LIST, "Tab types");
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey(EXTRA_TITLE)) {
            title = extras.getString(EXTRA_TITLE);
        }
        if (extras.containsKey(EXTRA_TYPE)) {
            final int type = getIntent().getExtras().getInt(EXTRA_TYPE);
            switch (type) {
                case TYPE_SAMPLE_LIST:
                    onCreateList();
                    break;
                default:
                    onCreateTabs(type);
                    break;
            }
        }
    }

    private void onCreateList() {
        setContentView(R.layout.activity_tabs_list);
        ButterKnife.bind(this);

        StringAdapter adapter = new StringAdapter(R.layout.item_sample, new StringAdapter.ListClickListener() {
            @Override
            public void onItemClicked(int position) {
                startActivity(getStartIntent(TabsActivity.this, position + 1, tabs_samples[position]));
            }
        });
        adapter.setItems(Arrays.asList(tabs_samples));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void onCreateTabs(final int type) {
        switch (type) {
            case TYPE_FIXED:
                setupFixedTabs();
                break;
            case TYPE_SCROLLABLE:
                setupScrollableTabs();
                wrapTabIndicatorToTitle(tabLayout, 20, 20);
                break;
            case TYPE_ICON_AND_TEXT:
                setupIconAndTextTabs();
                break;
            case TYPE_CUSTOM_ICON_AND_TEXT:
                setupCustomIconAndTextTabs(R.layout.custom_tab, R.id.tab_textview);
                wrapTabIndicatorToTitle(tabLayout, 20, 20);
                break;
            case TYPE_CUSTOM_ICON_AND_TEXT_WITH_SELECTOR:
                setupCustomIconAndTextTabsWithSelector(R.layout.custom_tab, R.id.tab_textview, R.drawable.selector_tab_indicator);
                wrapTabIndicatorToTitle(tabLayout, 20, 20);
                break;
        }

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextClicked();
            }
        });
    }

    /**
     * Add to TabLayout xml or tabLayout.setTabGravity for:
     * <p>
     * Full Width tabs: app:tabGravity=”fill”
     * Center aligned tabs: app:tabGravity=”center”
     */
    private void setupFixedTabs() {
        setContentView(R.layout.activity_tabs_default);
        ButterKnife.bind(this);

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager(viewPager, 6);
        tabLayout.setupWithViewPager(viewPager);

        // tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    /**
     * Add to TabLayout xml or tabLayout.setTabMode for:
     * Scrollable tabs: app:tabMode=”scrollable”
     */
    private void setupScrollableTabs() {
        setContentView(R.layout.activity_tabs_default);
        ButterKnife.bind(this);

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager(viewPager, 10);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    /**
     * can combine with scrollable or alignment
     * return null in adapter.getPageTitle to make only icons tabs
     */
    private void setupIconAndTextTabs() {
        setContentView(R.layout.activity_tabs_default);
        ButterKnife.bind(this);

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager(viewPager, 5);
        tabLayout.setupWithViewPager(viewPager);

        final int tabCount = adapter.getCount();
        for (int i = 0, ti = 0; i < tabCount; i++) {
            tabLayout.getTabAt(i).setIcon(tabs_icons[ti]);
            if (++ti >= tabs_icons.length) {
                ti = 0;
            }
        }

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    /**
     * Remember to increase tab height in TabLayout:
     * android:layout_height="72dp"
     * <p>
     * change setCompoundDrawablesWithIntrinsicBounds to put text & icon positions
     *
     * @param customTabLayout
     * @param textViewId
     */
    private void setupCustomIconAndTextTabs(@LayoutRes final int customTabLayout, @IdRes final int textViewId) {
        setupIconAndTextTabs();

        final int tabCount = adapter.getCount();
        for (int i = 0, ti = 0, p = 0, tt = 0; i < tabCount; i++) {
            View tab = LayoutInflater.from(this).inflate(customTabLayout, null);
            TextView tabTextView = tab.findViewById(textViewId);
            tabTextView.setText(tabs_titles[tt]);

            // tabTextView.setCompoundDrawablesWithIntrinsicBounds(0, tabs_icons[ti], 0, 0); // icon over text

            switch (p) {
                case 0:
                    tabTextView.setCompoundDrawablesWithIntrinsicBounds(tabs_icons[ti], 0, 0, 0);
                    break;
                case 1:
                    tabTextView.setCompoundDrawablesWithIntrinsicBounds(0, tabs_icons[ti], 0, 0);
                    break;
                case 2:
                    tabTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, tabs_icons[ti], 0);
                    break;
                case 3:
                    tabTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, tabs_icons[ti]);
                    break;
            }

            tabLayout.getTabAt(i).setCustomView(tab);

            if (++p >= 4) {
                p = 0;
            }

            if (++ti >= tabs_icons.length) {
                ti = 0;
            }

            if (++tt >= tabs_titles.length) {
                tt = 0;
            }
        }
    }

    /**
     * Use a selector (enabled true/false) as icon, and use tabLayout.getTabAt(i).getCustomView().setEnabled(true/false);
     *
     * @param customTabLayout
     * @param textViewId
     * @param selector
     */
    private void setupCustomIconAndTextTabsWithSelector(@LayoutRes final int customTabLayout, @IdRes final int textViewId, @DrawableRes final int selector) {
        setupIconAndTextTabs();

        final int tabCount = adapter.getCount();
        for (int i = 0, ti = 0, p = 0, tt = 0; i < tabCount; i++) {
            View tab = LayoutInflater.from(this).inflate(customTabLayout, null);
            TextView tabTextView = tab.findViewById(textViewId);
            tabTextView.setText(tabs_titles[tt]);
            tabTextView.setEnabled(false);
            // tabTextView.setCompoundDrawablesWithIntrinsicBounds(0, tabs_icons[ti], 0, 0); // icon over text

            switch (p) {
                case 0:
                    tabTextView.setCompoundDrawablesWithIntrinsicBounds(selector, 0, 0, 0);
                    break;
                case 1:
                    tabTextView.setCompoundDrawablesWithIntrinsicBounds(0, selector, 0, 0);
                    break;
                case 2:
                    tabTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, selector, 0);
                    break;
                case 3:
                    tabTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, selector);
                    break;
            }

            tabLayout.getTabAt(i).setCustomView(tab);
            tabLayout.getTabAt(i).getCustomView().setEnabled(true);

            if (++p >= 4) {
                p = 0;
            }

            if (++ti >= tabs_icons.length) {
                ti = 0;
            }

            if (++tt >= tabs_titles.length) {
                tt = 0;
            }
        }
    }

    private void setupViewPager(@NonNull ViewPager viewPager, int pageCount) {
        adapter = new PagePagerAdapter(getSupportFragmentManager());
        String title;
        for (int i = 0, tt = 0; i < pageCount; i++) {
            title = tabs_titles[tt];
            adapter.addFragment(PageFragment.newInstance(title), title);

            if (++tt >= tabs_titles.length) {
                tt = 0;
            }
        }
        viewPager.setAdapter(adapter);
    }

    public void wrapTabIndicatorToTitle(TabLayout tabLayout, int externalMargin, int internalMargin) {
        View tabStrip = tabLayout.getChildAt(0);
        if (tabStrip instanceof ViewGroup) {
            ViewGroup tabStripGroup = (ViewGroup) tabStrip;
            int childCount = ((ViewGroup) tabStrip).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View tabView = tabStripGroup.getChildAt(i);
                //set minimum width to 0 for instead for small texts, indicator is not wrapped as expected
                tabView.setMinimumWidth(0);
                // set padding to 0 for wrapping indicator as title
                tabView.setPadding(0, tabView.getPaddingTop(), 0, tabView.getPaddingBottom());
                // setting custom margin between tabs
                if (tabView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
                    if (i == 0) {
                        // left
                        setMargin(layoutParams, externalMargin, internalMargin);
                    } else if (i == childCount - 1) {
                        // right
                        setMargin(layoutParams, internalMargin, externalMargin);
                    } else {
                        // internal
                        setMargin(layoutParams, internalMargin, internalMargin);
                    }
                }
            }

            tabLayout.requestLayout();
        }
    }

    private void setMargin(ViewGroup.MarginLayoutParams layoutParams, int start, int end) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.setMarginStart(start);
            layoutParams.setMarginEnd(end);
        } else {
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        }
    }
}
