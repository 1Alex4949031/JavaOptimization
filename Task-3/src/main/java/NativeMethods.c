#include <jni.h>
#include <stdlib.h>
#include <string.h>
#include "NativeMethods.h"
#include <stdio.h>

// Прототипы функций
void method1();
void method2();
void method3();

// Метод, который "жрет" память и потенциально вызывает сбой
JNIEXPORT void JNICALL Java_NativeMethods_crashMemory(JNIEnv *env, jobject obj) {
     while (1) {
             // Попытка выделить большой блок памяти
             size_t blockSize = 1024 * 1024 * 1024; // 1 гб
             void* memory = malloc(blockSize);
             if (memory != NULL) {
                 // Заполняем выделенную память, чтобы гарантировать, что ОС действительно ее выделила
                 memset(memory, 0, blockSize);
             } else {
                 // Если выделить память не удалось, прекращаем цикл
                 printf("Something goes wrong!");
                 break;
             }
         }
}

// Метод, выделяющий 1 Кб памяти и возвращающий указатель на выделенную память
JNIEXPORT jlong JNICALL Java_NativeMethods_allocateMemory(JNIEnv *env, jobject obj) {
    void* memory = malloc(1024);
    if (memory != NULL) {
        memset(memory, 0, 1024);
    }
}

JNIEXPORT void JNICALL Java_NativeMethods_crashWithStackTrace(JNIEnv *env, jobject obj) {
    method1();
}

void method1() {
    method2();
}

void method2() {
    method3();
}

void method3() {
    printf("Inside method3...\n");
    int *ptr = NULL;
    *ptr = 1;
}

JNIEXPORT jint JNICALL Java_NativeMethods_getStringLength(JNIEnv *env, jobject obj, jstring input) {
    const char *str = (*env)->GetStringUTFChars(env, input, 0);
    if (str == NULL) {
        return 0;
    }
    jint length = (*env)->GetStringUTFLength(env, input);
    (*env)->ReleaseStringUTFChars(env, input, str);
    return length;
}

JNIEXPORT void JNICALL Java_NativeMethods_callObjectMethod(JNIEnv *env, jobject thisObj, jobject javaString){
    // Получаем класс String
    jclass stringClass = (*env)->FindClass(env, "java/lang/String");

    // Находим ID метода length() класса String
    jmethodID mid = (*env)->GetMethodID(env, stringClass, "length", "()I");
    if (mid == NULL) {
        return; // Метод не найден
    }

    // Вызываем метод length(), возвращающий длину строки
    jint length = (*env)->CallIntMethod(env, javaString, mid);

    // Выводим полученную длину строки
    printf("Length of the string: %d\n", length);
}

JNIEXPORT void JNICALL Java_NativeMethods_changeObjectField(JNIEnv *env, jobject thisObj, jobject personObj){
    // Получаем класс объекта Person
    jclass personClass = (*env)->GetObjectClass(env, personObj);

    // Находим ID поля age
    jfieldID fidAge = (*env)->GetFieldID(env, personClass, "age", "I");
    if (fidAge == NULL) {
        return; // Не удалось найти поле
    }

    // Получаем текущее значение поля age
    jint age = (*env)->GetIntField(env, personObj, fidAge);

    // Изменяем значение поля age (например, увеличиваем на 1)
    age++;
    (*env)->SetIntField(env, personObj, fidAge, age);
}

typedef struct AgeStruct {
    int value;
} AgeStruct;

JNIEXPORT jlong JNICALL Java_NativeMethods_allocateStructure(JNIEnv *end, jobject obj){
    AgeStruct* ageStruct = (AgeStruct*) malloc(sizeof(AgeStruct));
    ageStruct->value = 42;
    printf("1)The structure was allocated. Value of the structure is 42.\n");
    return (jlong)ageStruct;
}

JNIEXPORT jlong JNICALL Java_NativeMethods_getStructureValue(JNIEnv *env, jobject thisObj, jlong structValue){
    AgeStruct* ageStruct = (AgeStruct*) structValue;
    if (ageStruct == NULL) return 0;
    return ageStruct->value;
}

JNIEXPORT void JNICALL Java_NativeMethods_freeStructure(JNIEnv *env, jobject thisObj, jlong structValue){
    AgeStruct* ageStruct = (AgeStruct*) structValue;
    free(ageStruct);
    printf("2)The structure was cleared.\n");
}





