//
// Created by Alex on 7/24/18.
//

#include <darwin/jni_md.h>
#include <jni.h>
#include "etherdream.h"

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    libStart
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_spacefiller_etherdream_EtherdreamDevice_libStart
        (JNIEnv *, jobject) {
    etherdream_lib_start();
}

/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    dacCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_spacefiller_etherdream_EtherdreamDevice_dacCount
        (JNIEnv *, jobject) {
    return etherdream_dac_count();
}

/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    deviceConnect
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceConnect
        (JNIEnv *, jobject, jint deviceID) {
    struct etherdream * device = etherdream_get(deviceID);
    if (device == NULL) {
        return -1;
    }

    return etherdream_connect(device);
}

/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    deviceDisconnect
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceDisconnect
        (JNIEnv *, jobject, jint deviceID) {
    struct etherdream * device = etherdream_get(deviceID);
    etherdream_disconnect(device);
}

/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    deviceReady
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceReady
        (JNIEnv *, jobject, jint deviceID) {
    struct etherdream * device = etherdream_get(deviceID);
    return etherdream_is_ready(device);
}


/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    deviceWrite
 * Signature: (I[Lspacefiller/ilda/IldaPoint;II)I
 */
JNIEXPORT jint JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceWrite
        (JNIEnv * env, jobject, jint deviceID, jobjectArray javaPoints, jint numPoints, jint pps) {
    etherdream_point points[numPoints];
    for (int i = 0; i < numPoints; i++) {
        jobject javaPoint = env->GetObjectArrayElement(javaPoints, i);
        jclass clazz = env->GetObjectClass(javaPoint);
        points[i].x = env->GetFloatField(javaPoint, env->GetFieldID(clazz, "x", "F")) * 32767;
        points[i].y = env->GetFloatField(javaPoint, env->GetFieldID(clazz, "y", "F")) * 32767;
        points[i].r = env->GetFloatField(javaPoint, env->GetFieldID(clazz, "r", "F")) * 65535;
        points[i].g = env->GetFloatField(javaPoint, env->GetFieldID(clazz, "g", "F")) * 65535;
        points[i].b = env->GetFloatField(javaPoint, env->GetFieldID(clazz, "b", "F")) * 65535;
        points[i].i = env->GetFloatField(javaPoint, env->GetFieldID(clazz, "a", "F")) * 65535;
    }
    struct etherdream * device = etherdream_get(deviceID);
    return etherdream_write(device, points, numPoints, pps, -1);
}

JNIEXPORT void JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceWaitUntilReady
        (JNIEnv *, jobject, jint deviceID) {
    struct etherdream * device = etherdream_get(deviceID);
    etherdream_wait_for_ready(device);
}

#ifdef __cplusplus
}
#endif