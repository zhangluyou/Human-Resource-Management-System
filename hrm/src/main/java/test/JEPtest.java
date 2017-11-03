package test;

import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

/**
 * Created by dartagnan on 17/9/18.
 */
public class JEPtest {


    public static void main(String[] args) {
        JEP jep = new JEP();
        try {
            jep.addVariable("x", 10);
            jep.addVariable("y",2);
//            jep.addStandardConstants();
//            jep.addStandardFunctions();
            Node node = jep.parse("(x+1)*y");
            Object i = jep.evaluate(node);
            System.err.println(i);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
