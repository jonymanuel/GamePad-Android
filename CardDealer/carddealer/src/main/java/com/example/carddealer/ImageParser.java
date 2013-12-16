package com.example.carddealer;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class ImageParser {

    Activity activity;

    public ImageParser(Activity mActivity) {
        activity = mActivity;
    }

    public void setSVGImage(ImageView imageView, int img) {

        // Parse SVG
        SVG svg = SVGParser.getSVGFromResource(activity.getResources(), img);

        // Convert SVG to Drawable
        Drawable tmpImg = svg.createPictureDrawable();

        // If Android version is between 2.3 and lower than 3, don't use setLayerType
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        // Set Drawable in ImageView
        imageView.setImageDrawable(tmpImg);
    }
}
