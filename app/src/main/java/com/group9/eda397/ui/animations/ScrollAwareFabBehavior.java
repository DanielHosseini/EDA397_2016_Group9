package com.group9.eda397.ui.animations;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Floating Action Button Behavior implementation which hides the button on scroll down and
 * shows it on scroll up. The button is hid using zoom in / zoom out animation
 *
 * @author palmithor
 * @since 17/05/16.
 */
public class ScrollAwareFabBehavior extends FloatingActionButton.Behavior {


    public ScrollAwareFabBehavior(final Context context, final AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout,
                                       final FloatingActionButton child,
                                       final View directTargetChild,
                                       final View target,
                                       final int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                        nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout,
                               final FloatingActionButton child,
                               final View target,
                               final int dxConsumed,
                               final int dyConsumed,
                               final int dxUnconsumed,
                               final int dyUnconsumed) {

        super.onNestedScroll(
                coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed
        );

        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            child.hide();
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            child.show();
        }
    }
}
