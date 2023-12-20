package com.matrimony.assesment.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.matrimony.assesment.data.persistence.entity.User
import com.matrimony.assesment.databinding.ActivityGestureBinding
import com.matrimony.assesment.utils.CenterZoomLayoutManager
import com.matrimony.assesment.view.adapter.GestureAdapter
import com.matrimony.assesment.view.viewmodel.MyViewModel

class GestureActivity : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels()
    private val listItem = ArrayList<User>()
    private lateinit var adapter : GestureAdapter

    private val binding: ActivityGestureBinding by lazy {
        ActivityGestureBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            setContentView(root)
            val layoutManager: RecyclerView.LayoutManager = CenterZoomLayoutManager(
                this@GestureActivity, LinearLayoutManager.HORIZONTAL,
                false
            )
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)
            recyclerView.layoutManager = layoutManager

            btnBack.setOnClickListener {
                finish()
            }
        }
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.list().observe(this@GestureActivity, Observer {
            listItem.clear()
            listItem.addAll(it)
            adapter = GestureAdapter(this@GestureActivity, listItem)
            binding.recyclerView.adapter = adapter
        })
    }

    fun viewProfile(position: Int){
        val intent = Intent(this@GestureActivity, DetailActivity::class.java);
        intent.putExtra("ID", listItem[position].id)
        startActivity(intent)
    }

    fun shortListProfile(position: Int){
        Toast.makeText(this@GestureActivity, "Profile shortlisted successfully", Toast.LENGTH_SHORT).show()
        listItem.removeAt(position)
        adapter.notifyItemRemoved(position)

    }
    fun rejectProfile(position: Int){
        Toast.makeText(this@GestureActivity, "Profile rejected successfully", Toast.LENGTH_SHORT).show()
        listItem.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}