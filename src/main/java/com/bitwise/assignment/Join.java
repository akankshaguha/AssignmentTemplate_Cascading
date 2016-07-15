package com.bitwise.assignment;

import cascading.pipe.CoGroup;
import cascading.pipe.Pipe;
import cascading.pipe.SubAssembly;
import cascading.pipe.joiner.InnerJoin;
import cascading.tuple.Fields;

/**
 * Created by akankshag on 7/15/2016.
 */
public class Join extends SubAssembly {
    public Join(Pipe sourcePipe1, Pipe sourcePipe2) {
            setPrevious(sourcePipe1,sourcePipe2);

            Pipe join = new CoGroup(sourcePipe1,
                    new Fields("acc_no","name","dob","phone_no"),sourcePipe2,
                    new Fields("acc_no1","trans_type","trans_amt","trans_date"),new InnerJoin());
            setTails(join);
        }
    }

