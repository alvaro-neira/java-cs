package com.alvaroneira.questions;

/**
 * Determine whether a given string of parentheses (multiple types) is properly nested.
 * Task Score
 * 25%
 * Correctness
 * 66%
 * Performance
 * 0%
 * Task description
 * A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
 *
 * S is empty;
 * S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
 * S has the form "VW" where V and W are properly nested strings.
 * For example, the string "{[()()]}" is properly nested but "([)()]" is not.
 *
 * Write a function:
 *
 * class Solution { public int solution(String S); }
 *
 * that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
 *
 * For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..200,000];
 * string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
 */

import org.junit.Assert;

public class Brackets {
    public int solution(String S) {
        // write your code in Java SE 8
        return rec(S, 0, S.length());
    }

    public int rec(String S, int start, int end) {
        // System.out.println("rec='"+S.substring(start,end)+"'");

        if (end - start < 1) {
            return 1;
        }
        if (end - start == 1) {
            return 0;
        }
        if (end - start == 2) {
            if (S.substring(start, start + 2).compareTo("{}") == 0 || S.substring(start, start + 2).compareTo("()") == 0 || S.substring(start, start + 2).compareTo("[]") == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        if (end - start == 3) {
            return 0;
        }

        int retVal = 0;
        if (S.substring(start, start + 1).compareTo("{") == 0) {
            if (S.substring(end - 1, end).compareTo("}") == 0) {
                retVal = rec(S, start + 1, end - 1);
            }
        } else if (S.substring(start, start + 1).compareTo("[") == 0) {
            if (S.substring(end - 1, end).compareTo("]") == 0) {
                retVal = rec(S, start + 1, end - 1);
            }
        } else if (S.substring(start, start + 1).compareTo("(") == 0) {
            if (S.substring(end - 1, end).compareTo(")") == 0) {
                retVal = rec(S, start + 1, end - 1);
            }
        }

        if (retVal == 1) {
            return 1;
        }

        int pivot = start + 1;
        boolean isValidConcatenation = true;
        for (; pivot < end; pivot++) {
            isValidConcatenation = (rec(S, start, pivot) == 1 && rec(S, pivot, end) == 1);
            if (isValidConcatenation) {
                break;
            }
        }

        return isValidConcatenation ? 1 : 0;
    }

    public static void main(String[] args) {
        Brackets b = new Brackets();
        Assert.assertEquals(1, b.solution("{[()()]}"));
        Assert.assertEquals(0, b.solution("([)()]"));
    }
}
