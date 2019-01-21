package itproj.personal.com.terriblewidgets

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat

val CHANNEL_ID: String = NotificationReceiver::class.java.name

class NotificationReceiver : BroadcastReceiver() {

    enum class RequestCode(val value: Int) {
        WEATHER_REPORT(1)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return

        val pendingIntent = PendingIntent.getActivity(context, RequestCode.WEATHER_REPORT.value, Intent(context, HomeActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
        activateNotification(context, pendingIntent)
    }

    fun activateNotification(context: Context, notifyDetail: PendingIntent) {
        if (android.os.Build.VERSION_CODES.O <= android.os.Build.VERSION.SDK_INT) {
            setNotification(context, notifyDetail)
        } else {
            setNotificationLegacy()
        }
    }


    @TargetApi(android.os.Build.VERSION_CODES.O)
    private fun setNotification(context: Context, notifyDetail: PendingIntent) {

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // チャンネルが無ければ作る
        if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
            val newChannel = NotificationChannel(CHANNEL_ID, "天気予報の通知", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(newChannel)
        }

        val notification = NotificationCompat
                .Builder(context, CHANNEL_ID)
                .apply {
                    setSmallIcon(R.drawable.ic_launcher_background)
                    setContentTitle("タイトルだよ")
                    setContentText("内容だよ")
                }.build()
        notificationManager.notify(RequestCode.WEATHER_REPORT.value, notification)
    }

    private fun setNotificationLegacy() {

    }
}

