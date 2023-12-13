package com.dicoding.picodiploma.mystoryapp.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mystoryapp.R
import com.dicoding.picodiploma.mystoryapp.ViewModelFactory
import com.dicoding.picodiploma.mystoryapp.adapter.LoadingStateAdapter
import com.dicoding.picodiploma.mystoryapp.adapter.MainAdapter
import com.dicoding.picodiploma.mystoryapp.addstory.AddStoryActivity
import com.dicoding.picodiploma.mystoryapp.data.ResultState
import com.dicoding.picodiploma.mystoryapp.databinding.ActivityMainBinding
import com.dicoding.picodiploma.mystoryapp.maps.MapsActivity
import com.dicoding.picodiploma.mystoryapp.welcome.WelcomeActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.show()

        binding.fabUpload.setOnClickListener {
            startActivity(Intent(this, AddStoryActivity::class.java))
        }

        viewModel.getSession().observe(this){
            if (!it.isLogin){
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }else{
                getData()
                setupAction()
            }
        }
        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)
    }

    private fun setupAction() {
        lifecycleScope.launch {
            viewModel.getStories().observe(this@MainActivity) { story ->
                when (story) {
                    is ResultState.Success -> {
                        binding.progressBar.visibility = View.INVISIBLE

                    }
                    is ResultState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ResultState.Error -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        val error = story.error
                        Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }
    private fun getData() {
        val adapter = MainAdapter()
        binding.rvReview.adapter =  adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        viewModel.quote.observe(this, {
            adapter.submitData(lifecycle, it)
        })
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.maps -> {
                lifecycleScope.launch {
                    val intent = Intent(this@MainActivity, MapsActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.logout -> {
                lifecycleScope.launch {
                    viewModel.logout()
                    val intent = Intent(this@MainActivity,WelcomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.setting->{
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}