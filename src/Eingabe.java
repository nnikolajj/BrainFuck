import javax.xml.transform.Source;
import java.lang.reflect.Array;
import java.util.*;

public class Eingabe {
    private Scanner sc = new Scanner(System.in);
    private String[] letters;
    private List<String> lettersList;
    private final List<Integer> theList = new ArrayList<>();
    private int error = 0;
    private int position = 0;
    private final List<Integer> bfPosition = new ArrayList<>();
    private String input;
    private int inputposition=0;
    private boolean bugStop=false;
    private String memoryPic = "THE FUCKING CHAD LIST:";
    private String text= "";

    public Eingabe() {
        setInput();
        eingabe();

    }

    public void eingabe() {
        theList.add(0);
        while (true) {
            String eingabe = sc.nextLine();
            letters = eingabe.split("");
            lettersList = Arrays.stream(letters).toList();

            for (int i = 0; lettersList.size() > i; i++) {
                switch (lettersList.get(i)) {
                    case "+": {
                        int a = theList.get(position);
                        a++;
                        theList.set(position, a);

                        if (theList.get(position) > 255) {
                            theList.set(position, 0);
                        }

                        break;
                    }
                    case "-": {
                        int a = theList.get(position);
                        a--;
                        theList.set(position, a);

                        if (theList.get(position) < 0) {
                            theList.set(position, 255);
                        }

                        break;
                    }
                    case ".":
                        int intw = theList.get(position);
                        char c = (char) intw;
                        String a = String.valueOf(c);
                        System.out.println(theList.get(position) + a);
                        break;
                    case ",":
                        try {
                            char character = input.charAt(inputposition);
                            theList.set(position, (int) character);
                            inputposition++;
                        }catch (StringIndexOutOfBoundsException e){
                            System.err.println("Du hast nicht genügend eingegeben eingegeben");
                            bugStop=true;
                        }
                        break;
                    case "[":
                        error++;
                        bfPosition.add(i - 1);
                        break;
                    case "]":

                        error--;
                        if (theList.get(position) != 0) {
                            i = bfPosition.get(error);
                        }
                        break;
                    case ">":
                        try {
                            position++;
                            if (theList.get(position) == null) {
                                theList.add(0);
                            }
                        } catch (IndexOutOfBoundsException e) {
                            theList.add(0);
                        }
                        break;
                    case "<":
                        try {
                            position--;
                            if (theList.get(position) == null) {
                                theList.add(0);
                            }
                        } catch (IndexOutOfBoundsException e) {
                            theList.add(0);
                        }
                        break;
                }

                if (error < 0||bugStop) {
                    System.err.println("Eingabe stimmt nicht");
                    System.exit(1);
                }
            }
            inputposition=0;
            for (int j=0; j< theList.size();j++){
               memoryPic+="|"+theList.get(j);
               int i = theList.get(j);
               char c = (char) i;
               text += String.valueOf(c);

            }
            System.out.println(memoryPic+"|");
            System.out.println("Your Text " + text);
        }
    }

    private void setInput(){
        System.out.println("Input: ");
        input = sc.nextLine();
        System.out.println("Brainfuck: ");
    }

}
