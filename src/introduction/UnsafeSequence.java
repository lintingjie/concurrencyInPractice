package introduction;

/**
 * Created by lintingjie on 2018/8/22.
 */

public class UnsafeSequence {

    private int value;

    public int nextValue(){
        return value++;
    }

    public synchronized int nextValueSync(){
        return value++;
    }
}
