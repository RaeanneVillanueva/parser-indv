import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class LexicalAnalyzer {

  enum TokenType {
    num,
    add,
    mult,
    bracket_left,
    bracket_right,
    paren_left,
    paren_right,
    error
  }

  static ArrayList<String> inputList = new ArrayList<>();
  static ArrayList<String[]> inputTokens = new ArrayList<>();

	// start state
	private static final int q0 = 0;
  
  private static final int num = 1;
  private static final int add = 2;
  private static final int mult = 3;
  private static final int brack_left = 4;
  private static final int brack_right = 5;
  private static final int paren_left = 6;
  private static final int paren_right = 7;

  private static final int e = 8;

  public int lexicalAnalyzer (int s, char c) {
    switch (s) {
      case q0: switch(c){
        // number
        case '0': return num;
        case '1': return num;
        case '2': return num;
        case '3': return num;
        case '4': return num;
        case '5': return num;
        case '6': return num;
        case '7': return num;
        case '8': return num;
        case '9': return num;

        // symbol
        case '+': return add;
        case '*': return mult;

        // parenthesis left
        case '[': return brack_left;
        case ']': return brack_right;

        // parenthesis
        case '(': return paren_left;
        case ')': return paren_right;

        default: return e;
      }

      case num: switch(c){
        case '0': return num;
        case '1': return num;
        case '2': return num;
        case '3': return num;
        case '4': return num;
        case '5': return num;
        case '6': return num;
        case '7': return num;
        case '8': return num;
        case '9': return num;

        default: return e;
      }

      default: return e;
    }
  }

  public String identifyToken(int val) {
    
    if (val == 1) {
      return TokenType.num.toString();

    } else if (val == 2) {
      return TokenType.add.toString();

    } else if (val == 3) {
      return TokenType.mult.toString();

    }
     else if (val == 4) {
      return TokenType.bracket_left.toString();

    } else if (val == 5){
      return TokenType.bracket_right.toString();

    } else if (val == 6){
      return TokenType.paren_left.toString();

    } else if (val == 7){
        return TokenType.paren_right.toString();
        
    } else if (val == 0){
			return "";
		}
    else {
      return TokenType.error.toString();
    }
  }

  public static ArrayList<String> getInputList() {
    return inputList;
  }

  public static ArrayList<String[]> getInputTokens() {
    return inputTokens;
  }

  public static ArrayList<ArrayList<String>> getTokenTypes(String file) {
    
    LexicalAnalyzer analyzer = new LexicalAnalyzer();
    int state = 0;
    
    ArrayList<ArrayList<String>> tokenTypes = new ArrayList<>();
    BufferedReader bfr = null;

    try {
      bfr =
        new BufferedReader(
          new FileReader("C:\\Users\\Raeanne\\Desktop\\parser-indv\\" + file)
        );

      String line;

      while ((line = bfr.readLine()) != null) {
        
        getInputList().add(line);
        
        line = line.replaceAll("\\(", "\\( ");
        line = line.replaceAll("\\)", " \\)");
        line = line.replaceAll("\\[", "\\[ ");
        line = line.replaceAll("\\]", " \\]");

        String[] tokens = line.split("\\s");
        getInputTokens().add(tokens);
      }

      bfr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    String tokenType = "";

    for (int i = 0; i < getInputTokens().size(); i++) {
      int arrLength = getInputTokens().get(i).length;
      ArrayList<String> typesPerLine = new ArrayList<>();


      for (int j = 0; j < arrLength; j++) {
        String line = getInputTokens().get(i)[j];

        for(char c: line.toCharArray()) {
          state = analyzer.lexicalAnalyzer(state, c);
        }

        tokenType = analyzer.identifyToken(state);
        typesPerLine.add(tokenType);
        state = 0;
      }

      tokenTypes.add(typesPerLine);
    }

    return tokenTypes;
  }
}