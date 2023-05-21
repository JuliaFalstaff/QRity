package com.jfalstaff.qrity.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jfalstaff.qrity.R
import com.jfalstaff.qrity.databinding.FragmentCreateQrBinding

class CreateQrCodeFragment : Fragment() {

    private var _binding: FragmentCreateQrBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding is null")
    val viewModel by lazy {
        ViewModelProvider(requireActivity())[CreateQrCodeViewModel::class.java]
    }
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
        viewModel.getColorList2()
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
                    chosenColor = DEFAULT_COLOR
                }
            }
    }

    private fun createQrCode() = with(binding) {
        createQrCodeButton.setOnClickListener {
            val data = binding.dataEditTextView.text.toString()
            val size = checkIsEmpty(sizeEditTextView.toString(), DEFAULT_SIZE)
            val color = checkColor(chosenColor)
            val url = generateValidUrl(data, size, color)
            Log.d("VVV", url)
            Glide.with(requireActivity())
                .load(url)
                .into(imageView)
        }
    }

    private fun checkIsEmpty(string: String, defaultValue: String): String {
        return string.ifEmpty {
            defaultValue
        }
    }

    private fun generateValidUrl(
        data: String,
        size: String,
        color: String
    ): String {
        return "$QR_URL?data=$data&color=$color&size=$size" + "x$size"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val QR_URL = "https://api.qrserver.com/v1/create-qr-code/"
        const val DEFAULT_SIZE = "150x150"
        const val DEFAULT_COLOR = "Black"

        fun newInstance(): CreateQrCodeFragment = CreateQrCodeFragment()
    }
}