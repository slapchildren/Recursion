/*  Student information for assignment:
 *
 *  On <MY> honor, <Gideon Mogaji> (and <NAME2),
 *  this programming assignment is <MY> own work
 *  and <I> have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1: Gideon Mogaji
 *  UTEID: gm34284
 *  email address: gideonmogaji@gmail.com
 *
 *  Student 2:
 *  UTEID:
 *  email address:
 *
 *  Grader name: Gracelynn Ray
 *  Section number: 50720
 */

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.*;

public class AnagramSolver {
    private Map<String, LetterInventory> dictionaryMap;

    /*
     * pre: list != null
     *
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException("Dictionary can't be null");
        }
        dictionaryMap = new HashMap<>();
        for (String s : dictionary) {
            dictionaryMap.put(s, new LetterInventory(s));
        }
    }

    /*
     * Return a list of anagrams that can be formed from s with no more than
     * maxWords, unless maxWords is 0 in which case there is no limit on the
     * number of words in the anagram.
     * pre: maxWords >= 0, s != null, s contains at least one English letter.
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        if (maxWords < 0 || !validString(s)) {
            throw new IllegalArgumentException("Invalid parameters");

        }

        LetterInventory targetWord  = new LetterInventory(s);
        List<String> possibleWords = new ArrayList<>();
        for(String word : dictionaryMap.keySet()){
            // word needs to be same length or shorter than target word
            if(targetWord.subtract(dictionaryMap.get(word)) != null){
                possibleWords.add(word);
            }
        }
        Collections.sort(possibleWords);
        List<List<String>> result = new ArrayList<>();
        findAnagrams(targetWord, possibleWords, new ArrayList<String>(), result, 0, maxWords);
        Collections.sort(result, new AnagramComparator());
        return result;
    }

    /**
     *
     * @param lettersLeft letter left that can be used in the anagram
     * @param possibleWords list of words that are sorted and care candidates
     * @param currentList current list of part of current anagram
     * @param result the result
     * @param index index to start at in possibleWords to not duplicate and keep lexicographical
     *              order
     * @param maxWords the limit on allowed words
     */
    private void findAnagrams(LetterInventory lettersLeft, List<String> possibleWords,
                              List<String> currentList, List<List<String>> result, int index,
                              int maxWords){
        if(lettersLeft == null || possibleWords == null || currentList == null || index < 0){
            throw new IllegalArgumentException("Invalid parameters");
        }
        boolean allAnagramsGenerated = lettersLeft.size() == 0;
        boolean stoppingPoint = (maxWords != 0 && currentList.size() >= maxWords);
        if(allAnagramsGenerated){
            result.add(new ArrayList<>(currentList));
        }
        if(!stoppingPoint){
            for(int i = index; i < possibleWords.size(); i++){
                String currWord = possibleWords.get(i);
                LetterInventory currWordInv = dictionaryMap.get(currWord);
                LetterInventory newLettersLeft = lettersLeft.subtract(currWordInv);
                if(newLettersLeft != null){
                    currentList.add(currWord);
                    findAnagrams(newLettersLeft, possibleWords, currentList, result, i, maxWords);
                    currentList.remove(currentList.size() -1);
                }
            }
        }
    }
    /**
     *
     * @param s string to validate
     * @return true if s is a valid string, meaning contains aat least one english letter and
     * false otherwise
     */
    private boolean validString(String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (Character.isLetter(current)) {
                return true;
            }
        }
        return false;
    }

    /**
     * nested class that implements comparator to sort lists with lists of strings
     * orders by least words then lexicographical order if both lists have same amount of words
     */
    private static class AnagramComparator implements Comparator<List<String>> {

        public int compare(List<String> o1, List<String> o2) {
            if (o1.size() != o2.size()) {
                return o1.size() - o2.size();
            }
            for (int i = 0; i < o1.size(); i++) {
                int result = o1.get(i).compareTo(o2.get(i));
                if (result != 0) {
                    return result;
                }
            }
            return 0;
        }
    }
}