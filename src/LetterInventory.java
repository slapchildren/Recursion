/*  Student information for assignment:
 *
 *  On <MY> honor, <Gideon Mogaji> (and <NAME2),
 *  this programming assignment is <MY> own work
 *  and <I> have not provided this code to any other student.
 *
 *  Number of slip days used: 0
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
public class LetterInventory {
    private String str;
    private static final int ALPHABET_LENGTH = 26;
    private int size;
    private int[] letterFreq;

    public LetterInventory(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        letterFreq = new int[ALPHABET_LENGTH];
        size = 0;
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if ('a' <= current && current <= 'z') {
                current = Character.toLowerCase(current);
                int currentPos = current - 'a';
                letterFreq[currentPos]++;
                size++;
            }
        }
    }

    public int get(char ch) {
        if (!('a' <= ch && ch <= 'z')) {
            throw new IllegalArgumentException("Invalid character");
        }
        ch = Character.toLowerCase(ch);
        return letterFreq[ch - 'a'];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            char current = (char) ('a' + i);
            int freq = letterFreq[i];
            for (int j = 0; j < freq; j++) {
                string.append(current);
            }
        }
        return string.toString();
    }

    public LetterInventory add(LetterInventory inv) {
        if (inv == null) {
            throw new IllegalArgumentException("LetterInventory cannot be null");
        }
        LetterInventory result = new LetterInventory("");
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            result.letterFreq[i] = this.letterFreq[i] + inv.letterFreq[i];
        }
        result.size = this.size + inv.size;
        return result;
    }

    public LetterInventory subtract(LetterInventory inv) {
        if (inv == null) {
            throw new IllegalArgumentException("LetterInventory cannot be null");
        }
        LetterInventory result = new LetterInventory("");
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            int difference = this.letterFreq[i] - inv.letterFreq[i];
            if (difference < 0) {
                return null;
            }
            result.letterFreq[i] = difference;
        }
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            result.size += result.letterFreq[i];
        }
        return result;
    }

    public boolean equals(Object obj) {
        if(obj == null){
            throw new IllegalArgumentException("Invalid parameter");
        }
        if(this == obj){
            return true;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        LetterInventory other = (LetterInventory) obj;
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            if (this.letterFreq[i] != other.letterFreq[i]) {
                return false;
            }
        }
        return this.size == other.size;
    }
}