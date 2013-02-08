package com.cossacksolutions.overscrollwebviewlib;

/**
 * @author igor
 *         Date: 08.02.13
 *         Time: 16:39
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

public class OverscrollWebView extends WebView {

    private float oldX;

    // indicate if horizontal scrollbar can't go more to the left
    private boolean overScrollLeft = false;

    // indicate if horizontal scrollbar can't go more to the right
    private boolean overScrollRight = false;

    // indicate if horizontal scrollbar can't go more to the left OR right
    private boolean isScrolling = false;

    private OnSwypeListener onSwypeListener;

    public OverscrollWebView(Context context) {
        super(context);
    }

    public OverscrollWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public OverscrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // width of the vertical scrollbar
        int scrollBarWidth = getVerticalScrollbarWidth();

        // width of the view depending of you set in the layout
        int viewWidth = computeHorizontalScrollExtent();

        // width of the webpage depending of the zoom
        int innerWidth = computeHorizontalScrollRange();

        // position of the left side of the horizontal scrollbar
        int scrollBarLeftPos = computeHorizontalScrollOffset();

        // position of the right side of the horizontal scrollbar, the width of
        // scroll is the width of view minus the width of vertical scrollbar
        int scrollBarRightPos = scrollBarLeftPos + viewWidth - scrollBarWidth;

        // if left pos of scroll bar is 0 left over scrolling is true
        overScrollLeft = (scrollBarLeftPos == 0);

        // if right pos of scroll bar is superior to webpage width: right over
        // scrolling is true
        overScrollRight = (scrollBarRightPos >= (innerWidth - scrollBarWidth));

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // when user touch the screen
                // if scrollbar is the most left or right
                isScrolling = !(overScrollLeft || overScrollRight);
                oldX = event.getX();
                break;

            case MotionEvent.ACTION_UP: // when user stop to touch the screen
                // if scrollbar can't go more to the left OR right
                // this allow to force the user to do another gesture when he reach
                // a side
                if (!isScrolling) {

                }
                int swypeDelta = this.getWidth() / 4;
                if (event.getX() > (oldX + swypeDelta) && overScrollLeft) {
                    onSwypeListener.onLeftSwype();
                }

                if (event.getX() < (oldX - swypeDelta) && overScrollRight) {
                    onSwypeListener.onRightSwype();
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setSwypeListener(OnSwypeListener onSwypeListener) {
        this.onSwypeListener = onSwypeListener;
    }

    public interface OnSwypeListener {
        void onLeftSwype();

        void onRightSwype();
    }

}
