//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);

            if(openBracket >  closeBracket ||
                openBracket >  openParen ||
                openBracket >  closeParen){ break; }
            
            if(openBracket == -1 || closeParen == -1){
                break;
            }
	    //check if image
	    if(openBracket > 0){
	        if(markdown.substring(openBracket-1,openBracket).equals("!")){
		    currentIndex = closeParen + 1;
		    continue;
		}
	    }
            
            if (markdown.substring(openParen + 1, closeParen).length() != 0 &&
                markdown.substring(closeBracket + 1, openParen).length() == 0 &&
                !markdown.substring(openParen + 1, closeParen).contains("\n")
            ){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            
            currentIndex = closeParen + 1;
            
            
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
