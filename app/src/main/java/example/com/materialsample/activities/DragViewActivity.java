package example.com.materialsample.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.materialsample.R;
import example.com.materialsample.listeners.DragAndDropWindowTouchListener;
import example.com.materialsample.utils.Constants;
import example.com.materialsample.utils.ViewUtils;

// http://www.singhajit.com/android-draggable-view/
public class DragViewActivity extends AppCompatActivity {

    @BindView(R.id.draggable_view) View draggableView;
    @BindView(R.id.drop_area_0) View dropArea0;
    @BindView(R.id.drop_area_1) View dropArea1;
    @BindView(R.id.drop_area_2) View dropArea2;
    @BindView(R.id.drop_area_3) View dropArea3;
    @BindView(R.id.drop_area_4) View dropArea4;
    @BindView(R.id.drop_area_5) View dropArea5;

    List<View> viewWindows;

    ImageDragAndDropTouchListener imageDragAndDropTouchListener;

    public static Intent getStartIntent(@NonNull Context from) {
        Intent intent = new Intent(from, DragViewActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        ButterKnife.bind(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        configDragAndDropListener();
    }

    private void configDragAndDropListener() {
        viewWindows = new ArrayList<>();
        viewWindows.add(dropArea0);
        viewWindows.add(dropArea1);
        viewWindows.add(dropArea2);
        viewWindows.add(dropArea3);
        viewWindows.add(dropArea4);
        viewWindows.add(dropArea5);

        List<Rect> windowRects = new ArrayList<>();

        int i = 0;
        for (View window : viewWindows) {
            windowRects.add(ViewUtils.getViewRectInParent(window));
            Point c = ViewUtils.getViewCenterInParent(viewWindows.get(i));
            Log.e("HERMAN", String.format("new WINDOW %d: l %d, t %d, r %d, b %d, center %d, %d, center2 %d, %d", i, windowRects.get(i).left, windowRects.get(i).top, windowRects.get(i).right, windowRects.get(i).bottom, windowRects.get(i).centerX(), windowRects.get(i).centerY(), c.x, c.y));
            i++;
        }

        imageDragAndDropTouchListener = new ImageDragAndDropTouchListener(windowRects);
        draggableView.setOnTouchListener(imageDragAndDropTouchListener);
    }

    public class ImageDragAndDropTouchListener extends DragAndDropWindowTouchListener {

        public ImageDragAndDropTouchListener(@NonNull List<Rect> dropWindows) {
            super(dropWindows);
        }

        @Override
        protected void onDropInWindow(int windowIndex) {
            if (windowIndex != Constants.NONE) {
                Toast.makeText(DragViewActivity.this, "Drop on " + windowIndex, Toast.LENGTH_SHORT).show();
                ViewUtils.translateViewCenterToViewCenter(draggableView, viewWindows.get(windowIndex), 500);
            }
        }

        @Override
        protected void onDragStarted(@NonNull View view) {

        }
    }
}
