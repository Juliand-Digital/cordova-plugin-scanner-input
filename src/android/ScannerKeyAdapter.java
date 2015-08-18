package uk.co.juliand.scannerinput;

import android.view.KeyEvent;
import android.view.View;

public class ScannerKeyAdapter implements View.OnKeyListener, ScannerInputDialog.ScannerInputListener {
	
	public static final int KEYCODE_SCAN = 220;

	@Override
	public final boolean onKey(View view, int keyCode, KeyEvent event) {
		if (keyCode == KEYCODE_SCAN && event.getAction() == KeyEvent.ACTION_DOWN)
			new ScannerInputDialog(view.getContext(), this).show();
		return false;
	}

	@Override
	public void onScannerInput(CharSequence input) {
	}

}
