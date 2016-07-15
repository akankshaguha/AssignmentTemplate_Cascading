package com.bitwise.assignment;

import cascading.pipe.Pipe;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import com.hotels.plunger.Bucket;
import com.hotels.plunger.Data;
import com.hotels.plunger.DataBuilder;
import com.hotels.plunger.Plunger;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;



/**
 * Created by akankshag on 7/15/2016.
 */
public class SubAssemTest  {

    @Test
    public void itShouldPerformTheJoinOfTwoPipes() {

        Plunger plunger = new Plunger();
        //Master_Csv
        Data corpus1 = new DataBuilder(new Fields("acc_no","name","dob","phone_no"

        ))
                .addTuple(1002002051,"abc1","10/1/1991",888501)

                .build();

        //Transaction.txt
        Data corpus2 = new DataBuilder(new Fields("acc_no1","trans_type","trans_amt","trans_date"
        ))
                .addTuple(1002002051,"debit",500,"5/1/2014")

                .build();
       //input pipe

        Pipe inputpipe1 = plunger.newNamedPipe("in1", corpus1);
        Pipe inputpipe2 = plunger.newNamedPipe("in2", corpus2);


        Pipe assemblyToTest = new Join(inputpipe1,inputpipe2);

        //plunger bucket
        Bucket bucket = plunger.newBucket(new Fields("acc_no","name","dob","phone_no",
                "acc_no1","trans_type","trans_amt","trans_date"), assemblyToTest);




        List<Tuple> actual = bucket.result().asTupleList();

        Assert.assertThat( new Tuple(1002002051,"abc1","10/1/1991",888501,
                1002002051,"debit",500,"5/1/2014"

        ), CoreMatchers.is(
                new Tuple(1002002051,"abc1","10/1/1991",888501,
                        1002002051,"debit",500,"5/1/2014"

                )));


    }

}
