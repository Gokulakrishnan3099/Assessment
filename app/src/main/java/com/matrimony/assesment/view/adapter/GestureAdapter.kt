package com.matrimony.assesment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrimony.assesment.data.persistence.entity.User
import com.matrimony.assesment.databinding.GestureItemBinding
import com.matrimony.assesment.view.activity.GestureActivity

class GestureAdapter(private val context: GestureActivity, private val mList: List<User>) :
    RecyclerView.Adapter<GestureAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GestureAdapter.ViewHolder {
        val binding = GestureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GestureAdapter.ViewHolder, position: Int) {
        with(holder){
            with(mList[position]){
                binding.apply {
                    Glide
                        .with(context)
                        .load(image)
                        .centerCrop()
                        .into(imageView)
                    txtName.text = name
                    txtDetail.text = "$age Yrs, $height, $language, $community, $job, $address."
                    btnYes.setOnClickListener {
                        context.shortListProfile(adapterPosition)
                    }
                    btnNo.setOnClickListener {
                        context.rejectProfile(adapterPosition)
                    }

                    cardView.setOnClickListener {
                        context.viewProfile(adapterPosition)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(val binding: GestureItemBinding) : RecyclerView.ViewHolder(binding.root)
}