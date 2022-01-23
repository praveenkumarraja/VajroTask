package com.example.vajrotask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.vajrotask.adapter.HomeDataAdapter
import com.example.vajrotask.database.AppDataBase
import com.example.vajrotask.database.model.MyData
import com.example.vajrotask.databinding.FragmentHomeBinding

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    lateinit var recycler: RecyclerView
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    lateinit var db: AppDataBase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recycler = binding.dashBoardRecycler
        return root

    }
    private fun callAdapter(item: List<MyData>) {
        val myAdapter = HomeDataAdapter(requireContext(), item,db)
        recycler.adapter = myAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        db = AppDataBase.getInstance(requireContext())!!
        callAdapter(db.dataModelDao.getAll().filter { it.inputQuantity>0 })
        super.onResume()
    }
}