package com.game.kotvitz.war

import android.content.Intent
import android.content.res.Resources
import java.util.*

object LocaleManager {

    fun setLocale(localeName: String, intent: Intent, res: Resources) {
        val myLocale = Locale(localeName)
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        intent.putExtra("lang", localeName)
    }
}
