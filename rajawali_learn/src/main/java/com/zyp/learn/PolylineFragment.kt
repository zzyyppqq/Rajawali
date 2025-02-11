package com.zyp.learn

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.zyp.learn.camera.CameraLine2D
import com.zyp.learn.mock.MockWaveData
import org.rajawali3d.materials.Material
import org.rajawali3d.math.vector.Vector3
import org.rajawali3d.primitives.Line3D
import org.rajawali3d.renderer.ISurfaceRenderer
import org.rajawali3d.renderer.Renderer
import org.rajawali3d.view.IDisplay
import org.rajawali3d.view.ISurface
import org.rajawali3d.view.TextureView
import java.util.Stack
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class LineFragment : Fragment(), IDisplay {

    private lateinit var mRenderer: ISurfaceRenderer
    private var mLineRenderer: LineRenderer? = null

    private lateinit var mRenderSurface: TextureView
    private lateinit var flRoot: FrameLayout
    private lateinit var seekBarX: SeekBar
    private lateinit var seekBarY: SeekBar
    private lateinit var seekBarTranslateX: SeekBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_line, container, false)
        initView(view)
        initRenderer()
        initListener()
        return view
    }

    private fun initView(view: View) {
        flRoot = view.findViewById(R.id.fl_root)
        seekBarX = view.findViewById(R.id.seekBarX)
        seekBarY = view.findViewById(R.id.seekBarY)
        seekBarTranslateX = view.findViewById(R.id.seekBarTranslateX)
        mRenderSurface = view.findViewById(R.id.rajwali_surface)
    }

    private fun initRenderer() {
        mRenderSurface.renderMode = ISurface.RENDERMODE_WHEN_DIRTY
        // mRenderSurface.setFrameRate(0.0)
        mRenderer = createRenderer()
        onBeforeApplyRenderer()
        applyRenderer()

    }

    private fun initListener() {
        seekBarX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                mLineRenderer?.scaleX(progress * 0.1)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar) = Unit
        })
        seekBarY.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                mLineRenderer?.scaleY(progress * 0.1)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar) = Unit
        })

        seekBarTranslateX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                mLineRenderer?.translateX(progress * 20.0)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar) = Unit
        })
    }

    private fun onBeforeApplyRenderer() {

    }

    private fun applyRenderer() {
        mRenderSurface.setSurfaceRenderer(mRenderer)
    }

    override fun createRenderer(): ISurfaceRenderer {
        return LineRenderer(context, this).also {
            mLineRenderer = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        flRoot.removeView(mRenderSurface as View)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LineFragment()
    }
}

//        BaseLineScene.initScene(this)
//        OrthoLineScene.initScene(this)
//        Line2DScene.initScene(this)


class LineRenderer(context: Context?, private val fragment: LineFragment) : Renderer(context) {

    var width = 0

    var height = 0

    var line: Line3D? = null

    val points = Stack<Vector3>()

    val colors: IntArray

    val array = MockWaveData.generateDoubleArray(256, 10, 100.0)

    init {
        colors = IntArray(array.size)
        array.forEachIndexed { index, value ->
            points.add(Vector3(index.toDouble(), value, 0.0))
            if (index < array.size / 3) {
                colors[index] = -0x10000 // red
            } else if (index < array.size * 2 / 3) {
                colors[index] = -0xff0100 // green
            } else {
                colors[index] = -0x100 // yellow
            }
        }
    }

    override fun initScene() {
        val camera2D = CameraLine2D()
        currentScene.switchCamera(camera2D)
        currentScene.backgroundColor = Color.BLACK

        line = Line3D(points, 2f, colors, true)
        val material = Material()
        material.useVertexColors(true)
        line?.material = material
        // 移动
        line?.setPosition(0.0, height / 2.0, 0.0)
        // 缩放
        line?.setScaleX(1.0)
        line?.setScaleY(1.0)
        // 旋转
        line?.setRotation(Vector3.Axis.Z, 0.0)
        //line?.viewPort = ViewPort(0, 0, width / 2, height)
        currentScene.addChild(line)
    }

    fun translateX(offsetX: Double) {
        line?.setPosition(-offsetX, height / 2.0, 0.0)
    }

    fun scaleX(scaleX: Double) {
        line?.setScaleX(scaleX)
    }

    fun scaleY(scaleY: Double) {
        line?.setScaleY(scaleY)
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

    override fun onTouchEvent(event: MotionEvent) {}
}
