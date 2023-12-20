package com.matrimony.assesment.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.matrimony.assesment.databinding.ActivityDetailBinding
import com.matrimony.assesment.utils.CenterZoomLayoutManager
import com.matrimony.assesment.utils.CirclePagerIndicatorDecoration
import com.matrimony.assesment.view.adapter.ImageAdapter
import com.matrimony.assesment.view.viewmodel.MyViewModel

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val viewModel: MyViewModel by viewModels()

    private val listItem = ArrayList<String>()
    private lateinit var adapter : ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            setContentView(root)
            btnBack.setOnClickListener {
                finish()
            }
            val layoutManager: RecyclerView.LayoutManager = CenterZoomLayoutManager(
                this@DetailActivity, LinearLayoutManager.HORIZONTAL,
                false
            )
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)
            recyclerView.layoutManager = layoutManager
            recyclerView.addItemDecoration(CirclePagerIndicatorDecoration())
        }
        setupObserver()
    }

    private fun setupObserver() {
        val id = intent.extras?.getInt("ID") ?: 0
        binding.txtTitle.text = "PROFILE$id"
        viewModel.getUser(id).observe(this@DetailActivity, Observer {
            it?.let {
                listItem.clear()
                for(i in 0..6){
                    listItem.add(it.image)
                }
                adapter = ImageAdapter(this@DetailActivity, listItem)
                binding.recyclerView.adapter = adapter

                binding.txtName.text = it.name
                binding.txtDetail.text = "${it.age} Yrs, ${it.height}, ${it.language}, ${it.community}, ${it.job}, ${it.address}."
            }
        })
    }
}