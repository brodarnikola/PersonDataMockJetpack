package com.vjezba.persondatamockjetpack.ui.dialog

import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vjezba.persondatamockjetpack.R
import com.vjezba.persondatamockjetpack.ui.fragments.RoomPersonDetailsFragment
import kotlinx.android.synthetic.main.dialog_delete_user.*
import kotlinx.coroutines.launch

class RoomDeleteUserDialog constructor( val personId: Int, val roomPersonDetailsFragment: RoomPersonDetailsFragment ) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.dialog_delete_user, container, false)
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
            dialog?.setCanceledOnTouchOutside(false)

            val displayRectangle = Rect()
            val window = activity?.window
            window?.decorView?.getWindowVisibleDisplayFrame(displayRectangle)

            view.minimumWidth = (displayRectangle.width() * 1.0f).toInt()
            view.minimumHeight = (displayRectangle.height() * 0.7f).toInt()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDelete.setOnClickListener {
            roomPersonDetailsFragment.deleteUser(personId)
            dismiss()
        }

        cancel.setOnClickListener {
            dismiss()
        }
    }

}