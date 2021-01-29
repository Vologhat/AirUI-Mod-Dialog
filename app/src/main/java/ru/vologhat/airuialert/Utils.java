package ru.vologhat.airuialert;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Utils {
    private static Context ctx;
    public static void setCtx(Context context) {
        ctx = context;
    }

    public static LinearLayout.LayoutParams createLinear(int w, int h) {
        return new LinearLayout.LayoutParams(w, h);
    }

    public static RelativeLayout.LayoutParams createRelative(int w, int h) {
        return new RelativeLayout.LayoutParams(w, h);
    }

    public static int dp(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, ctx.getResources().getDisplayMetrics());
    }

    public static Typeface getTypeface(String assetsPath) {
        return Typeface.createFromAsset(ctx.getAssets(), assetsPath);
    }


}
