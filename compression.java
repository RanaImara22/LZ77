import java.util.ArrayList;
import java.util.List;
class compression {
    public static List<String> compress(String input) {
        List<String> compressedData = new ArrayList<>();
        int inputLength = input.length();
        int currentIndex = 0;

        while (currentIndex < inputLength) {
            int longestMatchPosition = 0;
            int longestMatchLength = 0;

            for (int lookAhead = 1; currentIndex + lookAhead <= inputLength && lookAhead <= Tag.MAX_LENGTH; lookAhead++) {
                String currentSubstring = input.substring(currentIndex, currentIndex + lookAhead);

                int lastOccurrenceIndex = input.lastIndexOf(currentSubstring, currentIndex - 1);

                if (lastOccurrenceIndex == -1) {
                    break;
                }

                int currentMatchPosition = currentIndex  - lastOccurrenceIndex;
                 int currentMatchLength = currentSubstring.length();

                if (currentMatchLength > longestMatchLength) {
                    longestMatchPosition = currentMatchPosition;
                    longestMatchLength = currentMatchLength;
                }

            }


            char nextChar = currentIndex + longestMatchLength < inputLength ?
                    input.charAt(currentIndex + longestMatchLength) : Tag.END_OF_INPUT;

            Tag tag = new Tag(longestMatchPosition, longestMatchLength, nextChar);
            String t=tag.toString();
            compressedData.add(t);
            currentIndex += longestMatchLength + 1;
        }

        return compressedData;
    }
}
