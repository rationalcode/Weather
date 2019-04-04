package com.example.weather;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class DialogFragment extends android.support.v4.app.DialogFragment {



    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.select_city)
                .setItems(R.array.cities_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getActivity(),
                                which,
                                Toast.LENGTH_SHORT).show();
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        return builder.create();


    }
}
