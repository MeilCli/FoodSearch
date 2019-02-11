package net.meilcli.foodsearch.views.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import net.meilcli.foodsearch.R
import android.view.View.GONE as visibilityGone
import android.view.View.VISIBLE as visibilityVisible

class StateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    enum class State {
        Progress,
        Success,
        Failed
    }

    val progressLayout: View?
    val successLayout: View?
    val failedLayout: View?

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StateView)

        fun inflateAndAttachView(attrIndex: Int): View? {
            val id = typedArray.getResourceId(attrIndex, -1)
            if (id < 0) {
                return null
            }
            val view = View.inflate(context, id, null)
            addView(view)
            return view
        }

        progressLayout = inflateAndAttachView(R.styleable.StateView_progressStateLayout)
        successLayout = inflateAndAttachView(R.styleable.StateView_successStateLayout)
        failedLayout = inflateAndAttachView(R.styleable.StateView_failedStateLayout)

        updateState(State.Progress)

        typedArray.recycle()
    }

    fun updateState(state: State) {
        when (state) {
            State.Progress -> {
                progressLayout?.visibility = visibilityVisible
                successLayout?.visibility = visibilityGone
                failedLayout?.visibility = visibilityGone
            }
            State.Success -> {
                progressLayout?.visibility = visibilityGone
                successLayout?.visibility = visibilityVisible
                failedLayout?.visibility = visibilityGone
            }
            State.Failed -> {
                progressLayout?.visibility = visibilityGone
                successLayout?.visibility = visibilityGone
                failedLayout?.visibility = visibilityVisible
            }
        }
    }

}