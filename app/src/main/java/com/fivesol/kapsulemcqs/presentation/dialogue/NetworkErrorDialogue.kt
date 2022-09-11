package com.fivesol.kapsulemcqs.presentation.dialogue

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import com.fivesol.kapsulemcqs.databinding.LayoutNetworkConnectionBinding

class NetworkErrorDialogue(
    context: Context,
    val onDismissDialogue: () -> Unit
) : Dialog(context) {
    private var binding: LayoutNetworkConnectionBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LayoutNetworkConnectionBinding.inflate(LayoutInflater.from(context)).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            binding = this
            setContentView(this.root)
            setCancelable(true)
        }
        binding?.btnDismiss?.setOnClickListener {
            dismiss()
        }
        binding?.btnClose?.setOnClickListener {
            dismiss()
        }
    }

    override fun dismiss() {
        super.dismiss()
        onDismissDialogue.invoke()
    }
}