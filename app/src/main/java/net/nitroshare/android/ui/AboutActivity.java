package net.nitroshare.android.ui;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.nitroshare.android.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Show basic application information
 */
public class AboutActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SharedPreferences mSharedPreferences;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(AboutActivity.this);

        if(mSharedPreferences.getBoolean("dark-theme", false))
        {
            setTheme(R.style.DarkTheme);
        }
        else
        {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        PackageInfo packageInfo = null;
        try
        {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        }
        catch (PackageManager.NameNotFoundException ignored)
        {

        }

        if (packageInfo != null)
        {
            Date lastUpdateDate = new Date(packageInfo.lastUpdateTime);

            ((TextView) findViewById(R.id.version)).setText(
                    String.format(
                            Locale.getDefault(),
                            "%s (%d)",
                            packageInfo.versionName,
                            packageInfo.versionCode
                    )
            );

            ((TextView) findViewById(R.id.lastUpdated)).setText(
                    DateFormat.getDateInstance().format(lastUpdateDate)
            );
        }
    }
}
