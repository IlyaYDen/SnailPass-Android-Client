package com.example.snailpasswordmanager.utils

import kotlin.math.cos
import kotlin.math.sin

object ColorPicker {
    fun rotate(
        x: Int,
        y: Int,
        z: Int,
        radius: Int
    ): DoubleArray {
        val vector =
            doubleArrayOf(radius.toDouble(), 0.0, 0.0) // original vector (x=1, y=0, z=0)
        val rotationX = arrayOf(
            doubleArrayOf(1.0, 0.0, 0.0),
            doubleArrayOf(
                0.0,
                cos(Math.toRadians(x.toDouble())),
                -sin(Math.toRadians(x.toDouble()))
            ),
            doubleArrayOf(
                0.0,
                sin(Math.toRadians(x.toDouble())),
                cos(Math.toRadians(x.toDouble()))
            )
        ) // rotation matrix for 45 degrees around the x-axis
        val rotationY = arrayOf(
            doubleArrayOf(
                cos(Math.toRadians(y.toDouble())),
                0.0,
                sin(Math.toRadians(y.toDouble()))
            ),
            doubleArrayOf(0.0, 1.0, 0.0),
            doubleArrayOf(
                -sin(Math.toRadians(y.toDouble())),
                0.0,
                cos(Math.toRadians(y.toDouble()))
            )
        ) // rotation matrix for 45 degrees around the y-axis
        val rotationZ = arrayOf(
            doubleArrayOf(
                cos(Math.toRadians(z.toDouble())),
                -sin(Math.toRadians(z.toDouble())),
                0.0
            ),
            doubleArrayOf(
                sin(Math.toRadians(z.toDouble())),
                cos(Math.toRadians(z.toDouble())),
                0.0
            ),
            doubleArrayOf(0.0, 0.0, 1.0)
        ) // rotation matrix for 45 degrees around the z-axis

        // multiply the original vector by the overall rotation matrix

        // print the result
        //System.out.println(Arrays.toString(rotatedVector)); // [0.5, -0.5, 0.7071067811865476]


        return matrixVectorMultiply(matrixMultiply(rotationY, rotationZ), vector)
    }

    private fun matrixMultiply(a: Array<DoubleArray>, b: Array<DoubleArray>): Array<DoubleArray> {
        val rowsA = a.size
        val colsA = a[0].size
        val rowsB = b.size
        val colsB = b[0].size
        require(colsA == rowsB) { "Matrices are not compatible for multiplication" }
        val result = Array(rowsA) {
            DoubleArray(
                colsB
            )
        }
        for (i in 0 until rowsA) {
            for (j in 0 until colsB) {
                var sum = 0.0
                for (k in 0 until colsA) {
                    sum += a[i][k] * b[k][j]
                }
                result[i][j] = sum
            }
        }
        return result
    }
    private fun matrixVectorMultiply(
        matrix: Array<DoubleArray>,
        vector: DoubleArray
    ): DoubleArray {
        val rows = matrix.size
        val cols = matrix[0].size
        require(vector.size == cols) { "Vector size does not match matrix size" }
        val result = DoubleArray(rows)
        for (i in 0 until rows) {
            var sum = 0.0
            for (j in 0 until cols) {
                sum += matrix[i][j] * vector[j]
            }
            result[i] = sum
        }
        return result
    }
}