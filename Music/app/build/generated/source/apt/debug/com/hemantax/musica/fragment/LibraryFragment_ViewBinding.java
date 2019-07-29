// Generated code from Butter Knife. Do not modify!
package com.hemantax.musica.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hemantax.musica.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LibraryFragment_ViewBinding implements Unbinder {
  private LibraryFragment target;

  @UiThread
  public LibraryFragment_ViewBinding(LibraryFragment target, View source) {
    this.target = target;

    target.rvGenre = Utils.findRequiredViewAsType(source, R.id.rvGenre, "field 'rvGenre'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LibraryFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvGenre = null;
  }
}
