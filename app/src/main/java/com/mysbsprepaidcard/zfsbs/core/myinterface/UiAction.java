package com.mysbsprepaidcard.zfsbs.core.myinterface;

import android.app.Activity;
import android.os.Bundle;

public interface UiAction {
	public void UiAction(Activity context, Class<?> cls, boolean flag);
	public void UiAction(Activity context, Class<?> cls, Bundle bundle, boolean flag);
	public void UiResultAction(Activity context, Class<?> cls, Bundle bundle, int requestCode);
}
