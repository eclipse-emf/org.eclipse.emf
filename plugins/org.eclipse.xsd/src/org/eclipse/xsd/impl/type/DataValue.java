/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: DataValue.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 *
 * ---------------------------------------------------------------------
 *
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 1999-2004 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Xerces" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 1999, International
 * Business Machines, Inc., http://www.apache.org.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.eclipse.xsd.impl.type;


import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;


public class DataValue
{

static class InvalidDatatypeValueException extends RuntimeException
{
  public InvalidDatatypeValueException(String reason, Object [] objects)
  {
    super(reason);
  }
}

static class ValidationContext
{
}

static class XSSimpleType
{
}

public static class SchemaDVFactory
{
}

/*
 * This class provides encode/decode for RFC 2045 Base64 as
 * defined by RFC 2045, N. Freed and N. Borenstein.
 * RFC 2045: Multipurpose Internet Mail Extensions (MIME)
 * Part One: Format of Internet Message Bodies. Reference
 * 1996 Available at: http://www.ietf.org/rfc/rfc2045.txt
 * This class is used by XML Schema binary format validation
 *
 * This implementation does not encode/decode streaming
 * data. You need the data that you will encode/decode
 * already on a byte arrray.
 *
 * @author Jeffrey Rodriguez
 * @author Sandy Gao
 */
public static final class  Base64 {

    static private final int  BASELENGTH         = 255;
    static private final int  LOOKUPLENGTH       = 64;
    static private final int  TWENTYFOURBITGROUP = 24;
    static private final int  EIGHTBIT           = 8;
    static private final int  SIXTEENBIT         = 16;
    static private final int  SIXBIT             = 6;
    static private final int  FOURBYTE           = 4;
    static private final int  SIGN               = -128;
    static private final byte PAD                = ( byte ) '=';
    static private final boolean fDebug          = false;
    static private final byte [] base64Alphabet        = new byte[BASELENGTH];
    static private final byte [] lookUpBase64Alphabet  = new byte[LOOKUPLENGTH];

    static {

        for (int i = 0; i<BASELENGTH; i++) {
            base64Alphabet[i] = -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i-'A');
        }
        for (int i = 'z'; i>= 'a'; i--) {
            base64Alphabet[i] = (byte) ( i-'a' + 26);
        }

        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i-'0' + 52);
        }

        base64Alphabet['+']  = 62;
        base64Alphabet['/']  = 63;

        for (int i = 0; i<=25; i++)
            lookUpBase64Alphabet[i] = (byte) ('A'+i );

        for (int i = 26,  j = 0; i<=51; i++, j++)
            lookUpBase64Alphabet[i] = (byte) ('a'+ j );

        for (int i = 52,  j = 0; i<=61; i++, j++)
            lookUpBase64Alphabet[i] = (byte) ('0' + j );
        lookUpBase64Alphabet[62] = (byte) '+';
        lookUpBase64Alphabet[63] = (byte) '/';

    }

    protected static boolean isWhiteSpace(byte octect) {
        return (octect == 0x20 || octect == 0xd || octect == 0xa || octect == 0x9);
    }

    protected static boolean isPad(byte octect) {
        return (octect == PAD);
    }

    protected static boolean isData(byte octect) {
        return (base64Alphabet[octect] != -1);
    }

    protected static boolean isBase64(byte octect) {
        return (isWhiteSpace(octect) || isPad(octect) || isData(octect));
    }

    /*
     * Encodes hex octects into Base64
     *
     * @param binaryData Array containing binaryData
     * @return Encoded Base64 array
     */
    public static byte[] encode(byte[] binaryData) {

        if (binaryData == null)
            return null;

        int      lengthDataBits    = binaryData.length*EIGHTBIT;
        int      fewerThan24bits   = lengthDataBits%TWENTYFOURBITGROUP;
        int      numberTriplets    = lengthDataBits/TWENTYFOURBITGROUP;
        byte     encodedData[]     = null;

        if (fewerThan24bits != 0) //data not divisible by 24 bit
            encodedData = new byte[ (numberTriplets + 1 )*4  ];
        else // 16 or 8 bit
            encodedData = new byte[ numberTriplets*4 ];

        byte k=0, l=0, b1=0,b2=0,b3=0;

        int encodedIndex = 0;
        int dataIndex   = 0;
        int i           = 0;
        if (fDebug) {
            System.out.println("number of triplets = " + numberTriplets );
        }
        for (i = 0; i<numberTriplets; i++) {

            dataIndex = i*3;
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            b3 = binaryData[dataIndex + 2];

            if (fDebug) {
                System.out.println( "b1= " + b1 +", b2= " + b2 + ", b3= " + b3 );
            }

            l  = (byte)(b2 & 0x0f);
            k  = (byte)(b1 & 0x03);

            encodedIndex = i*4;
            byte val1 = ((b1 & SIGN)==0)?(byte)(b1>>2):(byte)((b1)>>2^0xc0);

            byte val2 = ((b2 & SIGN)==0)?(byte)(b2>>4):(byte)((b2)>>4^0xf0);
            byte val3 = ((b3 & SIGN)==0)?(byte)(b3>>6):(byte)((b3)>>6^0xfc);

            encodedData[encodedIndex]   = lookUpBase64Alphabet[ val1 ];
            if (fDebug) {
                System.out.println( "val2 = " + val2 );
                System.out.println( "k4   = " + (k<<4));
                System.out.println( "vak  = " + (val2 | (k<<4)));
            }

            encodedData[encodedIndex+1] = lookUpBase64Alphabet[ val2 | ( k<<4 )];
            encodedData[encodedIndex+2] = lookUpBase64Alphabet[ (l <<2 ) | val3 ];
            encodedData[encodedIndex+3] = lookUpBase64Alphabet[ b3 & 0x3f ];
        }

        // form integral number of 6-bit groups
        dataIndex    = i*3;
        encodedIndex = i*4;
        if (fewerThan24bits == EIGHTBIT) {
            b1 = binaryData[dataIndex];
            k = (byte) ( b1 &0x03 );
            if (fDebug) {
                System.out.println("b1=" + b1);
                System.out.println("b1<<2 = " + (b1>>2) );
            }
            byte val1 = ((b1 & SIGN)==0)?(byte)(b1>>2):(byte)((b1)>>2^0xc0);
            encodedData[encodedIndex]     = lookUpBase64Alphabet[ val1 ];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[ k<<4 ];
            encodedData[encodedIndex + 2] = PAD;
            encodedData[encodedIndex + 3] = PAD;
        } else if (fewerThan24bits == SIXTEENBIT) {

            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex +1 ];
            l = ( byte ) ( b2 &0x0f );
            k = ( byte ) ( b1 &0x03 );

            byte val1 = ((b1 & SIGN)==0)?(byte)(b1>>2):(byte)((b1)>>2^0xc0);
            byte val2 = ((b2 & SIGN)==0)?(byte)(b2>>4):(byte)((b2)>>4^0xf0);

            encodedData[encodedIndex]     = lookUpBase64Alphabet[ val1 ];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[ val2 | ( k<<4 )];
            encodedData[encodedIndex + 2] = lookUpBase64Alphabet[ l<<2 ];
            encodedData[encodedIndex + 3] = PAD;
        }

        return encodedData;
    }

    /*
     * Decodes Base64 data into octects
     *
     * @param binaryData Byte array containing Base64 data
     * @return Array containind decoded data.
     */
    public static byte[] decode(byte[] base64Data) {

        if (base64Data == null)
            return null;

        if (base64Data.length%FOURBYTE != 0) {
            return null;//should be divisible by four
        }

        int      numberQuadruple    = (base64Data.length/FOURBYTE );

        if (numberQuadruple == 0)
            return new byte[0];

        byte     decodedData[]      = null;
        byte     b1=0,b2=0,b3=0, b4=0, marker0=0, marker1=0;
        byte     d1=0,d2=0,d3=0,d4=0;

        // Throw away anything not in normalizedBase64Data
        // Adjust size
        int i = 0;
        int encodedIndex = 0;
        int dataIndex    = 0;
        decodedData      = new byte[ (numberQuadruple)*3];

        for (; i<numberQuadruple-1; i++) {

            if (!isData( (d1 = base64Data[dataIndex++]) )||
                !isData( (d2 = base64Data[dataIndex++]) )||
                !isData( (d3 = base64Data[dataIndex++]) )||
                !isData( (d4 = base64Data[dataIndex++]) ))
                return null;//if found "no data" just return null

            b1 = base64Alphabet[d1];
            b2 = base64Alphabet[d2];
            b3 = base64Alphabet[d3];
            b4 = base64Alphabet[d4];

            decodedData[encodedIndex++] = (byte)(  b1 <<2 | b2>>4 ) ;
            decodedData[encodedIndex++] = (byte)(((b2 & 0xf)<<4 ) |( (b3>>2) & 0xf) );
            decodedData[encodedIndex++] = (byte)( b3<<6 | b4 );
        }

        if (!isData( (d1 = base64Data[dataIndex++]) ) ||
            !isData( (d2 = base64Data[dataIndex++]) )) {
            return null;//if found "no data" just return null
        }

        b1 = base64Alphabet[d1];
        b2 = base64Alphabet[d2];

        d3 = base64Data[dataIndex++];
        d4 = base64Data[dataIndex++];
        if (!isData( (d3 ) ) ||
            !isData( (d4 ) )) {//Check if they are PAD characters
            if (isPad( d3 ) && isPad( d4)) {               //Two PAD e.g. 3c[Pad][Pad]
                if ((b2 & 0xf) != 0)//last 4 bits should be zero
                    return null;
                byte[] tmp = new byte[ i*3 + 1 ];
                System.arraycopy( decodedData, 0, tmp, 0, i*3 );
                tmp[encodedIndex]   = (byte)(  b1 <<2 | b2>>4 ) ;
                return tmp;
            } else if (!isPad( d3) && isPad(d4)) {               //One PAD  e.g. 3cQ[Pad]
                b3 = base64Alphabet[ d3 ];
                if ((b3 & 0x3 ) != 0)//last 2 bits should be zero
                    return null;
                byte[] tmp = new byte[ i*3 + 2 ];
                System.arraycopy( decodedData, 0, tmp, 0, i*3 );
                tmp[encodedIndex++] = (byte)(  b1 <<2 | b2>>4 );
                tmp[encodedIndex]   = (byte)(((b2 & 0xf)<<4 ) |( (b3>>2) & 0xf) );
                return tmp;
            } else {
                return null;//an error  like "3c[Pad]r", "3cdX", "3cXd", "3cXX" where X is non data
            }
        } else { //No PAD e.g 3cQl
            b3 = base64Alphabet[ d3 ];
            b4 = base64Alphabet[ d4 ];
            decodedData[encodedIndex++] = (byte)(  b1 <<2 | b2>>4 ) ;
            decodedData[encodedIndex++] = (byte)(((b2 & 0xf)<<4 ) |( (b3>>2) & 0xf) );
            decodedData[encodedIndex++] = (byte)( b3<<6 | b4 );

        }

        return decodedData;
    }

    /*
     * Decodes Base64 data into octects
     *
     * @param base64Data String containing Base64 data
     * @return string containing decoded data.
     */
    public static String decode(String base64Data) {
        if (base64Data == null)
            return null;

	byte[] decoded = null;	
	try {
	  decoded = decode(base64Data.getBytes("utf-8"));
	}
	catch(UnsupportedEncodingException e) {	
	}
	finally {
            String retVal = null;
            try {
                retVal = decoded == null ? null : new String(decoded, "8859_1");
            } catch (UnsupportedEncodingException e) {
            }
            return retVal;
	}
    }
}


/*
 * format validation
 *
 * This class encodes/decodes hexadecimal data
 * @author Jeffrey Rodriguez
 */
public static final class  HexBin {
    static private final int  BASELENGTH   = 255;
    static private final int  LOOKUPLENGTH = 16;
    static final private byte [] hexNumberTable    = new byte[BASELENGTH];
    static final private byte [] lookUpHexAlphabet = new byte[LOOKUPLENGTH];


    static {
        for (int i = 0; i<BASELENGTH; i++ ) {
            hexNumberTable[i] = -1;
        }
        for ( int i = '9'; i >= '0'; i--) {
            hexNumberTable[i] = (byte) (i-'0');
        }
        for ( int i = 'F'; i>= 'A'; i--) {
            hexNumberTable[i] = (byte) ( i-'A' + 10 );
        }
        for ( int i = 'f'; i>= 'a'; i--) {
           hexNumberTable[i] = (byte) ( i-'a' + 10 );
        }

        for(int i = 0; i<10; i++ )
            lookUpHexAlphabet[i] = (byte) ('0'+i );
        for(int i = 10; i<=15; i++ )
            lookUpHexAlphabet[i] = (byte) ('A'+i -10);
    }

    /*
     * byte to be tested if it is Base64 alphabet
     *
     * @param octect
     * @return
     */
    static boolean isHex(byte octect) {
        return (hexNumberTable[octect] != -1);
    }

    /*
     * array of byte to encode
     *
     * @param binaryData
     * @return return encode binary array
     */
    static public byte[] encode(byte[] binaryData) {
        if (binaryData == null)
            return null;
        int lengthData   = binaryData.length;
        int lengthEncode = lengthData * 2;
        byte[] encodedData = new byte[lengthEncode];
        for( int i = 0; i<lengthData; i++ ){
            encodedData[i*2] = lookUpHexAlphabet[ binaryData[i] >> 4];
            encodedData[i*2+1] = lookUpHexAlphabet[ binaryData[i] & 0xf];
        }
        return encodedData;
    }

    static public byte[] decode(byte[] binaryData) {
        if (binaryData == null)
            return null;
        int lengthData   = binaryData.length;
        if (lengthData % 2 != 0)
            return null;

        int lengthDecode = lengthData / 2;
        byte[] decodedData = new byte[lengthDecode];
        for( int i = 0; i<lengthDecode; i++ ){
            if (!isHex(binaryData[i*2]) || !isHex(binaryData[i*2+1])) {
                return null;
            }
            decodedData[i] = (byte)((hexNumberTable[binaryData[i*2]] << 4) | hexNumberTable[binaryData[i*2+1]]);
        }
        return decodedData;
    }

    /*
     * Decodes Hex data into octects
     *
     * @param binaryData String containing Hex data
     * @return string containing decoded data.
     */
    public static String decode(String binaryData) {
        if (binaryData == null)
            return null;

	byte[] decoded = null;
 	try {
          decoded = decode(binaryData.getBytes("utf-8"));
	}
	catch(UnsupportedEncodingException e) {
 	}
	finally {
            String retVal = null;
            try {
                retVal = decoded == null ? null : new String(decoded, "8859_1");
            } catch (UnsupportedEncodingException e) {
            }
            return retVal;
	}
    }
}

/*
 * All primitive types plus ID/IDREF/ENTITY are derived from this abstract
 * class. It provides extra information XSSimpleTypeDecl requires from each
 * type: allowed facets, converting String to actual value, check equality,
 * comparison, etc.
 *
 * @author Neeraj Bajaj, Sun Microsystems, inc.
 * @author Sandy Gao, IBM
 */
public static abstract class TypeValidator {

    // which facets are allowed for this type
    // public abstract short getAllowedFacets();

    // convert a string to an actual value. for example,
    // for number types (decimal, double, float, and types derived from them),
    // get the BigDecimal, Double, Flout object.
    // for some types (string and derived), they just return the string itself
    public abstract Object getActualValue(String content, ValidationContext context)
        throws InvalidDatatypeValueException;

    // for ID/IDREF/ENTITY types, do some extra checking after the value is
    // checked to be valid with respect to both lexical representation and
    // facets
    public void checkExtraRules(Object value, ValidationContext context) throws InvalidDatatypeValueException {
    }

    // whether two values are equal
    // the parameters are in compiled form (from getActualValue)
    public boolean isEqual(Object value1, Object value2) {
        return value1.equals(value2);
    }

    // the following methods might not be supported by every DV.
    // but XSSimpleTypeDecl should know which type supports which methods,
    // and it's an *internal* error if a method is called on a DV that
    // doesn't support it.

    //order constants
    public static final short LESS_THAN     = -1;
    public static final short EQUAL         = 0;
    public static final short GREATER_THAN  = 1;
    public static final short INDETERMINATE = 2;

    // check the order relation between the two values
    // the parameters are in compiled form (from getActualValue)
    public int compare(Object value1, Object value2) {
        return -1;
    }

    // get the length of the value
    // the parameters are in compiled form (from getActualValue)
    public int getDataLength(Object value) {
        return (value instanceof String) ? ((String)value).length() : -1;
    }

    // get the number of digits of the value
    // the parameters are in compiled form (from getActualValue)
    public int getTotalDigits(Object value) {
        return -1;
    }

    // get the number of fraction digits of the value
    // the parameters are in compiled form (from getActualValue)
    public int getFractionDigits(Object value) {
        return -1;
    }

} // interface TypeValidator
/*
 * This is the base class of all date/time datatype validators.
 * It implements common code for parsing, validating and comparing datatypes.
 * Classes that extend this class, must implement parse() method.
 *
 * REVISIT: There are many instance variables, which would cause problems
 *          when we support grammar caching. A grammar is possibly used by
 *          two parser instances at the same time, then the same simple type
 *          decl object can be used to validate two strings at the same time.
 *          -SG
 *
 * @author Elena Litani
 * @author Len Berman
 * @author Gopal Sharma, SUN Microsystems Inc.
 */
public static abstract class AbstractDateTimeDV extends TypeValidator {

    //debugging
    private static final boolean DEBUG=false;

    //define shared variables for date/time

    //define constants
    protected final static int CY = 0,  M = 1, D = 2, h = 3,
    m = 4, s = 5, ms = 6, utc=7, hh=0, mm=1;

    //size for all objects must have the same fields:
    //CCYY, MM, DD, h, m, s, ms + timeZone
    protected final static int TOTAL_SIZE = 8;

    //date obj size for gMonth datatype (without time zone): --09--
    protected final static int MONTH_SIZE = 6;

    //date obj must have at least 6 chars after year (without time zone): "-MM-DD"
    private final static int YEARMONTH_SIZE = 7;

    //define constants to be used in assigning default values for
    //all date/time excluding duration
    protected final static int YEAR=2001;
    protected final static int MONTH=01;
    protected final static int DAY = 15;

    //obj to store timeZone for date/time object excluding duration
    protected int[] timeZone;

    //size of enumeration if any
    protected int  fEnumSize;

    //size of string buffer
    protected int fEnd;
    protected int fStart;

    //storage for string value of date/time object
    protected StringBuffer fBuffer;

    //obj to store all date/time objects with fields:
    // {CY, M, D, h, m, s, ms, utc}
    protected int[] fDateValue;
    private int[] fTempDate;

    //error message buffer
    protected StringBuffer message;

    public AbstractDateTimeDV(){
        initializeValues();
    }

    protected void initializeValues(){
        fDateValue = new int[TOTAL_SIZE];
        fTempDate = new int[TOTAL_SIZE];
        fEnd = 30;
        fStart = 0;
        message = new StringBuffer(TOTAL_SIZE);
        fBuffer = new StringBuffer(fEnd);
        timeZone = new int[2];
    }

    // the parameters are in compiled form (from getActualValue)
    public boolean isEqual(Object value1, Object value2){
        if (!(value1 instanceof int[]) || !(value2 instanceof int[]))
            return false;
        return compareDates((int[])value1,(int[])value2, true)==0;
    }//IsEqual()

    // the parameters are in compiled form (from getActualValue)
    public int compare (Object value1, Object value2) {
        return compareDates((int[])value1, (int[])value2, true);
    }//compare()

    /*
     * Implemented by each subtype, calling appropriate function to parse
     * given date/time
     *
     * @param content String value of the date/time
     * @param date    Storage to represent date/time object.
     *                If null - new object will be created, otherwise
     *                date will be reset and reused
     * @return updated date/time object
     * @exception SchemaDateTimeException
     */
     abstract protected int[] parse (String content, int[] date) throws SchemaDateTimeException;

     /*
     * Compare algorithm described in dateDime (3.2.7).
     * Duration datatype overwrites this method
     *
     * @param date1  normalized date representation of the first value
     * @param date2  normalized date representation of the second value
     * @param strict
     * @return less, greater, less_equal, greater_equal, equal
     */
    protected short compareDates(int[] date1, int[] date2, boolean strict) {
        if ( date1[utc]==date2[utc] ) {
            return compareOrder(date1, date2);
        }
        short c1, c2;

        if ( date1[utc]=='Z' ) {

            //compare date1<=date1<=(date2 with time zone -14)
            //
            cloneDate(date2); //clones date1 value to global temporary storage: fTempDate
            timeZone[hh]=14;
            timeZone[mm]=0;
            fTempDate[utc]='+';
            normalize(fTempDate);
            c1 = compareOrder(date1, fTempDate);

            //compare date1>=(date2 with time zone +14)
            //
            cloneDate(date2); //clones date1 value to global temporary storage: fTempDate
            timeZone[hh]=14;
            timeZone[mm]=0;
            fTempDate[utc]='-';
            normalize(fTempDate);
            c2 = compareOrder(date1, fTempDate);

            if ( (c1 < 0 && c2 > 0) ||
                 (c1 == 0 && c2 == 0) ) {
                return INDETERMINATE;
            }
            //REVISIT: wait for clarification on this case from schema
            return(c1!=INDETERMINATE)?c1:c2;
        }
        else if ( date2[utc]=='Z' ) {

            //compare (date1 with time zone -14)<=date2
            //
            cloneDate(date1); //clones date1 value to global temporary storage: fTempDate
            timeZone[hh]=14;
            timeZone[mm]=0;

            fTempDate[utc]='-';
            if (DEBUG) {
               System.out.println("fTempDate=" + dateToString(fTempDate));
            }
            normalize(fTempDate);
            c1 = compareOrder(fTempDate, date2);
            if (DEBUG) {
                System.out.println("date=" + dateToString(date2));
                System.out.println("fTempDate=" + dateToString(fTempDate));
            }
            //compare (date1 with time zone +14)<=date2
            //
            cloneDate(date1); //clones date1 value to global temporary storage: fTempDate
            timeZone[hh]=14;
            timeZone[mm]=0;
            fTempDate[utc]='+';
            normalize(fTempDate);
            c2 = compareOrder(fTempDate, date2);
            if (DEBUG) {
               System.out.println("fTempDate=" + dateToString(fTempDate));
            }
            if ( (c1 < 0 && c2 > 0) ||
                 (c1 == 0 && c2 == 0) ) {
                return INDETERMINATE;
            }
            //REVISIT: wait for clarification on this case from schema
            return(c1!=INDETERMINATE)?c1:c2;
        }
        return INDETERMINATE;

    }

    /*
     * Given normalized values, determines order-relation
     * between give date/time objects.
     *
     * @param date1  date/time object
     * @param date2  date/time object
     * @return 0 if date1 and date2 are equal, a value less than 0 if date1 is less than date2, a value greater than 0 if date1 is greater than date2
     */
    protected short compareOrder (int[] date1, int[] date2) {

        for ( int i=0;i<TOTAL_SIZE;i++ ) {
            if ( date1[i]<date2[i] ) {
                return -1;
            }
            else if ( date1[i]>date2[i] ) {
                return 1;
            }
        }
        return 0;
    }

    /*
     * Parses time hh:mm:ss.sss and time zone if any
     *
     * @param start
     * @param end
     * @param data
     * @exception RuntimeException
     */
    protected  void getTime (int start, int end, int[] data) throws RuntimeException{

        int stop = start+2;

        //get hours (hh)
        data[h]=parseInt(start,stop);

        //get minutes (mm)

        if (fBuffer.charAt(stop++)!=':') {
                throw new RuntimeException("Error in parsing time zone" );
        }
        start = stop;
        stop = stop+2;
        data[m]=parseInt(start,stop);

        //get seconds (ss)
        if (fBuffer.charAt(stop++)!=':') {
                throw new RuntimeException("Error in parsing time zone" );
        }
        start = stop;
        stop = stop+2;
        data[s]=parseInt(start,stop);

        //get miliseconds (ms)
        int milisec = indexOf(start, end, '.');

        //find UTC sign if any
        int sign = findUTCSign((milisec!=-1)?milisec:start, end);

        //parse miliseconds
        if ( milisec != -1 ) {

            if ( sign<0 ) {

                //get all digits after "."
                data[ms]=parseInt(milisec+1,fEnd);
            }
            else {

                //get ms before UTC sign
                data[ms]=parseInt(milisec+1,sign);
            }

        }

        //parse UTC time zone (hh:mm)
        if ( sign>0 ) {
            getTimeZone(data,sign);
        }
    }

    /*
     * Parses date CCYY-MM-DD
     *
     * @param start
     * @param end
     * @param data
     * @exception RuntimeException
     */
    protected void getDate (int start, int end, int[] date) throws RuntimeException{

        getYearMonth(start, end, date);

        if (fBuffer.charAt(fStart++) !='-') {
            throw new RuntimeException("CCYY-MM must be followed by '-' sign");
        }
        int stop = fStart + 2;
        date[D]=parseInt(fStart, stop);
        fStart = stop;  //fStart points right after the Day
    }

    /*
     * Parses date CCYY-MM
     *
     * @param start
     * @param end
     * @param data
     * @exception RuntimeException
     */
    protected void getYearMonth (int start, int end, int[] date) throws RuntimeException{

        if ( fBuffer.charAt(0)=='-' ) {
            // REVISIT: date starts with preceding '-' sign
            //          do we have to do anything with it?
            //
            start++;
        }
        int i = indexOf(start, end, '-');
        if ( i==-1 ) throw new RuntimeException("Year separator is missing or misplaced");
        int length = i-start;
        if (length<4) {
            throw new RuntimeException("Year must have 'CCYY' format");
        }
        else if (length > 4 && fBuffer.charAt(start)=='0'){
            throw new RuntimeException("Leading zeros are required if the year value would otherwise have fewer than four digits; otherwise they are forbidden");
        }
        date[CY]= parseIntYear(i);
        if (fBuffer.charAt(i)!='-') {
            throw new RuntimeException("CCYY must be followed by '-' sign");
        }
        start = ++i;
        i = start +2;
        date[M]=parseInt(start, i);
        fStart = i; //fStart points right after the MONTH
    }

    /*
     * Shared code from Date and YearMonth datatypes.
     * Finds if time zone sign is present
     *
     * @param end
     * @param date
     * @exception RuntimeException
     */
    protected void parseTimeZone (int end, int[] date) throws RuntimeException{

        //fStart points right after the date

        if ( fStart<fEnd ) {
            int sign = findUTCSign(fStart, fEnd);
            if ( sign<0 ) {
                throw new RuntimeException ("Error in month parsing");
            }
            else {
                getTimeZone(date, sign);
            }
        }
    }

    /*
     * Parses time zone: 'Z' or {+,-} followed by  hh:mm
     *
     * @param data
     * @param sign
     * @exception RuntimeException
     */
    protected void getTimeZone (int[] data, int sign) throws RuntimeException{
        data[utc]=fBuffer.charAt(sign);

        if ( fBuffer.charAt(sign) == 'Z' ) {
            if (fEnd>(++sign)) {
                throw new RuntimeException("Error in parsing time zone");
            }
            return;
        }
        if ( sign<=(fEnd-6) ) {

            //parse [hh]
            int stop = ++sign+2;
            timeZone[hh]=parseInt(sign, stop);
            if (fBuffer.charAt(stop++)!=':') {
                throw new RuntimeException("Error in parsing time zone" );
            }

            //parse [ss]
            timeZone[mm]=parseInt(stop, stop+2);

            if ( stop+2!=fEnd ) {
                throw new RuntimeException("Error in parsing time zone");
            }

        }
        else {
            throw new RuntimeException("Error in parsing time zone");
        }
        if ( DEBUG ) {
            System.out.println("time[hh]="+timeZone[hh] + " time[mm]=" +timeZone[mm]);
        }
    }

    /*
     * Computes index of given char within StringBuffer
     *
     * @param start
     * @param end
     * @param ch     character to look for in StringBuffer
     * @return index of ch within StringBuffer
     */
    protected  int indexOf (int start, int end, char ch) {
        for ( int i=start;i<end;i++ ) {
            if ( fBuffer.charAt(i) == ch ) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Validates given date/time object accoring to W3C PR Schema
     * [D.1 ISO 8601 Conventions]
     *
     * @param data
     */
    protected void validateDateTime (int[]  data) {

        //REVISIT: should we throw an exception for not valid dates
        //          or reporting an error message should be sufficient?
        if ( data[CY]==0 ) {
            throw new RuntimeException("The year \"0000\" is an illegal year value");

        }

        if ( data[M]<1 || data[M]>12 ) {
            throw new RuntimeException("The month must have values 1 to 12");

        }

        //validate days
        if ( data[D]>maxDayInMonthFor(data[CY], data[M]) || data[D]<1 ) {
            throw new RuntimeException("The day must have values 1 to 31");
        }

        //validate hours
        if ( data[h]>23 || data[h]<0 ) {
            throw new RuntimeException("Hour must have values 0-23");
        }

        //validate
        if ( data[m]>59 || data[m]<0 ) {
            throw new RuntimeException("Minute must have values 0-59");
        }

        //validate
        if ( data[s]>60 || data[s]<0 ) {
            throw new RuntimeException("Second must have values 0-60");

        }

        //validate
        if ( timeZone[hh]>14 || timeZone[hh]<-14 ) {
            throw new RuntimeException("Time zone should have range -14..+14");
        }

        //validate
        if ( timeZone[mm]>59 || timeZone[mm]<-59 ) {
            throw new RuntimeException("Minute must have values 0-59");
        }
    }

    /*
     * Return index of UTC char: 'Z', '+', '-'
     *
     * @param start
     * @param end
     * @return index of the UTC character that was found
     */
    protected int findUTCSign (int start, int end) {
        int c;
        for ( int i=start;i<end;i++ ) {
            c=fBuffer.charAt(i);
            if ( c == 'Z' || c=='+' || c=='-' ) {
                return i;
            }

        }
        return -1;
    }

    /*
     * Given start and end position, parses string value
     *
     * @param value  string to parse
     * @param start  Start position
     * @param end    end position
     * @return  return integer representation of characters
     */
    protected  int parseInt (int start, int end)
    throws NumberFormatException{
        //REVISIT: more testing on this parsing needs to be done.
        int radix=10;
        int result = 0;
        int digit=0;
        int limit = -Integer.MAX_VALUE;
        int multmin = limit / radix;
        int i = start;
        do {
            digit = Character.digit(fBuffer.charAt(i),radix);
            if ( digit < 0 ) throw new NumberFormatException("'"+fBuffer.toString()+"' has wrong format");
            if ( result < multmin ) throw new NumberFormatException("'"+fBuffer.toString()+"' has wrong format");
            result *= radix;
            if ( result < limit + digit ) throw new NumberFormatException("'"+fBuffer.toString()+"' has wrong format");
            result -= digit;

        }while ( ++i < end );
        return -result;
    }

    // parse Year differently to support negative value.
    protected int parseIntYear (int end){
        int radix=10;
        int result = 0;
        boolean negative = false;
        int i=0;
        int limit;
        int multmin;
        int digit=0;

        if (fBuffer.charAt(0) == '-'){
            negative = true;
            limit = Integer.MIN_VALUE;
            i++;

        }
        else{
            limit = -Integer.MAX_VALUE;
        }
        multmin = limit / radix;
        while (i < end)
        {
            digit = Character.digit(fBuffer.charAt(i++),radix);
            if (digit < 0) throw new NumberFormatException("'"+fBuffer.toString()+"' has wrong format");
            if (result < multmin) throw new NumberFormatException("'"+fBuffer.toString()+"' has wrong format");
            result *= radix;
            if (result < limit + digit) throw new NumberFormatException("'"+fBuffer.toString()+"' has wrong format");
            result -= digit;
        }

        if (negative)
        {
            if (i > 1) return result;
            else throw new NumberFormatException("'"+fBuffer.toString()+"' has wrong format");
        }
        return -result;

    }

    /*
     * If timezone present - normalize dateTime  [E Adding durations to dateTimes]
     *
     * @param date   CCYY-MM-DDThh:mm:ss+03
     * @return CCYY-MM-DDThh:mm:ssZ
     */
    protected  void normalize (int[] date) {

        // REVISIT: we have common code in addDuration() for durations
        //          should consider reorganizing it.
        //

        //add minutes (from time zone)
        int negate = 1;
        if (date[utc]=='+') {
            negate = -1;
        }
        if ( DEBUG ) {
            System.out.println("==>date[m]"+date[m]);
            System.out.println("==>timeZone[mm]" +timeZone[mm]);
        }
        int temp = date[m] + negate*timeZone[mm];
        int carry = fQuotient (temp, 60);
        date[m]= mod(temp, 60, carry);

        if ( DEBUG ) {
            System.out.println("==>carry: " + carry);
        }
        //add hours
        temp = date[h] + negate*timeZone[hh] + carry;
        carry = fQuotient(temp, 24);
        date[h]=mod(temp, 24, carry);
        if ( DEBUG ) {
            System.out.println("==>date[h]"+date[h]);
            System.out.println("==>carry: " + carry);
        }

        date[D]=date[D]+carry;

        while ( true ) {
            temp=maxDayInMonthFor(date[CY], date[M]);
            if (date[D]<1) {
                date[D] = date[D] + maxDayInMonthFor(date[CY], date[M]-1);
                carry=-1;
            }
            else if ( date[D]>temp ) {
                date[D]=date[D]-temp;
                carry=1;
            }
            else {
                break;
            }
            temp=date[M]+carry;
            date[M]=modulo(temp, 1, 13);
            date[CY]=date[CY]+fQuotient(temp, 1, 13);
        }
        date[utc]='Z';
    }


    /*
     * Resets fBuffer to store string representation of
     * date/time
     *
     * @param str    Lexical representation of date/time
     */
    protected void resetBuffer (String str) {
        fBuffer.setLength(0);
        fStart=fEnd=0;
        timeZone[hh]=timeZone[mm]=0;
        fBuffer.append(str);
        fEnd = fBuffer.length();

    }

    /*
     * Resets object representation of date/time
     *
     * @param data   date/time object
     */
    protected void resetDateObj (int[] data) {
        for ( int i=0;i<TOTAL_SIZE;i++ ) {
            data[i]=0;
        }
    }

    /*
     * Given {year,month} computes maximum
     * number of days for given month
     *
     * @param year
     * @param month
     * @return integer containg the number of days in a given month
     */
    protected int maxDayInMonthFor(int year, int month) {
        //validate days
        if ( month==4 || month==6 || month==9 || month==11 ) {
            return 30;
        }
        else if ( month==2 ) {
            if ( isLeapYear(year) ) {
                return 29;
            }
            else {
                return 28;
            }
        }
        else {
            return 31;
        }
    }

    private boolean isLeapYear(int year) {

        //REVISIT: should we take care about Julian calendar?
        return((year%4 == 0) && ((year%100 != 0) || (year%400 == 0)));
    }

    //
    // help function described in W3C PR Schema [E Adding durations to dateTimes]
    //
    protected int mod (int a, int b, int quotient) {
        //modulo(a, b) = a - fQuotient(a,b)*b
        return (a - quotient*b) ;
    }

    //
    // help function described in W3C PR Schema [E Adding durations to dateTimes]
    //
    protected int fQuotient (int a, int b) {

        //fQuotient(a, b) = the greatest integer less than or equal to a/b
        return (int)Math.floor((float)a/b);
    }

    //
    // help function described in W3C PR Schema [E Adding durations to dateTimes]
    //
    protected int modulo (int temp, int low, int high) {
        //modulo(a - low, high - low) + low
        int a = temp - low;
        int b = high - low;
        return (mod (a, b, fQuotient(a, b)) + low) ;
    }

    //
    // help function described in W3C PR Schema [E Adding durations to dateTimes]
    //
    protected int fQuotient (int temp, int low, int high) {
        //fQuotient(a - low, high - low)

        return fQuotient(temp - low, high - low);
    }


    protected String dateToString(int[] date) {
        message.setLength(0);
        message.append(date[CY]);
        message.append('-');
        message.append(date[M]);
        message.append('-');
        message.append(date[D]);
        message.append('T');
        message.append(date[h]);
        message.append(':');
        message.append(date[m]);
        message.append(':');
        message.append(date[s]);
        message.append('.');
        message.append(date[ms]);
        message.append((char)date[utc]);
        return message.toString();
    }


    /*
     * Use this function to report errors in constructor
     *
     * @param msg
     * @param value
     */
    protected void reportError(String msg, String value) {
        System.err.println("[Error]: " +msg+": Value  '"+value+"' is not legal for current datatype");
    }


    //
    //Private help functions
    //

    private void cloneDate (int[] finalValue) {
        resetDateObj(fTempDate);
        for ( int i=0;i<TOTAL_SIZE;i++ ) {
            fTempDate[i]=finalValue[i];
        }
    }

}

/*
 * Represent the schema type "anySimpleType"
 *
 * @author Neeraj Bajaj, Sun Microsystems, inc.
 * @author Sandy Gao, IBM
 *
 */
public static class AnySimpleDV extends TypeValidator {

    public short getAllowedFacets() {
        // anySimpleType doesn't allow any facet, not even whiteSpace
        return 0;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        return content;
    }

} // class AnySimpleDV

/*
 * Validator for <date> datatype (W3C Schema datatypes)
 *
 * @author Elena Litani
 * @Gopal Sharma, SUN Microsystems Inc.
 */
public static class DateDV extends DateTimeDV {

    public Object getActualValue(String content) throws InvalidDatatypeValueException {
        try{
            return parse(content, null);
        } catch(Exception ex){
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "date"});
        }
    }

    /*
     * Parses, validates and computes normalized version of dateTime object
     *
     * @param str    The lexical representation of dateTime object CCYY-MM-DD
     *               with possible time zone Z or (-),(+)hh:mm
     * @param date   uninitialized date object
     * @return normalized dateTime representation
     * @exception SchemaDateTimeException Invalid lexical representation
     */
    protected int[] parse(String str, int[] date) throws SchemaDateTimeException{
        resetBuffer(str);
        //create structure to hold an object

        if ( date == null ) {
            date = new int[TOTAL_SIZE];
        }
        resetDateObj(date);
        // get date

        getDate(fStart, fEnd, date);
        parseTimeZone (fEnd, date);

        //validate and normalize
        //REVISIT: do we need SchemaDateTimeException?
        validateDateTime(date);

        if ( date[utc]!=0 && date[utc]!='Z' ) {
            normalize(date);
        }
        return date;
    }

}


/*
 * Validator for <dateTime> datatype (W3C Schema Datatypes)
 *
 * @author Elena Litani
 * @author Gopal Sharma, SUN Microsystem Inc.
 */
public static class DateTimeDV extends AbstractDateTimeDV {

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try{
            return parse(content, null);
        } catch(Exception ex){
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "dateTime"});
        }
    }

    /*
     * Parses, validates and computes normalized version of dateTime object
     *
     * @param str    The lexical representation of dateTime object CCYY-MM-DDThh:mm:ss.sss
     *               with possible time zone Z or (-),(+)hh:mm
     * @param date   uninitialized date object
     * @return normalized dateTime representation
     * @exception SchemaDateTimeException Invalid lexical representation
     */
    protected int[] parse(String str, int[] date) throws SchemaDateTimeException {
        resetBuffer(str);

        //create structure to hold an object
        if ( date == null ) {
            date = new int[TOTAL_SIZE];
        }
        resetDateObj(date);
        int end = indexOf (fStart, fEnd, 'T');

        // both time and date
        getDate(fStart, end, date);
        getTime(end+1, fEnd, date);

        //validate and normalize

        //REVISIT: do we need SchemaDateTimeException?
        validateDateTime(date);

        if ( date[utc]!=0 && date[utc]!='Z') {
            normalize(date);
        }
        return date;
    }

}


/*
 * Validator for <gDay> datatype (W3C Schema datatypes)
 *
 * @author Elena Litani
 * @author Gopal Sharma, SUN Microsystem Inc.
 */
public static class DayDV extends AbstractDateTimeDV {

    //size without time zone: ---09
    private final static int DAY_SIZE=5;

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try{
            return parse(content, null);
        } catch(Exception ex){
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "gDay"});
        }
    }

    /*
     * Parses, validates and computes normalized version of gDay object
     *
     * @param str    The lexical representation of gDay object ---DD
     *               with possible time zone Z or (-),(+)hh:mm
     *               Pattern: ---(\\d\\d)(Z|(([-+])(\\d\\d)(:(\\d\\d))?
     * @param date   uninitialized date object
     * @return normalized date representation
     * @exception SchemaDateTimeException Invalid lexical representation
     */
    protected int[] parse(String str, int[] date) throws SchemaDateTimeException {

        resetBuffer(str);

        //create structure to hold an object
        if ( date== null ) {
            date=new int[TOTAL_SIZE];
        }
        resetDateObj(date);
        if (fBuffer.charAt(0)!='-' || fBuffer.charAt(1)!='-' || fBuffer.charAt(2)!='-') {
            throw new SchemaDateTimeException ("Error in day parsing");
        }

        //initialize values
        date[CY]=YEAR;
        date[M]=MONTH;

        date[D]=parseInt(fStart+3,fStart+5);


        if ( DAY_SIZE<fEnd ) {
            int sign = findUTCSign(DAY_SIZE, fEnd);
            if ( sign<0 ) {
                throw new SchemaDateTimeException ("Error in day parsing");
            }
            else {
                getTimeZone(date, sign);
            }
        }

       //validate and normalize
        validateDateTime(date);

        if ( date[utc]!=0 && date[utc]!='Z' ) {
            normalize(date);
        }
        return date;
    }

    /*
     * Converts gDay object representation to String
     *
     * @param date   gDay object
     * @return lexical representation of gDay: ---DD with an optional time zone sign
     */
    protected String dateToString(int[] date) {
        message.setLength(0);
        message.append('-');
        message.append('-');
        message.append('-');
        message.append(date[D]);
        message.append((char)date[utc]);
        return message.toString();
    }

}

/*
 * Validator for <duration> datatype (W3C Schema Datatypes)
 *
 * @author Elena Litani
 * @author Gopal Sharma, SUN Microsystem Inc.
 */
public static class DurationDV extends AbstractDateTimeDV {

    // order-relation on duration is a partial order. The dates below are used to
    // for comparison of 2 durations, based on the fact that
    // duration x and y is x<=y iff s+x<=s+y
    // see 3.2.6 duration W3C schema datatype specs
    //
    // the dates are in format: {CCYY,MM,DD, H, S, M, MS, timezone}
    private final static int[][] DATETIMES= {
        {1696, 9, 1, 0, 0, 0, 0, 'Z'},
        {1697, 2, 1, 0, 0, 0, 0, 'Z'},
        {1903, 3, 1, 0, 0, 0, 0, 'Z'},
        {1903, 7, 1, 0, 0, 0, 0, 'Z'}};

    private int[][] fDuration = null;

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException{
        try{
            return parse(content, null);
        } catch (Exception ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "duration"});
        }
    }

    /*
     * Parses, validates and computes normalized version of duration object
     *
     * @param str    The lexical representation of duration object PnYn MnDTnH nMnS
     * @param date   uninitialized date object
     * @return normalized date representation
     * @exception SchemaDateTimeException Invalid lexical representation
     */
    protected int[] parse(String str, int[] date) throws SchemaDateTimeException{

        //PnYn MnDTnH nMnS: -P1Y2M3DT10H30M
        resetBuffer(str);

        //create structure to hold an object
        if ( date== null ) {
            date=new int[TOTAL_SIZE];
        }
        resetDateObj(date);


        char c=fBuffer.charAt(fStart++);
        if ( c!='P' && c!='-' ) {
            throw new SchemaDateTimeException();
        }
        else {
            date[utc]=(c=='-')?'-':0;
            if ( c=='-' && fBuffer.charAt(fStart++)!='P' ) {
                throw new SchemaDateTimeException();
            }
        }

        int negate = 1;
        //negative duration
        if ( date[utc]=='-' ) {
            negate = -1;

        }
        //at least one number and designator must be seen after P
        boolean designator = false;

        int endDate = indexOf (fStart, fEnd, 'T');
        if ( endDate == -1 ) {
            endDate = fEnd;
        }
        //find 'Y'
        int end = indexOf (fStart, endDate, 'Y');
        if ( end!=-1 ) {
            //scan year
            date[CY]=negate * parseInt(fStart,end);
            fStart = end+1;
            designator = true;
        }

        end = indexOf (fStart, endDate, 'M');
        if ( end!=-1 ) {
            //scan month
            date[M]=negate * parseInt(fStart,end);
            fStart = end+1;
            designator = true;
        }

        end = indexOf (fStart, endDate, 'D');
        if ( end!=-1 ) {
            //scan day
            date[D]=negate * parseInt(fStart,end);
            fStart = end+1;
            designator = true;
        }

        if ( fEnd == endDate && fStart!=fEnd ) {
            throw new SchemaDateTimeException();
        }
        if ( fEnd !=endDate ) {

            //scan hours, minutes, seconds
            //REVISIT: can any item include a decimal fraction or only seconds?
            //

            end = indexOf (++fStart, fEnd, 'H');
            if ( end!=-1 ) {
                //scan hours
                date[h]=negate * parseInt(fStart,end);
                fStart=end+1;
                designator = true;
            }

            end = indexOf (fStart, fEnd, 'M');
            if ( end!=-1 ) {
                //scan min
                date[m]=negate * parseInt(fStart,end);
                fStart=end+1;
                designator = true;
            }

            end = indexOf (fStart, fEnd, 'S');
            if ( end!=-1 ) {
                //scan seconds
                int mlsec = indexOf (fStart, end, '.');
                if ( mlsec >0 ) {
                    date[s]  = negate * parseInt (fStart, mlsec);
                    date[ms] = negate * parseInt (mlsec+1, end);
                }
                else {
                    date[s]=negate * parseInt(fStart,end);
                }
                fStart=end+1;
                designator = true;
            }
            // no additional data shouls appear after last item
            // P1Y1M1DT is illigal value as well
            if ( fStart != fEnd || fBuffer.charAt(--fStart)=='T' ) {
                throw new SchemaDateTimeException();
            }
        }

        if ( !designator ) {
            throw new SchemaDateTimeException();
        }

        return date;
    }

    /*
     * Compares 2 given durations. (refer to W3C Schema Datatypes "3.2.6 duration")
     *
     * @param date1  Unnormalized duration
     * @param date2  Unnormalized duration
     * @param strict (min/max)Exclusive strict == true ( LESS_THAN ) or ( GREATER_THAN )
     *               (min/max)Inclusive strict == false (LESS_EQUAL) or (GREATER_EQUAL)
     * @return INDETERMINATE if the order relationship between date1 and date2 is indeterminate. 
     * EQUAL if the order relation between date1 and date2 is EQUAL.  
     * If the strict parameter is true, return LESS_THAN if date1 is less than date2 and
     * return GREATER_THAN if date1 is greater than date2. 
     * If the strict parameter is false, return LESS_THAN if date1 is less than OR equal to date2 and
     * return GREATER_THAN if date1 is greater than OR equal to date2 
     */
    protected  short compareDates(int[] date1, int[] date2, boolean strict) {

        //REVISIT: this is unoptimazed vs of comparing 2 durations
        //         Algorithm is described in 3.2.6.2 W3C Schema Datatype specs
        //

        //add constA to both durations
        short resultA, resultB= INDETERMINATE;

        //try and see if the objects are equal
        resultA = compareOrder (date1, date2);
        if ( resultA == 0 ) {
            return 0;
        }
        if ( fDuration == null ) {
            fDuration = new int[2][TOTAL_SIZE];
        }
        //long comparison algorithm is required
        int[] tempA = addDuration (date1, 0, fDuration[0]);
        int[] tempB = addDuration (date2, 0, fDuration[1]);
        resultA =  compareOrder(tempA, tempB);
        if ( resultA == INDETERMINATE ) {
            return INDETERMINATE;
        }

        tempA = addDuration(date1, 1, fDuration[0]);
        tempB = addDuration(date2, 1, fDuration[1]);
        resultB = compareOrder(tempA, tempB);
        resultA = compareResults(resultA, resultB, strict);
        if (resultA == INDETERMINATE) {
            return INDETERMINATE;
        }

        tempA = addDuration(date1, 2, fDuration[0]);
        tempB = addDuration(date2, 2, fDuration[1]);
        resultB = compareOrder(tempA, tempB);
        resultA = compareResults(resultA, resultB, strict);
        if (resultA == INDETERMINATE) {
            return INDETERMINATE;
        }

        tempA = addDuration(date1, 3, fDuration[0]);
        tempB = addDuration(date2, 3, fDuration[1]);
        resultB = compareOrder(tempA, tempB);
        resultA = compareResults(resultA, resultB, strict);

        return resultA;
    }

    private short compareResults(short resultA, short resultB, boolean strict){

      if ( resultB == INDETERMINATE ) {
            return INDETERMINATE;
        }
        else if ( resultA!=resultB && strict ) {
            return INDETERMINATE;
        }
        else if ( resultA!=resultB && !strict ) {
            if ( resultA!=0 && resultB!=0 ) {
                return INDETERMINATE;
            }
            else {
                return (resultA!=0)?resultA:resultB;
            }
        }
        return resultA;
    }

    private int[] addDuration(int[] date, int index, int[] duration) {

        //REVISIT: some code could be shared between normalize() and this method,
        //         however is it worth moving it? The structures are different...
        //

        resetDateObj(duration);
        //add months (may be modified additionaly below)
        int temp = DATETIMES[index][M] + date[M];
        duration[M] = modulo (temp, 1, 13);
        int carry = fQuotient (temp, 1, 13);

        //add years (may be modified additionaly below)
        duration[CY]=DATETIMES[index][CY] + date[CY] + carry;

        //add seconds
        temp = DATETIMES[index][s] + date[s];
        carry = fQuotient (temp, 60);
        duration[s] =  mod(temp, 60, carry);

        //add minutes
        temp = DATETIMES[index][m] +date[m] + carry;
        carry = fQuotient (temp, 60);
        duration[m]= mod(temp, 60, carry);

        //add hours
        temp = DATETIMES[index][h] + date[h] + carry;
        carry = fQuotient(temp, 24);
        duration[h] = mod(temp, 24, carry);


        duration[D]=DATETIMES[index][D] + date[D] + carry;

        while ( true ) {

            temp=maxDayInMonthFor(duration[CY], duration[M]);
            if ( duration[D] < 1 ) { //original duration was negative
                duration[D] = duration[D] + maxDayInMonthFor(duration[CY], duration[M]-1);
                carry=-1;
            }
            else if ( duration[D] > temp ) {
                duration[D] = duration[D] - temp;
                carry=1;
            }
            else {
                break;
            }
            temp = duration[M]+carry;
            duration[M] = modulo(temp, 1, 13);
            duration[CY] = duration[CY]+fQuotient(temp, 1, 13);
        }

        duration[utc]='Z';
        return duration;
    }

    protected String dateToString(int[] date) {
        message.setLength(0);
        int negate = 1;
        if ( date[CY]<0 ) {
            message.append('-');
            negate=-1;
        }
        message.append('P');
        message.append(negate * date[CY]);
        message.append('Y');
        message.append(negate * date[M]);
        message.append('M');
        message.append(negate * date[D]);
        message.append('D');
        message.append('T');
        message.append(negate * date[h]);
        message.append('H');
        message.append(negate * date[m]);
        message.append('M');
        message.append(negate * date[s]);
        message.append('.');
        message.append(negate * date[ms]);
        message.append('S');

        return message.toString();
    }
}
/*
 * Validator for <gMonth> datatype (W3C Schema Datatypes)
 *
 * @author Elena Litani
 * @author Gopal Sharma, SUN Microsystem Inc.
 */

public static class MonthDV extends AbstractDateTimeDV {

    /*
     * Convert a string to a compiled form
     *
     * @param  content The lexical representation of gMonth
     * @return a valid and normalized gMonth object
     */
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException{
        try{
            return parse(content, null);
        } catch(Exception ex){
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "gMonth"});
        }
    }

    /*
     * Parses, validates and computes normalized version of gMonth object
     *
     * @param str    The lexical representation of gMonth object --MM--
     *               with possible time zone Z or (-),(+)hh:mm
     * @param date   uninitialized date object
     * @return normalized date representation
     * @exception SchemaDateTimeException Invalid lexical representation
     */
    protected int[] parse(String str, int[] date) throws SchemaDateTimeException{

        //REVISIT: change --MM-- to --MM
        resetBuffer(str);

        //create structure to hold an object
        if ( date== null ) {
            date=new int[TOTAL_SIZE];
        }
        resetDateObj(date);

        //set constants
        date[CY]=YEAR;
        date[D]=DAY;
        if (fBuffer.charAt(0)!='-' || fBuffer.charAt(1)!='-') {
            throw new SchemaDateTimeException("Invalid format for gMonth: "+str);
        }
        int stop = fStart +4;
        date[M]=parseInt(fStart+2,stop);

        if (fBuffer.charAt(stop++)!='-' || fBuffer.charAt(stop)!='-') {
            throw new SchemaDateTimeException("Invalid format for gMonth: "+str);
        }
        if ( MONTH_SIZE<fEnd ) {
            int sign = findUTCSign(MONTH_SIZE, fEnd);
            if ( sign<0 ) {
                throw new SchemaDateTimeException ("Error in month parsing: "+str);
            }
            else {
                getTimeZone(date, sign);
            }
        }
        //validate and normalize
        validateDateTime(date);

        if ( date[utc]!=0 && date[utc]!='Z' ) {
            normalize(date);
        }
        return date;
    }

    /*
     * Overwrite compare algorithm to optimize month comparison
     *
     * REVISIT: this one is lack of the third parameter: boolean strict, so it
     *          doesn't override the method in the base. But maybe this method
     *          is not correctly implemented, and I did encounter errors when
     *          trying to add the extra parameter. I'm leaving it as is. -SG
     *
     * @param date1
     * @param date2
     * @return less, greater, equal, indeterminate
     */
    protected  short compareDates(int[] date1, int[] date2) {

        if ( date1[utc]==date2[utc] ) {
            return (short)((date1[M]>=date2[M])?(date1[M]>date2[M])?1:0:-1);
        }

        if ( date1[utc]=='Z' || date2[utc]=='Z' ) {

            if ( date1[M]==date2[M] ) {
                //--05--Z and --05--
                return INDETERMINATE;
            }
            if ( (date1[M]+1 == date2[M] || date1[M]-1 == date2[M]) ) {
                //--05--Z and (--04-- or --05--)
                //REVISIT: should this case be less than or equal?
                //         maxExclusive should fail but what about maxInclusive
                //
                return INDETERMINATE;
            }
        }

        if ( date1[M]<date2[M] ) {
            return -1;
        }
        else {
            return 1;
        }

    }

    /*
     * Converts month object representation to String
     *
     * @param date   month object
     * @return lexical representation of month: --MM-- with an optional time zone sign
     */
    protected String dateToString(int[] date) {

        message.setLength(0);
        message.append('-');
        message.append('-');
        message.append(date[M]);
        message.append('-');
        message.append('-');
        message.append((char)date[utc]);
        return message.toString();
    }

}
/*
 * Validator for <gMonthDay> datatype (W3C Schema Datatypes)
 *
 * @author Elena Litani
 * @author Gopal Sharma, SUN Microsystem Inc.
 *
 */

public static class MonthDayDV extends AbstractDateTimeDV {

    //size without time zone: --MM-DD
    private final static int MONTHDAY_SIZE = 7;

    /*
     * Convert a string to a compiled form
     *
     * @param  content The lexical representation of gMonthDay
     * @return a valid and normalized gMonthDay object
     */
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try{
            return parse(content, null);
        } catch(Exception ex){
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "gMonthDay"});
        }
    }

    /*
     * Parses, validates and computes normalized version of gMonthDay object
     *
     * @param str    The lexical representation of gMonthDay object --MM-DD
     *               with possible time zone Z or (-),(+)hh:mm
     * @param date   uninitialized date object
     * @return normalized date representation
     * @exception SchemaDateTimeException Invalid lexical representation
     */
    protected int[] parse(String str, int[] date) throws SchemaDateTimeException{

        resetBuffer(str);

        //create structure to hold an object
        if ( date== null ) {
            date=new int[TOTAL_SIZE];
        }
        resetDateObj(date);

        //initialize
        date[CY]=YEAR;

        if (fBuffer.charAt(0)!='-' || fBuffer.charAt(1)!='-') {
            throw new SchemaDateTimeException("Invalid format for gMonthDay: "+str);
        }
        date[M]=parseInt(fStart+2,fStart+4);
        fStart+=4;

        if (fBuffer.charAt(fStart++)!='-') {
            throw new SchemaDateTimeException("Invalid format for gMonthDay: " + str);
        }

        date[D]=parseInt(fStart, fStart+2);

        if ( MONTHDAY_SIZE<fEnd ) {
            int sign = findUTCSign(MONTHDAY_SIZE, fEnd);
            if ( sign<0 ) {
                throw new SchemaDateTimeException ("Error in month parsing:" +str);
            }
            else {
                getTimeZone(date, sign);
            }
        }
        //validate and normalize

        validateDateTime(date);

        if ( date[utc]!=0 && date[utc]!='Z' ) {
            normalize(date);
        }
        return date;
    }

    /*
     * Converts gMonthDay object representation to String
     *
     * @param date   gmonthDay object
     * @return lexical representation of month: --MM-DD with an optional time zone sign
     */
    protected String dateToString(int[] date) {
        message.setLength(0);
        message.append('-');
        message.append('-');
        message.append(date[M]);
        message.append('-');
        message.append(date[D]);
        message.append((char)date[utc]);
        return message.toString();
    }

}

/*
 */
public static class SchemaDateTimeException extends RuntimeException {
    public SchemaDateTimeException () {
        super();
    }

    public SchemaDateTimeException (String s) {
        super (s);
    }
}
/*
 * Validator for <time> datatype (W3C Schema Datatypes)
 *
 * @author Elena Litani
 * @author Gopal Sharma, SUN Microsystem Inc.
 */
public static class TimeDV extends AbstractDateTimeDV {

    /*
     * Convert a string to a compiled form
     *
     * @param  content The lexical representation of time
     * @return a valid and normalized time object
     */
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException{
        try{
            return parse(content, null);
        } catch(Exception ex){
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "time"});
        }
    }

    /*
     * Parses, validates and computes normalized version of time object
     *
     * @param str    The lexical representation of time object hh:mm:ss.sss
     *               with possible time zone Z or (-),(+)hh:mm
     *               Pattern: "(\\d\\d):(\\d\\d):(\\d\\d)(\\.(\\d)*)?(Z|(([-+])(\\d\\d)(:(\\d\\d))?))?")
     * @param date   uninitialized date object
     * @return normalized time representation
     * @exception SchemaDateTimeException Invalid lexical representation
     */
    protected int[] parse(String str, int[] date) throws SchemaDateTimeException{

        resetBuffer(str);

        //create structure to hold an object
        if ( date == null ) {
            date = new int[TOTAL_SIZE];
        }
        resetDateObj(date);

        // time
        // initialize to default values
        date[CY]=YEAR;
        date[M]=MONTH;
        date[D]=DAY;
        getTime(fStart, fEnd, date);

        //validate and normalize

        validateDateTime(date);

        if ( date[utc]!=0 ) {
            normalize(date);
        }
                return date;
    }

    /*
     * Converts time object representation to String
     *
     * @param date   time object
     * @return lexical representation of time: hh:mm:ss.sss with an optional time zone sign
     */
    protected String dateToString(int[] date) {
        message.setLength(0);
        message.append(date[h]);
        message.append(':');
        message.append(date[m]);
        message.append(':');
        message.append(date[s]);
        message.append('.');
        message.append(date[ms]);
        message.append((char)date[utc]);
        return message.toString();
    }

}
/*
 * Validator for <gYear> datatype (W3C Schema Datatypes)
 *
 * @author Elena Litani
 * @author Gopal Sharma, SUN Microsystem Inc.
 */

public static class YearDV extends AbstractDateTimeDV {

    /*
     * Convert a string to a compiled form
     *
     * @param  content The lexical representation of time
     * @return a valid and normalized time object
     */
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException{
        try{
            return parse(content, null);
        } catch(Exception ex){
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "gYear"});
        }
    }

    /*
     * Parses, validates and computes normalized version of gYear object
     *
     * @param str    The lexical representation of year object CCYY
     *               with possible time zone Z or (-),(+)hh:mm
     * @param date   uninitialized date object
     * @return normalized date representation
     * @exception SchemaDateTimeException Invalid lexical representation
     */
    protected int[] parse(String str, int[] date) throws SchemaDateTimeException{
        resetBuffer(str);

        //create structure to hold an object
        if ( date == null ) {
            date = new int[TOTAL_SIZE];
        }
        resetDateObj(date);

        // check for preceding '-' sign
        int start = 0;
        if (fBuffer.charAt(0)=='-') {
            start = 1;
        }
        int sign = findUTCSign(start, fEnd);
        if (sign == -1) {
            date[CY]=parseIntYear(fEnd);
        }
        else {
            date[CY]=parseIntYear(sign);
            getTimeZone (date, sign);
        }

        //initialize values
        date[M]=MONTH;
        date[D]=1;

        //validate and normalize
        validateDateTime(date);

        if ( date[utc]!=0 && date[utc]!='Z' ) {
            normalize(date);
        }
        return date;
    }

    /*
     * Converts year object representation to String
     *
     * @param date   year object
     * @return lexical representation of month: CCYY with optional time zone sign
     */
    protected String dateToString(int[] date) {

        message.setLength(0);
        message.append(date[CY]);
        message.append((char)date[utc]);
        return message.toString();
    }

}


/*
 * Validator for <gYearMonth> datatype (W3C Schema Datatypes)
 *
 * @author Elena Litani
 * @author Gopal Sharma, SUN Microsystem Inc.
 */
public static class YearMonthDV extends AbstractDateTimeDV{

    /*
     * Convert a string to a compiled form
     *
     * @param  content The lexical representation of gYearMonth
     * @return a valid and normalized gYearMonth object
     */
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException{
        try{
            return parse(content, null);
        } catch(Exception ex){
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "gYearMonth"});
        }
    }

    /*
     * Parses, validates and computes normalized version of gYearMonth object
     *
     * @param str    The lexical representation of gYearMonth object CCYY-MM
     *               with possible time zone Z or (-),(+)hh:mm
     * @param date   uninitialized date object
     * @return normalized date representation
     * @exception SchemaDateTimeException Invalid lexical representation
     */
    protected int[] parse(String str, int[] date) throws SchemaDateTimeException{
        resetBuffer(str);

        //create structure to hold an object
        if ( date == null ) {
            date = new int[TOTAL_SIZE];
        }
        resetDateObj(date);

        // get date
        getYearMonth(fStart, fEnd, date);
        date[D] = DAY;
        parseTimeZone (fEnd, date);

        //validate and normalize

        validateDateTime(date);

        if ( date[utc]!=0 && date[utc]!='Z' ) {
            normalize(date);
        }
        return date;
    }

}



/*********************************************************************
* A class to represent a Uniform Resource Identifier (URI). This class
* is designed to handle the parsing of URIs and provide access to
* the various components (scheme, host, port, userinfo, path, query
* string and fragment) that may constitute a URI.
* <p>
* Parsing of a URI specification is done according to the URI
* syntax described in RFC 2396
* <http://www.ietf.org/rfc/rfc2396.txt?number=2396>. Every URI consists
* of a scheme, followed by a colon (':'), followed by a scheme-specific
* part. For URIs that follow the "generic URI" syntax, the scheme-
* specific part begins with two slashes ("//") and may be followed
* by an authority segment (comprised of user information, host, and
* port), path segment, query segment and fragment. Note that RFC 2396
* no longer specifies the use of the parameters segment and excludes
* the "user:password" syntax as part of the authority segment. If
* "user:password" appears in a URI, the entire user/password string
* is stored as userinfo.
* <p>
* For URIs that do not follow the "generic URI" syntax (e.g. mailto),
* the entire scheme-specific part is treated as the "path" portion
* of the URI.
* <p>
* Note that, unlike the java.net.URL class, this class does not provide
* any built-in network access functionality nor does it provide any
* scheme-specific functionality (for example, it does not know a
* default port for a specific scheme). Rather, it only knows the
* grammar and basic set of operations that can be applied to a URI.
*
**********************************************************************/
 public static class URI implements Serializable {

  /******************************************************************
  * MalformedURIExceptions are thrown in the process of building a URI
  * or setting fields on a URI when an operation would result in an
  * invalid URI specification.
  *
  ********************************************************************/
  public static class MalformedURIException extends IOException {

   /*****************************************************************
    * Constructs a <code>MalformedURIException</code> with no specified
    * detail message.
    ******************************************************************/
    public MalformedURIException() {
      super();
    }

    /****************************************************************
    * Constructs a <code>MalformedURIException</code> with the
    * specified detail message.
    *
    * @param p_msg the detail message.
    ******************************************************************/
    public MalformedURIException(String p_msg) {
      super(p_msg);
    }
  }

  /* reserved characters */
  private static final String RESERVED_CHARACTERS = ";/?:@&=+$,";

  /* URI punctuation mark characters - these, combined with
      alphanumerics, constitute the "unreserved" characters */
  private static final String MARK_CHARACTERS = "-_.!~*'() ";

  /* scheme can be composed of alphanumerics and these characters */
  private static final String SCHEME_CHARACTERS = "+-.";

  /* userinfo can be composed of unreserved, escaped and these
      characters */
  private static final String USERINFO_CHARACTERS = ";:&=+$,";

  /* Stores the scheme (usually the protocol) for this URI. */
  private String m_scheme = null;

  /* If specified, stores the userinfo for this URI; otherwise null */
  private String m_userinfo = null;

  /* If specified, stores the host for this URI; otherwise null */
  private String m_host = null;

  /* If specified, stores the port for this URI; otherwise -1 */
  private int m_port = -1;

  /* If specified, stores the path for this URI; otherwise null */
  private String m_path = null;

  /* If specified, stores the query string for this URI; otherwise
      null.  */
  private String m_queryString = null;

  /* If specified, stores the fragment for this URI; otherwise null */
  private String m_fragment = null;

  private static boolean DEBUG = false;

  /*
  * Construct a new and uninitialized URI.
  */
  public URI() {
  }

 /*
  * Construct a new URI from another URI. All fields for this URI are
  * set equal to the fields of the URI passed in.
  *
  * @param p_other the URI to copy (cannot be null)
  */
  public URI(URI p_other) {
    initialize(p_other);
  }

 /*
  * Construct a new URI from a URI specification string. If the
  * specification follows the "generic URI" syntax, (two slashes
  * following the first colon), the specification will be parsed
  * accordingly - setting the scheme, userinfo, host,port, path, query
  * string and fragment fields as necessary. If the specification does
  * not follow the "generic URI" syntax, the specification is parsed
  * into a scheme and scheme-specific part (stored as the path) only.
  *
  * @param p_uriSpec the URI specification string (cannot be null or
  *                  empty)
  *
  * @exception MalformedURIException if p_uriSpec violates any syntax
  *                                   rules
  */
  public URI(String p_uriSpec) throws MalformedURIException {
    this((URI)null, p_uriSpec);
  }

 /*
  * Construct a new URI from a base URI and a URI specification string.
  * The URI specification string may be a relative URI.
  *
  * @param p_base the base URI (cannot be null if p_uriSpec is null or
  *               empty)
  * @param p_uriSpec the URI specification string (cannot be null or
  *                  empty if p_base is null)
  *
  * @exception MalformedURIException if p_uriSpec violates any syntax
  *                                  rules
  */
  public URI(URI p_base, String p_uriSpec) throws MalformedURIException {
    initialize(p_base, p_uriSpec);
  }

 /*
  * Construct a new URI that does not follow the generic URI syntax.
  * Only the scheme and scheme-specific part (stored as the path) are
  * initialized.
  *
  * @param p_scheme the URI scheme (cannot be null or empty)
  * @param p_schemeSpecificPart the scheme-specific part (cannot be
  *                             null or empty)
  *
  * @exception MalformedURIException if p_scheme violates any
  *                                  syntax rules
  */
  public URI(String p_scheme, String p_schemeSpecificPart)
             throws MalformedURIException {
    if (p_scheme == null || p_scheme.trim().length() == 0) {
      throw new MalformedURIException(
            "Cannot construct URI with null/empty scheme!");
    }
    if (p_schemeSpecificPart == null ||
        p_schemeSpecificPart.trim().length() == 0) {
      throw new MalformedURIException(
          "Cannot construct URI with null/empty scheme-specific part!");
    }
    setScheme(p_scheme);
    setPath(p_schemeSpecificPart);
  }

 /*
  * Construct a new URI that follows the generic URI syntax from its
  * component parts. Each component is validated for syntax and some
  * basic semantic checks are performed as well.  See the individual
  * setter methods for specifics.
  *
  * @param p_scheme the URI scheme (cannot be null or empty)
  * @param p_host the hostname or IPv4 address for the URI
  * @param p_path the URI path - if the path contains '?' or '#',
  *               then the query string and/or fragment will be
  *               set from the path; however, if the query and
  *               fragment are specified both in the path and as
  *               separate parameters, an exception is thrown
  * @param p_queryString the URI query string (cannot be specified
  *                      if path is null)
  * @param p_fragment the URI fragment (cannot be specified if path
  *                   is null)
  *
  * @exception MalformedURIException if any of the parameters violates
  *                                  syntax rules or semantic rules
  */
  public URI(String p_scheme, String p_host, String p_path,
             String p_queryString, String p_fragment)
         throws MalformedURIException {
    this(p_scheme, null, p_host, -1, p_path, p_queryString, p_fragment);
  }

 /*
  * Construct a new URI that follows the generic URI syntax from its
  * component parts. Each component is validated for syntax and some
  * basic semantic checks are performed as well.  See the individual
  * setter methods for specifics.
  *
  * @param p_scheme the URI scheme (cannot be null or empty)
  * @param p_userinfo the URI userinfo (cannot be specified if host
  *                   is null)
  * @param p_host the hostname or IPv4 address for the URI
  * @param p_port the URI port (may be -1 for "unspecified"; cannot
  *               be specified if host is null)
  * @param p_path the URI path - if the path contains '?' or '#',
  *               then the query string and/or fragment will be
  *               set from the path; however, if the query and
  *               fragment are specified both in the path and as
  *               separate parameters, an exception is thrown
  * @param p_queryString the URI query string (cannot be specified
  *                      if path is null)
  * @param p_fragment the URI fragment (cannot be specified if path
  *                   is null)
  *
  * @exception MalformedURIException if any of the parameters violates
  *                                  syntax rules or semantic rules
  */
  public URI(String p_scheme, String p_userinfo,
             String p_host, int p_port, String p_path,
             String p_queryString, String p_fragment)
         throws MalformedURIException {
    if (p_scheme == null || p_scheme.trim().length() == 0) {
      throw new MalformedURIException("Scheme is required!");
    }

    if (p_host == null) {
      if (p_userinfo != null) {
        throw new MalformedURIException(
             "Userinfo may not be specified if host is not specified!");
      }
      if (p_port != -1) {
        throw new MalformedURIException(
             "Port may not be specified if host is not specified!");
      }
    }

    if (p_path != null) {
      if (p_path.indexOf('?') != -1 && p_queryString != null) {
        throw new MalformedURIException(
          "Query string cannot be specified in path and query string!");
      }

      if (p_path.indexOf('#') != -1 && p_fragment != null) {
        throw new MalformedURIException(
          "Fragment cannot be specified in both the path and fragment!");
      }
    }

    setScheme(p_scheme);
    setHost(p_host);
    setPort(p_port);
    setUserinfo(p_userinfo);
    setPath(p_path);
    setQueryString(p_queryString);
    setFragment(p_fragment);
  }

 /*
  * Initialize all fields of this URI from another URI.
  *
  * @param p_other the URI to copy (cannot be null)
  */
  private void initialize(URI p_other) {
    m_scheme = p_other.getScheme();
    m_userinfo = p_other.getUserinfo();
    m_host = p_other.getHost();
    m_port = p_other.getPort();
    m_path = p_other.getPath();
    m_queryString = p_other.getQueryString();
    m_fragment = p_other.getFragment();
  }

 /*
  * Initializes this URI from a base URI and a URI specification string.
  * See RFC 2396 Section 4 and Appendix B for specifications on parsing
  * the URI and Section 5 for specifications on resolving relative URIs
  * and relative paths.
  *
  * @param p_base the base URI (may be null if p_uriSpec is an absolute
  *               URI)
  * @param p_uriSpec the URI spec string which may be an absolute or
  *                  relative URI (can only be null/empty if p_base
  *                  is not null)
  *
  * @exception MalformedURIException if p_base is null and p_uriSpec
  *                                  is not an absolute URI or if
  *                                  p_uriSpec violates syntax rules
  */
  private void initialize(URI p_base, String p_uriSpec)
                         throws MalformedURIException {
    if (p_base == null &&
        (p_uriSpec == null || p_uriSpec.trim().length() == 0)) {
      throw new MalformedURIException(
                  "Cannot initialize URI with empty parameters.");
      }

    // just make a copy of the base if spec is empty
    if (p_uriSpec == null || p_uriSpec.trim().length() == 0) {
      initialize(p_base);
      return;
    }

    String uriSpec = p_uriSpec.trim();
    int uriSpecLen = uriSpec.length();
    int index = 0;

    // Check for scheme, which must be before '/', '?' or '#'. Also handle
    // names with DOS drive letters ('D:'), so 1-character schemes are not
    // allowed.
    int colonIdx    = uriSpec.indexOf(':');
    int slashIdx    = uriSpec.indexOf('/');
    int queryIdx    = uriSpec.indexOf('?');
    int fragmentIdx = uriSpec.indexOf('#');

    if ((colonIdx < 2) ||
        (colonIdx > slashIdx && slashIdx != -1) ||
        (colonIdx > queryIdx && queryIdx != -1) ||
        (colonIdx > fragmentIdx && fragmentIdx != -1)) {
      // A standalone base is a valid URI according to spec
      if (p_base == null && fragmentIdx != 0 ) {
        throw new MalformedURIException("No scheme found in URI.");
      }
    }
    else {
      initializeScheme(uriSpec);
      index = m_scheme.length()+1;
    }

    // two slashes means generic URI syntax, so we get the authority
    if (((index+1) < uriSpecLen) &&
        (uriSpec.substring(index).startsWith("//"))) {
      index += 2;
      int startPos = index;

      // get authority - everything up to path, query or fragment
      char testChar = '\0';
      while (index < uriSpecLen) {
        testChar = uriSpec.charAt(index);
        if (testChar == '/' || testChar == '?' || testChar == '#') {
          break;
        }
        index++;
      }

      // if we found authority, parse it out, otherwise we set the
      // host to empty string
      if (index > startPos) {
        initializeAuthority(uriSpec.substring(startPos, index));
      }
      else {
        m_host = "";
      }
    }

    initializePath(uriSpec.substring(index));

    // Resolve relative URI to base URI - see RFC 2396 Section 5.2
    // In some cases, it might make more sense to throw an exception
    // (when scheme is specified is the string spec and the base URI
    // is also specified, for example), but we're just following the
    // RFC specifications
    if (p_base != null) {

      // check to see if this is the current doc - RFC 2396 5.2 #2
      // note that this is slightly different from the RFC spec in that
      // we don't include the check for query string being null
      // - this handles cases where the urispec is just a query
      // string or a fragment (e.g. "?y" or "#s") -
      // see <http://www.ics.uci.edu/~fielding/url/test1.html> which
      // identified this as a bug in the RFC
      if (m_path.length() == 0 && m_scheme == null &&
          m_host == null) {
        m_scheme = p_base.getScheme();
        m_userinfo = p_base.getUserinfo();
        m_host = p_base.getHost();
        m_port = p_base.getPort();
        m_path = p_base.getPath();

        if (m_queryString == null) {
          m_queryString = p_base.getQueryString();
        }
        return;
      }

      // check for scheme - RFC 2396 5.2 #3
      // if we found a scheme, it means absolute URI, so we're done
      if (m_scheme == null) {
        m_scheme = p_base.getScheme();
      }
      else {
        return;
      }

      // check for authority - RFC 2396 5.2 #4
      // if we found a host, then we've got a network path, so we're done
      if (m_host == null) {
        m_userinfo = p_base.getUserinfo();
        m_host = p_base.getHost();
        m_port = p_base.getPort();
      }
      else {
        return;
      }

      // check for absolute path - RFC 2396 5.2 #5
      if (m_path.length() > 0 &&
          m_path.startsWith("/")) {
        return;
      }

      // if we get to this point, we need to resolve relative path
      // RFC 2396 5.2 #6
      String path = new String();
      String basePath = p_base.getPath();

      // 6a - get all but the last segment of the base URI path
      if (basePath != null) {
        int lastSlash = basePath.lastIndexOf('/');
        if (lastSlash != -1) {
          path = basePath.substring(0, lastSlash+1);
        }
      }

      // 6b - append the relative URI path
      path = path.concat(m_path);

      // 6c - remove all "./" where "." is a complete path segment
      index = -1;
      while ((index = path.indexOf("/./")) != -1) {
        path = path.substring(0, index+1).concat(path.substring(index+3));
      }

      // 6d - remove "." if path ends with "." as a complete path segment
      if (path.endsWith("/.")) {
        path = path.substring(0, path.length()-1);
      }

      // 6e - remove all "<segment>/../" where "<segment>" is a complete
      // path segment not equal to ".."
      index = 1;
      int segIndex = -1;
      String tempString = null;

      while ((index = path.indexOf("/../", index)) > 0) {
        tempString = path.substring(0, path.indexOf("/../"));
        segIndex = tempString.lastIndexOf('/');
        if (segIndex != -1) {
          if (!tempString.substring(segIndex).equals("..")) {
            path = path.substring(0, segIndex+1).concat(path.substring(index+4));
            index = segIndex;
          }
          else
            index += 4;
        }
        else
          index += 4;
      }

      // 6f - remove ending "<segment>/.." where "<segment>" is a
      // complete path segment
      if (path.endsWith("/..")) {
        tempString = path.substring(0, path.length()-3);
        segIndex = tempString.lastIndexOf('/');
        if (segIndex != -1) {
          path = path.substring(0, segIndex+1);
        }
      }
      m_path = path;
    }
  }

 /*
  * Initialize the scheme for this URI from a URI string spec.
  *
  * @param p_uriSpec the URI specification (cannot be null)
  *
  * @exception MalformedURIException if URI does not have a conformant
  *                                  scheme
  */
  private void initializeScheme(String p_uriSpec)
                 throws MalformedURIException {
    int uriSpecLen = p_uriSpec.length();
    int index = 0;
    String scheme = null;
    char testChar = '\0';

    while (index < uriSpecLen) {
      testChar = p_uriSpec.charAt(index);
      if (testChar == ':' || testChar == '/' ||
          testChar == '?' || testChar == '#') {
        break;
      }
      index++;
    }
    scheme = p_uriSpec.substring(0, index);

    if (scheme.length() == 0) {
      throw new MalformedURIException("No scheme found in URI.");
    }
    else {
      setScheme(scheme);
    }
  }

 /*
  * Initialize the authority (userinfo, host and port) for this
  * URI from a URI string spec.
  *
  * @param p_uriSpec the URI specification (cannot be null)
  *
  * @exception MalformedURIException if p_uriSpec violates syntax rules
  */
  private void initializeAuthority(String p_uriSpec)
                 throws MalformedURIException {
    int index = 0;
    int start = 0;
    int end = p_uriSpec.length();
    char testChar = '\0';
    String userinfo = null;

    // userinfo is everything up @
    if (p_uriSpec.indexOf('@', start) != -1) {
      while (index < end) {
        testChar = p_uriSpec.charAt(index);
        if (testChar == '@') {
          break;
        }
        index++;
      }
      userinfo = p_uriSpec.substring(start, index);
      index++;
    }

    // host is everything up to ':'
    String host = null;
    start = index;
    while (index < end) {
      testChar = p_uriSpec.charAt(index);
      if (testChar == ':') {
        break;
      }
      index++;
    }
    host = p_uriSpec.substring(start, index);
    int port = -1;
    if (host.length() > 0) {
      // port
      if (testChar == ':') {
        index++;
        start = index;
        while (index < end) {
          index++;
        }
        String portStr = p_uriSpec.substring(start, index);
        if (portStr.length() > 0) {
          for (int i = 0; i < portStr.length(); i++) {
            if (!isDigit(portStr.charAt(i))) {
              throw new MalformedURIException(
                   portStr +
                   " is invalid. Port should only contain digits!");
            }
          }
          try {
            port = Integer.parseInt(portStr);
          }
          catch (NumberFormatException nfe) {
            // can't happen
          }
        }
      }
    }
    setHost(host);
    setPort(port);
    setUserinfo(userinfo);
  }

 /*
  * Initialize the path for this URI from a URI string spec.
  *
  * @param p_uriSpec the URI specification (cannot be null)
  *
  * @exception MalformedURIException if p_uriSpec violates syntax rules
  */
  private void initializePath(String p_uriSpec)
                 throws MalformedURIException {
    if (p_uriSpec == null) {
      throw new MalformedURIException(
                "Cannot initialize path from null string!");
    }

    int index = 0;
    int start = 0;
    int end = p_uriSpec.length();
    char testChar = '\0';

    // path - everything up to query string or fragment
    while (index < end) {
      testChar = p_uriSpec.charAt(index);
      if (testChar == '?' || testChar == '#') {
        break;
      }
      // check for valid escape sequence
      if (testChar == '%') {
         if (index+2 >= end ||
            !isHex(p_uriSpec.charAt(index+1)) ||
            !isHex(p_uriSpec.charAt(index+2))) {
          throw new MalformedURIException(
                "Path contains invalid escape sequence!");
         }
      }
      else if (!isReservedCharacter(testChar) &&
               !isUnreservedCharacter(testChar)) {
        throw new MalformedURIException(
                  "Path contains invalid character: " + testChar);
      }
      index++;
    }
    m_path = p_uriSpec.substring(start, index);

    // query - starts with ? and up to fragment or end
    if (testChar == '?') {
      index++;
      start = index;
      while (index < end) {
        testChar = p_uriSpec.charAt(index);
        if (testChar == '#') {
          break;
        }
        if (testChar == '%') {
           if (index+2 >= end ||
              !isHex(p_uriSpec.charAt(index+1)) ||
              !isHex(p_uriSpec.charAt(index+2))) {
            throw new MalformedURIException(
                    "Query string contains invalid escape sequence!");
           }
        }
        else if (!isReservedCharacter(testChar) &&
                 !isUnreservedCharacter(testChar)) {
          throw new MalformedURIException(
                "Query string contains invalid character:" + testChar);
        }
        index++;
      }
      m_queryString = p_uriSpec.substring(start, index);
    }

    // fragment - starts with #
    if (testChar == '#') {
      index++;
      start = index;
      while (index < end) {
        testChar = p_uriSpec.charAt(index);

        if (testChar == '%') {
           if (index+2 >= end ||
              !isHex(p_uriSpec.charAt(index+1)) ||
              !isHex(p_uriSpec.charAt(index+2))) {
            throw new MalformedURIException(
                    "Fragment contains invalid escape sequence!");
           }
        }
        else if (!isReservedCharacter(testChar) &&
                 !isUnreservedCharacter(testChar)) {
          throw new MalformedURIException(
                "Fragment contains invalid character:"+testChar);
        }
        index++;
      }
      m_fragment = p_uriSpec.substring(start, index);
    }
  }

 /*
  * Get the scheme for this URI.
  *
  * @return the scheme for this URI
  */
  public String getScheme() {
    return m_scheme;
  }

 /*
  * Get the scheme-specific part for this URI (everything following the
  * scheme and the first colon). See RFC 2396 Section 5.2 for spec.
  *
  * @return the scheme-specific part for this URI
  */
  public String getSchemeSpecificPart() {
    StringBuffer schemespec = new StringBuffer();

    if (m_userinfo != null || m_host != null || m_port != -1) {
      schemespec.append("//");
    }

    if (m_userinfo != null) {
      schemespec.append(m_userinfo);
      schemespec.append('@');
    }

    if (m_host != null) {
      schemespec.append(m_host);
    }

    if (m_port != -1) {
      schemespec.append(':');
      schemespec.append(m_port);
    }

    if (m_path != null) {
      schemespec.append((m_path));
    }

    if (m_queryString != null) {
      schemespec.append('?');
      schemespec.append(m_queryString);
    }

    if (m_fragment != null) {
      schemespec.append('#');
      schemespec.append(m_fragment);
    }

    return schemespec.toString();
  }

 /*
  * Get the userinfo for this URI.
  *
  * @return the userinfo for this URI (null if not specified).
  */
  public String getUserinfo() {
    return m_userinfo;
  }

  /*
  * Get the host for this URI.
  *
  * @return the host for this URI (null if not specified).
  */
  public String getHost() {
    return m_host;
  }

 /*
  * Get the port for this URI.
  *
  * @return the port for this URI (-1 if not specified).
  */
  public int getPort() {
    return m_port;
  }

 /*
  * Get the path for this URI (optionally with the query string and
  * fragment).
  *
  * @param p_includeQueryString if true (and query string is not null),
  *                             then a "?" followed by the query string
  *                             will be appended
  * @param p_includeFragment if true (and fragment is not null),
  *                             then a "#" followed by the fragment
  *                             will be appended
  *
  * @return the path for this URI possibly including the query string
  *         and fragment
  */
  public String getPath(boolean p_includeQueryString,
                        boolean p_includeFragment) {
    StringBuffer pathString = new StringBuffer(m_path);

    if (p_includeQueryString && m_queryString != null) {
      pathString.append('?');
      pathString.append(m_queryString);
    }

    if (p_includeFragment && m_fragment != null) {
      pathString.append('#');
      pathString.append(m_fragment);
    }
    return pathString.toString();
  }

 /*
  * Get the path for this URI. Note that the value returned is the path
  * only and does not include the query string or fragment.
  *
  * @return the path for this URI.
  */
  public String getPath() {
    return m_path;
  }

 /*
  * Get the query string for this URI.
  *
  * @return the query string for this URI. Null is returned if there
  *         was no "?" in the URI spec, empty string if there was a
  *         "?" but no query string following it.
  */
  public String getQueryString() {
    return m_queryString;
  }

 /*
  * Get the fragment for this URI.
  *
  * @return the fragment for this URI. Null is returned if there
  *         was no "#" in the URI spec, empty string if there was a
  *         "#" but no fragment following it.
  */
  public String getFragment() {
    return m_fragment;
  }

 /*
  * Set the scheme for this URI. The scheme is converted to lowercase
  * before it is set.
  *
  * @param p_scheme the scheme for this URI (cannot be null)
  *
  * @exception MalformedURIException if p_scheme is not a conformant
  *                                  scheme name
  */
  public void setScheme(String p_scheme) throws MalformedURIException {
    if (p_scheme == null) {
      throw new MalformedURIException(
                "Cannot set scheme from null string!");
    }
    if (!isConformantSchemeName(p_scheme)) {
      throw new MalformedURIException("The scheme is not conformant.");
    }

    m_scheme = p_scheme.toLowerCase();
  }

 /*
  * Set the userinfo for this URI. If a non-null value is passed in and
  * the host value is null, then an exception is thrown.
  *
  * @param p_userinfo the userinfo for this URI
  *
  * @exception MalformedURIException if p_userinfo contains invalid
  *                                  characters
  */
  public void setUserinfo(String p_userinfo) throws MalformedURIException {
    if (p_userinfo == null) {
      m_userinfo = null;
    }
    else {
      if (m_host == null) {
        throw new MalformedURIException(
                     "Userinfo cannot be set when host is null!");
      }

      // userinfo can contain alphanumerics, mark characters, escaped
      // and ';',':','&','=','+','$',','
      int index = 0;
      int end = p_userinfo.length();
      char testChar = '\0';
      while (index < end) {
        testChar = p_userinfo.charAt(index);
        if (testChar == '%') {
          if (index+2 >= end ||
              !isHex(p_userinfo.charAt(index+1)) ||
              !isHex(p_userinfo.charAt(index+2))) {
            throw new MalformedURIException(
                  "Userinfo contains invalid escape sequence!");
          }
        }
        else if (!isUnreservedCharacter(testChar) &&
                 USERINFO_CHARACTERS.indexOf(testChar) == -1) {
          throw new MalformedURIException(
                  "Userinfo contains invalid character:"+testChar);
        }
        index++;
      }
    }
    m_userinfo = p_userinfo;
  }

  /*
  * Set the host for this URI. If null is passed in, the userinfo
  * field is also set to null and the port is set to -1.
  *
  * @param p_host the host for this URI
  *
  * @exception MalformedURIException if p_host is not a valid IP
  *                                  address or DNS hostname.
  */
  public void setHost(String p_host) throws MalformedURIException {
    if (p_host == null || p_host.trim().length() == 0) {
      m_host = p_host;
      m_userinfo = null;
      m_port = -1;
    }
    else if (!isWellFormedAddress(p_host)) {
      throw new MalformedURIException("Host is not a well formed address!");
    }
    m_host = p_host;
  }

 /*
  * Set the port for this URI. -1 is used to indicate that the port is
  * not specified, otherwise valid port numbers are  between 0 and 65535.
  * If a valid port number is passed in and the host field is null,
  * an exception is thrown.
  *
  * @param p_port the port number for this URI
  *
  * @exception MalformedURIException if p_port is not -1 and not a
  *                                  valid port number
  */
  public void setPort(int p_port) throws MalformedURIException {
    if (p_port >= 0 && p_port <= 65535) {
      if (m_host == null) {
        throw new MalformedURIException(
                      "Port cannot be set when host is null!");
      }
    }
    else if (p_port != -1) {
      throw new MalformedURIException("Invalid port number!");
    }
    m_port = p_port;
  }

 /*
  * Set the path for this URI. If the supplied path is null, then the
  * query string and fragment are set to null as well. If the supplied
  * path includes a query string and/or fragment, these fields will be
  * parsed and set as well. Note that, for URIs following the "generic
  * URI" syntax, the path specified should start with a slash.
  * For URIs that do not follow the generic URI syntax, this method
  * sets the scheme-specific part.
  *
  * @param p_path the path for this URI (may be null)
  *
  * @exception MalformedURIException if p_path contains invalid
  *                                  characters
  */
  public void setPath(String p_path) throws MalformedURIException {
    if (p_path == null) {
      m_path = null;
      m_queryString = null;
      m_fragment = null;
    }
    else {
      initializePath(p_path);
    }
  }

 /*
  * Append to the end of the path of this URI. If the current path does
  * not end in a slash and the path to be appended does not begin with
  * a slash, a slash will be appended to the current path before the
  * new segment is added. Also, if the current path ends in a slash
  * and the new segment begins with a slash, the extra slash will be
  * removed before the new segment is appended.
  *
  * @param p_addToPath the new segment to be added to the current path
  *
  * @exception MalformedURIException if p_addToPath contains syntax
  *                                  errors
  */
  public void appendPath(String p_addToPath)
                         throws MalformedURIException {
    if (p_addToPath == null || p_addToPath.trim().length() == 0) {
      return;
    }

    if (!isURIString(p_addToPath)) {
      throw new MalformedURIException(
              "Path contains invalid character!");
    }

    if (m_path == null || m_path.trim().length() == 0) {
      if (p_addToPath.startsWith("/")) {
        m_path = p_addToPath;
      }
      else {
        m_path = "/" + p_addToPath;
      }
    }
    else if (m_path.endsWith("/")) {
      if (p_addToPath.startsWith("/")) {
        m_path = m_path.concat(p_addToPath.substring(1));
      }
      else {
        m_path = m_path.concat(p_addToPath);
      }
    }
    else {
      if (p_addToPath.startsWith("/")) {
        m_path = m_path.concat(p_addToPath);
      }
      else {
        m_path = m_path.concat("/" + p_addToPath);
      }
    }
  }

 /*
  * Set the query string for this URI. A non-null value is valid only
  * if this is an URI conforming to the generic URI syntax and
  * the path value is not null.
  *
  * @param p_queryString the query string for this URI
  *
  * @exception MalformedURIException if p_queryString is not null and this
  *                                  URI does not conform to the generic
  *                                  URI syntax or if the path is null
  */
  public void setQueryString(String p_queryString) throws MalformedURIException {
    if (p_queryString == null) {
      m_queryString = null;
    }
    else if (!isGenericURI()) {
      throw new MalformedURIException(
              "Query string can only be set for a generic URI!");
    }
    else if (getPath() == null) {
      throw new MalformedURIException(
              "Query string cannot be set when path is null!");
    }
    else if (!isURIString(p_queryString)) {
      throw new MalformedURIException(
              "Query string contains invalid character!");
    }
    else {
      m_queryString = p_queryString;
    }
  }

 /*
  * Set the fragment for this URI. A non-null value is valid only
  * if this is a URI conforming to the generic URI syntax and
  * the path value is not null.
  *
  * @param p_fragment the fragment for this URI
  *
  * @exception MalformedURIException if p_fragment is not null and this
  *                                  URI does not conform to the generic
  *                                  URI syntax or if the path is null
  */
  public void setFragment(String p_fragment) throws MalformedURIException {
    if (p_fragment == null) {
      m_fragment = null;
    }
    else if (!isGenericURI()) {
      throw new MalformedURIException(
         "Fragment can only be set for a generic URI!");
    }
    else if (getPath() == null) {
      throw new MalformedURIException(
              "Fragment cannot be set when path is null!");
    }
    else if (!isURIString(p_fragment)) {
      throw new MalformedURIException(
              "Fragment contains invalid character!");
    }
    else {
      m_fragment = p_fragment;
    }
  }

 /*
  * Determines if the passed-in Object is equivalent to this URI.
  *
  * @param p_test the Object to test for equality.
  *
  * @return true if p_test is a URI with all values equal to this
  *         URI, false otherwise
  */
  public boolean equals(Object p_test) {
    if (p_test instanceof URI) {
      URI testURI = (URI) p_test;
      if (((m_scheme == null && testURI.m_scheme == null) ||
           (m_scheme != null && testURI.m_scheme != null &&
            m_scheme.equals(testURI.m_scheme))) &&
          ((m_userinfo == null && testURI.m_userinfo == null) ||
           (m_userinfo != null && testURI.m_userinfo != null &&
            m_userinfo.equals(testURI.m_userinfo))) &&
          ((m_host == null && testURI.m_host == null) ||
           (m_host != null && testURI.m_host != null &&
            m_host.equals(testURI.m_host))) &&
            m_port == testURI.m_port &&
          ((m_path == null && testURI.m_path == null) ||
           (m_path != null && testURI.m_path != null &&
            m_path.equals(testURI.m_path))) &&
          ((m_queryString == null && testURI.m_queryString == null) ||
           (m_queryString != null && testURI.m_queryString != null &&
            m_queryString.equals(testURI.m_queryString))) &&
          ((m_fragment == null && testURI.m_fragment == null) ||
           (m_fragment != null && testURI.m_fragment != null &&
            m_fragment.equals(testURI.m_fragment)))) {
        return true;
      }
    }
    return false;
  }

 /*
  * Get the URI as a string specification. See RFC 2396 Section 5.2.
  *
  * @return the URI string specification
  */
  public String toString() {
    StringBuffer uriSpecString = new StringBuffer();

    if (m_scheme != null) {
      uriSpecString.append(m_scheme);
      uriSpecString.append(':');
    }
    uriSpecString.append(getSchemeSpecificPart());
    return uriSpecString.toString();
  }

 /*
  * Get the indicator as to whether this URI uses the "generic URI"
  * syntax.
  *
  * @return true if this URI uses the "generic URI" syntax, false
  *         otherwise
  */
  public boolean isGenericURI() {
    // presence of the host (whether valid or empty) means
    // double-slashes which means generic uri
    return (m_host != null);
  }

 /*
  * Determine whether a scheme conforms to the rules for a scheme name.
  * A scheme is conformant if it starts with an alphanumeric, and
  * contains only alphanumerics, '+','-' and '.'.
  *
  * @return true if the scheme is conformant, false otherwise
  */
  public static boolean isConformantSchemeName(String p_scheme) {
    if (p_scheme == null || p_scheme.trim().length() == 0) {
      return false;
    }

    if (!isAlpha(p_scheme.charAt(0))) {
      return false;
    }

    char testChar;
    for (int i = 1; i < p_scheme.length(); i++) {
      testChar = p_scheme.charAt(i);
      if (!isAlphanum(testChar) &&
          SCHEME_CHARACTERS.indexOf(testChar) == -1) {
        return false;
      }
    }

    return true;
  }

 /*
  * Determine whether a string is syntactically capable of representing
  * a valid IPv4 address or the domain name of a network host. A valid
  * IPv4 address consists of four decimal digit groups separated by a
  * '.'. A hostname consists of domain labels (each of which must
  * begin and end with an alphanumeric but may contain '-') separated
  & by a '.'. See RFC 2396 Section 3.2.2.
  *
  * @return true if the string is a syntactically valid IPv4 address
  *              or hostname
  */
  public static boolean isWellFormedAddress(String p_address) {
    if (p_address == null) {
      return false;
    }

    String address = p_address.trim();
    int addrLength = address.length();
    if (addrLength == 0 || addrLength > 255) {
      return false;
    }

    if (address.startsWith(".") || address.startsWith("-")) {
      return false;
    }

    // rightmost domain label starting with digit indicates IP address
    // since top level domain label can only start with an alpha
    // see RFC 2396 Section 3.2.2
    int index = address.lastIndexOf('.');
    if (address.endsWith(".")) {
      index = address.substring(0, index).lastIndexOf('.');
    }

    if (index+1 < addrLength && isDigit(p_address.charAt(index+1))) {
      char testChar;
      int numDots = 0;

      // make sure that 1) we see only digits and dot separators, 2) that
      // any dot separator is preceded and followed by a digit and
      // 3) that we find 3 dots
      for (int i = 0; i < addrLength; i++) {
        testChar = address.charAt(i);
        if (testChar == '.') {
          if (!isDigit(address.charAt(i-1)) ||
              (i+1 < addrLength && !isDigit(address.charAt(i+1)))) {
            return false;
          }
          numDots++;
        }
        else if (!isDigit(testChar)) {
          return false;
        }
      }
      if (numDots != 3) {
        return false;
      }
    }
    else {
      // domain labels can contain alphanumerics and '-"
      // but must start and end with an alphanumeric
      char testChar;

      for (int i = 0; i < addrLength; i++) {
        testChar = address.charAt(i);
        if (testChar == '.') {
          if (!isAlphanum(address.charAt(i-1))) {
            return false;
          }
          if (i+1 < addrLength && !isAlphanum(address.charAt(i+1))) {
            return false;
          }
        }
        else if (!isAlphanum(testChar) && testChar != '-') {
          return false;
        }
      }
    }
    return true;
  }


 /*
  * Determine whether a char is a digit.
  *
  * @return true if the char is betweeen '0' and '9', false otherwise
  */
  private static boolean isDigit(char p_char) {
    return p_char >= '0' && p_char <= '9';
  }

 /*
  * Determine whether a character is a hexadecimal character.
  *
  * @return true if the char is betweeen '0' and '9', 'a' and 'f'
  *         or 'A' and 'F', false otherwise
  */
  private static boolean isHex(char p_char) {
    return (isDigit(p_char) ||
            (p_char >= 'a' && p_char <= 'f') ||
            (p_char >= 'A' && p_char <= 'F'));
  }

 /*
  * Determine whether a char is an alphabetic character: a-z or A-Z
  *
  * @return true if the char is alphabetic, false otherwise
  */
  private static boolean isAlpha(char p_char) {
    return ((p_char >= 'a' && p_char <= 'z') ||
            (p_char >= 'A' && p_char <= 'Z' ));
  }

 /*
  * Determine whether a char is an alphanumeric: 0-9, a-z or A-Z
  *
  * @return true if the char is alphanumeric, false otherwise
  */
  private static boolean isAlphanum(char p_char) {
    return (isAlpha(p_char) || isDigit(p_char));
  }

 /*
  * Determine whether a character is a reserved character:
  * ';', '/', '?', ':', '@', '&', '=', '+', '$' or ','
  *
  * @return true if the string contains any reserved characters
  */
  private static boolean isReservedCharacter(char p_char) {
    return RESERVED_CHARACTERS.indexOf(p_char) != -1;
  }

 /*
  * Determine whether a char is an unreserved character.
  *
  * @return true if the char is unreserved, false otherwise
  */
  private static boolean isUnreservedCharacter(char p_char) {
    return (isAlphanum(p_char) ||
            MARK_CHARACTERS.indexOf(p_char) != -1);
  }

 /*
  * Determine whether a given string contains only URI characters (also
  * called "uric" in RFC 2396). uric consist of all reserved
  * characters, unreserved characters and escaped characters.
  *
  * @return true if the string is comprised of uric, false otherwise
  */
  private static boolean isURIString(String p_uric) {
    if (p_uric == null) {
      return false;
    }
    int end = p_uric.length();
    char testChar = '\0';
    for (int i = 0; i < end; i++) {
      testChar = p_uric.charAt(i);
      if (testChar == '%') {
        if (i+2 >= end ||
            !isHex(p_uric.charAt(i+1)) ||
            !isHex(p_uric.charAt(i+2))) {
          return false;
        }
        else {
          i += 2;
          continue;
        }
      }
      if (isReservedCharacter(testChar) ||
          isUnreservedCharacter(testChar)) {
          continue;
      }
      else {
        return false;
      }
    }
    return true;
  }
}

/*
 * This class defines the basic XML character properties. The data
 * in this class can be used to verify that a character is a valid
 * XML character or if the character is a space, name start, or name
 * character.
 * <p>
 * A series of convenience methods are supplied to ease the burden
 * of the developer. Because inlining the checks can improve per
 * character performance, the tables of character properties are
 * public. Using the character as an index into the <code>CHARS</code>
 * array and applying the appropriate mask flag (e.g.
 * <code>MASK_VALID</code>), yields the same results as calling the
 * convenience methods. There is one exception: check the comments
 * for the <code>isValid</code> method for details.
 *
 * @author Glenn Marcy, IBM
 * @author Andy Clark, IBM
 * @author Eric Ye, IBM
 * @author Arnaud  Le Hors, IBM
 * @author Rahul Srivastava, Sun Microsystems Inc.
 */
public static class XMLChar {

    //
    // Constants
    //

    /* Character flags. */
    // performance may be improved on JVM's with bad JITs, but this creates
    // reusability problems.  
    private static final byte[] CHARS = new byte[1 << 16];

    /* Valid character mask. */
    public static final int MASK_VALID = 0x01;

    /* Space character mask. */
    public static final int MASK_SPACE = 0x02;

    /* Name start character mask. */
    public static final int MASK_NAME_START = 0x04;

    /* Name character mask. */
    public static final int MASK_NAME = 0x08;

    /* Pubid character mask. */
    public static final int MASK_PUBID = 0x10;
    
    /* 
     * Content character mask. Special characters are those that can
     * be considered the start of markup, such as '&lt;' and '&amp;'. 
     * The various newline characters are considered special as well.
     * All other valid XML characters can be considered content.
     * <p>
     * This is an optimization for the inner loop of character scanning.
     */
    public static final int MASK_CONTENT = 0x20;

    /* NCName start character mask. */
    public static final int MASK_NCNAME_START = 0x40;

    /* NCName character mask. */
    public static final int MASK_NCNAME = 0x80;

    //
    // Static initialization
    //

    static {
        
        //
        // [2] Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] |
        //              [#xE000-#xFFFD] | [#x10000-#x10FFFF]
        //

        int charRange[] = { 
            0x0009, 0x000A, 0x000D, 0x000D, 0x0020, 0xD7FF, 0xE000, 0xFFFD,
        };

        //
        // [3] S ::= (#x20 | #x9 | #xD | #xA)+
        //

        int spaceChar[] = { 
            0x0020, 0x0009, 0x000D, 0x000A,
        };

        //
        // [4] NameChar ::= Letter | Digit | '.' | '-' | '_' | ':' |
        //                  CombiningChar | Extender
        //

        int nameChar[] = { 
            0x002D, 0x002E, // '-' and '.'
        };

        //
        // [5] Name ::= (Letter | '_' | ':') (NameChar)*
        //

        int nameStartChar[] = { 
            0x003A, 0x005F, // ':' and '_'
        };

        //
        // [13] PubidChar ::= #x20 | 0xD | 0xA | [a-zA-Z0-9] | [-'()+,./:=?;!*#@$_%]
        //

        int pubidChar[] = {
            0x000A, 0x000D, 0x0020, 0x0021, 0x0023, 0x0024, 0x0025, 0x003D,
            0x005F
        };

        int pubidRange[] = {
            0x0027, 0x003B, 0x003F, 0x005A, 0x0061, 0x007A
        };

        //
        // [84] Letter ::= BaseChar | Ideographic
        //

        int letterRange[] = {
            // BaseChar
            0x0041, 0x005A, 0x0061, 0x007A, 0x00C0, 0x00D6, 0x00D8, 0x00F6,
            0x00F8, 0x0131, 0x0134, 0x013E, 0x0141, 0x0148, 0x014A, 0x017E,
            0x0180, 0x01C3, 0x01CD, 0x01F0, 0x01F4, 0x01F5, 0x01FA, 0x0217,
            0x0250, 0x02A8, 0x02BB, 0x02C1, 0x0388, 0x038A, 0x038E, 0x03A1,
            0x03A3, 0x03CE, 0x03D0, 0x03D6, 0x03E2, 0x03F3, 0x0401, 0x040C,
            0x040E, 0x044F, 0x0451, 0x045C, 0x045E, 0x0481, 0x0490, 0x04C4,
            0x04C7, 0x04C8, 0x04CB, 0x04CC, 0x04D0, 0x04EB, 0x04EE, 0x04F5,
            0x04F8, 0x04F9, 0x0531, 0x0556, 0x0561, 0x0586, 0x05D0, 0x05EA,
            0x05F0, 0x05F2, 0x0621, 0x063A, 0x0641, 0x064A, 0x0671, 0x06B7,
            0x06BA, 0x06BE, 0x06C0, 0x06CE, 0x06D0, 0x06D3, 0x06E5, 0x06E6,
            0x0905, 0x0939, 0x0958, 0x0961, 0x0985, 0x098C, 0x098F, 0x0990,
            0x0993, 0x09A8, 0x09AA, 0x09B0, 0x09B6, 0x09B9, 0x09DC, 0x09DD,
            0x09DF, 0x09E1, 0x09F0, 0x09F1, 0x0A05, 0x0A0A, 0x0A0F, 0x0A10,
            0x0A13, 0x0A28, 0x0A2A, 0x0A30, 0x0A32, 0x0A33, 0x0A35, 0x0A36,
            0x0A38, 0x0A39, 0x0A59, 0x0A5C, 0x0A72, 0x0A74, 0x0A85, 0x0A8B,
            0x0A8F, 0x0A91, 0x0A93, 0x0AA8, 0x0AAA, 0x0AB0, 0x0AB2, 0x0AB3,
            0x0AB5, 0x0AB9, 0x0B05, 0x0B0C, 0x0B0F, 0x0B10, 0x0B13, 0x0B28,
            0x0B2A, 0x0B30, 0x0B32, 0x0B33, 0x0B36, 0x0B39, 0x0B5C, 0x0B5D,
            0x0B5F, 0x0B61, 0x0B85, 0x0B8A, 0x0B8E, 0x0B90, 0x0B92, 0x0B95,
            0x0B99, 0x0B9A, 0x0B9E, 0x0B9F, 0x0BA3, 0x0BA4, 0x0BA8, 0x0BAA,
            0x0BAE, 0x0BB5, 0x0BB7, 0x0BB9, 0x0C05, 0x0C0C, 0x0C0E, 0x0C10,
            0x0C12, 0x0C28, 0x0C2A, 0x0C33, 0x0C35, 0x0C39, 0x0C60, 0x0C61,
            0x0C85, 0x0C8C, 0x0C8E, 0x0C90, 0x0C92, 0x0CA8, 0x0CAA, 0x0CB3,
            0x0CB5, 0x0CB9, 0x0CE0, 0x0CE1, 0x0D05, 0x0D0C, 0x0D0E, 0x0D10,
            0x0D12, 0x0D28, 0x0D2A, 0x0D39, 0x0D60, 0x0D61, 0x0E01, 0x0E2E,
            0x0E32, 0x0E33, 0x0E40, 0x0E45, 0x0E81, 0x0E82, 0x0E87, 0x0E88,
            0x0E94, 0x0E97, 0x0E99, 0x0E9F, 0x0EA1, 0x0EA3, 0x0EAA, 0x0EAB,
            0x0EAD, 0x0EAE, 0x0EB2, 0x0EB3, 0x0EC0, 0x0EC4, 0x0F40, 0x0F47,
            0x0F49, 0x0F69, 0x10A0, 0x10C5, 0x10D0, 0x10F6, 0x1102, 0x1103,
            0x1105, 0x1107, 0x110B, 0x110C, 0x110E, 0x1112, 0x1154, 0x1155,
            0x115F, 0x1161, 0x116D, 0x116E, 0x1172, 0x1173, 0x11AE, 0x11AF,
            0x11B7, 0x11B8, 0x11BC, 0x11C2, 0x1E00, 0x1E9B, 0x1EA0, 0x1EF9,
            0x1F00, 0x1F15, 0x1F18, 0x1F1D, 0x1F20, 0x1F45, 0x1F48, 0x1F4D,
            0x1F50, 0x1F57, 0x1F5F, 0x1F7D, 0x1F80, 0x1FB4, 0x1FB6, 0x1FBC,
            0x1FC2, 0x1FC4, 0x1FC6, 0x1FCC, 0x1FD0, 0x1FD3, 0x1FD6, 0x1FDB,
            0x1FE0, 0x1FEC, 0x1FF2, 0x1FF4, 0x1FF6, 0x1FFC, 0x212A, 0x212B,
            0x2180, 0x2182, 0x3041, 0x3094, 0x30A1, 0x30FA, 0x3105, 0x312C,
            0xAC00, 0xD7A3,
            // Ideographic
            0x3021, 0x3029, 0x4E00, 0x9FA5,
        };
        int letterChar[] = {
            // BaseChar
            0x0386, 0x038C, 0x03DA, 0x03DC, 0x03DE, 0x03E0, 0x0559, 0x06D5,
            0x093D, 0x09B2, 0x0A5E, 0x0A8D, 0x0ABD, 0x0AE0, 0x0B3D, 0x0B9C,
            0x0CDE, 0x0E30, 0x0E84, 0x0E8A, 0x0E8D, 0x0EA5, 0x0EA7, 0x0EB0,
            0x0EBD, 0x1100, 0x1109, 0x113C, 0x113E, 0x1140, 0x114C, 0x114E,
            0x1150, 0x1159, 0x1163, 0x1165, 0x1167, 0x1169, 0x1175, 0x119E,
            0x11A8, 0x11AB, 0x11BA, 0x11EB, 0x11F0, 0x11F9, 0x1F59, 0x1F5B,
            0x1F5D, 0x1FBE, 0x2126, 0x212E,
            // Ideographic
            0x3007,
        };

        //
        // [87] CombiningChar ::= ...
        //

        int combiningCharRange[] = {
            0x0300, 0x0345, 0x0360, 0x0361, 0x0483, 0x0486, 0x0591, 0x05A1,
            0x05A3, 0x05B9, 0x05BB, 0x05BD, 0x05C1, 0x05C2, 0x064B, 0x0652,
            0x06D6, 0x06DC, 0x06DD, 0x06DF, 0x06E0, 0x06E4, 0x06E7, 0x06E8,
            0x06EA, 0x06ED, 0x0901, 0x0903, 0x093E, 0x094C, 0x0951, 0x0954,
            0x0962, 0x0963, 0x0981, 0x0983, 0x09C0, 0x09C4, 0x09C7, 0x09C8,
            0x09CB, 0x09CD, 0x09E2, 0x09E3, 0x0A40, 0x0A42, 0x0A47, 0x0A48,
            0x0A4B, 0x0A4D, 0x0A70, 0x0A71, 0x0A81, 0x0A83, 0x0ABE, 0x0AC5,
            0x0AC7, 0x0AC9, 0x0ACB, 0x0ACD, 0x0B01, 0x0B03, 0x0B3E, 0x0B43,
            0x0B47, 0x0B48, 0x0B4B, 0x0B4D, 0x0B56, 0x0B57, 0x0B82, 0x0B83,
            0x0BBE, 0x0BC2, 0x0BC6, 0x0BC8, 0x0BCA, 0x0BCD, 0x0C01, 0x0C03,
            0x0C3E, 0x0C44, 0x0C46, 0x0C48, 0x0C4A, 0x0C4D, 0x0C55, 0x0C56,
            0x0C82, 0x0C83, 0x0CBE, 0x0CC4, 0x0CC6, 0x0CC8, 0x0CCA, 0x0CCD,
            0x0CD5, 0x0CD6, 0x0D02, 0x0D03, 0x0D3E, 0x0D43, 0x0D46, 0x0D48,
            0x0D4A, 0x0D4D, 0x0E34, 0x0E3A, 0x0E47, 0x0E4E, 0x0EB4, 0x0EB9,
            0x0EBB, 0x0EBC, 0x0EC8, 0x0ECD, 0x0F18, 0x0F19, 0x0F71, 0x0F84,
            0x0F86, 0x0F8B, 0x0F90, 0x0F95, 0x0F99, 0x0FAD, 0x0FB1, 0x0FB7,
            0x20D0, 0x20DC, 0x302A, 0x302F,
        };

        int combiningCharChar[] = {
            0x05BF, 0x05C4, 0x0670, 0x093C, 0x094D, 0x09BC, 0x09BE, 0x09BF,
            0x09D7, 0x0A02, 0x0A3C, 0x0A3E, 0x0A3F, 0x0ABC, 0x0B3C, 0x0BD7,
            0x0D57, 0x0E31, 0x0EB1, 0x0F35, 0x0F37, 0x0F39, 0x0F3E, 0x0F3F,
            0x0F97, 0x0FB9, 0x20E1, 0x3099, 0x309A,
        };

        //
        // [88] Digit ::= ...
        //

        int digitRange[] = {
            0x0030, 0x0039, 0x0660, 0x0669, 0x06F0, 0x06F9, 0x0966, 0x096F,
            0x09E6, 0x09EF, 0x0A66, 0x0A6F, 0x0AE6, 0x0AEF, 0x0B66, 0x0B6F,
            0x0BE7, 0x0BEF, 0x0C66, 0x0C6F, 0x0CE6, 0x0CEF, 0x0D66, 0x0D6F,
            0x0E50, 0x0E59, 0x0ED0, 0x0ED9, 0x0F20, 0x0F29,
        };

        //
        // [89] Extender ::= ...
        //

        int extenderRange[] = {
            0x3031, 0x3035, 0x309D, 0x309E, 0x30FC, 0x30FE,
        };

        int extenderChar[] = {
            0x00B7, 0x02D0, 0x02D1, 0x0387, 0x0640, 0x0E46, 0x0EC6, 0x3005,
        };

        //
        // SpecialChar ::= '<', '&', '\n', '\r', ']'
        //

        int specialChar[] = {
            '<', '&', '\n', '\r', ']',
        };

        //
        // Initialize
        //

        // set valid characters
        for (int i = 0; i < charRange.length; i += 2) {
            for (int j = charRange[i]; j <= charRange[i + 1]; j++) {
                CHARS[j] |= MASK_VALID | MASK_CONTENT;
            }
        }

        // remove special characters
        for (int i = 0; i < specialChar.length; i++) {
            CHARS[specialChar[i]] = (byte)(CHARS[specialChar[i]] & ~MASK_CONTENT);
        }

        // set space characters
        for (int i = 0; i < spaceChar.length; i++) {
            CHARS[spaceChar[i]] |= MASK_SPACE;
        }

        // set name start characters
        for (int i = 0; i < nameStartChar.length; i++) {
            CHARS[nameStartChar[i]] |= MASK_NAME_START | MASK_NAME | 
                                       MASK_NCNAME_START | MASK_NCNAME;
        }
        for (int i = 0; i < letterRange.length; i += 2) {
            for (int j = letterRange[i]; j <= letterRange[i + 1]; j++) {
                CHARS[j] |= MASK_NAME_START | MASK_NAME |
                            MASK_NCNAME_START | MASK_NCNAME;
            }
        }
        for (int i = 0; i < letterChar.length; i++) {
            CHARS[letterChar[i]] |= MASK_NAME_START | MASK_NAME |
                                    MASK_NCNAME_START | MASK_NCNAME;
        }

        // set name characters
        for (int i = 0; i < nameChar.length; i++) {
            CHARS[nameChar[i]] |= MASK_NAME | MASK_NCNAME;
        }
        for (int i = 0; i < digitRange.length; i += 2) {
            for (int j = digitRange[i]; j <= digitRange[i + 1]; j++) {
                CHARS[j] |= MASK_NAME | MASK_NCNAME;
            }
        }
        for (int i = 0; i < combiningCharRange.length; i += 2) {
            for (int j = combiningCharRange[i]; j <= combiningCharRange[i + 1]; j++) {
                CHARS[j] |= MASK_NAME | MASK_NCNAME;
            }
        }
        for (int i = 0; i < combiningCharChar.length; i++) {
            CHARS[combiningCharChar[i]] |= MASK_NAME | MASK_NCNAME;
        }
        for (int i = 0; i < extenderRange.length; i += 2) {
            for (int j = extenderRange[i]; j <= extenderRange[i + 1]; j++) {
                CHARS[j] |= MASK_NAME | MASK_NCNAME;
            }
        }
        for (int i = 0; i < extenderChar.length; i++) {
            CHARS[extenderChar[i]] |= MASK_NAME | MASK_NCNAME;
        }

        // remove ':' from allowable MASK_NCNAME_START and MASK_NCNAME chars
        CHARS[':'] &= ~(MASK_NCNAME_START | MASK_NCNAME);

        // set Pubid characters
        for (int i = 0; i < pubidChar.length; i++) {
            CHARS[pubidChar[i]] |= MASK_PUBID;
        }
        for (int i = 0; i < pubidRange.length; i += 2) {
            for (int j = pubidRange[i]; j <= pubidRange[i + 1]; j++) {
                CHARS[j] |= MASK_PUBID;
            }
        }

    } // <clinit>()

    //
    // Public static methods
    //

    /*
     * Returns true if the specified character is a supplemental character.
     *
     * @param c The character to check.
     */
    public static boolean isSupplemental(int c) {
        return (c >= 0x10000 && c <= 0x10FFFF);
    }

    /*
     * Returns true the supplemental character corresponding to the given
     * surrogates.
     *
     * @param h The high surrogate.
     * @param l The low surrogate.
     */
    public static int supplemental(char h, char l) {
        return (h - 0xD800) * 0x400 + (l - 0xDC00) + 0x10000;
    }

    /*
     * Returns the high surrogate of a supplemental character
     *
     * @param c The supplemental character to "split".
     */
    public static char highSurrogate(int c) {
        return (char) (((c - 0x00010000) >> 10) + 0xD800);
    }

    /*
     * Returns the low surrogate of a supplemental character
     *
     * @param c The supplemental character to "split".
     */
    public static char lowSurrogate(int c) {
        return (char) (((c - 0x00010000) & 0x3FF) + 0xDC00);
    }

    /*
     * Returns whether the given character is a high surrogate
     *
     * @param c The character to check.
     */
    public static boolean isHighSurrogate(int c) {
        return (0xD800 <= c && c <= 0xDBFF);
    }

    /*
     * Returns whether the given character is a low surrogate
     *
     * @param c The character to check.
     */
    public static boolean isLowSurrogate(int c) {
        return (0xDC00 <= c && c <= 0xDFFF);
    }


    /*
     * Returns true if the specified character is valid. This method
     * also checks the surrogate character range from 0x10000 to 0x10FFFF.
     * <p>
     * If the program chooses to apply the mask directly to the
     * <code>CHARS</code> array, then they are responsible for checking
     * the surrogate character range.
     *
     * @param c The character to check.
     */
    public static boolean isValid(int c) {
        return (c < 0x10000 && (CHARS[c] & MASK_VALID) != 0) ||
               (0x10000 <= c && c <= 0x10FFFF);
    } // isValid(int):boolean

    /*
     * Returns true if the specified character is invalid.
     *
     * @param c The character to check.
     */
    public static boolean isInvalid(int c) {
        return !isValid(c);
    } // isInvalid(int):boolean

    /*
     * Returns true if the specified character can be considered content.
     *
     * @param c The character to check.
     */
    public static boolean isContent(int c) {
        return (c < 0x10000 && (CHARS[c] & MASK_CONTENT) != 0) ||
               (0x10000 <= c && c <= 0x10FFFF);
    } // isContent(int):boolean

    /*
     * Returns true if the specified character can be considered markup.
     * Markup characters include '&lt;', '&amp;', and '%'.
     *
     * @param c The character to check.
     */
    public static boolean isMarkup(int c) {
        return c == '<' || c == '&' || c == '%';
    } // isMarkup(int):boolean

    /*
     * Returns true if the specified character is a space character
     * as defined by production [3] in the XML 1.0 specification.
     *
     * @param c The character to check.
     */
    public static boolean isSpace(int c) {
        return c < 0x10000 && (CHARS[c] & MASK_SPACE) != 0;
    } // isSpace(int):boolean

    /*
     * Returns true if the specified character is a valid name start
     * character as defined by production [5] in the XML 1.0
     * specification.
     *
     * @param c The character to check.
     */
    public static boolean isNameStart(int c) {
        return c < 0x10000 && (CHARS[c] & MASK_NAME_START) != 0;
    } // isNameStart(int):boolean

    /*
     * Returns true if the specified character is a valid name
     * character as defined by production [4] in the XML 1.0
     * specification.
     *
     * @param c The character to check.
     */
    public static boolean isName(int c) {
        return c < 0x10000 && (CHARS[c] & MASK_NAME) != 0;
    } // isName(int):boolean

    /*
     * Returns true if the specified character is a valid NCName start
     * character as defined by production [4] in Namespaces in XML
     * recommendation.
     *
     * @param c The character to check.
     */
    public static boolean isNCNameStart(int c) {
        return c < 0x10000 && (CHARS[c] & MASK_NCNAME_START) != 0;
    } // isNCNameStart(int):boolean

    /*
     * Returns true if the specified character is a valid NCName
     * character as defined by production [5] in Namespaces in XML
     * recommendation.
     *
     * @param c The character to check.
     */
    public static boolean isNCName(int c) {
        return c < 0x10000 && (CHARS[c] & MASK_NCNAME) != 0;
    } // isNCName(int):boolean

    /*
     * Returns true if the specified character is a valid Pubid
     * character as defined by production [13] in the XML 1.0
     * specification.
     *
     * @param c The character to check.
     */
    public static boolean isPubid(int c) {
        return c < 0x10000 && (CHARS[c] & MASK_PUBID) != 0;
    } // isPubid(int):boolean

    /*
     * [5] Name ::= (Letter | '_' | ':') (NameChar)*
     */
    /*
     * Check to see if a string is a valid Name according to [5]
     * in the XML 1.0 Recommendation
     *
     * @param name string to check
     * @return true if name is a valid Name
     */
    public static boolean isValidName(String name) {
        if (name.length() == 0)
            return false;
        char ch = name.charAt(0);
        if( isNameStart(ch) == false)
           return false;
        for (int i = 1; i < name.length(); i++ ) {
           ch = name.charAt(i);
           if( isName( ch ) == false ){
              return false;
           }
        }
        return true;
    } // isValidName(String):boolean
    

    /*
     * from the namespace rec
     * [4] NCName ::= (Letter | '_') (NCNameChar)*
     */
    /*
     * Check to see if a string is a valid NCName according to [4]
     * from the XML Namespaces 1.0 Recommendation
     *
     * @param name string to check
     * @return true if name is a valid NCName
     */
    public static boolean isValidNCName(String ncName) {
        if (ncName.length() == 0)
            return false;
        char ch = ncName.charAt(0);
        if( isNCNameStart(ch) == false)
           return false;
        for (int i = 1; i < ncName.length(); i++ ) {
           ch = ncName.charAt(i);
           if( isNCName( ch ) == false ){
              return false;
           }
        }
        return true;
    } // isValidNCName(String):boolean

    /*
     * [7] Nmtoken ::= (NameChar)+
     */
    /*
     * Check to see if a string is a valid Nmtoken according to [7]
     * in the XML 1.0 Recommendation
     *
     * @param nmtoken string to check
     * @return true if nmtoken is a valid Nmtoken 
     */
    public static boolean isValidNmtoken(String nmtoken) {
        if (nmtoken.length() == 0)
            return false;
        for (int i = 0; i < nmtoken.length(); i++ ) {
           char ch = nmtoken.charAt(i);
           if(  ! isName( ch ) ){
              return false;
           }
        }
        return true;
    } // isValidName(String):boolean





    // encodings

    /*
     * Returns true if the encoding name is a valid IANA encoding.
     * This method does not verify that there is a decoder available
     * for this encoding, only that the characters are valid for an
     * IANA encoding name.
     *
     * @param ianaEncoding The IANA encoding name.
     */
    public static boolean isValidIANAEncoding(String ianaEncoding) {
        if (ianaEncoding != null) {
            int length = ianaEncoding.length();
            if (length > 0) {
                char c = ianaEncoding.charAt(0);
                if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                    for (int i = 1; i < length; i++) {
                        c = ianaEncoding.charAt(i);
                        if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') &&
                            (c < '0' || c > '9') && c != '.' && c != '_' &&
                            c != '-') {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    } // isValidIANAEncoding(String):boolean

    /*
     * Returns true if the encoding name is a valid Java encoding.
     * This method does not verify that there is a decoder available
     * for this encoding, only that the characters are valid for an
     * Java encoding name.
     *
     * @param javaEncoding The Java encoding name.
     */
    public static boolean isValidJavaEncoding(String javaEncoding) {
        if (javaEncoding != null) {
            int length = javaEncoding.length();
            if (length > 0) {
                for (int i = 1; i < length; i++) {
                    char c = javaEncoding.charAt(i);
                    if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') &&
                        (c < '0' || c > '9') && c != '.' && c != '_' &&
                        c != '-') {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    } // isValidIANAEncoding(String):boolean

} // class XMLChar

/*
 * EncodingMap is a convenience class which handles conversions between 
 * IANA encoding names and Java encoding names, and vice versa. The
 * encoding names used in XML instance documents <strong>must</strong>
 * be the IANA encoding names specified or one of the aliases for those names
 * which IANA defines.
 * <p>
 * <TABLE BORDER="0" WIDTH="100%">
 *  <TR>
 *      <TD WIDTH="33%">
 *          <P ALIGN="CENTER"><B>Common Name</B>
 *      </TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER"><B>Use this name in XML files</B>
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER"><B>Name Type</B>
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER"><B>Xerces converts to this Java Encoder Name</B>
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">8 bit Unicode</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">UTF-8
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">UTF8
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">ISO Latin 1</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ISO-8859-1
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">ISO-8859-1
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">ISO Latin 2</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ISO-8859-2
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">ISO-8859-2
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">ISO Latin 3</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ISO-8859-3
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">ISO-8859-3
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">ISO Latin 4</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ISO-8859-4
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">ISO-8859-4
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">ISO Latin Cyrillic</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ISO-8859-5
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">ISO-8859-5
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">ISO Latin Arabic</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ISO-8859-6
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">ISO-8859-6
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">ISO Latin Greek</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ISO-8859-7
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">ISO-8859-7
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">ISO Latin Hebrew</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ISO-8859-8
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">ISO-8859-8
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">ISO Latin 5</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ISO-8859-9
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">ISO-8859-9
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: US</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-us
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp037
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Canada</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-ca
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp037
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Netherlands</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-nl
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp037
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Denmark</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-dk
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp277
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Norway</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-no
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp277
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Finland</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-fi
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp278
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Sweden</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-se
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp278
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Italy</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-it
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp280
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Spain, Latin America</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-es
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp284
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Great Britain</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-gb
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp285
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: France</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-fr
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp297
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Arabic</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-ar1
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp420
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Hebrew</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-he
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp424
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Switzerland</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-ch
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp500
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Roece</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-roece
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp870
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Yugoslavia</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-yu
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp870
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Iceland</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-is
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp871
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">EBCDIC: Urdu</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">ebcdic-cp-ar2
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">IANA
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">cp918
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">Chinese for PRC, mixed 1/2 byte</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">gb2312
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">GB2312
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">Extended Unix Code, packed for Japanese</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">euc-jp
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">eucjis
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">Japanese: iso-2022-jp</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">iso-2020-jp
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">JIS
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">Japanese: Shift JIS</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">Shift_JIS
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">SJIS
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">Chinese: Big5</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">Big5
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">Big5
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">Extended Unix Code, packed for Korean</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">euc-kr
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">iso2022kr
 *      </TD>
 *  </TR>
 *  <TR>
 *      <TD WIDTH="33%">Cyrillic</TD>
 *      <TD WIDTH="15%">
 *          <P ALIGN="CENTER">koi8-r
 *      </TD>
 *      <TD WIDTH="12%">
 *          <P ALIGN="CENTER">MIME
 *      </TD>
 *      <TD WIDTH="31%">
 *          <P ALIGN="CENTER">koi8-r
 *      </TD>
 *  </TR>
 * </TABLE>
 * 
 * @author TAMURA Kent, IBM
 * @author Andy Clark, IBM
 */
public static class EncodingMap {

    //
    // Data
    //

    /** fIANA2JavaMap */
    protected final static Hashtable fIANA2JavaMap = new Hashtable();

    /** fJava2IANAMap */
    protected final static Hashtable fJava2IANAMap = new Hashtable();

    //
    // Static initialization
    //

    static {

        // add IANA to Java encoding mappings.
        fIANA2JavaMap.put("BIG5",            "Big5");
        fIANA2JavaMap.put("CSBIG5",            "Big5");
        fIANA2JavaMap.put("CP037",    "CP037");
        fIANA2JavaMap.put("IBM037",    "CP037");
        fIANA2JavaMap.put("CSIBM037",    "CP037");
        fIANA2JavaMap.put("EBCDIC-CP-US",    "CP037");
        fIANA2JavaMap.put("EBCDIC-CP-CA",    "CP037");
        fIANA2JavaMap.put("EBCDIC-CP-NL",    "CP037");
        fIANA2JavaMap.put("EBCDIC-CP-WT",    "CP037");
        fIANA2JavaMap.put("IBM273",    "CP273");
        fIANA2JavaMap.put("CP273",    "CP273");
        fIANA2JavaMap.put("CSIBM273",    "CP273");
        fIANA2JavaMap.put("IBM277",    "CP277");
        fIANA2JavaMap.put("CP277",    "CP277");
        fIANA2JavaMap.put("CSIBM277",    "CP277");
        fIANA2JavaMap.put("EBCDIC-CP-DK",    "CP277");
        fIANA2JavaMap.put("EBCDIC-CP-NO",    "CP277");
        fIANA2JavaMap.put("IBM278",    "CP278");
        fIANA2JavaMap.put("CP278",    "CP278");
        fIANA2JavaMap.put("CSIBM278",    "CP278");
        fIANA2JavaMap.put("EBCDIC-CP-FI",    "CP278");
        fIANA2JavaMap.put("EBCDIC-CP-SE",    "CP278");
        fIANA2JavaMap.put("IBM280",    "CP280");
        fIANA2JavaMap.put("CP280",    "CP280");
        fIANA2JavaMap.put("CSIBM280",    "CP280");
        fIANA2JavaMap.put("EBCDIC-CP-IT",    "CP280");
        fIANA2JavaMap.put("IBM284",    "CP284");
        fIANA2JavaMap.put("CP284",    "CP284");
        fIANA2JavaMap.put("CSIBM284",    "CP284");
        fIANA2JavaMap.put("EBCDIC-CP-ES",    "CP284");
        fIANA2JavaMap.put("EBCDIC-CP-GB",    "CP285");
        fIANA2JavaMap.put("IBM285",    "CP285");
        fIANA2JavaMap.put("CP285",    "CP285");
        fIANA2JavaMap.put("CSIBM285",    "CP285");
        fIANA2JavaMap.put("EBCDIC-JP-KANA",    "CP290");
        fIANA2JavaMap.put("IBM290",    "CP290");
        fIANA2JavaMap.put("CP290",    "CP290");
        fIANA2JavaMap.put("CSIBM290",    "CP290");
        fIANA2JavaMap.put("EBCDIC-CP-FR",    "CP297");
        fIANA2JavaMap.put("IBM297",    "CP297");
        fIANA2JavaMap.put("CP297",    "CP297");
        fIANA2JavaMap.put("CSIBM297",    "CP297");
        fIANA2JavaMap.put("EBCDIC-CP-AR1",   "CP420");
        fIANA2JavaMap.put("IBM420",    "CP420");
        fIANA2JavaMap.put("CP420",    "CP420");
        fIANA2JavaMap.put("CSIBM420",    "CP420");
        fIANA2JavaMap.put("EBCDIC-CP-HE",    "CP424");
        fIANA2JavaMap.put("IBM424",    "CP424");
        fIANA2JavaMap.put("CP424",    "CP424");
        fIANA2JavaMap.put("CSIBM424",    "CP424");
        fIANA2JavaMap.put("IBM437",    "CP437");
        fIANA2JavaMap.put("437",    "CP437");
        fIANA2JavaMap.put("CP437",    "CP437");
        fIANA2JavaMap.put("CSPC8CODEPAGE437",    "CP437");
        fIANA2JavaMap.put("EBCDIC-CP-CH",    "CP500");
        fIANA2JavaMap.put("IBM500",    "CP500");
        fIANA2JavaMap.put("CP500",    "CP500");
        fIANA2JavaMap.put("CSIBM500",    "CP500");
        fIANA2JavaMap.put("EBCDIC-CP-CH",    "CP500");
        fIANA2JavaMap.put("EBCDIC-CP-BE",    "CP500"); 
        fIANA2JavaMap.put("IBM775",    "CP775");
        fIANA2JavaMap.put("CP775",    "CP775");
        fIANA2JavaMap.put("CSPC775BALTIC",    "CP775");
        fIANA2JavaMap.put("IBM850",    "CP850");
        fIANA2JavaMap.put("850",    "CP850");
        fIANA2JavaMap.put("CP850",    "CP850");
        fIANA2JavaMap.put("CSPC850MULTILINGUAL",    "CP850");
        fIANA2JavaMap.put("IBM852",    "CP852");
        fIANA2JavaMap.put("852",    "CP852");
        fIANA2JavaMap.put("CP852",    "CP852");
        fIANA2JavaMap.put("CSPCP852",    "CP852");
        fIANA2JavaMap.put("IBM855",    "CP855");
        fIANA2JavaMap.put("855",    "CP855");
        fIANA2JavaMap.put("CP855",    "CP855");
        fIANA2JavaMap.put("CSIBM855",    "CP855");
        fIANA2JavaMap.put("IBM857",    "CP857");
        fIANA2JavaMap.put("857",    "CP857");
        fIANA2JavaMap.put("CP857",    "CP857");
        fIANA2JavaMap.put("CSIBM857",    "CP857");
        fIANA2JavaMap.put("IBM00858",    "CP858");
        fIANA2JavaMap.put("CP00858",    "CP858");
        fIANA2JavaMap.put("CCSID00858",    "CP858");
        fIANA2JavaMap.put("IBM860",    "CP860");
        fIANA2JavaMap.put("860",    "CP860");
        fIANA2JavaMap.put("CP860",    "CP860");
        fIANA2JavaMap.put("CSIBM860",    "CP860");
        fIANA2JavaMap.put("IBM861",    "CP861");
        fIANA2JavaMap.put("861",    "CP861");
        fIANA2JavaMap.put("CP861",    "CP861");
        fIANA2JavaMap.put("CP-IS",    "CP861");
        fIANA2JavaMap.put("CSIBM861",    "CP861");
        fIANA2JavaMap.put("IBM862",    "CP862");
        fIANA2JavaMap.put("862",    "CP862");
        fIANA2JavaMap.put("CP862",    "CP862");
        fIANA2JavaMap.put("CSPC862LATINHEBREW",    "CP862");
        fIANA2JavaMap.put("IBM863",    "CP863");
        fIANA2JavaMap.put("863",    "CP863");
        fIANA2JavaMap.put("CP863",    "CP863");
        fIANA2JavaMap.put("CSIBM863",    "CP863");
        fIANA2JavaMap.put("IBM864",    "CP864");
        fIANA2JavaMap.put("CP864",    "CP864");
        fIANA2JavaMap.put("CSIBM864",    "CP864");
        fIANA2JavaMap.put("IBM865",    "CP865");
        fIANA2JavaMap.put("865",    "CP865");
        fIANA2JavaMap.put("CP865",    "CP865");
        fIANA2JavaMap.put("CSIBM865",    "CP865");
        fIANA2JavaMap.put("IBM866",    "CP866");
        fIANA2JavaMap.put("866",    "CP866");
        fIANA2JavaMap.put("CP866",    "CP866");
        fIANA2JavaMap.put("CSIBM866",    "CP866");
        fIANA2JavaMap.put("IBM868",    "CP868");
        fIANA2JavaMap.put("CP868",    "CP868");
        fIANA2JavaMap.put("CSIBM868",    "CP868");
        fIANA2JavaMap.put("CP-AR",        "CP868");
        fIANA2JavaMap.put("IBM869",    "CP869");
        fIANA2JavaMap.put("CP869",    "CP869");
        fIANA2JavaMap.put("CSIBM869",    "CP869");
        fIANA2JavaMap.put("CP-GR",        "CP869");
        fIANA2JavaMap.put("IBM870",    "CP870");
        fIANA2JavaMap.put("CP870",    "CP870");
        fIANA2JavaMap.put("CSIBM870",    "CP870");
        fIANA2JavaMap.put("EBCDIC-CP-ROECE", "CP870");
        fIANA2JavaMap.put("EBCDIC-CP-YU",    "CP870");
        fIANA2JavaMap.put("IBM871",    "CP871");
        fIANA2JavaMap.put("CP871",    "CP871");
        fIANA2JavaMap.put("CSIBM871",    "CP871");
        fIANA2JavaMap.put("EBCDIC-CP-IS",    "CP871");
        fIANA2JavaMap.put("IBM918",    "CP918");
        fIANA2JavaMap.put("CP918",    "CP918");
        fIANA2JavaMap.put("CSIBM918",    "CP918");
        fIANA2JavaMap.put("EBCDIC-CP-AR2",   "CP918");
        fIANA2JavaMap.put("IBM00924",    "CP924");
        fIANA2JavaMap.put("CP00924",    "CP924");
        fIANA2JavaMap.put("CCSID00924",    "CP924");
        // is this an error???
        fIANA2JavaMap.put("EBCDIC-LATIN9--EURO",    "CP924");
        fIANA2JavaMap.put("IBM1026",    "CP1026");
        fIANA2JavaMap.put("CP1026",    "CP1026");
        fIANA2JavaMap.put("CSIBM1026",    "CP1026");
        fIANA2JavaMap.put("IBM01140",    "Cp1140");
        fIANA2JavaMap.put("CP01140",    "Cp1140");
        fIANA2JavaMap.put("CCSID01140",    "Cp1140");
        fIANA2JavaMap.put("IBM01141",    "Cp1141");
        fIANA2JavaMap.put("CP01141",    "Cp1141");
        fIANA2JavaMap.put("CCSID01141",    "Cp1141");
        fIANA2JavaMap.put("IBM01142",    "Cp1142");
        fIANA2JavaMap.put("CP01142",    "Cp1142");
        fIANA2JavaMap.put("CCSID01142",    "Cp1142");
        fIANA2JavaMap.put("IBM01143",    "Cp1143");
        fIANA2JavaMap.put("CP01143",    "Cp1143");
        fIANA2JavaMap.put("CCSID01143",    "Cp1143");
        fIANA2JavaMap.put("IBM01144",    "Cp1144");
        fIANA2JavaMap.put("CP01144",    "Cp1144");
        fIANA2JavaMap.put("CCSID01144",    "Cp1144");
        fIANA2JavaMap.put("IBM01145",    "Cp1145");
        fIANA2JavaMap.put("CP01145",    "Cp1145");
        fIANA2JavaMap.put("CCSID01145",    "Cp1145");
        fIANA2JavaMap.put("IBM01146",    "Cp1146");
        fIANA2JavaMap.put("CP01146",    "Cp1146");
        fIANA2JavaMap.put("CCSID01146",    "Cp1146");
        fIANA2JavaMap.put("IBM01147",    "Cp1147");
        fIANA2JavaMap.put("CP01147",    "Cp1147");
        fIANA2JavaMap.put("CCSID01147",    "Cp1147");
        fIANA2JavaMap.put("IBM01148",    "Cp1148");
        fIANA2JavaMap.put("CP01148",    "Cp1148");
        fIANA2JavaMap.put("CCSID01148",    "Cp1148");
        fIANA2JavaMap.put("IBM01149",    "Cp1149");
        fIANA2JavaMap.put("CP01149",    "Cp1149");
        fIANA2JavaMap.put("CCSID01149",    "Cp1149");
        fIANA2JavaMap.put("EUC-JP",          "EUCJIS");
        fIANA2JavaMap.put("CSEUCPKDFMTJAPANESE",          "EUCJIS");
        fIANA2JavaMap.put("EXTENDED_UNIX_CODE_PACKED_FORMAT_FOR_JAPANESE",          "EUCJIS");
        fIANA2JavaMap.put("EUC-KR",          "KSC5601");
        fIANA2JavaMap.put("GB2312",          "GB2312");
        fIANA2JavaMap.put("CSGB2312",          "GB2312");
        fIANA2JavaMap.put("ISO-2022-JP",     "JIS");
        fIANA2JavaMap.put("CSISO2022JP",     "JIS");
        fIANA2JavaMap.put("ISO-2022-KR",     "ISO2022KR");
        fIANA2JavaMap.put("CSISO2022KR",     "ISO2022KR");
        fIANA2JavaMap.put("ISO-2022-CN",     "ISO2022CN");

        fIANA2JavaMap.put("X0201",  "JIS0201");
        fIANA2JavaMap.put("CSISO13JISC6220JP", "JIS0201");
        fIANA2JavaMap.put("X0208",  "JIS0208");
        fIANA2JavaMap.put("ISO-IR-87",  "JIS0208");
        fIANA2JavaMap.put("X0208dbiJIS_X0208-1983",  "JIS0208");
        fIANA2JavaMap.put("CSISO87JISX0208",  "JIS0208");
        fIANA2JavaMap.put("X0212",  "JIS0212");
        fIANA2JavaMap.put("ISO-IR-159",  "JIS0212");
        fIANA2JavaMap.put("CSISO159JISX02121990",  "JIS0212");
        fIANA2JavaMap.put("GB18030",       "GB18030");
        fIANA2JavaMap.put("SHIFT_JIS",       "SJIS");
        fIANA2JavaMap.put("CSSHIFTJIS",       "SJIS");
        fIANA2JavaMap.put("MS_KANJI",       "SJIS");
        fIANA2JavaMap.put("WINDOWS-31J",       "MS932");
        fIANA2JavaMap.put("CSWINDOWS31J",       "MS932");

	    // Add support for Cp1252 and its friends
        fIANA2JavaMap.put("WINDOWS-1250",   "Cp1250");
        fIANA2JavaMap.put("WINDOWS-1251",   "Cp1251");
        fIANA2JavaMap.put("WINDOWS-1252",   "Cp1252");
        fIANA2JavaMap.put("WINDOWS-1253",   "Cp1253");
        fIANA2JavaMap.put("WINDOWS-1254",   "Cp1254");
        fIANA2JavaMap.put("WINDOWS-1255",   "Cp1255");
        fIANA2JavaMap.put("WINDOWS-1256",   "Cp1256");
        fIANA2JavaMap.put("WINDOWS-1257",   "Cp1257");
        fIANA2JavaMap.put("WINDOWS-1258",   "Cp1258");
        fIANA2JavaMap.put("TIS-620",   "TIS620");

        fIANA2JavaMap.put("ISO-8859-1",      "ISO8859_1"); 
        fIANA2JavaMap.put("ISO-IR-100",      "ISO8859_1");
        fIANA2JavaMap.put("ISO_8859-1",      "ISO8859_1");
        fIANA2JavaMap.put("LATIN1",      "ISO8859_1");
        fIANA2JavaMap.put("CSISOLATIN1",      "ISO8859_1");
        fIANA2JavaMap.put("L1",      "ISO8859_1");
        fIANA2JavaMap.put("IBM819",      "ISO8859_1");
        fIANA2JavaMap.put("CP819",      "ISO8859_1");

        fIANA2JavaMap.put("ISO-8859-2",      "ISO8859_2"); 
        fIANA2JavaMap.put("ISO-IR-101",      "ISO8859_2");
        fIANA2JavaMap.put("ISO_8859-2",      "ISO8859_2");
        fIANA2JavaMap.put("LATIN2",      "ISO8859_2");
        fIANA2JavaMap.put("CSISOLATIN2",      "ISO8859_2");
        fIANA2JavaMap.put("L2",      "ISO8859_2");

        fIANA2JavaMap.put("ISO-8859-3",      "ISO8859_3"); 
        fIANA2JavaMap.put("ISO-IR-109",      "ISO8859_3");
        fIANA2JavaMap.put("ISO_8859-3",      "ISO8859_3");
        fIANA2JavaMap.put("LATIN3",      "ISO8859_3");
        fIANA2JavaMap.put("CSISOLATIN3",      "ISO8859_3");
        fIANA2JavaMap.put("L3",      "ISO8859_3");

        fIANA2JavaMap.put("ISO-8859-4",      "ISO8859_4"); 
        fIANA2JavaMap.put("ISO-IR-110",      "ISO8859_4");
        fIANA2JavaMap.put("ISO_8859-4",      "ISO8859_4");
        fIANA2JavaMap.put("LATIN4",      "ISO8859_4");
        fIANA2JavaMap.put("CSISOLATIN4",      "ISO8859_4");
        fIANA2JavaMap.put("L4",      "ISO8859_4");

        fIANA2JavaMap.put("ISO-8859-5",      "ISO8859_5"); 
        fIANA2JavaMap.put("ISO-IR-144",      "ISO8859_5");
        fIANA2JavaMap.put("ISO_8859-5",      "ISO8859_5");
        fIANA2JavaMap.put("CYRILLIC",      "ISO8859_5");
        fIANA2JavaMap.put("CSISOLATINCYRILLIC",      "ISO8859_5");

        fIANA2JavaMap.put("ISO-8859-6",      "ISO8859_6"); 
        fIANA2JavaMap.put("ISO-IR-127",      "ISO8859_6");
        fIANA2JavaMap.put("ISO_8859-6",      "ISO8859_6");
        fIANA2JavaMap.put("ECMA-114",      "ISO8859_6");
        fIANA2JavaMap.put("ASMO-708",      "ISO8859_6");
        fIANA2JavaMap.put("ARABIC",      "ISO8859_6");
        fIANA2JavaMap.put("CSISOLATINARABIC",      "ISO8859_6");

        fIANA2JavaMap.put("ISO-8859-7",      "ISO8859_7"); 
        fIANA2JavaMap.put("ISO-IR-126",      "ISO8859_7");
        fIANA2JavaMap.put("ISO_8859-7",      "ISO8859_7");
        fIANA2JavaMap.put("ELOT_928",      "ISO8859_7");
        fIANA2JavaMap.put("ECMA-118",      "ISO8859_7");
        fIANA2JavaMap.put("GREEK",      "ISO8859_7");
        fIANA2JavaMap.put("CSISOLATINGREEK",      "ISO8859_7");
        fIANA2JavaMap.put("GREEK8",      "ISO8859_7");

        fIANA2JavaMap.put("ISO-8859-8",      "ISO8859_8"); 
        fIANA2JavaMap.put("ISO-8859-8-I",      "ISO8859_8"); // added since this encoding only differs w.r.t. presentation 
        fIANA2JavaMap.put("ISO-IR-138",      "ISO8859_8");
        fIANA2JavaMap.put("ISO_8859-8",      "ISO8859_8");
        fIANA2JavaMap.put("HEBREW",      "ISO8859_8");
        fIANA2JavaMap.put("CSISOLATINHEBREW",      "ISO8859_8");

        fIANA2JavaMap.put("ISO-8859-9",      "ISO8859_9"); 
        fIANA2JavaMap.put("ISO-IR-148",      "ISO8859_9");
        fIANA2JavaMap.put("ISO_8859-9",      "ISO8859_9");
        fIANA2JavaMap.put("LATIN5",      "ISO8859_9");
        fIANA2JavaMap.put("CSISOLATIN5",      "ISO8859_9");
        fIANA2JavaMap.put("L5",      "ISO8859_9");

        fIANA2JavaMap.put("KOI8-R",          "KOI8_R");
        fIANA2JavaMap.put("CSKOI8R",          "KOI8_R");
        fIANA2JavaMap.put("US-ASCII",        "ASCII"); 
        fIANA2JavaMap.put("ISO-IR-6",        "ASCII");
        fIANA2JavaMap.put("ANSI_X3.4-1986",        "ASCII");
        fIANA2JavaMap.put("ISO_646.IRV:1991",        "ASCII");
        fIANA2JavaMap.put("ASCII",        "ASCII");
        fIANA2JavaMap.put("CSASCII",        "ASCII");
        fIANA2JavaMap.put("ISO646-US",        "ASCII");
        fIANA2JavaMap.put("US",        "ASCII");
        fIANA2JavaMap.put("IBM367",        "ASCII");
        fIANA2JavaMap.put("CP367",        "ASCII");
        fIANA2JavaMap.put("UTF-8",           "UTF8");
        fIANA2JavaMap.put("UTF-16",           "Unicode");
        fIANA2JavaMap.put("UTF-16BE",           "UnicodeBig");
        fIANA2JavaMap.put("UTF-16LE",           "UnicodeLittle");

        // support for 1047, as proposed to be added to the 
        // IANA registry in 
        // http://lists.w3.org/Archives/Public/ietf-charset/2002JulSep/0049.html
        fIANA2JavaMap.put("IBM-1047",    "Cp1047");
        fIANA2JavaMap.put("IBM1047",    "Cp1047");
        fIANA2JavaMap.put("CP1047",    "Cp1047");

        // Adding new aliases as proposed in
        // http://lists.w3.org/Archives/Public/ietf-charset/2002JulSep/0058.html
        fIANA2JavaMap.put("IBM-37",    "CP037");
        fIANA2JavaMap.put("IBM-273",    "CP273");
        fIANA2JavaMap.put("IBM-277",    "CP277");
        fIANA2JavaMap.put("IBM-278",    "CP278");
        fIANA2JavaMap.put("IBM-280",    "CP280");
        fIANA2JavaMap.put("IBM-284",    "CP284");
        fIANA2JavaMap.put("IBM-285",    "CP285");
        fIANA2JavaMap.put("IBM-290",    "CP290");
        fIANA2JavaMap.put("IBM-297",    "CP297");
        fIANA2JavaMap.put("IBM-420",    "CP420");
        fIANA2JavaMap.put("IBM-424",    "CP424");
        fIANA2JavaMap.put("IBM-437",    "CP437");
        fIANA2JavaMap.put("IBM-500",    "CP500");
        fIANA2JavaMap.put("IBM-775",    "CP775");
        fIANA2JavaMap.put("IBM-850",    "CP850");
        fIANA2JavaMap.put("IBM-852",    "CP852");
        fIANA2JavaMap.put("IBM-855",    "CP855");
        fIANA2JavaMap.put("IBM-857",    "CP857");
        fIANA2JavaMap.put("IBM-858",    "CP858");
        fIANA2JavaMap.put("IBM-860",    "CP860");
        fIANA2JavaMap.put("IBM-861",    "CP861");
        fIANA2JavaMap.put("IBM-862",    "CP862");
        fIANA2JavaMap.put("IBM-863",    "CP863");
        fIANA2JavaMap.put("IBM-864",    "CP864");
        fIANA2JavaMap.put("IBM-865",    "CP865");
        fIANA2JavaMap.put("IBM-866",    "CP866");
        fIANA2JavaMap.put("IBM-868",    "CP868");
        fIANA2JavaMap.put("IBM-869",    "CP869");
        fIANA2JavaMap.put("IBM-870",    "CP870");
        fIANA2JavaMap.put("IBM-871",    "CP871");
        fIANA2JavaMap.put("IBM-918",    "CP918");
        fIANA2JavaMap.put("IBM-924",    "CP924");
        fIANA2JavaMap.put("IBM-1026",    "CP1026");
        fIANA2JavaMap.put("IBM-1140",    "Cp1140");
        fIANA2JavaMap.put("IBM-1141",    "Cp1141");
        fIANA2JavaMap.put("IBM-1142",    "Cp1142");
        fIANA2JavaMap.put("IBM-1143",    "Cp1143");
        fIANA2JavaMap.put("IBM-1144",    "Cp1144");
        fIANA2JavaMap.put("IBM-1145",    "Cp1145");
        fIANA2JavaMap.put("IBM-1146",    "Cp1146");
        fIANA2JavaMap.put("IBM-1147",    "Cp1147");
        fIANA2JavaMap.put("IBM-1148",    "Cp1148");
        fIANA2JavaMap.put("IBM-1149",    "Cp1149");
        fIANA2JavaMap.put("IBM-819",      "ISO8859_1");
        fIANA2JavaMap.put("IBM-367",        "ASCII");

        // REVISIT:
        //   j:CNS11643 -> EUC-TW?
        //   ISO-2022-CN? ISO-2022-CN-EXT?
                                                
        // add Java to IANA encoding mappings
        //fJava2IANAMap.put("8859_1",    "US-ASCII"); // ?
        fJava2IANAMap.put("ISO8859_1",    "ISO-8859-1");
        fJava2IANAMap.put("ISO8859_2",    "ISO-8859-2");
        fJava2IANAMap.put("ISO8859_3",    "ISO-8859-3");
        fJava2IANAMap.put("ISO8859_4",    "ISO-8859-4");
        fJava2IANAMap.put("ISO8859_5",    "ISO-8859-5");
        fJava2IANAMap.put("ISO8859_6",    "ISO-8859-6");
        fJava2IANAMap.put("ISO8859_7",    "ISO-8859-7");
        fJava2IANAMap.put("ISO8859_8",    "ISO-8859-8");
        fJava2IANAMap.put("ISO8859_9",    "ISO-8859-9");
        fJava2IANAMap.put("Big5",      "BIG5");
        fJava2IANAMap.put("CP037",     "EBCDIC-CP-US");
        fJava2IANAMap.put("CP273",     "IBM273");
        fJava2IANAMap.put("CP277",     "EBCDIC-CP-DK");
        fJava2IANAMap.put("CP278",     "EBCDIC-CP-FI");
        fJava2IANAMap.put("CP280",     "EBCDIC-CP-IT");
        fJava2IANAMap.put("CP284",     "EBCDIC-CP-ES");
        fJava2IANAMap.put("CP285",     "EBCDIC-CP-GB");
        fJava2IANAMap.put("CP290",     "EBCDIC-JP-KANA");
        fJava2IANAMap.put("CP297",     "EBCDIC-CP-FR");
        fJava2IANAMap.put("CP420",     "EBCDIC-CP-AR1");
        fJava2IANAMap.put("CP424",     "EBCDIC-CP-HE");
        fJava2IANAMap.put("CP437",     "IBM437");
        fJava2IANAMap.put("CP500",     "EBCDIC-CP-CH");
        fJava2IANAMap.put("CP775",     "IBM775");
        fJava2IANAMap.put("CP850",     "IBM850");
        fJava2IANAMap.put("CP852",     "IBM852");
        fJava2IANAMap.put("CP855",     "IBM855");
        fJava2IANAMap.put("CP857",     "IBM857");
        fJava2IANAMap.put("CP858",     "IBM00858");
        fJava2IANAMap.put("CP860",     "IBM860");
        fJava2IANAMap.put("CP861",     "IBM861");
        fJava2IANAMap.put("CP862",     "IBM862");
        fJava2IANAMap.put("CP863",     "IBM863");
        fJava2IANAMap.put("CP864",     "IBM864");
        fJava2IANAMap.put("CP865",     "IBM865");
        fJava2IANAMap.put("CP866",     "IBM866");
        fJava2IANAMap.put("CP868",     "IBM868");
        fJava2IANAMap.put("CP869",     "IBM869");
        fJava2IANAMap.put("CP870",     "EBCDIC-CP-ROECE");
        fJava2IANAMap.put("CP871",     "EBCDIC-CP-IS");
        fJava2IANAMap.put("CP918",     "EBCDIC-CP-AR2");
        fJava2IANAMap.put("CP924",     "IBM00924");
        fJava2IANAMap.put("CP1026",     "IBM1026");
        fJava2IANAMap.put("Cp01140",     "IBM01140");
        fJava2IANAMap.put("Cp01141",     "IBM01141");
        fJava2IANAMap.put("Cp01142",     "IBM01142");
        fJava2IANAMap.put("Cp01143",     "IBM01143");
        fJava2IANAMap.put("Cp01144",     "IBM01144");
        fJava2IANAMap.put("Cp01145",     "IBM01145");
        fJava2IANAMap.put("Cp01146",     "IBM01146");
        fJava2IANAMap.put("Cp01147",     "IBM01147");
        fJava2IANAMap.put("Cp01148",     "IBM01148");
        fJava2IANAMap.put("Cp01149",     "IBM01149");
        fJava2IANAMap.put("EUCJIS",    "EUC-JP");
        fJava2IANAMap.put("GB2312",    "GB2312");
        fJava2IANAMap.put("ISO2022KR", "ISO-2022-KR");
        fJava2IANAMap.put("ISO2022CN", "ISO-2022-CN");
        fJava2IANAMap.put("JIS",       "ISO-2022-JP");
        fJava2IANAMap.put("KOI8_R",    "KOI8-R");
        fJava2IANAMap.put("KSC5601",   "EUC-KR");
        fJava2IANAMap.put("GB18030",      "GB18030");
        fJava2IANAMap.put("SJIS",      "SHIFT_JIS");
        fJava2IANAMap.put("MS932",      "WINDOWS-31J");
        fJava2IANAMap.put("UTF8",      "UTF-8");
        fJava2IANAMap.put("Unicode",   "UTF-16");
        fJava2IANAMap.put("UnicodeBig",   "UTF-16BE");
        fJava2IANAMap.put("UnicodeLittle",   "UTF-16LE");
        fJava2IANAMap.put("JIS0201",  "X0201");
        fJava2IANAMap.put("JIS0208",  "X0208");
        fJava2IANAMap.put("JIS0212",  "ISO-IR-159");

        // proposed addition (see above for details):
        fJava2IANAMap.put("CP1047",    "IBM1047");

    } // <clinit>()

    //
    // Constructors
    //

    /** Default constructor. */
    public EncodingMap() {}

    //
    // Public static methods
    //

    /**
     * Adds an IANA to Java encoding name mapping.
     * 
     * @param ianaEncoding The IANA encoding name.
     * @param javaEncoding The Java encoding name.
     */
    public static void putIANA2JavaMapping(String ianaEncoding, 
                                           String javaEncoding) {
        fIANA2JavaMap.put(ianaEncoding, javaEncoding);
    } // putIANA2JavaMapping(String,String)

    /**
     * Returns the Java encoding name for the specified IANA encoding name.
     * 
     * @param ianaEncoding The IANA encoding name.
     */
    public static String getIANA2JavaMapping(String ianaEncoding) {
        return (String)fIANA2JavaMap.get(ianaEncoding);
    } // getIANA2JavaMapping(String):String

    /**
     * Removes an IANA to Java encoding name mapping.
     * 
     * @param ianaEncoding The IANA encoding name.
     */
    public static String removeIANA2JavaMapping(String ianaEncoding) {
        return (String)fIANA2JavaMap.remove(ianaEncoding);
    } // removeIANA2JavaMapping(String):String

    /**
     * Adds a Java to IANA encoding name mapping.
     * 
     * @param javaEncoding The Java encoding name.
     * @param ianaEncoding The IANA encoding name.
     */
    public static void putJava2IANAMapping(String javaEncoding, 
                                           String ianaEncoding) {
        fJava2IANAMap.put(javaEncoding, ianaEncoding);
    } // putJava2IANAMapping(String,String)

    /**
     * Returns the IANA encoding name for the specified Java encoding name.
     * 
     * @param javaEncoding The Java encoding name.
     */
    public static String getJava2IANAMapping(String javaEncoding) {
        return (String)fJava2IANAMap.get(javaEncoding);
    } // getJava2IANAMapping(String):String

    /**
     * Removes a Java to IANA encoding name mapping.
     * 
     * @param javaEncoding The Java encoding name.
     */
    public static String removeJava2IANAMapping(String javaEncoding) {
        return (String)fJava2IANAMap.remove(javaEncoding);
    } // removeJava2IANAMapping

} // class EncodingMap

}
