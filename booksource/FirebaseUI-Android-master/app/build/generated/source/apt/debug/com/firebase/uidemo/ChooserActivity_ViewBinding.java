// Generated code from Butter Knife. Do not modify!
package com.firebase.uidemo;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChooserActivity_ViewBinding implements Unbinder {
  private ChooserActivity target;

  @UiThread
  public ChooserActivity_ViewBinding(ChooserActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChooserActivity_ViewBinding(ChooserActivity target, View source) {
    this.target = target;

    target.mActivities = Utils.findRequiredViewAsType(source, R.id.activities, "field 'mActivities'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChooserActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mActivities = null;
  }
}
