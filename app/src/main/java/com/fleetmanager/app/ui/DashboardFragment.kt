package com.fleetmanager.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fleetmanager.app.databinding.FragmentDashboardBinding
import com.fleetmanager.app.viewmodel.FleetViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FleetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.totalCount.observe(viewLifecycleOwner) { count ->
            binding.tvTotalVehicles.text = count?.toString() ?: "0"
        }
        viewModel.availableCount.observe(viewLifecycleOwner) { count ->
            binding.tvAvailable.text = count?.toString() ?: "0"
        }
        viewModel.inUseCount.observe(viewLifecycleOwner) { count ->
            binding.tvInUse.text = count?.toString() ?: "0"
        }
        viewModel.maintenanceCount.observe(viewLifecycleOwner) { count ->
            binding.tvMaintenance.text = count?.toString() ?: "0"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
