package com.example.admin.w2weekendlistdialogsms;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by admin on 9/8/2017.
 */

public class DialogPictureFrag extends android.support.v4.app.DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout2 = layoutInflater.inflate(R.layout.contact_filter_popup,null);

        //dialog.setView(layout2);

        return new AlertDialog.Builder(getActivity())
        .setView(R.layout.contact_filter_popup)

        .create();
    }

}
