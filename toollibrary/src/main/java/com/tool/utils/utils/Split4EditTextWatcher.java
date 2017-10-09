package com.tool.utils.utils;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 给EditText内容每间隔4个字符用 " " 隔开
 */
public class Split4EditTextWatcher implements TextWatcher {

	private EditText editText;

	public Split4EditTextWatcher(EditText editText) {
		this.editText = editText;
	}

	int beforeTextLength = 0;
	int onTextLength = 0;
	boolean isChanged = false;

	int location = 0;// 记录光标的位置
	private char[] tempChar;
	private StringBuffer buffer = new StringBuffer();
	int konggeNumberB = 0;

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		beforeTextLength = s.length();
		if (buffer.length() > 0) {
			buffer.delete(0, buffer.length());
		}
		konggeNumberB = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				konggeNumberB++;
			}
		}
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		onTextLength = s.length();
		buffer.append(s.toString());
		if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged) {
			isChanged = false;
			return;
		}
		isChanged = true;
	}

	@Override
	public void afterTextChanged(Editable s) {
		if (isChanged) {
			location = editText.getSelectionEnd();
			int index = 0;
			while (index < buffer.length()) {
				if (buffer.charAt(index) == ' ') {
					buffer.deleteCharAt(index);
				} else {
					index++;
				}
			}

			index = 0;
			int konggeNumberC = 0;
			while (index < buffer.length()) {
				if ((index == 4 || index == 9 || index == 14 || index == 19)) {
					buffer.insert(index, ' ');
					konggeNumberC++;
				}
				index++;
			}

			if (konggeNumberC > konggeNumberB) {
				location += (konggeNumberC - konggeNumberB);
			}

			tempChar = new char[buffer.length()];
			buffer.getChars(0, buffer.length(), tempChar, 0);
			String str = buffer.toString();

			editText.setText(str);
			Editable etable = editText.getText();
			if (location > etable.length()) {
				location = etable.length();
			} else if (location < 0) {
				location = 0;
			}
			Selection.setSelection(etable, location);
			isChanged = false;
		}
	}

}
