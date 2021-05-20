package com.helderpinhal.crypto.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.helderpinhal.crypto.R
import com.helderpinhal.crypto.databinding.CryptoPriceRowBinding
import com.helderpinhal.crypto.databinding.MainFragmentBinding
import com.helderpinhal.crypto.utils.Crypto
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: MainFragmentBinding
    private val adapter = CryptoPricesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.layoutManager = LinearLayoutManager(view.context)
        binding.list.adapter = adapter

        viewModel.prices.observe(viewLifecycleOwner, {
            adapter.data = it
        })
    }

    private class CryptoPricesAdapter : RecyclerView.Adapter<CryptoPricesAdapter.PriceViewHolder>() {

        var data = mapOf<String, String?>()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewHolder {
            return PriceViewHolder.newInstance(parent)
        }

        override fun onBindViewHolder(holder: PriceViewHolder, position: Int) {
            val crypto = Crypto.values()[position]
            val price = data[crypto.id]

            holder.update(crypto, price)
        }

        override fun getItemCount(): Int {
            return Crypto.values().size
        }


        private class PriceViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val binding = CryptoPriceRowBinding.bind(itemView)

            fun update(crypto: Crypto, price: String?) {
                binding.code.text = crypto.code
                binding.name.text = crypto.friendlyName
                binding.price.text = price?.let {
                    "$ $it"
                }
            }

            companion object {
                fun newInstance(parent: ViewGroup): PriceViewHolder {
                    val inflater = LayoutInflater.from(parent.context)
                    val view = inflater.inflate(R.layout.crypto_price_row, parent, false)

                    return PriceViewHolder(view)
                }
            }
        }
    }
}
