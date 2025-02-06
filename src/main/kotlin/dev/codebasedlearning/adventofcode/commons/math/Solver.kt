package dev.codebasedlearning.adventofcode.commons.math

import kotlin.math.abs

fun crossProduct(v1: DoubleArray, v2: DoubleArray): DoubleArray {
    require(v1.size == 3 && v2.size == 3) { "requires two DoubleArrays of size 3" }
    return doubleArrayOf(v1[1]*v2[2] - v1[2]*v2[1], v1[2]*v2[0] - v1[0]*v2[2], v1[0]*v2[1] - v1[1]*v2[0])
}

// solves A x = b using Gaussian elimination with partial pivoting;
// A: n x n matrix, b: dim n, both mutated in-place
fun solveGaussian(A: Array<DoubleArray>, b: DoubleArray): DoubleArray {
    val n = A.size
    require(n == b.size) { "A and b must have compatible sizes" }
    require(A.all { it.size == n }) { "A must be square (n x n)" }
    val eps = 1e-14

    // elimination
    for (k in 0 until n) {
        // partial pivot: find largest pivot in k
        var pivotRow = k
        var maxVal = abs(A[k][k])
        for (r in (k+1) until n) {
            val absVal = abs(A[r][k])
            if (absVal > maxVal) {
                pivotRow = r
                maxVal = absVal
            }
        }
        // swap rows if needed
        if (pivotRow != k) {
            val tempRow = A[k]
            A[k] = A[pivotRow]
            A[pivotRow] = tempRow

            val tempB = b[k]
            b[k] = b[pivotRow]
            b[pivotRow] = tempB
        }

        // eliminate below pivot
        val pivot = A[k][k]
        if (abs(pivot) < eps) continue  // open, could handle or throw an error

        for (i in (k+1) until n) {
            val factor = A[i][k] / pivot
            A[i][k] = 0.0
            for (j in (k+1) until n) {
                A[i][j] -= factor * A[k][j]
            }
            b[i] -= factor * b[k]
        }
    }

    // back-substitution
    val x = DoubleArray(n) { 0.0 }
    for (i in (n-1) downTo 0) {
        var sum = b[i]
        for (j in (i+1) until n) {
            sum -= A[i][j]*x[j]
        }
        // same as above, if A[i][i] is small, system might be singular
        x[i] = if (abs(A[i][i]) < eps) 0.0 else sum / A[i][i]
    }
    return x
}
