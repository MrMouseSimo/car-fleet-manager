package com.fleetmanager.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fleetmanager.app.R
import com.fleetmanager.app.databinding.FragmentVehicleListBinding
import com.fleetmanager.app.ui.adapter.VehicleAdapter
import com.fleetmanager.app.viewmodel.FleetViewModel

class VehicleListFragment : Fragment() {

    private var _binding: FragmentVehicleListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FleetViewModel by viewModels()
    private lateinit var adapter: VehicleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehicleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = VehicleAdapter { vehicle ->
            val bundle = Bundle().apply {
                putLong("vehicleId", vehicle.id)
            }
            findNavController().navigate(R.id.action_vehicleList_to_addEditVehicle, bundle)
        }

        binding.recyclerView.adapter = adapter

        viewModel.allVehicles.observe(viewLifecycleOwner) { vehicles ->
            adapter.submitList(vehicles)
            binding.tvEmpty.visibility = if (vehicles.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.fabAddVehicle.setOnClickListener {
            findNavController().navigate(R.id.action_vehicleList_to_addEditVehicle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
