// Generated code from Butter Knife. Do not modify!
package com.firebase.uidemo.auth;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.firebase.uidemo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignedInActivity_ViewBinding implements Unbinder {
  private SignedInActivity target;

  private View view2131689741;

  private View view2131689742;

  @UiThread
  public SignedInActivity_ViewBinding(SignedInActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignedInActivity_ViewBinding(final SignedInActivity target, View source) {
    this.target = target;

    View view;
    target.mRootView = Utils.findRequiredView(source, android.R.id.content, "field 'mRootView'");
    target.mUserProfilePicture = Utils.findRequiredViewAsType(source, R.id.user_profile_picture, "field 'mUserProfilePicture'", ImageView.class);
    target.mUserEmail = Utils.findRequiredViewAsType(source, R.id.user_email, "field 'mUserEmail'", TextView.class);
    target.mUserDisplayName = Utils.findRequiredViewAsType(source, R.id.user_display_name, "field 'mUserDisplayName'", TextView.class);
    target.mUserPhoneNumber = Utils.findRequiredViewAsType(source, R.id.user_phone_number, "field 'mUserPhoneNumber'", TextView.class);
    target.mEnabledProviders = Utils.findRequiredViewAsType(source, R.id.user_enabled_providers, "field 'mEnabledProviders'", TextView.class);
    view = Utils.findRequiredView(source, R.id.sign_out, "method 'signOut'");
    view2131689741 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.signOut();
      }
    });
    view = Utils.findRequiredView(source, R.id.delete_account, "method 'deleteAccountClicked'");
    view2131689742 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.deleteAccountClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SignedInActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRootView = null;
    target.mUserProfilePicture = null;
    target.mUserEmail = null;
    target.mUserDisplayName = null;
    target.mUserPhoneNumber = null;
    target.mEnabledProviders = null;

    view2131689741.setOnClickListener(null);
    view2131689741 = null;
    view2131689742.setOnClickListener(null);
    view2131689742 = null;
  }
}
