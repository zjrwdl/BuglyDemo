#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_rockypzhang_buglydemo_NativeCrashJni_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++ patch2";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_example_rockypzhang_buglydemo_NativeCrashJni_createNativeCrash(
        JNIEnv* env,
        jobject /* this */) {
    int *p = 0;
    *p = 1;
}
