package com.tool.utils.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.toollibrary.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 自定义的dialog，用来展示
 * @author Administrator
 *
 */
public class CustomTimingProgressDialog extends ProgressDialog {

//	private Context context;
	private TextView dialog_tv_test;
	private CustomTimingCircle dialog_tv_cc;
	private Timer timer;
	private int timeValue = 60;
	private DialogDismissEvent dialogDismissEvent;

	public CustomTimingProgressDialog(Context context, DialogDismissEvent dialogDismissEvent) {
		super(context, R.style.LoadDialog);
//		this.context = context;
		this.dialogDismissEvent = dialogDismissEvent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_timing_progress_dialog);
		dialog_tv_test = (TextView) findViewById(R.id.dialog_tv_test);
		dialog_tv_cc = (CustomTimingCircle) findViewById(R.id.dialog_tv_cc);
		dialog_tv_cc.setMax(100);
		
		setCanceledOnTouchOutside(false);
		timer = new Timer();
		//计时器任务，延迟多少开始数，数的速度
		timer.schedule(timerTask, 0,1000);
		//这里用的是nineold的属性动画向下兼容包
		ValueAnimator animator = ValueAnimator.ofInt(100,0);
		animator.setDuration(timeValue*1000);
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int animatedValue = (Integer) animation.getAnimatedValue();
				dialog_tv_cc.setProgress(animatedValue);
			}
		});
		animator.start();
	}
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
			if (msg.what > 0) {
				dialog_tv_test.setText(msg.what+ "\n秒");
			} else {
				dialogDismiss();
			}
		}
	};
	
	TimerTask timerTask = new TimerTask() {
		int second = timeValue;
		@Override
		public void run() {
			Message msg = new Message();
			msg.what = second;
			handler.sendMessage(msg);
			second--;
//			Log.e("second", "" + second);
		}
	};
	/**
	 * 强制取消掉dialog，同样的计时器也同时取消掉
	 *  看具体情况来判断是否打开新的界面
	 */
	public void dialogDismiss() {
		this.dismiss();
		timer.cancel();
		dialogDismissEvent.dialogDismiss();
	}

	public interface DialogDismissEvent {
		void dialogDismiss();
	}
}
