package org.ytt.code4good

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.ytt.code4good.databinding.ListItemWithImageBinding
import org.ytt.code4good.viewModels.ChatViewModel

class ChatFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_chat, container, false)

        val viewManager = LinearLayoutManager(activity)
        val viewAdapter = ChatAdapter(List(10) {
            ChatViewModel(
                activity!!.application,
                R.drawable.head_placeholder,
                "I go to school by bus"
            )
        })

        view.findViewById<RecyclerView>(R.id.view_list).also {
            it.setHasFixedSize(true)

            it.layoutManager = viewManager

            it.adapter = viewAdapter
        }

        return view
    }

    private class ChatAdapter(val data: List<ChatViewModel>) :
        RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ListItemWithImageBinding.inflate(layoutInflater, parent, false)

            return MyViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = data[position]
            holder.bind(item)
        }

        override fun getItemCount() = data.size

        private class MyViewHolder(private val binding: ListItemWithImageBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: ChatViewModel) {
                binding.viewModel = item
                binding.executePendingBindings()
            }
        }
    }
}