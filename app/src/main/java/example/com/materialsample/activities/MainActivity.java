package example.com.materialsample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.materialsample.R;
import example.com.materialsample.adapters.StringAdapter;

public class MainActivity extends AppCompatActivity implements StringAdapter.ListClickListener {

    @BindArray(R.array.samples_list)
    String[] samples;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    StringAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupList();
    }

    private void setupList() {
        adapter = new StringAdapter(R.layout.item_sample, this);
        adapter.setItems(Arrays.asList(samples));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = TabsActivity.getStartIntent(MainActivity.this, TabsActivity.TYPE_SAMPLE_LIST, "Tab types");
                break;
            case 1:
                intent = TextInputLayoutActivity.getStartIntent(MainActivity.this);
                break;
            case 2:
                intent = SelectionControlsActivity.getStartIntent(MainActivity.this);
                break;
            case 3:
                intent = PickersActivity.getStartIntent(MainActivity.this);
                break;
            case 4:
                intent = BottomSheetActivity.getStartIntent(MainActivity.this);
                break;
            case 5:
                intent = BottomNavigationActivity.getStartIntent(MainActivity.this);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
