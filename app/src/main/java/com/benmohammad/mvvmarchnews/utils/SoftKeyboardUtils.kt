package com.benmohammad.mvvmarchnews.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

object SoftKeyboardUtils {

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        var view = activity.currentFocus

        if(view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        activity.currentFocus?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    fun hideKeyboardWhenClickOutSide(view: View, activity: Activity) {
        if(view !is EditText && view !is Button) {
            view.setOnTouchListener{
                _, _ -> hideSoftKeyboard(activity)
                false
            }
        }
        if(view is ViewGroup) {
            for(i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                hideKeyboardWhenClickOutSide(innerView, activity)
            }
        }
    }

    fun hideKeyboardWhenClickOutsideText(view: View, activity: Activity) {
        if(view !is EditText && view !is Button && view !is TextView) {
            view.setOnTouchListener{
                _, _ -> hideSoftKeyboard(activity)
                false
            }
        }
        if(view is ViewGroup) {
            for(i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                hideKeyboardWhenClickOutsideText(innerView, activity)
            }
        }
    }

    fun closeSoftKeyboard(activity: Activity) {
        val currentFocusView = activity.currentFocus
        currentFocusView?.let {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    fun hideKeyboardInDialogFragment(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun forceShowKeyboard(view: View) {
        view.requestFocus()
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }}