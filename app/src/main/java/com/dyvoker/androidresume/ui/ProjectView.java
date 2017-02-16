package com.dyvoker.androidresume.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dyvoker.androidresume.R;

/**
 * View for drawing icon with text
 */

public class ProjectView extends FrameLayout {

    public ProjectView(Context context) {
        this(context, null);
    }

    public ProjectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProjectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.layout_project, this, true);
        TextView projectName = (TextView) findViewById(R.id.projectName);
        TextView projectDescription = (TextView) findViewById(R.id.projectDescription);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProjectView, defStyleAttr, 0);
        try {
            //AppCompatDrawableManager drawableManager = AppCompatDrawableManager.get();
            //Drawable image = getDrawableFromXml(drawableManager, a, R.styleable.IconTextView_iconBeforeText);
            projectName.setText(a.getResourceId(R.styleable.ProjectView_projectName, -1));
            projectDescription.setText(a.getResourceId(R.styleable.ProjectView_projectDescription, -1));
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
