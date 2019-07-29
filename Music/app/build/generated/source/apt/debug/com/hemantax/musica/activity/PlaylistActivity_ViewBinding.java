// Generated code from Butter Knife. Do not modify!
package com.hemantax.musica.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hemantax.musica.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlaylistActivity_ViewBinding implements Unbinder {
  private PlaylistActivity target;

  @UiThread
  public PlaylistActivity_ViewBinding(PlaylistActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PlaylistActivity_ViewBinding(PlaylistActivity target, View source) {
    this.target = target;

    target.songRv = Utils.findRequiredViewAsType(source, R.id.songRv, "field 'songRv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PlaylistActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.songRv = null;
  }
}
