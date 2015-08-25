package uk.co.juliand.scannerinput;

import android.view.ViewGroup;

import org.apache.cordova.*;
import org.json.*;

public class ScannerInputPlugin extends CordovaPlugin implements ScannerInputView.ScannerInputListener {
	private static CallbackContext callback = null;

	@Override
	public void pluginInitialize() {
		cordova.getActivity().runOnUiThread(new java.lang.Runnable() {
			public void run() {
				ScannerInputView view = new ScannerInputView(cordova.getActivity(), ScannerInputPlugin.this);
				((ViewGroup) webView.getView()).addView(view);
				webView.getView().setOnKeyListener(view.getScanKeyListener());
			}
		});
	}

	@Override
	public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
		if (action.equals("register")) {
			callback = callbackContext;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onScannerInput(CharSequence input) {
		if (callback != null) {
			PluginResult result = new PluginResult(PluginResult.Status.OK, input != null ? input.toString() : null);
			result.setKeepCallback(true);
			callback.sendPluginResult(result);
		}
	}
}
