import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mine {
    public static void main(String[] args) throws FileNotFoundException, MineExeption {
        Scanner scan = new Scanner(System.in);
            System.out.println("Введите выражение математической операции с двумя числами, которые могут быть " +
                "арабскими (от 1 до 10 включительно) или римскими (от I до X включительно), но не одновременно.");
            String zapis = scan.nextLine();   scan.close();

            char div = '0'; int sch=0, poz=0; boolean rim1, rim2, simbolOk=true;
            //Общая проверка на корректность символики
            for(int i=0;i<zapis.length();i++){
                switch (zapis.charAt(i)){
                    case 42,43,45,47-> {div=zapis.charAt(i); poz=i; sch++;}
                    case 32,48,49,50,51,52,53,54,55,56,57,73,86,88->{}
                    default-> simbolOk=false;
                }
            }
            //Выброс, если не те символы
            if(!simbolOk){
                throw new MineExeption("Некорректный ввод символов (допустимы: 1...10, +,-,/,*, I...X)");
            }
            //Выброс, если нет оператора или запись некорректна
            if(sch==0|poz==0|poz==zapis.length()-1){
                throw new MineExeption("Cтрока не является математической операцией");
            }
            //Выброс, если несколько операторов
            if(sch>1){
                throw new MineExeption("Формат математической операции " +
                        "не удовлетворяет заданию - несколько операторов (+, -, /, *)");
            }

            String arg1=zapis.substring(0,poz), arg2=zapis.substring(++poz);
            arg1=arg1.trim();arg2=arg2.trim();

            rim1= arg1.codePointAt(0) >= 58;    rim2= arg2.codePointAt(0) >= 58;

            //Выброс, если разные системы исчисления
            if (rim1 && !rim2 || !rim1 && rim2){
                throw new MineExeption("Используются одновременно разные системы счисления");
            }

            if(!rim1 & !rim2) {
                //Выброс, если цифры больше 10
                if (Integer.parseInt(arg1)>10 | Integer.parseInt(arg2)>10){
                    throw new MineExeption("Числа вне диапазона (1...10)");
                }
                switch (div) {
                    case '+'-> System.out.println(Integer.parseInt(arg1) + Integer.parseInt(arg2));
                    case '-'-> System.out.println(Integer.parseInt(arg1) - Integer.parseInt(arg2));
                    case '*'-> System.out.println(Integer.parseInt(arg1) * Integer.parseInt(arg2));
                    case '/'-> System.out.println(Integer.parseInt(arg1) / Integer.parseInt(arg2));
                }
            }else {
                int rezult=0;   Rimskie aa;
                switch(arg1){
                    case"I"     ->  {aa= Rimskie.I;      arg1 = String.valueOf(aa.getKonvertacia());}
                    case"II"    ->  {aa= Rimskie.II;     arg1 = String.valueOf(aa.getKonvertacia());}
                    case"III"   ->  {aa= Rimskie.III;    arg1 = String.valueOf(aa.getKonvertacia());}
                    case"IV"    ->  {aa= Rimskie.IV;     arg1 = String.valueOf(aa.getKonvertacia());}
                    case"V"     ->  {aa= Rimskie.V;      arg1 = String.valueOf(aa.getKonvertacia());}
                    case"VI"    ->  {aa= Rimskie.VI;     arg1 = String.valueOf(aa.getKonvertacia());}
                    case"VII"   ->  {aa= Rimskie.VII;    arg1 = String.valueOf(aa.getKonvertacia());}
                    case"VIII"  ->  {aa= Rimskie.VIII;   arg1 = String.valueOf(aa.getKonvertacia());}
                    case"IX"    ->  {aa= Rimskie.IX;     arg1 = String.valueOf(aa.getKonvertacia());}
                    case"X"     ->  {aa= Rimskie.X;      arg1 = String.valueOf(aa.getKonvertacia());}
                    default -> throw new MineExeption("Числа вне диапазона (I...X)");
                }
                switch(arg2){
                    case"I"     ->  {aa= Rimskie.I;      arg2 = String.valueOf(aa.getKonvertacia());}
                    case"II"    ->  {aa= Rimskie.II;     arg2 = String.valueOf(aa.getKonvertacia());}
                    case"III"   ->  {aa= Rimskie.III;    arg2 = String.valueOf(aa.getKonvertacia());}
                    case"IV"    ->  {aa= Rimskie.IV;     arg2 = String.valueOf(aa.getKonvertacia());}
                    case"V"     ->  {aa= Rimskie.V;      arg2 = String.valueOf(aa.getKonvertacia());}
                    case"VI"    ->  {aa= Rimskie.VI;     arg2 = String.valueOf(aa.getKonvertacia());}
                    case"VII"   ->  {aa= Rimskie.VII;    arg2 = String.valueOf(aa.getKonvertacia());}
                    case"VIII"  ->  {aa= Rimskie.VIII;   arg2 = String.valueOf(aa.getKonvertacia());}
                    case"IX"    ->  {aa= Rimskie.IX;     arg2 = String.valueOf(aa.getKonvertacia());}
                    case"X"     ->  {aa= Rimskie.X;      arg2 = String.valueOf(aa.getKonvertacia());}
                    default -> throw new MineExeption("Числа вне диапазона (I...X)");
                }
                switch (div) {
                    case '+'-> rezult=Integer.parseInt(arg1) + Integer.parseInt(arg2);
                    case '-'-> rezult=Integer.parseInt(arg1) - Integer.parseInt(arg2);
                    case '*'-> rezult=Integer.parseInt(arg1) * Integer.parseInt(arg2);
                    case '/'-> rezult=Integer.parseInt(arg1) / Integer.parseInt(arg2);
                }
                //Выброс, если меньше единицы
                if (rezult == 0){
                    throw new MineExeption("Результат меньше единицы");}
                //Выброс, если меньше нуля
                if (rezult < 0){
                    throw new MineExeption("Результат - отрицательное число");}

                String bb=String.valueOf(rezult), rez="";    String[] zap;
                File file=new File("src/Konvertacia");
                Scanner scanFile = new Scanner(file);

                if (bb.equals("100")){rez="C";}
                if (bb.length()<2){
                    while (scanFile.hasNextLine()){
                        zapis=scanFile.nextLine(); zapis=zapis.trim(); zap=zapis.split("_");
                        if(bb.equals(zap[0])){rez=zap[1];}
                    }
                }
                if (bb.length()==2) {
                    String c1=bb.charAt(0)+"0", c2=bb.substring(1,2), rez1="", rez2="";
                    while (scanFile.hasNextLine()) {
                        zapis = scanFile.nextLine(); zapis = zapis.trim(); zap = zapis.split("_");
                        if (c1.equals(zap[0])) { rez1 = zap[1];}
                        if (c2.equals(zap[0])) { rez2 = zap[1];}
                    }
                    rez=rez1+rez2;
                }
                System.out.println(rez);
                scanFile.close();
            }
    }
}
