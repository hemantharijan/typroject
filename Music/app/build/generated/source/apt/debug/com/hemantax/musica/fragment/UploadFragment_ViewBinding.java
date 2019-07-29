// Generated code from Butter Knife. Do not modify!
package com.hemantax.musica.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hemantax.musica.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadFragment_ViewBinding implements Unbinder {
  private UploadFragment target;

  private View view2131296305;

  private View view2131296266;

  @UiThread
  public UploadFragment_ViewBinding(final UploadFragment target, View source) {
    this.target = target;

    View view;
    target.etMname = Utils.findRequiredViewAsType(source, R.id.Mname, "field 'etMname'", EditText.class);
    target.etMartist = Utils.findRequiredViewAsType(source, R.id.MArtist, "field 'etMartist'", EditText.class);
    target.sMgenre = Utils.findRequiredViewAsType(source, R.id.MGenre, "field 'sMgenre'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.btnuploadmusic, "method 'onViewClicked'");
    view2131296305 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.Mpicker, "method 'onViewClicked'");
    view2131296266 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UploadFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etMname = null;
    target.etMartist = null;
    target.sMgenre = null;

    view2131296305.setOnClickListener(null);
    view2131296305 = null;
    view2131296266.setOnClickListener(null);
    view2131296266 = null;
  }
}
