package com.fleetmanager.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fleetmanager.app.data.Vehicle
import com.fleetmanager.app.data.VehicleStatus
import com.fleetmanager.app.databinding.FragmentAddEditVehicleBinding
import com.fleetmanager.app.viewmodel.FleetViewModel
import kotlinx.coroutines.launch

class AddEditVehicleFragment : Fragment() {

    private var _binding: FragmentAddEditVehicleBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FleetViewModel by viewModels()
    private var vehicleId: Long = -1L
    private var existingVehicle: Vehicle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vehicleId = arguments?.getLong("vehicleId", -1L) ?: -1L

        // Setup status spinner
        val statuses = VehicleStatus.values().map { it.name }
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, statuses)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerStatus.adapter = spinnerAdapter

        if (vehicleId != -1L) {
            binding.btnSave.text = "Update Vehicle"
            lifecycleScope.launch {
                existingVehicle = viewModel.allVehicles.value?.find { it.id == vehicleId }
                existingVehicle?.let { populateFields(it) }
            }
        }

        binding.btnSave.setOnClickListener { saveVehicle() }

        binding.btnDelete.visibility = if (vehicleId != -1L) View.VISIBLE else View.GONE
        binding.btnDelete.setOnClickListener { deleteVehicle() }
    }

    private fun populateFields(vehicle: Vehicle) {
        binding.etLicensePlate.setText(vehicle.licensePlate)
        binding.etBrand.setText(vehicle.brand)
        binding.etModel.setText(vehicle.model)
        binding.etYear.setText(vehicle.year.toString())
        binding.etColor.setText(vehicle.color)
        binding.etAssignedTo.setText(vehicle.assignedTo)
        binding.etMileage.setText(vehicle.mileage.toString())
        binding.etFuelLevel.setText(vehicle.fuelLevel.toString())
        binding.etNotes.setText(vehicle.notes)
        val statusIndex = VehicleStatus.values().indexOfFirst { it == vehicle.status }
        if (statusIndex >= 0) binding.spinnerStatus.setSelection(statusIndex)
    }

    private fun saveVehicle() {
        val licensePlate = binding.etLicensePlate.text.toString().trim()
        val brand = binding.etBrand.text.toString().trim()
        val model = binding.etModel.text.toString().trim()
        val yearStr = binding.etYear.text.toString().trim()

        if (licensePlate.isEmpty() || brand.isEmpty() || model.isEmpty() || yearStr.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val year = yearStr.toIntOrNull() ?: run {
            Toast.makeText(requireContext(), "Invalid year", Toast.LENGTH_SHORT).show()
            return
        }

        val status = VehicleStatus.valueOf(binding.spinnerStatus.selectedItem.toString())
        val vehicle = Vehicle(
            id = if (vehicleId != -1L) vehicleId else 0,
            licensePlate = licensePlate,
            brand = brand,
            model = model,
            year = year,
            color = binding.etColor.text.toString().trim(),
            status = status,
            assignedTo = binding.etAssignedTo.text.toString().trim(),
            mileage = binding.etMileage.text.toString().toIntOrNull() ?: 0,
            fuelLevel = binding.etFuelLevel.text.toString().toIntOrNull() ?: 100,
            notes = binding.etNotes.text.toString().trim()
        )

        if (vehicleId != -1L) {
            viewModel.update(vehicle)
        } else {
            viewModel.insert(vehicle)
        }

        findNavController().navigateUp()
    }

    private fun deleteVehicle() {
        existingVehicle?.let {
            viewModel.delete(it)
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
