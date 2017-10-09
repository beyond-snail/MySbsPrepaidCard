package com.tool.utils.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

import com.tool.utils.dialog.CommonCustomDialog;
import com.tool.utils.dialog.CustomDialog;
import com.tool.utils.wheel.AreaPickerView;
import com.toollibrary.R;

public class AlertUtils {

	/**
	 * 显示消息+提示
	 */
	public static void alert(String msg, String hint, Context ctx, OnClickListener listener) {
		alert(msg, hint, ctx, "确定", null, listener, null, null, false, false, 1);
	}

	/**
	 * 仅仅显示消息
	 */
	public static void alert(String msg, Context ctx) {
		alert(msg, null, ctx, "确定", null, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}, null, null, true, true, 1);
	}

	/**
	 * 显示消息+提示
	 */
	public static void alert(String msg, String hint, Context ctx) {
		alert(msg, hint, ctx, "确定", null, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}, null, null, true, true, 1);
	}

	/**
	 * 显示消息 只能点击按钮取消 需用户确认
	 */
	public static void alertTips(String msg, Context ctx) {
		alert(msg, null, ctx, "确定", null, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}, null, null, false, false, 1);
	}

	/**
	 * 显示消息 确定事件有其他操作
	 */
	public static void alert(String msg, Context ctx, String positiveText, OnClickListener listerner) {
		alert(msg, null, ctx, positiveText, null, listerner, null, null, true, true, 1);
	}

	public static void alert(String msg, Context ctx, OnClickListener listerner) {
		alert("温馨提示", msg, ctx, "我知道了", null, listerner, null, true, false);
	}

	/**
	 * 显示消息 确保用户知道 例如删除车辆 失败了 需用户点击按钮确认 才消失
	 */
	public static void alertTips(String msg, Context ctx, OnClickListener listerner) {
		alert("温馨提示", msg, ctx, "我知道了", null, listerner, null, true, false);
	}

	/**
	 * 显示消息 hint可为null 自定义按钮名称
	 */
	public static void alert(String title, String hint, Context ctx, String positiveText, String negativeText,
			OnClickListener positive, OnClickListener negative) {
		if (negative == null) {
			negative = new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();

				}
			};
		}
		alert(title, hint, ctx, positiveText, negativeText, positive, negative, null, true, true, 1);

	}

	/**
	 * 显示消息 hint为null 自定义按钮名称
	 */
	public static void alert(String title, Context ctx, String positiveText, String negativeText,
			OnClickListener positive, OnClickListener negative) {
		if (negative == null) {
			negative = new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();

				}
			};
		}
		alert(title, null, ctx, positiveText, negativeText, positive, negative, null, true, true, 1);

	}



	/**
	 * 设置支付密码
	 * 
	 * @param ctx
	 * @param positive
	 * @param view
	 */
	public static void alertSetPassword(final Context ctx, String title, String positiveText,
			OnClickListener positive, View view) {
		alert(title, null, ctx, positiveText, null, positive, null, view, false, false, 0);
	}

	public static void alertSetPayPassword(final Context ctx, String title, String positiveText,
			OnClickListener positive, OnClickListener imageViewButton, View view) {
		alert2(title, null, ctx, positiveText, null, positive, null, imageViewButton, view, false, false, 0);
	}

	/**
	 * 设置支付密码
	 * 
	 * @param ctx
	 * @param positive
	 * @param view
	 */
	public static void alertSetPassword(final Context ctx, String title, String hint, String positiveText,
			OnClickListener positive, View view) {
		alert(title, hint, ctx, positiveText, null, positive, null, view, false, false, 0);
	}

	/**
	 * 显示消息 按钮为 确定 取消
	 */
	public static void alert(String title, String hint, Context ctx, OnClickListener positive,
			OnClickListener negative) {
		if (negative == null) {
			negative = new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();

				}
			};
		}
		alert(title, hint, ctx, "确定", "取消", positive, negative, null, true, true, 1);

	}

	private static CustomDialog Dialog;

	// 最全的
	public static void alert(String title, String hint, Context ctx, String positiveText, String negativeText,
			OnClickListener positive, OnClickListener negative, View view, boolean a,
			boolean b, int line) {

		if (Dialog != null) {
			Dialog.dismiss();
			Dialog = null;
		}

		CustomDialog.Builder builder = new CustomDialog.Builder(ctx, R.style.MyDialogStyleBottom);
		builder.setTitle(title);// 消息内容
		builder.setMessage(hint);// 提示补充
		builder.setContentView(view);// 自定义布局
		builder.setCancelable(a);
		builder.setCanceledOnTouchOutside(b);
		builder.setLine(line);// 分割横线所处位置 在自定义布局上下或隐藏 0隐藏 1线在上方 2线在下方

		builder.setPositiveButton(positiveText, positive);
		builder.setNegativeButton(negativeText, negative);
		Dialog = builder.create();

		Dialog.show();

	}

	// 带有设置右上角 取消按钮的 最全框
	public static void alert2(String title, String hint, Context ctx, String positiveText, String negativeText,
			OnClickListener positive, OnClickListener negative,
			OnClickListener ImageViewButton, View view, boolean a, boolean b, int line) {

		if (Dialog != null) {
			Dialog.dismiss();
			Dialog = null;
		}

		CustomDialog.Builder builder = new CustomDialog.Builder(ctx, R.style.MyDialogStyleBottom);
		builder.setTitle(title);// 消息内容
		builder.setMessage(hint);// 提示补充
		builder.setContentView(view);// 自定义布局
		builder.setCancelable(a);
		builder.setCanceledOnTouchOutside(b);
		builder.setLine(line);// 分割横线所处位置 在自定义布局上下或隐藏 0隐藏 1线在上方 2线在下方
		builder.setImageViewButton(ImageViewButton);
		builder.setPositiveButton(positiveText, positive);
		builder.setNegativeButton(negativeText, negative);
		Dialog = builder.create();

		Dialog.show();

	}

	public static void alert(String title, String msg, Context ctx, String positiveText, String negativeText,
			OnClickListener positive, OnClickListener negative, boolean cancelable,
			boolean touchOutside) {
		CustomDialog.Builder builder = new CustomDialog.Builder(ctx);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(positiveText, positive);

		if (!StringUtils.isBlank(negativeText)) {
			if (negative == null) {
				negative = new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				};
			}
			builder.setNegativeButton(negativeText, negative);
		}

		builder.setCancel(cancelable);
		builder.setOutsideCancel(touchOutside);
		builder.create().show();
	}

	// html 显示
	public static void alertHtml(String title, String hint, Context ctx) {
		CustomDialog.Builder builder = new CustomDialog.Builder(ctx, R.style.MyDialogStyleBottom);
		builder.setTitle(title);// 消息内容
		builder.setMessage(hint);// 提示补充
		builder.setContentView(null);// 自定义布局
		builder.setCancelable(true);
		builder.setHTMLTEXT(true);
		builder.setCanceledOnTouchOutside(true);
		builder.setLine(1);// 分割横线所处位置 在自定义布局上下或隐藏 0隐藏 1线在上方 2线在下方

		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});
		builder.setNegativeButton(null, null);

		builder.create().show();

	}

	// TODO 带listview的对话框
	/**
	 * 有列表的弹出框
	 * 
	 * @param ctx
	 * @param items
	 *            列表项
	 * @param title
	 * @param itemClickListener
	 *            列表项点击事件 重点不可忽略dialog.dismiss()
	 */
	public static void selectItems(Context ctx, String[] items, String title, OnClickListener itemClickListener) {
		CustomDialog.Builder builder = new CustomDialog.Builder(ctx, R.style.MyDialogStyleBottom);
		builder.setTitle(title);
		builder.setItems(items, itemClickListener);
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});

		builder.create().show();
	}

	// TODO 不带取消按钮的 带listview的对话框
	/**
	 * 有列表的弹出框
	 * 
	 * @param ctx
	 * @param items
	 *            列表项
	 * @param title
	 * @param itemClickListener
	 *            列表项点击事件 重点不可忽略dialog.dismiss()
	 */
	public static void selectItemsNoNegativeButton(Context ctx, String[] items, String title,
			OnClickListener itemClickListener) {
		CustomDialog.Builder builder = new CustomDialog.Builder(ctx, R.style.MyDialogStyleBottom);
		builder.setTitle(title);
		builder.setItems(items, itemClickListener);
		builder.create().show();
	}

	// 带地址日期的对话框
	/**
	 * 省市区选择下拉
	 * 
	 * @param ctx
	 * @param items
	 *            列表项
	 * @param title
	 * @param itemClickListener
	 *            列表项点击事件
	 */
	public static void selectDate(Context ctx, AreaPickerView view, OnClickListener itemClickListener) {

		CustomDialog.Builder builder = new CustomDialog.Builder(ctx, R.style.MyDialogStyleBottom);
		builder.setTitle("请选择日期");
		builder.setContentView(view);
		builder.setLine(0);
		builder.setPositiveButton("设定", itemClickListener);
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	/**
	 * 带确定，取消的提示框
	 * 
	 * @param title
	 *            提示头
	 * @param msg
	 *            标题
	 * @param ctx
	 *            context
	 * @param positive
	 *            确定事件
	 * @param positives
	 *            取消事件
	 */
	public static void confirm(String title, String msg, Context ctx, OnClickListener positive,
			OnClickListener negative) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setCancelable(false);
		builder.setPositiveButton("购买设备", positive);
		builder.setNegativeButton("模拟演示", negative);
		builder.show().setCanceledOnTouchOutside(false);
	}

	/**
	 * 带确定，不提示的提示框
	 * 
	 * @param title
	 *            提示头
	 * @param msg
	 *            标题
	 * @param ctx
	 *            context
	 * @param positive
	 *            确定事件
	 * @param positives
	 *            取消事件
	 */
	public static void alertWithTips(String title, String msg, Context ctx, OnClickListener positive,
			OnClickListener negative) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setCancelable(false);
		builder.setPositiveButton("确定", positive);
		builder.setNegativeButton("不再提示", negative);
		builder.show().setCanceledOnTouchOutside(false);
	}

	/**
	 * 有列表的弹出框
	 * 
	 * @param ctx
	 * @param items
	 *            列表项
	 * @param title
	 * @param itemClickListener
	 *            列表项点击事件
	 */
	// public static void alert(Context ctx, String[] items, String title,
	// DialogInterface.OnClickListener itemClickListener) {
	// CustomDialog.Builder builder = new CustomDialog.Builder(ctx,
	// R.style.MyDialogStyleBottom);
	// builder.setTitle(title);
	// builder.setItems(items, itemClickListener);
	// builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
	//
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// dialog.dismiss();
	//
	// }
	// });
	//
	// builder.create().show();
	// }

	/**
	 * 编辑对话框
	 * 
	 * @param ctx
	 * @param title
	 * @param positive
	 */
	public static void editAlert(Context ctx, String title, View view, OnClickListener positive) {

		CustomDialog.Builder builder = new CustomDialog.Builder(ctx, R.style.MyDialogStyleBottom);
		builder.setTitle(title);
		builder.setView(view);
		builder.setLine(0);
		builder.setPositiveButton("确定", positive);
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});
		builder.create().show();
	}

	/**
	 * 自定义布局对话框
	 * 
	 * @param ctx
	 * @param title
	 * @param positive
	 */
	public static void AlertView(Context ctx, String title, View view, String positiveText, String negativeText,
			OnClickListener positiveListener, OnClickListener negativeListener) {

		CustomDialog.Builder builder = new CustomDialog.Builder(ctx, R.style.MyDialogStyleBottom);
		builder.setTitle(title);
		builder.setView(view);
		builder.setLine(0);
		builder.setPositiveButton(positiveText, positiveListener);
		builder.setNegativeButton(negativeText, negativeListener);
		builder.create().show();
	}

	/**
	 * 列表对话框
	 * 
	 * @param ctx
	 * @param title
	 * @param positive
	 */
	public static void listAlert(Context ctx, String title, View view, OnClickListener positive) {

		CustomDialog.Builder builder = new CustomDialog.Builder(ctx, R.style.MyDialogStyleBottom);
		builder.setTitle(title);
		builder.setView(view);
		builder.setPositiveButton("确定", positive);
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});
		builder.create().show();
	}
	
	
	/**
	 * 列表对话框,没有按钮的
	 * 
	 * @param ctx
	 * @param title
	 * @param positive
	 */
	public static void listAlertNoButton(Context ctx, String title, View view) {

		CustomDialog.Builder builder = new CustomDialog.Builder(ctx, R.style.MyDialogStyleBottom);
		builder.setTitle(title);
		builder.setView(view);
		builder.setLine(0);
//		builder.setPositiveButton("确定", positive);
//		builder.setNegativeButton("取消", new OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//
//			}
//		});
		builder.create().show();
	}

	/**
	 * 按钮为附近汽修，橙色的客服援助
	 * 
	 * @param context
	 * @param title
	 * @param positiveText
	 * @param negativeText
	 * @param positiveListener
	 * @param negativeListener
	 */
	public static void alert(final Context context, String title, String positiveText, String negativeText,
			OnClickListener positiveListener, OnClickListener negativeListener) {
		CommonCustomDialog.Builder builder = new CommonCustomDialog.Builder(context);
		builder.setTitle(title);
		builder.setPositiveButton(positiveText, R.drawable.btn_purple_selector, positiveListener);
		builder.setNegativeButton(negativeText, R.drawable.btn_orange_selector, negativeListener);
		Dialog dialog = builder.create();
		dialog.show();
	}

	/**
	 * 按钮为绿色、灰色的常用对话框 点击关闭按钮调用取消事件（如：取消回到上一页）
	 * 
	 * @param context
	 * @param title
	 * @param positiveText
	 * @param negativeText
	 * @param positiveListener
	 * @param negativeListener
	 */
	public static void alertCommon(final Context context, String title, String positiveText, String negativeText,
			OnClickListener positiveListener, OnClickListener negativeListener) {
		CommonCustomDialog.Builder builder = new CommonCustomDialog.Builder(context);
		builder.setTitle(title);
		builder.setPositiveButton(positiveText, R.drawable.btn_purple_selector, positiveListener);
		builder.setNegativeButton(negativeText, R.drawable.btn_purple_cancel_selector, negativeListener);
		Dialog dialog = builder.create();
		dialog.show();
	}

	/**
	 * 分享
	 * 
	 * @param context
	 * @param title
	 * @param positiveText
	 * @param negativeText
	 * @param positiveListener
	 * @param negativeListener
	 */
	public static void alertShare(final Context context, String title, String positiveText, String negativeText,
			OnClickListener positiveListener, OnClickListener negativeListener) {
		CommonCustomDialog.Builder builder = new CommonCustomDialog.Builder(context);
		builder.setTitle(title);
		builder.setPositiveButton(positiveText, R.drawable.btn_purple_selector, positiveListener);
		builder.setNegativeButton(negativeText, R.drawable.btn_purple_cancel_selector, negativeListener);
		Dialog dialog = builder.create();
		dialog.show();
	}
}
