package lab3_singlylink;
import java.util.Arrays ;

public class Get_postfix {
    Get_answer val = new Get_answer() ;
    byte Priority, j, k ;
    Stack bottom, top ;
    char Postfix[], Operand[], ch ;
    
    void calPostfix( String Infix ){
        if( Infix == "" ) {
            System.out.println( "Invalid input" );
            return;
        }//if: input empty.
        Postfix = new char[ Infix.length() ] ;//Set Postfix size
        Operand = new char[ Infix.length() - ( Infix.length()/2 ) ] ; //Set Operand size
        System.out.println( "Input:> " + Infix );
        System.out.println( "+-----+------+-------------------------+" );
        System.out.println( "|Infix|Stack|Postfix                   |" );//7-6-30
        System.out.println( "+-----+------+-------------------------+" );
            
        for (int i = 0; i < Infix.length() ; i++) {
            ch = Infix.charAt(i) ;
            System.out.print( "|  "+ ch +"  |" );
            switch ( ch ) { 
                case '*': 
                case '/':
                case '%':
                    Priority = 2 ;
                    handlePopPush();
                    break ;
                case '+': 
                case '-':
                    Priority = 1 ;
                    handlePopPush();
                    break ;
                case '^': 
                case '(': 
                    Priority = 4 ;
                    if( ch == '(' )
                        Postfix = Arrays.copyOf( Postfix, Postfix.length-2 );
                    handlePopPush();
                    break ;
                case ')':
                    while( top.ch != '(' ) {
                        pop();
                    }//Pop until reach '(' then stop.
                    delTopStack();
                    break ;
                default:
                    Postfix[j] = ch ;
                    boolean same = false ;
                    for (int l = 0; l < k; l++) {
                        if( ch == Operand[l] ) {
                            same = true ;
                        }//if: find same value, same = true.
                    }//forl: check double value.
                    if( same == false ) {
                        Operand[k] = ch ;
                        k++;
                    }//if: Have this value before, will not add double.
                    j++; //count postfix
            }//switchcase: Mange ch.
            showStack();
            System.out.print("|");
            showPostfix();
            System.out.println("|");
        }//for: loop all string. 
        while( bottom != null ){
            pop();
        }//while: When out of input -> pop stack until stack empty.
        System.out.println("+-----+------+-------------------------+");
        System.out.print("|            |");
        showPostfix();
        System.out.println("|");
        System.out.println("+------------+-------------------------+");
        Operand = Arrays.copyOf(Operand, k) ;
        val.calValue( Operand, Postfix ) ;
    }//method: Calulator to get postfix

    private void handlePopPush(){
        if( bottom != null ){
            while( Priority <= top.priority ) {
                pop();
                if( bottom == null ) break ;
            }//while: Compare priority of input and in stack to excute.
        }//if: stack != empty.
        push() ;
    }//method: Manage pop and push condition priority.
    
    private void pop(){
        Postfix[j] = top.ch ;
        j++;
        //Pop stack -> fill pop to Postfix.
        delTopStack();
    }//method: pop stack.
    
    private void delTopStack(){
        Stack walk = bottom ;
        if( top != bottom ){
            while( walk.link != top ){
                walk = walk.link ;
            }//while: walk != previous top -> walk forward.
            top = walk ;
            top.link = null ;
            //remove last node.
        } else {
            bottom = null ;
            top = null ;
        }//ifelse: Stack have more than 1 node -> remove lastnode / Stack have only 1 node -> set all null.
    }//method: remove last node.
    
    private void push(){
        Stack newnode = new Stack() ;
        if( bottom != null ){
            newnode.ch = ch ;
            top.link = newnode ;
            top = newnode ;
            //set newnode data, connect last node to new node, top point at new node.
        } else {
            newnode.ch = ch ;
            bottom = newnode ;
            top = newnode ;
            //set newnode data, bottom and top point at newnode.
        }//ifelse: stack not empty -> add lastnode / stack empty -> newnode
        SetPriority();
    }//method: push stack.
    
    private void SetPriority(){
        if( bottom != null ){
            switch( top.ch ) {
                case '^' : 
                    top.priority = 3 ;
                    break;
                case '*' : 
                case '%' : 
                case '/' : 
                    top.priority = 2 ;
                    break;
                case '+' : 
                case '-' : 
                    top.priority = 1 ;
                    break;
                case '(' : 
                    top.priority = 0 ;
                    break;
                default: break ;
            }//switchcase: case priority            
        }//if: stack != empty
    }//method: set priority of top.stack
    
    private void showStack(){
        Stack walk = bottom ;
        if( bottom != null ){
            byte n = 0 ;
            while( walk != null ){
                n++;
                System.out.print( walk.ch );
                walk = walk.link ;
                //walk forward 1 step.
            }//walking start at bottom forward to null.
            for (int i = n; i < 6; i++)
                System.out.print( " " );
        } else {
            System.out.print( "Empty " );
        }//ifelse: stack != empty
    }//method.
    
    private void showPostfix(){
        for (int i = 0; i < 25; i++) {
            if( i < j ) System.out.print( Postfix[i] );
            else System.out.print( " " ) ;
        }//for:
    }//method:
    
}//End-class
