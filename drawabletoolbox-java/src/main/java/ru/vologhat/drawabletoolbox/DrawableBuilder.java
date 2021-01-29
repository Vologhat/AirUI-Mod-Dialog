package ru.vologhat.drawabletoolbox;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.StateSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DrawableBuilder {
    private DrawableProperties properties = new DrawableProperties();
    private AtomicInteger order = new AtomicInteger(1);
    private TreeMap<Integer, Drawable> transformsMap = new TreeMap<>();
    private Drawable baseDrawable = null;

    public DrawableBuilder() {
        /*properties.useLevelForRing = true;
        properties.useGradient = true;
        properties.useCenterColor = true;
        properties.useRotate = true;
        rotateOrder = order.getAndIncrement();
        properties.useScale = true;
        scaleOrder = order.getAndIncrement();
        properties.useFlip = true;
        properties.useRipple = true;*/
    }

    public DrawableBuilder batch(DrawableProperties properties) {
        this.properties = properties.copy();
        return this;
    }

    public DrawableBuilder baseDrawable(Drawable drawable) {
        this.baseDrawable = drawable;
        return this;
    }

    // <shape>
    public DrawableBuilder shape(int shape) {
        this.properties.shape = shape;
        return this;
    }

    public DrawableBuilder rectangle() {
        return this.shape(GradientDrawable.RECTANGLE);
    }

    public DrawableBuilder oval() {
        return this.shape(GradientDrawable.OVAL);
    }

    public DrawableBuilder line() {
        return this.shape(GradientDrawable.LINE);
    }

    public DrawableBuilder ring() {
        return this.shape(GradientDrawable.RING);
    }

    public DrawableBuilder innerRadius(int innerRadius) {
        this.properties.innerRadius = innerRadius;
        return this;
    }

    public DrawableBuilder innerRadiusRatio(int innerRadiusRatio) {
        this.properties.innerRadiusRatio = innerRadiusRatio;
        return this;
    }

    public DrawableBuilder thickness(int thickness) {
        this.properties.thickness = thickness;
        return this;
    }

    public DrawableBuilder thicknessRatio(int thicknessRatio) {
        this.properties.thickness = thicknessRatio;
        return this;
    }

    public DrawableBuilder useLevelForRing(boolean use) {
        this.properties.useLevelForRing = use;
        return this;
    }

    // <corner>
    public DrawableBuilder cornerRadius(int cornerRadius) {
        this.properties.setCornerRadius(cornerRadius);
        return this;
    }

    public DrawableBuilder topLeftRadius(int topLeftRadius) {
        this.properties.topLeftRadius = topLeftRadius;
        return this;
    }

    public DrawableBuilder topRightRadius(int topRightRadius) {
        this.properties.topRightRadius = topRightRadius;
        return this;
    }

    public DrawableBuilder bottomRightRadius(int bottomRightRadius) {
        this.properties.bottomRightRadius = bottomRightRadius;
        return this;
    }

    public DrawableBuilder bottomLeftRadius(int bottomLeftRadius) {
        this.properties.bottomLeftRadius = bottomLeftRadius;
        return this;
    }

    public DrawableBuilder rounded() {
        return this.cornerRadius(Integer.MAX_VALUE);
    }

    public DrawableBuilder cornerRadii(int topLeftRadius, int topRightRadius, int bottomRightRadius, int bottomLeftRadius) {
        return this.topLeftRadius(topLeftRadius)
                .topRightRadius(topRightRadius)
                .bottomRightRadius(bottomRightRadius)
                .bottomLeftRadius(bottomLeftRadius);
    }

    // <gradient>
    public DrawableBuilder gradient(boolean useGradient) {
        this.properties.useGradient = useGradient;
        return this;
    }

    public DrawableBuilder gradientType(int type) {
        this.properties.type = type;
        return this;
    }

    public DrawableBuilder linearGradient() {
        this.gradientType(GradientDrawable.LINEAR_GRADIENT);
        return this;
    }

    public DrawableBuilder radialGradient() {
        this.gradientType(GradientDrawable.RADIAL_GRADIENT);
        return this;
    }

    public DrawableBuilder sweepGradient() {
        this.gradientType(GradientDrawable.SWEEP_GRADIENT);
        return this;
    }

    public DrawableBuilder angle(int angle) {
        this.properties.angle = angle;
        return this;
    }

    public DrawableBuilder centerX(float centerX) {
        this.properties.centerX = centerX;
        return this;
    }

    public DrawableBuilder centerY(float centerY) {
        this.properties.centerY = centerY;
        return this;
    }

    public DrawableBuilder center(float centerX, float centerY) {
        return this.centerX(centerX).centerY(centerY);
    }

    public DrawableBuilder useCenterColor(boolean useCenterColor) {
        this.properties.useCenterColor = useCenterColor;
        return this;
    }

    public DrawableBuilder startColor(int startColor) {
        this.properties.startColor = startColor;
        return this;
    }

    public DrawableBuilder centerColor(int centerColor) {
        this.properties.centerColor = centerColor;
        return this.useCenterColor(true);
    }

    public DrawableBuilder endColor(int endColor) {
        this.properties.endColor = endColor;
        return this;
    }

    public DrawableBuilder gradientColors(int startColor, int endColor, int centerColor) {
        return startColor(startColor)
                .endColor(endColor)
                .useCenterColor(centerColor != Color.TRANSPARENT)
                .centerColor(centerColor);
    }

    public DrawableBuilder gradientRadiusType(int gradientRadiusType) {
        this.properties.gradientRadiusType = gradientRadiusType;
        return this;
    }

    public DrawableBuilder gradientRadius(float gradientRadius) {
        this.properties.gradientRadius = gradientRadius;
        return this;
    }

    public DrawableBuilder gradientRadius(float radius, int type) {
       return this.gradientRadius(radius).gradientRadiusType(type);
    }

    public DrawableBuilder gradientRadiusInPixel(float radius) {
        return this.gradientRadius(radius).gradientRadiusType(DrawableProperties.RADIUS_TYPE_PIXELS);
    }

    public DrawableBuilder gradientRadiusInFraction(float radius) {
        return this.gradientRadius(radius).gradientRadiusType(DrawableProperties.RADIUS_TYPE_FRACTION);
    }

    public DrawableBuilder useLevelForGradient(boolean use) {
        this.properties.useLevelForGradient = use;
        return this;
    }

    public DrawableBuilder useLevelForGradient() {
        return  this.useLevelForGradient(true);
    }

    // <size>
    public DrawableBuilder width(int width) {
        this.properties.width = width;
        return this;
    }

    public DrawableBuilder height(int height) {
        this.properties.height = height;
        return this;
    }

    public DrawableBuilder size(int width, int height) {
        return this.width(width).height(height);
    }

    public DrawableBuilder size(int size) {
        return this.width(size).height(size);
    }

    // <solid>
    public DrawableBuilder solidColor(int solidColor) {
        this.properties.solidColor = solidColor;
        return this;
    }

    private int solidColorPressed = Color.TRANSPARENT;

    public DrawableBuilder solidColorPressed(int color) {
        this.solidColorPressed = color;
        return this;
    }

    private int solidColorPressedWhenRippleUnsupported = Color.TRANSPARENT;

    public DrawableBuilder solidColorPressedWhenRippleUnsupported(int color) {
        this.solidColorPressedWhenRippleUnsupported = color;
        return this;
    }

    private int solidColorDisabled = Color.TRANSPARENT;

    public DrawableBuilder solidColorDisabled(int color) {
        this.solidColorDisabled = color;
        return this;
    }

    private int solidColorSelected = Color.TRANSPARENT;

    public DrawableBuilder solidColorSelected(int color) {
        this.solidColorSelected = color;
        return this;
    }

    public DrawableBuilder solidColorStateList(ColorStateList colorStateList) {
        this.properties.solidColorStateList = colorStateList;
        return this;
    }

    // <stroke>
    public DrawableBuilder strokeWidth(int strokeWidth) {
        this.properties.strokeWidth = strokeWidth;
        return this;
    }

    public DrawableBuilder strokeColor(int strokeColor) {
        this.properties.strokeColor = strokeColor;
        return this;
    }

    private int strokeColorPressed = Color.TRANSPARENT;

    public DrawableBuilder strokeColorPressed(int color) {
        this.strokeColorPressed = color;
        return this;
    }

    private int strokeColorDisabled = Color.TRANSPARENT;

    public DrawableBuilder strokeColorDisabled(int color) {
        this.strokeColorDisabled = color;
        return this;
    }

    private int strokeColorSelected = Color.TRANSPARENT;

    public DrawableBuilder strokeColorSelected(int color) {
        this.strokeColorSelected = color;
        return this;
    }

    public DrawableBuilder strokeColorStateList(ColorStateList colorStateList) {
        this.properties.strokeColorStateList = colorStateList;
        return this;
    }

    public DrawableBuilder dashWidth(int dashWidth) {
        this.properties.dashWidth = dashWidth;
        return this;
    }

    public DrawableBuilder dashGap(int dashGap) {
        this.properties.dashGap = dashGap;
        return this;
    }

    public DrawableBuilder hairlineBordered() {
        return this.strokeWidth(1);
    }

    public DrawableBuilder shortDashed() {
        return this.dashWidth(12).dashGap(12);
    }

    public DrawableBuilder mediumDashed() {
        return this.dashWidth(24).dashGap(24);
    }

    public DrawableBuilder longDashed() {
        return this.dashWidth(36).dashGap(36);
    }

    public DrawableBuilder dashed() {
        return this.mediumDashed();
    }

    // <rotate>
    private int rotateOrder = 0;

    public DrawableBuilder rotate(boolean useRotate) {
        this.properties.useRotate = useRotate;
        this.rotateOrder = useRotate ? order.getAndIncrement() : 0;
        return this;
    }

    public DrawableBuilder pivotX(float pivotX) {
        this.properties.pivotX = pivotX;
        return this;
    }

    public DrawableBuilder pivotY(float pivotY) {
        this.properties.pivotY = pivotY;
        return this;
    }

    public DrawableBuilder pivot(float pivotX, float pivotY) {
        return this.pivotX(pivotX).pivotY(pivotY);
    }

    public DrawableBuilder fromDegrees(float degrees) {
        this.properties.fromDegrees = degrees;
        return this;
    }

    public DrawableBuilder toDegrees(float degrees) {
        this.properties.toDegrees = degrees;
        return this;
    }

    public DrawableBuilder degrees(float fromDegrees, float toDegrees) {
        return this.fromDegrees(fromDegrees).toDegrees(toDegrees);
    }

    public DrawableBuilder degrees(float degrees) {
        return this.fromDegrees(degrees).toDegrees(degrees);
    }

    public DrawableBuilder rotate(float fromDegrees, float toDegrees) {
        return this.rotate(true).fromDegrees(fromDegrees).toDegrees(toDegrees);
    }

    public DrawableBuilder rotate(float degrees) {
        return this.rotate(true).degrees(degrees);
    }

    // <scale>
    private int scaleOrder = 0;

    public DrawableBuilder scale(boolean useScale) {
        this.properties.useScale = useScale;
        this.scaleOrder = useScale ? order.getAndIncrement() : 0;
        return this;
    }

    public DrawableBuilder scaleLevel(int level) {
        this.properties.scaleLevel = level;
        return this;
    }

    public DrawableBuilder scaleGravity(int gravity) {
        this.properties.scaleGravity = gravity;
        return this;
    }

    public DrawableBuilder scaleWidth(float scale) {
        this.properties.scaleWidth = scale;
        return this;
    }

    public DrawableBuilder scaleHeight(float scale) {
        this.properties.scaleHeight = scale;
        return this;
    }

    public DrawableBuilder scale(float scale) {
        return this.scale(true).scaleWidth(scale).scaleHeight(scale);
    }

    public DrawableBuilder scale(float scaleWidth, float scaleHeight) {
        return this.scale(true).scaleWidth(scaleWidth).scaleHeight(scaleHeight);
    }

    // flip
    public DrawableBuilder flip(boolean useFlip) {
        this.properties.useFlip = useFlip;
        return this;
    }

    public DrawableBuilder orientation(int orientation) {
        this.properties.orientation = orientation;
        return this;
    }

    public DrawableBuilder flipVertical() {
        return flip(true).orientation(FlipDrawable.ORIENTATION_VERTICAL);
    }

    // <ripple>
    public DrawableBuilder ripple(boolean useRipple) {
        this.properties.useRipple = useRipple;
        return this;
    }

    public DrawableBuilder rippleColor(int color) {
        this.properties.rippleColor = color;
        return this;
    }

    public DrawableBuilder rippleColorStateList(ColorStateList colorStateList) {
        this.properties.rippleColorStateList = colorStateList;
        return this;
    }

    public DrawableBuilder rippleRadius(int radius) {
        this.properties.rippleRadius = radius;
        return this;
    }

    public Drawable build() {
        if (baseDrawable != null) {
            return wrap(baseDrawable);
        }

        Drawable drawable;

        // fall back when ripple is unavailable on devices with API < 21
        if (shouldFallbackRipple()) {
            if (solidColorPressedWhenRippleUnsupported != Color.TRANSPARENT) {
                solidColorPressed(solidColorPressedWhenRippleUnsupported);
            } else {
                solidColorPressed(properties.rippleColor);
            }
        }

        if (needStateListDrawable()) {
            drawable = new StateListDrawableBuilder()
                    .setPressed(buildPressedDrawable())
                    .setDisabled(buildDisabledDrawable())
                    .setSelected(buildSelectedDrawable())
                    .setNormal(buildNormalDrawable())
                    .build();
        } else {
            drawable = new GradientDrawable();
            setupGradientDrawable((GradientDrawable) drawable);
        }
        drawable = wrap(drawable);
        return drawable;
    }

    private ColorStateList getSolidColorStateList() {
        if (properties.solidColorStateList != null) {
            return properties.solidColorStateList;
        }

        List<int[]> states = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        if (solidColorPressed != Color.TRANSPARENT) {
            states.add(new int[]{android.R.attr.state_pressed});
            colors.add(solidColorPressed);
        }
        if (solidColorDisabled != Color.TRANSPARENT) {
            states.add(new int[]{android.R.attr.state_enabled});
            colors.add(solidColorDisabled);
        }
        if (solidColorSelected != Color.TRANSPARENT) {
            states.add(new int[]{android.R.attr.state_selected});
            colors.add(solidColorSelected);
        }
        states.add(StateSet.WILD_CARD);
        colors.add(properties.solidColor);

        return new ColorStateList(toArrayOfIntArrays(states), toIntArray(colors));
    }

    private ColorStateList getStrokeColorStateList() {
        if (properties.strokeColorStateList != null) {
            return properties.strokeColorStateList;
        }

        List<int[]> states = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        if (strokeColorPressed != Color.TRANSPARENT) {
            states.add(new int[]{android.R.attr.state_pressed});
            colors.add(strokeColorPressed);
        }
        if (strokeColorDisabled != Color.TRANSPARENT) {
            states.add(new int[]{android.R.attr.state_enabled});
            colors.add(strokeColorDisabled);
        }
        if (strokeColorSelected != Color.TRANSPARENT) {
            states.add(new int[]{android.R.attr.state_selected});
            colors.add(strokeColorSelected);
        }
        states.add(StateSet.WILD_CARD);
        colors.add(properties.strokeColor);

        return new ColorStateList((int[][]) states.toArray(), toIntArray(colors));
    }

    private Drawable buildPressedDrawable() {
        if (solidColorPressed == Color.TRANSPARENT && strokeColorPressed == Color.TRANSPARENT) return null;
        GradientDrawable pressed = new GradientDrawable();
        if (solidColorPressed != Color.TRANSPARENT) {
            pressed.setColor(solidColorPressed);
        }
        if (strokeColorPressed != Color.TRANSPARENT) {
            Compatible.setStrokeColor(pressed, strokeColorPressed);
        }
        return pressed;
    }

    private Drawable buildDisabledDrawable() {
        if (solidColorDisabled == Color.TRANSPARENT && strokeColorDisabled == Color.TRANSPARENT) return null;
        GradientDrawable disabled = new GradientDrawable();
        if (solidColorDisabled != Color.TRANSPARENT) {
            disabled.setColor(solidColorDisabled);
        }
        if (strokeColorDisabled != Color.TRANSPARENT) {
            Compatible.setStrokeColor(disabled, strokeColorDisabled);
        }
        return disabled;
    }

    private Drawable buildSelectedDrawable() {
        if (solidColorSelected == Color.TRANSPARENT && strokeColorSelected == Color.TRANSPARENT) return null;
        GradientDrawable selected = new GradientDrawable();
        if (solidColorSelected != Color.TRANSPARENT) {
            selected.setColor(solidColorSelected);
        }
        if (strokeColorSelected != Color.TRANSPARENT) {
            Compatible.setStrokeColor(selected, strokeColorSelected);
        }
        return selected;
    }

    private Drawable buildNormalDrawable() {
        GradientDrawable drawable = new GradientDrawable();
        setupGradientDrawable(drawable);
        return drawable;
    }

    private void setupGradientDrawable(GradientDrawable drawable) {
        drawable.setShape(properties.shape);
        if (properties.shape == GradientDrawable.RING) {
            Compatible.setInnerRadiusRatio(drawable, properties.innerRadiusRatio);
            Compatible.setInnerRadius(drawable, properties.innerRadius);
            Compatible.setThickness(drawable, properties.thickness);
            Compatible.setThicknessRatio(drawable, properties.thicknessRatio);
            Compatible.setUseLevelForShape(drawable, properties.useLevelForRing);
        }
        drawable.setCornerRadii(properties.getCornerRadii());
        if (properties.useGradient) {
            drawable.setGradientType(properties.type);
            Compatible.setGradientRadiusType(drawable, properties.gradientRadiusType);
            Compatible.setGradientRadius(drawable, properties.gradientRadius);
            drawable.setGradientCenter(properties.centerX, properties.centerY);
            Compatible.setOrientation(drawable, properties.getOrientation());
            Compatible.setColors(drawable, properties.getColors());
            drawable.setUseLevel(properties.useLevelForGradient);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable.setColor(getSolidColorStateList());
            } else {
                drawable.setColor(properties.solidColor);
            }
        }
        drawable.setSize(properties.width, properties.height);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.setStroke(properties.strokeWidth, getSolidColorStateList(), properties.dashWidth, properties.dashGap);
        } else {
            drawable.setStroke(properties.strokeWidth, properties.strokeColor, properties.dashWidth, properties.dashGap);
        }
    }

    private boolean needStateListDrawable() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
                && (hasStrokeColorStateList() || (!properties.useGradient && hasSolidColorStateList()));
    }

    private boolean needRotateDrawable() {
        return properties.useRotate &&
                !(properties.pivotX == 0.5f && properties.pivotY == 0.5f
                  && properties.fromDegrees == 0f && properties.toDegrees == 0f);
    }

    private boolean needScaleDrawable() {
        return properties.useScale;
    }

    private boolean shouldFallbackRipple() {
        return properties.useRipple && !isRippleSupported();
    }

    private Drawable wrap(Drawable drawable) {
        Drawable wrappedDrawable = drawable;

        if (rotateOrder > 0) {
            transformsMap.put(rotateOrder, wrapRotateIfNeeded(wrappedDrawable));
        }
        if (scaleOrder > 0) {
            transformsMap.put(scaleOrder, wrapScaleIfNeeded(wrappedDrawable));
        }

        for (Drawable action : transformsMap.values()) {
            if (action != null) {
                wrappedDrawable = action;
            }
        }

        if (properties.useFlip) {
            wrappedDrawable = new FlipDrawableBuilder()
                    .setDrawable(wrappedDrawable)
                    .setOrientation(properties.orientation)
                    .build();
        }

        if (isRippleSupported() && properties.useRipple) {
            wrappedDrawable = new RippleDrawableBuilder()
                    .setDrawable(wrappedDrawable)
                    .setColor(properties.rippleColor)
                    .setColorStateList(properties.rippleColorStateList)
                    .setRadius(properties.rippleRadius)
                    .build();
        }

        return wrappedDrawable;
    }

    private Drawable wrapRotateIfNeeded(Drawable drawable) {
        if (!needRotateDrawable()) return drawable;

        return new RotateDrawableBuilder()
                    .setDrawable(drawable)
                    .setPivotX(properties.pivotX)
                    .setPivotY(properties.pivotY)
                    .setFromDegrees(properties.fromDegrees)
                    .setToDegrees(properties.toDegrees)
                    .build();
    }

    private Drawable wrapScaleIfNeeded(Drawable drawable) {
        if (!needScaleDrawable()) return drawable;

        return new ScaleDrawableBuilder()
                    .setDrawable(drawable)
                    .setLevel(properties.scaleLevel)
                    .setScaleGravity(properties.scaleGravity)
                    .setScaleWidth(properties.scaleWidth)
                    .setScaleHeight(properties.scaleHeight)
                    .build();
    }

    private boolean isRippleSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private boolean hasSolidColorStateList() {
        return solidColorPressed != Color.TRANSPARENT || solidColorDisabled != Color.TRANSPARENT || solidColorSelected != Color.TRANSPARENT;
    }

    private boolean hasStrokeColorStateList() {
        return strokeColorPressed != Color.TRANSPARENT || strokeColorDisabled != Color.TRANSPARENT || strokeColorSelected != Color.TRANSPARENT;
    }

    private int[][] toArrayOfIntArrays(List<int[]> list) {
        int[][] arr = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private int[] toIntArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
