// Generated by view binder compiler. Do not edit!
package com.application.dbscgcollectionmanager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.application.dbscgcollectionmanager.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class RowBinding implements ViewBinding {
  @NonNull
  private final TextView rootView;

  @NonNull
  public final TextView text1;

  private RowBinding(@NonNull TextView rootView, @NonNull TextView text1) {
    this.rootView = rootView;
    this.text1 = text1;
  }

  @Override
  @NonNull
  public TextView getRoot() {
    return rootView;
  }

  @NonNull
  public static RowBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RowBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.row, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RowBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    TextView text1 = (TextView) rootView;

    return new RowBinding((TextView) rootView, text1);
  }
}
