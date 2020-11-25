package com.example.demo

/**
 *@auther          hcy
 *@create          2020-11-06 16:44
 *@Description
 */
fun main(args: Array<String>) {
    print("nihao")
    println("你好 kotlin")
    vars(1,2,3,4,5)
    name ++
    println(name)
    println(anem2)
    println(name3)
    var a =1
    // $字符串模板，后面可以直接是变量，也可以${}使用方法
    var s1 = "a is $a"
    var s2="${s1.replace("is","was")},but now is $a"
    println(s1)
    println(s2)

    var age: String?="23"

    val ages=age!!.toInt()

    val ages1 = age?.toInt()

    val ages2= age?.toInt()?:-1

}




// var声明变量
var name:Int =10
// val 声明常量
val anem2:Int =20
// 声明变量或常量时如果不指定类型，编译器会自动推导类型
var name3=30

fun vars(vararg  v:Int){
    for (vt in v){
        println(vt)
    }
}