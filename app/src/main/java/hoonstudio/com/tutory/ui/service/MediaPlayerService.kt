package hoonstudio.com.tutory.ui.service

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

class MediaPlayerService: IntentService("MediaPlayerService"){

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onHandleIntent(intent: Intent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindService(service: Intent?, conn: ServiceConnection, flags: Int): Boolean {
        return super.bindService(service, conn, flags)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}