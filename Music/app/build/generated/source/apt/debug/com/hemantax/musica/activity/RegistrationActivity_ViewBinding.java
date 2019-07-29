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

public class RegistrationActivity_ViewBinding implements Unbinder {
  private RegistrationActivity target;

  private View view2131296303;

  @UiThread
  public RegistrationActivity_ViewBinding(RegistrationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegistrationActivity_ViewBinding(final RegistrationActivity target, View source) {
    this.target = target;

    View view;
    target.etName = Utils.findRequiredViewAsType(source, R.id.etName, "field 'etName'", EditText.class);
    target.etUsername = Utils.findRequiredViewAsType(source, R.id.etUsername, "field 'etUsername'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'etPassword'", EditText.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnSignUp, "method 'onViewClicked'");
    view2131296303 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RegistrationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etName = null;
    target.etUsername = null;
    target.etPassword = null;
    target.etEmail = null;

    view2131296303.setOnClickListener(null);
    view2131296303 = null;
  }
}
