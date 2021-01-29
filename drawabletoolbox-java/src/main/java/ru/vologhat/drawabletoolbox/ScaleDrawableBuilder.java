package ru.vologhat.drawabletoolbox;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.view.Gravity;

class ScaleDrawableBuilder extends DrawableWrapperBuilder<ScaleDrawableBuilder> {
    private int level = 1000;
    private int scaleGravity = Gravity.CENTER;
    private float scaleWidth = 0f;
    private float scaleHeight = 0f;

    public ScaleDrawableBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public ScaleDrawableBuilder setScaleGravity(int scaleGravity) {
        this.scaleGravity = scaleGravity;
        return this;
    }

    public ScaleDrawableBuilder setScaleWidth(float scaleWidth) {
        this.scaleWidth = scaleWidth;
        return this;
    }

    public ScaleDrawableBuilder setScaleHeight(float scaleHeight) {
        this.scaleHeight = scaleHeight;
        return this;
    }

    @Override
    Drawable build() {
        ScaleDrawable scaleDrawable = new ScaleDrawable(drawable, scaleGravity, scaleWidth, scaleHeight);
        scaleDrawable.setLevel(level);
        return scaleDrawable;
    }
}
