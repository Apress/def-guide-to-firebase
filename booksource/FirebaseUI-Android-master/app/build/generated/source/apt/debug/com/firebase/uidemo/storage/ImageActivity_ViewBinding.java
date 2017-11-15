// Generated code from Butter Knife. Do not modify!
package com.firebase.uidemo.storage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.firebase.uidemo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ImageActivity_ViewBinding implements Unbinder {
  private ImageActivity target;

  private View view2131689613;

  private View view2131689614;

  @UiThread
  public ImageActivity_ViewBinding(ImageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ImageActivity_ViewBinding(final ImageActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.button_choose_photo, "field 'mUploadButton' and method 'choosePhoto'");
    target.mUploadButton = Utils.castView(view, R.id.button_choose_photo, "field 'mUploadButton'", Button.class);
    view2131689613 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.choosePhoto();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_download_direct, "field 'mDownloadDirectButton' and method 'downloadDirect'");
    target.mDownloadDirectButton = Utils.castView(view, R.id.button_download_direct, "field 'mDownloadDirectButton'", Button.class);
    view2131689614 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.downloadDirect();
      }
    });
    target.mImageView = Utils.findRequiredViewAsType(source, R.id.first_image, "field 'mImageView'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ImageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mUploadButton = null;
    target.mDownloadDirectButton = null;
    target.mImageView = null;

    view2131689613.setOnClickListener(null);
    view2131689613 = null;
    view2131689614.setOnClickListener(null);
    view2131689614 = null;
  }
}
