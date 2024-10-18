package com.monosoft.myprofile.presentation.screens.work_experience

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.monosoft.myprofile.R
import com.monosoft.myprofile.databinding.ActivityMainBinding
import com.monosoft.myprofile.databinding.ActivityWorkExperienceBinding

import com.monosoft.myprofile.domain.models.Skills
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.utils.Resource
import com.monosoft.myprofile.presentation.MainViewModel
import com.monosoft.myprofile.presentation.adapters.ContactMeAdapter
import com.monosoft.myprofile.presentation.adapters.WorkExperienceAdapter
import com.monosoft.myprofile.presentation.viewmodels.WorkExperienceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WorkExperienceActivity : AppCompatActivity() {

    lateinit var binding: ActivityWorkExperienceBinding
    val workExperienceViewModel: WorkExperienceViewModel by viewModels()
    var workExperienceAdapter : WorkExperienceAdapter? = null
    var listSkills : List<Skills> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityWorkExperienceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** recibimos el parametro enviado a travez del intent con putextras**/
        val idWorkExperience = intent.getStringExtra("idWorkExperience").toString() ?: ""
        val company = intent.getStringExtra("company").toString() ?: ""

        idWorkExperience.toString()
        binding.shimmerLayout.startShimmer()
        binding.shimmerLayoutrecyclerSkills.startShimmer()
        binding.shimmerLayoutTextWE.startShimmer()
        binding.lblCompanyWE.setText(company)
        workExperienceViewModel.getWorkExperienceById(idWorkExperience)


        binding.imageBackWE.setOnClickListener{

            finish()
        }

        val mLayoutManager2: RecyclerView.LayoutManager = LinearLayoutManager(
            this@WorkExperienceActivity, LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerSkills.layoutManager = mLayoutManager2
        binding.recyclerSkills.itemAnimator = DefaultItemAnimator()
        binding.recyclerSkills.setLayoutManager(mLayoutManager2)


        lifecycleScope.launch {
            workExperienceViewModel.workExperienceResponse.collect{
                when(it){
                    Resource.Loading -> {
                        loadingState()
                    }
                    is Resource.Success -> {
                        binding.lblTextWE.text = it.data.company
                        binding.lblTextWE.text = it.data.text1

                        //listSkills = it.data.skills
                        workExperienceAdapter = WorkExperienceAdapter(
                            this@WorkExperienceActivity, it.data.skills,""
                        )
                        binding.recyclerSkills.adapter = workExperienceAdapter
                        workExperienceAdapter!!.notifyDataSetChanged()
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.hideShimmer()
                        binding.shimmerLayoutrecyclerSkills.stopShimmer()
                        binding.shimmerLayoutrecyclerSkills.hideShimmer()
                        binding.shimmerLayoutTextWE.stopShimmer()
                        binding.shimmerLayoutTextWE.hideShimmer()
                        binding.shimmerLayoutrecyclerSkills.visibility = View.GONE
                        binding.recyclerSkills.visibility = View.VISIBLE
                    }
                    is Resource.Failure -> {}
                    else -> {}
                }
            }
        }
    }


    private fun loadingState() {
        //binding.pb.isVisible = true
    }

    private fun closeLoadingState() {
        //binding.pb.isVisible = false
    }

    fun showSnackBar(msg: SuccesModel) {
        Snackbar.make(binding.constraint, msg.msg, Snackbar.LENGTH_SHORT).show()
    }
}