package com.example.facebookapi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.facebookapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var pickImageLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = binding.button

        pickImageLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let { shareToInstagramStory(it) }
            }

        button.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }
    }

    private fun shareToInstagramStory(imageUri: Uri) {
        try {
            val storiesIntent = Intent("com.instagram.share.ADD_TO_STORY")
            storiesIntent.setDataAndType(imageUri, "image/*")
            storiesIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            storiesIntent.setPackage("com.instagram.android")
            this.grantUriPermission(
                "com.instagram.android",
                imageUri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            ContextCompat.startActivity(this, storiesIntent, null)
        } catch (e: Exception) {
            Toast.makeText(this, "Instagram app not installed", Toast.LENGTH_SHORT).show()
        }
    }
}