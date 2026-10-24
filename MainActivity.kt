package com.example.minecraftlauncher2

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private val bg = Color.rgb(28, 30, 34)
    private val panel = Color.rgb(42, 45, 51)
    private val green = Color.rgb(94, 180, 70)
    private fun label(text: String, size: Float, color: Int, bold: Boolean = false): TextView =
        TextView(this).apply {
            this.text = text
            textSize = size
            setTextColor(color)
            if (bold) setTypeface(null, android.graphics.Typeface.BOLD)
            gravity = Gravity.CENTER_VERTICAL
        }

    private fun button(text: String, action: () -> Unit) = Button(this).apply {
        this.text = text
        isAllCaps = false
        setOnClickListener { action() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
            setPadding(18, 18, 18, 18)
        }
        root.addView(label("MINECRAFT LAUNCHER 2", 22f, Color.WHITE, true),
            LinearLayout.LayoutParams(-1, 58))

        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, 8, 0, 8)
        }
        scroll.addView(content)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))

        val hero = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(panel)
            setPadding(18, 18, 18, 18)
        }
        hero.addView(label("MINECRAFT JAVA EDITION", 19f, Color.WHITE, true))
        hero.addView(label("Dein Launcher. Deine Installationen.", 14f, Color.LTGRAY))
        hero.addView(label("Prototyp – Spielstart noch nicht integriert", 12f, 0xFFFFD180.toInt()))
        hero.addView(button("▶  Spielen") {
            Toast.makeText(this, "Der echte Minecraft-Spielstart muss noch integriert werden.", Toast.LENGTH_LONG).show()
        })
        content.addView(hero, LinearLayout.LayoutParams(-1, -2).apply { bottomMargin = 14 })

        content.addView(label("MENÜ", 13f, Color.LTGRAY, true))
        val items = listOf(
            "Installationen" to "Hier werden Minecraft-Versionen und Profile verwaltet.",
            "Mods & Clients installieren" to "Geplant: Mods, Modpacks, Ressourcenpakete, Shader und kompatible Clients.",
            "Ressourcenpakete" to "Verwaltung wird später integriert.",
            "Shader" to "Nur kompatible Shader können unterstützt werden.",
            "Konto" to "Microsoft-Anmeldung ist noch nicht integriert.",
            "Einstellungen" to "Launcher-Einstellungen werden später ergänzt."
        )
        items.forEach { (title, detail) ->
            val card = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(panel)
                setPadding(14, 12, 14, 12)
            }
            card.addView(label(title, 16f, Color.WHITE, true))
            card.addView(label(detail, 12f, Color.LTGRAY))
            card.setOnClickListener {
                Toast.makeText(this, "$title: noch nicht verfügbar im Prototyp.", Toast.LENGTH_SHORT).show()
            }
            val lp = LinearLayout.LayoutParams(-1, -2)
            lp.bottomMargin = 8
            content.addView(card, lp)
        }
        setContentView(root)
    }
}
