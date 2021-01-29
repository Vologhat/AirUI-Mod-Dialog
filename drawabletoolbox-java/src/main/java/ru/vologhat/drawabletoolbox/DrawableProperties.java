package ru.vologhat.drawabletoolbox;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;
import static java.lang.Float.*;

public class DrawableProperties implements Parcelable {

    public DrawableProperties() {

    }

    public DrawableProperties(Parcel parcel) {
        shape = parcel.readInt();
        innerRadius = parcel.readInt();
        innerRadiusRatio = parcel.readInt();
        thickness = parcel.readInt();
        thicknessRatio = parcel.readFloat();
        useLevelForRing = (parcel.readByte() != (byte) 0);

        _cornerRadius = parcel.readInt();
        topLeftRadius = parcel.readInt();
        topRightRadius = parcel.readInt();
        bottomRightRadius = parcel.readInt();
        bottomLeftRadius = parcel.readInt();

        useGradient = (parcel.readByte() != (byte) 0);
        type = parcel.readInt();
        angle = parcel.readInt();
        centerX = parcel.readFloat();
        centerY = parcel.readFloat();
        useCenterColor = (parcel.readByte() != (byte) 0);
        startColor = parcel.readInt();
        centerColor = (int) parcel.readValue(Integer.class.getClassLoader());
        endColor = parcel.readInt();
        gradientRadiusType = parcel.readInt();
        gradientRadius = parcel.readFloat();
        useLevelForGradient = (parcel.readByte() != (byte) 0);
        type = parcel.readInt();

        width = parcel.readInt();
        height = parcel.readInt();

        solidColor = parcel.readInt();
        solidColorStateList = parcel.readParcelable(ColorStateList.class.getClassLoader());

        strokeWidth = parcel.readInt();
        strokeColor = parcel.readInt();
        strokeColorStateList = parcel.readParcelable(ColorStateList.class.getClassLoader());
        dashWidth = parcel.readInt();
        dashGap = parcel.readInt();

        useRotate = (parcel.readByte() != (byte) 0);
        pivotX = parcel.readFloat();
        pivotY = parcel.readFloat();
        fromDegrees = parcel.readFloat();
        toDegrees = parcel.readFloat();

        useScale = (parcel.readByte() != (byte) 0);
        scaleLevel = parcel.readInt();
        scaleGravity = parcel.readInt();
        scaleWidth = parcel.readInt();
        scaleHeight = parcel.readInt();

        useFlip = (parcel.readByte() != (byte) 0);
        orientation = parcel.readInt();

        useRipple = (parcel.readByte() != (byte) 0);
        rippleColor = parcel.readInt();
        rippleColorStateList = parcel.readParcelable(ColorStateList.class.getClassLoader());
        rippleRadius = parcel.readInt();
    }

    public DrawableProperties copy() {
        Parcel parcel = Parcel.obtain();
        writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        DrawableProperties properties = CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return properties;
    }

    public static final Parcelable.Creator<DrawableProperties> CREATOR = new Parcelable.Creator<DrawableProperties>() {
        public DrawableProperties createFromParcel(Parcel parcel) {
            return new DrawableProperties(parcel);
        }

        public DrawableProperties[] newArray(int size) {
            return new DrawableProperties[size];
        }
    };

    void setCornerRadius(int value) {
        _cornerRadius = value;
        topRightRadius = value;
        topLeftRadius = value;
        bottomLeftRadius = value;
        bottomRightRadius = value;
    }

    float[] getCornerRadii() {
        return new float[]{valueOf(topLeftRadius), valueOf(topLeftRadius),
                    valueOf(topRightRadius), valueOf(topRightRadius),
                    valueOf(bottomRightRadius), valueOf(bottomRightRadius),
                    valueOf(bottomLeftRadius), valueOf(bottomLeftRadius)};
    }

    GradientDrawable.Orientation getOrientation() {
        int angle = this.angle % 360;
        GradientDrawable.Orientation orientation;
        switch (angle) {
            case 0:
                orientation = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case 45:
                orientation = GradientDrawable.Orientation.BL_TR;
                break;
            case 90:
                orientation = GradientDrawable.Orientation.BOTTOM_TOP;
                break;
            case 135:
                orientation = GradientDrawable.Orientation.BR_TL;
                break;
            case 180:
                orientation = GradientDrawable.Orientation.RIGHT_LEFT;
                break;
            case 225:
                orientation = GradientDrawable.Orientation.TR_BL;
                break;
            case 270:
                orientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
            case 315:
                orientation = GradientDrawable.Orientation.TL_BR;
                break;
            default:
                throw new IllegalArgumentException("Unsupported angle: " + angle);
        }
        return orientation;
    }

    int[] getColors() {
        if (useCenterColor & centerColor != Color.TRANSPARENT) {
            return new int[]{startColor, centerColor, endColor};
        } else {
            return new int[]{startColor, endColor};
        }
    }

    public Drawable materialization() {
        return new DrawableBuilder().batch(this).build();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(shape);
        parcel.writeInt(innerRadius);
        parcel.writeFloat(innerRadiusRatio);
        parcel.writeInt(thickness);
        parcel.writeFloat(thicknessRatio);
        parcel.writeByte(useLevelForRing ? (byte) 1 : (byte) 0);
        parcel.writeInt(_cornerRadius);
        parcel.writeInt(topLeftRadius);
        parcel.writeInt(topRightRadius);
        parcel.writeInt(bottomRightRadius);
        parcel.writeInt(bottomLeftRadius);
        parcel.writeByte(useGradient ? (byte) 1 : (byte) 0);
        parcel.writeInt(type);
        parcel.writeInt(angle);
        parcel.writeFloat(centerX);
        parcel.writeFloat(centerY);
        parcel.writeByte(useCenterColor ? (byte) 1 : (byte) 0);
        parcel.writeInt(startColor);
        parcel.writeValue(centerColor);
        parcel.writeInt(endColor);
        parcel.writeInt(gradientRadiusType);
        parcel.writeFloat(gradientRadius);
        parcel.writeByte(useLevelForGradient ? (byte) 1 : (byte) 0);
        parcel.writeInt(width);
        parcel.writeInt(height);
        parcel.writeInt(solidColor);
        parcel.writeParcelable(solidColorStateList, flags);
        parcel.writeInt(strokeWidth);
        parcel.writeInt(strokeColor);
        parcel.writeParcelable(strokeColorStateList, flags);
        parcel.writeInt(dashWidth);
        parcel.writeInt(dashGap);
        parcel.writeByte(useRotate ? (byte) 1 : (byte) 0);
        parcel.writeFloat(pivotX);
        parcel.writeFloat(pivotY);
        parcel.writeFloat(fromDegrees);
        parcel.writeFloat(toDegrees);
        parcel.writeByte(useScale ? (byte) 1 : (byte) 0);
        parcel.writeInt(scaleLevel);
        parcel.writeInt(scaleGravity);
        parcel.writeFloat(scaleWidth);
        parcel.writeFloat(scaleHeight);
        parcel.writeByte(useFlip ? (byte) 1 : (byte) 0);
        parcel.writeInt(orientation);
        parcel.writeByte(useRipple ? (byte) 1 : (byte) 0);
        parcel.writeInt(rippleColor);
        parcel.writeParcelable(rippleColorStateList, flags);
        parcel.writeInt(rippleRadius);
    }

    // <shape>
    int shape = GradientDrawable.RECTANGLE;
    int innerRadius = -1;
    float innerRadiusRatio = 9f;
    int thickness = -1;
    float thicknessRatio = 3f;
    boolean useLevelForRing = false;

    // <corner>
    private int _cornerRadius = 0;
    int topLeftRadius = 0;
    int topRightRadius = 0;
    int bottomRightRadius = 0;
    int bottomLeftRadius = 0;

    // <gradient>
    boolean useGradient = false;
    int type = GradientDrawable.RADIAL_GRADIENT;
    int angle = 0;
    float centerX = 0.5f;
    float centerY = 0.5f;
    boolean useCenterColor = false;
    int startColor = Constants.DEFAULT_COLOR;
    int centerColor = Color.TRANSPARENT;
    int endColor = 0x7FFFFFFF;
    int gradientRadiusType = RADIUS_TYPE_FRACTION;
    float gradientRadius = 0.5f;
    boolean useLevelForGradient = false;

    // <size>
    int width = -1;
    int height = -1;

    // <solid>
    int solidColor = Color.TRANSPARENT;
    ColorStateList solidColorStateList = null;

    // <stroke>
    int strokeWidth = 0;
    int strokeColor = Color.DKGRAY;
    ColorStateList strokeColorStateList = null;
    int dashWidth = 0;
    int dashGap = 0;

    // <rotate>
    boolean useRotate = false;
    float pivotX = 0.5f;
    float pivotY = 0.5f;
    float fromDegrees = 0f;
    float toDegrees = 0f;

    // <scale>
    boolean useScale = false;
    int scaleLevel = 10000;
    int scaleGravity = Gravity.CENTER;
    float scaleWidth = 0f;
    float scaleHeight = 0f;

    // flip
    boolean useFlip = false;
    int orientation = FlipDrawable.ORIENTATION_HORIZONTAL;

    // ripple
    boolean useRipple = false;
    int rippleColor = Constants.DEFAULT_COLOR;
    ColorStateList rippleColorStateList = null;
    int rippleRadius = -1;

    public static final int RADIUS_TYPE_PIXELS = 0;
    public static final int RADIUS_TYPE_FRACTION = 1;
}
