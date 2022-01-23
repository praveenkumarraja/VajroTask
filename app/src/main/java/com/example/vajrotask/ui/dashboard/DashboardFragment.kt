package com.example.vajrotask.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.vajrotask.adapter.DashBoardDataAdapter
import com.example.vajrotask.database.AppDataBase
import com.example.vajrotask.database.model.MyData
import com.example.vajrotask.databinding.FragmentDashboardBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    lateinit var recycler: RecyclerView
    lateinit var db: AppDataBase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recycler = binding.dashBoardRecycler
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDataBase.getInstance(requireContext())!!
        if (db.dataModelDao.getAll().isNullOrEmpty()) {
            dashboardViewModel.getApiCall(requireContext(), db)
        } else {
            callAdapter(db.dataModelDao.getAll())
        }

        dashboardViewModel.isDataCallCompleted?.observe(viewLifecycleOwner, Observer {
            callAdapter(it)

        })

    }

    private fun callAdapter(item: List<MyData>) {
        val myAdapter = DashBoardDataAdapter(requireContext(), item,db)
        recycler.adapter = myAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}