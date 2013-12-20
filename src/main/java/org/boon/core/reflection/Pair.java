package org.boon.core.reflection;


public class Pair<T> {

    private T first;
    private T second;
    private T[] both = ( T[] ) new Object[ 2 ];

<<<<<<< HEAD
    public Pair () {
=======
    public Pair() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
    }

    public Pair ( T f, T s ) {
        this.first = f;
        this.second = s;
        both[ 0 ] = f;
        both[ 1 ] = s;
    }


<<<<<<< HEAD
    public T getFirst () {
        return first;
    }

    public T getSecond () {
=======
    public T getFirst() {
        return first;
    }

    public T getSecond() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return second;
    }


<<<<<<< HEAD
    public T[] getBoth () {
=======
    public T[] getBoth() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return both;
    }

    public void setFirst ( T first ) {
        this.first = first;
        both[ 0 ] = first;

    }

    public void setSecond ( T second ) {
        this.second = second;
        both[ 1 ] = second;

    }

    public void setBoth ( T[] both ) {
        this.both = both;
        this.first = both[ 0 ];
        this.second = both[ 1 ];

    }


}
