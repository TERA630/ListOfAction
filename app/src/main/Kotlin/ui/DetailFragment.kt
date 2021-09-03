package io.terameteo.listofaction.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.terameteo.listofaction.MainViewModel
import io.terameteo.listofaction.R
import io.terameteo.listofaction.databinding.FragmentDetailBinding
import io.terameteo.listofaction.model.DEFAULT_REWARD
import io.terameteo.listofaction.safetyGetList


class DetailFragment : Fragment() {
    private val mViewModel: MainViewModel by activityViewModels()
    private val args:DetailFragmentArgs by navArgs()  // -1 新しいアイテム
    lateinit var mBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = mViewModel.allItemList.safetyGetList()
        val idSafe = args.idToEdit
        val item = list.find { itemEntity -> itemEntity.id == idSafe}
        if(item == null ) {
            Log.w("detailFragment","item ${args.idToEdit} was not found")

        } else {
            mBinding.editTitle.setText(item.title)
            mBinding.editReward.setText(item.reward.toString())
        }

        mBinding.detailCancelButton.setOnClickListener { v ->
            findNavController().navigate()
        }
        mBinding.detailOkButton.setOnClickListener { v ->
            val newTitle = mBinding.editTitle.text.toString()
            val rewardStr = mBinding.editReward.text.toString()
            val reward = if(rewardStr.isBlank()) { DEFAULT_REWARD } else {rewardStr.toInt()}

            mViewModel.appendItem(newTitle,reward, DEFAULT_CATEGORY)
            findNavController().navigate(R.id.action_detailFragment_to_mainFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }

}