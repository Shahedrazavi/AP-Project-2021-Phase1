package Classes;

public class IntHolder {
    public Integer value;

    public IntHolder(Integer value){
        this.value = value;
    }

    public void add(int n){
        value = value + n;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
