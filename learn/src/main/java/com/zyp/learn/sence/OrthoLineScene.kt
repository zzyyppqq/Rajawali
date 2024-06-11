package com.zyp.learn.sence

import android.util.Log
import org.rajawali3d.cameras.Camera
import org.rajawali3d.cameras.OrthographicCamera
import org.rajawali3d.materials.Material
import org.rajawali3d.math.vector.Vector3
import org.rajawali3d.primitives.Line3D
import org.rajawali3d.renderer.Renderer
import org.rajawali3d.scene.Scene
import java.util.Stack

object OrthoLineScene {

    fun initScene(render: Renderer, width: Int, height: Int, currentCamera:Camera, currentScene: Scene) {
        Log.i("ZYP", "initScene width: $width, height: $height")


//        currentCamera.setPosition(0.0, 0.0, 10.0)

        val orthoCam = OrthographicCamera()
        orthoCam.setLookAt(0.0, 0.0, 0.0)
        orthoCam.enableLookAt()
        orthoCam.y = 0.0

        currentScene.switchCamera(orthoCam)
//        currentCamera.setProjectionMatrix(width, height)
//
//        render.setViewPort(width, height)

        val points = Stack<Vector3>()
        val colors = IntArray(3)

        points.add(Vector3(-0.5, 0.0, 0.0))
        points.add(Vector3(0.5, 0.0, 0.0))
        points.add(Vector3(0.0, 0.5, 0.0))

        colors[0] = -0x10000 // red
        colors[1] = -0xff0100 // green
        colors[2] = -0x100 // yellow

        val line = Line3D(points, 10f, colors, true)
        val material = Material()
        material.useVertexColors(true)
        line.material = material

        line.setScale(0.5)

        currentScene.addChild(line)
    }
}