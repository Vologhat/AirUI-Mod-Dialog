package ru.vologhat.drawabletoolbox;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.StateSet;

public class RippleDrawableBuilder extends DrawableWrapperBuilder<RippleDrawableBuilder> {
    private int color = Constants.DEFAULT_COLOR;
    private ColorStateList colorStateList = null;
    private int radius = -1;

    public RippleDrawableBuilder setColor(int color) {
        this.color = color;
        return this;
    }

    public RippleDrawableBuilder setColorStateList(ColorStateList colorStateList) {
        this.colorStateList = colorStateList;
        return this;
    }

    public RippleDrawableBuilder setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    @Override
    Drawable build() {
        Drawable drawable = this.drawable;
        if (drawable != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ColorStateList colorStateList;
            if (this.colorStateList != null) {
                colorStateList = this.colorStateList;
            } else {
                colorStateList = new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{color});
            }
            Drawable mask;
            if (drawable instanceof DrawableContainer) {
                mask = drawable.getCurrent();
            } else {
                mask = drawable;
            }
            if (mask instanceof ShapeDrawable) {
                Drawable.ConstantState state = mask.getConstantState();
                if (state != null) {
                    ShapeDrawable temp = (ShapeDrawable) state.newDrawable().mutate();
                    temp.getPaint().setColor(Color.BLACK);
                    mask = temp;
                }
            } else if (mask instanceof GradientDrawable) {
                Drawable.ConstantState state = mask.getConstantState();
                if (state != null) {
                    GradientDrawable temp = (GradientDrawable) state.newDrawable().mutate();
                    temp.setColor(Color.BLACK);
                    mask = temp;
                }
            } else {
                mask = new ColorDrawable(Color.BLACK);
            }
            RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, drawable, mask);
            Compatible.setRadius(rippleDrawable, radius);
            rippleDrawable.invalidateSelf();
            drawable = rippleDrawable;
        }
        return drawable;
    }
}
