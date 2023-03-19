package com.example.sharedpreferencesexample

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text1 = findViewById<TextView>(R.id.text1)
        val text2 = findViewById<TextView>(R.id.text2)
        val edit1 = findViewById<EditText>(R.id.edit1)
        val btn1 = findViewById<Button>(R.id.btn1)

        val s = GetTextFromSharedPreference("logedinuser", "displayname")
        if (s != null && s.length > 0) {
            text2.setText(text2.text.toString()+" "+s)
        }
        btn1.setOnClickListener(){


            if(SaveTextToSharedPreference("logedinuser", "displayname", edit1.text.toString())){
                Toast.makeText(this,"Stored Successfully", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Unable to Store! Try later.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun GetTextFromSharedPreference(name: String, key: String): String? {
        // Opening the shared preference of specified name
        val sharedPreference: SharedPreferences = getSharedPreferences(name, MODE_PRIVATE)

        // get Value
        val s1: String? = sharedPreference.getString(key,"")
        return s1
    }

    private fun SaveTextToSharedPreference(name: String, key: String, value: String): Boolean {
        // Opening the shared preference of specified name
        val sharedPreference: SharedPreferences = getSharedPreferences(name, MODE_PRIVATE)

        // Allow editing
        val editor: SharedPreferences.Editor = sharedPreference.edit()

        // Save data
        editor.putString(key,value)
        return editor.commit()
    }


}