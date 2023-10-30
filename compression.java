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


























// Vector<LZ77Tag> v = new Vector<>();
//         int curr = 0;

//         while (curr < data.length()) {
//             int position = 0;
//             int maxLength = 0;
//             char nextSymbol = data.charAt(maxLength + curr);
//             for (int i = 1; i <= WINDOW_SIZE && i <= curr; i++)
//             {
//                 int length = 0;

//                 while (curr + length < data.length() && length < BUFFER_SIZE && curr + length - i < curr
//                         && data.charAt(curr + length) == data.charAt(curr + length - i))
//                 {
//                     length++;
//                 }

//                 if (length > maxLength)
//                 {
//                     position = i;
//                     maxLength = length;
//                     if (curr + length < data.length())
//                         nextSymbol = data.charAt(curr + length);
//                     else
//                         nextSymbol = 0;
//                 }
//             }
//             curr += maxLength + 1;
//             v.add(new LZ77Tag(position, maxLength, nextSymbol));
// //            System.out.println(position + " " + maxLength + " " + nextSymbol);
//         }
//         return  v;
