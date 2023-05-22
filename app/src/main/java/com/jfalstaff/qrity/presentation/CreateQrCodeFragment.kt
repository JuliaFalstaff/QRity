package com.jfalstaff.qrity.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.jfalstaff.qrity.R
import com.jfalstaff.qrity.databinding.FragmentCreateQrBinding
import com.jfalstaff.qrity.domain.BLACK_COLOR
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateQrCodeFragment : Fragment() {
    private var _binding: FragmentCreateQrBinding? = null
    private val binding get() = _binding ?: throw RuntimeException(CREATE_QR_BINDING_IS_NULL)
    private val viewModel: CreateQrCodeViewModel by viewModels()
    private var chosenColor: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateQrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getColorList()
        viewModel.state.observe(viewLifecycleOwner) {
            setSpinnerAdapter(it)
        }
        createQrCode()
    }

    private fun setSpinnerAdapter(colorList: List<String>) {
        //set adapter
        val adapter = ArrayAdapter(requireActivity(), R.layout.item_spinner, colorList)
        adapter.setDropDownViewResource(R.layout.item_spinner)
        binding.colorSpinner.adapter = adapter

        binding.colorSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selected = parent?.getItemAtPosition(position).toString()
                    chosenColor = selected
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    chosenColor = BLACK_COLOR
                }
            }
    }

    private fun createQrCode() = with(binding) {
        createQrCodeButton.setOnClickListener {
            val data = binding.dataEditTextView.text.toString()
            val color = checkColor(chosenColor)
            val url = generateValidUrl(data, color)
            Log.d("VVV", url)
            Glide.with(requireActivity())
                .load(url)
                .into(imageView)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val CREATE_QR_BINDING_IS_NULL = "FragmentCreateQrBinding is null"
        fun newInstance(): CreateQrCodeFragment = CreateQrCodeFragment()
    }
}