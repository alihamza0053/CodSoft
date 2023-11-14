package com.flashlightapp.a.h

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flashlightapp.a.h.ui.theme.FlashLightAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashLightAppTheme {
                // A surface container using the 'background' color from the theme
                FlashLight()
            }
        }
    }
}


@Composable
fun FlashLight() {
    var context = LocalContext.current
    var isFlashOn = remember {
        mutableStateOf(false)
    }
    var flashOnOff = remember {
        mutableStateOf("Off")
    }
    var camera: Camera? = null

    var cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    var cameraID = cameraManager.cameraIdList[0]

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
                    if (isFlashOn.value) {
                        cameraManager.setTorchMode(cameraID, false)
                        flashOnOff.value = "Off"
                    } else {
                        cameraManager.setTorchMode(cameraID, true)
                        flashOnOff.value = "On"
                    }
                    isFlashOn.value = !isFlashOn.value
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
