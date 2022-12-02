package com.ozancanguz.mvvmemployessapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozancanguz.mvvmemployessapp.databinding.EmpRowLayoutBinding
import com.ozancanguz.mvvmemployessapp.models.Employee
import com.ozancanguz.mvvmemployessapp.models.Result

class EmployeeAdapter:RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>() {

    var employeeList= emptyList<Result>()

    class MyViewHolder(private val binding:EmpRowLayoutBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result){
            binding.result = result
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding=EmpRowLayoutBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult=employeeList[position]
        holder.bind(currentResult)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    fun setData(newData: Employee){
        employeeList=newData.data
        notifyDataSetChanged()
    }
}