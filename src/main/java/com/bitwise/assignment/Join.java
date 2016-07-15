package com.bitwise.assignment;

import cascading.pipe.CoGroup;
import cascading.pipe.Pipe;
import cascading.pipe.SubAssembly;

/**
 * Created by akankshag on 7/15/2016.
 */
public class Join extends SubAssembly {
    public Join(Pipe sourcePipe1, Pipe sourcePipe2) {
            setPrevious(sourcePipe1,sourcePipe2);

            Pipe join = new CoGroup(sourcePipe1,sourcePipe2);
            setTails(join);
        }
    }

