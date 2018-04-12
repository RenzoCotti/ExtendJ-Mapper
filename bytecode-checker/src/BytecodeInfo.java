import java.util.HashMap;

public class BytecodeInfo {
    private String methodName;
    private HashMap<Integer, String> pcAndBC;
    private HashMap<Integer, Position> pcAndPositions;

    public BytecodeInfo(String methodName, HashMap<Integer, String> pcAndBC, HashMap<Integer, Position> pcAndPositions) {
        this.methodName = methodName;
        this.pcAndBC = pcAndBC;
        this.pcAndPositions = pcAndPositions;
    }

    public String getMethodName() {
        return methodName;
    }

    public HashMap<Integer, String> getPcAndBC() {
        return pcAndBC;
    }

    public HashMap<Integer, Position> getPcAndPositions() {
        return pcAndPositions;
    }

    public void printInfo(){
        System.out.println(getMethodName());
        System.out.println();
        System.out.println("Bytecode instructions: ");
        System.out.println("-------------------------------------");
        for(Integer i : getPcAndBC().keySet()){
            System.out.println("PC: "+i+" - "+getPcAndBC().get(i));
        }
        System.out.println();
        System.out.println("PositionTable: ");
        System.out.println("-------------------------------------");
        for(Integer i : getPcAndPositions().keySet()){
            Position p = getPcAndPositions().get(i);
            System.out.println("PC: "+i+" Start: "+p.getStartLine()+"-"+p.getStartColumn()
                    +" End: "+p.getEndLine()+"-"+p.getEndColumn());
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public int getTotal() {return getPcAndBC().keySet().size();}

    public int getMatching(){
        int count = 0;
        for(Integer i: getPcAndBC().keySet()){
            if(getPcAndPositions().get(i) != null){
                count++;
            }
        }
        return count;
    }

    public void printMissing(){
        for(Integer i: getPcAndBC().keySet()){
            if(getPcAndPositions().get(i) == null){
                System.out.println("PC: "+i+" - "+getPcAndBC().get(i));
            }
        }
    }
}
