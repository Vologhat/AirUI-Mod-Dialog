package ru.vologhat.airuialert;

import android.graphics.Color;
import ru.vologhat.*;

import static ru.vologhat.airuialert.Utils.*;
public class Constants {
    //Dimens
    public static final int DIMEN_CONTENT_SPACE = dp(10);
    public static final int DIMEN_BUTTON_WRAPPER_PADDING_HORIZONTAL = dp(12);
    public static final int DIMEN_BUTTON_WRAPPER_PADDING_VERTICAL = dp(8);
    public static final int DIMEN_RADIUS = dp(10);
    public static final int DIMEN_BUTTON_RADIUS = dp(7);
    public static final int DIMEN_MIN_WIDTH_DIALOG = dp(350);

    //Fonts
    public static final String FONT_SANS_BOLD = "fonts/google_sans_bold.ttf";
    public static final String FONT_SANS_MEDIUM = "fonts/google_sans_medium.ttf";
    public static final String FONT_SANS_REGULAR = "fonts/google_sans_regular.ttf";

    //Light theme
    public static int DEFAULT_COLOR_LIGHT = Color.WHITE;
    public static int COLOR_HIGHLIGHT_LIGHT = Color.parseColor("#A2C5FF");
    public static int BUTTON_TEXT_COLOR = Color.parseColor("#FF448AFF");
    public static int TITLE_TEXT_COLOR_LIGHT = Color.BLACK;
    public static int MESSAGE_TEXT_COLOR_LIGHT = Color.parseColor("#757575");

    //Dark theme
    public static int DEFAULT_COLOR_DARK = Color.parseColor("#424242");
    public static int COLOR_HIGHLIGHT_DARK = Color.parseColor("#4c627b");
    public static int TITLE_TEXT_COLOR_DARK = Color.WHITE;
    public static int MESSAGE_TEXT_COLOR_DARK = Color.parseColor("#d7d7d7");
}
