package com.mrerror.singleRowCalendarApp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mrerror.singleRowCalendar.SingleRowCalendar
import com.mrerror.singleRowCalendarApp.ui.theme.SingleRowCalendarTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SingleRowCalendarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp() {
    Column(Modifier.fillMaxSize()) {
        var day by remember { mutableStateOf(Date()) }

        SingleRowCalendar(onSelectedDayChange = {
            day = it
        })

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Selected Day is ${
                    SimpleDateFormat(
                        "dd-MM-yyyy",
                        Locale.US
                    ).format(day)
                }"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SingleRowCalendarTheme {
        SingleRowCalendar(modifier = Modifier, onSelectedDayChange = {})
    }
}