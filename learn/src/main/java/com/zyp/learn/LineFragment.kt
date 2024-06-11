package com.zyp.learn

import android.content.Context
import android.opengl.GLES20
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.zyp.learn.sence.Line2DScene
import org.rajawali3d.renderer.ISurfaceRenderer
import org.rajawali3d.renderer.Renderer
import org.rajawali3d.view.IDisplay
import org.rajawali3d.view.TextureView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LineFragment : Fragment(), IDisplay {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var mRenderSurface: TextureView
    private lateinit var flRoot: FrameLayout

    private lateinit var mRenderer: ISurfaceRenderer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_line, container, false)
        flRoot = view.findViewById(R.id.fl_root)
        // Find the TextureView
        mRenderSurface = view.findViewById(R.id.rajwali_surface)
        mRenderer = createRenderer()
        onBeforeApplyRenderer()
        applyRenderer()
        return view
    }

    private fun onBeforeApplyRenderer() {

    }

    private fun applyRenderer() {
        mRenderSurface.setSurfaceRenderer(mRenderer)
    }


    override fun createRenderer(): ISurfaceRenderer {
        return LineRenderer(context, this)
    }


    class LineRenderer(context: Context?, private val fragment: LineFragment) :
        Renderer(context) {

        private var width = 0
        private var height = 0

        override fun initScene() {

//            BaseLineScene.initScene(currentCamera, currentScene)
//            OrthoLineScene.initScene(this, width, height, currentCamera, currentScene)
            Line2DScene.initScene(this, width, height, currentCamera, currentScene)


        }

        override fun setViewPort(width: Int, height: Int) {
            super.setViewPort(width, height)
        }

        fun setViewPort(x: Int, y: Int, width: Int, height: Int) {
            //if (width != mCurrentViewportWidth || height != mCurrentViewportHeight) {
//                mCurrentViewportWidth = width
//                mCurrentViewportHeight = height
//                currentScene.updateProjectionMatrix(width, height)
                GLES20.glViewport(x, y, width, height)
            //}
        }

        override fun onRenderSurfaceSizeChanged(gl: GL10?, width: Int, height: Int) {
            this.width = width
            this.height = height
            super.onRenderSurfaceSizeChanged(gl, width, height)
        }

        override fun onRenderSurfaceCreated(config: EGLConfig, gl: GL10, width: Int, height: Int) {
            super.onRenderSurfaceCreated(config, gl, width, height)
        }

        override fun onOffsetsChanged(
            xOffset: Float,
            yOffset: Float,
            xOffsetStep: Float,
            yOffsetStep: Float,
            xPixelOffset: Int,
            yPixelOffset: Int
        ) {

        }

        override fun onTouchEvent(event: MotionEvent) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        flRoot.removeView(mRenderSurface as View)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String = "", param2: String = "") = LineFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }

}