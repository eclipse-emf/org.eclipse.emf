/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XMLDuration.java,v 1.4.4.1 2007/09/06 18:24:36 emerks Exp $
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
 * originally based on software copyright (c) 1999-2003, International
 * Business Machines, Inc., http://www.apache.org.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.eclipse.emf.ecore.xml.type.internal;

import org.eclipse.emf.ecore.xml.type.InvalidDatatypeValueException;
import org.eclipse.emf.ecore.xml.type.internal.DataValue.TypeValidator;


/**
 * Representation for the <a href="http://www.w3.org/TR/2001/REC-xmlschema-2-20010502/">W3C XML Schema 1.0</a>
 * duration datatype.
 * 
 * NOTE: this class is for internal use only. 
 * Later this class will be replaced by JAXP 1.3 javax.xml.datatype.Duration class.
 * This class is based on Apache Xerces2 2.6.2 parser implementation of date/time validation.
 */
public final class XMLDuration {

   private final String ERROR_MESSAGE="The 'duration' value is invalid: ";
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
    
    private int hashCode = 0;
    
    final int[] dateValue;
    
    final String valueString;

    public XMLDuration(String value)
    {
      this.dateValue = parse(value);
      valueString = value;
    }
    
    public boolean equals(Object obj)
    {
      if (!(obj instanceof XMLDuration))
        return false;
      int[] odata = ((XMLDuration)obj).dateValue;
      return compareDates(dateValue, odata, true) == 0;
    }
    
    public int hashCode()
    {
      if (hashCode == 0)
      {
        int[] temp = addDuration(dateValue, DATETIMES[0], new int[XMLCalendar.TOTAL_SIZE]);
        for (int i=0;i<XMLCalendar.TOTAL_SIZE;i++)
        {
          hashCode^=temp[i];
        }
      }
      return hashCode;
    }
    
    // the parameters are in compiled form (from getActualValue)
    public static int compare(XMLDuration value1, XMLDuration value2)
    {
      return compareDates(value1.dateValue, value2.dateValue, true);
    }//compare()


    /**
     * Parses, validates and computes normalized version of duration object
     *
     * @param str    The lexical representation of duration object PnYn MnDTnH nMnS
     * @return normalized date representation
     * @exception InvalidDatatypeValueException Invalid lexical representation
     */
    private int[] parse(String str) throws InvalidDatatypeValueException{
        int len = str.length();
        int[] date=new int[XMLCalendar.TOTAL_SIZE];

        int start = 0;
        char c=str.charAt(start++);
        if ( c!='P' && c!='-' ) {
            throw new InvalidDatatypeValueException(ERROR_MESSAGE+str);
        }
        else {
            date[XMLCalendar.utc]=(c=='-')?'-':0;
            if ( c=='-' && str.charAt(start++)!='P' ) {
                throw new InvalidDatatypeValueException(ERROR_MESSAGE+str);
            }
        }

        int negate = 1;
        //negative duration
        if ( date[XMLCalendar.utc]=='-' ) {
            negate = -1;

        }
        //at least one number and designator must be seen after P
        boolean designator = false;

        int endDate = XMLCalendar.indexOf (str, start, len, 'T');
        if ( endDate == -1 ) {
            endDate = len;
        }
        //find 'Y'
        int end = XMLCalendar.indexOf (str, start, endDate, 'Y');
        if ( end!=-1 ) {
            //scan year
            date[XMLCalendar.CY]=negate * XMLCalendar.parseInt(str,start,end);
            start = end+1;
            designator = true;
        }

        end = XMLCalendar.indexOf (str, start, endDate, 'M');
        if ( end!=-1 ) {
            //scan month
            date[XMLCalendar.M]=negate * XMLCalendar.parseInt(str,start,end);
            start = end+1;
            designator = true;
        }

        end = XMLCalendar.indexOf (str, start, endDate, 'D');
        if ( end!=-1 ) {
            //scan day
            date[XMLCalendar.D]=negate * XMLCalendar.parseInt(str,start,end);
            start = end+1;
            designator = true;
        }

        if ( len == endDate && start!=len ) {
            throw new InvalidDatatypeValueException(ERROR_MESSAGE+str);
        }
        if ( len !=endDate ) {

            end = XMLCalendar.indexOf (str, ++start, len, 'H');
            if ( end!=-1 ) {
                //scan hours
                date[XMLCalendar.h]=negate * XMLCalendar.parseInt(str,start,end);
                start=end+1;
                designator = true;
            }

            end = XMLCalendar.indexOf (str, start, len, 'M');
            if ( end!=-1 ) {
                //scan min
                date[XMLCalendar.m]=negate * XMLCalendar.parseInt(str,start,end);
                start=end+1;
                designator = true;
            }

            end = XMLCalendar.indexOf (str, start, len, 'S');
            if ( end!=-1 ) {
                //scan seconds
                int mlsec = XMLCalendar.indexOf (str, start, end, '.');
                if ( mlsec >0 ) {
                    date[XMLCalendar.s]  = negate * XMLCalendar.parseInt (str, start, mlsec);
                    date[XMLCalendar.ms] = negate * XMLCalendar.parseInt (str, mlsec+1, end);
                }
                else {
                    date[XMLCalendar.s]=negate * XMLCalendar.parseInt(str, start,end);
                }
                start=end+1;
                designator = true;
            }
            // no additional data shouls appear after last item
            // P1Y1M1DT is illigal value as well
            if ( start != len || str.charAt(--start)=='T' ) {
                throw new InvalidDatatypeValueException(ERROR_MESSAGE+str);
            }
        }

        if ( !designator ) {
            throw new InvalidDatatypeValueException(ERROR_MESSAGE+str);
        }

        return date;
    }

    /**
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
    protected  static short compareDates(int[] date1, int[] date2, boolean strict) {

        //REVISIT: this is unoptimazed vs of comparing 2 durations
        //         Algorithm is described in 3.2.6.2 W3C Schema Datatype specs
        //

        //add constA to both durations
        short resultA, resultB= TypeValidator.INDETERMINATE;

        //try and see if the objects are equal
        resultA = XMLCalendar.compareOrder (date1, date2);
        if ( resultA == 0 ) {
            return 0;
        }

        int[][] result = new int[2][XMLCalendar.TOTAL_SIZE];

        //long comparison algorithm is required
        int[] tempA = addDuration (date1, DATETIMES[0], result[0]);
        int[] tempB = addDuration (date2, DATETIMES[0], result[1]);
        resultA =  XMLCalendar.compareOrder(tempA, tempB);
        if ( resultA == TypeValidator.INDETERMINATE ) {
            return TypeValidator.INDETERMINATE;
        }

        tempA = addDuration(date1, DATETIMES[1], result[0]);
        tempB = addDuration(date2, DATETIMES[1], result[1]);
        resultB = XMLCalendar.compareOrder(tempA, tempB);
        resultA = compareResults(resultA, resultB, strict);
        if (resultA == TypeValidator.INDETERMINATE) {
            return TypeValidator.INDETERMINATE;
        }

        tempA = addDuration(date1, DATETIMES[2], result[0]);
        tempB = addDuration(date2, DATETIMES[2], result[1]);
        resultB = XMLCalendar.compareOrder(tempA, tempB);
        resultA = compareResults(resultA, resultB, strict);
        if (resultA == TypeValidator.INDETERMINATE) {
            return TypeValidator.INDETERMINATE;
        }

        tempA = addDuration(date1, DATETIMES[3], result[0]);
        tempB = addDuration(date2, DATETIMES[3], result[1]);
        resultB = XMLCalendar.compareOrder(tempA, tempB);
        resultA = compareResults(resultA, resultB, strict);

        return resultA;
    }

    private static short compareResults(short resultA, short resultB, boolean strict){

      if ( resultB == TypeValidator.INDETERMINATE ) {
            return TypeValidator.INDETERMINATE;
        }
        else if ( resultA!=resultB && strict ) {
            return TypeValidator.INDETERMINATE;
        }
        else if ( resultA!=resultB && !strict ) {
            if ( resultA!=0 && resultB!=0 ) {
                return TypeValidator.INDETERMINATE;
            }
            else {
                return (resultA!=0)?resultA:resultB;
            }
        }
        return resultA;
    }

    private static int[] addDuration(int[] date, int[] addto, int[] duration) {

        //REVISIT: some code could be shared between normalize() and this method,
        //         however is it worth moving it? The structures are different...
        //

        XMLCalendar.resetDateObj(duration);
        //add months (may be modified additionaly below)
        int temp = addto[XMLCalendar.M] + date[XMLCalendar.M];
        duration[XMLCalendar.M] = XMLCalendar.modulo (temp, 1, 13);
        int carry = XMLCalendar.fQuotient (temp, 1, 13);

        //add years (may be modified additionaly below)
        duration[XMLCalendar.CY]=addto[XMLCalendar.CY] + date[XMLCalendar.CY] + carry;
        
        //add milliseconds
        temp = addto[XMLCalendar.ms] + date[XMLCalendar.ms];
        carry = XMLCalendar.fQuotient(temp, 1000);
        duration[XMLCalendar.ms] = XMLCalendar.mod(temp, 1000, carry);


        //add seconds
        temp = addto[XMLCalendar.s] + date[XMLCalendar.s];
        carry = XMLCalendar.fQuotient (temp, 60);
        duration[XMLCalendar.s] =  XMLCalendar.mod(temp, 60, carry);

        //add minutes
        temp = addto[XMLCalendar.m] +date[XMLCalendar.m] + carry;
        carry = XMLCalendar.fQuotient (temp, 60);
        duration[XMLCalendar.m]= XMLCalendar.mod(temp, 60, carry);

        //add hours
        temp = addto[XMLCalendar.h] + date[XMLCalendar.h] + carry;
        carry = XMLCalendar.fQuotient(temp, 24);
        duration[XMLCalendar.h] = XMLCalendar.mod(temp, 24, carry);


        duration[XMLCalendar.D]=addto[XMLCalendar.D] + date[XMLCalendar.D] + carry;

        while ( true ) {

            temp=XMLCalendar.maxDayInMonthFor(duration[XMLCalendar.CY], duration[XMLCalendar.M]);
            if ( duration[XMLCalendar.D] < 1 ) { //original duration was negative
                duration[XMLCalendar.D] = duration[XMLCalendar.D] + XMLCalendar.maxDayInMonthFor(duration[XMLCalendar.CY], duration[XMLCalendar.M]-1);
                carry=-1;
            }
            else if ( duration[XMLCalendar.D] > temp ) {
                duration[XMLCalendar.D] = duration[XMLCalendar.D] - temp;
                carry=1;
            }
            else {
                break;
            }
            temp = duration[XMLCalendar.M]+carry;
            duration[XMLCalendar.M] = XMLCalendar.modulo(temp, 1, 13);
            duration[XMLCalendar.CY] = duration[XMLCalendar.CY]+XMLCalendar.fQuotient(temp, 1, 13);
        }

        duration[XMLCalendar.utc]='Z';
        return duration;
    }

    public String toString() {
      return valueString;
    }
}
