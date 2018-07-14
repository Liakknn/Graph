package graph.functions;

import graph.Function;

public class Tan implements Function {
    
    @Override
    public float getValue(float x) {
        return (float) (5 * Math.tan(x / 4 + 7));
    }
    
    @Override
    public String toString() {
        return "y = 5 * tg(x / 4 + 7)";
    }
    
}
