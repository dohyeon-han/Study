import java.io.IOException;
public class AutoClose {
    public static void main(String[] args) {
    try (
            MyResource11 r1 = new MyResource11();
            MyResource21 r2 = new MyResource21();
            ) 
        {
            System.out.print("T ");
            r1.p();

        } catch (Exception ioe) {
            System.out.print("IOE ");
        } finally {
            System.out.print("F ");
        }
    }
}
class MyResource11 implements AutoCloseable {
    public void p(){
        System.out.println("A");
    }
    public void close() throws IOException {
        System.out.print("1 ");
    }
}
class MyResource21 implements AutoCloseable {
    public void close() throws IOException {
        System.out.print("2 ");
    }
}
 