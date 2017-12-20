package com.lionkwon.kwonutils.bytes;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * @class ArrayUtils
 * @brief 배열을 비교,자르기,합치기,찾기,등의 작업을 하는 메소드를 모아놓은 객체
 * 
 * @author lionkwon
 * @date 2011/11/14
 * 
 */
public class ArrayUtils
{
	/// @example BytesUtilsExample.java
    public ArrayUtils()
    {
    }

    /**
     * 두 배열객체를 비교하는 함수
     * @param array1 비교할 배열 Object 객체
     * @param array2 비교할 배열 Object 객체
     * @return true:같으면 반환,false:다르면 반환
     */
    public static boolean isEquals(Object array1, Object array2)
    {
        return (new EqualsBuilder()).append(array1, array2).isEquals();
    }

    public static Map toMap(Object array[])
    {
        if(array == null)
            return null;
        Map map = new HashMap((int)((double)array.length * 1.5D));
        for(int i = 0; i < array.length; i++)
        {
            Object object = array[i];
            if(object instanceof java.util.Map.Entry)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)object;
                map.put(entry.getKey(), entry.getValue());
            } else
            if(object instanceof Object[])
            {
                Object entry[] = (Object[])object;
                if(entry.length < 2)
                    throw new IllegalArgumentException("Array element " + i + ", '" + object + "', has a length less than 2");
                map.put(entry[0], entry[1]);
            } else
            {
                throw new IllegalArgumentException("Array element " + i + ", '" + object + "', is neither of type Map.Entry nor an Array");
            }
        }

        return map;
    }

    /**
     * Object 배열을 복사하는 함수
     * @param array 복사할 배열 Object 객체
     * @return 복사된 Object 배열 반환
     */
    public static Object[] clone(Object array[])
    {
        if(array == null)
            return null;
        else
            return (Object[])array.clone();
    }

    /**
     * long 배열을 복사하는 함수
     * @param array 복사할 long 배열 객체
     * @return 복사된 long 배열 반환
     */
    public static long[] clone(long array[])
    {
        if(array == null)
            return null;
        else
            return (long[])array.clone();
    }

    /**
     * int 배열을 복사하는 함수
     * @param array 복사할 int 배열 객체
     * @return 복사된 int 배열 반환
     */
    public static int[] clone(int array[])
    {
        if(array == null)
            return null;
        else
            return (int[])array.clone();
    }

    /**
     * short 배열을 복사하는 함수
     * @param array 복사할 short 배열 객체
     * @return 복사된 short 배열 반환
     */
    public static short[] clone(short array[])
    {
        if(array == null)
            return null;
        else
            return (short[])array.clone();
    }

    /**
     * char 배열을 복사하는 함수
     * @param array 복사할 char 배열 객체
     * @return 복사된 char 배열 반환
     */
    public static char[] clone(char array[])
    {
        if(array == null)
            return null;
        else
            return (char[])array.clone();
    }

    /**
     * byte 배열을 복사하는 함수
     * @param array 복사할 byte 배열 객체
     * @return 복사된 byte 배열 반환
     */
    public static byte[] clone(byte array[])
    {
        if(array == null)
            return null;
        else
            return (byte[])array.clone();
    }

    /**
     * double 배열을 복사하는 함수
     * @param array 복사할 double 배열 객체
     * @return 복사된 dobule 배열 반환
     */
    public static double[] clone(double array[])
    {
        if(array == null)
            return null;
        else
            return (double[])array.clone();
    }

    /**
     * float 배열을 복사하는 함수
     * @param array 복사할 float 배열 객체
     * @return 복사된 float 배열 반환
     */
    public static float[] clone(float array[])
    {
        if(array == null)
            return null;
        else
            return (float[])array.clone();
    }

    /**
     * boolean 배열을 복사하는 함수
     * @param array 복사할 boolean 배열 객체
     * @return 복사된 boolean 배열 반환
     */
    public static boolean[] clone(boolean array[])
    {
        if(array == null)
            return null;
        else
            return (boolean[])array.clone();
    }

    /**
     * 배열을 특정 크기 만큼 자르는 함수
     * @param array 자를 Object 배열
     * @param startIndexInclusive 배열의 자를 시작 위치
     * @param endIndexExclusive 배열의 자를 마지막 위치
     * @return 지정한 크기만큼 자른 Object 배열 반환
     */
    public static Object[] subarray(Object array[], int startIndexInclusive, int endIndexExclusive)
    {
        if(array == null)
            return null;
        if(startIndexInclusive < 0)
            startIndexInclusive = 0;
        if(endIndexExclusive > array.length)
            endIndexExclusive = array.length;
        int newSize = endIndexExclusive - startIndexInclusive;
        Class type = ((Object) (array)).getClass().getComponentType();
        if(newSize <= 0)
        {
            return (Object[])Array.newInstance(type, 0);
        } else
        {
            Object subarray[] = (Object[])Array.newInstance(type, newSize);
            System.arraycopy(((Object) (array)), startIndexInclusive, ((Object) (subarray)), 0, newSize);
            return subarray;
        }
    }

    /**
     * 배열을 특정 크기 만큼 자르는 함수
     * @param array 자를 long 배열
     * @param startIndexInclusive 배열의 자를 시작 위치
     * @param endIndexExclusive 배열의 자를 마지막 위치
     * @return 지정한 크기만큼 자른 long 배열 반환
     */
    public static long[] subarray(long array[], int startIndexInclusive, int endIndexExclusive)
    {
        if(array == null)
            return null;
        if(startIndexInclusive < 0)
            startIndexInclusive = 0;
        if(endIndexExclusive > array.length)
            endIndexExclusive = array.length;
        int newSize = endIndexExclusive - startIndexInclusive;
        if(newSize <= 0)
        {
            return EMPTY_LONG_ARRAY;
        } else
        {
            long subarray[] = new long[newSize];
            System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
            return subarray;
        }
    }

    /**
     * 배열을 특정 크기 만큼 자르는 함수
     * @param array 자를 int 배열
     * @param startIndexInclusive 배열의 자를 시작 위치
     * @param endIndexExclusive 배열의 자를 마지막 위치
     * @return 지정한 크기만큼 자른 int 배열 반환
     */
    public static int[] subarray(int array[], int startIndexInclusive, int endIndexExclusive)
    {
        if(array == null)
            return null;
        if(startIndexInclusive < 0)
            startIndexInclusive = 0;
        if(endIndexExclusive > array.length)
            endIndexExclusive = array.length;
        int newSize = endIndexExclusive - startIndexInclusive;
        if(newSize <= 0)
        {
            return EMPTY_INT_ARRAY;
        } else
        {
            int subarray[] = new int[newSize];
            System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
            return subarray;
        }
    }

    /**
     * 배열을 특정 크기 만큼 자르는 함수
     * @param array 자를 short 배열
     * @param startIndexInclusive 배열의 자를 시작 위치
     * @param endIndexExclusive 배열의 자를 마지막 위치
     * @return 지정한 크기만큼 자른 short 배열 반환
     */
    public static short[] subarray(short array[], int startIndexInclusive, int endIndexExclusive)
    {
        if(array == null)
            return null;
        if(startIndexInclusive < 0)
            startIndexInclusive = 0;
        if(endIndexExclusive > array.length)
            endIndexExclusive = array.length;
        int newSize = endIndexExclusive - startIndexInclusive;
        if(newSize <= 0)
        {
            return EMPTY_SHORT_ARRAY;
        } else
        {
            short subarray[] = new short[newSize];
            System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
            return subarray;
        }
    }

    /**
     * 배열을 특정 크기 만큼 자르는 함수
     * @param array 자를 char 배열
     * @param startIndexInclusive 배열의 자를 시작 위치
     * @param endIndexExclusive 배열의 자를 마지막 위치
     * @return 지정한 크기만큼 자른 char 배열 반환
     */
    public static char[] subarray(char array[], int startIndexInclusive, int endIndexExclusive)
    {
        if(array == null)
            return null;
        if(startIndexInclusive < 0)
            startIndexInclusive = 0;
        if(endIndexExclusive > array.length)
            endIndexExclusive = array.length;
        int newSize = endIndexExclusive - startIndexInclusive;
        if(newSize <= 0)
        {
            return EMPTY_CHAR_ARRAY;
        } else
        {
            char subarray[] = new char[newSize];
            System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
            return subarray;
        }
    }

    /**
     * 배열을 특정 크기 만큼 자르는 함수
     * @param array 자를 byte 배열
     * @param startIndexInclusive 배열의 자를 시작 위치
     * @param endIndexExclusive 배열의 자를 마지막 위치
     * @return 지정한 크기만큼 자른 byte 배열 반환
     */
    public static byte[] subarray(byte array[], int startIndexInclusive, int endIndexExclusive)
    {
        if(array == null)
            return null;
        if(startIndexInclusive < 0)
            startIndexInclusive = 0;
        if(endIndexExclusive > array.length)
            endIndexExclusive = array.length;
        int newSize = endIndexExclusive - startIndexInclusive;
        if(newSize <= 0)
        {
            return EMPTY_BYTE_ARRAY;
        } else
        {
            byte subarray[] = new byte[newSize];
            System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
            return subarray;
        }
    }

    /**
     * 배열을 특정 크기 만큼 자르는 함수
     * @param array 자를 double 배열
     * @param startIndexInclusive 배열의 자를 시작 위치
     * @param endIndexExclusive 배열의 자를 마지막 위치
     * @return 지정한 크기만큼 자른 double 배열 반환
     */
    public static double[] subarray(double array[], int startIndexInclusive, int endIndexExclusive)
    {
        if(array == null)
            return null;
        if(startIndexInclusive < 0)
            startIndexInclusive = 0;
        if(endIndexExclusive > array.length)
            endIndexExclusive = array.length;
        int newSize = endIndexExclusive - startIndexInclusive;
        if(newSize <= 0)
        {
            return EMPTY_DOUBLE_ARRAY;
        } else
        {
            double subarray[] = new double[newSize];
            System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
            return subarray;
        }
    }

    /**
     * 배열을 특정 크기 만큼 자르는 함수
     * @param array 자를 float 배열
     * @param startIndexInclusive 배열의 자를 시작 위치
     * @param endIndexExclusive 배열의 자를 마지막 위치
     * @return 지정한 크기만큼 자른 float 배열 반환
     */
    public static float[] subarray(float array[], int startIndexInclusive, int endIndexExclusive)
    {
        if(array == null)
            return null;
        if(startIndexInclusive < 0)
            startIndexInclusive = 0;
        if(endIndexExclusive > array.length)
            endIndexExclusive = array.length;
        int newSize = endIndexExclusive - startIndexInclusive;
        if(newSize <= 0)
        {
            return EMPTY_FLOAT_ARRAY;
        } else
        {
            float subarray[] = new float[newSize];
            System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
            return subarray;
        }
    }

    /**
     * 배열을 특정 크기 만큼 자르는 함수
     * @param array 자를 boolean 배열
     * @param startIndexInclusive 배열의 자를 시작 위치
     * @param endIndexExclusive 배열의 자를 마지막 위치
     * @return 지정한 크기만큼 자른 boolean 배열 반환
     */
    public static boolean[] subarray(boolean array[], int startIndexInclusive, int endIndexExclusive)
    {
        if(array == null)
            return null;
        if(startIndexInclusive < 0)
            startIndexInclusive = 0;
        if(endIndexExclusive > array.length)
            endIndexExclusive = array.length;
        int newSize = endIndexExclusive - startIndexInclusive;
        if(newSize <= 0)
        {
            return EMPTY_BOOLEAN_ARRAY;
        } else
        {
            boolean subarray[] = new boolean[newSize];
            System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
            return subarray;
        }
    }

    //두 배열의 길이를 비교하는 함수 :: 길이가 같으면 true 반환, 다르면 false 반환
    public static boolean isSameLength(Object array1[], Object array2[])
    {
        return (array1 != null || array2 == null || array2.length <= 0) && (array2 != null || array1 == null || array1.length <= 0) && (array1 == null || array2 == null || array1.length == array2.length);
    }
    
    //두 배열의 길이를 비교하는 함수 :: 길이가 같으면 true 반환, 다르면 false 반환
    public static boolean isSameLength(long array1[], long array2[])
    {
        return (array1 != null || array2 == null || array2.length <= 0) && (array2 != null || array1 == null || array1.length <= 0) && (array1 == null || array2 == null || array1.length == array2.length);
    }

    //두 배열의 길이를 비교하는 함수 :: 길이가 같으면 true 반환, 다르면 false 반환
    public static boolean isSameLength(int array1[], int array2[])
    {
        return (array1 != null || array2 == null || array2.length <= 0) && (array2 != null || array1 == null || array1.length <= 0) && (array1 == null || array2 == null || array1.length == array2.length);
    }

    //두 배열의 길이를 비교하는 함수 :: 길이가 같으면 true 반환, 다르면 false 반환
    public static boolean isSameLength(short array1[], short array2[])
    {
        return (array1 != null || array2 == null || array2.length <= 0) && (array2 != null || array1 == null || array1.length <= 0) && (array1 == null || array2 == null || array1.length == array2.length);
    }

    //두 배열의 길이를 비교하는 함수 :: 길이가 같으면 true 반환, 다르면 false 반환
    public static boolean isSameLength(char array1[], char array2[])
    {
        return (array1 != null || array2 == null || array2.length <= 0) && (array2 != null || array1 == null || array1.length <= 0) && (array1 == null || array2 == null || array1.length == array2.length);
    }

    //두 배열의 길이를 비교하는 함수 :: 길이가 같으면 true 반환, 다르면 false 반환
    public static boolean isSameLength(byte array1[], byte array2[])
    {
        return (array1 != null || array2 == null || array2.length <= 0) && (array2 != null || array1 == null || array1.length <= 0) && (array1 == null || array2 == null || array1.length == array2.length);
    }

    //두 배열의 길이를 비교하는 함수 :: 길이가 같으면 true 반환, 다르면 false 반환
    public static boolean isSameLength(double array1[], double array2[])
    {
        return (array1 != null || array2 == null || array2.length <= 0) && (array2 != null || array1 == null || array1.length <= 0) && (array1 == null || array2 == null || array1.length == array2.length);
    }

    //두 배열의 길이를 비교하는 함수 :: 길이가 같으면 true 반환, 다르면 false 반환
    public static boolean isSameLength(float array1[], float array2[])
    {
        return (array1 != null || array2 == null || array2.length <= 0) && (array2 != null || array1 == null || array1.length <= 0) && (array1 == null || array2 == null || array1.length == array2.length);
    }

    public static boolean isSameLength(boolean array1[], boolean array2[])
    {
        return (array1 != null || array2 == null || array2.length <= 0) && (array2 != null || array1 == null || array1.length <= 0) && (array1 == null || array2 == null || array1.length == array2.length);
    }

    //배열의 길이를 반환하는 함수
    public static int getLength(Object array)
    {
        if(array == null)
            return 0;
        else
            return Array.getLength(array);
    }

    //두 배열의 Type이 같은지 비교하는 함수 :: 길이가 같으면 true 반환, 다르면 false 반환
    public static boolean isSameType(Object array1, Object array2)
    {
        if(array1 == null || array2 == null)
            throw new IllegalArgumentException("The Array must not be null");
        else
            return array1.getClass().getName().equals(array2.getClass().getName());
    }

    /**
     * 배열의 저장된 순서를 역순으로 sort하는 함수
     * @param array 역순으로 sort할 배열
     * @remark java는 간접 메모리 참조 방식이기 때문에\n
     * 결과값을 반환을하지 않아도 배열의 Data는 변경된다.
     */
    public static void reverse(Object array[])
    {
        if(array == null)
            return;
        int i = 0;
        for(int j = array.length - 1; j > i; i++)
        {
            Object tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }

    }

    /**
     * 배열의 저장된 순서를 역순으로 sort하는 함수
     * @param array 역순으로 sort할 배열
     * @remark java는 간접 메모리 참조 방식이기 때문에\n
     * 결과값을 반환을하지 않아도 배열의 Data는 변경된다.
     */
    public static void reverse(long array[])
    {
        if(array == null)
            return;
        int i = 0;
        for(int j = array.length - 1; j > i; i++)
        {
            long tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }

    }

    /**
     * 배열의 저장된 순서를 역순으로 sort하는 함수
     * @param array 역순으로 sort할 배열
     * @remark java는 간접 메모리 참조 방식이기 때문에\n
     * 결과값을 반환을하지 않아도 배열의 Data는 변경된다.
     */
    public static void reverse(int array[])
    {
        if(array == null)
            return;
        int i = 0;
        for(int j = array.length - 1; j > i; i++)
        {
            int tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }

    }

    /**
     * 배열의 저장된 순서를 역순으로 sort하는 함수
     * @param array 역순으로 sort할 배열
     * @remark java는 간접 메모리 참조 방식이기 때문에\n
     * 결과값을 반환을하지 않아도 배열의 Data는 변경된다.
     */
    public static void reverse(short array[])
    {
        if(array == null)
            return;
        int i = 0;
        for(int j = array.length - 1; j > i; i++)
        {
            short tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }

    }

    /**
     * 배열의 저장된 순서를 역순으로 sort하는 함수
     * @param array 역순으로 sort할 배열
     * @remark java는 간접 메모리 참조 방식이기 때문에\n
     * 결과값을 반환을하지 않아도 배열의 Data는 변경된다.
     */
    public static void reverse(char array[])
    {
        if(array == null)
            return;
        int i = 0;
        for(int j = array.length - 1; j > i; i++)
        {
            char tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }

    }

    /**
     * 배열의 저장된 순서를 역순으로 sort하는 함수
     * @param array 역순으로 sort할 배열
     * @remark java는 간접 메모리 참조 방식이기 때문에\n
     * 결과값을 반환을하지 않아도 배열의 Data는 변경된다.
     */
    public static void reverse(byte array[])
    {
        if(array == null)
            return;
        int i = 0;
        for(int j = array.length - 1; j > i; i++)
        {
            byte tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }

    }

    /**
     * 배열의 저장된 순서를 역순으로 sort하는 함수
     * @param array 역순으로 sort할 배열
     * @remark java는 간접 메모리 참조 방식이기 때문에\n
     * 결과값을 반환을하지 않아도 배열의 Data는 변경된다.
     */
    public static void reverse(double array[])
    {
        if(array == null)
            return;
        int i = 0;
        for(int j = array.length - 1; j > i; i++)
        {
            double tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }

    }

    /**
     * 배열의 저장된 순서를 역순으로 sort하는 함수
     * @param array 역순으로 sort할 배열
     * @remark java는 간접 메모리 참조 방식이기 때문에\n
     * 결과값을 반환을하지 않아도 배열의 Data는 변경된다.
     */
    public static void reverse(float array[])
    {
        if(array == null)
            return;
        int i = 0;
        for(int j = array.length - 1; j > i; i++)
        {
            float tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }

    }

    /**
     * 배열의 저장된 순서를 역순으로 sort하는 함수
     * @param array 역순으로 sort할 배열
     * @remark java는 간접 메모리 참조 방식이기 때문에\n
     * 결과값을 반환을하지 않아도 배열의 Data는 변경된다.
     */
    public static void reverse(boolean array[])
    {
        if(array == null)
            return;
        int i = 0;
        for(int j = array.length - 1; j > i; i++)
        {
            boolean tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }

    }

    /**
     * 배열에서 특정 데이타의 위치를 반환하는 함수 
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(Object array[], Object objectToFind)
    {
        return indexOf(array, objectToFind, 0);
    }

    /**
     * 배열에서 특정 데이타의 위치를 반환하는 함수 
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(Object array[], Object objectToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            startIndex = 0;
        if(objectToFind == null)
        {
            for(int i = startIndex; i < array.length; i++)
                if(array[i] == null)
                    return i;

        } else
        {
            for(int i = startIndex; i < array.length; i++)
                if(objectToFind.equals(array[i]))
                    return i;

        }
        return -1;
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(Object array[], Object objectToFind)
    {
        return lastIndexOf(array, objectToFind, 0x7fffffff);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(Object array[], Object objectToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            return -1;
        if(startIndex >= array.length)
            startIndex = array.length - 1;
        if(objectToFind == null)
        {
            for(int i = startIndex; i >= 0; i--)
                if(array[i] == null)
                    return i;

        } else
        {
            for(int i = startIndex; i >= 0; i--)
                if(objectToFind.equals(array[i]))
                    return i;

        }
        return -1;
    }

    /**
     * 배열에서 특정 데이타가 있는 지 검색하는 함수
     * @param array 검색할 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 특정 데이타가 있으면 true, 특정 데이타가 없으면 false
     */
    public static boolean contains(Object array[], Object objectToFind)
    {
        return indexOf(array, objectToFind) != -1;
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(long array[], long valueToFind)
    {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(long array[], long valueToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            startIndex = 0;
        for(int i = startIndex; i < array.length; i++)
            if(valueToFind == array[i])
                return i;

        return -1;
    }
    
    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(long array[], long valueToFind)
    {
        return lastIndexOf(array, valueToFind, 0x7fffffff);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(long array[], long valueToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            return -1;
        if(startIndex >= array.length)
            startIndex = array.length - 1;
        for(int i = startIndex; i >= 0; i--)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열에서 특정 데이타가 있는 지 검색하는 함수
     * @param array 검색할 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 특정 데이타가 있으면 true, 특정 데이타가 없으면 false
     */
    public static boolean contains(long array[], long valueToFind)
    {
        return indexOf(array, valueToFind) != -1;
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(int array[], int valueToFind)
    {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(int array[], int valueToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            startIndex = 0;
        for(int i = startIndex; i < array.length; i++)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(int array[], int valueToFind)
    {
        return lastIndexOf(array, valueToFind, 0x7fffffff);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(int array[], int valueToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            return -1;
        if(startIndex >= array.length)
            startIndex = array.length - 1;
        for(int i = startIndex; i >= 0; i--)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열에서 특정 데이타가 있는 지 검색하는 함수
     * @param array 검색할 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 특정 데이타가 있으면 true, 특정 데이타가 없으면 false
     */
    public static boolean contains(int array[], int valueToFind)
    {
        return indexOf(array, valueToFind) != -1;
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(short array[], short valueToFind)
    {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(short array[], short valueToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            startIndex = 0;
        for(int i = startIndex; i < array.length; i++)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(short array[], short valueToFind)
    {
        return lastIndexOf(array, valueToFind, 0x7fffffff);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(short array[], short valueToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            return -1;
        if(startIndex >= array.length)
            startIndex = array.length - 1;
        for(int i = startIndex; i >= 0; i--)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열에서 특정 데이타가 있는 지 검색하는 함수
     * @param array 검색할 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 특정 데이타가 있으면 true, 특정 데이타가 없으면 false
     */
    public static boolean contains(short array[], short valueToFind)
    {
        return indexOf(array, valueToFind) != -1;
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(char array[], char valueToFind)
    {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(char array[], char valueToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            startIndex = 0;
        for(int i = startIndex; i < array.length; i++)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(char array[], char valueToFind)
    {
        return lastIndexOf(array, valueToFind, 0x7fffffff);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(char array[], char valueToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            return -1;
        if(startIndex >= array.length)
            startIndex = array.length - 1;
        for(int i = startIndex; i >= 0; i--)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열에서 특정 데이타가 있는 지 검색하는 함수
     * @param array 검색할 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 특정 데이타가 있으면 true, 특정 데이타가 없으면 false
     */
    public static boolean contains(char array[], char valueToFind)
    {
        return indexOf(array, valueToFind) != -1;
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(byte array[], byte valueToFind)
    {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(byte array[], byte valueToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            startIndex = 0;
        for(int i = startIndex; i < array.length; i++)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param valueToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(byte[] array, byte[] valueToFind) {
    	
    	return indexOf(array, valueToFind, 0);
    }
    
    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param valueToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(byte[] array, byte[] valueToFind, int startIndex) {
    	if(array == null || valueToFind == null)
            return -1;
        if(startIndex < 0)
            startIndex = 0;
        
        int index = 0;
        boolean flag = false;
        for(int i=startIndex; i < array.length; i++) {
        	
        	if(valueToFind[index] == array[i]) {
        		index++; flag = true;
        	}else{
        		if(flag && valueToFind.length-1 > index) {
        			flag = false; index = 0;
        		}
        	}
        	
        	if(flag && valueToFind.length-1 == index){
    			index = i-index+1; break;
    		}
        }
    	return index;
    }
    
    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(byte array[], byte valueToFind)
    {
        return lastIndexOf(array, valueToFind, 0x7fffffff);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(byte array[], byte valueToFind, int startIndex)
    {
        if(array == null)
            return -1;
        if(startIndex < 0)
            return -1;
        if(startIndex >= array.length)
            startIndex = array.length - 1;
        for(int i = startIndex; i >= 0; i--)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열에서 특정 데이타가 있는 지 검색하는 함수
     * @param array 검색할 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 특정 데이타가 있으면 true, 특정 데이타가 없으면 false
     */
    public static boolean contains(byte array[], byte valueToFind)
    {
        return indexOf(array, valueToFind) != -1;
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(double array[], double valueToFind)
    {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(double array[], double valueToFind, double tolerance)
    {
        return indexOf(array, valueToFind, 0, tolerance);
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(double array[], double valueToFind, int startIndex)
    {
        if(isEmpty(array))
            return -1;
        if(startIndex < 0)
            startIndex = 0;
        for(int i = startIndex; i < array.length; i++)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(double array[], double valueToFind, int startIndex, double tolerance)
    {
        if(isEmpty(array))
            return -1;
        if(startIndex < 0)
            startIndex = 0;
        double min = valueToFind - tolerance;
        double max = valueToFind + tolerance;
        for(int i = startIndex; i < array.length; i++)
            if(array[i] >= min && array[i] <= max)
                return i;

        return -1;
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(double array[], double valueToFind)
    {
        return lastIndexOf(array, valueToFind, 0x7fffffff);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(double array[], double valueToFind, double tolerance)
    {
        return lastIndexOf(array, valueToFind, 0x7fffffff, tolerance);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(double array[], double valueToFind, int startIndex)
    {
        if(isEmpty(array))
            return -1;
        if(startIndex < 0)
            return -1;
        if(startIndex >= array.length)
            startIndex = array.length - 1;
        for(int i = startIndex; i >= 0; i--)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(double array[], double valueToFind, int startIndex, double tolerance)
    {
        if(isEmpty(array))
            return -1;
        if(startIndex < 0)
            return -1;
        if(startIndex >= array.length)
            startIndex = array.length - 1;
        double min = valueToFind - tolerance;
        double max = valueToFind + tolerance;
        for(int i = startIndex; i >= 0; i--)
            if(array[i] >= min && array[i] <= max)
                return i;

        return -1;
    }

    /**
     * 배열에서 특정 데이타가 있는 지 검색하는 함수
     * @param array 검색할 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 특정 데이타가 있으면 true, 특정 데이타가 없으면 false
     */
    public static boolean contains(double array[], double valueToFind)
    {
        return indexOf(array, valueToFind) != -1;
    }

    /**
     * 배열에서 특정 데이타가 있는 지 검색하는 함수
     * @param array 검색할 배열 객체
     * @param objectToFind 검색할 데이타
     * @param tolerance 검색할 배열 범위 :: 0부터 지정한 범위까지 검색
     * @return 특정 데이타가 있으면 true, 특정 데이타가 없으면 false
     */
    public static boolean contains(double array[], double valueToFind, double tolerance)
    {
        return indexOf(array, valueToFind, 0, tolerance) != -1;
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(float array[], float valueToFind)
    {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(float array[], float valueToFind, int startIndex)
    {
        if(isEmpty(array))
            return -1;
        if(startIndex < 0)
            startIndex = 0;
        for(int i = startIndex; i < array.length; i++)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(float array[], float valueToFind)
    {
        return lastIndexOf(array, valueToFind, 0x7fffffff);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(float array[], float valueToFind, int startIndex)
    {
        if(isEmpty(array))
            return -1;
        if(startIndex < 0)
            return -1;
        if(startIndex >= array.length)
            startIndex = array.length - 1;
        for(int i = startIndex; i >= 0; i--)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열에서 특정 데이타가 있는 지 검색하는 함수
     * @param array 검색할 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 특정 데이타가 있으면 true, 특정 데이타가 없으면 false
     */
    public static boolean contains(float array[], float valueToFind)
    {
        return indexOf(array, valueToFind) != -1;
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(boolean array[], boolean valueToFind)
    {
        return indexOf(array, valueToFind, 0);
    }

    /**
     * 배열의 특정 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int indexOf(boolean array[], boolean valueToFind, int startIndex)
    {
        if(isEmpty(array))
            return -1;
        if(startIndex < 0)
            startIndex = 0;
        for(int i = startIndex; i < array.length; i++)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(boolean array[], boolean valueToFind)
    {
        return lastIndexOf(array, valueToFind, 0x7fffffff);
    }

    /**
     * 배열의 마지막 데이타를 검색하는 함수
     * @param array 검색할 데이타를 가지고있는 배열 객체
     * @param objectToFind 검색할 데이타
     * @param startIndex 배열에서 검색을 시작할 위치 index
     * @return 데이타가 있으면 배열에 저장된 위치 index 반환, 데이타가 없으면 -1 반환
     */
    public static int lastIndexOf(boolean array[], boolean valueToFind, int startIndex)
    {
        if(isEmpty(array))
            return -1;
        if(startIndex < 0)
            return -1;
        if(startIndex >= array.length)
            startIndex = array.length - 1;
        for(int i = startIndex; i >= 0; i--)
            if(valueToFind == array[i])
                return i;

        return -1;
    }

    /**
     * 배열에서 특정 데이타가 있는 지 검색하는 함수
     * @param array 검색할 배열 객체
     * @param objectToFind 검색할 데이타
     * @return 특정 데이타가 있으면 true, 특정 데이타가 없으면 false
     */
    public static boolean contains(boolean array[], boolean valueToFind)
    {
        return indexOf(array, valueToFind) != -1;
    }

    /**
     * Character Object 배열을 char 배열로 변환하는 함수.
     * @param array Character Object 배열 객체
     * @return char 배열로 반환.
     */
    public static char[] toPrimitive(Character array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_CHAR_ARRAY;
        char result[] = new char[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = array[i].charValue();

        return result;
    }

    /**
     * Character Object 배열을 char 배열로 변환하는 함수.
     * @param array Character Object 배열 객체
     * @param valueForNull null 데이타를 대체하는 데이타:: 배열의 데이타가 null인 경우 데이타를 대신 배열에 저장함.
     * @return char 배열로 반환.
     */
    public static char[] toPrimitive(Character array[], char valueForNull)
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_CHAR_ARRAY;
        char result[] = new char[array.length];
        for(int i = 0; i < array.length; i++)
        {
            Character b = array[i];
            result[i] = b != null ? b.charValue() : valueForNull;
        }

        return result;
    }

    /**
     * char 배열을 Character Object 배열로 변환하는 함수.
     * @param array char 배열 객체
     * @return Character Object 배열로 반환.
     */
    public static Character[] toObject(char array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        Character result[] = new Character[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = new Character(array[i]);

        return result;
    }

    /**
     * Long Object 배열을 long 배열로 변환하는 함수.
     * @param array Long Object 배열 객체
     * @return long 배열로 반환.
     */
    public static long[] toPrimitive(Long array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_LONG_ARRAY;
        long result[] = new long[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = array[i].longValue();

        return result;
    }

    /**
     * Long Object 배열을 char 배열로 변환하는 함수.
     * @param array Long Object 배열 객체
     * @param valueForNull null 데이타를 대체하는 데이타:: 배열의 데이타가 null인 경우 데이타를 대신 배열에 저장함.
     * @return long 배열로 반환.
     */
    public static long[] toPrimitive(Long array[], long valueForNull)
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_LONG_ARRAY;
        long result[] = new long[array.length];
        for(int i = 0; i < array.length; i++)
        {
            Long b = array[i];
            result[i] = b != null ? b.longValue() : valueForNull;
        }

        return result;
    }

    /**
     * long 배열을 Long Object 배열로 변환하는 함수.
     * @param array long 배열 객체
     * @return Long Object 배열로 반환.
     */
    public static Long[] toObject(long array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_LONG_OBJECT_ARRAY;
        Long result[] = new Long[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = new Long(array[i]);

        return result;
    }

    /**
     * Integer Object 배열을 int 배열로 변환하는 함수.
     * @param array Integer Object 배열 객체
     * @return int 배열로 반환.
     */
    public static int[] toPrimitive(Integer array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_INT_ARRAY;
        int result[] = new int[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = array[i].intValue();

        return result;
    }

    /**
     * Integer Object 배열을 char 배열로 변환하는 함수.
     * @param array Integer Object 배열 객체
     * @param valueForNull null 데이타를 대체하는 데이타:: 배열의 데이타가 null인 경우 데이타를 대신 배열에 저장함.
     * @return int 배열로 반환.
     */
    public static int[] toPrimitive(Integer array[], int valueForNull)
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_INT_ARRAY;
        int result[] = new int[array.length];
        for(int i = 0; i < array.length; i++)
        {
            Integer b = array[i];
            result[i] = b != null ? b.intValue() : valueForNull;
        }

        return result;
    }

    /**
     * int 배열을 Integer Object 배열로 변환하는 함수.
     * @param array int 배열 객체
     * @return Integer Object 배열로 반환.
     */
    public static Integer[] toObject(int array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_INTEGER_OBJECT_ARRAY;
        Integer result[] = new Integer[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = new Integer(array[i]);

        return result;
    }

    /**
     * Short Object 배열을 short 배열로 변환하는 함수.
     * @param array Short Object 배열 객체
     * @return short 배열로 반환.
     */
    public static short[] toPrimitive(Short array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_SHORT_ARRAY;
        short result[] = new short[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = array[i].shortValue();

        return result;
    }

    /**
     * Short Object 배열을 short 배열로 변환하는 함수.
     * @param array Short Object 배열 객체
     * @param valueForNull null 데이타를 대체하는 데이타:: 배열의 데이타가 null인 경우 데이타를 대신 배열에 저장함.
     * @return short 배열로 반환.
     */
    public static short[] toPrimitive(Short array[], short valueForNull)
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_SHORT_ARRAY;
        short result[] = new short[array.length];
        for(int i = 0; i < array.length; i++)
        {
            Short b = array[i];
            result[i] = b != null ? b.shortValue() : valueForNull;
        }

        return result;
    }

    /**
     * short 배열을 Short Object 배열로 변환하는 함수.
     * @param array short 배열 객체
     * @return Short Object 배열로 반환.
     */
    public static Short[] toObject(short array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_SHORT_OBJECT_ARRAY;
        Short result[] = new Short[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = new Short(array[i]);

        return result;
    }

    /**
     * Byte Object 배열을 byte 배열로 변환하는 함수.
     * @param array Byte Object 배열 객체
     * @return byte 배열로 반환.
     */
    public static byte[] toPrimitive(Byte array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_BYTE_ARRAY;
        byte result[] = new byte[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = array[i].byteValue();

        return result;
    }

    /**
     * Byte Object 배열을 byte 배열로 변환하는 함수.
     * @param array Byte Object 배열 객체
     * @param valueForNull null 데이타를 대체하는 데이타:: 배열의 데이타가 null인 경우 데이타를 대신 배열에 저장함.
     * @return byte 배열로 반환.
     */
    public static byte[] toPrimitive(Byte array[], byte valueForNull)
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_BYTE_ARRAY;
        byte result[] = new byte[array.length];
        for(int i = 0; i < array.length; i++)
        {
            Byte b = array[i];
            result[i] = b != null ? b.byteValue() : valueForNull;
        }

        return result;
    }

    /**
     * byte 배열을 Byte Object 배열로 변환하는 함수.
     * @param array byte 배열 객체
     * @return Byte Object 배열로 반환.
     */
    public static Byte[] toObject(byte array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_BYTE_OBJECT_ARRAY;
        Byte result[] = new Byte[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = new Byte(array[i]);

        return result;
    }

    /**
     * Double Object 배열을 double 배열로 변환하는 함수.
     * @param array Double Object 배열 객체
     * @return double 배열로 반환.
     */
    public static double[] toPrimitive(Double array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_DOUBLE_ARRAY;
        double result[] = new double[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = array[i].doubleValue();

        return result;
    }

    /**
     * Double Object 배열을 double 배열로 변환하는 함수.
     * @param array Double Object 배열 객체
     * @param valueForNull null 데이타를 대체하는 데이타:: 배열의 데이타가 null인 경우 데이타를 대신 배열에 저장함.
     * @return double 배열로 반환.
     */
    public static double[] toPrimitive(Double array[], double valueForNull)
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_DOUBLE_ARRAY;
        double result[] = new double[array.length];
        for(int i = 0; i < array.length; i++)
        {
            Double b = array[i];
            result[i] = b != null ? b.doubleValue() : valueForNull;
        }

        return result;
    }

    /**
     * double 배열을 Double Object 배열로 변환하는 함수.
     * @param array double 배열 객체
     * @return Double Object 배열로 반환.
     */
    public static Double[] toObject(double array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        Double result[] = new Double[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = new Double(array[i]);

        return result;
    }

    
    /**
     * Float Object 배열을 float 배열로 변환하는 함수.
     * @param array Float Object 배열 객체
     * @return float 배열로 반환.
     */
    public static float[] toPrimitive(Float array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_FLOAT_ARRAY;
        float result[] = new float[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = array[i].floatValue();

        return result;
    }

    /**
     * Float Object 배열을 float 배열로 변환하는 함수.
     * @param array Float Object 배열 객체
     * @param valueForNull null 데이타를 대체하는 데이타:: 배열의 데이타가 null인 경우 데이타를 대신 배열에 저장함.
     * @return float 배열로 반환.
     */
    public static float[] toPrimitive(Float array[], float valueForNull)
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_FLOAT_ARRAY;
        float result[] = new float[array.length];
        for(int i = 0; i < array.length; i++)
        {
            Float b = array[i];
            result[i] = b != null ? b.floatValue() : valueForNull;
        }

        return result;
    }

    /**
     * float 배열을 Float Object 배열로 변환하는 함수.
     * @param array float 배열 객체
     * @return Float Object 배열로 반환.
     */
    public static Float[] toObject(float array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_FLOAT_OBJECT_ARRAY;
        Float result[] = new Float[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = new Float(array[i]);

        return result;
    }

    /**
     * Boolean Object 배열을 boolean 배열로 변환하는 함수.
     * @param array Boolean Object 배열 객체
     * @return boolean 배열로 반환.
     */
    public static boolean[] toPrimitive(Boolean array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_BOOLEAN_ARRAY;
        boolean result[] = new boolean[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = array[i].booleanValue();

        return result;
    }

    /**
     * Boolean Object 배열을 boolean 배열로 변환하는 함수.
     * @param array Boolean Object 배열 객체
     * @param valueForNull null 데이타를 대체하는 데이타:: 배열의 데이타가 null인 경우 데이타를 대신 배열에 저장함.
     * @return boolean 배열로 반환.
     */
    public static boolean[] toPrimitive(Boolean array[], boolean valueForNull)
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_BOOLEAN_ARRAY;
        boolean result[] = new boolean[array.length];
        for(int i = 0; i < array.length; i++)
        {
            Boolean b = array[i];
            result[i] = b != null ? b.booleanValue() : valueForNull;
        }

        return result;
    }

    /**
     * boolean 배열을 Boolean Object 배열로 변환하는 함수.
     * @param array boolean 배열 객체
     * @return Boolean Object 배열로 반환.
     */
    public static Boolean[] toObject(boolean array[])
    {
        if(array == null)
            return null;
        if(array.length == 0)
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        Boolean result[] = new Boolean[array.length];
        for(int i = 0; i < array.length; i++)
            result[i] = array[i] ? Boolean.TRUE : Boolean.FALSE;

        return result;
    }

    //배열에 데이타가 없는지 검사하는 함수 :: 데이타가 없으면 true, 데이타가 있으면 false
    public static boolean isEmpty(Object array[])
    {
        return array == null || array.length == 0;
    }

    //배열에 데이타가 없는지 검사하는 함수 :: 데이타가 없으면 true, 데이타가 있으면 false
    public static boolean isEmpty(long array[])
    {
        return array == null || array.length == 0;
    }

    //배열에 데이타가 없는지 검사하는 함수 :: 데이타가 없으면 true, 데이타가 있으면 false
    public static boolean isEmpty(int array[])
    {
        return array == null || array.length == 0;
    }

    //배열에 데이타가 없는지 검사하는 함수 :: 데이타가 없으면 true, 데이타가 있으면 false
    public static boolean isEmpty(short array[])
    {
        return array == null || array.length == 0;
    }

    //배열에 데이타가 없는지 검사하는 함수 :: 데이타가 없으면 true, 데이타가 있으면 false
    public static boolean isEmpty(char array[])
    {
        return array == null || array.length == 0;
    }

    //배열에 데이타가 없는지 검사하는 함수 :: 데이타가 없으면 true, 데이타가 있으면 false
    public static boolean isEmpty(byte array[])
    {
        return array == null || array.length == 0;
    }

    //배열에 데이타가 없는지 검사하는 함수 :: 데이타가 없으면 true, 데이타가 있으면 false
    public static boolean isEmpty(double array[])
    {
        return array == null || array.length == 0;
    }

    //배열에 데이타가 없는지 검사하는 함수 :: 데이타가 없으면 true, 데이타가 있으면 false
    public static boolean isEmpty(float array[])
    {
        return array == null || array.length == 0;
    }

    //배열에 데이타가 없는지 검사하는 함수 :: 데이타가 없으면 true, 데이타가 있으면 false
    public static boolean isEmpty(boolean array[])
    {
        return array == null || array.length == 0;
    }

    /**
     * 두 배열을 합쳐서 새로운 배열을 만드는 함수
     * @param array1 합칠 배열 첫 배열(새로운 배열의 헤더에 해당)
     * @param array2 합칠 배열 두번째 배열(새로운 배열의 끝에 해당)
     * @return 두배열이 합쳐진 하나의 배열 반환
     */
    public static Object[] addAll(Object array1[], Object array2[])
    {
        if(array1 == null)
            return clone(array2);
        if(array2 == null)
        {
            return clone(array1);
        } else
        {
            Object joinedArray[] = (Object[])Array.newInstance(((Object) (array1)).getClass().getComponentType(), array1.length + array2.length);
            System.arraycopy(((Object) (array1)), 0, ((Object) (joinedArray)), 0, array1.length);
            System.arraycopy(((Object) (array2)), 0, ((Object) (joinedArray)), array1.length, array2.length);
            return joinedArray;
        }
    }

    /**
     * 두 배열을 합쳐서 새로운 배열을 만드는 함수
     * @param array1 합칠 배열 첫 배열(새로운 배열의 헤더에 해당)
     * @param array2 합칠 배열 두번째 배열(새로운 배열의 끝에 해당)
     * @return 두배열이 합쳐진 하나의 배열 반환
     */
    public static boolean[] addAll(boolean array1[], boolean array2[])
    {
        if(array1 == null)
            return clone(array2);
        if(array2 == null)
        {
            return clone(array1);
        } else
        {
            boolean joinedArray[] = new boolean[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    /**
     * 두 배열을 합쳐서 새로운 배열을 만드는 함수
     * @param array1 합칠 배열 첫 배열(새로운 배열의 헤더에 해당)
     * @param array2 합칠 배열 두번째 배열(새로운 배열의 끝에 해당)
     * @return 두배열이 합쳐진 하나의 배열 반환
     */
    public static char[] addAll(char array1[], char array2[])
    {
        if(array1 == null)
            return clone(array2);
        if(array2 == null)
        {
            return clone(array1);
        } else
        {
            char joinedArray[] = new char[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    /**
     * 두 배열을 합쳐서 새로운 배열을 만드는 함수
     * @param array1 합칠 배열 첫 배열(새로운 배열의 헤더에 해당)
     * @param array2 합칠 배열 두번째 배열(새로운 배열의 끝에 해당)
     * @return 두배열이 합쳐진 하나의 배열 반환
     */
    public static byte[] addAll(byte array1[], byte array2[])
    {
        if(array1 == null)
            return clone(array2);
        if(array2 == null)
        {
            return clone(array1);
        } else
        {
            byte joinedArray[] = new byte[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    /**
     * 두 배열을 합쳐서 새로운 배열을 만드는 함수
     * @param array1 합칠 배열 첫 배열(새로운 배열의 헤더에 해당)
     * @param array2 합칠 배열 두번째 배열(새로운 배열의 끝에 해당)
     * @return 두배열이 합쳐진 하나의 배열 반환
     */
    public static short[] addAll(short array1[], short array2[])
    {
        if(array1 == null)
            return clone(array2);
        if(array2 == null)
        {
            return clone(array1);
        } else
        {
            short joinedArray[] = new short[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    /**
     * 두 배열을 합쳐서 새로운 배열을 만드는 함수
     * @param array1 합칠 배열 첫 배열(새로운 배열의 헤더에 해당)
     * @param array2 합칠 배열 두번째 배열(새로운 배열의 끝에 해당)
     * @return 두배열이 합쳐진 하나의 배열 반환
     */
    public static int[] addAll(int array1[], int array2[])
    {
        if(array1 == null)
            return clone(array2);
        if(array2 == null)
        {
            return clone(array1);
        } else
        {
            int joinedArray[] = new int[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    /**
     * 두 배열을 합쳐서 새로운 배열을 만드는 함수
     * @param array1 합칠 배열 첫 배열(새로운 배열의 헤더에 해당)
     * @param array2 합칠 배열 두번째 배열(새로운 배열의 끝에 해당)
     * @return 두배열이 합쳐진 하나의 배열 반환
     */
    public static long[] addAll(long array1[], long array2[])
    {
        if(array1 == null)
            return clone(array2);
        if(array2 == null)
        {
            return clone(array1);
        } else
        {
            long joinedArray[] = new long[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    /**
     * 두 배열을 합쳐서 새로운 배열을 만드는 함수
     * @param array1 합칠 배열 첫 배열(새로운 배열의 헤더에 해당)
     * @param array2 합칠 배열 두번째 배열(새로운 배열의 끝에 해당)
     * @return 두배열이 합쳐진 하나의 배열 반환
     */
    public static float[] addAll(float array1[], float array2[])
    {
        if(array1 == null)
            return clone(array2);
        if(array2 == null)
        {
            return clone(array1);
        } else
        {
            float joinedArray[] = new float[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    /**
     * 두 배열을 합쳐서 새로운 배열을 만드는 함수
     * @param array1 합칠 배열 첫 배열(새로운 배열의 헤더에 해당)
     * @param array2 합칠 배열 두번째 배열(새로운 배열의 끝에 해당)
     * @return 두배열이 합쳐진 하나의 배열 반환
     */
    public static double[] addAll(double array1[], double array2[])
    {
        if(array1 == null)
            return clone(array2);
        if(array2 == null)
        {
            return clone(array1);
        } else
        {
            double joinedArray[] = new double[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }

    public static Object[] add(Object array[], Object element)
    {
        Class type = array == null ? element == null ? java.lang.Object.class : element.getClass() : ((Object) (array)).getClass();
        Object newArray[] = (Object[])copyArrayGrow1(((Object) (array)), type);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * 배열에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param element 배열에 추가할 데이타
     * @return 새로운 데이타가 추가된 배열 반환.
     */
    public static boolean[] add(boolean array[], boolean element)
    {
        boolean newArray[] = (boolean[])copyArrayGrow1(array, Boolean.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * 배열에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param element 배열에 추가할 데이타
     * @return 새로운 데이타가 추가된 배열 반환.
     */
    public static byte[] add(byte array[], byte element)
    {
        byte newArray[] = (byte[])copyArrayGrow1(array, Byte.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * 배열에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param element 배열에 추가할 데이타
     * @return 새로운 데이타가 추가된 배열 반환.
     */
    public static char[] add(char array[], char element)
    {
        char newArray[] = (char[])copyArrayGrow1(array, Character.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * 배열에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param element 배열에 추가할 데이타
     * @return 새로운 데이타가 추가된 배열 반환.
     */
    public static double[] add(double array[], double element)
    {
        double newArray[] = (double[])copyArrayGrow1(array, Double.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * 배열에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param element 배열에 추가할 데이타
     * @return 새로운 데이타가 추가된 배열 반환.
     */
    public static float[] add(float array[], float element)
    {
        float newArray[] = (float[])copyArrayGrow1(array, Float.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * 배열에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param element 배열에 추가할 데이타
     * @return 새로운 데이타가 추가된 배열 반환.
     */
    public static int[] add(int array[], int element)
    {
        int newArray[] = (int[])copyArrayGrow1(array, Integer.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * 배열에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param element 배열에 추가할 데이타
     * @return 새로운 데이타가 추가된 배열 반환.
     */
    public static long[] add(long array[], long element)
    {
        long newArray[] = (long[])copyArrayGrow1(array, Long.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * 배열에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param element 배열에 추가할 데이타
     * @return 새로운 데이타가 추가된 배열 반환.
     */
    public static short[] add(short array[], short element)
    {
        short newArray[] = (short[])copyArrayGrow1(array, Short.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    private static Object copyArrayGrow1(Object array, Class newArrayComponentType)
    {
        if(array != null)
        {
            int arrayLength = Array.getLength(array);
            Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        } else
        {
            return Array.newInstance(newArrayComponentType, 1);
        }
    }

    /**
     * 배열의 특정 위치에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param index 배열에 데이타를 추가할 특정 위치.
     * @param element 배열에 추가할 데이타
     * @return 특정 위치에 새로운 데이타가 추가된 배열 반환.
     */
    public static Object[] add(Object array[], int index, Object element)
    {
        Class clss = null;
        if(array != null)
            clss = ((Object) (array)).getClass().getComponentType();
        else
        if(element != null)
            clss = element.getClass();
        else
            return new Object[1];
        return (Object[])add(((Object) (array)), index, element, clss);
    }

    /**
     * 배열의 특정 위치에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param index 배열에 데이타를 추가할 특정 위치.
     * @param element 배열에 추가할 데이타
     * @return 특정 위치에 새로운 데이타가 추가된 배열 반환.
     */
   public static char[] add(char array[], int index, char element)
    {
        return (char[])add(array, index, new Character(element), Character.TYPE);
    }

   /**
    * 배열의 특정 위치에 새로운 데이타를 추가하는 함수
    * @param array 데이타를 추가할 배열객체.
    * @param index 배열에 데이타를 추가할 특정 위치.
    * @param element 배열에 추가할 데이타
    * @return 특정 위치에 새로운 데이타가 추가된 배열 반환.
    */
    public static byte[] add(byte array[], int index, byte element)
    {
        return (byte[])add(array, index, new Byte(element), Byte.TYPE);
    }

   /**
    * 배열의 특정 위치에 새로운 데이타를 추가하는 함수
    * @param array 데이타를 추가할 배열객체.
    * @param index 배열에 데이타를 추가할 특정 위치.
    * @param element 배열에 추가할 데이타
    * @return 특정 위치에 새로운 데이타가 추가된 배열 반환.
    */
    public static short[] add(short array[], int index, short element)
    {
        return (short[])add(array, index, new Short(element), Short.TYPE);
    }

    /**
     * 배열의 특정 위치에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param index 배열에 데이타를 추가할 특정 위치.
     * @param element 배열에 추가할 데이타
     * @return 특정 위치에 새로운 데이타가 추가된 배열 반환.
     */
    public static int[] add(int array[], int index, int element)
    {
        return (int[])add(array, index, new Integer(element), Integer.TYPE);
    }

    /**
     * 배열의 특정 위치에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param index 배열에 데이타를 추가할 특정 위치.
     * @param element 배열에 추가할 데이타
     * @return 특정 위치에 새로운 데이타가 추가된 배열 반환.
     */
    public static long[] add(long array[], int index, long element)
    {
        return (long[])add(array, index, new Long(element), Long.TYPE);
    }

    /**
     * 배열의 특정 위치에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param index 배열에 데이타를 추가할 특정 위치.
     * @param element 배열에 추가할 데이타
     * @return 특정 위치에 새로운 데이타가 추가된 배열 반환.
     */
    public static float[] add(float array[], int index, float element)
    {
        return (float[])add(array, index, new Float(element), Float.TYPE);
    }

    /**
     * 배열의 특정 위치에 새로운 데이타를 추가하는 함수
     * @param array 데이타를 추가할 배열객체.
     * @param index 배열에 데이타를 추가할 특정 위치.
     * @param element 배열에 추가할 데이타
     * @return 특정 위치에 새로운 데이타가 추가된 배열 반환.
     */
    public static double[] add(double array[], int index, double element)
    {
        return (double[])add(array, index, new Double(element), Double.TYPE);
    }

    private static Object add(Object array, int index, Object element, Class clss)
    {
        if(array == null)
            if(index != 0)
            {
                throw new IndexOutOfBoundsException("Index: " + index + ", Length: 0");
            } else
            {
                Object joinedArray = Array.newInstance(clss, 1);
                Array.set(joinedArray, 0, element);
                return joinedArray;
            }
        int length = Array.getLength(array);
        if(index > length || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        Object result = Array.newInstance(clss, length + 1);
        System.arraycopy(array, 0, result, 0, index);
        Array.set(result, index, element);
        if(index < length)
            System.arraycopy(array, index, result, index + 1, length - index);
        return result;
    }

    /**
     * 배열의 특정위치의 데이터를 삭제하는 함수.
     * @param array 특정위치의 데이터를 삭제할 배열
     * @param index 배열의 삭제할 데이타의 위치.
     * @return 특정 위치의 데이터를 삭제한 배열 반환.
     */
    public static Object[] remove(Object array[], int index)
    {
        return (Object[])remove(((Object) (array)), index);
    }

    /**
     * 배열의 특정 데이터를 삭제하는 함수.
     * @param array 특정 데이터를 삭제할 배열
     * @param element 삭제할 데이타.
     * @return 특정 데이터를 삭제한 배열 반환.
     */
    public static Object[] removeElement(Object array[], Object element)
    {
        int index = indexOf(array, element);
        if(index == -1)
            return clone(array);
        else
            return remove(array, index);
    }

    /**
     * 배열의 특정위치의 데이터를 삭제하는 함수.
     * @param array 특정위치의 데이터를 삭제할 배열
     * @param index 배열의 삭제할 데이타의 위치.
     * @return 특정 위치의 데이터를 삭제한 배열 반환.
     */
    public static boolean[] remove(boolean array[], int index)
    {
        return (boolean[])remove((Object)array, index);
    }

    /**
     * 배열의 특정 데이터를 삭제하는 함수.
     * @param array 특정 데이터를 삭제할 배열
     * @param element 삭제할 데이타.
     * @return 특정 데이터를 삭제한 배열 반환.
     */
    public static boolean[] removeElement(boolean array[], boolean element)
    {
        int index = indexOf(array, element);
        if(index == -1)
            return clone(array);
        else
            return remove(array, index);
    }

    /**
     * 배열의 특정위치의 데이터를 삭제하는 함수.
     * @param array 특정위치의 데이터를 삭제할 배열
     * @param index 배열의 삭제할 데이타의 위치.
     * @return 특정 위치의 데이터를 삭제한 배열 반환.
     */
    public static byte[] remove(byte array[], int index)
    {
        return (byte[])remove((Object)array, index);
    }

    /**
     * 배열의 특정 데이터를 삭제하는 함수.
     * @param array 특정 데이터를 삭제할 배열
     * @param element 삭제할 데이타.
     * @return 특정 데이터를 삭제한 배열 반환.
     */
    public static byte[] removeElement(byte array[], byte element)
    {
        int index = indexOf(array, element);
        if(index == -1)
            return clone(array);
        else
            return remove(array, index);
    }

    /**
     * 배열의 특정위치의 데이터를 삭제하는 함수.
     * @param array 특정위치의 데이터를 삭제할 배열
     * @param index 배열의 삭제할 데이타의 위치.
     * @return 특정 위치의 데이터를 삭제한 배열 반환.
     */
    public static char[] remove(char array[], int index)
    {
        return (char[])remove((Object)array, index);
    }

    /**
     * 배열의 특정 데이터를 삭제하는 함수.
     * @param array 특정 데이터를 삭제할 배열
     * @param element 삭제할 데이타.
     * @return 특정 데이터를 삭제한 배열 반환.
     */
    public static char[] removeElement(char array[], char element)
    {
        int index = indexOf(array, element);
        if(index == -1)
            return clone(array);
        else
            return remove(array, index);
    }

    /**
     * 배열의 특정위치의 데이터를 삭제하는 함수.
     * @param array 특정위치의 데이터를 삭제할 배열
     * @param index 배열의 삭제할 데이타의 위치.
     * @return 특정 위치의 데이터를 삭제한 배열 반환.
     */
    public static double[] remove(double array[], int index)
    {
        return (double[])remove((Object)array, index);
    }

    /**
     * 배열의 특정 데이터를 삭제하는 함수.
     * @param array 특정 데이터를 삭제할 배열
     * @param element 삭제할 데이타.
     * @return 특정 데이터를 삭제한 배열 반환.
     */
    public static double[] removeElement(double array[], double element)
    {
        int index = indexOf(array, element);
        if(index == -1)
            return clone(array);
        else
            return remove(array, index);
    }

    /**
     * 배열의 특정위치의 데이터를 삭제하는 함수.
     * @param array 특정위치의 데이터를 삭제할 배열
     * @param index 배열의 삭제할 데이타의 위치.
     * @return 특정 위치의 데이터를 삭제한 배열 반환.
     */
    public static float[] remove(float array[], int index)
    {
        return (float[])remove((Object)array, index);
    }

    /**
     * 배열의 특정 데이터를 삭제하는 함수.
     * @param array 특정 데이터를 삭제할 배열
     * @param element 삭제할 데이타.
     * @return 특정 데이터를 삭제한 배열 반환.
     */
    public static float[] removeElement(float array[], float element)
    {
        int index = indexOf(array, element);
        if(index == -1)
            return clone(array);
        else
            return remove(array, index);
    }

    /**
     * 배열의 특정위치의 데이터를 삭제하는 함수.
     * @param array 특정위치의 데이터를 삭제할 배열
     * @param index 배열의 삭제할 데이타의 위치.
     * @return 특정 위치의 데이터를 삭제한 배열 반환.
     */
    public static int[] remove(int array[], int index)
    {
        return (int[])remove((Object)array, index);
    }

    /**
     * 배열의 특정 데이터를 삭제하는 함수.
     * @param array 특정 데이터를 삭제할 배열
     * @param element 삭제할 데이타.
     * @return 특정 데이터를 삭제한 배열 반환.
     */
    public static int[] removeElement(int array[], int element)
    {
        int index = indexOf(array, element);
        if(index == -1)
            return clone(array);
        else
            return remove(array, index);
    }

    /**
     * 배열의 특정위치의 데이터를 삭제하는 함수.
     * @param array 특정위치의 데이터를 삭제할 배열
     * @param index 배열의 삭제할 데이타의 위치.
     * @return 특정 위치의 데이터를 삭제한 배열 반환.
     */
    public static long[] remove(long array[], int index)
    {
        return (long[])remove((Object)array, index);
    }

    /**
     * 배열의 특정 데이터를 삭제하는 함수.
     * @param array 특정 데이터를 삭제할 배열
     * @param element 삭제할 데이타.
     * @return 특정 데이터를 삭제한 배열 반환.
     */
    public static long[] removeElement(long array[], long element)
    {
        int index = indexOf(array, element);
        if(index == -1)
            return clone(array);
        else
            return remove(array, index);
    }

    /**
     * 배열의 특정위치의 데이터를 삭제하는 함수.
     * @param array 특정위치의 데이터를 삭제할 배열
     * @param index 배열의 삭제할 데이타의 위치.
     * @return 특정 위치의 데이터를 삭제한 배열 반환.
     */
    public static short[] remove(short array[], int index)
    {
        return (short[])remove((Object)array, index);
    }

    /**
     * 배열의 특정 데이터를 삭제하는 함수.
     * @param array 특정 데이터를 삭제할 배열
     * @param element 삭제할 데이타.
     * @return 특정 데이터를 삭제한 배열 반환.
     */
    public static short[] removeElement(short array[], short element)
    {
        int index = indexOf(array, element);
        if(index == -1)
            return clone(array);
        else
            return remove(array, index);
    }

    private static Object remove(Object array, int index)
    {
        int length = getLength(array);
        if(index < 0 || index >= length)
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
        System.arraycopy(array, 0, result, 0, index);
        if(index < length - 1)
            System.arraycopy(array, index + 1, result, index, length - index - 1);
        return result;
    }

    public static final Object EMPTY_OBJECT_ARRAY[] = new Object[0];
    public static final Class EMPTY_CLASS_ARRAY[] = new Class[0];
    public static final String EMPTY_STRING_ARRAY[] = new String[0];
    public static final long EMPTY_LONG_ARRAY[] = new long[0];
    public static final Long EMPTY_LONG_OBJECT_ARRAY[] = new Long[0];
    public static final int EMPTY_INT_ARRAY[] = new int[0];
    public static final Integer EMPTY_INTEGER_OBJECT_ARRAY[] = new Integer[0];
    public static final short EMPTY_SHORT_ARRAY[] = new short[0];
    public static final Short EMPTY_SHORT_OBJECT_ARRAY[] = new Short[0];
    public static final byte EMPTY_BYTE_ARRAY[] = new byte[0];
    public static final Byte EMPTY_BYTE_OBJECT_ARRAY[] = new Byte[0];
    public static final double EMPTY_DOUBLE_ARRAY[] = new double[0];
    public static final Double EMPTY_DOUBLE_OBJECT_ARRAY[] = new Double[0];
    public static final float EMPTY_FLOAT_ARRAY[] = new float[0];
    public static final Float EMPTY_FLOAT_OBJECT_ARRAY[] = new Float[0];
    public static final boolean EMPTY_BOOLEAN_ARRAY[] = new boolean[0];
    public static final Boolean EMPTY_BOOLEAN_OBJECT_ARRAY[] = new Boolean[0];
    public static final char EMPTY_CHAR_ARRAY[] = new char[0];
    public static final Character EMPTY_CHARACTER_OBJECT_ARRAY[] = new Character[0];
    public static final int INDEX_NOT_FOUND = -1;

}
