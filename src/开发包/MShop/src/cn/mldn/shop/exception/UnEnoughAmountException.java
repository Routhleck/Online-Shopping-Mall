package cn.mldn.shop.exception;

public class UnEnoughAmountException extends Exception {
    public UnEnoughAmountException(String msg){
        super(msg) ;
    }
}
