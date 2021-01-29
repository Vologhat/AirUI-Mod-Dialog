package ru.vologhat.airuialert;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.regex.Matcher;

import ru.vologhat.drawabletoolbox.DrawableBuilder;

import static ru.vologhat.airuialert.Utils.*;
import static ru.vologhat.airuialert.Constants.*;

class AirAUIAlertDialog extends Dialog {
    private Context mContext;
    private LinearLayout rootView;
    private TextView tvTitle;
    private TextView tvBody;
    private LinearLayout bContainer;
    private Button mNegative;
    private Button mNeutral;
    private Button mPositive;

    public AirAUIAlertDialog(Context context) {
        super(context);
        setCancelable(false);
        this.mContext = context;
        initViews();
    }

    private void initViews() {
        rootView = new LinearLayout(mContext);
        tvTitle = new TextView(mContext);
        tvBody = new TextView(mContext);
        bContainer = new LinearLayout(mContext);
        mNegative = createButton();
        mNeutral = createButton();
        mPositive = createButton();

        rootView.setLayoutParams(createLinear(-1, -2));
        rootView.setMinimumWidth(DIMEN_MIN_WIDTH_DIALOG);
        rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.setPadding(DIMEN_CONTENT_SPACE, DIMEN_CONTENT_SPACE, DIMEN_CONTENT_SPACE, 0);
        rootView.setBackground(new DrawableBuilder().rectangle().solidColor(DEFAULT_COLOR_LIGHT).cornerRadius(DIMEN_RADIUS).build());

        LinearLayout.LayoutParams a = createLinear(-1, -2);
        a.gravity = Gravity.LEFT;
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(a);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        rootView.addView(linearLayout);

        tvTitle.setLayoutParams(createLinear(-1, -2));
        tvTitle.setMaxLines(1);
        tvTitle.setTextSize(18);
        tvTitle.setPadding(DIMEN_CONTENT_SPACE, DIMEN_CONTENT_SPACE, DIMEN_CONTENT_SPACE, 0);
        tvTitle.setTypeface(getTypeface(FONT_SANS_BOLD));
        tvTitle.setTextColor(TITLE_TEXT_COLOR_LIGHT);
        linearLayout.addView(tvTitle);

        LinearLayout linearLayout1 = new LinearLayout(mContext);
        linearLayout1.setLayoutParams(createLinear(-1, -2));
        linearLayout1.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayout1.setMinimumHeight(dp(2));
        linearLayout.addView(linearLayout1);

        tvBody.setLayoutParams(createLinear(-1, -2));
        tvBody.setTextSize(16);
        tvBody.setTextColor(MESSAGE_TEXT_COLOR_LIGHT);
        tvBody.setTypeface(getTypeface(FONT_SANS_MEDIUM));
        tvBody.setPadding(DIMEN_CONTENT_SPACE, dp(20), DIMEN_CONTENT_SPACE, dp(12));
        linearLayout1.addView(tvBody);

        bContainer.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
        bContainer.setLayoutParams(createLinear(-1, -2));
        bContainer.setOrientation(LinearLayout.HORIZONTAL);
        bContainer.setPadding(dp(12), dp(8), dp(12), dp(8));
        bContainer.setClickable(true); //Wtf
        rootView.addView(bContainer);

        mNegative.setLayoutParams(createLinear(-2, -2));
        mNegative.setPadding(dp(5), dp(5), dp(5), dp(5));
        mNegative.setMaxLines(1);
        mNegative.setTextColor(BUTTON_TEXT_COLOR);
        mNegative.setBackground(new DrawableBuilder()
                .cornerRadius(DIMEN_BUTTON_RADIUS)
                .rectangle()
                .solidColor(DEFAULT_COLOR_LIGHT)
                .ripple(true)
                .rippleColor(COLOR_HIGHLIGHT_LIGHT)
                .build());
        mNegative.setTypeface(getTypeface(FONT_SANS_REGULAR));
        mNegative.setVisibility(View.GONE);
        bContainer.addView(mNegative);

        mNeutral.setLayoutParams(createLinear(-2, -2));
        mNeutral.setPadding(dp(5), dp(5), dp(5), dp(5));
        mNeutral.setMaxLines(1);
        mNeutral.setTextColor(BUTTON_TEXT_COLOR);
        mNeutral.setBackground(new DrawableBuilder()
                .cornerRadius(DIMEN_BUTTON_RADIUS)
                .rectangle()
                .solidColor(DEFAULT_COLOR_LIGHT)
                .ripple(true)
                .rippleColor(COLOR_HIGHLIGHT_LIGHT)
                .build());
        mNeutral.setTypeface(getTypeface(FONT_SANS_REGULAR));
        mNeutral.setVisibility(View.GONE);
        bContainer.addView(mNeutral);

        mPositive.setLayoutParams(createLinear(-2, -2));
        mPositive.setPadding(dp(5), dp(5), dp(5), dp(5));
        mPositive.setMaxLines(1);
        mPositive.setTextColor(BUTTON_TEXT_COLOR);
        mPositive.setBackground(new DrawableBuilder()
                .cornerRadius(DIMEN_BUTTON_RADIUS)
                .rectangle()
                .solidColor(DEFAULT_COLOR_LIGHT)
                .ripple(true)
                .rippleColor(COLOR_HIGHLIGHT_LIGHT)
                .build());
        mPositive.setTypeface(getTypeface(FONT_SANS_REGULAR));
        mPositive.setVisibility(View.GONE);
        bContainer.addView(mPositive);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setBackground(new DrawableBuilder()
                .rectangle()
                .solidColor(DEFAULT_COLOR_LIGHT)
                .cornerRadius(DIMEN_RADIUS)
                .build());
        setContentView(rootView);
    }

    public void setTitle(String str) {
        tvTitle.setText(str);
    }

    public void setMessage(String msg) {
        tvBody.setText(msg);
    }

    public void setNegativeButton(View.OnClickListener click, String text) {
       setNegativeClick(click);
       setNegativeText(text);
    }

    public void setNeutralButton(View.OnClickListener click, String text) {
        setNeutralClick(click);
        setNeutralText(text);
    }

    public void setPositiveButton(View.OnClickListener click, String text) {
        setPositiveClick(click);
        setPositiveText(text);
    }

    public void setNegativeText(String text) {
        mNegative.setText(text);
        mNegative.setVisibility(View.VISIBLE);
    }

    public void setNeutralText(String text) {
        mNeutral.setText(text);
        mNeutral.setVisibility(View.VISIBLE);
    }

    public void setPositiveText(String text) {
        mPositive.setText(text);
        mPositive.setVisibility(View.VISIBLE);
    }


    public void setNegativeClick(View.OnClickListener click) {
        mNegative.setOnClickListener(click);
        mNegative.setVisibility(View.VISIBLE);
    }

    public void setNeutralClick(View.OnClickListener click) {
        mNeutral.setOnClickListener(click);
        mNeutral.setVisibility(View.VISIBLE);
    }

    public void setPositiveClick(View.OnClickListener click) {
        mPositive.setOnClickListener(click);
        mPositive.setVisibility(View.VISIBLE);
    }

    private Button createButton() {
        Button button = new Button(mContext);
        // Remove default animations and shadows for new apis
        if (Build.VERSION.SDK_INT >= 25) {
            button.setStateListAnimator(null);
        }
        return button;
    }
}
