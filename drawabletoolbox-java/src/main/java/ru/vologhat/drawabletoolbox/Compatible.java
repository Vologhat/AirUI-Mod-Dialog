package ru.vologhat.drawabletoolbox;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Build;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Compatible {
    private static Class<?> gradientState = resolveGradientState();
    private static Class<?> rotateState = resolveRotateState();

    private static Class<?> resolveGradientState() {
        Class<?>[] classes = GradientDrawable.class.getDeclaredClasses();
        for (Class<?> clz: classes) {
            if (clz.getSimpleName().equals("GradientState")) return clz;
        }
        throw new RuntimeException("GradientState could not be found in current GradientDrawable implementation");
    }

    private static Class<?> resolveRotateState() {
        Class<?>[] classes = RotateDrawable.class.getDeclaredClasses();
        for (Class<?> clz: classes) {
            if (clz.getSimpleName().equals("RotateState")) return clz;
        }
        throw new RuntimeException("RotateState could not be found in current RotateDrawable implementation");
    }

    private static Field resolveField(Class<?> clz, String name) throws SecurityException, NoSuchFieldException {
        Field field = clz.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    private static Method resolveMethod(Class<?> clz, String name, Class<?>... args) throws SecurityException, NoSuchMethodException {
        Method method = clz.getDeclaredMethod(name, args);
        method.setAccessible(true);
        return method;
    }

    public static void setInnerRadius(GradientDrawable drawable, float value) {
        try {
            Field field = resolveField(gradientState, "mInnerRadius");
            field.setFloat(drawable, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setInnerRadiusRatio(GradientDrawable drawable, float value) {
        try {
            Field field = resolveField(gradientState, "mInnerRadiusRatio");
            field.setFloat(drawable, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setThickness(GradientDrawable drawable, int value) {
        try {
            Field field = resolveField(gradientState, "mThickness");
            field.setInt(drawable, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setThicknessRatio(GradientDrawable drawable, float value) {
        try {
            Field field = resolveField(gradientState, "ThicknessRatio");
            field.setFloat(drawable, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setUseLevelForShape(GradientDrawable drawable, boolean value) {
        try {
            Field field = resolveField(gradientState, "mUseLevelForShape");
            field.setBoolean(drawable, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setOrientation(GradientDrawable drawable, GradientDrawable.Orientation value) {
        try {
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
               drawable.setOrientation(value);
           } else {
               Field field = resolveField(gradientState, "mOrientation");
               field.set(drawable.getConstantState(), value);
               Field field1 = resolveField(GradientDrawable.class, "mRectIsDirty");
               field1.setBoolean(drawable, true);
               drawable.invalidateSelf();
           }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setColors(GradientDrawable drawable, int[] value) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                drawable.setColors(value);
            } else {
                Field field = resolveField(gradientState, "mColors");
                field.set(drawable, value);
                drawable.invalidateSelf();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setGradientRadiusType(GradientDrawable drawable, int value) {
        try {
            Field field = resolveField(gradientState, "mGradientRadiusType");
            field.setInt(drawable, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setGradientRadius(GradientDrawable drawable, float value) {
        try {
            Field field = resolveField(gradientState, "mGradientRadius");
            field.setFloat(drawable.getConstantState(), value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setStrokeColor(GradientDrawable drawable, int value) {
        try {
            Field field = resolveField(gradientState, "mStrokeColor");
            field.setInt(drawable, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setDrawable(RotateDrawable rotateDrawable, Drawable drawable) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                rotateDrawable.setDrawable(drawable);
            } else {
                Field field = resolveField(rotateState, "mDrawable");
                Field field1 = resolveField(RotateDrawable.class, "mState");
                field.set(field1.get(rotateDrawable), drawable);
                drawable.setCallback(rotateDrawable);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setPivotX(RotateDrawable drawable, float value) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable.setPivotX(value);
            } else {
                Field field = resolveField(rotateState, "mPivotX");
                field.setFloat(drawable.getConstantState(), value);
                Field field1 = resolveField(rotateState, "mPivotXRel");
                field1.setBoolean(drawable.getConstantState(), true);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setPivotY(RotateDrawable drawable, float value) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable.setPivotY(value);
            } else {
                Field field = resolveField(rotateState, "mPivotY");
                field.setFloat(drawable.getConstantState(), value);
                Field field1 = resolveField(rotateState, "mPivotYRel");
                field1.setBoolean(drawable.getConstantState(), true);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setFromDegrees(RotateDrawable drawable, float value) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable.setFromDegrees(value);
            } else {
                Field field = resolveField(rotateState, "mFromDegrees");
                field.setFloat(drawable.getConstantState(), value);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setToDegrees(RotateDrawable drawable, float value) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable.setFromDegrees(value);
            } else {
                Field field = resolveField(rotateState, "mToDegrees");
                field.setFloat(drawable.getConstantState(), value);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setRadius(RippleDrawable drawable, int value) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                drawable.setRadius(value);
            } else {
                Method method = resolveMethod(RippleDrawable.class, "setMaxRadius", Integer.class);
                method.invoke(drawable, value);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
