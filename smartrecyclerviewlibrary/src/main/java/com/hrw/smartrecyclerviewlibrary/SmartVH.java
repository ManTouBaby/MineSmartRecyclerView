package com.hrw.smartrecyclerviewlibrary;

import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * @author:MtBaby
 * @date:2017/09/17 14:56
 * @desc:
 */


public class SmartVH extends RecyclerView.ViewHolder {

    public SmartVH(View itemView) {
        super(itemView);

        /**
         * 设置水波纹背景
         */
        if (itemView.getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = itemView.getContext().getTheme();
            int top = itemView.getPaddingTop();
            int bottom = itemView.getPaddingBottom();
            int left = itemView.getPaddingLeft();
            int right = itemView.getPaddingRight();
            if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
                itemView.setBackgroundResource(typedValue.resourceId);
            }
            itemView.setPadding(left, top, right, bottom);
        }
    }


    public void text(@IdRes int idRes, String label) {
        TextView textView;
        View view = itemView.findViewById(idRes);
        if (view instanceof TextView) {
            textView = (TextView) view;
            textView.setText(label);
        }
    }

    public void textColor(@IdRes int idRes, @ColorInt int color) {
        TextView textView;
        View view = itemView.findViewById(idRes);
        if (view instanceof TextView) {
            textView = (TextView) view;
            textView.setTextColor(color);
        }
    }

    public Button getButton(@IdRes int idRes) {
        Button button = null;
        View view = itemView.findViewById(idRes);
        if (view instanceof Button) {
            button = (Button) view;
        }
        return button;
    }

    public TextView getText(@IdRes int idRes) {
        TextView textView = null;
        View view = itemView.findViewById(idRes);
        if (view instanceof TextView) {
            textView = (TextView) view;
        }
        return textView;
    }

    public LinearLayout getLinearLayout(@IdRes int idRes) {
        LinearLayout linearLayout = null;
        View view = itemView.findViewById(idRes);
        if (view instanceof LinearLayout) {
            linearLayout = (LinearLayout) view;
        }
        return linearLayout;
    }

    public RelativeLayout getRelativeLayout(@IdRes int idRes) {
        RelativeLayout relativeLayout = null;
        View view = itemView.findViewById(idRes);
        if (view instanceof RelativeLayout) {
            relativeLayout = (RelativeLayout) view;
        }
        return relativeLayout;
    }

    public ImageView getImage(@IdRes int idRes) {
        ImageView imageView = null;
        View view = itemView.findViewById(idRes);
        if (view instanceof ImageView) {
            imageView = (ImageView) view;
        }
        return imageView;
    }

    public View getView(@IdRes int idRes) {
        View view = itemView.findViewById(idRes);
        return view;
    }

}
