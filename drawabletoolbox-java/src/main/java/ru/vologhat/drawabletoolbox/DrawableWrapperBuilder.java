package ru.vologhat.drawabletoolbox;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

abstract class DrawableWrapperBuilder<T extends DrawableWrapperBuilder<T>> {
    Drawable drawable = null;

    @SuppressLint("UNCHECKED_CAST")
    public T setDrawable(Drawable drawable) {
        this.drawable = drawable;
        return (T) this;
    }

    abstract Drawable build();
}
