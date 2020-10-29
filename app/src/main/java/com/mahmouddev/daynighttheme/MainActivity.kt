package com.mahmouddev.daynighttheme

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.mahmouddev.daynighttheme.datastore.DataStoreRef
import com.mahmouddev.daynighttheme.util.Constance
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var dataStoreRef: DataStoreRef

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataStoreRef = DataStoreRef(this)
        getSavedTheme()
        initThemeListener()

    }

    private fun initThemeListener() {
        themeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbDark -> setTheme(AppCompatDelegate.MODE_NIGHT_YES, Constance.DARK_MODE)
                R.id.rbLight -> setTheme(AppCompatDelegate.MODE_NIGHT_NO, Constance.LIGHT_MODE)
                R.id.rbSystem -> setTheme(
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
                    Constance.SYSTEM_MODE
                )
                R.id.rbBattery -> setTheme(
                    AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY,
                    Constance.BATTERY_MODE
                )
            }
        }
    }

    private fun setTheme(themeMode: Int, modeType: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(modeType)
    }

    private fun saveTheme(value: Int) {
        lifecycleScope.launch {
            dataStoreRef.setInt(Constance.MODE_TYPE, value)
        }
    }

    private fun getSavedTheme() {
        dataStoreRef.getInt(Constance.MODE_TYPE).asLiveData().observe(this, Observer {


            when (it ?: Constance.UNKNOWN_MODE) {
                Constance.DARK_MODE -> rbDark.isChecked = true
                Constance.LIGHT_MODE -> rbLight.isChecked = true
                Constance.SYSTEM_MODE -> rbSystem.isChecked = true
                Constance.BATTERY_MODE -> rbBattery.isChecked = true
                Constance.UNKNOWN_MODE -> {

                    // check current mode
                    when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
                        Configuration.UI_MODE_NIGHT_NO -> rbLight.isChecked = true
                        Configuration.UI_MODE_NIGHT_YES -> rbDark.isChecked = true
                        Configuration.UI_MODE_NIGHT_UNDEFINED -> rbLight.isChecked = true
                    }
                }
            }
        })

    }
}