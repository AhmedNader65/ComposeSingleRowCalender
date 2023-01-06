package com.mrerror.singleRowCalendar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrerror.singleRowCalendar.DateUtils.getFutureDates
import com.mrerror.singleRowCalendar.SingleRowCalendarDefaults.Grey500
import com.mrerror.singlerowcalendar.R
import java.util.*


@Composable
fun SingleRowCalendar(
    modifier: Modifier = Modifier,
    selectedDayBackgroundColor: Color = SingleRowCalendarDefaults.Blue600,
    selectedDayTextColor: Color = Color.White,
    dayNumTextColor: Color = Color.Black,
    dayTextColor: Color = Grey500,
    iconsTintColor: Color = Color.Black,
    headTextColor: Color = Color.Black,
    headTextStyle: TextStyle = MaterialTheme.typography.titleMedium,
    @DrawableRes nextDrawableRes: Int = R.drawable.baseline_keyboard_double_arrow_right_24,
    @DrawableRes prevDrawableRes: Int = R.drawable.baseline_keyboard_double_arrow_left_24,
    onSelectedDayChange: (Date) -> Unit,

    ) {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    var currentDate by rememberSaveable { mutableStateOf(calendar.time) }
    var selectedDate by rememberSaveable { mutableStateOf(calendar.time) }
    Column(modifier) {

        WeekHeader(firstDayDate = currentDate,
            iconsTintColor = iconsTintColor,
            headTextColor = headTextColor,
            headTextStyle = headTextStyle,
            nextDrawableRes = nextDrawableRes,
            prevDrawableRes = prevDrawableRes,
            onNextWeekClicked = {
                calendar.time = it
                currentDate = it
            },
            onPrevWeekClicked = {
                calendar.time = it
                currentDate = it
            })

        WeekDaysHeader(selectedDayBackgroundColor = selectedDayBackgroundColor,
            selectedDayTextColor = selectedDayTextColor,
            dayNumTextColor = dayNumTextColor,
            dayTextColor = dayTextColor,
            firstDayDate = currentDate,
            selectedDate = selectedDate,
            onSelectDay = { day ->
                calendar.time = day
                selectedDate = day
                onSelectedDayChange(day)
            })
    }
}

@Composable
fun WeekHeader(
    modifier: Modifier = Modifier,
    iconsTintColor: Color,
    headTextColor: Color,
    headTextStyle: TextStyle,
    firstDayDate: Date,
    @DrawableRes nextDrawableRes: Int,
    @DrawableRes prevDrawableRes: Int,
    onNextWeekClicked: (firstDayDate: Date) -> Unit,
    onPrevWeekClicked: (firstDayDate: Date) -> Unit,
) {

    val dayName = DateUtils.getDayNumber(firstDayDate)
    val monthName = DateUtils.getMonthName(firstDayDate)
    val yearName = DateUtils.getYear2Letters(firstDayDate)
    val weekFinalDays = getFutureDates(6, Calendar.getInstance().apply { time = firstDayDate })
    val weekFinalDate = weekFinalDays.last()
    val fDayName = DateUtils.getDayNumber(weekFinalDate)
    val fMonthName = DateUtils.getMonthName(weekFinalDate)
    val fYearName = DateUtils.getYear2Letters(weekFinalDate)

    Row(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier.clickable {
                val c = Calendar.getInstance()
                c.time = firstDayDate
                c.add(Calendar.DATE, -7)
                val prevWeekFirstDay = c.time
                onPrevWeekClicked(prevWeekFirstDay)
            },
            painter = painterResource(id = prevDrawableRes),
            contentDescription = "",
            colorFilter = ColorFilter.tint(iconsTintColor)
        )
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = "$dayName $monthName $yearName - $fDayName $fMonthName $fYearName",
            style = headTextStyle,
            color = headTextColor
        )
        Image(
            modifier = Modifier.clickable {
                val c = Calendar.getInstance()
                c.time = weekFinalDate
                c.add(Calendar.DATE, 1)
                val nextWeekFirstDay = c.time
                onNextWeekClicked(nextWeekFirstDay)
            },
            painter = painterResource(id = nextDrawableRes),
            contentDescription = "",
            colorFilter = ColorFilter.tint(iconsTintColor)
        )
    }
}

@Composable
fun WeekDaysHeader(
    modifier: Modifier = Modifier,
    selectedDayBackgroundColor: Color,
    selectedDayTextColor: Color,
    dayNumTextColor: Color,
    dayTextColor: Color,
    firstDayDate: Date,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit
) {

    val weekFinalDays = getFutureDates(6, Calendar.getInstance().apply { time = firstDayDate })
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (day in weekFinalDays) {
            Column(
                modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = DateUtils.getDay3LettersName(day),
                    style = MaterialTheme.typography.bodyMedium,
                    color = dayTextColor,
                    textAlign = TextAlign.Center
                )
                Text(text = DateUtils.getDayNumber(day),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 16.sp,
                    color = if (selectedDate == day) selectedDayTextColor else dayNumTextColor,

                    modifier = (if (selectedDate == day) Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .drawBehind {
                            drawCircle(
                                color = selectedDayBackgroundColor, radius = this.size.height
                            )
                        }
                    else Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)).clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onSelectDay(day)
                    },
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

object SingleRowCalendarDefaults {
    val Blue600: Color = Color(0xFF1570EF)
    val Grey500: Color = Color(0xFF667085)
}

@Preview
@Composable
fun SingleRowCalendarPreview() {
    SingleRowCalendar() {}
}

@Preview(locale = "ar")
@Composable
fun SingleRowCalendarARPreview() {
    SingleRowCalendar(modifier = Modifier.fillMaxWidth()) {}
}