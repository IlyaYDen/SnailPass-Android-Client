package com.example.snailpasswordmanager.presentation

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

class MyGLRenderer : GLSurfaceView.Renderer {
    override fun onDrawFrame(unused: GL10) {
        //Redraw background
    }
    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        // Set the viewport to the size of the view.
        GLES20.glViewport(0, 0, width, height)
    }
}