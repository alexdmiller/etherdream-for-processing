/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class spacefiller_etherdream_EtherdreamDevice */

#ifndef _Included_spacefiller_etherdream_EtherdreamDevice
#define _Included_spacefiller_etherdream_EtherdreamDevice
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    libStart
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_spacefiller_etherdream_EtherdreamDevice_libStart
  (JNIEnv *, jclass);

/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    dacCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_spacefiller_etherdream_EtherdreamDevice_dacCount
  (JNIEnv *, jclass);

/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    deviceConnect
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceConnect
  (JNIEnv *, jobject, jint);

/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    deviceDisconnect
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceDisconnect
  (JNIEnv *, jobject, jint);

/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    deviceWaitUntilReady
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceWaitUntilReady
  (JNIEnv *, jobject, jint);

/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    deviceReady
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceReady
  (JNIEnv *, jobject, jint);

/*
 * Class:     spacefiller_etherdream_EtherdreamDevice
 * Method:    deviceWrite
 * Signature: (I[Lspacefiller/ilda/IldaPoint;II)I
 */
JNIEXPORT jint JNICALL Java_spacefiller_etherdream_EtherdreamDevice_deviceWrite
  (JNIEnv *, jobject, jint, jobjectArray, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
