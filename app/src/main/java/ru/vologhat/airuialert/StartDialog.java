package ru.vologhat.airuialert;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import static ru.vologhat.airuialert.Utils.*;

public class StartDialog {
    public static void run(final Context context) {
        Utils.setCtx(context);
        final AirAUIAlertDialog dialog = new AirAUIAlertDialog(context);
        dialog.setTitle("AirUI Dialog by Vologhat");
        dialog.setMessage("The bird of Hermes is my name eating my wings to make me tame");
        dialog.setNegativeButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Goodbye", (int) 0).show();
                dialog.cancel();
            }
        }, "Cancel");
        dialog.setNeutralButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }, "More");
        dialog.setPositiveButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Goodbye", (int) 0).show();
                dialog.cancel();
            }
        }, "Telegram");
        dialog.show();
    }
}
