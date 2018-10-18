package com.firebase.ui.auth.ui;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;

/**
 * View (Activity or Fragment, normally) that can respond to progress events.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public interface ProgressView {

    void hideProgress();

    void hideProgress(Context context);

    void showProgress(@StringRes int message);

    void showProgress(Context context, @StringRes int message);

}
