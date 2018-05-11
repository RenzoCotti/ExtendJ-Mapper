import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0){
            System.err.println("Please provide at least an argument");
        } else {
            if(args.length > 3 || args.length < 2){
                System.err.println("Args: [print|missing|percent|source] filename (optional source file)");
                return;
            }

            String mode = args[0];
            String fileName = args[1];

            try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                //hashmap of method signatures and respective BC/PC
                ArrayList<BytecodeInfo> classFileInfo = new ArrayList<>();
                String currentMethod = "invalid_method_name";
                HashMap<Integer, String> currentBC = new HashMap<>();



                for(String line; (line = br.readLine()) != null; ) {

                    if(line.isEmpty()){
                        // start of a new method block
                        line = br.readLine();
                        currentMethod = line.trim();
                    } else if(line.contains("stack=") && line.contains("locals=")){
                        //start of PC
                        boolean openParethesis = false;


                        HashMap<Integer, String> bytecodeInstructions = new HashMap<>();
                        while (true){
                            line = br.readLine();
                            if(line == null || line.trim().equals("LineNumberTable:")){
                                break;
                            } else {
                                line = line.trim();
                            }

                            if(line.contains("{")){
//                                openParethesis = true;
//                            } else if(openParethesis){
                                String temp = "";
                                while(!temp.trim().equals("}")){
                                    temp = br.readLine();
                                }
                                line = br.readLine().trim();

                            }

                            String [] splitLine = line.split(": ");

                            int PC;
                            try{
                                PC = Integer.parseInt(splitLine[0]);
                            } catch (NumberFormatException e){
                                //
                                continue;
                            }

                            try{
                                String bc = splitLine[1];
                                bytecodeInstructions.put(PC, bc);
                            } catch (ArrayIndexOutOfBoundsException ex){

                            }

                        }

                        currentBC = bytecodeInstructions;


                    } else if(line.trim().contains("PositionTable: length")){

                        ArrayList<String> tempHexValues = new ArrayList<>();
                        HashMap<Integer, Position> positions = new HashMap<>();


                        while(true){
                            line = br.readLine().trim();

                            if(line.contains("LocalVariableTable:")){
                                break;
                            }

                            tempHexValues.addAll(Arrays.asList(line.split(" ")));
                        }

                        ArrayList<String> hexValues = new ArrayList<>();

                        //guaranteed even size
                        for(int i = 0; i < tempHexValues.size()-1; i++){
                            String s1 = tempHexValues.get(i);
                            i++;
                            String s2 = tempHexValues.get(i);
                            hexValues.add(s1+s2);
                        }

                        for(int i = 0; i < hexValues.size(); i+=6){
                            Integer PC = Integer.parseInt(hexValues.get(i), 16);
                            Integer startLine = Integer.parseInt(hexValues.get(i+1), 16);
                            Integer startColumn = Integer.parseInt(hexValues.get(i+2), 16);
                            Integer endLine = Integer.parseInt(hexValues.get(i+3), 16);
                            Integer endColumn = Integer.parseInt(hexValues.get(i+4), 16);
                            Integer id = Integer.parseInt(hexValues.get(i+5), 16);
                            Position p = new Position(startLine, startColumn, endLine, endColumn, id);

                            positions.put(PC, p);
                        }

                        classFileInfo.add(new BytecodeInfo(currentMethod, currentBC, positions));
                        currentMethod = "invalid_method_name";
                    }
                }

                int covered = 0;
                int total = 0;
                if(mode.equals("missing")){
                    System.out.println("Bytecode instructions missing:");
                } else if(mode.equals("print")){
                    System.out.println("Information:");
                }
                for(BytecodeInfo bc : classFileInfo){
                    covered += bc.getMatching();
                    total += bc.getTotal();
                    if(mode.equals("missing")){
                        System.out.println();
                        System.out.println();
                        System.out.println(bc.getMethodName());
                        System.out.println("-------------------------");

                        bc.printMissing();
                    } else if(mode.equals("print")){
                        bc.printInfo();
                    }
                }

                if(mode.equals("percent")){
                    double number = (double)covered*100/total;

                    System.out.println("Percentage of coverage: "+
                            Math.round(number*100.0)/100.0+"% ("+covered+"/"+total+")");
                }

                if(mode.equals("source")){
                    String sourceFile = args[2];
                    Scanner reader = new Scanner(System.in);


                    //for each method
                    for(BytecodeInfo bc : classFileInfo){
                        String methodName = bc.getMethodName();
                        HashMap<Integer, Position> pcAndPosition = bc.getPcAndPositions();
                        HashMap<Integer, String> pcAndBC = bc.getPcAndBC();

                        System.out.println("\n\n\n\n");
                        System.out.println(methodName);

                        for(int k : pcAndBC.keySet()){
                            reader.nextLine();

                            System.out.println("-------------------------");
                            System.out.println("PC: "+k+" - "+pcAndBC.get(k).toUpperCase());
                            Position p = pcAndPosition.get(k);
                            if(p == null) {
                                System.out.println("NOT MAPPED");
                                continue;
                            }

                            //we wait for enter

                            try(BufferedReader src = new BufferedReader(new FileReader(sourceFile))){
                                int lineNum = 1;
                                String line = src.readLine();
                                String out = "";

                                while (lineNum != p.getStartLine()){
                                    lineNum++;
                                    line = src.readLine();
                                }
                                if(p.getEndLine() != p.getStartLine()){
                                    out += line.substring(p.getStartColumn()-1, line.length()) + '\n';
                                    while (lineNum != p.getEndLine()){
                                        line = src.readLine();
                                        out += line +'\n';
                                        lineNum++;
                                    }

                                    out += line.substring(0, p.getEndColumn());
                                } else {
                                    out += line.substring(p.getStartColumn()-1, p.getEndColumn());
                                }


                                System.out.println(out);

                            } catch (FileNotFoundException e){
                                System.err.println("Source file not found");
                            }
                            catch (Exception e){
                                for(BytecodeInfo bcode : classFileInfo){
                                    bcode.printInfo();
                                }
                                System.out.println(e);
                            }
                        }



                    }

                }







            } catch (FileNotFoundException e){
                System.err.println("File not found: "+fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
