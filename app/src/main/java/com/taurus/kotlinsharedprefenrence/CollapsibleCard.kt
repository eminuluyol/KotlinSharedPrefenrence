package com.taurus.kotlinsharedprefenrence

import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.content.res.AppCompatResources
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView


class CollapsibleCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  private var expanded = false
  private val cardTitleView: TextView
  private val cardChildView: FrameLayout
  private val expandIcon: ImageView
  private val titleContainer: View
  private val toggle: Transition
  private val root: View
  private val cardTitle: String

  init {
    val arr = context.obtainStyledAttributes(attrs, R.styleable.CollapsibleCard, 0, 0)
    cardTitle = arr.getString(R.styleable.CollapsibleCard_cardTitle)
    arr.recycle()
    root = LayoutInflater.from(context).inflate(R.layout.collapsible_card_content, this, true)

    titleContainer = root.findViewById(R.id.title_container)
    cardTitleView = root.findViewById<TextView>(R.id.card_title).apply {
      text = cardTitle
    }
    setTitleContentDescription(cardTitle)
    cardChildView = root.findViewById(R.id.card_description)
    expandIcon = root.findViewById(R.id.expand_icon)
    if (VERSION.SDK_INT < VERSION_CODES.M) {
      expandIcon.imageTintList = AppCompatResources.getColorStateList(context,
          R.color.collapsing_section)
    }
    toggle = TransitionInflater.from(context).inflateTransition(R.transition.info_card_toggle)
    titleContainer.setOnClickListener {
      toggleExpanded()
    }
  }

  private fun setTitleContentDescription(cardTitle: String?) {
    cardTitleView.contentDescription = "$cardTitle, " + if (expanded) {
      resources.getString(R.string.expanded)
    } else {
      resources.getString(R.string.collapsed)
    }
  }

  private fun toggleExpanded() {
    expanded = !expanded
    toggle.duration = if (expanded) 300L else 200L
    TransitionManager.beginDelayedTransition(root.parent as ViewGroup, toggle)
    cardChildView.visibility = if (expanded) View.VISIBLE else View.GONE
    expandIcon.rotation = if (expanded) 180f else 0f
    // activated used to tint controls when expanded
    expandIcon.isActivated = expanded
    cardTitleView.isActivated = expanded
    setTitleContentDescription(cardTitle)
  }

  fun addChildView(view: View) {
    cardChildView.addView(view)
  }

  override fun onSaveInstanceState(): Parcelable {
    val savedState = SavedState(super.onSaveInstanceState())
    savedState.expanded = expanded
    return savedState
  }

  override fun onRestoreInstanceState(state: Parcelable?) {
    val customState = (state as SavedState)
    if (expanded != customState.expanded) {
      toggleExpanded()
    }
    super.onRestoreInstanceState(customState.superState)
  }
}

internal class SavedState : View.BaseSavedState {
  var expanded = false

  constructor(source: Parcel) : super(source) {
    expanded = source.readByte().toInt() != 0
  }

  constructor(superState: Parcelable) : super(superState)

  override fun writeToParcel(out: Parcel, flags: Int) {
    super.writeToParcel(out, flags)
    out.writeByte((if (expanded) 1 else 0).toByte())
  }

  companion object {
    @JvmField
    val CREATOR = object : Parcelable.Creator<SavedState> {
      override fun createFromParcel(source: Parcel): SavedState {
        return SavedState(source)
      }

      override fun newArray(size: Int): Array<SavedState?> {
        return arrayOfNulls(size)
      }
    }
  }
}
