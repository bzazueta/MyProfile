package com.monosoft.myprofile.presentation.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monosoft.myprofile.R
import com.monosoft.myprofile.domain.models.ContactMeModel
import com.monosoft.myprofile.domain.models.WorkExperience
import com.monosoft.myprofile.presentation.screens.work_experience.WorkExperienceActivity

class ExperienceAdapter(context: Context,
                        private  val workExperienceList: List<WorkExperience>,tkn :String): RecyclerView.Adapter<ExperienceAdapter.MyViewHolder>() {
    private val context_ = context
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val  text= view.findViewById<TextView>(R.id.lblNameCompany)
        val  years = view.findViewById<TextView>(R.id.lblYearsCompany)
        val  dates = view.findViewById<TextView>(R.id.lblDatesCompany)
        val  position = view.findViewById<TextView>(R.id.lblPosition)
        val  more = view.findViewById<TextView>(R.id.lblMoreEx)
        val  img= view.findViewById<ImageView>(R.id.imageViewRE)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExperienceAdapter.MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_experience, parent, false)
        return ExperienceAdapter.MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  workExperienceList.size
    }

    override fun onBindViewHolder(holder: ExperienceAdapter.MyViewHolder, position: Int) {
        val workExperience : WorkExperience = workExperienceList[position]
        holder.text.setText(workExperience.text1)
        holder.dates.text = workExperience.dates
        holder.years.text = workExperience.years + " Años"
        holder.position.text = workExperience.position
        Glide.with(context_).load(workExperience.image).into(holder.img)

        holder.more.setOnClickListener{
            val intent = Intent(context_,WorkExperienceActivity::class.java)
            intent.putExtra("idWorkExperience", workExperience.id.toString())
            intent.putExtra("company", workExperience.company.toString())
            context_.startActivity(intent)
        }
    }
}