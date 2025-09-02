package lab3_singlylink;
import java.util.Scanner;
import java.util.Arrays;

public class Get_answer {
    double[] stack, value ;
    char[] opl, pfix ;
    byte top = -1 ;
    boolean IsOpl = false;
    Scanner scan = new Scanner( System.in );
    
    void calValue( char[] Operand, char[] Postfix ){
        pfix = Arrays.copyOf( Postfix, Postfix.length );
        opl = Arrays.copyOf( Operand, Operand.length );
        value = new double[ opl.length ];
        stack = new double[ 4 ] ;
        inputValue();
        execValue();
        System.out.println( "+--------------------------------------+" );
        System.out.printf( "Answer = %.3f #\n", stack[0] );
        System.out.println( "+--------------------------------------+" );
    }//method:
    
    private void inputValue(){
        System.out.println( "Set value:>" );
        for (int i = 0; i < opl.length; i++) {
            System.out.print( " * " + opl[i] + " = " );
            value[i] = scan.nextDouble();
        }//for:
        scan.close();
    }//method:
    
    private void execValue(){
        for (int i = 0; i < pfix.length; i++) {
            System.out.println( "+--------------------------------------+" );
            for (int j = 0; j < opl.length; j++) {
                if( pfix[i] == opl[j] ) {
                    System.out.printf( "Input:> %c value is %.1f\n", opl[j], value[j] );
                    stack[++top] = value[j] ;
                    IsOpl = true ;
                    break ;
                }//if: pfix[i] is operand.
                IsOpl = false ;
            }//forj: Search if pfix[i] is match some value in opl.
            if( IsOpl != true ) System.out.println( "Input:> " + pfix[i] );
            showStack();
            if( IsOpl != true ){
                pop2Cal( pfix[i] );
            }//if: This pfix[i] isn't operand.
            System.out.println("");
        }//fori:
    }//method:
    
    private void pop2Cal( char opt ){
        System.out.printf("\n[Pop]: %.1f\n", stack[top] );
        System.out.printf("[Pop]: %.1f\n", stack[top-1] );
        System.out.printf( "Calculate> %.1f %c %.1f", stack[top-1], opt, stack[top] ) ;
        double result = 0 ;
        switch( opt ) {
            case '+' :
                result = ( stack[top-1] + stack[top] ) ;
                break;
            case '-' : 
                result = ( stack[top-1] - stack[top] ) ;
                break;
            case '*' : 
                result = ( stack[top-1] * stack[top] ) ;
                break;
            case '/' : 
                result = ( stack[top-1] / stack[top] ) ;
                break;
            case '%' : 
                result = ( stack[top-1] % stack[top] ) ;
                break;
            case '^' : 
                result = Math.pow( stack[top-1], stack[top] ) ;
                break;
            default: break ;
        }//switchcase: case operator
        stack[--top] = result ;
        System.out.printf( " = %.1f #\n", result ) ;
        System.out.printf( "[Push]: %.1f\n", result ) ;
        showStack();
    }//method:
    
    private void showStack() {
        System.out.print( "Stack:> " );
        for (int i = 0; i <= top; i++) 
            System.out.printf( "%.1f ", stack[i] );
    }//method:

}//Endclass
