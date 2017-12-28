package com.example.shlez.synagogue;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * Created by Shlez on 11/25/17.
 */

public class AboutUs extends AppCompatActivity {


    private static final String TAG = "AboutUs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element titleElement = new Element();
        titleElement.setTitle("About Synagogue");
        Element versionElement = new Element();
        versionElement.setTitle("Version 1.0");



        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription(getString(R.string.about_description))
                .setImage(R.drawable.synagogue)
                .addEmail("shlez1993.hs@gmail.com")
                .addItem(versionElement)
                .create();

        setContentView(aboutPage);
    }

}
