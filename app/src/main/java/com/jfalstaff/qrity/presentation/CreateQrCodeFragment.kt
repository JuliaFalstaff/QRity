package com.jfalstaff.qrity.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jfalstaff.qrity.databinding.FragmentCreateQrBinding

class CreateQrCodeFragment : Fragment() {

    private var _binding: FragmentCreateQrBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding is null")

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
        createQrCode()
    }

    private fun createQrCode() = with(binding) {
        createQrCodeButton.setOnClickListener {

            val data = binding.dataEditTextView.text.toString()
            val size = checkIsEmpty (sizeEditTextView, DEFAULT_SIZE)
            val color = checkIsEmpty(colorEditTextView, DEFAULT_COLOR)
            val format = checkIsEmpty(formatEditTextView, DEFAULT_FORMAT)
            val url = generateValidUrl(data, size, color, format)
            Log.d("VVV", url)

            Glide.with(requireActivity())
                .load(url)
                .into(imageView)
            Log.d("VVV", "https://api.qrserver.com/v1/create-qr-code/?data=$data")
        }
    }

    private fun checkIsEmpty(string: EditText, defaultValue: String): String {
        return string.text.toString().ifEmpty {
            defaultValue
        }
    }

    private fun generateValidUrl(
        data: String,
        size: String,
        color: String,
        format: String
    ): String {
        return "$QR_URL?data=$data&color=$color&format=$format&size=$size"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val QR_URL = "https://api.qrserver.com/v1/create-qr-code/"
        const val DEFAULT_SIZE = "150x150"
        const val DEFAULT_COLOR = "0000ff"
        const val DEFAULT_FORMAT = "png"
        fun newInstance(): CreateQrCodeFragment = CreateQrCodeFragment()
    }
}