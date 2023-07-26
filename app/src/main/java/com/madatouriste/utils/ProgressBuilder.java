package com.madatouriste.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.madatouriste.R;


public class ProgressBuilder {
    private Context context;
    private Dialog dialog;

    public ProgressBuilder(Context context) {
        this.context = context;

    }

    public void showProgressDialog() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                context.getResources().getDrawable(
                        R.drawable.dialog_progress_background));
        dialog.setContentView(R.layout.dialog_spinner);
        dialog.setCancelable(false);
        dialog.show();
    }

    public TextView getTextView()
    {
        return (TextView)dialog.findViewById(R.id.textView1);
    }

    public Dialog getDialog()
    {
        return dialog;
    }

    public void dismissProgressDialog() {
        dialog.dismiss();
    }
}
