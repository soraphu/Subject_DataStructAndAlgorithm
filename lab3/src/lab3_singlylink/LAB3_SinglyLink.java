package lab3_singlylink;

public class LAB3_SinglyLink {

    public static void main(String[] args) {
        Get_postfix ls = new Get_postfix() ;
        ls.calPostfix("(A+B*C)^D-(A%C+E/B)" );
    }//main
}//class
