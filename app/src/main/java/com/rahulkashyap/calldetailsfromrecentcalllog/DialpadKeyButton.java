package com.rahulkashyap.calldetailsfromrecentcalllog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class DialpadKeyButton extends FrameLayout {

    private Boolean mLongHovered;

    private OnPressedListener mOnPressedListener;

    /**
     * Constructor
     *
     * @param context
     * @param attrs
     */
    public DialpadKeyButton(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * Set OnPressedListener
     *
     * @param onPressedListener
     */
    public void setOnPressedListener(OnPressedListener onPressedListener) {
        mOnPressedListener = onPressedListener;
    }

    public interface OnPressedListener {
        void onPressed(View view, boolean pressed);
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (mOnPressedListener != null) {
            mOnPressedListener.onPressed(this, pressed);
        }
    }

}
