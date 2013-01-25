package com.bigfoot.android.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.androidquery.util.AQUtility;
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

        TextView appVersionView = (TextView) findViewById(R.id.app_vn_text_view);
        PackageInfo info;
        String version;
        try {
            info = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(getClass().getSimpleName(), "Error reading package info", e);
            version = "";
        }
        appVersionView.setText(appVersionView.getText() + version);

        setTitle(R.string.about_title);
    }
}
