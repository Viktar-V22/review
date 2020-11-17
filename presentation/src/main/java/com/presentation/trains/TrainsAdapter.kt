package com.presentation.trains

import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.core.common.TrainType
import com.interactors.trains.ItemTrain
import com.presentation.R
import com.presentation.core.extensions.inflater
import com.presentation.core.extensions.popupMenu
import com.presentation.core.extensions.safeClicks
import com.presentation.core.extensions.showPopupMenuWithIcons
import com.presentation.core.views.BaseAdapter
import com.presentation.databinding.ItemTrainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import java.util.*
import javax.inject.Inject

class TrainsAdapter @Inject constructor() : BaseAdapter<ItemTrain>() {

    override val data = ArrayList<ItemTrain>()

    @ExperimentalCoroutinesApi
    private val trainsChannel = BroadcastChannel<ItemTrain>(1)

    @FlowPreview
    @ExperimentalCoroutinesApi
    val trains = trainsChannel.safeClicks()

    @ExperimentalCoroutinesApi
    private val menuChannel = BroadcastChannel<Pair<TrainType, TrainMenu>>(1)

    @FlowPreview
    @ExperimentalCoroutinesApi
    val menu = menuChannel.safeClicks()

    @ExperimentalCoroutinesApi
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemTrainBinding.inflate(parent.inflater(), parent, false)
        binding.root.setOnClickListener { trainsChannel.offer(binding.item!!) }
        binding.ivSettings.setOnClickListener {
            val menu = it.popupMenu(R.menu.item_train)
            populateMenu(menu, binding.item!!)
            it.showPopupMenuWithIcons(menu)
        }
        return TrainsHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        (holder as TrainsHolder).bind(getItem(pos))
    }

    override fun diffCallback(old: List<ItemTrain>, actual: List<ItemTrain>): DiffUtil.Callback {
        return TrainsDiff(old, actual)
    }

    @ExperimentalCoroutinesApi
    private fun populateMenu(menu: PopupMenu, item: ItemTrain) {
        for (i in 0 until menu.menu.size()) {
            val menuItem = menu.menu.getItem(i)
            val itemId = menuItem.itemId
            menuItem.isChecked = if (itemId == R.id.mute) !item.isMute else
                item.count.toMenuId() == itemId

            if (itemId == R.id.mute) {
                menuItem.setIcon(if (item.isMute) R.drawable.ic_volume_off else R.drawable.ic_volume_high)
            }
        }

        menu.setOnMenuItemClickListener {
            val data = Pair(item.train, it.itemId.toTrainMenu())
            menuChannel.offer(data)
        }
    }
}