package com.zyp.learn.sence

import android.util.Log
import com.zyp.learn.camera.CameraLine2D
import com.zyp.learn.LineFragment
import org.rajawali3d.cameras.Camera
import org.rajawali3d.materials.Material
import org.rajawali3d.math.vector.Vector3
import org.rajawali3d.primitives.Line3D
import org.rajawali3d.scene.Scene
import java.util.Stack

object Line2DScene {

    fun initScene(render: LineFragment.LineRenderer, width: Int, height: Int, currentCamera:Camera, currentScene: Scene) {
        Log.i("ZYP", "initScene width: $width, height: $height")

        val camera2D = CameraLine2D()

        currentScene.switchCamera(camera2D)

        render.setViewPort(0,0,width / 2, height)

        val points = Stack<Vector3>()
        val colors = IntArray(3)

        points.add(Vector3(0.0, 0.0, 0.0))
        points.add(Vector3(300.0, 0.0, 0.0))
        points.add(Vector3(0.0, 300.0, 0.0))

        colors[0] = -0x10000 // red
        colors[1] = -0xff0100 // green
        colors[2] = -0x100 // yellow

        val line = Line3D(points, 10f, colors, true)
        val material = Material()
        material.useVertexColors(true)
        line.material = material
        // 移动
        line.setPosition(-0.0, 0.0, 0.0)
        // 缩放
        line.setScaleX(0.5)
        line.setScaleY(2.0)
        // 旋转
        line.setRotation(Vector3.Axis.Z, 0.0)

        currentScene.addChild(line)

        render.startRendering()

        render.setViewPort(width/2, 0, width / 2, height)
        val line2 = Line3D(points, 10f, colors, true)
        val material2 = Material()
        material2.useVertexColors(true)
        line2.material = material2
        // 移动
        line2.setPosition(-0.0, 0.0, 0.0)
        // 缩放
        line2.setScaleX(0.5)
        line2.setScaleY(2.0)
        // 旋转
        line2.setRotation(Vector3.Axis.Z, 0.0)

        currentScene.addChild(line2)
    }
}