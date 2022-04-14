package com.krak.olympicmap.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.krak.olympicmap.R;

/*
    Диалоговое окно с текстом
 */
public class MessageDialog extends DialogFragment {

    private String message = null;
    private String title = null;

    public MessageDialog(@NonNull String title, @NonNull String message) {
        this.title = title;
        this.message = message;
    }

    public MessageDialog(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (message != null){
            builder.setMessage(message);
        }
        AlertDialog dialog = builder.setTitle(title).setPositiveButton("Ok", null).create();
        dialog.setOnShowListener(arg0 -> {
            dialog.getWindow().setBackgroundDrawableResource(R.color.main);

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.white));
        });
        return dialog;
    }
}
