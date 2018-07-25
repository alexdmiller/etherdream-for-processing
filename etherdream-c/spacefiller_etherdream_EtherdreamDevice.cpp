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

///*
// * Class:     spacefiller_etherdream_EtherdreamDevice
// * Method:    deviceReady
// * Signature: (I)Z
// */
//JNIEXPORT jboolean JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceReady
//        (JNIEnv *, jobject, jint);
//
///*
// * Class:     spacefiller_etherdream_EtherdreamDevice
// * Method:    deviceWrite
// * Signature: (I[Lspacefiller/ilda/IldaPoint;II)I
// */
//JNIEXPORT jint JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceWrite
//        (JNIEnv *, jobject, jint, jobjectArray, jint, jint);

#ifdef __cplusplus
}
#endif