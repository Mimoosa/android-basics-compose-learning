package fi.monami.bluetoothscanner

import android.Manifest
import android.bluetooth.BluetoothAdapter

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel





class MainActivity : ComponentActivity() {

    private val mBluetoothAdapter: BluetoothAdapter? by lazy {
        BluetoothAdapter.getDefaultAdapter()
    }

    private val scanner by lazy {
        mBluetoothAdapter?.bluetoothLeScanner
    }

    @androidx.annotation.RequiresPermission(android.Manifest.permission.BLUETOOTH_SCAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        hasPermissions()

        setContent {
            val model: BluetoothViewModel = viewModel()

            Column(Modifier.safeDrawingPadding()) {
                Button(onClick = {
                    scanner?.let  { model.scanDevices(it) }
                }) {
                    Text(if (model.scanning.value) "Scanning..." else "Scan BLE Devices")
                }

                LazyColumn {
                    items(model.results) { result ->

                        val name =
                            if (checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT)
                                == PackageManager.PERMISSION_GRANTED
                            ) {
                                result.device.name ?: "Unknown"
                            } else {
                                "Unknown"
                            }

                        Text(
                            text = "$name  ${result.device.address}  RSSI: ${result.rssi}",
                            color = if (result.isConnectable) Color.Black else Color.Gray
                        )
                    }
                }


            }
        }
    }


    private fun hasPermissions(): Boolean {
        if (mBluetoothAdapter == null || !mBluetoothAdapter!!.isEnabled) {
            Log.d("DBG", "No Bluetooth LE capability")
            return false
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val needed = arrayOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
            )

            val missing = needed.any {
                checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED
            }

            if (missing) {
                ActivityCompat.requestPermissions(this, needed, 1)
                return false
            }

        } else {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
                return false
            }
        }

        return true
    }
}
