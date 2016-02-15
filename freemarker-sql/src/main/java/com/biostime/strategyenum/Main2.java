package com.biostime.strategyenum;
// 策略枚举  
enum Calculator {
	
    // 加法策略的实现  
    Add("+"){  
        public int calculate(int a, int b) {  
            return (a + b);  
        }  
    },  // 这里用逗号隔开各个枚举变量  
      
    // 减法策略的实现  
    Sub("-"){  
        public int calculate(int a, int b) {  
            return (a - b);  
        }  
    }; // 这里用逗号结束枚举变量的定义  
      
    Calculator(String type){
    }
    // 定义抽象算法方法，让每个枚举变量来具体实现  
    public abstract int calculate(int a, int b);  
}  
 
public class Main2 {  
    public static void main(String[] args) {  
        // 加法  
        System.out.println(Calculator.Add.calculate(20, 13));  
          
        // 减法  
        System.out.println(Calculator.Sub.calculate(20, 13));  
    }  
} 