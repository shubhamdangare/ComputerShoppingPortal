#include"App.h"
#include<stdlib.h>
#include<dlfcn.h>  // function for dynamic link library

JNIEXPORT jint JNICALL Java_App_calcBill(JNIEnv *env, jclass jobj, jintArray arr){


    int i,sum = 0;
    jsize len  = (*env)->GetArrayLength(env,arr);
    void *p = NULL;
    int (*countTotal)(int [],int) = NULL;


    jint *params = (*env)->GetIntArrayElements(env,arr,0);
    p = dlopen("/home/shubham/Desktop/ComputerShoppingPortail/calcLogic.so",RTLD_LAZY);
    if(!p){
        printf("unable to load libreary %s",dlerror());
    }
    countTotal = dlsym(p,"countTotal");
    if(countTotal==NULL){
        printf("unable to get address of function%s",dlerror());
    }
    sum = countTotal(params,len);
    (*env)->ReleaseIntArrayElements(env,arr,params,0);
    dlclose(p);
    return sum;
}
