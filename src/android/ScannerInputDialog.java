package uk.co.juliand.scannerinput;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class ScannerInputDialog extends Dialog {
	public static interface ScannerInputListener {
		void onScannerInput(CharSequence input);
	}

	public static final int KEYCODE_SCAN = 220;
	private EditText edit;

	public ScannerInputDialog(Context context, final ScannerInputListener listener) {
		super(context, android.R.style.Theme_Translucent_NoTitleBar);
		getWindow().setLayout(0, 0);
		setContentView(edit = new EditText(context));
		edit.setInputType(InputType.TYPE_NULL);
		edit.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (listener != null) {
					if (count > 0 && s.charAt(start) != 0)
						s = s.subSequence(start, start + count);
					else
						s = null;
					listener.onScannerInput(s);
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

	public View.OnKeyListener getScanKeyListener() {
		return new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KEYCODE_SCAN && event.getAction() == KeyEvent.ACTION_DOWN) {
					edit.requestFocus();
					return true;
				}
				return false;
			}
		};
	}
}
