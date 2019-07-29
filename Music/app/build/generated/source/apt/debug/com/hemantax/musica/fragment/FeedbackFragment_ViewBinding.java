// Generated code from Butter Knife. Do not modify!
package com.hemantax.musica.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hemantax.musica.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FeedbackFragment_ViewBinding implements Unbinder {
  private FeedbackFragment target;

  private View view2131296304;

  @UiThread
  public FeedbackFragment_ViewBinding(final FeedbackFragment target, View source) {
    this.target = target;

    View view;
    target.txtfeedback = Utils.findRequiredViewAsType(source, R.id.etfeedback, "field 'txtfeedback'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnfeedbacksend, "method 'onViewClicked'");
    view2131296304 = view;
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
    FeedbackFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtfeedback = null;

    view2131296304.setOnClickListener(null);
    view2131296304 = null;
  }
}
