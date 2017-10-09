package com.tool.utils.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

public class FileUtil {

	public static final String TAG = "FileUtil";

	public static int QR_WIDTH = 200;//350;
	public static int QR_HEIGHT = 200;//350;

	// public static String filePath;

	/**
	 * 获取目录名称
	 * 
	 * @param url
	 * @return FileName
	 */
	public static String getFileName(String url) {
		int lastIndexStart = url.lastIndexOf("/");
		if (lastIndexStart != -1) {
			return url.substring(lastIndexStart + 1, url.length());
		} else {
			return "shouzhangbao.apk";
		}
	}

	/**
	 * 检查SD卡是否存在
	 * 
	 * @return boolean
	 */
	public static boolean checkSDCard() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 保存目录目录到目录
	 * 
	 * @param context
	 * @return 目录保存的目录
	 */
	public static String createDir(Context context) {
		return createDir(context, null);
	}

	/**
	 * 保存目录目录到目录
	 * 
	 * @param context
	 * @return 目录保存的目录
	 */
	public static String createDir(Context context, String dirName) {
		if (dirName == null) {
			dirName = "";
		}
		String filePath;
		if (checkSDCard()) {
			filePath = Environment.getExternalStorageDirectory() + File.separator + ".shouzhangbao" + File.separator
					+ dirName;
		} else {
			filePath = context.getCacheDir().getAbsolutePath() + File.separator + ".shouzhangbao" + File.separator
					+ dirName;
		}

		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
			Log.e("file", "目录不存在  创建目录    ");
		} else {
			Log.e("file", "目录存在");
		}
		Log.i("file", "下载的路径：" + filePath);
		return filePath;
	}

	/***
	 * 转化文件大小
	 * 
	 * @param fileSize
	 * @return
	 */
	public static String formatFileSize(long fileSize) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileSize < 1024) {
			fileSizeString = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			fileSizeString = df.format((double) fileSize / 1024) + "K";
		} else if (fileSize < 1073741824) {
			fileSizeString = df.format((double) fileSize / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/***
	 * 转化文件大小
	 * 
	 * @return
	 */
	public static String formatFileSize(String filePath) {
		File file = new File(filePath);
		return formatFileSize(file.length());
	}

	public static String bitmapCompress(String fromPath, String toFileName) {
		String toFile = Environment.getExternalStorageDirectory() + File.separator + ".shouzhangbao" + File.separator
				+ "temppic" + File.separator + "pic";
		File file = new File(toFile);
		if (!file.exists()) {
			file.mkdirs();
		}
		File fromFile = new File(fromPath);
		long fileSize = fromFile.length();
		Log.i(TAG, "文件大小：" + fileSize + "   " + formatFileSize(fileSize));

		if (fileSize < 102400) // 当文件小于100K 则不需要压缩
		{
			if (fromPath.toLowerCase().endsWith(".png")) {
				toFile += toFileName + ".png";
			} else {
				toFile += toFileName + ".jpg";
			}
			copyFile(fromPath, toFile);
		} else {
			toFile += toFileName + ".jpg";
			fromatImage(fromPath, toFile);
		}
		return toFile;
	}

	public static void fromatImage(String fromPath, String toFile) {
		fromatImage(fromPath, toFile, 20);
	}

	public static void fromatImage(String fromPath, String toFile, int quality) {
		Log.i(TAG, "开始压缩图片" + fromPath + " to " + toFile);
		Bitmap bitmap = null;
		// BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inJustDecodeBounds = true;
		// bitmap = BitmapFactory.decodeFile(fromPath, options);
		// // Log.i(TAG,"初始化宽高："+bitmap.getWidth()+"x"+bitmap.getHeight());
		// options.inJustDecodeBounds = false;
		// int outSize = options.outHeight/200;
		// if(outSize <=0)
		// {
		// outSize = 1;
		// }
		// Log.i(TAG,
		// "options.outHeight="+options.outHeight+" outSize="+outSize);
		// options.inSampleSize = outSize;
		// bitmap = zoomImage(fromPath);

		// BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inJustDecodeBounds = true;
		// BitmapFactory.decodeFile(fromPath, options);
		// options.inSampleSize = computeSampleSize(options,-1, 128*128);
		// options.inJustDecodeBounds = false;

		FileOutputStream stream = null;
		try {
			bitmap = decodeBitmapFromPath(fromPath, 480, 800);
			// bitmap = BitmapFactory.decodeFile(fromPath,options);
			Log.i(TAG, "压缩后宽高：" + bitmap.getWidth() + "x" + bitmap.getHeight());
			stream = new FileOutputStream(toFile);
			bitmap.compress(CompressFormat.JPEG, quality, stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				stream = null;
			}
			if (bitmap != null) {
				try {
					bitmap.recycle();
				} catch (Exception e) {
					e.printStackTrace();
				}
				bitmap = null;
			}
		}
	}

	private static final int COPY_BUFF = 1024 * 1024 * 10;

	public static void copyFile(String fromPath, String toPath) {
		Log.i(TAG, "开始copy文件" + fromPath + " to " + toPath);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(fromPath);
			outputStream = new FileOutputStream(toPath);
			byte[] bytes = new byte[COPY_BUFF];
			int len = -1;
			while ((len = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				outputStream = null;
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				inputStream = null;
			}
		}
	}

	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

	public static Bitmap decodeBitmapFromPath(String pathName, int reqWidth, int reqHeight) {
		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(pathName, options);
	}

	public static Bitmap decodeBitmapFromPath(String pathName) {
		return decodeBitmapFromPath(pathName, 480, 800);
	}

	/***
	 * 图片的缩放方法
	 * 
	 * @param bgimage
	 *            ：源图片资源
	 * @param newWidth
	 *            ：缩放后宽度
	 * @param newHeight
	 *            ：缩放后高度
	 * @return
	 */
	public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
		// 获取这个图片的宽和高
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		// 计算宽高缩放率
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 缩放图片动作
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
		return bitmap;
	}

	/**
	 * @方法功能说明:
	 * 生成二维码图片,实际使用时要初始化sweepIV,不然会报空指针错误 @作者:饶正勇 @时间:2013-4-18上午11:14:16 @参数: @param
	 * url 要转换的地址或字符串,可以是中文 @return void @throws
	 */

	// 要转换的地址或字符串,可以是中文
	public static Bitmap createQRImage(String content) {
		Bitmap bitmap = null;
//		try {
//			// 判断URL合法性
//			if (StringUtils.checkNotNull(content)) {
//				Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//				hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//				// 图像数据转换，使用了矩阵转换
//				BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, QR_WIDTH,
//						QR_HEIGHT, hints);
//				int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
//				// 下面这里按照二维码的算法，逐个生成二维码的图片，
//				// 两个for循环是图片横列扫描的结果
//				for (int y = 0; y < QR_HEIGHT; y++) {
//					for (int x = 0; x < QR_WIDTH; x++) {
//						if (bitMatrix.get(x, y)) {
//							pixels[y * QR_WIDTH + x] = 0xff000000;
//						} else {
//							pixels[y * QR_WIDTH + x] = 0xffffffff;
//						}
//					}
//				}
//				// 生成二维码图片的格式，使用ARGB_8888
//				bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
//				bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
//
//			}
//
//		} catch (WriterException e) {
//			e.printStackTrace();
//		}
		return bitmap;
	}

}
