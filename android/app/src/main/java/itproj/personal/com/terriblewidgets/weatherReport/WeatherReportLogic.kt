package itproj.personal.com.terriblewidgets

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

val KEY_MESSAGE: String = "WeatherReportLogic.KEY_MESSAGE"

class WeatherReportLogic {

    fun setAlarm(activity: Activity, alarmDate: DateDefine) {

//        val calender = Calendar.getInstance()
//        calender.set(alarmDate.mYear, alarmDate.mMonth, alarmDate.mDate, alarmDate.mHourOfDay, alarmDate.mMinute)

        val intent = Intent(activity.getApplicationContext(), NotificationReceiver::class.java)
        intent.putExtra(KEY_MESSAGE, "かり")
        val sender = PendingIntent.getBroadcast(activity, NotificationReceiver.RequestCode.WEATHER_REPORT.value, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val nowCalender = Calendar.getInstance()
        nowCalender.timeInMillis = System.currentTimeMillis() + 3000

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, nowCalender.getTimeInMillis(), 1000 * 60 * 60 * 24, sender)
    }
}

