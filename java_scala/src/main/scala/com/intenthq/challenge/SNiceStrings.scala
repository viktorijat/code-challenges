package com.intenthq.challenge

object SNiceStrings {

// From http://adventofcode.com/day/5
//  --- Day 5: Doesn't He Have Intern-Elves For This? ---
//
//  Santa needs help figuring out which strings in his text file are naughty or nice.
//
//    A nice string is one with all of the following properties:
//
//    It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
//  It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
//    It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
//    For example:
//
//    ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
//  aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
//    jchzalrnumimnmhp is naughty because it has no double letter.
//    haegwjzuvuyypxyu is naughty because it contains the string xy.
//    dvszwmarrgswjxmb is naughty because it contains only one vowel.
//    How many strings are nice?

  def stringHasThreeVowels(word: String): Boolean = {
    val vowels: List[String] = List("a", "e", "i", "o", "u")
    val found = word.count(letter => vowels.contains(letter.toString))
    found >= 3
  }

  def stringContainsUnacceptedSubStrings(word: String): Boolean = {
    val unacceptableSubStrings: List[String] = List("ab", "cd", "pq", "xy")
    val found = unacceptableSubStrings.count(subString => word.contains(subString))
    found == 0
  }

  def stringHasADoubleLetter(word: String): Boolean = {
    for (i <- 0 until word.length - 1) {
      if (word.charAt(i) == word.charAt(i + 1)) {
        return true
      }
    }
    false
  }

  def isStringNice(str: String): Boolean = {
    val hasVowels = stringHasThreeVowels(str)
    val hasUnacceptableSubString = stringContainsUnacceptedSubStrings(str)
    val hasADoubleLetter = stringHasADoubleLetter(str)
    hasVowels && hasADoubleLetter && hasUnacceptableSubString
  }

  def nice(xs: List[String]): Int = {
    xs.count(word => isStringNice(word))
  }

  def isStringNiceRegex(str: String): Boolean = {
    str.matches("^(.*[aeiou].*[aeiou].*[aeiou]).*$") && str.matches("^((?!(ab|cd|pq|xy)).)*$") && str
      .matches("^.*(.)\\1.*$")
  }

  def niceRegex(xs: List[String]): Int = {
    xs.count(word => isStringNice(word))
  }
}
