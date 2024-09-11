package com.example.list23

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.list23.databinding.ItemLayoutOneBinding
import com.example.list23.databinding.ItemLayoutTwoBinding

class UserAdapter(val onItemDeleted:(Pair<UserData,Int>)->Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val userList: MutableList<UserData> = mutableListOf()

    fun setUserList(list: List<UserData>) {
        val myDiffUtil = MyDiffUtil(userList,list)
        val diffResult = DiffUtil.calculateDiff(myDiffUtil)
        userList.clear()
        userList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    companion object {
        private const val HolderTypeOne = 1
        private const val HolderTypeTwo = 2
    }

    override fun getItemCount() = userList.size

    override fun getItemViewType(position: Int): Int {
        return when (userList[position].type) {
            1 -> HolderTypeOne
            2 -> HolderTypeTwo
            else -> HolderTypeOne
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HolderTypeOne -> {
                val binding =
                    ItemLayoutOneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolderOne(binding)
            }

            HolderTypeTwo -> {
                val binding =
                    ItemLayoutTwoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolderTwo(binding)
            }

            else -> throw IllegalArgumentException("Invalid Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            HolderTypeOne -> (holder as ViewHolderOne).bindData(userList[position])
            HolderTypeTwo -> (holder as ViewHolderOne).bindData(userList[position])
        }
    }

    inner class ViewHolderOne(val binding: ItemLayoutOneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(userData: UserData) {
            binding.image.load("https://example.com/image.jpg")
            binding.user = userData
            binding.Delete.setOnClickListener{
                onItemDeleted(Pair(userData,adapterPosition))
            }
        }
    }

    inner class ViewHolderTwo(val binding: ItemLayoutTwoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(userData: UserData) {
            binding.user = userData
             binding.Delete.setOnClickListener{
                onItemDeleted(Pair(userData,adapterPosition))
            }
        }

    }
}