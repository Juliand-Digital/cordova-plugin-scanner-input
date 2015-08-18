cordova.addWindowEventHandler("scannerinput");
cordova.exec(
	function onScannerInput(input) {
		cordova.fireWindowEvent("scannerinput", {
			input: input
		});
	},
	null,
	"ScannerInputPlugin",
	"register",
	[]
);
