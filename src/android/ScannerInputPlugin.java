package uk.co.juliand.scannerinput;

import org.apache.cordova.*;
import org.json.*;

public class ScannerInputPlugin extends CordovaPlugin implements ScannerInputDialog.ScannerInputListener {
	private static CallbackContext callback = null;
	private ScannerInputDialog dialog = null;

	@Override
	public void pluginInitialize() {
		cordova.getActivity().runOnUiThread(new java.lang.Runnable() {
			public void run() {
				dialog = new ScannerInputDialog(cordova.getActivity(), ScannerInputPlugin.this);
				webView.getView().setOnKeyListener(dialog.getScanKeyListener());
				dialog.show();
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
