package com.higor.usertwitter.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputLayout
import com.higor.core.ui.DialogFragmentBase
import com.higor.usertwitter.R

internal class SearchUserDialog() : DialogFragmentBase() {


    private lateinit var tvTitleSearch: TextView
    private lateinit var inputSearchUserName: TextInputLayout
    private lateinit var btnSearch: AppCompatButton
    private var onSearchClickListener: OnSearchClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.search_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        btnSearch.setOnClickListener {
            onSearchClickListener?.OnClickSearch(inputSearchUserName.editText?.text.toString())
            dismiss()
        }
    }

    fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    private fun initView(view: View) {
        tvTitleSearch = view.findViewById(R.id.tvTitleSearch)
        inputSearchUserName = view.findViewById(R.id.inputSearchUserName)
        btnSearch = view.findViewById(R.id.btnSearch)

    }

    interface OnSearchClickListener {
        fun OnClickSearch(text : String)
    }
}