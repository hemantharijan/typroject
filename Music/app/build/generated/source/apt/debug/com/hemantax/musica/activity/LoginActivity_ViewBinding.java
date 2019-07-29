// Generated code from Butter Knife. Do not modify!
package com.hemantax.musica.activity;

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

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131296300;

  private View view2131296302;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.etUser = Utils.findRequiredViewAsType(source, R.id.etUser, "field 'etUser'", EditText.class);
    target.etPass = Utils.findRequiredViewAsType(source, R.id.etPass, "field 'etPass'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnLogin, "method 'onViewClicked'");
    view2131296300 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnReg, "method 'onViewClicked'");
    view2131296302 = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etUser = null;
    target.etPass = null;

    view2131296300.setOnClickListener(null);
    view2131296300 = null;
    view2131296302.setOnClickListener(null);
    view2131296302 = null;
  }
}
