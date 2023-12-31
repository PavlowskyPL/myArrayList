public class MyArrayList<anyType> implements ListInterface<anyType> {
    private Object[] list;        //stores the actual elements
    private int numElements;    //used to keep track of the number of valid elements in the list

    public MyArrayList() {
        list = new Object[10];    //start with a buffer size of 10
        numElements = 0;
    }

    private void doubleCapacity()    //private because this is a helper method that need not be used outside of the class
    {

        Object[] temp = new Object[list.length * 2];
        System.arraycopy(list, 0, temp, 0, list.length);
        list = temp;

    }

    private void cutCapacity()    //private because this is a helper method that need not be used outside of the class
    {
        Object[] temp = new Object[list.length / 2];
        for (int i = 0; i < temp.length; i++)
            temp[i] = list[i];
        list = temp;
    }

    public String toString() {
        String ans = "[";
        // [A, B, C]
        for (int i = 0; i < numElements; i++) {
            ans += list[i];
            if (i < numElements - 1)
                ans += ", ";
        }
        return ans + "]";
    }

    public boolean add(anyType x) {
        if (numElements == list.length)
            doubleCapacity();
        list[numElements] = x;
        numElements++;
        return true;
    }

    public boolean add(int index, anyType x) {
        if (index < 0 || index > numElements)
            throw new IndexOutOfBoundsException();
        if (numElements == list.length)
            doubleCapacity();
        for (int i = numElements; i > index; i--)
            list[i] = list[i - 1];
        list[index] = x;
        numElements++;
        return true;
    }

    public int size() {
        return numElements;
    }

    public int bufferSize() {
        return list.length;
    }

    public anyType set(int index, anyType x) {
        if (index < 0 || index >= numElements)
            throw new IndexOutOfBoundsException();
        anyType temp = (anyType) list[index];
        list[index] = x;
        return temp;
    }

    public anyType get(int index) {
        if (index < 0 || index >= numElements)
            throw new IndexOutOfBoundsException();
        return (anyType) list[index];
    }

    public anyType remove(int index) {
        if (index < 0 || index >= numElements)
            throw new IndexOutOfBoundsException();
        anyType temp = (anyType) list[index];
        for (int i = index; i < numElements - 1; i++)
            list[i] = list[i + 1];
        numElements--;
        if (numElements < list.length / 3)
            cutCapacity();
        return temp;
    }
}
