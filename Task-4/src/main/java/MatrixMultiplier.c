#include "MatrixMultiplier.h"
#include <jni.h>
#include <stdio.h>
#include <stdlib.h>

JNIEXPORT jobjectArray JNICALL Java_MatrixMultiplier_multiplyMatricesNative(JNIEnv *env, jobject thisObj, jobjectArray a, jobjectArray b) {
    jint aRows = (*env)->GetArrayLength(env, a);
    jint bColumns = (*env)->GetArrayLength(env, (*env)->GetObjectArrayElement(env, b, 0));
    jint aColumns = (*env)->GetArrayLength(env, (*env)->GetObjectArrayElement(env, a, 0));
    jint bRows = (*env)->GetArrayLength(env, b);

    if (aColumns != bRows) {
        jclass illegalArgumentExceptionClass = (*env)->FindClass(env, "java/lang/IllegalArgumentException");
        (*env)->ThrowNew(env, illegalArgumentExceptionClass, "Matrix dimensions do not match for multiplication.");
        return NULL;
    }

    jclass intArrayClass = (*env)->FindClass(env, "[I");
    jobjectArray result = (*env)->NewObjectArray(env, aRows, intArrayClass, NULL);

    // Allocate memory for the entire b matrix and transpose it
    jint **bTransposed = (jint **)malloc(bColumns * sizeof(jint *));
    for (int i = 0; i < bColumns; i++) {
        bTransposed[i] = (jint *)malloc(bRows * sizeof(jint));
    }

    for (int i = 0; i < bRows; i++) {
        jintArray bRow = (jintArray)(*env)->GetObjectArrayElement(env, b, i);
        jint *bRowElements = (*env)->GetIntArrayElements(env, bRow, NULL);

        for (int j = 0; j < bColumns; j++) {
            bTransposed[j][i] = bRowElements[j];
        }

        (*env)->ReleaseIntArrayElements(env, bRow, bRowElements, 0);
    }

    // Perform matrix multiplication
    for (int i = 0; i < aRows; i++) {
        jintArray resultRow = (*env)->NewIntArray(env, bColumns);
        jint *resultRowElements = (*env)->GetIntArrayElements(env, resultRow, NULL);

        jintArray aRow = (jintArray)(*env)->GetObjectArrayElement(env, a, i);
        jint *aRowElements = (*env)->GetIntArrayElements(env, aRow, NULL);

        for (int j = 0; j < bColumns; j++) {
            jint sum = 0;
            for (int k = 0; k < aColumns; k++) {
                sum += aRowElements[k] * bTransposed[j][k];
            }
            resultRowElements[j] = sum;
        }

        (*env)->ReleaseIntArrayElements(env, aRow, aRowElements, 0);
        (*env)->SetObjectArrayElement(env, result, i, resultRow);
        (*env)->ReleaseIntArrayElements(env, resultRow, resultRowElements, 0);
    }

    // Free the allocated memory for the transposed matrix
    for (int i = 0; i < bColumns; i++) {
        free(bTransposed[i]);
    }
    free(bTransposed);

    return result;
}