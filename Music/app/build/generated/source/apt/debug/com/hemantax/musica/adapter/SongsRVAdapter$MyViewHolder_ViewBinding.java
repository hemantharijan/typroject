// Generated code from Butter Knife. Do not modify!
package com.hemantax.musica.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hemantax.musica.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SongsRVAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private SongsRVAdapter.MyViewHolder target;

  private View view2131296299;

  private View view2131296301;

  @UiThread
  public SongsRVAdapter$MyViewHolder_ViewBinding(final SongsRVAdapter.MyViewHolder target,
      View source) {
    this.target = target;

    View view;
    target.imgCover = Utils.findRequiredViewAsType(source, R.id.imgCover, "field 'imgCover'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btnDownload, "field 'btnDownload' and method 'onViewClicked'");
    target.btnDownload = Utils.castView(view, R.id.btnDownload, "field 'btnDownload'", ImageView.class);
    view2131296299 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnPlayPause, "field 'btnPlayPause' and method 'onViewClicked'");
    target.btnPlayPause = Utils.castView(view, R.id.btnPlayPause, "field 'btnPlayPause'", ImageView.class);
    view2131296301 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvSongName = Utils.findRequiredViewAsType(source, R.id.tvSongName, "field 'tvSongName'", TextView.class);
    target.tvArtist = Utils.findRequiredViewAsType(source, R.id.tvArtist, "field 'tvArtist'", TextView.class);
    target.tvUploaded = Utils.findRequiredViewAsType(source, R.id.tvUploaded, "field 'tvUploaded'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SongsRVAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgCover = null;
    target.btnDownload = null;
    target.btnPlayPause = null;
    target.tvSongName = null;
    target.tvArtist = null;
    target.tvUploaded = null;

    view2131296299.setOnClickListener(null);
    view2131296299 = null;
    view2131296301.setOnClickListener(null);
    view2131296301 = null;
  }
}
