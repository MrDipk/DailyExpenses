package com.dip.dailyexpenses;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class ShowAlertDialog {

    public ShowAlertDialog(){}


    public void showAlertDialog( String message, String positive,Context cn) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(cn)
                .setMessage(message)
                .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
