import java.util.*;
import java.io.*;
//Look into this: using System.Collections.Generic;
public class ReplaceSemanticNormal
{
    // Replace: Replaces all occurences of a value in a collection
    // target: collection of items to be replaced
    // exclude: item that should not be replaced
    // replacement: value to replace items with
    // result: result
    // length: length
    // index: index
    // replace: replace

    public static int[] Replace(int[] target, int exclude, int replacement)
    {
        int[] result = new int[target.length];
        int length = target.length;
        for (int index = 0; index != length; index++)
        {
            int replace = replacement;
            if (target[index] == exclude)
            {
                replace = target[index];
            }
            target[index] = replace;
        }
        return result;
    }
}