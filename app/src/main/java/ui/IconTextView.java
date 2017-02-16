package ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dyvoker.androidresume.R;

/**
 * View for drawing icon with text
 */

public class IconTextView extends LinearLayout {

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
            iconView.setImageDrawable(a.getDrawable(R.styleable.IconTextView_iconBeforeText));
            textView.setText(a.getResourceId(R.styleable.IconTextView_text, -1));
        }
        finally {
            a.recycle();
        }
    }

}
