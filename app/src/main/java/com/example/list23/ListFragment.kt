package com.example.list23

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.list23.databinding.FragmentListBinding

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    val userList:MutableList<UserData> = mutableListOf()
    lateinit var userAdapter: UserAdapter
    lateinit var pairone:Triple<String,Int,Double>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentListBinding.inflate(layoutInflater)
        pairone= Triple("Rana",1,5.0)
        pairone.first
        pairone.second
        pairone.third
        addItemToList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myList.layoutManager = LinearLayoutManager(requireContext())
        userAdapter = UserAdapter()
        {
            userList.remove(it.first)
            userAdapter.setUserList(userList)
        }

        binding.myList.adapter = userAdapter
        userAdapter.setUserList(userList)
        binding.update.setOnClickListener{
            updateItemToList()
            userAdapter.setUserList(userList)
        }
    }

    fun addItemToList(){
        userList.add(UserData(1,11,"Rana","0100320887","rana@eck.com"))
        userList.add(UserData(2,12,"Mena","0129420887","mena@eck.com"))
        userList.add(UserData(1,13,"Jana","0157738822","Jana@eck.com"))
        userList.add(UserData(1,14,"Ahmed","0176583929","Ahmed@eck.com"))
        userList.add(UserData(2,15,"Muhammad","0134249565","Muhammad@eck.com"))
        userList.add(UserData(1,16,"Hana","0131278497","Hana@eck.com"))
        userList.add(UserData(2,17,"Sara","0185470549","Sara@eck.com"))
        userList.add(UserData(1,18,"Ali","0165359838","Ali@eck.com"))
    }

    fun updateItemToList(){
        userList.clear()
        userList.add(UserData(1,11,"Rana","0100320887","rana@eck.com"))
        userList.add(UserData(2,12,"Mariam","0129420887","mena@eck.com"))
        userList.add(UserData(1,13,"Jana","0157738822","Jana@eck.com"))
        userList.add(UserData(1,14,"Ahmed","0176583929","Ahmed@eck.com"))
        userList.add(UserData(2,15,"Muhammad","0134249565","Muhammad@eck.com"))
        userList.add(UserData(1,16,"Hana","0131278497","Hana@eck.com"))
        userList.add(UserData(2,17,"Sara","0185470549","Sara@eck.com"))
        userList.add(UserData(1,18,"Ali","0165359838","Ali@eck.com"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        userList.clear()
    }
}