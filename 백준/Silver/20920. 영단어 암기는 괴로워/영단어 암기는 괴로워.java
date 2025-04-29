import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Word implements Comparable<Word> {
    String word;
    int count;

    Word(String word, int count) {
        this.word = word;
        this.count = count;
    }


    @Override
    public int compareTo(Word w) {

        if (this.count == w.count) {
            if (this.word.length() == w.word.length()) {
                return this.word.compareTo(w.word);
            }
            return Integer.compare(w.word.length(), this.word.length());
        }
        return Integer.compare(w.count, this.count);
    }
}
public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> note = new HashMap<>();
        Set<Word> finalNote = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            note.put(word, note.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : note.entrySet()) {
            String word = entry.getKey();
            Integer count = entry.getValue();

            if(word.length() < m) continue;
            finalNote.add(new Word(word, count));
        }

        for (Word word : finalNote) {
            sb.append(word.word).append("\n");

        }
        System.out.println(sb);
    }
}