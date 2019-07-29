// Generated code from Butter Knife. Do not modify!
package com.hemantax.musica.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hemantax.musica.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GenreRVAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private GenreRVAdapter.MyViewHolder target;

  @UiThread
  public GenreRVAdapter$MyViewHolder_ViewBinding(GenreRVAdapter.MyViewHolder target, View source) {
    this.target = target;

    target.imgGenre = Utils.findRequiredViewAsType(source, R.id.imgGenre, "field 'imgGenre'", ImageView.class);
    target.tvGenreName = Utils.findRequiredViewAsType(source, R.id.tvGenreName, "field 'tvGenreName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GenreRVAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgGenre = null;
    target.tvGenreName = null;
  }
}
