package example.com.materialsample.listeners;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import example.com.materialsample.utils.Constants;

public abstract class DragAndDropWindowTouchListener extends DragAndDropTouchListener {
    private List<Rect> dropWindows;

    /**
     * Drop Windows relative to parent
     *
     * @param dropWindows - ViewUtils.getViewRectInParent()
     */
    public DragAndDropWindowTouchListener(@NonNull List<Rect> dropWindows) {
        super(true);
        this.dropWindows = dropWindows;
    }

    @Override
    protected void onDrop(@NonNull View view, @NonNull Point centerInParentCoords) {
        int i = 0;
        for (Rect dropWindow : dropWindows) {
            if (dropWindow.contains(centerInParentCoords.x, centerInParentCoords.y)) {
                onDropInWindow(i);
                break;
            }
            i++;
        }
        onDropInWindow(Constants.NONE);
    }

    protected abstract void onDropInWindow(final int windowIndex);
}
