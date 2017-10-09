package com.tool.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.toollibrary.R;

/**
 * 自定义对话框
 * 
 * @author Ivan
 * 
 */
public class CommonCustomDialog extends Dialog {

	public CommonCustomDialog(Context context) {
		super(context);
	}

	public CommonCustomDialog(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder {
		private Context context;
		private int mTheme = R.style.MyDialogStyle;
		private String title;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private View contentView;
		private View mView;
		private int positive_bg = R.drawable.btn_purple_selector;
		private int negative_bg = R.drawable.btn_purple_cancel_selector;
		private OnClickListener positiveButtonClickListener;
		private OnClickListener negativeButtonClickListener;
		private boolean iscancel_back = true;// 回车键能否取消
		private boolean iscancel_out = false;// 点击对话框外部能否取消
		private boolean isHtml = false;// message是否为html格式显示

		// 列表
		private OnClickListener itemClickListener;
		private CharSequence[] items = null;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder(Context context, int theme) {
			this.context = context;
			this.mTheme = theme;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder setHTMLTEXT(boolean isHtml) {
			this.isHtml = isHtml;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		public Builder setCancelable(boolean iscancel) {
			this.iscancel_back = iscancel;
			return this;
		}

		public Builder setCanceledOnTouchOutside(boolean iscancel) {
			this.iscancel_out = iscancel;
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 * 
		 * @param title
		 * @return
		 */

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		public Builder setView(View v) {
			this.mView = v;
			return this;
		}

		/**
		 * 0 线 隐藏 1 线 自定义布局 线在上方 2 线 自定义布局 线在下方
		 */
		public Builder setNegativeBg(int id) {
			this.negative_bg = id;
			return this;
		}

		public Builder setPositiveBg(int id) {
			this.positive_bg = id;
			return this;
		}

		/**
		 * Set the Dialog items
		 * 
		 * @param title
		 * @return
		 */
		public Builder setItems(CharSequence[] items, OnClickListener itemClickListener) {
			this.items = items;
			this.itemClickListener = itemClickListener;
			return this;
		}

		/**
		 * Set the positive button resource and it's listener
		 * 
		 * @param positiveButtonText
		 * @return
		 */
		public Builder setPositiveButton(int positiveButtonText, int bgId, OnClickListener listener) {
			this.positiveButtonText = (String) context.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			this.positive_bg = bgId;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText, int bgId,
				OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			this.positive_bg = bgId;
			return this;
		}

		public Builder setNegativeButton(int negativeButtonText, int bgId, OnClickListener listener) {
			this.negativeButtonText = (String) context.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			this.negative_bg = bgId;
			return this;
		}

		public Builder setNegativeButton(String negativeButtonText, int bgId,
				OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			this.negative_bg = bgId;
			return this;
		}

		public Builder setCancel(boolean iscancel) {
			this.iscancel_back = iscancel;
			return this;
		}

		public Builder setOutsideCancel(boolean OutsideCancel) {
			this.iscancel_out = OutsideCancel;
			return this;
		}

		/**
		 * 消息内容
		 * 
		 * 自定义布局（插入）
		 * 
		 * 分割横线
		 * 
		 * 提示（隐藏）
		 * 
		 * 确定按钮 取消按钮
		 * 
		 */
		public CommonCustomDialog create() {
			// instantiate the dialog with the custom Theme
			final CommonCustomDialog dialog = new CommonCustomDialog(context, mTheme);
			dialog.setContentView(R.layout.dialog_common_custom);
			// set the dialog title 设置标题即信息内容
			((TextView) dialog.findViewById(R.id.tv_title)).setText(title);

			// set the confirm button 设置 确认按钮 和取消按钮
			if (positiveButtonText != null && positiveButtonText.length() > 0) {
				Button positiveButton = (Button) dialog.findViewById(R.id.positiveButton);
				positiveButton.setText(positiveButtonText);
				positiveButton.setBackgroundResource(positive_bg);
				if (positiveButtonClickListener != null) {
					((Button) dialog.findViewById(R.id.positiveButton)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
						}
					});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				dialog.findViewById(R.id.positiveButton).setVisibility(View.GONE);
			}
			// set the cancel button
			if (negativeButtonText != null && negativeButtonText.length() > 0) {
				Button positiveButton = (Button) dialog.findViewById(R.id.negativeButton);
				positiveButton.setText(negativeButtonText);
				positiveButton.setBackgroundResource(negative_bg);
				if (negativeButtonClickListener != null) {
					((Button) dialog.findViewById(R.id.negativeButton)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
						}
					});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				dialog.findViewById(R.id.negativeButton).setVisibility(View.GONE);
			}
			((ImageView) dialog.findViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.dismiss();
					negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
				}
			});
			dialog.setCancelable(iscancel_back);
			dialog.setCanceledOnTouchOutside(iscancel_out);
			dialog.setWindow();

			return dialog;
		}

	}

	/**
	 * 设置自适应屏幕
	 * 
	 * @return
	 */
	public CommonCustomDialog setWindow() {
		// 适应屏幕
		DisplayMetrics mDisplayMetrics = this.getContext().getResources().getDisplayMetrics();
		if (mDisplayMetrics.widthPixels < mDisplayMetrics.heightPixels) {
			int paddWidth = mDisplayMetrics.widthPixels / 6;
			getWindow().setLayout(mDisplayMetrics.widthPixels - paddWidth, LayoutParams.WRAP_CONTENT);
		} else {
			int paddWidth = mDisplayMetrics.widthPixels / 2;
			getWindow().setLayout(mDisplayMetrics.widthPixels - paddWidth, LayoutParams.WRAP_CONTENT);
		}
		return this;
	}

}
