package com.example.tatacodechallenge.helper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tatacodechallenge.data.RandomText
import com.example.tatacodechallenge.databinding.ItemRandomStringBinding

class RandomStringAdapter(
    private var items: List<RandomText>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<RandomStringAdapter.StringViewHolder>() {

    inner class StringViewHolder(private val binding: ItemRandomStringBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RandomText, position: Int) {
            binding.tvString.text = "Value: ${item.value}"
            binding.tvLength.text = "Length: ${item.length}"
            binding.tvCreated.text = "Created: ${item.created}"
            binding.btnDelete.setOnClickListener {
                onDelete(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val binding = ItemRandomStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StringViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size
}