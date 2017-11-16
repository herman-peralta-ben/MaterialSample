package example.com.materialsample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.materialsample.R;
import example.com.materialsample.utils.ImageUtils;

public class ImageSampleActivity extends AppCompatActivity {
    @BindView(R.id.imageView)
    ImageView imageView;

    public static Intent getStartIntent(@NonNull Context from) {
        Intent intent = new Intent(from, ImageSampleActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ButterKnife.bind(this);

        imageView.setImageBitmap(ImageUtils.maskBitmap(this, R.drawable.bg_aurora, R.drawable.msk_jigsaw1));
    }
}
