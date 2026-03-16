package fi.monami.bluetoothscanner

import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BluetoothViewModel : ViewModel() {

    val results = mutableStateListOf<ScanResult>()
    val scanning = mutableStateOf(false)

    private val mResults = HashMap<String, ScanResult>()

    private val leScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            mResults[result.device.address] = result
            Log.d("DBG", "device found: ${result.device.address}")
        }
    }
    @androidx.annotation.RequiresPermission(android.Manifest.permission.BLUETOOTH_SCAN)
    fun scanDevices(scanner: BluetoothLeScanner) {
        viewModelScope.launch(Dispatchers.IO) {
            scanning.value = true
            mResults.clear()
            results.clear()

            val settings = ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .build()

            scanner.startScan(null, settings, leScanCallback)

            delay(5000)

            scanner.stopScan(leScanCallback)

            results.addAll(mResults.values)
            scanning.value = false
        }
    }
}
