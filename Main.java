// import java.io.BufferedReader;
// import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.LinkedHashMap;

public class Main {
  public static void main(String[] args) throws IOException{
    Parser parser = new Parser();
    LinkedHashMap<String, Parser.Rule> rules = parser.createRules();
    ArrayList<ArrayList<String>> inputTokens = LexicalAnalyzer.getTokenTypes("input.txt");
    ArrayList<String> inputList = LexicalAnalyzer.getInputList();
    ArrayList<String[]> tokens = LexicalAnalyzer.getInputTokens();
    BufferedWriter bfw = null;

    try {
      bfw =
        new BufferedWriter(
          new FileWriter("C:\\Users\\Raeanne\\Desktop\\parser-indv\\output.txt")
        );

      for (int i = 0; i < inputTokens.size(); i++) {
        int arrLength = inputTokens.get(i).size();

        if(inputTokens.get(i).size() == 1 && inputTokens.get(i).contains("")){
          bfw.newLine();
        }else {
          parser.parse(inputTokens.get(i), rules);
          if(!parser.getOutput().isEmpty()){
            bfw.write(inputList.get(i) + " - ACCEPT");
            bfw.newLine();
          }else {
            if(parser.getFinalPtr() < arrLength){
              bfw.write(inputList.get(i) + " - REJECT." + " Offending token '" + tokens.get(i)[parser.getFinalPtr()] + "'");
              bfw.newLine();
            } else {
              bfw.write(inputList.get(i) + " - REJECT." + " No closing parenthesis or bracket after'" + tokens.get(i)[parser.getFinalPtr()-1] + "'");
              bfw.newLine();
            }
          }
        }
        
        parser.setFinalPtr(0);
        parser.setOutput("");
      }
      
      bfw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
