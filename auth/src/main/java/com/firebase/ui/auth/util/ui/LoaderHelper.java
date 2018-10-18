package com.firebase.ui.auth.util.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Parcel;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import com.firebase.ui.auth.ui.ProgressView;
import java.io.Serializable;

/**
 * Created by Bernhard Müller on 4/3/2018.
 */
public abstract class LoaderHelper implements Serializable, ProgressView {

    transient private AlertDialog mDialog;

    transient private View mDialogView;

    @LayoutRes
    private int mLoaderLayoutId;

    public LoaderHelper() {
    }

    public LoaderHelper(Parcel in) {
        this.mLoaderLayoutId = in.readInt();
    }

    public int getLoaderLayoutId() {
        return mLoaderLayoutId;
    }

    public boolean hasLayout() {
        return mLoaderLayoutId != 0;
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void hideProgress(Context context) {
        if (mDialog != null && mDialog.isShowing()) {
            if (mDialogView != null) {
                showSuccessImage(mDialogView);
                mDialogView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mDialog.dismiss();
                            reset(mDialogView);
                        } catch (IllegalArgumentException ignored) {
                            //App was already dismissed. Ignoring the exception
                        }
                    }
                }, 200);
            } else {
                mDialog.dismiss();
            }
        }
    }

    public void reset(@Nullable View view) {

    }

    public void setMessage(@Nullable View view, CharSequence message) {
    }

    @Override
    public void showProgress(@StringRes int message) {
    }

    @Override
    public void showProgress(Context context, @StringRes int message) {
        if (mDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            if (hasLayout()) {
                mDialogView = LayoutInflater.from(context)
                        .inflate(getLoaderLayoutId(), null, false);
                builder.setView(mDialogView);
            }
            mDialog = builder.create();
        }

        setMessage(mDialogView, context.getString(message));

        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void showSuccessImage(@Nullable View view) {
    }
}