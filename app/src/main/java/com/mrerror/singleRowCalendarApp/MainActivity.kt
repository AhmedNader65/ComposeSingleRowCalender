package com.mrerror.singleRowCalendarApp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mrerror.singleRowCalendar.SingleRowCalendar
import com.mrerror.singleRowCalendarApp.ui.theme.SingleRowCalendarTheme

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
                    val context = LocalContext.current
                    SingleRowCalendar(modifier = Modifier, onSelectedDayChange = {
                        Toast.makeText(context, "${it.time}", Toast.LENGTH_SHORT).show()
                    })
                }
            }
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