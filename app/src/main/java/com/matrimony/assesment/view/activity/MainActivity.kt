package com.matrimony.assesment.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matrimony.assesment.data.persistence.entity.User
import com.matrimony.assesment.databinding.ActivityMainBinding
import com.matrimony.assesment.view.adapter.HomeAdapter
import com.matrimony.assesment.view.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by viewModels()
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val listItem = ArrayList<User>()
    private lateinit var adapter : HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            setContentView(root)

            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )
            recyclerView.layoutManager = layoutManager

            btnMore.setOnClickListener {
                startActivity(Intent(this@MainActivity, GestureActivity::class.java))
            }
        }


        setupObserver()
    }

    private fun setupObserver() {
        //Load Dummy Data
        viewModel.loadDummy()

        //


        viewModel.list().observe(this@MainActivity, Observer {
            listItem.clear()
            listItem.addAll(it)
            adapter = HomeAdapter(this@MainActivity, listItem)
            binding.recyclerView.adapter = adapter
        })

    }

    fun viewProfile(position: Int){
        val intent = Intent(this@MainActivity, DetailActivity::class.java);
        intent.putExtra("ID", listItem[position].id)
        startActivity(intent)
    }

    fun shortListProfile(position: Int){
        Toast.makeText(this@MainActivity, "Profile shortlisted successfully", Toast.LENGTH_SHORT).show()
        listItem.removeAt(position)
        adapter.notifyItemRemoved(position)

    }
    fun rejectProfile(position: Int){
        Toast.makeText(this@MainActivity, "Profile rejected successfully", Toast.LENGTH_SHORT).show()
        listItem.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}