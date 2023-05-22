package com.jfalstaff.qrity.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.jfalstaff.qrity.databinding.FragmentReadQrBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReadQrCodeFragment : Fragment() {
    private var _binding: FragmentReadQrBinding? = null
    private val binding get() = _binding ?: throw RuntimeException(READ_BINDING_IS_NULL)
    lateinit var scanner: GmsBarcodeScanner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReadQrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE
            )
            .build()
        scanner = GmsBarcodeScanning.getClient(requireActivity(), options)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                val rawValue: String? = barcode.rawValue
                Log.d("VVV READ", rawValue.toString())
            }
            .addOnCanceledListener {
                Log.d("VVV READ", "cancelled")
            }
            .addOnFailureListener { e ->
                Log.d("VVV READ", "fail")
            }
    }

    companion object {
        private const val READ_BINDING_IS_NULL = "FragmentReadQrBinding is null"
        fun newInstance(): ReadQrCodeFragment = ReadQrCodeFragment()
    }
}