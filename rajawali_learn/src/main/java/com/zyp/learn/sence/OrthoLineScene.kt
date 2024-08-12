package com.zyp.learn.sence

import com.zyp.learn.LineRenderer
import org.rajawali3d.cameras.OrthographicCamera
import org.rajawali3d.materials.Material
import org.rajawali3d.math.vector.Vector3
import org.rajawali3d.primitives.Line3D
import java.util.Stack

object OrthoLineScene {

    fun initScene(renderer: LineRenderer) {
        val orthoCam = OrthographicCamera()
        orthoCam.setLookAt(0.0, 0.0, 0.0)
        orthoCam.enableLookAt()
        orthoCam.y = 0.0

        renderer.currentScene.switchCamera(orthoCam)


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

        renderer.currentScene.addChild(line)
    }
}