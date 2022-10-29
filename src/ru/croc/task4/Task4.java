package ru.croc.task4;


public class Task4 {
    public static void main(String[] args) {
        String source = """
                               /**
                                * My first ever program in Java!
                                **/
                               class Hello { // class body starts here
                               
                                 /* main method */
                                 public static void main(String[] args/* we put command line arguments here*/) {
                                   // this line prints my first greeting to the screen
                                   System.out.println("Hi!"); // :)
                                 }/**/
                               } // the end
                               // to be continued...
                        """; // test data
        String noComments = removeJavaComments(source);
        System.out.println(noComments);
    }

    static String removeJavaComments(String source){
        source = source.replaceAll("/\\*([\\s\\S]*?)\\*/", ""); //замена блоков
        source = source.replaceAll("//.*", "");     //замена однострочных
      return source;
    }

}
