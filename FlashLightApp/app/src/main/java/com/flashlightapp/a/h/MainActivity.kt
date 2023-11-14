package com.flashlightapp.a.h

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flashlightapp.a.h.ui.theme.FlashLightAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var cameraManager =
            applicationContext.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        var cameraID = cameraManager.cameraIdList[0]

        setContent {
            FlashLightAppTheme {
                // A surface container using the 'background' color from the theme

                    FlashLight(cameraManager, cameraID)
            }
        }
    }

    override fun onBackPressed() {
        var cameraManager =
            applicationContext.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        var cameraID = cameraManager.cameraIdList[0]
        cameraManager.setTorchMode(cameraID, false)
        finishAffinity()
        super.onBackPressed()
    }
}


@Composable
fun FlashLight(cameraManager: CameraManager, cameraID: String) {
    var context = LocalContext.current
    var isFlashOn = remember {
        mutableStateOf(false)
    }
    var flashOnOff = remember {
        mutableStateOf("Off")
    }
    if (cameraID != null) {
        isFlashOn.value = true
        flashOnOff.value = "On"
    }else{
        isFlashOn.value = false
        flashOnOff.value = "Off"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "FlashLight",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff3BA424)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Divider(modifier = Modifier.padding(20.dp))
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
                        if (isFlashOn.value) {
                            cameraManager.setTorchMode(cameraID, false)
                            flashOnOff.value = "Off"
                        } else {
                            cameraManager.setTorchMode(cameraID, true)
                            flashOnOff.value = "On"
                        }
                        isFlashOn.value = !isFlashOn.value
                    }else{
                        Toast.makeText(context,"Your Phone Do not Support Flash Light!", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier.padding(25.dp)
            ) {
                Text(
                    text = flashOnOff.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )

            }
        }
    }
}
