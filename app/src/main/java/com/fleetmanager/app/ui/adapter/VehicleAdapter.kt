package com.fleetmanager.app.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fleetmanager.app.data.Vehicle
import com.fleetmanager.app.data.VehicleStatus
import com.fleetmanager.app.databinding.ItemVehicleBinding

class VehicleAdapter(private val onItemClick: (Vehicle) -> Unit) :
    ListAdapter<Vehicle, VehicleAdapter.VehicleViewHolder>(DiffCallback) {

    inner class VehicleViewHolder(private val binding: ItemVehicleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(vehicle: Vehicle) {
            binding.tvVehicleName.text = "${vehicle.brand} ${vehicle.model} ${vehicle.year}"
            binding.tvLicensePlate.text = vehicle.licensePlate
            binding.tvStatus.text = vehicle.status.name
            binding.tvAssignedTo.text = if (vehicle.assignedTo.isNotEmpty()) "Driver: ${vehicle.assignedTo}" else "Unassigned"
            binding.tvMileage.text = "${vehicle.mileage} km"
            binding.tvFuelLevel.text = "Fuel: ${vehicle.fuelLevel}%"

            val statusColor = when (vehicle.status) {
                VehicleStatus.AVAILABLE -> Color.parseColor("#4CAF50")
                VehicleStatus.IN_USE -> Color.parseColor("#2196F3")
                VehicleStatus.MAINTENANCE -> Color.parseColor("#FF9800")
                VehicleStatus.OUT_OF_SERVICE -> Color.parseColor("#F44336")
            }
            binding.tvStatus.setTextColor(statusColor)

            binding.root.setOnClickListener { onItemClick(vehicle) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val binding = ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Vehicle>() {
        override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle) = oldItem == newItem
    }
}
