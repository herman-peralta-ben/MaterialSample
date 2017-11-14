package example.com.materialsample.listeners;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import example.com.materialsample.utils.ViewUtils;

// http://www.singhajit.com/android-draggable-view/
public abstract class DragAndDropTouchListener implements View.OnTouchListener {
    protected final boolean enableBounds;
    private float dX;
    private float dY;

    public DragAndDropTouchListener(final boolean enableBounds) {
        this.enableBounds = enableBounds;
    }

    @Override
    public boolean onTouch(@NonNull View view, MotionEvent event) {
        final View parent = (View) view.getParent();
        float vWidth = view.getWidth();
        float vHeight = view.getHeight();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                onDragStarted(view);
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getRawX() + dX;
                float y = event.getRawY() + dY;

                if (enableBounds) {
                    if (x < 0) {
                        x = 0;
                    } else if (x > parent.getRight() - vWidth) {
                        x = parent.getRight() - vWidth;
                    }

                    if (y < 0) {
                        y = 0;
                    } else if (y > parent.getBottom() - vHeight) {
                        y = parent.getBottom() - vHeight;
                    }
                }

                view.setX(x);
                view.setY(y);
                break;
            case MotionEvent.ACTION_UP:
                Point centerInParentCoords = ViewUtils.getViewPositionInParent(view);
                centerInParentCoords.offset(view.getWidth() / 2, view.getHeight() / 2);
                onDrop(view, centerInParentCoords);
                break;
            default:
                return false;
        }

        return true;
    }

    protected abstract void onDragStarted(@NonNull View view);

    protected abstract void onDrop(@NonNull View view, @NonNull Point centerInParentCoords);
}
