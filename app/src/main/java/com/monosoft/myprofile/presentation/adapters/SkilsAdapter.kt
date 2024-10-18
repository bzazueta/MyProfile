package com.monosoft.myprofile.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monosoft.myprofile.R
import com.monosoft.myprofile.domain.models.ContactMeModel
import com.monosoft.myprofile.domain.models.FrameworksAndTecnology
import de.hdodenhof.circleimageview.CircleImageView

class SkilsAdapter(context: Context,
                   private  val frameworksAndTecnologyList: List<FrameworksAndTecnology>): RecyclerView.Adapter<SkilsAdapter.MyViewHolder>() {

    private val context_ = context
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val  text= view.findViewById<TextView>(R.id.lblName1)
        val  text2= view.findViewById<TextView>(R.id.lblName2)
        val  text3= view.findViewById<TextView>(R.id.lblYearsSkill)
        val  img= view.findViewById<CircleImageView>(R.id.profile_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkilsAdapter.MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_skills, parent, false)
        return SkilsAdapter.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SkilsAdapter.MyViewHolder, position: Int) {
        val contectMe: FrameworksAndTecnology = frameworksAndTecnologyList[position]
        holder.text.setText(contectMe.technology)
        holder.text2.setText(contectMe.language)
        holder.text3.setText(contectMe.years+ " Años")
        Glide.with(context_).load(contectMe.image).into(holder.img)
    }

    override fun getItemCount(): Int {
        return  frameworksAndTecnologyList.size
    }
}