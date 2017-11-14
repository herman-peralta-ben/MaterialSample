package example.com.materialsample.utils;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.View;

public class ViewUtils {

    public static Rect getViewRectInParent(@NonNull View view) {
        Rect viewRect = new Rect();
        view.getGlobalVisibleRect(viewRect);
        return viewRect;
    }

    // https://stackoverflow.com/questions/3619693/getting-views-coordinates-relative-to-the-root-layout
    public static Point getViewPositionInParent(@NonNull View view) {
        /*Rect offsetViewBounds = new Rect();
        view.getDrawingRect(offsetViewBounds);
        ((ViewGroup) view.getParent()).offsetDescendantRectToMyCoords(view, offsetViewBounds);
        return new Point(offsetViewBounds.left, offsetViewBounds.top);*/

        Rect viewRect = getViewRectInParent(view);
        return new Point(viewRect.left, viewRect.top);
    }

    public static Point getViewCenterInParent(@NonNull View view) {
        Rect r = getViewRectInParent(view);
        return new Point(r.centerX(), r.centerY());
    }

    public static void translateViewCenterToViewCenter(@NonNull View fromView, @NonNull View toView, final long duration) {
        Point to = getViewCenterInParent(toView);
        translateViewCenterToPoint(fromView, to, duration);
    }

    public static void translateViewCenterToPoint(@NonNull final View fromView, @NonNull Point to, final long duration) {
        Point from = ViewUtils.getViewCenterInParent(fromView);
        fromView.animate()
                .translationXBy(to.x - from.x)
                .translationYBy(to.y - from.y)
                .setDuration(duration)
                .start();
    }
}
