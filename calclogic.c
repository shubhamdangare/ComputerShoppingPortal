#include<stdio.h>
extern int countTotal(int[],int);

int countTotal(int arr[],int len){
    int i,sum=0;
    for(int i=0;i<len;i++){
        sum = sum+arr[i];
    }
    return sum;
}