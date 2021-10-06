package com.becomedev.unliminetpro.ui.main.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.data.base.BaseFragment
import com.becomedev.unliminetpro.data.constant.ApplicationConstantKey
import com.becomedev.unliminetpro.data.enum.PromotionNetworkEnum
import com.becomedev.unliminetpro.databinding.FragmentPromotionBinding
import com.becomedev.unliminetpro.extension.callPhone
import com.becomedev.unliminetpro.model.NetworkPromotionModel
import com.becomedev.unliminetpro.model.PromotionModel
import com.becomedev.unliminetpro.ui.main.PromotionAdapter
import com.google.gson.Gson

class PromotionFragment : BaseFragment<FragmentPromotionBinding>() {

    private var promotionType: PromotionNetworkEnum? = null
    private val adapter by lazy { PromotionAdapter() }

    override fun getLayoutId(): Int = R.layout.fragment_promotion

    override fun getExtra(bundle: Bundle?) {
        super.getExtra(bundle)
        bundle?.let {
            promotionType =
                it.get(ApplicationConstantKey.KEY_PROMOTION_TYPE) as PromotionNetworkEnum
        }
    }

    override fun setUp() {
        super.setUp()
        initView()
        initListener()
    }

    private fun initView() {
        binding.rvPromotion.apply {
            layoutManager = LinearLayoutManager(this@PromotionFragment.context)
            adapter = this@PromotionFragment.adapter
        }

        adapter.items = getPromotions().toMutableList()
    }

    private fun initListener() {
        adapter.setOnItemClick { item ->
            requireActivity().callPhone(item.tel)
        }
    }

    private fun getPromotions(): List<NetworkPromotionModel> {

        val inputStream = when (promotionType) {
            PromotionNetworkEnum.TRUE -> requireActivity().assets.open("data_true.json")
            PromotionNetworkEnum.AIS -> requireActivity().assets.open("data_ais.json")
            PromotionNetworkEnum.DTAC -> requireActivity().assets.open("data_dtac.json")
            null -> throw IllegalArgumentException("type not match")
        }

        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, charset("UTF-8"))
        val gson = Gson().fromJson<PromotionModel>(json, PromotionModel::class.java)

        return gson.network
    }
}