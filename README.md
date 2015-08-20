# cordova-plugin-scanner-input

This is an *Apache Cordova* plugin which allows a web app to intercept text coming non-standard APIs.
It creates an invisible temporary overlay when the device's trigger key is pressed.
Inside it, there is a standard `EditText` component which can receive input from code which erroneously looks for a focused input whose text to set.

Such code has been encountered on a device with a built-in bar code scanner.

## Usage

1. Install the plugin through the `cordova plugin add` command.
2. Listen for the `scannerinput` event on `window`:
```javascript
window.addEventListener('scannerinput', app.onScannerInput, false);
```

## Notes

The plugin can easily be modified to support multiple trigger keys, right now it expects `keyCode` to be `220`.
Do not remove the seemingly useless `clobber` inside `js-module` in `plugin.xml` because the plugin's JavaScript file is not loaded otherwise.
