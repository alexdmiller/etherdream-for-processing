//
// Created by Alex on 7/24/18.
//

#include <darwin/jni_md.h>
#include <jni.h>
#include "etherdream.h"

#ifdef __cplusplus
extern "C" {
#endif
jint Java_spacefiller_etherdream_EtherdreamDevice_doSomething(JNIEnv * env, jobject obj) {
    etherdream_lib_start();
    return 1;
}
#ifdef __cplusplus
}
#endif