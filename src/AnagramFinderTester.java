import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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


public class AnagramFinderTester {


    private static final String testCaseFileName = "testCaseAnagrams.txt";
    private static final String dictionaryFileName = "d3.txt";

    /**
     * main method that executes tests.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        // tests on the anagram solver itself
        boolean displayAnagrams = getChoiceToDisplayAnagrams();
        AnagramSolver solver
            = new AnagramSolver(AnagramMain.readWords(dictionaryFileName));
        runAnagramTests(solver, displayAnagrams);
    }

    private static void cs314StudentTestsForLetterInventory() {
        // CS314 Students, delete the above tests when you turn in your assignment
        // CS314 Students add your LetterInventory tests here.

        // constructor and toString();
        LetterInventory LI1 = new LetterInventory("Stanford");
        Object expected = "adfnorst";
        Object actual = LI1.toString();
        showTestResults(expected, actual, 1, "LetterInventory constructor and toString");
        LetterInventory LI2 = new LetterInventory("");
        expected = "";
        actual = LI2.toString();
        showTestResults(expected, actual, 2, "LetterInventory constructor and toString");
        // size()
        LI1 = new LetterInventory("banana");
        expected = 6;
        actual = LI1.size();
        showTestResults(expected, actual, 3, "size()");
        LI2 = new LetterInventory("123@!#$%");
        expected = 0;
        actual = LI2.size();
        showTestResults(expected, actual, 4, "size()");
        // get(char ch)
        LI1 = new LetterInventory("apple");
        expected = 2;
        actual = LI1.get('p');
        showTestResults(expected, actual, 5, "getFreq('p')");
        LI2 = new LetterInventory("hello");
        expected = 0;
        actual = LI2.get('z');
        showTestResults(expected, actual, 6, "getFreq('z')");
        // add(LetterInventory other
        LI1 = new LetterInventory("abc");
        LI2 = new LetterInventory("def");
        LetterInventory result = LI1.add(LI2);
        expected = "abcdef";
        actual = result.toString();
        showTestResults(expected, actual, 7, "add()");
        LetterInventory LI3 = new LetterInventory("aaa");
        LetterInventory LI4 = new LetterInventory("bbb");
        result = LI3.add(LI4);
        expected = "aaabbb";
        actual = result.toString();
        showTestResults(expected, actual, 8, "add()");
        // subtract(LetterInventory other)
        LI1 = new LetterInventory("banana");
        LI2 = new LetterInventory("ana");
        result = LI1.subtract(LI2);
        expected = "abn";
        actual = result.toString();
        showTestResults(expected, actual, 9, "subtract()");
        LI3 = new LetterInventory("apple");
        LI4 = new LetterInventory("pear");
        result = LI3.subtract(LI4);  // "pear" has more 'r' than "apple" has
        expected = null;
        actual = result;
        showTestResults(expected, actual, 10, "subtract()");
        //equals(Object obj)
        LI1 = new LetterInventory("listen");
        LI2 = new LetterInventory("silent");
        expected = true;
        actual = LI1.equals(LI2);
        showTestResults(expected, actual, 11, "equals() 'listen' == 'silent'");

        LI3 = new LetterInventory("test");
        LI4 = new LetterInventory("best");
        expected = false;
        actual = LI3.equals(LI4);
        showTestResults(expected, actual, 12, "equals() 'test' != 'best'");
    }

    private static boolean getChoiceToDisplayAnagrams() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter y or Y to display anagrams during tests: ");
        String response = console.nextLine();
        console.close();
        return response.length() > 0
            && response.toLowerCase().charAt(0) == 'y';
    }

    private static boolean showTestResults(Object expected, Object actual,
                                           int testNum, String featureTested) {

        System.out.println("Test Number " + testNum + " testing "
            + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null)
            || (actual != null && actual.equals(expected));
        if (passed) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("!!! FAILED TEST !!! " + testNum);
        }
        System.out.println();
        return passed;
    }

    /**
     * Method to run tests on Anagram solver itself.
     * pre: the files d3.txt and testCaseAnagrams.txt are in the local directory
     * <p>
     * assumed format for file is
     * <NUM_TESTS>
     * <TEST_NUM>
     * <MAX_WORDS>
     * <PHRASE>
     * <NUMBER OF ANAGRAMS>
     * <ANAGRAMS>
     */
    private static void runAnagramTests(AnagramSolver solver,
                                        boolean displayAnagrams) {

        int solverTestCases = 0;
        int solverTestCasesPassed = 0;
        Stopwatch st = new Stopwatch();
        try {
            Scanner sc = new Scanner(new File(testCaseFileName));
            final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
            System.out.println(NUM_TEST_CASES);
            for (int i = 0; i < NUM_TEST_CASES; i++) {
                // expected results
                TestCase currentTest = new TestCase(sc);
                solverTestCases++;
                st.start();
                // actual results
                List<List<String>> actualAnagrams
                    = solver.getAnagrams(currentTest.phrase, currentTest.maxWords);
                st.stop();
                if (displayAnagrams) {
                    displayAnagrams("actual anagrams", actualAnagrams);
                    displayAnagrams("expected anagrams", currentTest.anagrams);
                }


                if (checkPassOrFailTest(currentTest, actualAnagrams))
                    solverTestCasesPassed++;
                System.out.println("Time to find anagrams: " + st.time());
                /* System.out.println("Number of calls to recursive helper method: " 
                        + NumberFormat.getNumberInstance(Locale.US).format(AnagramSolver.callsCount));*/
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("\nProblem while running test cases on AnagramSolver. Check" +
                " that file testCaseAnagrams.txt is in the correct location.");
            System.out.println(e);
            System.out.println("AnagramSolver test cases run: " + solverTestCases);
            System.out.println("AnagramSolver test cases failed: "
                + (solverTestCases - solverTestCasesPassed));
        }
        System.out.println("\nAnagramSolver test cases run: " + solverTestCases);
        System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
    }


    // print out all of the anagrams in a list of anagram
    private static void displayAnagrams(String type,
                                        List<List<String>> anagrams) {

        System.out.println("Results for " + type);
        System.out.println("num anagrams: " + anagrams.size());
        System.out.println("anagrams: ");
        for (List<String> singleAnagram : anagrams) {
            System.out.println(singleAnagram);
        }
    }


    // determine if the test passed or failed
    private static boolean checkPassOrFailTest(TestCase currentTest,
                                               List<List<String>> actualAnagrams) {

        boolean passed = true;
        System.out.println();
        System.out.println("Test number: " + currentTest.testCaseNumber);
        System.out.println("Phrase: " + currentTest.phrase);
        System.out.println("Word limit: " + currentTest.maxWords);
        System.out.println("Expected Number of Anagrams: "
            + currentTest.anagrams.size());
        if (actualAnagrams.equals(currentTest.anagrams)) {
            System.out.println("Passed Test");
        } else {
            System.out.println("\n!!! FAILED TEST CASE !!!");
            System.out.println("Recall MAXWORDS = 0 means no limit.");
            System.out.println("Expected number of anagrams: "
                + currentTest.anagrams.size());
            System.out.println("Actual number of anagrams:   "
                + actualAnagrams.size());
            if (currentTest.anagrams.size() == actualAnagrams.size()) {
                System.out.println("Sizes the same, "
                    + "but either a difference in anagrams or"
                    + " anagrams not in correct order.");
            }
            System.out.println();
            passed = false;
        }
        return passed;
    }

    // class to handle the parameters for an anagram test 
    // and the expected result
    private static class TestCase {

        private int testCaseNumber;
        private String phrase;
        private int maxWords;
        private List<List<String>> anagrams;

        // pre: sc is positioned at the start of a test case
        private TestCase(Scanner sc) {
            testCaseNumber = Integer.parseInt(sc.nextLine().trim());
            maxWords = Integer.parseInt(sc.nextLine().trim());
            phrase = sc.nextLine().trim();
            anagrams = new ArrayList<>();
            readAndStoreAnagrams(sc);
        }

        // pre: sc is positioned at the start of the resulting anagrams
        // read in the number of anagrams and then for each anagram:
        //  - read in the line
        //  - break the line up into words
        //  - create a new list of Strings for the anagram
        //  - add each word to the anagram
        //  - add the anagram to the list of anagrams
        private void readAndStoreAnagrams(Scanner sc) {
            int numAnagrams = Integer.parseInt(sc.nextLine().trim());
            for (int j = 0; j < numAnagrams; j++) {
                String[] words = sc.nextLine().split("\\s+");
                ArrayList<String> anagram = new ArrayList<>();
                for (String st : words) {
                    anagram.add(st);
                }
                anagrams.add(anagram);
            }
            assert anagrams.size() == numAnagrams
                : "Wrong number of angrams read or expected";
        }
    }
}