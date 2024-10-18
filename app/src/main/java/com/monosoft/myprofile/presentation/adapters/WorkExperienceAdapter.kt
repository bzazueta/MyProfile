package com.monosoft.myprofile.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monosoft.myprofile.R
import com.monosoft.myprofile.domain.models.Skills
import de.hdodenhof.circleimageview.CircleImageView

class WorkExperienceAdapter(context: Context,
                            private  val workExperienceList: List<Skills>, tkn :String): RecyclerView.Adapter<WorkExperienceAdapter.MyViewHolder>() {
    private val context_ = context

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val  skillTitle= view.findViewById<TextView>(R.id.lblSkillTitle)
        val  skillName= view.findViewById<TextView>(R.id.lblSkillName)
        val  text3= view.findViewById<TextView>(R.id.lblYearsSkill)
        val  skill_image = view.findViewById<CircleImageView>(R.id.skill_image)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):WorkExperienceAdapter.MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_skill_work, parent, false)
        return WorkExperienceAdapter.MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  workExperienceList.size
    }

    override fun onBindViewHolder(holder: WorkExperienceAdapter.MyViewHolder, position: Int) {
        val skill : Skills = workExperienceList[position]
        holder.skillName.setText(skill.description)
        holder.skillTitle.setText(skill.skill)
//        holder.dates.text = workExperience.dates
//        holder.years.text = workExperience.years + " Años"
//        holder.position.text = workExperience.position
        Glide.with(context_).load(skill.image).into(holder.skill_image)
//
//        holder.more.setOnClickListener{
//            val intent = Intent(context_, WorkExperienceActivity::class.java)
//            intent.putExtra("idWorkExperience", workExperience.id.toString())
//            context_.startActivity(intent)
//        }
    }
}