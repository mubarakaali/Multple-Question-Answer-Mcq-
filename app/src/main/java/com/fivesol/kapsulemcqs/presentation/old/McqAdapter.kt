package com.fivesol.kapsulemcqs.presentation.old

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fivesol.kapsulemcqs.R
import com.fivesol.kapsulemcqs.databinding.ItemMcqSubLayoutBinding
import com.fivesol.kapsulemcqs.presentation.old.McqAdapter.McqViewHolder
import fivesol.networklibrary.domain.models.sub_category.Mcq

class McqAdapter(
    val context: Context,
    private val items: List<Mcq>,
) :
    RecyclerView.Adapter<McqViewHolder>() {
    private var itemPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): McqViewHolder {
        val binding =
            ItemMcqSubLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return McqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: McqViewHolder, position: Int) {
        val subMcq = items[position]
        holder.binding.categoryName.text = subMcq.category!!.name
        holder.binding.questionTitle.text = subMcq.question
        holder.binding.optionA.text = subMcq.optionA
        holder.binding.optionB.text = subMcq.optionB
        holder.binding.optionC.text = subMcq.optionC
        holder.binding.optionD.text = subMcq.optionD
        holder.binding.ansWer.text = subMcq.ans
        holder.binding.sUbmit.text = subMcq.submittedBy

        if (subMcq.isItemSelected) {
//            Log.e("jejeje ", "onBindViewHolder: subMcq.isItemSelected TRUE .........")
            if (subMcq.optionA.equals(subMcq.ans)) {
//                Log.d("jejeje ", "onBindViewHolder: IF ans is CORRECT .........")
                holder.binding.apply {
                    onSelect(optionAWrapper, optionA, ansWer)
                }

            } else if (subMcq.optionB.equals(subMcq.ans)) {
                holder.binding.apply {
                    onSelect(optionBWrapper, optionB, ansWer)
                }
            } else if (subMcq.optionC.equals(subMcq.ans)) {
                holder.binding.apply {
                    onSelect(optionCWrapper, optionC, ansWer)
                }
            } else if (subMcq.optionD.equals(subMcq.ans)) {
                holder.binding.apply {
                    onSelect(optionDWrapper, optionD, ansWer)
                }
            } else {
//                Log.e("jejeje ", "onBindViewHolder: IF ans is INCORRECT .........")
                unSelect(holder.binding)
            }

        } else {
//            Log.d("jejeje ", "onBindViewHolder: subMcq.isItemSelected .................................... FALSE")
            subMcq.isItemSelected = false
            unSelect(holder.binding)

        }


        holder.binding.mainWrapper.setOnClickListener {
            itemPosition = position
            subMcq.isItemSelected = true
            Log.d("jejeje ", "onBindViewHolder: isItemSelected ........." + subMcq.isItemSelected)
            Log.d("jejeje ", "onBindViewHolder: itemPosition ........." + itemPosition)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun onSelect(optionBWrapper: LinearLayout, optionB: TextView, ansWer: TextView) {
        optionBWrapper.background = context.getDrawable(R.color.green)
        optionB.setTextColor(context.resources.getColor(R.color.white))
        ansWer.visibility = View.VISIBLE
    }

    private fun unSelect(binding: ItemMcqSubLayoutBinding) {
        binding.apply {
            optionA.setTextColor(context.resources.getColor(R.color.black))
            optionB.setTextColor(context.resources.getColor(R.color.black))
            optionC.setTextColor(context.resources.getColor(R.color.black))
            optionD.setTextColor(context.resources.getColor(R.color.black))
            optionAWrapper.setBackgroundColor(context.resources.getColor(R.color.lightBlue))
            optionBWrapper.background = context.getDrawable(R.color.lightBlue)
            optionCWrapper.background = context.getDrawable(R.color.lightBlue)
            optionDWrapper.background = context.getDrawable(R.color.lightBlue)
            ansWer.visibility = View.GONE
        }
    }

    inner class McqViewHolder(val binding: ItemMcqSubLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}