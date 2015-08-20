package uk.co.juliand.scannerinput;

import org.apache.cordova.*;
import org.json.*;

public class ScannerInputPlugin extends CordovaPlugin implements ScannerInputDialog.ScannerInputListener {
	
	public static final int KEYCODE_SCAN = 220;
	private static CallbackContext callback = null;
	private CharSequence code = null;
	
	@Override
	public void pluginInitialize() {
		webView.getView().setOnKeyListener(new android.view.View.OnKeyListener() {
			
			@Override
			public boolean onKey(android.view.View v, int keyCode, android.view.KeyEvent event) {
				if (keyCode == KEYCODE_SCAN && event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
					new uk.co.juliand.scannerinput.ScannerInputDialog(cordova.getActivity(), ScannerInputPlugin.this).show();
					return true;
				}
				return false;
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
    		PluginResult result = new PluginResult(PluginResult.Status.OK, input.toString());
    		result.setKeepCallback(true);
    		callback.sendPluginResult(result);
    	}
	}
    
}
