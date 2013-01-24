package com.bigfoot.android.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.bigfoot.android.R;

/**
 * Implements a simple 'about' dialog.
 *
 * User: Neil Pattinson
 * Date: 24/01/13
 * Time: 18:06
 */
public class AboutDialog extends Dialog {

    public AboutDialog(Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        Button okBtn = (Button) findViewById(R.id.about_ok_button);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(getClass().getSimpleName(), "Dismissed!");
                dismiss();
            }
        });

        setTitle(R.string.about_title);
    }
}
