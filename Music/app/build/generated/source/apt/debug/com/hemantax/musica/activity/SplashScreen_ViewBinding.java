// Generated code from Butter Knife. Do not modify!
package com.hemantax.musica.activity;

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

public class SplashScreen_ViewBinding implements Unbinder {
  private SplashScreen target;

  @UiThread
  public SplashScreen_ViewBinding(SplashScreen target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SplashScreen_ViewBinding(SplashScreen target, View source) {
    this.target = target;

    target.imageView = Utils.findRequiredViewAsType(source, R.id.imageView40, "field 'imageView'", ImageView.class);
    target.textView = Utils.findRequiredViewAsType(source, R.id.textView, "field 'textView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SplashScreen target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageView = null;
    target.textView = null;
  }
}
