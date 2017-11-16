package example.com.materialsample.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

public class ImageUtils {
    private ImageUtils() {

    }

    public static Bitmap maskBitmap(@NonNull Context context,
                                     @DrawableRes final int imageDrawableRes,
                                     @DrawableRes final int maskDrawableRes) {
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), imageDrawableRes);
        Bitmap mask = BitmapFactory.decodeResource(context.getResources(), maskDrawableRes);

        return maskBitmap(image, mask);
    }

    // https://stackoverflow.com/questions/12614542/maskingcrop-image-in-frame
    public static Bitmap maskBitmap(@NonNull Bitmap image, @NonNull Bitmap mask) {
        Bitmap result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas mCanvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mCanvas.drawBitmap(image, 0, 0, null);
        mCanvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);

        return result;
    }
}
