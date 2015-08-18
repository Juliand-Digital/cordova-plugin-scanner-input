package uk.co.juliand.scannerinput;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class ScannerInputDialog extends Dialog {
	public static interface ScannerInputListener {
		void onScannerInput(CharSequence input);
	}

	private EditText edit;

	public ScannerInputDialog(Context context, final ScannerInputListener listener) {
		super(context, android.R.style.Theme_Translucent_NoTitleBar);
		edit = new EditText(context);
		edit.setTextColor(Color.TRANSPARENT);
		edit.setBackgroundColor(Color.TRANSPARENT);
		setContentView(edit);
		edit.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (listener != null && count > 0 && s.charAt(start) != 0) {
					listener.onScannerInput(s.subSequence(start, start + count));
					dismiss();
				} else {
					cancel();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	@Override
	public void show() {
		super.show();
		edit.requestFocus();
	}
}
