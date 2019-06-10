package com.example.iteradmin.phonecallpermissionatruntime

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    val REQUEST_CODE =100

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val number = findViewById<EditText>(R.id.number)
        val call = findViewById<Button>(R.id.button)
        call.setOnClickListener {
            if (ContextCompat.checkSelfPermission
                    (this,android.Manifest.permission.CALL_PHONE )== PackageManager.PERMISSION_GRANTED)
            {
            val s:String= "tel:"+number.text.toString()
                val i = Intent(Intent.ACTION_CALL)
                i.setData(Uri.parse(s))
                startActivity(i)
            }else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CALL_PHONE )== false) {
                    val ar = arrayOf(android.Manifest.permission.CALL_PHONE)
                    ActivityCompat.requestPermissions(this, ar, REQUEST_CODE)
                }
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        when(requestCode){
            REQUEST_CODE->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"permission allowed",Toast.LENGTH_LONG).show()

                }else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()
                }
                return

            }
        }

    }
}
