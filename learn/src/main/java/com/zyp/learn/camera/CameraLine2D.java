//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zyp.learn.camera;

import org.rajawali3d.cameras.Camera;

public class CameraLine2D extends Camera {
    private double mWidth = 1.0;
    private double mHeight = 1.0;

    public CameraLine2D() {
        this.setZ(4.0);
        this.setLookAt(0.0, 0.0, 0.0);
    }

    public CameraLine2D(int width, int height) {
        this.setZ(4.0);
        this.setLookAt(0.0, 0.0, 0.0);
        this.mWidth = width;
        this.mHeight = height;
    }

    public void setProjectionMatrix(int width, int height) {
        this.mProjMatrix.setToOrthographic(0.0, width, height,0.0,  this.mNearPlane, this.mFarPlane);
    }

    public void setWidth(double width) {
        this.mWidth = width;
    }

    public double getWidth() {
        return this.mWidth;
    }

    public void setHeight(double height) {
        this.mHeight = height;
    }

    public double getHeight() {
        return this.mHeight;
    }
}
