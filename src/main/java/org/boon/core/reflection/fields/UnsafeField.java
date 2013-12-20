package org.boon.core.reflection.fields;

import org.boon.core.Typ;
import org.boon.core.Value;
import org.boon.core.reflection.Conversions;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;

import static org.boon.Exceptions.die;
import static org.boon.core.reflection.Conversions.*;


public abstract class UnsafeField implements FieldAccess {


<<<<<<< HEAD
    private static Unsafe getUnsafe () {
=======
    private static Unsafe getUnsafe() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        try {
            Field f = Unsafe.class.getDeclaredField ( "theUnsafe" );
            f.setAccessible ( true );
            return ( Unsafe ) f.get ( null );
        } catch ( Exception e ) {
            return null;
        }
    }

    static final Unsafe unsafe = getUnsafe ();
    protected final Field field;
    protected long offset;
    protected final boolean isFinal;
    protected final Object base;
    protected final boolean isStatic;
    protected final boolean isVolatile;
    protected final boolean qualified;
    protected final boolean readOnly;
    protected final Class<?> type;
    protected final String name;


<<<<<<< HEAD
    public static UnsafeField createUnsafeField ( Field field ) {
=======
    public static UnsafeField createUnsafeField( Field field ) {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        Class<?> type = field.getType ();
        boolean isVolatile = Modifier.isVolatile ( field.getModifiers () );
        if ( !isVolatile ) {
            if ( type == Typ.intgr ) {
                return new IntUnsafeField ( field );
            } else if ( type == Typ.lng ) {
                return new LongUnsafeField ( field );
            } else if ( type == Typ.bt ) {
                return new ByteUnsafeField ( field );
            } else if ( type == Typ.shrt ) {
                return new ShortUnsafeField ( field );
            } else if ( type == Typ.chr ) {
                return new CharUnsafeField ( field );
            } else if ( type == Typ.dbl ) {
                return new DoubleUnsafeField ( field );
            } else if ( type == Typ.flt ) {
                return new FloatUnsafeField ( field );
            } else if ( type == Typ.bln ) {
                return new BooleanUnsafeField ( field );
            } else {
                return new ObjectUnsafeField ( field );
            }
        } else {
            if ( type == Typ.intgr ) {
                return new VolatileIntUnsafeField ( field );
            } else if ( type == Typ.lng ) {
                return new VolatileLongUnsafeField ( field );
            } else if ( type == Typ.bt ) {
                return new VolatileByteUnsafeField ( field );
            } else if ( type == Typ.shrt ) {
                return new VolatileShortUnsafeField ( field );
            } else if ( type == Typ.chr ) {
                return new VolatileCharUnsafeField ( field );
            } else if ( type == Typ.dbl ) {
                return new VolatileDoubleUnsafeField ( field );
            } else if ( type == Typ.flt ) {
                return new VolatileFloatUnsafeField ( field );
            } else if ( type == Typ.bln ) {
                return new VolatileBooleanUnsafeField ( field );
            } else {
                return new ObjectUnsafeField ( field );
            }

        }
    }


<<<<<<< HEAD
    protected UnsafeField ( Field f ) {
=======
    protected UnsafeField( Field f ) {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        name = f.getName ();
        field = f;

        isFinal = Modifier.isFinal ( field.getModifiers () );
        isStatic = Modifier.isStatic ( field.getModifiers () );

        if ( isStatic ) {
            base = unsafe.staticFieldBase ( field );
            offset = unsafe.staticFieldOffset ( field );
        } else {
            offset = unsafe.objectFieldOffset ( field );
            base = null;
        }
        isVolatile = Modifier.isVolatile ( field.getModifiers () );
        qualified = isFinal || isVolatile;
        readOnly = isFinal || isStatic;
        type = f.getType ();
    }


    @Override
    public Object getValue ( Object obj ) {
        if ( type == Typ.intgr ) {
            int i = this.getInt ( obj );
            return Integer.valueOf ( i );
        } else if ( type == Typ.lng ) {
            long l = this.getLong ( obj );
            return Long.valueOf ( l );
        } else if ( type == Typ.bln ) {
            boolean bool = this.getBoolean ( obj );
            return Boolean.valueOf ( bool );
        } else if ( type == Typ.bt ) {
            byte b = this.getByte ( obj );
            return Byte.valueOf ( b );
        } else if ( type == Typ.shrt ) {
            short s = this.getShort ( obj );
            return Short.valueOf ( s );
        } else if ( type == Typ.chr ) {
            char c = this.getChar ( obj );
            return Character.valueOf ( c );
        } else if ( type == Typ.dbl ) {
            double d = this.getDouble ( obj );
            return Double.valueOf ( d );
        } else if ( type == Typ.flt ) {
            float f = this.getFloat ( obj );
            return Float.valueOf ( f );
        } else {
            return this.getObject ( obj );
        }
    }


    @Override
    public void setValue ( Object obj, Object value ) {
        if ( value != null && value.getClass () == this.type ) {
            this.setObject ( obj, value );
            return;
        }

        if ( type == Typ.string ) {
            setObject ( obj, Conversions.coerce ( type, value ) );
        } else if ( type == Typ.intgr ) {
            setInt ( obj, toInt ( value ) );
        } else if ( type == Typ.bln ) {
            setBoolean ( obj, toBoolean ( value ) );
        } else if ( type == Typ.lng ) {
            setLong ( obj, toLong ( value ) );
        } else if ( type == Typ.bt ) {
            setByte ( obj, toByte ( value ) );

        } else if ( type == Typ.shrt ) {
            setShort ( obj, toShort ( value ) );

        } else if ( type == Typ.chr ) {
            setChar ( obj, toChar ( value ) );

        } else if ( type == Typ.dbl ) {
            setDouble ( obj, toDouble ( value ) );

        } else if ( type == Typ.flt ) {
            setFloat ( obj, toFloat ( value ) );

        } else {
            setObject ( obj, Conversions.coerce ( type, value ) );
        }

    }

<<<<<<< HEAD
    public void setFromValue ( Object obj, Value value ) {
=======
    public void setFromValue( Object obj, Value value ) {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc

        if ( type == Typ.string ) {
            setObject ( obj, value.stringValue () );
        } else if ( type == Typ.intgr ) {
            setInt ( obj, value.intValue () );
        } else if ( type == Typ.flt ) {
            setFloat ( obj, value.floatValue () );
        } else if ( type == Typ.dbl ) {
            setDouble ( obj, value.doubleValue () );
        } else if ( type == Typ.lng ) {
            setDouble ( obj, value.longValue () );
        } else if ( type == Typ.bt ) {
            setByte ( obj, value.byteValue () );
        } else if ( type == Typ.bln ) {
            setBoolean ( obj, value.booleanValue () );
        } else if ( type == Typ.shrt ) {
            setShort ( obj, value.shortValue () );
        } else if ( type == Typ.integer ) {
            setObject ( obj, value.intValue () );
        } else if ( type == Typ.floatWrapper ) {
            setObject ( obj, value.floatValue () );
        } else if ( type == Typ.doubleWrapper ) {
            setObject ( obj, value.doubleValue () );
        } else if ( type == Typ.longWrapper ) {
            setObject ( obj, value.longValue () );
        } else if ( type == Typ.byteWrapper ) {
            setObject ( obj, value.byteValue () );
        } else if ( type == Typ.bool ) {
            setObject ( obj, value.booleanValue () );
        } else if ( type == Typ.shortWrapper ) {
            setObject ( obj, value.shortValue () );
        } else if ( type == Typ.bigDecimal ) {
            setObject ( obj, value.bigDecimalValue () );
        } else if ( type == Typ.bigInteger ) {
            setObject ( obj, value.bigIntegerValue () );
        } else if ( type == Typ.date ) {
            setObject ( obj, value.dateValue () );
        } else {
            setObject ( obj, coerce ( type, value ) );
        }
    }


    @Override
    public int getInt ( Object obj ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );
        return 0;
    }

    @Override
    public boolean getBoolean ( Object obj ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );
        return false;
    }


    @Override
    public short getShort ( Object obj ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );
        return 0;
    }


    @Override
    public char getChar ( Object obj ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );
        return 0;
    }


    @Override
    public long getLong ( Object obj ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );
        return 0;
    }


    @Override
    public double getDouble ( Object obj ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );
        return 0;
    }


    @Override
    public float getFloat ( Object obj ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );
        return 0;
    }


    @Override
    public byte getByte ( Object obj ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );
        return 0;
    }


    @Override
    public Object getObject ( Object obj ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );
        return 0;
    }


<<<<<<< HEAD
    public boolean getStaticBoolean () {
=======
    public boolean getStaticBoolean() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return getBoolean ( base );
    }


<<<<<<< HEAD
    public int getStaticInt () {
=======
    public int getStaticInt() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return getInt ( base );
    }


<<<<<<< HEAD
    public short getStaticShort () {
=======
    public short getStaticShort() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return getShort ( base );
    }


<<<<<<< HEAD
    public long getStaticLong () {
        return getLong ( base );
    }

    public double getStaticDouble () {
=======
    public long getStaticLong() {
        return getLong ( base );
    }

    public double getStaticDouble() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return getDouble ( base );
    }


<<<<<<< HEAD
    public float getStaticFloat () {
=======
    public float getStaticFloat() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return getFloat ( base );
    }


<<<<<<< HEAD
    public byte getStaticByte () {
=======
    public byte getStaticByte() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return getByte ( base );
    }


<<<<<<< HEAD
    public Object getObject () {
=======
    public Object getObject() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return getObject ( base );
    }


    @Override
<<<<<<< HEAD
    public Field getField () {
=======
    public Field getField() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return field;
    }


    @Override
<<<<<<< HEAD
    public boolean isFinal () {
        return isFinal;
    }

    public Object getBase () {
=======
    public boolean isFinal() {
        return isFinal;
    }

    public Object getBase() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return base;
    }


<<<<<<< HEAD
    public ParameterizedType getParameterizedType () {
=======
    public ParameterizedType getParameterizedType() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc


        ParameterizedType type = null;

        if ( field != null ) {
            Object obj = field.getGenericType ();

            if ( obj instanceof ParameterizedType ) {

                type = ( ParameterizedType ) obj;
            }

        }

        return type;

    }


    private Class<?> componentClass;

<<<<<<< HEAD
    public Class<?> getComponentClass () {
=======
    public Class<?> getComponentClass() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        if ( componentClass == null ) {
            componentClass = doGetComponentClass ();
        }
        return componentClass;
    }


<<<<<<< HEAD
    private Class<?> doGetComponentClass () {
=======
    private Class<?> doGetComponentClass() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        final ParameterizedType parameterizedType = this.getParameterizedType ();
        if ( parameterizedType == null ) {
            return null;
        } else {
<<<<<<< HEAD
            return ( Class<?> ) ( parameterizedType.getActualTypeArguments ()[ 0 ] );
=======
            return ( Class<?> ) ( parameterizedType.getActualTypeArguments ()[0] );
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        }
    }

    @Override
<<<<<<< HEAD
    public boolean isStatic () {
=======
    public boolean isStatic() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return isStatic;
    }


    @Override
<<<<<<< HEAD
    public boolean isVolatile () {
=======
    public boolean isVolatile() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return isVolatile;
    }


    @Override
<<<<<<< HEAD
    public boolean isQualified () {
=======
    public boolean isQualified() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return qualified;
    }


    @Override
<<<<<<< HEAD
    public boolean isReadOnly () {
=======
    public boolean isReadOnly() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return readOnly;
    }


    @Override
<<<<<<< HEAD
    public Class<?> getType () {
=======
    public Class<?> getType() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return type;
    }


    @Override
<<<<<<< HEAD
    public String getName () {
=======
    public String getName() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return name;
    }


    @Override
    public void setBoolean ( Object obj, boolean value ) {

        die ( String.format ( "Can't call this method on this type %s", this.type ) );

    }


    @Override
    public void setInt ( Object obj, int value ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );

    }


    @Override
    public void setShort ( Object obj, short value ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );


    }


    @Override
    public void setChar ( Object obj, char value ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );

    }


    @Override
    public void setLong ( Object obj, long value ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );

    }


    @Override
    public void setDouble ( Object obj, double value ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );

    }


    @Override
    public void setFloat ( Object obj, float value ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );
    }


    @Override
    public void setByte ( Object obj, byte value ) {
        die ( String.format ( "Can't call this method on this type %s", this.type ) );
    }


    @Override
    public void setObject ( Object obj, Object value ) {
        die ( String.format ( "Can't call this method on this type %s name = %s  value type = %s", this.type, this.name,
                value == null ? "null" : value.getClass () ) );

    }


    @Override
<<<<<<< HEAD
    public String toString () {
=======
    public String toString() {
>>>>>>> 6573736791d65b6ea53d0b71a4c23db4a87188fc
        return "UnsafeField [field=" + field + ", offset=" + offset
                + ", isFinal=" + isFinal + ", base=" + base + ", isStatic="
                + isStatic + ", isVolatile=" + isVolatile + ", qualified="
                + qualified + ", readOnly=" + readOnly + ", type=" + type
                + ", name=" + name + "]";
    }


    private static final class IntUnsafeField extends UnsafeField {

        protected IntUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public final void setInt ( Object obj, int value ) {
            unsafe.putInt ( obj, offset, value );
        }

        @Override
        public final int getInt ( Object obj ) {
            return unsafe.getInt ( obj, offset );
        }
    }

    private static class LongUnsafeField extends UnsafeField {

        protected LongUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setLong ( Object obj, long value ) {
            unsafe.putLong ( obj, offset, value );
        }

        @Override
        public long getLong ( Object obj ) {
            return unsafe.getLong ( obj, offset );
        }
    }

    private static class CharUnsafeField extends UnsafeField {

        protected CharUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setChar ( Object obj, char value ) {
            unsafe.putChar ( obj, offset, value );
        }

        @Override
        public char getChar ( Object obj ) {
            return unsafe.getChar ( obj, offset );
        }
    }

    private static class ByteUnsafeField extends UnsafeField {

        protected ByteUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setByte ( Object obj, byte value ) {
            unsafe.putByte ( obj, offset, value );
        }

        @Override
        public byte getByte ( Object obj ) {
            return unsafe.getByte ( obj, offset );
        }
    }

    private static class ShortUnsafeField extends UnsafeField {

        protected ShortUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setShort ( Object obj, short value ) {
            unsafe.putShort ( obj, offset, value );
        }

        @Override
        public short getShort ( Object obj ) {
            return unsafe.getShort ( obj, offset );
        }
    }

    private static class ObjectUnsafeField extends UnsafeField {

        protected ObjectUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setObject ( Object obj, Object value ) {
            unsafe.putObject ( obj, offset, value );
        }

        @Override
        public Object getObject ( Object obj ) {
            return unsafe.getObject ( obj, offset );
        }
    }

    private static class FloatUnsafeField extends UnsafeField {

        protected FloatUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setFloat ( Object obj, float value ) {
            unsafe.putFloat ( obj, offset, value );
        }

        @Override
        public float getFloat ( Object obj ) {
            return unsafe.getFloat ( obj, offset );
        }
    }

    private static class DoubleUnsafeField extends UnsafeField {

        protected DoubleUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setDouble ( Object obj, double value ) {
            unsafe.putDouble ( obj, offset, value );
        }

        @Override
        public double getDouble ( Object obj ) {
            return unsafe.getDouble ( obj, offset );
        }
    }


    private static class BooleanUnsafeField extends UnsafeField {

        protected BooleanUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setBoolean ( Object obj, boolean value ) {
            unsafe.putBoolean ( obj, offset, value );
        }

        @Override
        public boolean getBoolean ( Object obj ) {
            return unsafe.getBoolean ( obj, offset );
        }
    }


    private static class VolatileIntUnsafeField extends UnsafeField {

        protected VolatileIntUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setInt ( Object obj, int value ) {
            unsafe.putIntVolatile ( obj, offset, value );
        }

        @Override
        public int getInt ( Object obj ) {
            return unsafe.getIntVolatile ( obj, offset );
        }
    }


    private static class VolatileBooleanUnsafeField extends UnsafeField {

        protected VolatileBooleanUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setBoolean ( Object obj, boolean value ) {
            unsafe.putBooleanVolatile ( obj, offset, value );
        }

        @Override
        public boolean getBoolean ( Object obj ) {
            return unsafe.getBooleanVolatile ( obj, offset );
        }
    }

    private static class VolatileLongUnsafeField extends UnsafeField {

        protected VolatileLongUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setLong ( Object obj, long value ) {
            unsafe.putLongVolatile ( obj, offset, value );
        }

        @Override
        public long getLong ( Object obj ) {
            return unsafe.getLongVolatile ( obj, offset );
        }
    }

    private static class VolatileCharUnsafeField extends UnsafeField {

        protected VolatileCharUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setChar ( Object obj, char value ) {
            unsafe.putCharVolatile ( obj, offset, value );
        }

        @Override
        public char getChar ( Object obj ) {
            return unsafe.getCharVolatile ( obj, offset );
        }
    }

    private static class VolatileByteUnsafeField extends UnsafeField {

        protected VolatileByteUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setByte ( Object obj, byte value ) {
            unsafe.putByteVolatile ( obj, offset, value );
        }

        @Override
        public byte getByte ( Object obj ) {
            return unsafe.getByteVolatile ( obj, offset );
        }
    }

    private static class VolatileShortUnsafeField extends UnsafeField {

        protected VolatileShortUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setShort ( Object obj, short value ) {
            unsafe.putShortVolatile ( obj, offset, value );
        }

        @Override
        public short getShort ( Object obj ) {
            return unsafe.getShortVolatile ( obj, offset );
        }
    }

    private static class VolatileObjectUnsafeField extends UnsafeField {

        protected VolatileObjectUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setObject ( Object obj, Object value ) {
            unsafe.putObjectVolatile ( obj, offset, value );
        }

        @Override
        public Object getObject ( Object obj ) {
            return unsafe.getObjectVolatile ( obj, offset );
        }
    }

    private static class VolatileFloatUnsafeField extends UnsafeField {

        protected VolatileFloatUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setFloat ( Object obj, float value ) {
            unsafe.putFloatVolatile ( obj, offset, value );
        }

        @Override
        public float getFloat ( Object obj ) {
            return unsafe.getFloatVolatile ( obj, offset );
        }
    }

    private static class VolatileDoubleUnsafeField extends UnsafeField {

        protected VolatileDoubleUnsafeField ( Field f ) {
            super ( f );
        }

        @Override
        public void setDouble ( Object obj, double value ) {
            unsafe.putDoubleVolatile ( obj, offset, value );
        }

        @Override
        public double getDouble ( Object obj ) {
            return unsafe.getDoubleVolatile ( obj, offset );
        }
    }


}
