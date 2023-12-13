package com.dicoding.picodiploma.mystoryapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.mystoryapp.databinding.ActivityDetailStoryBinding
import com.dicoding.picodiploma.mystoryapp.response.ListStoryItem



@Suppress("DEPRECATION")
class DetailStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val detail = intent.getParcelableExtra<ListStoryItem>(DetailStoryActivity.DETAIL_STORY) as ListStoryItem
        setupAction(detail)

        supportActionBar?.show()
        supportActionBar?.title = "Detail Story"



    }

    private fun setupAction(detail: ListStoryItem){
        Glide.with(applicationContext)
            .load(detail.photoUrl)
            .into(binding.avatarDetail)
        binding.tvName.text = detail.name
        binding.tvDesc.text = detail.description
    }
    companion object {
        const val DETAIL_STORY = "detail_story"
    }
}