package ru.vologhat.drawabletoolbox;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;

public class RotateDrawableBuilder extends DrawableWrapperBuilder<RotateDrawableBuilder> {
    private float pivotX = 0.5f;
    private float pivotY = 0.5f;
    private float fromDegrees = 0f;
    private float toDegrees = 360f;

    public RotateDrawableBuilder() {

    }

    public RotateDrawableBuilder setPivotX(float pivotX) {
        this.pivotX = pivotX;
        return this;
    }

    public RotateDrawableBuilder setPivotY(float pivotY) {
        this.pivotY = pivotY;
        return this;
    }

    public RotateDrawableBuilder setFromDegrees(float fromDegrees) {
        this.fromDegrees = fromDegrees;
        return this;
    }

    public RotateDrawableBuilder setToDegrees(float toDegrees) {
        this.toDegrees = toDegrees;
        return this;
    }

    @Override
    Drawable build() {
        RotateDrawable rotateDrawable = new RotateDrawable();
        if (drawable != null) {
            Compatible.setDrawable(rotateDrawable, drawable);
            Compatible.setPivotX(rotateDrawable, pivotX);
            Compatible.setPivotY(rotateDrawable, pivotY);
            Compatible.setFromDegrees(rotateDrawable, fromDegrees);
            Compatible.setToDegrees(rotateDrawable, toDegrees);
        }
        return rotateDrawable;
    }
}
