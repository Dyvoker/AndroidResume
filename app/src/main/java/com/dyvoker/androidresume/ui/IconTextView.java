package com.dyvoker.androidresume.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyvoker.androidresume.R;

/**
 * View for drawing icon with text
 */

public class IconTextView extends FrameLayout {

    public IconTextView(Context context) {
        this(context, null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.layout_icon_text, this, true);
        ImageView iconView = (ImageView) findViewById(R.id.iconView);
        TextView textView = (TextView) findViewById(R.id.textView);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IconTextView, defStyleAttr, 0);
        try {
            AppCompatDrawableManager drawableManager = AppCompatDrawableManager.get();
            Drawable icon = getDrawableFromXml(drawableManager, a, R.styleable.IconTextView_iconBeforeText);
            iconView.setImageDrawable(icon);
            textView.setText(a.getResourceId(R.styleable.IconTextView_text, -1));
        }
        finally {
            a.recycle();
        }
    }

    private Drawable getDrawableFromXml(AppCompatDrawableManager drawableManager, TypedArray a, int styleableId) {
        final int id = a.getResourceId(styleableId, -1);
        return drawableManager.getDrawable(getContext(), id);
    }
}
