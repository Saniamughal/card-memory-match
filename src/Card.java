
import javax.swing.*;
public class Card extends JButton{
    private int cardNo;
    private boolean matched = false;


    public void setCardNo(int id){
        cardNo= id;
    }

    public int getCardNo(){
        return cardNo;
    }


    public void setCardsMatched(boolean x){
        matched = x;
    }

    public boolean getCardsMatched(){
        return matched;
    }
}