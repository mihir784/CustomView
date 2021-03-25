package com.example.android.timelinefragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.jetbrains.annotations.Nullable

class ImagesCustomView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    private var imagesBitmap: ArrayList<Bitmap> = ArrayList(0)
    private var coordinates = RectF()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val length = height - 40
        var i = 0f
        val customMargin = 50f // to be set dynamically after some trial and error

        if (imagesBitmap.size > 0) {
            imagesBitmap.forEach {
                coordinates.top = 20f
                coordinates.left = i
                coordinates.right = i + length
                coordinates.bottom = length + 20f
                canvas?.drawBitmap(it, null, coordinates, null)
                i += customMargin
            }
        }
    }

    fun setImages(imageList: ArrayList<String>) {
        imageList.forEach {
            Glide.with(this.context)
                .asBitmap()
                .load(it)
                .circleCrop()
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        imagesBitmap.add(resource)
                        if (imageList.indexOf(it) == imageList.size - 1) {
                            postInvalidate()
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }

}