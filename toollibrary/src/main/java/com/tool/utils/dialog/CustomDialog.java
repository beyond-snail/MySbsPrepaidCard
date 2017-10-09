package com.tool.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tool.utils.utils.StringUtils;
import com.toollibrary.R;

/**
 * 自定义对话框
 * 
 * @author Ivan
 */
public class CustomDialog extends Dialog {

	public CustomDialog(Context context) {
		super(context);
	}

	public CustomDialog(Context context, int theme) {
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
		private int line = 1;
		private OnClickListener positiveButtonClickListener;
		private OnClickListener negativeButtonClickListener;
		private boolean iscancel_back = true;// 回车键能否取消
		private boolean iscancel_out = false;// 点击对话框外部能否取消
		private boolean isHtml = false;// message是否为html格式显示

		// 列表
		private OnClickListener itemClickListener;
		// 右上角关闭按钮监听
		private OnClickListener imageViewClickListener;
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
		 * @param
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
		public Builder setLine(int line) {
			this.line = line;
			return this;
		}

		/**
		 * Set the Dialog items
		 * 
		 * @param
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
		public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
			this.positiveButtonText = (String) context.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
			this.negativeButtonText = (String) context.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}

		public Builder setImageViewButton(OnClickListener listener) {
			this.imageViewClickListener = listener;
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
		 * 消息内容 自定义布局（插入） 分割横线 提示（隐藏） 确定按钮 取消按钮
		 */
		public CustomDialog create() {
			// instantiate the dialog with the custom Theme
			final CustomDialog dialog = new CustomDialog(context, mTheme);
			dialog.setContentView(R.layout.view_customdialog);
			// set the dialog title 设置标题即信息内容
			((TextView) dialog.findViewById(R.id.tv_title)).setText(title);

			// 设置自定义布局
			if (contentView != null) {
				// if no message set
				// add the contentView to the dialog body
				((ViewGroup) dialog.findViewById(R.id.content)).removeAllViews();
				((ViewGroup) dialog.findViewById(R.id.content)).addView(contentView,
						new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			}

			if (mView != null) {
				((ViewGroup) dialog.findViewById(R.id.content)).removeAllViews();
				((ViewGroup) dialog.findViewById(R.id.content)).addView(mView,
						new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			}

			// set the content message 设置提示信息
			if (message != null && message.length() > 0) {
				if (isHtml) {
					((TextView) dialog.findViewById(R.id.tv_message)).setText(Html.fromHtml(message));
				} else {
					((TextView) dialog.findViewById(R.id.tv_message)).setText(message);
				}

				((TextView) dialog.findViewById(R.id.tv_message)).setVisibility(View.VISIBLE);
				((View) dialog.findViewById(R.id.hint)).setVisibility(View.VISIBLE);
			}

			// set the confirm button 设置 确认按钮 和取消按钮
			if (positiveButtonText != null && positiveButtonText.length() > 0) {
				((Button) dialog.findViewById(R.id.positiveButton)).setText(positiveButtonText);
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
				((Button) dialog.findViewById(R.id.negativeButton)).setText(negativeButtonText);
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

			// 设置分割线的位置
			if (line == 0) {
				dialog.findViewById(R.id.line_1).setVisibility(View.GONE);
				dialog.findViewById(R.id.line_2).setVisibility(View.GONE);
			} else if (line == 1) {
				dialog.findViewById(R.id.line_1).setVisibility(View.VISIBLE);
				dialog.findViewById(R.id.line_2).setVisibility(View.GONE);
			} else {
				dialog.findViewById(R.id.line_1).setVisibility(View.GONE);
				dialog.findViewById(R.id.line_2).setVisibility(View.VISIBLE);
			}

			// 列表
			if (items != null && items.length > 0) {
				if (StringUtils.isBlank(title)) {// 标题为空，则隐藏标题
					dialog.findViewById(R.id.rl_title).setVisibility(View.GONE);
				} else {
					dialog.findViewById(R.id.rl_title).setVisibility(View.VISIBLE);
				}
				ListView listview = (ListView) dialog.findViewById(R.id.listview);

				listview.setVisibility(View.VISIBLE);
				ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(context, R.layout.spinner,
						R.id.tv_user_gender, items);
				listview.setAdapter(adapter);
				listview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// call the item click
						if (itemClickListener != null) {
							itemClickListener.onClick(dialog, position);
						}
					}
				});
				((View) dialog.findViewById(R.id.hint)).setVisibility(View.VISIBLE);

				// 高度处理 TODO bug待处理 注释
				// ListAdapter listAdapter = listview.getAdapter();
				// if (listAdapter != null) {
				// int totalHeight = 0;
				// for (int i = 0; i < listAdapter.getCount(); i++) {
				// View listItem = listAdapter.getView(i, null, listview);
				// listItem.measure(0, 0);
				// totalHeight += listItem.getMeasuredHeight();
				// if (totalHeight > ScreenUtils.getScreenHeight(context) *
				// 0.5f) {
				// break;
				// }
				// }
				// ViewGroup.LayoutParams params = listview.getLayoutParams();
				// params.height = totalHeight + (listview.getDividerHeight() *
				// (listAdapter.getCount() - 1));
				// listview.setLayoutParams(params);
				// }
			}

			ImageView iv_close = ((ImageView) dialog.findViewById(R.id.iv_close));
			iv_close.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (imageViewClickListener != null) {
						imageViewClickListener.onClick(dialog, 0);
					} else {
						dialog.dismiss();
					}

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
	public CustomDialog setWindow() {
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
