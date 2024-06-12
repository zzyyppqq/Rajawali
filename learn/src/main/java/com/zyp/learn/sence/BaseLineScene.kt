package com.zyp.learn.sence

import com.zyp.learn.LineRenderer
import org.rajawali3d.materials.Material
import org.rajawali3d.math.vector.Vector3
import org.rajawali3d.primitives.Line3D
import java.util.Stack

object BaseLineScene {

    fun initScene(renderer: LineRenderer) {

        renderer.currentCamera.setPosition(0.0, 0.0, 27.0)

        val points = Stack<Vector3>()
        val colors = IntArray(3)

        points.add(Vector3(-2.0, 0.0, 1.0))
        points.add(Vector3(-1.0, -1.0, 2.0))
        points.add(Vector3(0.0, 2.0, 4.0))

        colors[0] = -0x10000 // red
        colors[1] = -0xff0100 // green
        colors[2] = -0x100 // yellow

        val line = Line3D(points, 1f, colors, true)
        val material = Material()
        material.useVertexColors(true)
        line.material = material


        renderer.currentScene.addChild(line)
    }
}