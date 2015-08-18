package uk.co.juliand.scannerinput;

import org.apache.cordova.*;
import org.json.*;

public class ScannerInputPlugin extends CordovaPlugin implements ScannerInputDialog.ScannerInputListener {
	
	public static final int KEYCODE_SCAN = 220;
	private static ScannerInputPlugin instance = null;
	private static CallbackContext callback = null;
	private CharSequence code = null;
	
	@Override
	public void pluginInitialize() {
		instance = this;
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
    
    public static boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (keyCode == KEYCODE_SCAN && event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
			if (instance != null)
				new uk.co.juliand.scannerinput.ScannerInputDialog(instance.cordova.getActivity(), instance).show();
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
