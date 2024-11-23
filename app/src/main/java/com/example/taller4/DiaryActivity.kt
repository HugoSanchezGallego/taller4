package com.example.taller4

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import kotlin.math.sqrt

class DiaryActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastShakeTime: Long = 0
    val diaryEntries = mutableListOf(
        DiaryEntry("Entrada 1", "01/01/2024", "Descripción 1"),
        DiaryEntry("Entrada 2", "02/01/2024", "Descripción 2"),
        DiaryEntry("Entrada 3", "03/01/2024", "Descripción 3")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (savedInstanceState == null) {
            val listFragment = DiaryListFragment()
            supportFragmentManager.commit {
                add(R.id.listFragmentContainer, listFragment)
                add(R.id.detailFragmentContainer, DiaryDetailFragment.newInstance(0))
            }

            val addButton: Button = findViewById(R.id.addButton)
            addButton.setOnClickListener {
                val dialog = NewEntryDialogFragment()
                dialog.setNewEntryListener(listFragment)
                dialog.show(supportFragmentManager, "NewEntryDialogFragment")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val x = it.values[0]
            val y = it.values[1]
            val z = it.values[2]

            val acceleration = sqrt((x * x + y * y + z * z).toDouble())
            val currentTime = System.currentTimeMillis()

            if (acceleration > 12 && currentTime - lastShakeTime > 1000) {
                lastShakeTime = currentTime
                changeBackgroundColor()
                Toast.makeText(this, "¡Sacudiste para un nuevo color de ánimo!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No se necesita implementar
    }

    private fun changeBackgroundColor() {
        val colors = listOf(
            android.R.color.holo_red_light,
            android.R.color.holo_green_light,
            android.R.color.holo_blue_light,
            android.R.color.holo_orange_light
        )
        val randomColor = colors.random()
        findViewById<View>(R.id.detailFragmentContainer).setBackgroundResource(randomColor)
    }

    fun showDiaryDetail(entryId: Int) {
        supportFragmentManager.commit {
            replace(R.id.detailFragmentContainer, DiaryDetailFragment.newInstance(entryId))
        }
    }

    fun getDiaryEntry(entryId: Int): DiaryEntry? {
        return diaryEntries.getOrNull(entryId)
    }

    fun addDiaryEntry(entry: DiaryEntry) {
        diaryEntries.add(entry)
        val listFragment = supportFragmentManager.findFragmentById(R.id.listFragmentContainer) as? DiaryListFragment
        listFragment?.updateAdapter(diaryEntries)
    }

    fun deleteDiaryEntry(entryId: Int) {
        diaryEntries.removeAt(entryId)
        val listFragment = supportFragmentManager.findFragmentById(R.id.listFragmentContainer) as? DiaryListFragment
        listFragment?.updateAdapter(diaryEntries)
        showDiaryDetail(0) // Show the first entry or a default view
    }
}