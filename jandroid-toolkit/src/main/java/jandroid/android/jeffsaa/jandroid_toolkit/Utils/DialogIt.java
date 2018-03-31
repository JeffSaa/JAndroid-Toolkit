package jandroid.android.jeffsaa.jandroid_toolkit.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public class DialogIt {

    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;

    public static void toastIt(Context context, String text, int duration){
        Toast.makeText(context, text, duration).show();
    }

    public static ProgressDialog getLoader(Context context, String label){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(label);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return  progressDialog;
    }

}
