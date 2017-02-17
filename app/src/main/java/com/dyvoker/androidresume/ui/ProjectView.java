package com.dyvoker.androidresume.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
        Button goToButton = (Button) findViewById(R.id.goToButton);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProjectView, defStyleAttr, 0);
        try {
            projectName.setText(a.getResourceId(R.styleable.ProjectView_projectName, -1));
            projectDescription.setText(a.getResourceId(R.styleable.ProjectView_projectDescription, -1));
            goToButton.setText(a.getResourceId(R.styleable.ProjectView_goToButtonText, -1));
            final String url = a.getString(R.styleable.ProjectView_url);

            goToButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    getContext().startActivity(intent);
                }
            });
        }
        finally {
            a.recycle();
        }
    }
}
