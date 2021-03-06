interface A
interface B : A
interface C

fun foo(a: A){}
fun foo(c: C){}

fun B?.bar(a: A, b: B, c: C, a1: A?, b1: B?, c1: C?) {
    foo(this ?: <caret>
}

// EXIST: { itemText:"a" }
// EXIST: { itemText:"b" }
// ABSENT: { itemText:"c" }
// ABSENT: { itemText:"a1" }
// ABSENT: { itemText:"b1" }
// ABSENT: { itemText:"c1" }
// EXIST: { itemText:"!! a1" }
// EXIST: { itemText:"!! b1" }
// ABSENT: { itemText:"!! c1" }
