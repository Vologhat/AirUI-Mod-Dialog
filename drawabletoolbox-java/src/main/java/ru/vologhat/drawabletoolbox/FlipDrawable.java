package ru.vologhat.drawabletoolbox;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class FlipDrawable extends Drawable {
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;

    private Drawable drawable;
    private int orientation = ORIENTATION_HORIZONTAL;

    public FlipDrawable(Drawable drawable, int orientation) {
        this.drawable = drawable;
        this.orientation = orientation;
    }

    @Override
    public void draw(Canvas canvas) {
        int saveCount = canvas.save();
        if (orientation == ORIENTATION_VERTICAL) {
            canvas.scale(1f, -1f, (float)canvas.getWidth() / 2, (float) canvas.getHeight() / 2);
        } else {
            canvas.scale(-1f, 1f, (float)canvas.getWidth() / 2, (float) canvas.getHeight() / 2);
        }
        drawable.setBounds(new Rect(0, 0, canvas.getWidth(), canvas.getHeight()));
        drawable.draw(canvas);
        canvas.restoreToCount(saveCount);
    }

    @Override
    public boolean onLevelChange(int level) {
        drawable.setLevel(level);
        drawable.invalidateSelf();
        return true;
    }

    @Override
    public int getIntrinsicWidth() {
        return drawable.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return drawable.getIntrinsicHeight();
    }

    @Override
    public void setAlpha(int alpha) {
        drawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        drawable.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return drawable.getOpacity();
    }
}
