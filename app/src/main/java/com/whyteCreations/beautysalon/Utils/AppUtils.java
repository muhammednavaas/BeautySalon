package com.whyteCreations.beautysalon.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.whyteCreations.beautysalon.R;

public class AppUtils {
    public static void ShortToast(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View CustomView = inflater.inflate(R.layout.customtoast, null);
        Toast toast = new Toast(context);
        TextView TvMsg = CustomView.findViewById(R.id.TvMsg);
        TvMsg.setText(msg);
        // Set layout to toast
        toast.setView(CustomView);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 300);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


}
