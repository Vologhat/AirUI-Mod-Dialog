package ru.vologhat.drawabletoolbox;

import android.graphics.drawable.Drawable;

public class FlipDrawableBuilder extends DrawableWrapperBuilder<FlipDrawableBuilder> {
    private int orientation = FlipDrawable.ORIENTATION_HORIZONTAL;

    public FlipDrawableBuilder setOrientation(int orientation) {
        this.orientation = orientation;
        return this;
    }

    @Override
    Drawable build() {
        if (drawable != null) {
            return new FlipDrawable(drawable, orientation);
        }
        return drawable;
    }
}
