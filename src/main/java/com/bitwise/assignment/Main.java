package com.bitwise.assignment;

import cascading.flow.Flow;
import cascading.flow.FlowDef;
import cascading.flow.local.LocalFlowConnector;
import cascading.pipe.Pipe;
import cascading.property.AppProps;
import cascading.scheme.Scheme;
import cascading.scheme.local.TextDelimited;
import cascading.tap.SinkMode;
import cascading.tap.Tap;
import cascading.tap.hadoop.Lfs;

import java.util.Properties;

/**
 * Created by akankshag on 7/15/2016.
 */
public class Main {
    public static void main(String[] args) {

        //setting path
        String inputpath1= "C://Users//akankshag//IdeaProjects//AssignmentTemplate_Cascading//src//main//resources//Master_CSV.txt";
        String inputpath2= "C://Users//akankshag//IdeaProjects//AssignmentTemplate_Cascading//src//main//resources//Transaction.txt";
        String outputpath= "C://Users//akankshag//IdeaProjects//AssignmentTemplate_Cascading//src//main//resources//Results.txt";


       /* //created fields
        Fields fields1 = new Fields("accno", "name", "dob", "phonenumber");
        Fields fields2 = new Fields("acccno", "Transaction Type", " Transaction Amount", " Transaction Date");
*/
        //scheme
        Scheme sourceScheme1 = new TextDelimited(true, ",");
        Scheme sourceScheme2 = new TextDelimited(true, ",");
        Scheme sinkScheme = new TextDelimited(true, ",");

        //Tap
        Tap sourceTap1 = new Lfs(sourceScheme1,inputpath1 );
        Tap sourceTap2 = new Lfs(sourceScheme2, inputpath2);
        Tap sinkTap = new Lfs(sinkScheme, outputpath, SinkMode.REPLACE);

        //Inputs from two files
        Pipe sourcePipe1 = new Pipe("source1");
        Pipe sourcePipe2 = new Pipe("source2");

        //Join data from two files
        Pipe join = new Pipe("join");
        join = new Join(sourcePipe1, sourcePipe2);

       //properties
        Properties properties = new Properties();
        AppProps.setApplicationJarClass(properties, Main.class);


        //flow
        FlowDef flowDef = FlowDef.flowDef()
                .setName("JoinSubAssembly")
                .addSource(sourcePipe1, sourceTap1).addSource(sourcePipe2, sourceTap2)
                .addTailSink(join, sinkTap);
        Flow flow = new LocalFlowConnector(properties).connect(flowDef);
        flow.complete();
    }
}
