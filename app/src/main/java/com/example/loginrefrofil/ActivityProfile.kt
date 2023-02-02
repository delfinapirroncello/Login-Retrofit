package com.example.loginrefrofil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.loginrefrofil.databinding.ActivityProfileBinding
import com.example.loginrefrofil.retrofit.UserService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActivityProfile: AppCompatActivity () {

    private lateinit var mBinding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        getSupport()
    }

    private fun getSupport() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(UserService::class.java)
        //Coroutines
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = service.getSingerUser()
                updateUI(result.data, result.support)
            } catch (e: Exception) {
                showMessage(getString(R.string.main_error_server))
            }
        }
    }
        private suspend fun updateUI(user: User, support: Support) = withContext(Dispatchers.Main) {
            with(mBinding) {
                textNameSuport.text = user.getFullName()
                textEmailSuport.text = user.email

                Glide.with(this@ActivityProfile)
                    .load(user.avatar)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(CircleCrop())
                    .centerCrop()
                    .into(avatarSuport)

                txResponse.text = support.text
                tvSupportUrl.text = support.url
            }
        }

        private fun showMessage(message: String) {
            Snackbar.make(mBinding.root, message, Snackbar.LENGTH_SHORT).show()
        }
    }