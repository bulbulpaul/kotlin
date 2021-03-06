/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package kotlin.collections

/**
 * Provides a skeletal implementation of the [MutableCollection] interface.
 *
 * @param E the type of elements contained in the collection. The collection is invariant on its element type.
 */
public abstract class AbstractMutableCollection<E> protected constructor() : AbstractCollection<E>(), MutableCollection<E> {

    abstract override fun add(element: E): Boolean

    override fun remove(element: E): Boolean {
        val iterator = iterator()
        while (iterator.hasNext()) {
            if (iterator.next() == element) {
                iterator.remove()
                return true
            }
        }
        return false
    }

    override fun addAll(elements: Collection<E>): Boolean {
        var modified = false
        for (element in elements) {
            if (add(element)) modified = true
        }
        return modified
    }

    override fun removeAll(elements: Collection<E>): Boolean = (this as MutableIterable<E>).removeAll { it in elements }
    override fun retainAll(elements: Collection<E>): Boolean = (this as MutableIterable<E>).removeAll { it !in elements }

    override fun clear(): Unit {
        val iterator = this.iterator()
        while (iterator.hasNext()) {
            iterator.next()
            iterator.remove()
        }
    }

    open fun toJSON(): Any = this.toArray()
}

