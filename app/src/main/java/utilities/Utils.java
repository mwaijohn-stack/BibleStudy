package utilities;

import android.app.ProgressDialog;
import android.content.Context;

public class Utils {

    public static  ProgressDialog progress(Context context,String massage){

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(massage);
        return progressDialog;

    }
}
