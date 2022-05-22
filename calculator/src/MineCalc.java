import java.io.FileNotFoundException;
import java.util.Scanner;

public class MineCalc {
    public static void main(String[] args) throws MineExeption, FileNotFoundException {

        System.out.println("Введите выражение математической операции с двумя числами, которые могут быть " +
                "арабскими (от 1 до 10 включительно) или римскими (от I до X включительно), но не одновременно.");
            Scanner scan = new Scanner(System.in);
            main.calc(scan.nextLine());

    }
}
