import java.util.ArrayList;
import java.util.List;
public class Decompression {
    public static String decompress(String compressedData) {
        List<Tag> tags = parseCompressedData(compressedData);
        StringBuilder decompressed = new StringBuilder();
        for (Tag tag : tags) {
            //<0,0,A>
            if (tag.getPosition() == 0) {
                decompressed.append(tag.getNextChar());
            //ABCDE|ABL
            //<5,2,L>
            } else {
                //start=0
                int start = decompressed.length() - tag.getPosition();
                //end=2
                int end = start + tag.getLength();

                for (int i = start; i < end; i++) {
                    //ABCDEAB
                    decompressed.append(decompressed.charAt(i));
                }

                if (decompressed.length() > tag.getLength()) {
                    decompressed.append(tag.getNextChar());
                }
            }
        }

        return decompressed.toString();
    }



    private static List<Tag> parseCompressedData(String compressedData) {
        List<Tag> tags = new ArrayList<>();
        String str=compressedData.substring(1,compressedData.length()-1);
        String[]splittedstr = str.split(", ");

        for (String STR : splittedstr) {
            if (STR.startsWith("<") && STR.endsWith(">")) {

                List<String> parts=new ArrayList<>();
                int noComma=0;
                String part="";
                //<22,3,,>
                int i=1;
                while(i<STR.length()-1){
                    if(STR.charAt(i)==','&&noComma<2){
                        parts.add(part);
                        part="";
                        noComma++;
                    }else{
                        part+=STR.charAt(i);               
                    }
                    i++;

                }
                parts.add(part);


                if (parts.size() == 3) {
                    int position = Integer.parseInt(parts.get(0));
                    int length = Integer.parseInt(parts.get(1));
                    char nextChar = parts.get(2).charAt(1);
                    tags.add(new Tag(position, length, nextChar));
                }
            }
        }

        return tags;
    }


}
