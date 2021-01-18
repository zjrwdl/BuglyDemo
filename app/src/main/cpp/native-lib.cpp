#include <jni.h>
#include <string>
#include <android/log.h>
#include <stdio.h>
#include <signal.h>
#include <inttypes.h>
#include <pthread.h>
#include <errno.h>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include<sys/mman.h>
#include<sys/types.h>
#include<fcntl.h>
#include<string.h>
#include<stdio.h>
#include<unistd.h>
#include <errno.h>
#define slogd(...) __android_log_print(ANDROID_LOG_INFO, "crashreport", __VA_ARGS__)
void* anr_backtrace(void *arg){
    sigset_t sigSet;
    sigemptyset(&sigSet);
    if(sigaddset(&sigSet, SIGQUIT) == -1){
        slogd("add sigquit error");
    } else{
        slogd("add sigquit success");
    }
    sigaddset(&sigSet, SIGUSR1);
    sigprocmask( SIG_BLOCK, &sigSet, NULL );
    pthread_sigmask(SIG_BLOCK, &sigSet, NULL);
    /*if(pthread_sigmask(SIG_BLOCK, &sigSet, NULL) == -1){
        slogd("set SIG_BLOCK error");
    } else{
        slogd("set SIG_BLOCK success");
    }*/
    int sigwaitResult, signalNum;
    slogd("anr_backtrace");
    slogd("sigwait");
    sigwaitResult = sigwait(&sigSet, &signalNum);
    slogd("sigwait end");
    for (;;) {
        slogd("sigwait loop");
    }
}

void *signal_thread (void *arg)
{
    sigset_t sigSet;
    int sigwaitResult, signalNum;
    sleep(1000000);
    for (;;) {
        slogd("sigwait");
        sigwaitResult = sigwait(&sigSet, &signalNum);
        if (sigwaitResult != 0) {
            slogd("sigwait handler error");
        }
        slogd("Signal handling thread got signal %d\n", signalNum);
    }
}
pthread_t sig_thr_id;
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_rockypzhang_buglydemo_NativeCrashJni_pthead(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++ patch2";
    int rc = -1;
    rc = pthread_create (&sig_thr_id, NULL, signal_thread, NULL);
    if (rc != 0) {
        slogd("pthread_create fail");
    }
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_rockypzhang_buglydemo_NativeCrashJni_createNativeAnr(
        JNIEnv* env,
        jobject /* this */) {
    for (int i = 0; i < 8000; ++i) {
        usleep(100000000);
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_example_rockypzhang_buglydemo_NativeCrashJni_createNativeOomMallocCrash(
        JNIEnv* env,
jobject /* this */) {
slogd("mock native malloc oom crash begin");
//for (int i = 0; i < 20000; i++)
while(true)
{
char *p = (char *)malloc(1024 * 1024 * sizeof(char));
if (p == NULL)
{
//slogd("malloc fail");
}
else
{
//slogd("malloc success");
}
}
slogd("mock native malloc oom crash end");
}

extern "C" JNIEXPORT void JNICALL
Java_com_example_rockypzhang_buglydemo_NativeCrashJni_createNativeOomMmapCrash(
        JNIEnv* env,
jobject /* this */) {
slogd("mock native mmap oom crash begin");
//for (int i = 0; i < 1024 * 500; i++)
while(true)
{
int N = 5; // Number of elements for the array
void *base = mmap(NULL, N * sizeof(int),
                PROT_READ | PROT_WRITE,
                MAP_PRIVATE | MAP_ANONYMOUS,
                0, 0);
if (base == (void *)-1)
{
//slogd("mmap fail");
} else {
//slogd("mmap success");
}

}
slogd("mock native mmap oom crash end");
}


extern "C" JNIEXPORT void JNICALL
Java_com_example_rockypzhang_buglydemo_NativeCrashJni_createNativeCrash(
        JNIEnv* env,
        jobject /* this */) {
//kill(getpid(),SIGQUIT);
//kill(getpid(),SIGUSR1);
//pthread_kill(sig_thr_id, SIGQUIT);
    //slogd("mock native crash begin");
    for (int i = 0; i < 8000; ++i) {
        usleep(100000000);
    }
    //slogd("mock native crash end");
    /*while(true){
        char* name = (char *)malloc(1024*1024*sizeof(char));
    }*/
    /*for(int i= 0;i <1400;i++)
    {
        char str[] = "This is runoob.com";
        FILE* file = NULL;
        char filename[] = "/data/data/com.example.rockypzhang.buglydemo/";
        sprintf(filename,"%s%d%s",filename,i,".txt");
        file = fopen(filename,"w");    //创建文件
        slogd("filename:%s",filename);
        if(file == NULL)
        {
            slogd("open file fail");
        }
        fwrite(str,sizeof(str),1,file);
        //fclose(file);
    }*/
    /*void (*pfun)(int data);
    pfun = nullptr;
    pfun(2);*/
    //int *p = 0;
    //*p = 1;
    //abort();
    //sleep(10000000);
    //kill(gettid(), 11);
    /*char *fileName = "tst.txt";
    int fd = open(fileName, O_RDONLY);
    void *base = mmap(NULL, 1, PROT_READ, MAP_PRIVATE, fd, 0);
    if (base == (void *)-1)
    {
        slogd("mmap fail");
    } else {
        slogd("mmap success");
    }*/

}
