# ComposeSingleRowCalender
![Maven Central](https://img.shields.io/maven-central/v/io.github.ahmednader65/ComposeSingleRowCalender?style=for-the-badge)

Basic High customizable Single Row Calendar component for Jetpack Compose 

Simple Usage               |  Customized Component
:-------------------------:|:-------------------------:
![](https://github.com/AhmedNader65/ComposeSingleRowCalender/blob/master/screenshots/Screenshot_20230109_230729.png?raw=true)  |  ![](https://github.com/AhmedNader65/ComposeSingleRowCalender/blob/master/screenshots/Screenshot_20230109_230625.png?raw=true)

## Usage

### 1- 
implement the library by adding this dependency to your module's `build.gradle`

`implementation("io.github.ahmednader65:ComposeSingleRowCalender:version")`

### 2-
Simple Usage 
```kotlin
var day by remember { mutableStateOf(Date()) }
SingleRowCalendar(onSelectedDayChange = {
    day = it
})
```

## Customizing fields
```kotlin
        SingleRowCalendar(
            modifier = Modifier.background(Color.Black),
            selectedDayBackgroundColor = Color.Red,
            selectedDayTextColor = Color.White,
            dayNumTextColor = Color.Cyan,
            dayTextColor = Color.Yellow,
            iconsTintColor = Color.Gray,
            headTextColor = Color.Magenta,
            headTextStyle = MaterialTheme.typography.bodyLarge,
            nextDrawableRes = R.drawable.custom_next_icon,
            prevDrawableRes = R.drawable.custom_prev_icon,
            onSelectedDayChange = {
                day = it
            })
```
